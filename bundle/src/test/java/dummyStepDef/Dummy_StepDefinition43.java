package dummyStepDef;import java.util.concurrent.TimeUnit;

import java.util.ArrayList;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import core.Base;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class Dummy_StepDefinition43 extends Base{
	private String author = "Aman Saxena";

	private static Logger logger;

	private static ArrayList<String> urls= new ArrayList<>();
	private static String currentDomain = "=>";
	private static WebDriver mydriver ; 
	@BeforeClass
	public void setup() {
		
		fetchSession(Dummy_StepDefinition43.class);
		mydriver = LATEST_DRIVER_POOL.get(Dummy_StepDefinition43.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("Dummt") == null) {
			urls.add("https://google.com");
		} else {
			String[] scannedUrls = fetchUrl("pluv2-intake").split(",");
			for (String link : scannedUrls) {
				urls.add(link);
			}
		}

		ExtentTestManager.startTest(Dummy_StepDefinition43.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("Dummy Test", author, currentDomain, Dummy_StepDefinition43.class.getName());

		logger = LoggerLog4j.startTestCase(Dummy_StepDefinition43.class);
		logger.info("Urls for '" + Dummy_StepDefinition43.class.getName() + "' => " + currentDomain);
		testURLS.put(Dummy_StepDefinition43.class.getName(), currentDomain);

		driverMap.put(Dummy_StepDefinition43.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + Dummy_StepDefinition43.class.getName() + "' =>\n" + driverMap);

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
	public void DummyTest() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get("https://google.com");

			System.out.println("completed");
	
		}
	}
	@Test(priority = 1, enabled = true)
	public void DummyTest2() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get("https://google.com");

			System.out.println("completed");
	
		}
	}
	@Test(priority = 1, enabled = true)
	public void DummyTest3() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get("https://google.com");

			System.out.println("completed");
	
		}
	}
	@Test(priority = 1, enabled = true)
	public void DummyTest4() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get("https://google.com");

			System.out.println("completed");
	
		}
	}
/*	@Test(priority = 1, enabled = true)
	public void DummyTest5() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get("https://google.com");

			System.out.println("completed");
	
		}
	}
	@Test(priority = 1, enabled = true)
	public void DummyTest6() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get("https://google.com");

			System.out.println("completed");
	
		}
	}
	@Test(priority = 1, enabled = true)
	public void DummyTest7() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get("https://google.com");

			System.out.println("completed");
	
		}
	}*/
	
}
