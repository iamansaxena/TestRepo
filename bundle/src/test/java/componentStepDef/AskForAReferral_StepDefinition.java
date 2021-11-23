package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.AskForAReferral_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class AskForAReferral_StepDefinition extends AskForAReferral_page {
	private String author = "Rekha Vasugan";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(AskForAReferral_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(AskForAReferral_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new AskForAReferral_page();
		ExtentTestManager.startTest(AskForAReferral_StepDefinition.class.getName());
		setTagForTestClass("ask-for-referral", author, AskForAReferral_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(AskForAReferral_StepDefinition.class);
		logger.info("Urls for '" + AskForAReferral_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(AskForAReferral_StepDefinition.class.getName(), currentDomain);

		driverMap.put(AskForAReferral_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + AskForAReferral_StepDefinition.class.getName() + "' =>\n" + driverMap);
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
			"ask-for-referral" })
	public void elementVisibilityCheck(String url) {
		
		mydriver.get(url);
		hardAssert.assertEquals(verifyElements(mydriver, logger), true);
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"ask-for-referral" })
	public void verifyFormSubmission(String url) {
		
		mydriver.get(url);
		String[] values = { "Name1", "Name2", "1/9/1992", "987-654-3210", "hello@gmail.com", "hello@gmail.com",
				"D'Esposito, Robert F., MD", "doctor", "Pulmonologist", "insurance", "current", "Sickness" };
		hardAssert.assertEquals(enterValuesintheForm(mydriver,values, logger), true);
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"ask-for-referral" })
	public void verifyErrorMessage(String url) {
		
		mydriver.get(url);
		hardAssert.assertEquals(verifyErrorMessagebySubmittingEmptyFields(mydriver,logger), true);
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"ask-for-referral" })
	public void verifyFormat(String url) {
		
		mydriver.get(url);
		getWebDriverWait(mydriver, 50).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='aemFormFrame']")));
		hardAssert.assertEquals(verifyFormat(mydriver,"13/12/1991", "Birth Date (m/d/yyyy) *", "DOB",
				"Field not filled in expected format", logger), true);
		hardAssert.assertEquals(verifyFormat(mydriver,"9876543210", "Phone Number (xxx-xxx-xxxx) *", "Phone Number",
				"Phone Number format is invalid.", logger), true);
		hardAssert.assertEquals(verifyFormat(mydriver,"mail@mail", "Email Address *", "Email", "Email Address format is invalid", logger), true);
		hardAssert.assertEquals(verifyFormat(mydriver,"mail@mail", "Re-enter Email Address *", "ReEnter Email",
				"Re-enter Email Address format is invalid", logger), true);
		hardAssert.assertEquals(verifyDatePickerFormat(mydriver,"Birth Date (m/d/yyyy) *,Appointment Date (m/d/yyyy) *",
				"previous,future", logger), true);
	}

}
