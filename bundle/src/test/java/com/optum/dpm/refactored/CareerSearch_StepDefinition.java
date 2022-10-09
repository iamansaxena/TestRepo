package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.selectByOptionIndex;
import static com.optum.dpm.utils.DPMTestUtils.selectByOptionName;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.switchToNextTab;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.CareerSearch_page;

import core.CustomDataProvider;

public class CareerSearch_StepDefinition extends CareerSearch_page {
	private static final Logger logger = LogManager.getLogger(CareerSearch_StepDefinition.class);
	public static int waitTime = 60;

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"career-search" })
	public void CareerSearchVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(categorydropdown));
		scrollToElement(mydriver, Careersearchsection, logger);
		softAssert.assertTrue(categorydropdown.isDisplayed());
		getTest().info("Verifying the element present or not : " + categorydropdown.isDisplayed());
		softAssert.assertTrue(locationdropdown.isDisplayed());
		getTest().info("Verifying the element present or not : " + locationdropdown.isDisplayed());
		softAssert.assertTrue(distancedropdown.isDisplayed());

		getTest().info("Verifying the element present or not : " + distancedropdown.isDisplayed());
		softAssert.assertTrue(Searchbutton.isEnabled());
		getTest().info("Verifying the element present or not : " + Searchbutton.isEnabled());

		softAssert.assertTrue(Searchbutton.isDisplayed());

		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"career-search" })
	public void CareerSearchCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(categorydropdown));
		selectByOptionIndex(categorydropdown, 2, logger);
		locationdropdown.sendKeys("23456");
		selectByOptionIndex(distancedropdown, 2, logger);
		Searchbutton.click();
		wait.until(ExpectedConditions.visibilityOf(Careeropp));
		softAssert.assertTrue(Searchresult.isDisplayed());
		getTest().info("Verifying the element present or not : " + Searchresult.isDisplayed());
		softAssert.assertTrue(Careeropp.isDisplayed());

		getTest().info("Verifying the element present or not : " + Careeropp.isDisplayed());
		softAssert.assertAll();

		softAssert.assertAll();

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"career-search" })
	public void CareerSearchmandatoryfieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(categorydropdown));
		Searchbutton.click();
		wait.until(ExpectedConditions.visibilityOf(Mandatorycategorymsg));
		getTest().info("Checking the error message is displayed or not: "
				+ verifyElementExists(logger, Mandatorycategorymsg, "Please select a job category."));
		softAssert.assertTrue(verifyElementExists(logger, Mandatorycategorymsg, "Please select a job category."));
		getTest().info("Checking the error message is displayed or not: "
				+ verifyElementExists(logger, Mandatorylocationmsg, "Please enter a valid location."));
		softAssert.assertTrue(verifyElementExists(logger, Mandatorylocationmsg, "Please enter a valid location."));
		softAssert.assertAll();
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"career-search" })
	public void CareerSearchviewPositionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(categorydropdown));
		selectByOptionName(logger, categorydropdown, "Physicians");
		locationdropdown.sendKeys("Eden Prairie,MN");
		selectByOptionIndex(distancedropdown, 2, logger);
		Searchbutton.click();
		wait.until(ExpectedConditions.visibilityOf(Viewposition));
		hardAssert.assertTrue(Viewposition.isDisplayed());

		getTest().info("Verify the the Viewposition button componet is displayed: " + Viewposition.isDisplayed());
		softAssert.assertAll();

	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"career-search" })
	public void CareersOptumCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		String handle = mydriver.getWindowHandle();
		WebDriverWait wait = new WebDriverWait(mydriver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(categorydropdown));
		selectByOptionName(logger, categorydropdown, "Physicians");
		locationdropdown.sendKeys("Eden Prairie,MN");
		selectByOptionIndex(distancedropdown, 3, logger);
		Searchbutton.click();
		wait.until(ExpectedConditions.visibilityOf(Viewposition));
		Viewposition.click();
		switchToNextTab(mydriver, logger, handle);
		wait.until(ExpectedConditions.visibilityOf(Searchjobbtn));
		hardAssert.assertTrue(Searchjobbtn.isDisplayed());
		getTest().info("Verify the the search button componet is displayed: " + Searchjobbtn.isDisplayed());
		softAssert.assertAll();

	}

}
