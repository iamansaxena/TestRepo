package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.CtaModuleMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class CtaModuleMedex_StepDefinition extends CtaModuleMedex_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(CtaModuleMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(CtaModuleMedex_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new CtaModuleMedex_page();
		ExtentTestManager.startTest(CtaModuleMedex_StepDefinition.class.getName());
		setTagForTestClass("Cta Module [Medex]", author, CtaModuleMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(CtaModuleMedex_StepDefinition.class);
		logger.info("Urls for '" + CtaModuleMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(CtaModuleMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(CtaModuleMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + CtaModuleMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
	}

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cta-module" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, ctaModuleSection, logger);

		try {
			customTestLogs.get().add(
					"Verifying if Title Field is available: " + verifyElementExists(logger, ctaModuleTitle, "title"));
			ctaModuleTitle.isDisplayed();
			customTestLogs.get().add("Title: " + ctaModuleTitle.getAttribute("innerText"));
			softAssert.assertTrue(verifyElementExists(logger, ctaModuleTitle, "title"));
		} catch (Exception e) {
		}

		try {
			customTestLogs.get().add(
					"Verifying if Title Field is available: " + verifyElementExists(logger, ctaModuleCopy, "Copy"));
			ctaModuleCopy.isDisplayed();
			customTestLogs.get().add("Copy: " + ctaModuleCopy.getAttribute("innerText"));
			softAssert.assertTrue(verifyElementExists(logger, ctaModuleCopy, "Copy"));
		} catch (Exception e) {
		}

		try {
			customTestLogs.get().add("Verifying if Title Field is available: "
					+ verifyElementExists(logger, ctaModuleCtaLabel, "CTA Label"));
			ctaModuleCtaLabel.isDisplayed();
			customTestLogs.get().add("Label: " + ctaModuleCtaLabel.getAttribute("innerText"));
			softAssert.assertTrue(verifyElementExists(logger, ctaModuleCtaLabel, "CTA Label"));
		} catch (Exception e) {
		}
		softAssert.assertAll();

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cta-module" })
	public void ctaLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, ctaModuleSection, logger);
		try {
			ctaModuleCtaLink.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no Cta Link authored");
		}

		String handle = mydriver.getWindowHandle();
		String expLink = ctaModuleCtaLink.getAttribute("href");
		scrollToElement(mydriver, ctaModuleCtaLink, logger);
		customTestLogs.get().add("Link under CTA Button: " + expLink);
		scrolltillvisibilityMedex(mydriver, ctaModuleCtaLink, logger);
		ctaModuleCtaLink.click();
		pleaseWait(5, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
		customTestLogs.get().add("Is user being redirected to correct page: true");

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cta-module" })
	public void ctaLinkAndLabelFieldsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, ctaModuleSection, logger);
		customTestLogs.get().add("Verifying that Label should only be visible when there's a link available");
		try {
			ctaModuleCtaLabel.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no Cta label authored");
		}
		scrollToElement(mydriver, ctaModuleCtaLink, logger);
		customTestLogs.get().add("Assertion passed? : " + !(ctaModuleCtaLink.getAttribute("href").isEmpty()));
		hardAssert.assertFalse(ctaModuleCtaLink.getAttribute("href").isEmpty());

	}

}
