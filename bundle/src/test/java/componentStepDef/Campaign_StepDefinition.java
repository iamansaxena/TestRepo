package componentStepDef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.Campaign_page;
import compontentPages.CareerSearch_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class Campaign_StepDefinition extends Campaign_page{
	
	private String author = "Sreevidhya";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;
	private String FName ="Asiq";
	private String LName ="John";
	private String Email="Test@gmail.com";

	@BeforeClass
	public void setup() {
		fetchSession(Campaign_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(Campaign_StepDefinition.class.getName());
<<<<<<< Updated upstream
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
=======
>>>>>>> Stashed changes
		new Campaign_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("campaign") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5642:8080/content/medexpressautomationdirectory/campaign.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("http://apvrt31468:4503/content/medexpressautomationdirectory/campaign.html");
			}

		} else {
			String[] scannedUrls = fetchUrl("campaign").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(Campaign_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("campaign", author, currentDomain,
				Campaign_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(Campaign_StepDefinition.class);
		logger.info("Urls for '" + Campaign_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(Campaign_StepDefinition.class.getName(), currentDomain);

		driverMap.put(Campaign_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + Campaign_StepDefinition.class.getName() + "' =>\n" + driverMap);
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
	public void CampaignVisibilityCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(Firstname));
			scrollToElement(mydriver, Campaignsection, logger);
			boolean abc = false;
			softAssert.assertTrue(Campaignsection.isDisplayed());
			softAssert.assertTrue(Firstname.isDisplayed());
			softAssert.assertTrue(Lastname.isDisplayed());
			softAssert.assertTrue(E_mail.isDisplayed());
			softAssert.assertTrue(Submitbtn.isDisplayed());		
			softAssert.assertTrue(Heading.isDisplayed());	
			softAssert.assertAll();
			if (abc != (Heading.isDisplayed() && Firstname.isDisplayed() && Lastname.isDisplayed() && E_mail.isDisplayed() && Submitbtn.isDisplayed() ))
			{
				abc = true;
			}
			
			
			customTestLogs.get().add("Verifing the elememt is displayed: "+abc);
			
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void Campaignsubmision() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(Firstname));
			
			boolean xyz = false;
			scrollToElement(mydriver, Campaignsection, logger);
			Firstname.sendKeys(FName);
			Lastname.sendKeys(LName);
			E_mail.sendKeys(Email);
			Submitbtn.click();			
			pleaseWait(6,logger);			
			hardAssert.assertTrue(Success_msg.isDisplayed());	
			hardAssert.assertTrue(Heading.isDisplayed());
			softAssert.assertFalse(Firstname.isDisplayed());
			softAssert.assertFalse(Lastname.isDisplayed());
			softAssert.assertFalse(E_mail.isDisplayed());
			softAssert.assertFalse(Submitbtn.isDisplayed());
			softAssert.assertAll();

			if (xyz == (Firstname.isDisplayed() && Lastname.isDisplayed() && E_mail.isDisplayed() && Submitbtn.isDisplayed()) )
			{
				xyz = true;
				
				
			}
			
			
			customTestLogs.get().add("Verifing the Capaign form submission: "+xyz);
			
			
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void Mandatorymsg() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(Firstname));
			boolean abc = false;
			Submitbtn.click();	
			pleaseWait(6,logger);	
			softAssert.assertTrue(Firstname_Error.isDisplayed());
			softAssert.assertTrue(Lastname_Error.isDisplayed());
			softAssert.assertTrue(E_mail_Error.isDisplayed());
			softAssert.assertAll();
			
			if (abc !=( Firstname_Error.isDisplayed() && Lastname_Error.isDisplayed() && E_mail_Error.isDisplayed()))
			{
				abc = true;
			}
			
			
			customTestLogs.get().add("Verifing the mandatory error message: "+abc);
			
		}
	}
	
	@Test(priority = 4, enabled = true)
	public void EmailfieldCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(Campaignsection));
			scrollToElement(mydriver, Campaignsection, logger);
			E_mail.sendKeys("test@gmail.com");
			Firstname.click();
			softAssert.assertFalse(E_mail_Error.isDisplayed());
			customTestLogs.get().add("Verifing the Error message is not displayed: "+E_mail_Error.isDisplayed());
			E_mail.clear();
			E_mail.sendKeys("test@gmail.");
			Firstname.click();
			softAssert.assertTrue(E_mail_Error.isDisplayed());
			customTestLogs.get().add("Verifing the Error message is displayed: "+E_mail_Error.isDisplayed());
			E_mail.clear();
			E_mail.sendKeys("test@");
			Firstname.click();
			softAssert.assertTrue(E_mail_Error.isDisplayed());
			customTestLogs.get().add("Verifing the Error message is displayed: "+E_mail_Error.isDisplayed());
			E_mail.clear();
			E_mail.sendKeys("!@#.#$%.@#$");
			Firstname.click();
			softAssert.assertTrue(E_mail_Error.isDisplayed());
			customTestLogs.get().add("Verifing the Error message is displayed: "+E_mail_Error.isDisplayed());
			softAssert.assertAll();
		}
	}

}
