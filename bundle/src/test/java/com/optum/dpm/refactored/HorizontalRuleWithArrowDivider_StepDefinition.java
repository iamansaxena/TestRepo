package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.HorizontalRuleArrowDivider_page;

import core.CustomDataProvider;

public class HorizontalRuleWithArrowDivider_StepDefinition extends HorizontalRuleArrowDivider_page {
	
	private static final Logger logger = LogManager.getLogger(AccordionV2_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"divider divider--arrow true" })
	public void horizontalRuleCheck(String url) {
		skipNonExistingComponent(url);
		
		mydriver.get(url);
		scrollToElement(mydriver, horzontalRuleSection, logger);
		focusElement(mydriver, horzontalRuleHeader);
		getTest().info("Checking if Arrow Divider Exists: "
				+ verifyElementExists(logger, horzontalRuleHeader, "Header is displayed as expected"));
		hardAssert.assertTrue(
				verifyElementExists(logger, horzontalRuleHeader, "horzontalRule ::> " + horzontalRuleHeader.getText()));
		hardAssert.assertFalse(horzontalRuleHeader.getText().isEmpty());
		getTest().info("Display the Header in logger '" + horzontalRuleHeader.getText());
		focusElement(mydriver, horzontalRuleArrow);
		getTest().info("Checking if Arrow Divider Exists: "
				+ verifyElementExists(logger, horzontalRuleArrow, "Yes, Arrow Divider Exists"));
		hardAssert.assertTrue(verifyElementExists(logger, horzontalRuleArrow, "Yes, Arrow Divider Exists"));
	}

}
