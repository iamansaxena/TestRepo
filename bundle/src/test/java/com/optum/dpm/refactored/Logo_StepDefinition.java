package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.getVisibility;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.Logo_page;

import core.CustomDataProvider;
import utils.BrokenLinks;

public class Logo_StepDefinition extends Logo_page {

	private static final Logger logger = LogManager.getLogger(Logo_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"logo" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		softAssert.assertFalse(logoImg.getAttribute("alt").isEmpty());
		softAssert.assertFalse(logoLink.getAttribute("href").isEmpty());
		softAssert.assertFalse(favIcon.getAttribute("data-favicon").isEmpty());
		softAssert.assertAll();

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"logo" })
	public void brokenLogoImageCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		getVisibility(mydriver, logo, 20);
		hardAssert.assertTrue(verifyElementExists(logger, logoImg, "Logo Image"));
		hardAssert.assertEquals(BrokenLinks.isBroken(logoImg.getAttribute("src")), 200);

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"logo" })
	public void blankTagLineCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		try {
			getVisibility(mydriver, logoTagLine, 20);
			logoTagLine.isDisplayed();

		} catch (NoSuchElementException | TimeoutException e) {
			throw new SkipException("There's no tag-line field");
		}
		hardAssert.assertFalse(logoTagLine.getText().isEmpty());

	}
	/*
	 * @Test(priority = 4, enabled = true) public void logoRedirectionCheck() {
	 * skipNonExistingComponent(urls); for (String url : urls) {
	 * customLogsPool.get().add(url); mydriver.get(url); String expURL =
	 * logoLink.getAttribute("href").split("https://")[1]; scrollToElement(mydriver,
	 * logoImg).click(); pleaseWait(4, logger); Set<String> handles =
	 * mydriver.getWindowHandles(); if(handles.size()!=1) {
	 * handles.remove(mydriver.getWindowHandle()); String newTabHandle=
	 * handles.iterator().next(); mydriver.switchTo().window(newTabHandle);}
	 * hardAssert.assertEquals(mydriver.getCurrentUrl().split("www.")[1], expURL);
	 * 
	 * 
	 * } }
	 */

}
