package componentStepDef;import java.util.concurrent.TimeUnit;

import java.util.ArrayList;


import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.Logo_page;
import utils.BrokenLinks;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class Logo_StepDefinition extends Logo_page{

	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> urls= new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(Logo_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(Logo_StepDefinition.class.getName());
		new Logo_page();
		
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("logo") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				urls.add("http://apsrs5642:8080/content/AutomationDirectory/logo.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				urls.add("http://apvrt31468:4503/content/AutomationDirectory/logo.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("logo").split(",");
			for (String link : scannedUrls) {
				urls.add(link);
			}
		}

		
		ExtentTestManager.startTest(Logo_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("Logo", author, currentDomain, Logo_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(Logo_StepDefinition.class);
		logger.info("Urls for '" + Logo_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(Logo_StepDefinition.class.getName(), currentDomain);

		driverMap.put(Logo_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + Logo_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
//		mydriver.manage().deleteAllCookies();
	}

	@Test(priority = 1, enabled = true)
	public void elementVisibilityCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			softAssert.assertFalse(logoImg.getAttribute("alt").isEmpty());
			softAssert.assertFalse(logoLink.getAttribute("href").isEmpty());
			softAssert.assertFalse(favIcon.getAttribute("data-favicon").isEmpty());
		softAssert.assertAll();
		}
	}
	@Test(priority = 2, enabled = true)
	public void brokenLogoImageCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			getVisibility(mydriver, logo, 20);
			hardAssert.assertTrue(verifyElementExists(logger, logoImg, "Logo Image"));
			hardAssert.assertEquals(BrokenLinks.isBroken(logoImg.getAttribute("src")), 200);
		}
	}
	
	
	@Test(priority = 3, enabled = true)
	public void blankTagLineCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			try {
				getVisibility(mydriver, logoTagLine, 20);
				logoTagLine.isDisplayed();
				
			}catch (NoSuchElementException|TimeoutException e ) {
				throw new SkipException("There's no tag-line field");
			}
			hardAssert.assertFalse(logoTagLine.getText().isEmpty());
			
		}
	}
/*	@Test(priority = 4, enabled = true)
	public void logoRedirectionCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			customLogsPool.get().add(url); mydriver.get(url);
			String expURL = logoLink.getAttribute("href").split("https://")[1];
			scrollToElement(mydriver, logoImg).click();
			pleaseWait(4, logger);
			Set<String> handles = mydriver.getWindowHandles();
			if(handles.size()!=1) {
			handles.remove(mydriver.getWindowHandle());
			String newTabHandle= handles.iterator().next();
			mydriver.switchTo().window(newTabHandle);}
			hardAssert.assertEquals(mydriver.getCurrentUrl().split("www.")[1], expURL);
			
			
		}
	}
	*/
	
	
	
	
}
