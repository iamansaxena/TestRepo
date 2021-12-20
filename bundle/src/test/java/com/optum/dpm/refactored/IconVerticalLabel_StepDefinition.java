package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.IconVerticalLabel_page;

import core.CustomDataProvider;

public class IconVerticalLabel_StepDefinition extends IconVerticalLabel_page {
	
	private static final Logger logger = LogManager.getLogger(IconVerticalLabel_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"icon-div-text" })
	public void blankHeaderCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		try {
			scrollToElement(mydriver, headline, logger);
		} catch (NoSuchElementException e) {
			throw new SkipException("No headline field is available");
		}
		hardAssert.assertTrue(verifyElementExists(logger, headline, "headline"));
		hardAssert.assertFalse(headline.getText().isEmpty());
		logger.info("Icon vertical label : " + headline.getText());

	}

}
