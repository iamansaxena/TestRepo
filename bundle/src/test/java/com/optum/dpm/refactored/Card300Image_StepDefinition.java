package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static org.testng.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.Card300Image_page;

import core.CustomDataProvider;

public class Card300Image_StepDefinition extends Card300Image_page {
	
	private static final Logger logger = LogManager.getLogger(Card300Image_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mcard__image300" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> cards = mydriver.findElements(By.xpath(Card300Image_page.cardsXpath));
		int i = 0;
		for (WebElement card : cards) {
			scrollToElement(mydriver, card, logger);
			WebElement title = mydriver.findElements(By.xpath(Card300Image_page.titlesXpath)).get(i);
			WebElement content = mydriver.findElements(By.xpath(Card300Image_page.contentXpath)).get(i);
			focusElement(mydriver, content);
			focusElement(mydriver, title);
			if (!title.getText().isEmpty()) {
				logger.info("Card -1 Title: '"
						+ mydriver.findElements(By.xpath(Card300Image_page.titlesXpath)).get(i).getText()
						+ "'\n\t Content ==> "
						+ mydriver.findElements(By.xpath(Card300Image_page.contentXpath)).get(i).getText());
			} else {
				fail("Material card found with blank Title/Content");
			}
			i++;
		}

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mcard__image300" })
	public void card300BrokenImageCheck(String url) {

		skipNonExistingComponent(url);

		
		mydriver.get(url);

		List<WebElement> images = mydriver.findElements(By.xpath(Card300Image_page.imagesXpath));
		int i = getRandomInteger(images.size(), 0);
		scrollToElement(mydriver, images.get(i), logger);
		String imageLink = images.get(i).getAttribute("src");
		logger.info("Card Image Link ==>" + imageLink);
		try {
			URL imgUrl = new URL(imageLink);
			logger.info("Image Link => " + imgUrl);
		} catch (MalformedURLException e) {
			fail("Image link is invalid on material Image detail card");
			logger.error("Image link is invalid on material Image detail card");
		}

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mcard__image300" })
	public void card300LinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> links;

		try {
			scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@qa-handle=\"mcard-image300\"]//*[@href]")),
					logger);
		} catch (Exception e) {
			throw new SkipException("No Card Found with link");

		}
		links = mydriver.findElements(By.xpath("//*[@qa-handle=\"mcard-image300\"]//*[@href]"));
		int i = getRandomInteger(links.size(), 0);
		scrollToElement(mydriver, links.get(i), logger);
		String myTab = mydriver.getWindowHandle();
		String linkUrl = links.get(i).getAttribute("href");
		links.get(i).click();
		pleaseWait(5, logger);
		assertRedirection(mydriver, logger, getDomainName(url), linkUrl, myTab);

	}

}