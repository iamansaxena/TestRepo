package com.optum.dpm.refactored;
import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertStaticRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.Sharebar_page;

import core.CustomDataProvider;

public class Sharebar_StepDefinition extends Sharebar_page {

	private static final Logger logger = LogManager.getLogger(Sharebar_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"share-bar"})
	public void supportedShareOptionsAvailabilityCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, sharebarContainer, logger);
			try {
				facebookLink.isDisplayed();
				hardAssert.assertTrue(facebookLink.getAttribute("href").contains("facebook"));
			} catch (Exception e) {
				getTest().info("facebook share option not available");
			}
			try {
				linkedinLink.isDisplayed();
				hardAssert.assertTrue(linkedinLink.getAttribute("href").contains("linkedin"));
			} catch (Exception e) {
				getTest().info("LinkedIn share option not available");
			}

			try {
				emailShareLink.isDisplayed();
				hardAssert.assertTrue(emailShareLink.getAttribute("href").contains("mailto"));
			} catch (Exception e) {
				getTest().info("Email share option not available");
			}
			try {
				pinterestLink.isDisplayed();
				hardAssert.assertTrue(pinterestLink.getAttribute("href").contains("pinterest"));
			} catch (Exception e) {
				getTest().info("Pinterest share option not available");
			}
			try {
				twitterLink.isDisplayed();
				hardAssert.assertTrue(twitterLink.getAttribute("href").contains("twitter"));
			} catch (Exception e) {
				getTest().info("Twitter share option not available");
			}

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"share-bar"})
	public void shareInNewTabCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			String baseTab = mydriver.getWindowHandle();
			scrollToElement(mydriver, sharebarContainer, logger);

			try {
				facebookLink.click();
				pleaseWait(4, logger);
				hardAssert.assertEquals(mydriver.getWindowHandles().size(), 2);
				getBackToBaseTab(baseTab);
			} catch (Exception e) {
				getTest().info("facebook share option is not available");
			}

			try {
				linkedinLink.click();
				pleaseWait(4, logger);
				hardAssert.assertEquals(mydriver.getWindowHandles().size(), 2);
				getBackToBaseTab(baseTab);
			} catch (Exception e) {
				getTest().info("LinkedIn share option is not available");
			}

			try {
				pinterestLink.click();
				pleaseWait(4, logger);
				hardAssert.assertEquals(mydriver.getWindowHandles().size(), 2);
				getBackToBaseTab(baseTab);
			} catch (Exception e) {
				getTest().info("Pinterest share option is not available");
			}
			try {
				twitterLink.click();
				pleaseWait(4, logger);
				hardAssert.assertEquals(mydriver.getWindowHandles().size(), 2);
				getBackToBaseTab(baseTab);
			} catch (Exception e) {
				getTest().info("Twitter share option is not available");
			}


	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"share-bar"})
	public void redirectionCheck(String url) {
		skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			scrollToElement(mydriver, sharebarContainer, logger);
			String handle = mydriver.getWindowHandle();			
			try {
				facebookLink.click();
				pleaseWait(4, logger);
				assertStaticRedirection(mydriver, logger, getDomainName(url), "https://www.facebook.com","facebook", handle);
			} catch (Exception e) {
				getTest().info("facebook share option is not available");
			}

			try {
				linkedinLink.click();
				pleaseWait(4, logger);
				assertStaticRedirection(mydriver, logger, getDomainName(url), "https://www.linkedin.com",
						"linkedin", handle);
			} catch (Exception e) {
				getTest().info("LinkedIn share option is not available");
			}

			try {
				pinterestLink.click();
				pleaseWait(4, logger);
				assertStaticRedirection(mydriver, logger, getDomainName(url), "https://www.pinterest.com",
						"pinterest", handle);
			} catch (Exception e) {
				getTest().info("Pinterest share option is not available");
			}
			try {
				twitterLink.click();
				pleaseWait(4, logger);
				assertStaticRedirection(mydriver, logger, getDomainName(url), "https://twitter.com", "twitter",
						handle);
			} catch (Exception e) {
				getTest().info("Twitter share option is not available");
			}
//			}
	}
}
