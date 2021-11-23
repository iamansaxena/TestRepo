package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.IntroTextHorizontal_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class IntroTextHorizontal_StepDefinition extends IntroTextHorizontal_page {
	private String author = "Prateek Srivastava";
	private static String currentDomain = "=>";

	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(IntroTextHorizontal_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(IntroTextHorizontal_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new IntroTextHorizontal_page();
		ExtentTestManager.startTest(IntroTextHorizontal_StepDefinition.class.getName());
		setTagForTestClass("IntroTextHorizontal", author, IntroTextHorizontal_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(IntroTextHorizontal_StepDefinition.class);
		logger.info("Urls for '" + IntroTextHorizontal_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(IntroTextHorizontal_StepDefinition.class.getName(), currentDomain);

		driverMap.put(IntroTextHorizontal_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + IntroTextHorizontal_StepDefinition.class.getName() + "' =>\n" + driverMap);
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

	/*
	 * @Test(priority = 2, enabled = true,dataProvider = "data-provider",
	 * dataProviderClass = CustomDataProvider.class, parameters =
	 * {"intro-arrowtext"}) public void blankHeaderCheck(String cardUrl) {
	 * HashMap<String, Boolean> assertConditionMap =
	 * skipNonExistingComponent(cardUrl);
	 * 
	 * 
	 * mydriver.get(cardUrl); String expURL =
	 * mydriver.findElement(By.xpath(iconField)).getAttribute("href");
	 * assertRedirection(mydriver, logger, getDomainName(cardUrl), expURL); } }
	 */

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"intro-arrowtext" })
	public void longDividerFieldCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		mydriver.get(cardUrl);

		try {
			scrollToElement(mydriver, longDivider, logger);
		} catch (Exception e) {
			throw new SkipException("There's  no long divider field available");
		}
		customTestLogs.get().add("Checking if Long Divider Field is displying: "
				+ verifyElementExists(logger, longDivider, "Long Divider Field"));
		hardAssert.assertTrue(verifyElementExists(logger, longDivider, "Long Divider Field"));
		customTestLogs.get().add("Value for Long Divider Field: " + longDivider.getAttribute("innerText"));
		hardAssert.assertFalse(longDivider.getAttribute("innerText").isEmpty());

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"intro-arrowtext" })
	public void introDirectionalFieldCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		mydriver.get(cardUrl);

		try {
			scrollToElement(mydriver, introDirectionalCopy, logger);
		} catch (Exception e) {
			throw new SkipException("There's no intro directional field available");
		}
		customTestLogs.get().add("Checking if Intro Directional Field is displying: "
				+ verifyElementExists(logger, introDirectionalCopy, "Intro Directional Field"));
		hardAssert.assertTrue(verifyElementExists(logger, introDirectionalCopy, "Intro Directional Field"));
		customTestLogs.get()
				.add("Value for Intro Directional Field: " + introDirectionalCopy.getAttribute("innerText"));
		hardAssert.assertFalse(introDirectionalCopy.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"intro-arrowtext" })
	public void shortDividerFieldCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		mydriver.get(cardUrl);

		try {
			scrollToElement(mydriver, shortDivider, logger);
		} catch (Exception e) {
			throw new SkipException("There's no short divider field available");
		}
		customTestLogs.get().add("Checking if Intro Directional Field is displying: "
				+ verifyElementExists(logger, shortDivider, "Short Divider Field"));
		hardAssert.assertTrue(verifyElementExists(logger, shortDivider, "Short Divider Field"));
		customTestLogs.get().add("Value for Short Divider Field: " + shortDivider.getAttribute("innerText"));
		hardAssert.assertFalse(shortDivider.getAttribute("innerText").isEmpty());
	}
}
