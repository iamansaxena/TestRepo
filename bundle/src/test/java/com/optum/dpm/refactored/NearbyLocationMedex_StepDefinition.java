package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.getWebDriverWait;
import static com.optum.dpm.utils.DPMTestUtils.isElementExists;
import static com.optum.dpm.utils.DPMTestUtils.scrolltillvisibilityMedex;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;
import static org.junit.Assert.fail;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.NearbyLocationMedex_page;

import core.CustomDataProvider;

public class NearbyLocationMedex_StepDefinition extends NearbyLocationMedex_page {
	
	private static final Logger logger = LogManager.getLogger(NearbyLocationMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"near-location section" })
	public void titleFieldAvailabilityCheck(String url) {

		
		mydriver.get(url);
		getWebDriverWait(mydriver, 120).until(ExpectedConditions.visibilityOf(nearSection));
		scrolltillvisibilityMedex(mydriver, nearSection, logger);
		if (isElementExists(sectionHeader, "Section Header is not authored", logger, true)) {
			getTest().info("Is Title Field visible: " + sectionHeader.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, sectionHeader, "Main Title Field"));
			getTest().info("Title: " + sectionHeader.getText());
			hardAssert.assertFalse(sectionHeader.getText().isEmpty());
		}
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"near-location section" })
	public void bgrImageFieldAvailabilityCheck(String url) {

		
		mydriver.get(url);
		getWebDriverWait(mydriver, 120).until(ExpectedConditions.visibilityOf(nearSection));
		scrolltillvisibilityMedex(mydriver, nearSection, logger);
		getTest().info("is background image field visible: " + sectionImageField.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionImageField, "Background Image"));
		getTest().info("Image path: " + sectionImageField.getAttribute("style"));
		hardAssert.assertTrue(isBgrImageAvailable());
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"near-location section" })
	public void centerDataOrErrorAvailabilityCheck(String url) {

		
		mydriver.get(url);
		getWebDriverWait(mydriver, 120).until(ExpectedConditions.visibilityOf(nearSection));
		scrolltillvisibilityMedex(mydriver, nearSection, logger);
		if (isElementExists(centerCardSection, "Center Card section is not available", logger, false) == true) {
			getTest().info("Is center data visible: " + centerCardSection.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, centerCardSection, "Center data"));
		} else {
			boolean visibility = false;
			for (WebElement error : noCenterSection) {
				if (verifyElementExists(logger, error, error.getAttribute("class"))) {
					visibility = true;
					break;
				}
			}
			if (visibility == false) {
				fail("Neither Center ata is visible nor error message is visible");
			}
		}

	}

}
