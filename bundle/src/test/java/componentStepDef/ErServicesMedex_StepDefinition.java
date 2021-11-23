package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ErServicesMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ErServicesMedex_StepDefinition extends ErServicesMedex_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(ErServicesMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ErServicesMedex_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new ErServicesMedex_page();
		ExtentTestManager.startTest(ErServicesMedex_StepDefinition.class.getName());
		setTagForTestClass("Er Services [Medex]", author, ErServicesMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ErServicesMedex_StepDefinition.class);
		logger.info("Urls for '" + ErServicesMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ErServicesMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ErServicesMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + ErServicesMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		// mydriver.manage().deleteAllCookies();
	}

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-services" })
	public void mandatoryElementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, erServiceSection, logger);
		customTestLogs.get().add("Checking if main title field is available: "
				+ verifyElementExists(logger, erServiceSectionTitle, "main title"));
		hardAssert.assertTrue(verifyElementExists(logger, erServiceSectionTitle, "main title"));
		customTestLogs.get().add("Title: " + erServiceSectionTitle.getAttribute("innerText"));
		hardAssert.assertFalse(erServiceSectionTitle.getAttribute("innerText").isEmpty());
		customTestLogs.get().add("Checking if main description field is available: "
				+ verifyElementExists(logger, erServiceSectionCopy, "main description"));
		hardAssert.assertTrue(verifyElementExists(logger, erServiceSectionCopy, "main description"));
		customTestLogs.get().add("Description: " + erServiceSectionCopy.getAttribute("innerText"));
		hardAssert.assertFalse(erServiceSectionCopy.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-services" })
	public void medexSectionHeaderVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, erServiceSection, logger);

		try {
			medexSectionHeading.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no header field");
		}

		scrollToElement(mydriver, medexSectionHeading, logger);
		customTestLogs.get().add("Checking if medex section header is available: "
				+ verifyElementExists(logger, medexSectionHeading, "Medex section header"));
		hardAssert.assertTrue(verifyElementExists(logger, medexSectionHeading, "Medex section header"));
		customTestLogs.get().add("Header: " + medexSectionHeading.getAttribute("innerText"));
		hardAssert.assertFalse(medexSectionHeading.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-services" })
	public void medexSectionItemsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, erServiceSection, logger);
		int i = 1;
		int j = 0;
		try {
			medexSectionItemIcon.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no Medex Items available");
		}
		for (WebElement item : medexSectionItemIcon) {
			scrollToElement(mydriver, item, logger);
			customTestLogs.get().add("Checking if medex section icon '" + i + "' is visible: "
					+ verifyElementExists(logger, item, "Medex Section Item Icon '" + i + "'"));
			hardAssert.assertTrue(verifyElementExists(logger, item, "Medex Section Item Icon '" + i + "'"));
			customTestLogs.get()
					.add("Checking if medex section item title '" + i + "' is visible: " + verifyElementExists(logger,
							medexSectionItemLabel.get(j), "Medex Section Item Title '" + i + "'"));
			hardAssert.assertTrue(
					verifyElementExists(logger, medexSectionItemLabel.get(j), "Medex Section Item Title '" + i + "'"));
			customTestLogs.get().add("medex section item title '" + i + "' : " + medexSectionItemLabel.get(j));
			i++;
			j++;
		}
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-services" })
	public void emergencySectionHeaderVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, erServiceSection, logger);

		try {
			medexSectionHeading.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no header field");
		}

		scrollToElement(mydriver, emergencySectionHeading, logger);
		customTestLogs.get().add("Checking if emergency section header is available: "
				+ verifyElementExists(logger, emergencySectionHeading, "emergency section header"));
		hardAssert.assertTrue(verifyElementExists(logger, emergencySectionHeading, "emergency section header"));
		customTestLogs.get().add("Header: " + emergencySectionHeading.getAttribute("innerText"));
		hardAssert.assertFalse(emergencySectionHeading.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-services" })
	public void emergencySectionItemsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, erServiceSection, logger);
		int i = 1;
		int j = 0;
		try {
			emergencySectionItemIcon.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no Medex Items available");
		}
		for (WebElement item : emergencySectionItemIcon) {
			scrollToElement(mydriver, item, logger);
			customTestLogs.get().add("Checking if emergency section icon '" + i + "' is visible: "
					+ verifyElementExists(logger, item, "Emergency Section Item Icon '" + i + "'"));
			hardAssert.assertTrue(verifyElementExists(logger, item, "emergency Section Item Icon '" + i + "'"));
			customTestLogs.get().add(
					"Checking if emergency section item title '" + i + "' is visible: " + verifyElementExists(logger,
							emergencySectionItemLabel.get(j), "Emergency Section Item Title '" + i + "'"));
			hardAssert.assertTrue(verifyElementExists(logger, emergencySectionItemLabel.get(j),
					"Medex Section Item Title '" + i + "'"));
			customTestLogs.get().add("emergency section item title '" + i + "' : " + emergencySectionItemLabel.get(j));
			i++;
			j++;
		}
	}

}
