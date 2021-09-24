package componentStepDef;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.RealtimeMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class RealtimeMedex_StepDefinition extends RealtimeMedex_page{
	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(RealtimeMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(RealtimeMedex_StepDefinition.class.getName());
		new RealtimeMedex_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		ExtentTestManager.startTest(RealtimeMedex_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("Realtime [Medex]", author, RealtimeMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(RealtimeMedex_StepDefinition.class);
		logger.info("Urls for '" + RealtimeMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(RealtimeMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(RealtimeMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + RealtimeMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
	}

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"realtime"})
	public void imageFieldAvailabilityCheck(String url) {

			
			mydriver.get(url);
			scrollToElement(mydriver, realtimeSection, logger);
			if(ifElementExists(imageSection, "Image field not authored")) {
				customTestLogs.get().add("Is image field visible: "+imageSection.isDisplayed());
				hardAssert.assertTrue(verifyElementExists(logger, imageSection, "Image field"));
				customTestLogs.get().add("dusk image: "+imageSection.getAttribute("data-dusk"));
				hardAssert.assertFalse(imageSection.getAttribute("data-dusk").isEmpty());
				customTestLogs.get().add("day image: "+imageSection.getAttribute("data-day"));
				hardAssert.assertFalse(imageSection.getAttribute("data-day").isEmpty());
			}
	}

}
