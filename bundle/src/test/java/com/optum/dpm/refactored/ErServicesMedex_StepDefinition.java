package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ErServicesMedex_page;

import core.CustomDataProvider;

public class ErServicesMedex_StepDefinition extends ErServicesMedex_page {
	private static final Logger logger = LogManager.getLogger(ErServicesMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-services" })
	public void mandatoryElementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, erServiceSection, logger);
		getTest().info("Checking if main title field is available: "
				+ verifyElementExists(logger, erServiceSectionTitle, "main title"));
		hardAssert.assertTrue(verifyElementExists(logger, erServiceSectionTitle, "main title"));
		getTest().info("Title: " + erServiceSectionTitle.getAttribute("innerText"));
		hardAssert.assertFalse(erServiceSectionTitle.getAttribute("innerText").isEmpty());
		getTest().info("Checking if main description field is available: "
				+ verifyElementExists(logger, erServiceSectionCopy, "main description"));
		hardAssert.assertTrue(verifyElementExists(logger, erServiceSectionCopy, "main description"));
		getTest().info("Description: " + erServiceSectionCopy.getAttribute("innerText"));
		hardAssert.assertFalse(erServiceSectionCopy.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-services" })
	public void medexSectionHeaderVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, erServiceSection, logger);

		try {
			medexSectionHeading.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no header field");
		}

		scrollToElement(mydriver, medexSectionHeading, logger);
		getTest().info("Checking if medex section header is available: "
				+ verifyElementExists(logger, medexSectionHeading, "Medex section header"));
		hardAssert.assertTrue(verifyElementExists(logger, medexSectionHeading, "Medex section header"));
		getTest().info("Header: " + medexSectionHeading.getAttribute("innerText"));
		hardAssert.assertFalse(medexSectionHeading.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-services" })
	public void medexSectionItemsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, erServiceSection, logger);
		int i = 1;
		int j = 0;
		try {
			medexSectionItemIcon.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no Medex Items available");
		}
		for (WebElement item : medexSectionItemIcon) {
			scrollToElement(mydriver, item, logger);
			getTest().info("Checking if medex section icon '" + i + "' is visible: "
					+ verifyElementExists(logger, item, "Medex Section Item Icon '" + i + "'"));
			hardAssert.assertTrue(verifyElementExists(logger, item, "Medex Section Item Icon '" + i + "'"));
			getTest().info("Checking if medex section item title '" + i + "' is visible: " + verifyElementExists(logger,
							medexSectionItemLabel.get(j), "Medex Section Item Title '" + i + "'"));
			hardAssert.assertTrue(
					verifyElementExists(logger, medexSectionItemLabel.get(j), "Medex Section Item Title '" + i + "'"));
			getTest().info("medex section item title '" + i + "' : " + medexSectionItemLabel.get(j));
			i++;
			j++;
		}
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-services" })
	public void emergencySectionHeaderVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, erServiceSection, logger);

		try {
			medexSectionHeading.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no header field");
		}

		scrollToElement(mydriver, emergencySectionHeading, logger);
		getTest().info("Checking if emergency section header is available: "
				+ verifyElementExists(logger, emergencySectionHeading, "emergency section header"));
		hardAssert.assertTrue(verifyElementExists(logger, emergencySectionHeading, "emergency section header"));
		getTest().info("Header: " + emergencySectionHeading.getAttribute("innerText"));
		hardAssert.assertFalse(emergencySectionHeading.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-services" })
	public void emergencySectionItemsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, erServiceSection, logger);
		int i = 1;
		int j = 0;
		try {
			emergencySectionItemIcon.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no Medex Items available");
		}
		for (WebElement item : emergencySectionItemIcon) {
			scrollToElement(mydriver, item, logger);
			getTest().info("Checking if emergency section icon '" + i + "' is visible: "
					+ verifyElementExists(logger, item, "Emergency Section Item Icon '" + i + "'"));
			hardAssert.assertTrue(verifyElementExists(logger, item, "emergency Section Item Icon '" + i + "'"));
			getTest().info(
					"Checking if emergency section item title '" + i + "' is visible: " + verifyElementExists(logger,
							emergencySectionItemLabel.get(j), "Emergency Section Item Title '" + i + "'"));
			hardAssert.assertTrue(verifyElementExists(logger, emergencySectionItemLabel.get(j),
					"Medex Section Item Title '" + i + "'"));
			getTest().info("emergency section item title '" + i + "' : " + emergencySectionItemLabel.get(j));
			i++;
			j++;
		}
	}

}
