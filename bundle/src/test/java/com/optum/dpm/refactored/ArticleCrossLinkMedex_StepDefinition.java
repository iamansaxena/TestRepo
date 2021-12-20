package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.scrolltillvisibilityMedex;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ArticleCrossLinkMedex_page;

import core.CustomDataProvider;

public class ArticleCrossLinkMedex_StepDefinition extends ArticleCrossLinkMedex_page {
	
	private static final Logger logger = LogManager.getLogger(ArticleCrossLinkMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-link1 article-xlink" })
	public void titleVisibilityCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, section, logger);
		ifElementExists(sectionTitle, "Section title is not authored");
		getTest().info("Is title field visible: " + sectionTitle.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionTitle, "Section Title"));
		getTest().info("Title: " + sectionTitle.getText());
		hardAssert.assertFalse(sectionTitle.getText().isEmpty());

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-link1 article-xlink" })
	public void copyVisibilityCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, section, logger);
		ifElementExists(sectionCopy, "Section copy is not authored");
		getTest().info("Is Copy field visible: " + sectionCopy.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionCopy, "Section Copy"));
		getTest().info("Copy: " + sectionCopy.getText());
		hardAssert.assertFalse(sectionCopy.getText().isEmpty());

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-link1 article-xlink" })
	public void ctaFieldRedirectionCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, section, logger);

		ifElementExists(sectionCtaLink, "Section link is not authored");
		getTest().info("Is link field visible: " + sectionCtaLink.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionCtaLink, "Section cta field"));
		getTest().info("CTA Label: " + sectionCtaLink.getText());
		hardAssert.assertFalse(sectionCtaLink.getText().isEmpty());
		hardAssert.assertFalse(sectionCtaLink.getAttribute("href").isEmpty());
		String expLink = sectionCtaLink.getAttribute("href");
		sectionCtaLink.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, mydriver.getWindowHandle());

	}

}
