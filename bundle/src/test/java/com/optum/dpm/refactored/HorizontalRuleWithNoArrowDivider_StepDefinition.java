package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.HorizontalRuleWithNoArrowDivider_page;

import core.CustomDataProvider;

public class HorizontalRuleWithNoArrowDivider_StepDefinition extends HorizontalRuleWithNoArrowDivider_page {
	
	private static final Logger logger = LogManager.getLogger(HorizontalRuleWithNoArrowDivider_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"divider divider--arrow false" })
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
				+ verifyElementExists(logger, horzontalRuleArrow, "Arrow Divider does not Exists which is expected"));
		hardAssert.assertTrue(verifyElementExists(logger, horzontalRuleArrow,
				"Does Arrow Divider Exists? ::> " + "No, Arrow Divider does not Exists which is expected"));

	}

}
