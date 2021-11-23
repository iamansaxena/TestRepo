package componentStepDef;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.WaitTime_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class WaitTime_StepDefinition extends WaitTime_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() throws InterruptedException {

		fetchSession(WaitTime_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(WaitTime_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new WaitTime_page();
		ExtentTestManager.startTest(WaitTime_StepDefinition.class.getName());
		setTagForTestClass("Wait Time", author, WaitTime_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(WaitTime_StepDefinition.class);
		logger.info("Urls for '" + WaitTime_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(WaitTime_StepDefinition.class.getName(), currentDomain);

		driverMap.put(WaitTime_StepDefinition.class.getName().split("\\.")[1], mydriver);
		logger.info("Browser pool at '" + WaitTime_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void clockIconVisiblityCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			scrollToElement(mydriver, clockIcon, logger);
			hardAssert.assertTrue(verifyElementExists(logger, clockIcon, "Clock Icon"));

		
	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void urgentCareCenterDetailsCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			List<WebElement> urgentCareCenter = mydriver.findElements(By.xpath(urgentCareCenters));
			int i = 0;
			for(WebElement center : urgentCareCenter) {
				scrollToElement(mydriver, center, logger);
				softAssert.assertFalse(mydriver.findElements(By.xpath(urgentCareCenterNames)).get(i)
						.getAttribute("innerText").isEmpty());
				softAssert.assertFalse(mydriver.findElements(By.xpath(urgentCareCenterStatus)).get(i).getAttribute("innerText").isEmpty());
				softAssert.assertFalse(mydriver.findElements(By.xpath(urgentCareCenterAddress)).get(i).getAttribute("innerText").isEmpty());
				softAssert.assertAll();
			i++;}

		}
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void convenientCareCenterDetailsCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			List<WebElement> convenientCareCenter = mydriver.findElements(By.xpath(convenientCareCenters));
			int i = 0;
			for(WebElement center : convenientCareCenter) {
				scrollToElement(mydriver, center, logger);
				softAssert.assertFalse(mydriver.findElements(By.xpath(convenientCareCenterNames)).get(i)
						.getAttribute("innerText").isEmpty());
				softAssert.assertFalse(mydriver.findElements(By.xpath(convenientCareCenterStatus)).get(i).getAttribute("innerText").isEmpty());
				softAssert.assertFalse(mydriver.findElements(By.xpath(convenientCareCenterAddress)).get(i).getAttribute("innerText").isEmpty());
				softAssert.assertAll();
			i++;}

		}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void convenientCareSectionHeaderAndDescCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			boolean title = false;
			boolean description = false;
			try {
			convenientCareTitle.isDisplayed();	
			}catch (Exception e) {
				title=false;
			}
			try {
				convenientCareDesc.isDisplayed();	
				}catch (Exception e) {
					description=false;
				}
			
		if(title!=false) {
			scrollToElement(mydriver, convenientCareTitle, logger);
			hardAssert.assertFalse(convenientCareTitle.getAttribute("innerText").isEmpty());
		}
		if(description!=false) {
			scrollToElement(mydriver, convenientCareDesc, logger);
			hardAssert.assertFalse(convenientCareDesc.getAttribute("innerText").isEmpty());
		}
		

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void urgentCareSectionHeaderAndDescCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			boolean title = false;
			boolean description = false;
			try {
			urgentCareTitle.isDisplayed();	
			}catch (Exception e) {
				title=false;
			}
			try {
				urgentCareDesc.isDisplayed();	
				}catch (Exception e) {
					description=false;
				}
			
		if(title!=false) {
			scrollToElement(mydriver, urgentCareTitle, logger);
			hardAssert.assertFalse(urgentCareTitle.getAttribute("innerText").isEmpty());
		}
		if(description!=false) {
			scrollToElement(mydriver, urgentCareDesc, logger);
			hardAssert.assertFalse(urgentCareDesc.getAttribute("innerText").isEmpty());
		}
		

	}
	
	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void componentMainHeaderAndDescCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			
			try {
			componentTitle.isDisplayed();	
			}catch (Exception e) {
				throw new SkipException("There is no component title field available!");
			}
		
			scrollToElement(mydriver, componentTitle, logger);
			hardAssert.assertFalse(componentTitle.getAttribute("innerText").isEmpty());

	}
}
