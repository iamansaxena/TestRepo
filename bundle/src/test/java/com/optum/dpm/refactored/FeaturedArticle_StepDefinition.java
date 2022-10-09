package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.getVisibility;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;
import static org.testng.Assert.fail;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.FeaturedArticle_page;

import core.CustomDataProvider;

public class FeaturedArticle_StepDefinition extends FeaturedArticle_page {

	private static final Logger logger = LogManager.getLogger(FeaturedArticle_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"featured-articles" })
	public void elementVisibilityCheck(String url) {

		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> cards = mydriver.findElements(By.xpath(FeaturedArticle_page.cards));
		int i = 0;
		for (WebElement card : cards) {
			scrollToElement(mydriver, card, logger);
			WebElement title = mydriver.findElements(By.xpath(FeaturedArticle_page.cardtitle)).get(i);
			WebElement description = mydriver.findElements(By.xpath(FeaturedArticle_page.description)).get(i);
			focusElement(mydriver, description);
			focusElement(mydriver, title);
			// Aman: Commenting this as we description can be blank. Observed on
			// https://uat-www.optum.com/about-us/rory-mcilroy.html
			// if (!description.getText().isEmpty() && !title.getText().isEmpty()) {
			if (!title.getText().isEmpty()) {
				logger.info(" 'Card " + i + " Title: '"
						+ mydriver.findElements(By.xpath(FeaturedArticle_page.cardtitle)).get(i).getText()
						+ "'\n\t Description ==> "
						+ mydriver.findElements(By.xpath(FeaturedArticle_page.description)).get(i).getText());
			} else {
				fail("Featured Article card found with blank Title/Description");
			}
			i++;

		}
	}

	// DISABLING AS CONTENT IS NOT IN SCOPE
	/*
	 * @Test(priority = 2, enabled = true,dataProvider = "data-provider",
	 * dataProviderClass = CustomDataProvider.class, parameters =
	 * {"featured-articles"}) public void brokenImageCheck(String url) {
	 * skipNonExistingComponent(cardUrls); for (String cardUrl : cardUrls) {
	 * customLogsPool.get().add(cardUrl); mydriver.get(cardUrl); List<WebElement>
	 * images = mydriver.findElements(By.xpath(FeaturedArticle_page.cardimage)); int
	 * i = getRandomInteger(images.size(), 0); scrollToElement(mydriver,
	 * images.get(i)); focusElement(mydriver, images.get(i)); String imageLink =
	 * images.get(i).getAttribute("src"); logger.info("Card Image link ==> " +
	 * imageLink); customLogsPool.get().add(cardUrl); mydriver.get(imageLink); if
	 * (mydriver.getTitle().contains("404")) { fail("Broken Image Found"); } } }
	 */

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"featured-articles" })
	public void titleVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		getVisibility(mydriver, title, 10);
		softAssert.assertTrue(verifyElementExists(logger, title, "Featured Article Title"));
		softAssert.assertAll();

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"featured-articles" })
	public void cardredirectionAvailabilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> cards = mydriver.findElements(By.xpath(FeaturedArticle_page.cards));
		int i = 1;
		for (WebElement card : cards) {
			scrollToElement(mydriver, card, logger);
			WebElement cardLink = mydriver
					.findElement(By.xpath("(//*[@class=\"featured-article js-HubItemCard\"]/a)[" + i + "]"));
			if (cardLink.getAttribute("href").isEmpty()) {
				fail("Card Hyperlink is Empty");
			} else {
				logger.info("Redirection Link ==> " + cardLink.getAttribute("href"));
			}
			i++;
		}

	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"featured-articles" })
	public void cardLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> links;

		try {
			mydriver.findElement(By.xpath("//*[@class=\"featured-article js-HubItemCard\"]/a")).isDisplayed();
			links = mydriver.findElements(By.xpath("//*[@class=\"featured-article js-HubItemCard\"]/a"));

		} catch (Exception e) {
			throw new SkipException("No Card Found with link");

		}
		int i = getRandomInteger(links.size(), 0);
		scrollToElement(mydriver, mydriver.findElements(By.xpath(cards)).get(i), logger);
		String myTab = mydriver.getWindowHandle();
		String linkUrl = links.get(i).getAttribute("href");
		links.get(i).click();
		pleaseWait(5, logger);
		assertRedirection(mydriver, logger, getDomainName(url), linkUrl, myTab);
	}

}
