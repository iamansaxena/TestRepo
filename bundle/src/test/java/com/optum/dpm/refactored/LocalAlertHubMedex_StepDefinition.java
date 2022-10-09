package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.LocalAlertHubMedex_page;

import core.CustomDataProvider;

public class LocalAlertHubMedex_StepDefinition extends LocalAlertHubMedex_page {

	private static final Logger logger = LogManager.getLogger(LocalAlertHubMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"local-alert-hub"})
	public void defaultElementVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);
			mydriver.get(cardUrl);
			scrollToElement(mydriver, alertSection, logger);
			getTest().info("Is aler icon visible: " + alertIcon.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, alertIcon, "Alert Icon"));
	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"local-alert-hub"})
	public void alertMessageVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);
			mydriver.get(cardUrl);
			scrollToElement(mydriver, alertSection, logger);
			if (isMessageFieldAvailable()) {
				getTest().info("Is alert message field visible: " + alertMessage.isDisplayed());
				getTest().info("Alert Message: " + alertMessage.getText());
				softAssert.assertTrue(verifyElementExists(logger, alertMessage, "Alert Message Field"));
				softAssert.assertFalse(alertMessage.getText().isEmpty());
			}
	}
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"local-alert-hub"})
	public void alertTagVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);
			mydriver.get(cardUrl);
			scrollToElement(mydriver, alertSection, logger);
			if (isTagFieldAvailable()) {
				getTest().info("Is tag field visible: " + alertTags.isDisplayed());
				getTest().info("Tags: " + alertTags.getText());
				softAssert.assertTrue(verifyElementExists(logger, alertTags, "Tag Field"));
				softAssert.assertFalse(alertTags.getText().isEmpty());
			}
	}}
