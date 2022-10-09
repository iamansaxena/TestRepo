package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.IntroTextHorizontal_page;

import core.CustomDataProvider;

public class IntroTextHorizontal_StepDefinition extends IntroTextHorizontal_page {
	
	private static final Logger logger = LogManager.getLogger(IntroTextHorizontal_StepDefinition.class);

	/*
	 * @Test(priority = 2, enabled = true,dataProvider = "data-provider",
	 * dataProviderClass = CustomDataProvider.class, parameters =
	 * {"intro-arrowtext"}) public void blankHeaderCheck(String cardUrl) {
	 * HashMap<String, Boolean> assertConditionMap =
	 * skipNonExistingComponent(cardUrl);
	 * 
	 * 
	 * mydriver.get(cardUrl); String expURL =
	 * mydriver.findElement(By.xpath(iconField)).getAttribute("href");
	 * assertRedirection(mydriver, logger, getDomainName(cardUrl), expURL); } }
	 */

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"intro-arrowtext" })
	public void longDividerFieldCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		mydriver.get(cardUrl);

		try {
			scrollToElement(mydriver, longDivider, logger);
		} catch (Exception e) {
			throw new SkipException("There's  no long divider field available");
		}
		getTest().info("Checking if Long Divider Field is displying: "
				+ verifyElementExists(logger, longDivider, "Long Divider Field"));
		hardAssert.assertTrue(verifyElementExists(logger, longDivider, "Long Divider Field"));
		getTest().info("Value for Long Divider Field: " + longDivider.getAttribute("innerText"));
		hardAssert.assertFalse(longDivider.getAttribute("innerText").isEmpty());

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"intro-arrowtext" })
	public void introDirectionalFieldCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		mydriver.get(cardUrl);

		try {
			scrollToElement(mydriver, introDirectionalCopy, logger);
		} catch (Exception e) {
			throw new SkipException("There's no intro directional field available");
		}
		getTest().info("Checking if Intro Directional Field is displying: "
				+ verifyElementExists(logger, introDirectionalCopy, "Intro Directional Field"));
		hardAssert.assertTrue(verifyElementExists(logger, introDirectionalCopy, "Intro Directional Field"));
		getTest().info("Value for Intro Directional Field: " + introDirectionalCopy.getAttribute("innerText"));
		hardAssert.assertFalse(introDirectionalCopy.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"intro-arrowtext" })
	public void shortDividerFieldCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		mydriver.get(cardUrl);

		try {
			scrollToElement(mydriver, shortDivider, logger);
		} catch (Exception e) {
			throw new SkipException("There's no short divider field available");
		}
		getTest().info("Checking if Intro Directional Field is displying: "
				+ verifyElementExists(logger, shortDivider, "Short Divider Field"));
		hardAssert.assertTrue(verifyElementExists(logger, shortDivider, "Short Divider Field"));
		getTest().info("Value for Short Divider Field: " + shortDivider.getAttribute("innerText"));
		hardAssert.assertFalse(shortDivider.getAttribute("innerText").isEmpty());
	}
}
