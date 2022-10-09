package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ForumHeroBanner_page;

import core.CustomDataProvider;

public class ForumHeroBanner_StepDefinition extends ForumHeroBanner_page {

	private static final Logger logger = LogManager.getLogger(ForumHeroBanner_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void backgroundImageCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);

		try {
			bannerBgImageField.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no bakground image");
		}
		getTest().info("verifying if background image is visible : "
				+ verifyElementExists(logger, bannerBgImageField, "background image"));
		hardAssert.assertTrue(verifyElementExists(logger, bannerBgImageField, "background image"));
		getTest().info("verifying if background image has some image path : " + bannerBgImageField.getAttribute("style"));
		hardAssert.assertFalse(bannerBgImageField.getAttribute("style").contains("url()"));

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void breadcrumbFieldRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			breadcrumbField.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no breadcrumb field available");
		}

		getTest().info("Breadcrumb field link : " + breadcrumbField.getAttribute("href"));
		hardAssert.assertFalse(breadcrumbField.getAttribute("href").isEmpty());
		String handle = mydriver.getWindowHandle();
		String expLink = breadcrumbField.getAttribute("href");
		breadcrumbField.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void logoFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			logoField.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no Logo field available");
		}

		getTest().info("Is Logo field available: " + verifyElementExists(logger, logoField, "Logo"));
		hardAssert.assertTrue(verifyElementExists(logger, logoField, "Logo"));
		getTest().info("Logo field alt text: " + logoField.getAttribute("alt"));
		hardAssert.assertFalse(logoField.getAttribute("alt").isEmpty());

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void bannerHeadingFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			bannerHeading.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no banner heading field available");
		}
		getTest().info("Is Banner heading visible: " + verifyElementExists(logger, bannerHeading, "Banner heading"));
		hardAssert.assertTrue(verifyElementExists(logger, bannerHeading, "Banner heading"));
		getTest().info("Heading: " + bannerHeading.getAttribute("innerText"));
		hardAssert.assertFalse(bannerHeading.getAttribute("innerText").isEmpty());

	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void topLineFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			bannerFirstLine.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no banner top line field available");
		}
		getTest().info("Is Top line visible: " + verifyElementExists(logger, bannerFirstLine, "top line"));
		hardAssert.assertTrue(verifyElementExists(logger, bannerFirstLine, "top line"));
		getTest().info("First Line text: " + bannerFirstLine.getAttribute("innerText"));
		hardAssert.assertFalse(bannerFirstLine.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 6, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void secondLineFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			bannerFirstLine.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no banner second line field available");
		}
		getTest().info("Is Second line visible: " + verifyElementExists(logger, bannerSecondLine, "Second line"));
		hardAssert.assertTrue(verifyElementExists(logger, bannerSecondLine, "Second line"));
		getTest().info("Second Line text: " + bannerSecondLine.getAttribute("innerText"));
		hardAssert.assertFalse(bannerSecondLine.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 7, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void thirdLineFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			bannerThirdLine.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no banner third line field available");
		}
		getTest().info("Is Third line visible: " + verifyElementExists(logger, bannerThirdLine, "Third line"));
		hardAssert.assertTrue(verifyElementExists(logger, bannerThirdLine, "Third line"));
		getTest().info("Third Line text: " + bannerThirdLine.getAttribute("innerText"));
		hardAssert.assertFalse(bannerThirdLine.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 8, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void ctaButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			bannerCtaButton.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no cta field available");
		}
		getTest().info("Is CTA button visible : " + verifyElementExists(logger, bannerCtaButton, "Cta button"));
		getTest().info("CTA button link : " + bannerCtaButton.getAttribute("href"));

		hardAssert.assertFalse(bannerCtaButton.getAttribute("href").isEmpty());
		String handle = mydriver.getWindowHandle();
		String expLink = bannerCtaButton.getAttribute("href");
		bannerCtaButton.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

}
