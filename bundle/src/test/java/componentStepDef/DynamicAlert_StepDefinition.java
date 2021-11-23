package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.DynamicAlert_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class DynamicAlert_StepDefinition extends DynamicAlert_page {
	private String author = "Sai Tummala";
	private static String currentDomain = "=>";
	private static ArrayList<String> alertUrl = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(DynamicAlert_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(DynamicAlert_StepDefinition.class.getName());
		new DynamicAlert_page();
		
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("dynamic-alert") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
			alertUrl.add("http://apsrs5642:8080/content/AutomationDirectory/dynamic-alert.html");
		} else if (Environment.equalsIgnoreCase("test")) {
			alertUrl.add("http://apvrt31468:4503/content/AutomationDirectory/dynamic-alert.html");
		}
	
		} else {
			String[] scannedUrls = fetchUrl("dynamic-alert").split(",");
			for (String link : scannedUrls) {
				alertUrl.add(link);
			}
		}

		ExtentTestManager.startTest(DynamicAlert_StepDefinition.class.getName());
		for (String url : alertUrl) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("DynamicAlert", author, currentDomain, DynamicAlert_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(DynamicAlert_StepDefinition.class);
		logger.info("Urls for '" + DynamicAlert_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(DynamicAlert_StepDefinition.class.getName(), currentDomain);

		driverMap.put(DynamicAlert_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1,logger);
		logger.info("Browser pool at '" + DynamicAlert_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
	public void fieldsVisibilityCheck() {
		skipNonExistingComponent(alertUrl);
		for (String alert : alertUrl) {
			urlUnderTest.get().add(alert); mydriver.get(alert);
			getVisibility(mydriver, alerticon, 10);
			hardAssert.assertTrue(verifyElementExists(logger, alerticon, "Dynamic Alert Icon Exists"));
			getVisibility(mydriver, alerttitle, 10);
			softAssert.assertTrue(verifyElementExists(logger, alerttitle, "Dynamic Alert Title"));
			logger.info("Alert Title ==> " + alerttitle.getText());
			getVisibility(mydriver, description, 10);
			softAssert.assertTrue(verifyElementExists(logger, description, "Dynamic Alert Description"));
			logger.info("Alert Description ==> " + description.getText());
			getVisibility(mydriver, closeicon, 10);
			hardAssert.assertTrue(verifyElementExists(logger, closeicon, "Dynamic Alert CloseIcon Exists"));
			softAssert.assertAll();

		}
	}
//	Disabling this TC as it's out of the scope of Functionality 
//	@Test(priority = 2, enabled = true)
//	public void dynamicAlertColorsCheck() {
//		skipNonExistingComponent(alertUrl);
//		for (String alert : alertUrl) {
//			urlUnderTest.get().add(alert); mydriver.get(alert);
//			getVisibility(mydriver, alertwindowbackgroundcolor, 10);
//			logger.info("Dynamic Alert background color ==> " + alertwindowbackgroundcolor.getCssValue("background-color"));
//			hardAssert.assertTrue(verifyCSSValue(alertwindowbackgroundcolor, "background-color", "rgba(247, 183, 45, 1)"), "Background color of AlerWindow is showing correct");
//			logger.info("Dynamic Alert Icon color ==> " + alerticon.getCssValue("color"));
//			hardAssert.assertTrue(verifyCSSValue(alerticon, "color", "rgba(255, 255, 255, 1)"), "color is AlertIcon is showing correct");
//		}
//	}

	@Test(priority = 3, enabled = true)
	public void displayAlertRedirectionLink() {
		skipNonExistingComponent(alertUrl);
		for (String alert : alertUrl) {
			urlUnderTest.get().add(alert); mydriver.get(alert);	
			getVisibility(mydriver, button, 10);
			hardAssert.assertTrue(verifyElementExists(logger, button, "Button Exists"));
				if (button.getAttribute("href").isEmpty()) {
					fail("Hyperlink is Empty");
				} else {
					logger.info("Redirection Link ==> " + button.getAttribute("href"));
				}
		
			}
	}

	@Test(priority = 4, enabled = true)
	public void buttonRedirectionValidation() {
		skipNonExistingComponent(alertUrl);
		for (String alert : alertUrl) {
			urlUnderTest.get().add(alert); mydriver.get(alert);
			String domain = getDomainName(mydriver.getCurrentUrl());
			String myTab = mydriver.getWindowHandle();
			String expectedLink = button.getAttribute("href");
			scrollToElement(mydriver, button, logger).click();
			logger.info("Expected Link for main button => "+expectedLink);
			Set<String> allTabs = mydriver.getWindowHandles();
			if (getDomainName(expectedLink).equals(domain)) {
				hardAssert.assertEquals(mydriver.getWindowHandles().size(), 1);
				hardAssert.assertEquals(mydriver.getWindowHandle(), myTab);
				logger.info("Internal Link opened in the same tab => " + expectedLink);
				urlUnderTest.get().add(alert); mydriver.get(alert);
			} else {
				allTabs.remove(myTab);
				mydriver.switchTo().window(allTabs.iterator().next());
				hardAssert.assertEquals(mydriver.getCurrentUrl(), expectedLink);
				logger.info("External Links opened in a new tab => " + expectedLink);
				mydriver.switchTo().window(myTab);
			}
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void alertWindowCheck() {
		skipNonExistingComponent(alertUrl);
		for (String alert : alertUrl) {
			JavascriptExecutor js = (JavascriptExecutor)mydriver;
			urlUnderTest.get().add(alert); mydriver.get(alert);	
			getVisibility(mydriver, closeicon, 10);
			hardAssert.assertTrue(verifyElementExists(logger, closeicon, "Dynamic Alert is closed sucessfully"));
			closeicon.click();
			mydriver.navigate().refresh();
			logger.info("page got refreshed");
			hardAssert.assertFalse(verifyElementExists(logger, alertwindowcontainer, "Dynamic Alert Container is not showing"));
			js.executeScript(String.format("window.localStorage.clear();"));
			mydriver.navigate().refresh();
			logger.info("page got refreshed 2nd time");
			
			getVisibility(mydriver, alertwindowcontainer, 10);
			hardAssert.assertTrue(verifyElementExists(logger, alertwindowcontainer, "Dynamic Alert shows sucessfully"));
			
		}
	}
}

