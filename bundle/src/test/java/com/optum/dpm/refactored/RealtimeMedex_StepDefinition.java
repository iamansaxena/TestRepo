package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.RealtimeMedex_page;

import core.CustomDataProvider;

public class RealtimeMedex_StepDefinition extends RealtimeMedex_page{
	
	private static final Logger logger = LogManager.getLogger(RealtimeMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"realtime"})
	public void imageFieldAvailabilityCheck(String url) {

			
			mydriver.get(url);
			scrollToElement(mydriver, realtimeSection, logger);
			if(ifElementExists(imageSection, "Image field not authored")) {
				getTest().info("Is image field visible: "+imageSection.isDisplayed());
				hardAssert.assertTrue(verifyElementExists(logger, imageSection, "Image field"));
				getTest().info("dusk image: "+imageSection.getAttribute("data-dusk"));
				hardAssert.assertFalse(imageSection.getAttribute("data-dusk").isEmpty());
				getTest().info("day image: "+imageSection.getAttribute("data-day"));
				hardAssert.assertFalse(imageSection.getAttribute("data-day").isEmpty());
			}
	}

}
