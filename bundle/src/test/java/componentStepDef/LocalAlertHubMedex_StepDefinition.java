package componentStepDef;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.LocalAlertHubMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class LocalAlertHubMedex_StepDefinition extends LocalAlertHubMedex_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(LocalAlertHubMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(LocalAlertHubMedex_StepDefinition.class.getName());
		new LocalAlertHubMedex_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		ExtentTestManager.startTest(LocalAlertHubMedex_StepDefinition.class.getName());
		setTagForTestClass("Local Alert Hub", author, LocalAlertHubMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(LocalAlertHubMedex_StepDefinition.class);
		logger.info("Urls for '" + LocalAlertHubMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(LocalAlertHubMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(LocalAlertHubMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + LocalAlertHubMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();

	}

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"local-alert-hub"})
	public void defaultElementVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);
			mydriver.get(cardUrl);
			scrollToElement(mydriver, alertSection, logger);
			customTestLogs.get().add("Is aler icon visible: " + alertIcon.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, alertIcon, "Alert Icon"));
	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"local-alert-hub"})
	public void alertMessageVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);
			mydriver.get(cardUrl);
			scrollToElement(mydriver, alertSection, logger);
			if (isMessageFieldAvailable()) {
				customTestLogs.get().add("Is alert message field visible: " + alertMessage.isDisplayed());
				customTestLogs.get().add("Alert Message: " + alertMessage.getText());
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
				customTestLogs.get().add("Is tag field visible: " + alertTags.isDisplayed());
				customTestLogs.get().add("Tags: " + alertTags.getText());
				softAssert.assertTrue(verifyElementExists(logger, alertTags, "Tag Field"));
				softAssert.assertFalse(alertTags.getText().isEmpty());
			}
	}}
