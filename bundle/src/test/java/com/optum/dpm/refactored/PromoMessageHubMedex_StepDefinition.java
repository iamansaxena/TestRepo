package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.PromoMessageHubMedex_Page;

import core.CustomDataProvider;

public class PromoMessageHubMedex_StepDefinition extends PromoMessageHubMedex_Page{

	private static final Logger logger = LogManager.getLogger(PromoMessageHubMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"promo-message-hub"})
	public void elementVisibilityCheck(String url){
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			scrollToElement(mydriver, promoSection, logger);
			getTest().info("Verifying if mandatory icon visible: " + promoIcon.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, promoIcon, "Promo Icon"));
			

	}

	
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"promo-message-hub"})
	public void messageFieldVisibilityCheck(String url){
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			scrollToElement(mydriver, promoSection, logger);
			getTest().info("Verifying if message field visible: " + isMessageFieldsVisible());
			hardAssert.assertTrue(isMessageFieldsVisible());

	}


	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"promo-message-hub"})
	public void tagsFieldVisibilityCheck(String url){
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			scrollToElement(mydriver, promoSection, logger);
			getTest().info("Verifying if tag field visible: " + isTagFieldsVisible());
			hardAssert.assertTrue(isTagFieldsVisible());
		}

	
	
}
