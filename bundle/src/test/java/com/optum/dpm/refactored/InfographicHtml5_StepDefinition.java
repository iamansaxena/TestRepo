package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.InfographicHtml5_page;

import core.CustomDataProvider;

public class InfographicHtml5_StepDefinition extends InfographicHtml5_page {
	
	private static final Logger logger = LogManager.getLogger(InfographicHtml5_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"infographic-html5" })
	public void javaScriptFileShouldNotBeBlank(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		hardAssert.assertFalse(scripts.getAttribute("src").isEmpty());
		pleaseWait(10, logger);
	}

}