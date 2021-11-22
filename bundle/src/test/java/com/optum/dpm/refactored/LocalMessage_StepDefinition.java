package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.LocalMessage_page;

import core.CustomDataProvider;

public class LocalMessage_StepDefinition extends LocalMessage_page {
	
	private static final Logger logger = LogManager.getLogger(LocalMessage_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"local-message-hub" })
	public void defaultElementAvailabilityCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		scrollToElement(mydriver, messageSection, logger);
		softAssert.assertTrue(verifyElementExists(logger, stateField, "State Field"));
		getTest().info(
				"Checked if 'state field' is available: " + verifyElementExists(logger, stateField, "State Field"));

		softAssert.assertTrue(verifyElementExists(logger, messageHeader, "Message header field"));
		getTest().info("Checked if 'message header field' is available: "
				+ verifyElementExists(logger, messageHeader, "Message header field"));
		softAssert.assertTrue(verifyElementExists(logger, plusIcon, "plusIcon"));
		getTest().info(
				"Checked if 'plusIcon field' is available: " + verifyElementExists(logger, stateField, "plusIcon"));
		softAssert.assertAll();

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"local-message-hub" })
	public void messageFieldContentCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		scrollToElement(mydriver, messageSection, logger);
		try {
			messageFields.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("The message text field is not authored");
		}
		getTest().info("Checking if the message field is empty: " + messageFields.getAttribute("innerText").isEmpty());
		hardAssert.assertFalse(messageFields.getAttribute("innerText").isEmpty());

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"local-message-hub" })
	public void ctaLabelAvailabitiyCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, messageSection, logger);
		try {
			ctaLink.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Can't execute this TC as there is no CTA Link available");
		}
		getTest().info("Verifying when CTA link is there then if Cta label is there: "
				+ verifyElementExists(logger, ctaButton, "Cta button"));
		hardAssert.assertTrue(verifyElementExists(logger, ctaButton, "Cta button"));

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"local-message-hub" })
	public void ctaLabelUnavailabitiyCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, messageSection, logger);
		try {
			ctaLink.isDisplayed();
			throw new SkipException("Can't execute this TC as the CTA Link is available");
		} catch (NoSuchElementException e) {
			getTest().info("Verifying that if CTA link is not avaialable then if button is visible: "
					+ verifyElementExists(logger, ctaButton, "CTA button"));
			hardAssert.assertFalse(verifyElementExists(logger, ctaButton, "CTA button"));
		}

	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"local-message-hub" })
	public void ctaLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		scrollToElement(mydriver, messageSection, logger);

		try {
			ctaLink.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Can't execute this TC as there is no CTA Link available");
		}
		String expLink = ctaLink.getAttribute("href");
		String handle = mydriver.getWindowHandle();
		ctaButton.click();
		getTest().info("User is redirecting to : " + expLink);
		pleaseWait(5, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}
}
