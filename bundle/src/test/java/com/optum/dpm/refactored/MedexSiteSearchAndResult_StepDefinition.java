package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.getWebDriverWait;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.MedexSiteSearchAndResult_page;

import core.CustomDataProvider;

public class MedexSiteSearchAndResult_StepDefinition extends MedexSiteSearchAndResult_page {

	private static final Logger logger = LogManager.getLogger(LocalMessage_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void elementVisiblityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			getTest().info("Is search input field visible: " + searchInputField.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, searchInputField, "Search Input Field"));
			getTest().info("Is search button visible" + searchButton.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, searchButton, "Search Button"));
			getTest().info("Is placeholder text available" + !(searchInputField.getAttribute("innerText").isEmpty()));
			softAssert.assertFalse(searchInputField.getAttribute("placeholder").isEmpty());
			softAssert.assertAll();

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void headerAndDirectionalCopyCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			boolean header = false;
			boolean directionalCopy = false;
			try {
				headingField.isDisplayed();
				header = true;
			} catch (Exception e) {
			}

			try {
				directionalCopyField.isDisplayed();
				directionalCopy = true;
			} catch (Exception e) {
			}

			if (header == true) {
				getTest().info("Header: " + headingField.getAttribute("innerText"));
				softAssert.assertFalse(headingField.getAttribute("innerText").isEmpty());
			}
			if (directionalCopy == true) {
				getTest().info("Directional Copy: " + directionalCopyField.getAttribute("innerText"));
				softAssert.assertFalse(directionalCopyField.getAttribute("innerText").isEmpty());
			}


	
	}

	
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void searchSuggestionCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			searchInputField.sendKeys("s");
			getWebDriverWait(mydriver, 5).until(ExpectedConditions.visibilityOf(searchSugestionBox));
			getTest().info("Is Search suggestion box visible: "+ searchSugestionBox.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, searchSugestionBox, "search suggestion box"));
			

	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void searchSuggestionFunctionalityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			searchInputField.sendKeys("s");
			getWebDriverWait(mydriver, 5).until(ExpectedConditions.visibilityOf(searchSugestionBox));
			int i = getRandomInteger(searchSugestionItems.size(),0);
			String selectedSearchOption = searchSugestionLabel.get(i).getAttribute("innerText");
			getTest().info("Input Selected from suggestion: "+ selectedSearchOption);
			searchSugestionLabel.get(i).click();
			getTest().info("Landing page header: "+ resultPageInfoHeader.getAttribute("innerText"));
			hardAssert.assertTrue(resultPageInfoHeader.getAttribute("innerText").contains(selectedSearchOption));

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void searchFunctionalityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			String selectedSearchOption = "s";
			searchInputField.sendKeys(selectedSearchOption);
			getTest().info("Search Input: "+ selectedSearchOption);
			searchButton.click();
			getTest().info("Landing page header: "+ resultPageInfoHeader.getAttribute("innerText"));
			hardAssert.assertTrue(resultPageInfoHeader.getAttribute("innerText").contains(selectedSearchOption));

	}


	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void noResultsSearchFunctionalityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			String selectedSearchOption = "fdfdfdfdofofofofofofofofofofofofoofoeoreoreoroeroo";
			searchInputField.sendKeys(selectedSearchOption);
			getTest().info("Search Input: "+ selectedSearchOption);
			searchButton.click();
			getTest().info("Landing page header: "+ resultPageInfoHeader.getAttribute("innerText"));
			pleaseWait(3, logger);
			hardAssert.assertTrue(isNoResultMessageAvailable(logger));

	}


	@Test(priority = 7, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void paginationFunctionalityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			String selectedSearchOption = "s";
			searchInputField.sendKeys(selectedSearchOption);
			getTest().info("Search Input: "+ selectedSearchOption);
			searchButton.click();
			getTest().info("Landing page header: "+ resultPageInfoHeader.getAttribute("innerText"));
			try {
				paginationSection.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("No pagination Found");
			}
			hardAssert.assertTrue(moveToNextPage(logger));
			hardAssert.assertTrue(moveToPrevPage(logger));
			
//			hardAssert.assertTrue();

	}






	@Test(priority = 8, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void searchResultItemCountCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			String selectedSearchOption = "s";
			searchInputField.sendKeys(selectedSearchOption);
			getTest().info("Search Input: "+ selectedSearchOption);
			searchButton.click();
			getTest().info("Landing page header: "+ resultPageInfoHeader.getAttribute("innerText"));
			hardAssert.assertEquals(Integer.valueOf(noOfResultItems.getAttribute("innerText")).intValue(),getResultCountFromFooter(logger));

	}








}
