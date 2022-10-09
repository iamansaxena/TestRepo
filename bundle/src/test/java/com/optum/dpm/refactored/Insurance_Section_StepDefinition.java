package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.selectByValue;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.Insurance_Section_page;

import core.CustomDataProvider;

public class Insurance_Section_StepDefinition extends Insurance_Section_page {

	private static final Logger logger = LogManager.getLogger(Insurance_Section_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"insurance-section" })
	public void InsuranceVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, 30);
		wait.until(ExpectedConditions.visibilityOf(Insurancesection));
		scrollToElement(mydriver, Insurancesection, logger);
		boolean abc = false;
		softAssert.assertTrue(InsHeading.isDisplayed());
		softAssert.assertTrue(InsStatdropdown.isDisplayed());
		softAssert.assertTrue(Searchbtn.isDisplayed());
		softAssert.assertAll();
		if (abc != (InsHeading.isDisplayed() && InsStatdropdown.isDisplayed() && Searchbtn.isDisplayed())) {
			abc = true;
		}

		getTest().info("Verifing the elememt is displayed: " + abc);

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"insurance-section" })
	public void InsuranceStateSuccessSearch(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, 30);
		wait.until(ExpectedConditions.visibilityOf(Insurancesection));
		scrollToElement(mydriver, Insurancesection, logger);
		softAssert.assertTrue(InsHeading.isDisplayed());
		softAssert.assertTrue(InsStatdropdown.isDisplayed());
		selectByValue(logger, InsStatdropdown, "FL");
		softAssert.assertTrue(Searchbtn.isDisplayed());
		Searchbtn.click();
		pleaseWait(6, logger);
		Select comboBox = new Select(InsStatdropdown);
		String selectedComboValue = comboBox.getFirstSelectedOption().getText();
		// getTest().info("Verifingsearch result:
		// "+Successstatename.getAttribute("innerText").toString().equals(selectedComboValue));
		getTest().info("Verifying search result: " + Successstatename.getAttribute("innerText").toString());
		hardAssert.assertTrue(Successstatename.getAttribute("innerText").toString().contains(selectedComboValue));
		softAssert.assertAll();

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"insurance-section" })
	public void NoInsuranceSearch(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, 30);
		wait.until(ExpectedConditions.visibilityOf(Insurancesection));
		scrollToElement(mydriver, Insurancesection, logger);
		softAssert.assertTrue(InsHeading.isDisplayed());
		softAssert.assertTrue(InsStatdropdown.isDisplayed());
		selectByValue(logger, InsStatdropdown, "NV");
		softAssert.assertTrue(Searchbtn.isDisplayed());
		Searchbtn.click();
		pleaseWait(6, logger);
		Select comboBox = new Select(InsStatdropdown);
		String selectedComboValue = comboBox.getFirstSelectedOption().getText();
		hardAssert.assertEquals(NoInsurancestatename.getText().toString().substring(3), selectedComboValue);
		getTest().info("Verifingsearch result: "
				+ NoInsurancestatename.getText().toString().substring(3).equals(selectedComboValue));
		softAssert.assertAll();

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"insurance-section" })
	public void PromtPaySection(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, 30);
		wait.until(ExpectedConditions.visibilityOf(Insurancesection));
		scrollToElement(mydriver, Insurancesection, logger);
		softAssert.assertTrue(Promt_pay.isDisplayed());
		softAssert.assertTrue(Promt_pay_desc.isDisplayed());
		softAssert.assertAll();
		boolean abc = false;
		if (abc != (Promt_pay.isDisplayed() && Promt_pay_desc.isDisplayed())) {
			abc = true;
		}

		getTest().info("Verifing the elememt is displayed: " + abc);

	}

}
