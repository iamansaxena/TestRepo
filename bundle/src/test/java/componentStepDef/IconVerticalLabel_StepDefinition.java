package componentStepDef;import java.util.concurrent.TimeUnit;

import java.util.ArrayList;


import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.IconVerticalLabel_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class IconVerticalLabel_StepDefinition extends IconVerticalLabel_page {
	private String author = "Prateek Srivastava";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(IconVerticalLabel_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(IconVerticalLabel_StepDefinition.class.getName());
		new IconVerticalLabel_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("icon-vertical") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5642:8080/content/AutomationDirectory/icon-vertical-rule-label-.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("http://apvrt31468:4503/content/AutomationDirectory/icon-vertical-rule-label-.html");
			}

		} else {
			String[] scannedUrls = fetchUrl("icon-vertical").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(IconVerticalLabel_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("IconVerticalLabel", author, currentDomain,
				IconVerticalLabel_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(IconVerticalLabel_StepDefinition.class);
		logger.info("Urls for '" + IconVerticalLabel_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(IconVerticalLabel_StepDefinition.class.getName(), currentDomain);

		driverMap.put(IconVerticalLabel_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + IconVerticalLabel_StepDefinition.class.getName() + "' =>\n" + driverMap);
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
	public void blankHeaderCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			try {
				scrollToElement(mydriver, headline, logger);
			} catch (NoSuchElementException e) {
				throw new SkipException("No headline field is available");
			}
			hardAssert.assertTrue(verifyElementExists(logger, headline,"headline"));
			hardAssert.assertFalse(headline.getText().isEmpty());
			logger.info("Icon vertical label : " + headline.getText());

		}
	}

}
