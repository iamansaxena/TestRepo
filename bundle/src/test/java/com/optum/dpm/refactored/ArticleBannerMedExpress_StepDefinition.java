package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ArticleBannerMedExpress_page;

import core.CustomDataProvider;

public class ArticleBannerMedExpress_StepDefinition extends ArticleBannerMedExpress_page {
	private static final Logger logger = LogManager.getLogger(ArticleBannerMedExpress_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"article-banner" })
	public void elementVisibilityCheck(String cardUrl) {		
		skipNonExistingComponent(cardUrl);
		mydriver.get(cardUrl);
		hardAssert.assertEquals(verifyElements(logger), true);
	}
	
}


	/*@Test(priority = 1, enabled = true,  dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"article-banner"})
	public void elementVisibilityCheck(String cardUrl) {
			skipNonExistingComponent(cardUrl);
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			hardAssert.assertEquals(verifyElements(logger), true);
	}*/

	

