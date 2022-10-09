package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.EmployeeCarouselMedex_page;

import core.CustomDataProvider;

public class EmployeeCarouselMedex_StepDefinition extends EmployeeCarouselMedex_page {
	private static final Logger logger = LogManager.getLogger(EmployeeCarouselMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"employee-carousel" })
	public void employeesDetailsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility();
		try {
			employeeColumns.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no employee column available");
		}

		getTest().info("Employee Columns available" + employeeColumns.size());
		int i = 0;
		int j = 1;
		for (WebElement empColumn : employeeColumns) {
			scrollToEmployeeColumn(empColumn);
			getTest().info("Verifying employee column details for card : '" + j + "'");
			softAssert.assertTrue(verifyElementExists(logger, employeeHeaders.get(i), "employee Header '" + j + "'"));
			softAssert.assertTrue(
					verifyElementExists(logger, employeeDesignations.get(i), "employee designation '" + j + "'"));
			softAssert.assertTrue(verifyElementExists(logger, employeeDetails.get(i), "employee details '" + j + "'"));
			softAssert.assertTrue(verifyElementExists(logger, employeeImages.get(i), "employee images '" + j + "'"));
			softAssert
					.assertTrue(verifyElementExists(logger, employeeLocations.get(i), "employee location '" + j + "'"));
			assertEmployeeQuestion(j, empColumn, logger);

			i++;
			j++;
		}

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"employee-carousel" })
	public void sliderFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility();

		if (employeeColumns.size() > 2) {
			int i = getRandomInteger(employeeColumns.size() - 1, 0);
			int j = i + 1;
			logger.info("selected employee column for next button: '" + j + "'");
			slideCarouselNext(i);
			getTest().info("Checking if next button working fine");
			hardAssert.assertTrue(verifyElementExists(logger, employeeColumns.get(i), "Column no. '" + j + "'"));
			i = getRandomInteger(employeeColumns.size() - 1, 0);
			logger.info("selected employee column for prev button: '" + j + "'");
			slideCarouselPrev(i);
			j = i + 1;
			getTest().info("Checking if prev button working fine");
			hardAssert.assertTrue(verifyElementExists(logger, employeeColumns.get(i), "Column no. '" + j + "'"));
		}

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"employee-carousel" })
	public void sliderEndlessLoopCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility();
		if (employeeColumns.size() > 2) {
			carouselNextButton.click();
			slideCarouselNext(0);
			getTest().info("Checking if endless loop is working fine");
			hardAssert.assertTrue(verifyElementExists(logger, employeeColumns.get(0), "Column no. '" + 1 + "'"));
		}

	}

}
