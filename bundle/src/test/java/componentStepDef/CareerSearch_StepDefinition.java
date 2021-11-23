package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.CareerSearch_page;
import compontentPages.IconPickerNew_page;
import compontentPages.IconStrip_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class CareerSearch_StepDefinition extends CareerSearch_page{
	private String author = "Sreevidhya";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(CareerSearch_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(CareerSearch_StepDefinition.class.getName());
		new CareerSearch_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("career-search") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5642:8080/content/medexpressautomationdirectory/careersearch.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("http://apvrt31468:4503/content/medexpressautomationdirectory/careersearch.html");
			}

		} else {
			String[] scannedUrls = fetchUrl("career-search").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(CareerSearch_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("CareerSearch", author, currentDomain,
				CareerSearch_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(CareerSearch_StepDefinition.class);
		logger.info("Urls for '" + CareerSearch_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(CareerSearch_StepDefinition.class.getName(), currentDomain);

		driverMap.put(CareerSearch_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + CareerSearch_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		
	}

	
	@Test(priority = 1, enabled = true)
	public void CareerSearchVisibilityCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(categorydropdown));
			scrollToElement(mydriver, Careersearchsection, logger);
			softAssert.assertTrue(categorydropdown.isDisplayed());
			customTestLogs.get().add("Verifying the element present or not : " + categorydropdown.isDisplayed());
			softAssert.assertTrue(locationdropdown.isDisplayed());
			customTestLogs.get().add("Verifying the element present or not : " + locationdropdown.isDisplayed());
			softAssert.assertTrue(distancedropdown.isDisplayed());

			customTestLogs.get().add("Verifying the element present or not : " + distancedropdown.isDisplayed());
			softAssert.assertTrue(Searchbutton.isEnabled());
			customTestLogs.get().add("Verifying the element present or not : " + Searchbutton.isEnabled());

			softAssert.assertTrue(Searchbutton.isDisplayed());

			softAssert.assertAll();
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void CareerSearchCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(categorydropdown));			
			selectByOptionIndex(categorydropdown,2,logger);	
			locationdropdown.sendKeys("23456");			
			selectByOptionIndex(distancedropdown,2,logger);	
			Searchbutton.click();
			wait.until(ExpectedConditions.visibilityOf(Careeropp));			
			softAssert.assertTrue(Searchresult.isDisplayed());
			customTestLogs.get().add("Verifying the element present or not : " + Searchresult.isDisplayed());
			softAssert.assertTrue(Careeropp.isDisplayed());

			customTestLogs.get().add("Verifying the element present or not : " + Careeropp.isDisplayed());
			softAssert.assertAll();
			

			softAssert.assertAll();

		}
	}
	
	@Test(priority = 3, enabled = true)
	public void CareerSearchmandatoryfieldCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(categorydropdown));
			Searchbutton.click();
			wait.until(ExpectedConditions.visibilityOf(Mandatorycategorymsg));	
			customTestLogs.get().add("Checking the error message is displayed or not: "+verifyElementExists(logger, Mandatorycategorymsg, "Please select a job category."));
			softAssert.assertTrue(verifyElementExists(logger, Mandatorycategorymsg, "Please select a job category."));
			customTestLogs.get().add("Checking the error message is displayed or not: "+verifyElementExists(logger, Mandatorylocationmsg, "Please enter a valid location."));
			softAssert.assertTrue(verifyElementExists(logger, Mandatorylocationmsg, "Please enter a valid location."));
			softAssert.assertAll();
		}
	}

	@Test(priority = 4, enabled = true)
	public void CareerSearchviewPositionCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(categorydropdown));			
			selectByOptionName(logger,categorydropdown,"Physicians");				
			locationdropdown.sendKeys("Eden Prairie,MN");			
			selectByOptionIndex(distancedropdown,2,logger);			
			Searchbutton.click();
			wait.until(ExpectedConditions.visibilityOf(Viewposition));
			hardAssert.assertTrue(Viewposition.isDisplayed());

			customTestLogs.get().add("Verify the the Viewposition button componet is displayed: "+Viewposition.isDisplayed());
			softAssert.assertAll();

			

		}
	}
	
	@Test(priority = 5, enabled = true)
	public void CareersOptumCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			String handle=mydriver.getWindowHandle();
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(categorydropdown));
			selectByOptionName(logger,categorydropdown,"Physicians");	
			locationdropdown.sendKeys("Eden Prairie,MN");
			selectByOptionIndex(distancedropdown,3,logger);		
			Searchbutton.click();
			wait.until(ExpectedConditions.visibilityOf(Viewposition));
			Viewposition.click();
			switchToNextTab(mydriver,logger,handle);						
			wait.until(ExpectedConditions.visibilityOf(Searchjobbtn));
			hardAssert.assertTrue(Searchjobbtn.isDisplayed());
			customTestLogs.get().add("Verify the the search button componet is displayed: "+Searchjobbtn.isDisplayed());
			softAssert.assertAll();
			
		}
	}
	
}



