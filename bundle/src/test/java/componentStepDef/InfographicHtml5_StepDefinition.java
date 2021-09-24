package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.InfographicHtml5_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class InfographicHtml5_StepDefinition extends InfographicHtml5_page {
	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(InfographicHtml5_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(InfographicHtml5_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new InfographicHtml5_page();
		ExtentTestManager.startTest(InfographicHtml5_StepDefinition.class.getName());
		setTagForTestClass("Infographic html5", author, InfographicHtml5_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(InfographicHtml5_StepDefinition.class);
		logger.info("Urls for '" + InfographicHtml5_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(InfographicHtml5_StepDefinition.class.getName(), currentDomain);

		driverMap.put(InfographicHtml5_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + InfographicHtml5_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@BeforeMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		mydriver.manage().deleteAllCookies();
	}

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"infographic-html5" })
	public void javaScriptFileShouldNotBeBlank(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		hardAssert.assertFalse(scripts.getAttribute("src").isEmpty());
		pleaseWait(10, logger);
	}

}