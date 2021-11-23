package componentStepDef;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.HorizontalRuleWithNoArrowDivider_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class HorizontalRuleWithNoArrowDivider_StepDefinition extends HorizontalRuleWithNoArrowDivider_page {
	private String author = "Sai Tummala";
	private static Logger logger;
	private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(HorizontalRuleWithNoArrowDivider_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(HorizontalRuleWithNoArrowDivider_StepDefinition.class.getName());
<<<<<<< Updated upstream
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
=======
>>>>>>> Stashed changes
		new HorizontalRuleWithNoArrowDivider_page();
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		if (fetchUrl("HorizontalRuleWithNoArrowDivider") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				urls.add("http://apsrs5642:8080/content/AutomationDirectory/horizontal-rule-without-arrow-divider.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				urls.add("http://apvrt31468:4503/content/AutomationDirectory/horizontal-rule-without-arrow-divider.html");
			}

		} else {
			String[] scannedUrls = fetchUrl("HorizontalRuleWithNoArrowDivider").split(",");
			for (String link : scannedUrls) {
				urls.add(link);
			}
		}

		ExtentTestManager.startTest(HorizontalRuleWithNoArrowDivider_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("HorizontalRuleArrowDivider", author, currentDomain, HorizontalRuleWithNoArrowDivider_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(HorizontalRuleWithNoArrowDivider_StepDefinition.class);
		logger.info("Urls for '" + HorizontalRuleWithNoArrowDivider_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(HorizontalRuleWithNoArrowDivider_StepDefinition.class.getName(), currentDomain);

		driverMap.put(HorizontalRuleWithNoArrowDivider_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + HorizontalRuleWithNoArrowDivider_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true)
	public void horizontalRuleCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			scrollToElement(mydriver, horzontalRuleSection, logger);
			focusElement(mydriver, horzontalRuleHeader);
			customTestLogs.get().add("Checking if Arrow Divider Exists: "+verifyElementExists(logger, horzontalRuleHeader, "Header is displayed as expected"));
			hardAssert.assertTrue(verifyElementExists(logger, horzontalRuleHeader,
					"horzontalRule ::> " + horzontalRuleHeader.getText()));
			hardAssert.assertFalse(horzontalRuleHeader.getText().isEmpty());
			customTestLogs.get().add("Display the Header in logger '" + horzontalRuleHeader.getText());
			focusElement(mydriver, horzontalRuleArrow);
			customTestLogs.get().add("Checking if Arrow Divider Exists: "+verifyElementExists(logger, horzontalRuleArrow, "Arrow Divider does not Exists which is expected"));
			hardAssert.assertTrue(verifyElementExists(logger, horzontalRuleArrow,
					"Does Arrow Divider Exists? ::> " + "No, Arrow Divider does not Exists which is expected"));
			
		}
	}
	
}
