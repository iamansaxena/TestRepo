package com.optum.dpm.refactored;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.SkipException;
import org.testng.annotations.Test;
import static com.optum.dpm.utils.DPMTestUtils.*;

import com.optum.dpm.page.model.LoginBanner_page;

import core.CustomDataProvider;

public class LoginBanner_StepDefinition extends LoginBanner_page {
	private static final Logger logger = LogManager.getLogger(LoginBanner_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"login-banner" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, loginBannerSection, logger);
		hardAssert.assertTrue(isElementPresent(mainTitle, mydriver, "Main Title", logger));
		hardAssert.assertTrue(isElementBlank(mainTitle, mydriver, "Main Title", logger));

		hardAssert.assertTrue(isElementPresent(mainSubHeading, mydriver, "Main Sub heading", logger));
		hardAssert.assertTrue(isElementBlank(mainSubHeading, mydriver, "Main Sub heading", logger));

		hardAssert.assertTrue(isElementPresent(enrollNowCtaButton, mydriver, "Enroll Now", logger));
		hardAssert.assertTrue(isElementBlank(enrollNowCtaButton, mydriver, "Enroll Now", logger));

		hardAssert.assertTrue(isElementPresent(registerCtaLink, mydriver, "Register Link", logger));
		hardAssert.assertTrue(isElementBlank(registerCtaLink, mydriver, "Register Link", logger));

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"login-banner" })
	public void loginoptionsCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, loginBannerSection, logger);

		try {
			isElementPresent(loginOptionButton, mydriver, "login Option Button", logger);
		} catch (NoSuchElementException e) {
			throw new SkipException("No login options button authored");
		}
		hardAssert.assertTrue(isElementPresent(loginOptionButton, mydriver, "login Option Button", logger));
		hardAssert.assertFalse(loginOptionButton.getText().isEmpty());
		String handle = mydriver.getWindowHandle();
		String expLink = loginOptionButton.getAttribute("href");
		loginOptionButton.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"login-banner" })
	public void enrollNowCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, loginBannerSection, logger);
		hardAssert.assertTrue(isElementPresent(enrollNowCtaButton, mydriver, "Enroll Now Button", logger));
		hardAssert.assertTrue(isElementBlank(enrollNowCtaButton, mydriver, "Enroll Now Button", logger));
		String handle = mydriver.getWindowHandle();
		String expLink = enrollNowCtaButton.getAttribute("href");
		enrollNowCtaButton.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"login-banner" })
	public void registrationLinkCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, loginBannerSection, logger);
		hardAssert.assertTrue(isElementPresent(registerCtaLink, mydriver, "Registration Link", logger));
		hardAssert.assertTrue(isElementBlank(registerCtaLink, mydriver, "Registration Link", logger));
		String handle = mydriver.getWindowHandle();
		String expLink = registerCtaLink.getAttribute("href");
		registerCtaLink.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

}
