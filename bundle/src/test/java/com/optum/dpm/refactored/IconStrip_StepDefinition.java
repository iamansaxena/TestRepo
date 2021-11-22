package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.getVisibility;
import static com.optum.dpm.utils.DPMTestUtils.jsWaitForPageToLoad;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;
import static org.testng.Assert.fail;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.IconStrip_page;

import core.CustomDataProvider;

public class IconStrip_StepDefinition extends IconStrip_page {

	private static final Logger logger = LogManager.getLogger(IconStrip_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"icon-strip" })
	public void elementVisibilityCheck(String url) {

		skipNonExistingComponent(url);

		
		mydriver.get(url);

		List<WebElement> cards = mydriver.findElements(By.xpath(IconStrip_page.segments));
		int i = 0;
		for (WebElement card : cards) {
			scrollToElement(mydriver, card, logger);
			WebElement title = mydriver.findElements(By.xpath(IconStrip_page.segmenttitle)).get(i);
			WebElement icon = mydriver.findElements(By.xpath(IconStrip_page.segmenticon)).get(i);
			focusElement(mydriver, icon);
			focusElement(mydriver, title);
			if (!title.getText().isEmpty()) {
				logger.info(" 'Segment " + i + " Title: '"
						+ mydriver.findElements(By.xpath(IconStrip_page.segmenttitle)).get(i).getText());
			} else {
				fail("Icon Strip segment found with blank Title");
			}
			i++;

		}
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"icon-strip" })
	public void titleVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		getVisibility(mydriver, title, 10);
		softAssert.assertTrue(verifyElementExists(logger, title, "Icon Segment Title"));
		softAssert.assertAll();
		if (IconStrip_page.title.getText() != null) {
			logger.info("Icon Strip Title ==>" + title.getText());
		} else {
			fail("Icon Strip segment has missing header");
		}
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"icon-strip" })
	public void cardredirectionAvailabilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

		List<WebElement> segments = mydriver.findElements(By.xpath(IconStrip_page.segments));
		int i = 1;
		for (WebElement segment : segments) {
			scrollToElement(mydriver, segment, logger);
			WebElement segmentLink = mydriver.findElement(By.xpath("(//*[@class=\"iconstrip__item\"]/a)[" + i + "]"));
			if (segmentLink.getAttribute("href").isEmpty()) {
				fail("Segment Hyperlink is Empty");
			} else {
				logger.info("Redirection Link ==> " + segmentLink.getAttribute("href"));
			}
			i++;
		}

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"icon-strip" })
	public void cardLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

		List<WebElement> links;

		try {
			scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@class=\"iconstrip__item\"]/a")), logger);
			links = mydriver.findElements(By.xpath("//*[@class=\"iconstrip__item\"]/a"));

		} catch (Exception e) {
			throw new SkipException("No Segment Found with link");

		}
		int i = getRandomInteger(links.size(), 0);
		scrollToElement(mydriver, links.get(i), logger);
		String linkUrl = links.get(i).getAttribute("href");
		// getActions(mydriver).click(links.get(i)).perform();
		String handle = mydriver.getWindowHandle();
		links.get(i).click();
		pleaseWait(4, logger);
		jsWaitForPageToLoad(10, mydriver, logger);
		assertRedirection(mydriver, logger, getDomainName(url), linkUrl, handle);
	}

}