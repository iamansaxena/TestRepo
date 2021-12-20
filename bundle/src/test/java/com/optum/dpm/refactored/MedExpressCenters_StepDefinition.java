package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.scrolltillvisibilityMedex;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.MedExpressCenters_page;

import core.CustomDataProvider;

public class MedExpressCenters_StepDefinition extends MedExpressCenters_page {

	private static final Logger logger = LogManager.getLogger(MedExpressCenters_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"centers-sitemap"})
	public void elementVisibilityCheck(String url){
		skipNonExistingComponent(url);
		
			 
			mydriver.get(url);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(medExpressCenterSection));
			scrollToElement(mydriver, medExpressCenterSection, logger);
			getTest().info("Verify Med Express Center Section : " + medExpressCenterSection.isDisplayed());
			getTest().info("Verify Med Express Center : " + medExpresscenterList.get(0).isDisplayed());
			hardAssert.assertEquals(medExpressCenterSection.isDisplayed(), true);
			hardAssert.assertEquals(medExpresscenterList.get(0).isDisplayed(), true);
	}
	
	@Test(priority =2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"centers-sitemap"})
	public void verifyCenterDetails(String url){
			mydriver.get(url);
			scrolltillvisibilityMedex(mydriver, medExpressCenterSection, logger);
			WebDriverWait wait = new WebDriverWait(mydriver,60);
			wait.until(ExpectedConditions.visibilityOf(medExpressCenterSection));
			scrollToElement(mydriver, medExpressCenterSection, logger);
			hardAssert.assertEquals(verifyCenterList(mydriver , logger), true);
	}
	
}

