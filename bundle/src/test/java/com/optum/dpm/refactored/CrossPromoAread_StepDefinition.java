package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.jsWaitForPageToLoad;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.CrossPromoAread_page;

import core.CustomDataProvider;

public class CrossPromoAread_StepDefinition extends CrossPromoAread_page {

	private static final Logger logger = LogManager.getLogger(CrossPromoAread_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-promo-area" })
	public void minimumNumberOfArticlesCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> articleContainers = mydriver.findElements(By.xpath(articles));
		scrollToElement(mydriver, articleContainers.get(0), logger);
		logger.info("No of articles => " + articleContainers.size());
		hardAssert.assertTrue(articleContainers.size() > 2);

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-promo-area" })
	public void tagFieldsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> articleContainers = mydriver.findElements(By.xpath(articles));
		List<WebElement> tags = mydriver.findElements(By.xpath(tagField));
		scrollToElement(mydriver, articleContainers.get(0), logger);
		hardAssert.assertEquals(tags.size(), articleContainers.size());
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-promo-area" })
	public void articleRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> reirectionLinks = mydriver.findElements(By.xpath(articleLinks));
		int i = getRandomInteger(reirectionLinks.size() - 1, 0);
		String expLink = reirectionLinks.get(i).getAttribute("href");
		logger.info("Selected Article link => " + expLink);
		String handle = mydriver.getWindowHandle();
		scrollToElement(mydriver, reirectionLinks.get(i), logger).click();
		pleaseWait(5, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
		;

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-promo-area" })
	public void mainSectionButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		String expLink = null;
		String handle = mydriver.getWindowHandle();
		try {
			expLink = mainButton.getAttribute("href");
		} catch (NoSuchElementException e) {
			throw new SkipException("There's no Button to check redirection");
		}
		scrollToElement(mydriver, mainButton, logger);
		mainButton.click();
		pleaseWait(4, logger);
		jsWaitForPageToLoad(30, mydriver, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-promo-area" })
	public void sectionHeaderCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		String text;
		try {
			text = scrollToElement(mydriver, sectionTitle, logger).getText();
		} catch (Exception e) {
			throw new SkipException("There is no section title field available!");
		}

		hardAssert.assertFalse(text.isEmpty());
		logger.info("Section Header => " + text);

	}
}
