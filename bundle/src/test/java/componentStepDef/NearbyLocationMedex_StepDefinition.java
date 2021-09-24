package componentStepDef;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.NearbyLocationMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class NearbyLocationMedex_StepDefinition extends NearbyLocationMedex_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(NearbyLocationMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(NearbyLocationMedex_StepDefinition.class.getName());
		new NearbyLocationMedex_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		ExtentTestManager.startTest(NearbyLocationMedex_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("Nearby Locations [Medex]", author, NearbyLocationMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(NearbyLocationMedex_StepDefinition.class);
		logger.info("Urls for '" + NearbyLocationMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(NearbyLocationMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(NearbyLocationMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + NearbyLocationMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
			"near-location section" })
	public void titleFieldAvailabilityCheck(String url) {

		
		mydriver.get(url);
		getWebDriverWait(mydriver, 120).until(ExpectedConditions.visibilityOfAllElements(nearSection));
		scrolltillvisibilityMedex(mydriver, nearSection.get(0), logger);
		if (isElementExists(sectionHeader, "Section Header is not authored", logger, true)) {
			customTestLogs.get().add("Is Title Field visible: " + sectionHeader.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, sectionHeader, "Main Title Field"));
			customTestLogs.get().add("Title: " + sectionHeader.getText());
			hardAssert.assertFalse(sectionHeader.getText().isEmpty());
		}
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"near-location section" })
	public void bgrImageFieldAvailabilityCheck(String url) {

		
		mydriver.get(url);
		getWebDriverWait(mydriver, 120).until(ExpectedConditions.visibilityOfAllElements(nearSection));
		scrolltillvisibilityMedex(mydriver, nearSection.get(0), logger);
		customTestLogs.get().add("is background image field visible: " + sectionImageField.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionImageField, "Background Image"));
		customTestLogs.get().add("Image path: " + sectionImageField.getAttribute("style"));
		hardAssert.assertTrue(isBgrImageAvailable());
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"near-location section" })
	public void centerDataOrErrorAvailabilityCheck(String url) {

		
		mydriver.get(url);
		getWebDriverWait(mydriver, 120).until(ExpectedConditions.visibilityOfAllElements(nearSection));
		scrolltillvisibilityMedex(mydriver, nearSection.get(0), logger);
		if (isElementExists(centerCardSection, "Center Card section is not available", logger, false) == true) {
			customTestLogs.get().add("Is center data visible: " + centerCardSection.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, centerCardSection, "Center data"));
		} else {
			boolean visibility = false;
			for (WebElement error : noCenterSection) {
				if (verifyElementExists(logger, error, error.getAttribute("class"))) {
					visibility = true;
					break;
				}
			}
			if (visibility == false) {
				fail("Neither Center ata is visible nor error message is visible");
			}
		}

	}

}
