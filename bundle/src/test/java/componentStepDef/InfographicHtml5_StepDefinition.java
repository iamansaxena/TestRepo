package componentStepDef;import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.InfographicHtml5_page;
import runner.TestListener;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class InfographicHtml5_StepDefinition extends InfographicHtml5_page{
	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(InfographicHtml5_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(InfographicHtml5_StepDefinition.class.getName());
<<<<<<< Updated upstream
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
=======
>>>>>>> Stashed changes
		new InfographicHtml5_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("infographic-html5") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5642:8080/content/AutomationDirectory/infographic.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("http://apvrt31468:4503/content/AutomationDirectory/infographic.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("infographic-html5").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(InfographicHtml5_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("Infographic html5", author, currentDomain, InfographicHtml5_StepDefinition.class.getName());
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

	@Test(priority = 1, enabled = true)
	public void javaScriptFileShouldNotBeBlank() {
		skipNonExistingComponent(cardUrls);
		
		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			hardAssert.assertFalse(scripts.getAttribute("src").isEmpty());
			pleaseWait(10, logger);
		}
	}
	
	
}