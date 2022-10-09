package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.scrolltillvisibilityMedex;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.CentersContentMedex_page;

import core.CustomDataProvider;

public class CentersContentMedex_StepDefinition extends CentersContentMedex_page {

	private static final Logger logger = LogManager.getLogger(CentersContentMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-content" })
	public void defaultCopyFieldVisibilityCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, contentSection, logger);
		getTest().info("Is branded copy field available: " + brandedCopy.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, brandedCopy, "Branded copy field"));
		getTest().info("Branded Copy: " + brandedCopy.getText());
		hardAssert.assertFalse(brandedCopy.getText().isEmpty());
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-content" })
	public void backgroundFieldAvailabilityCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, contentSection, logger);
		getTest().info("Is Image Field is available: " + sectionImage.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionImage, "Section Image"));

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-content" })
	public void brandedLinkLabelAndRedirectionCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, contentSection, logger);
		try {
			brandedHyperlink.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Hyperlink not authored");
		}
		getTest().info("Is Image Field is available: " + brandedHyperlink.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, brandedHyperlink, "Branded hyperLink and Label"));
		getTest().info("Hyperlink: " + brandedHyperlink.getAttribute("href"));
		hardAssert.assertFalse(brandedHyperlink.getAttribute("href").isEmpty());
		getTest().info("Label: " + brandedHyperlink.getAttribute("innerText"));
		hardAssert.assertFalse(brandedHyperlink.getAttribute("innerText").isEmpty());
		String expLink = brandedHyperlink.getAttribute("href");
		brandedHyperlink.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, mydriver.getWindowHandle());

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-content" })
	public void internalTextComponentCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, contentSection, logger);
		try {
			sectionTextComponent.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Hyperlink not authored");
		}
		getTest().info("Is Text content field available: " + sectionTextComponentContent.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionTextComponentContent, "Text content field"));
		getTest().info("Content: " + sectionTextComponentContent.getText());
		hardAssert.assertFalse(sectionTextComponentContent.getText().isEmpty());
	}

}
