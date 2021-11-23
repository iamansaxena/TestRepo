package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MapBanner_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MapBanner_StepDefinition extends MapBanner_page {
	
	private String author = "Sreevidhya";
	private static String currentDomain = "=>";	
	private static Logger logger;
	

	@BeforeClass
	public void setup() {
		fetchSession(MapBanner_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MapBanner_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new MapBanner_page();

		
		ExtentTestManager.startTest(MapBanner_StepDefinition.class.getName());
		
		setTagForTestClass("map-banner", author,
				MapBanner_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MapBanner_StepDefinition.class);
		logger.info("Urls for '" + MapBanner_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MapBanner_StepDefinition.class.getName(), currentDomain);

		driverMap.put(MapBanner_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + MapBanner_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		
	}
	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"map-banner"})
	public void MapbannerVisibilityCheck(String cardUrl) {
		

		
			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(Center));
			scrollToElement(mydriver, Center, logger);
		
			softAssert.assertTrue(Findlocationinput.isDisplayed());
			softAssert.assertAll();
	
	}
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"map-banner"})
	public void FindCenterByZip(String cardUrl) {
	

		    String handle = mydriver.getWindowHandle();		
			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(Center));
			
			pleaseWait(9,logger);
			if(Findlocationinput.isDisplayed()) {
			Findlocationinput.click();
			Findlocationinput.sendKeys("12345");
			Center.click();
			}
			else {
				System.out.println("textbox not displayed");
			}
			pleaseWait(6,logger);
			boolean flag = false;
			if((!(mydriver.getCurrentUrl().equalsIgnoreCase(cardUrl)))|| (mydriver.getCurrentUrl().equalsIgnoreCase("http://test-medx.optum.com/search-result-answers.html?query=Centers+near+me"))) {
				flag = true;
			}
			
			hardAssert.assertEquals(flag, true);
		
	}
	
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"map-banner"})
	public void FindCenterbyCity(String cardUrl) {
		

		    String handle = mydriver.getWindowHandle();		
			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(Center));
			
			pleaseWait(9,logger);
			if(Findlocationinput.isDisplayed()) {
			Findlocationinput.click();
			Findlocationinput.sendKeys("Las Vegas");
			Center.click();
			}
			else {
				System.out.println("textbox not displayed");
			}
			pleaseWait(6,logger);
			boolean flag = false;
			if((!(mydriver.getCurrentUrl().equalsIgnoreCase(cardUrl)))|| (mydriver.getCurrentUrl().equalsIgnoreCase("http://test-medx.optum.com/search-result-answers.html?query=Centers+near+me"))) {
				flag = true;
			}
			
			hardAssert.assertEquals(flag, true);
		
	}
}
