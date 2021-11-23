package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.IconPickerNew_page;
import compontentPages.IconStrip_page;
import compontentPages.IntroTextHorizontal_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class IntroTextHorizontal_StepDefinition extends IntroTextHorizontal_page {
	private String author = "Prateek Srivastava";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(IntroTextHorizontal_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(IntroTextHorizontal_StepDefinition.class.getName());
		new IntroTextHorizontal_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("intro-texthorizontal") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5643:8080/content/AutomationDirectory/IntroTextHorizontalRule.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("http://apvrt31468:4503/content/AutomationDirectory/IntroTextHorizontalRule.html");
			}

		} else {
			String[] scannedUrls = fetchUrl("intro-texthorizontal").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(IntroTextHorizontal_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("IntroTextHorizontal", author, currentDomain,
				IntroTextHorizontal_StepDefinition.class.getName());
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

	/*@Test(priority = 2, enabled = true)
	public void blankHeaderCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			mydriver.get(cardUrl);
			String expURL = mydriver.findElement(By.xpath(iconField)).getAttribute("href");
		assertRedirection(mydriver, logger, getDomainName(cardUrl), expURL);
		}
	}*/
	
	@Test(priority = 1, enabled = true)
	public void longDividerFieldCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			
			try{scrollToElement(mydriver, longDivider, logger);
			}catch (Exception e) {
				throw new SkipException("There's  no long divider field available");
			}
			customTestLogs.get().add("Checking if Long Divider Field is displying: " + verifyElementExists(logger, longDivider,"Long Divider Field") );
			hardAssert.assertTrue(verifyElementExists(logger, longDivider,"Long Divider Field"));
			customTestLogs.get().add("Value for Long Divider Field: " + longDivider.getAttribute("innerText"));
			hardAssert.assertFalse(longDivider.getAttribute("innerText").isEmpty());
		}}
	
	@Test(priority = 2, enabled = true)
	public void introDirectionalFieldCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			
			try{scrollToElement(mydriver, introDirectionalCopy, logger);
			}catch (Exception e) {
				throw new SkipException("There's no intro directional field available");
			}
			customTestLogs.get().add("Checking if Intro Directional Field is displying: " + verifyElementExists(logger, introDirectionalCopy,"Intro Directional Field"));
			hardAssert.assertTrue(verifyElementExists(logger, introDirectionalCopy,"Intro Directional Field"));
			customTestLogs.get().add("Value for Intro Directional Field: " + introDirectionalCopy.getAttribute("innerText"));
			hardAssert.assertFalse(introDirectionalCopy.getAttribute("innerText").isEmpty());
		}}
	
	@Test(priority = 3, enabled = true)
	public void shortDividerFieldCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			
			try{scrollToElement(mydriver, shortDivider, logger);
			}catch (Exception e) {
				throw new SkipException("There's no short divider field available");
			}
			customTestLogs.get().add("Checking if Intro Directional Field is displying: " + verifyElementExists(logger, shortDivider,"Short Divider Field"));
			hardAssert.assertTrue(verifyElementExists(logger, shortDivider,"Short Divider Field"));
			customTestLogs.get().add("Value for Short Divider Field: " + shortDivider.getAttribute("innerText"));
			hardAssert.assertFalse(shortDivider.getAttribute("innerText").isEmpty());

}}}
