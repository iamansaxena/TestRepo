package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.NewsroomMedex_page;

import core.CustomDataProvider;

public class NewsroomMedex_StepDefinition extends NewsroomMedex_page {

	private static final Logger logger = LogManager.getLogger(NewsroomMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"newsroom"})
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrolltillvisibility();
			getTest().info("Verifying the presence of section title field: " + sectionTitle.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, sectionTitle, "Section Title"));
			getTest().info("Title => " + sectionTitle.getText());
			hardAssert.assertFalse(sectionTitle.getText().isEmpty());
			getTest().info("Verifying the presence of section description field: " + sectionTitle.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, sectionTitle, "Section Description"));
			getTest().info("Description => " + sectionDescription.getText());
			hardAssert.assertFalse(sectionDescription.getText().isEmpty());
	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"newsroom"})
	public void ctaButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrolltillvisibility();
			try {
				sectionCtaButton.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("No CTA Button available");
			}

			String handle = mydriver.getWindowHandle();
			String expLink = sectionCtaButton.getAttribute("href");
			sectionCtaButton.click();
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"newsroom"})
	public void newsCardRedirectionCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrolltillvisibility();
			int i = 0;
			if (newsCards.size() > 0) {
				newsCards.get(0).isDisplayed();
				i = getRandomInteger(newsCards.size(), 0);
			} else {
				throw new SkipException("No news card available");
			}

			String handle = mydriver.getWindowHandle();
			String expLink = newsCards.get(i).getAttribute("href");
			scrollToElement(mydriver, newsCards.get(i), logger).click();
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

}
