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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.AskForAReferral_page;
import compontentPages.AskForRefill_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class AskForRefill_StepDefinition extends AskForRefill_page {
	private String author = "Rekha Vasugan";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(AskForRefill_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(AskForRefill_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new AskForRefill_page();
		ExtentTestManager.startTest(AskForRefill_StepDefinition.class.getName());
		setTagForTestClass("ask-for-refill", author, AskForRefill_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(AskForRefill_StepDefinition.class);
		logger.info("Urls for '" + AskForRefill_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(AskForRefill_StepDefinition.class.getName(), currentDomain);

		driverMap.put(AskForRefill_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + AskForRefill_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
	}

	
	@Test(priority = 1, enabled = true,  dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"ask-for-refill"})
	public void elementVisibilityCheck(String cardUrl) {
			
			mydriver.get(cardUrl);
			hardAssert.assertEquals(verifyElements(mydriver, logger), true);
	}
	
	@Test(priority = 2, enabled = true,  dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"ask-for-refill"})
	public void verifyFormSubmission(String cardUrl) {
			
			mydriver.get(cardUrl);
			String[] values = {"Name1" , "Name2", "1/9/1992", "987-654-3210", "hello@gmail.com","hello@gmail.com" , "D'Esposito, Robert F., MD", "Medication", "Dosage", "3","Sickness" };		
			hardAssert.assertEquals(enterValuesintheForm(mydriver,values,logger), true);
	}
	
	@Test(priority = 3, enabled = true,  dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"ask-for-refill"})
	public void verifyErrorMessage(String cardUrl) {
			
			mydriver.get(cardUrl);			
			hardAssert.assertEquals(verifyErrorMessagebySubmittingEmptyFields(mydriver,logger), true);
	}
	
	@Test(priority = 4, enabled = true,  dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"ask-for-refill"})
	public void verifyFormat(String cardUrl) {
			
			mydriver.get(cardUrl);	
			mydriver.switchTo().frame("aemFormFrame");
			hardAssert.assertEquals(verifyFormat(mydriver,"13/12/1991","Birth Date (m/d/yyyy) *", "DOB","Field not filled in expected format", logger), true);
			hardAssert.assertEquals(verifyFormat(mydriver,"9876543210","Phone Number (xxx-xxx-xxxx) *", "Phone Number","Phone Number format is invalid.", logger), true);
			hardAssert.assertEquals(verifyFormat(mydriver,"mail@mail","Email Address *", "Email","Email Address format is invalid", logger), true);
			hardAssert.assertEquals(verifyFormat(mydriver,"mail@mail","Re-enter Email Address *", "ReEnter Email","Re-enter Email Address format is invalid", logger), true);
			hardAssert.assertEquals(verifyDatePickerFormat(mydriver,"Birth Date (m/d/yyyy) *","previous",  logger), true);
	}	
	
}
