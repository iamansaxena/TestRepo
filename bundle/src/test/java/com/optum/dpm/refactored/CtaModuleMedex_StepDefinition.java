package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.scrolltillvisibilityMedex;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.CtaModuleMedex_page;

import core.CustomDataProvider;

public class CtaModuleMedex_StepDefinition extends CtaModuleMedex_page {

	private static final Logger logger = LogManager.getLogger(CtaModuleMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cta-module" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, ctaModuleSection, logger);

		try {
			getTest().info(
					"Verifying if Title Field is available: " + verifyElementExists(logger, ctaModuleTitle, "title"));
			ctaModuleTitle.isDisplayed();
			getTest().info("Title: " + ctaModuleTitle.getAttribute("innerText"));
			softAssert.assertTrue(verifyElementExists(logger, ctaModuleTitle, "title"));
		} catch (Exception e) {
		}

		try {
			getTest().info(
					"Verifying if Title Field is available: " + verifyElementExists(logger, ctaModuleCopy, "Copy"));
			ctaModuleCopy.isDisplayed();
			getTest().info("Copy: " + ctaModuleCopy.getAttribute("innerText"));
			softAssert.assertTrue(verifyElementExists(logger, ctaModuleCopy, "Copy"));
		} catch (Exception e) {
		}

		try {
			getTest().info("Verifying if Title Field is available: "
					+ verifyElementExists(logger, ctaModuleCtaLabel, "CTA Label"));
			ctaModuleCtaLabel.isDisplayed();
			getTest().info("Label: " + ctaModuleCtaLabel.getAttribute("innerText"));
			softAssert.assertTrue(verifyElementExists(logger, ctaModuleCtaLabel, "CTA Label"));
		} catch (Exception e) {
		}
		softAssert.assertAll();

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cta-module" })
	public void ctaLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, ctaModuleSection, logger);
		try {
			ctaModuleCtaLink.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no Cta Link authored");
		}

		String handle = mydriver.getWindowHandle();
		String expLink = ctaModuleCtaLink.getAttribute("href");
		scrollToElement(mydriver, ctaModuleCtaLink, logger);
		getTest().info("Link under CTA Button: " + expLink);
		scrolltillvisibilityMedex(mydriver, ctaModuleCtaLink, logger);
		ctaModuleCtaLink.click();
		pleaseWait(5, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
		getTest().info("Is user being redirected to correct page: true");

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cta-module" })
	public void ctaLinkAndLabelFieldsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, ctaModuleSection, logger);
		getTest().info("Verifying that Label should only be visible when there's a link available");
		try {
			ctaModuleCtaLabel.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no Cta label authored");
		}
		scrollToElement(mydriver, ctaModuleCtaLink, logger);
		getTest().info("Assertion passed? : " + !(ctaModuleCtaLink.getAttribute("href").isEmpty()));
		hardAssert.assertFalse(ctaModuleCtaLink.getAttribute("href").isEmpty());

	}

}
