package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.getVisibility;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ArticleInPage_page;

import core.CustomDataProvider;

public class ArticleInPage_StepDefinition extends ArticleInPage_page {
	private static final Logger logger = LogManager.getLogger(ArticleInPage_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"article-in-page-nav" })
	public void navigationTabStaticPositionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> subsections = mydriver.findElements(By.xpath(subSection));
		scrollToElement(mydriver, subsections.get(subsections.size() - 1), logger);
		hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElement(By.xpath(navTabs)), "Navigation Tabs"));

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"article-in-page-nav" })
	public void tabsHighlightOnScrollCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		pleaseWait(5, logger);
		List<WebElement> subsections = mydriver.findElements(By.xpath(subSection));
		List<WebElement> navigationsTabs = mydriver.findElements(By.xpath(navTabs));

		int i = getRandomInteger(subsections.size(), 0);
		scrollToElement(mydriver, subsections.get(i), logger);
		hardAssert.assertTrue(navigationsTabs.get(i).getAttribute("class").equals("active"));

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"article-in-page-nav" })
	public void scrollToSectionOnClickCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		pleaseWait(4, logger);
		getVisibility(mydriver, mydriver.findElements(By.xpath(subSection)).get(0), 30);
		List<WebElement> subsections = mydriver.findElements(By.xpath(subSection));
		List<WebElement> navigationsTabs = mydriver.findElements(By.xpath(navTabs));

		int i = getRandomInteger(navigationsTabs.size(), 0);
		scrollToElement(mydriver, navigationsTabs.get(i), logger).click();
		pleaseWait(4, logger);
		hardAssert.assertTrue(verifyElementExists(logger, subsections.get(i), "Sub-section"));
		logger.info("User scrolled successfully!");

	}

}
