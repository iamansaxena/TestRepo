package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MapBanner_page;
import compontentPages.Search_map_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class Search_map_StepDefinition extends Search_map_page {

	
	private String author = "Sreevidhya";
	private static String currentDomain = "=>";	
	private static Logger logger;
	

	@BeforeClass
	public void setup() {
		fetchSession(Search_map_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(Search_map_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new Search_map_page();

		
		ExtentTestManager.startTest(Search_map_StepDefinition.class.getName());
		
		setTagForTestClass("search-map", author,
				Search_map_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(Search_map_StepDefinition.class);
		logger.info("Urls for '" + Search_map_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(Search_map_StepDefinition.class.getName(), currentDomain);

		driverMap.put(Search_map_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + Search_map_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}


	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		
	}
	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"search-map"})
	public void SearchMapVisibilityCheck(String cardUrl) {
		

		
			//urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,60);			
			wait.until(ExpectedConditions.visibilityOf(search_map_section));
			scrolltillvisibilityMedex(mydriver, search_map_section, logger);
			//scrollToElement(mydriver, search_map_section, logger);
			scrolltillvisibilityMedex(mydriver, Submit, logger);
			softAssert.assertTrue(search_map_section.isDisplayed());		
			softAssert.assertTrue(centers_cards_section.isDisplayed());
			softAssert.assertTrue(find_location.isDisplayed());
			
			softAssert.assertAll();
	
	}
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"search-map"})
	public void SearchMap(String cardUrl) {
		

		
			//urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(search_map_section));
			scrolltillvisibilityMedex(mydriver, search_map_section, logger);
			//scrollToElement(mydriver, search_map_section, logger);	
			
			softAssert.assertTrue(find_location.isDisplayed());
			scrolltillvisibilityMedex(mydriver, Submit, logger);
			find_location.click();
			String StateZip1 = "55421";
			find_location.sendKeys(StateZip1);
			pleaseWait(6,logger);
			scrolltillvisibilityMedex(mydriver, Submit, logger);
			Submit.click();
			pleaseWait(6,logger);
			
			String Card_name = First_result.getText();
			System.out.println("Card name="+ Card_name);
			
			String StateZip =  State_Zip.getText();
			System.out.println("State Zip="+ StateZip.substring(StateZip.length()-5));
			//Assert.assertEquals((StateZip.length()-5), StateZip1);
			softAssert.assertEquals(StateZip.substring(StateZip.length()-5), StateZip1 );
			customTestLogs.get().add("Verifing search result: "+ StateZip1.equals(StateZip.length()-5));
			softAssert.assertTrue(Maplocator.isDisplayed());
			softAssert.assertAll();
	
	}
	
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"search-map"})
	public void SelectLocationFromMap(String cardUrl) {
		

		
			//urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(search_map_section));
			scrollToElement(mydriver, search_map_section, logger);	
			
			softAssert.assertTrue(find_location.isDisplayed());
			//find_location.click();
			scrolltillvisibilityMedex(mydriver, Submit, logger);
			find_location.sendKeys("55421");
			Submit.click();
			pleaseWait(6,logger);
			String abc = First_result.getText();
			System.out.println("Search result ="+ abc);
			softAssert.assertTrue(Maplocator.isDisplayed());
			Maplocator2.click();
			String XYZ = First_result.getText();
			System.out.println("Search result 2 ="+ XYZ);	
			softAssert.assertNotEquals(abc, XYZ);
			softAssert.assertAll();
	
	}
	
	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"search-map"})
	public void Selectlocation(String cardUrl) {
		

		
			//urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(search_map_section));
			scrollToElement(mydriver, search_map_section, logger);	
			
			softAssert.assertTrue(find_location.isDisplayed());	
			scrolltillvisibilityMedex(mydriver, Submit, logger);
			find_location.sendKeys("55421");
			Submit.click();
			pleaseWait(6,logger);
			First_result.click();
		
			pleaseWait(6,logger);
			boolean flag = false;
			if((!(mydriver.getCurrentUrl().equalsIgnoreCase(cardUrl)))) {
				flag = true;
			}
			
			hardAssert.assertEquals(flag, true);
			
			softAssert.assertAll();
	
	}
}
