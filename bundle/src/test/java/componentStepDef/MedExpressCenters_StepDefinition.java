package componentStepDef;import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MedExpressCenters_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MedExpressCenters_StepDefinition extends MedExpressCenters_page {

	private String author = "Rekha Vasugan";
	private static Logger logger;
	//private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(MedExpressCenters_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MedExpressCenters_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new MedExpressCenters_page();

		
		ExtentTestManager.startTest(MedExpressCenters_StepDefinition.class.getName());
		setTagForTestClass("medexpress-centers", author, MedExpressCenters_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MedExpressCenters_StepDefinition.class);
		logger.info("Urls for '" + MedExpressCenters_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MedExpressCenters_StepDefinition.class.getName(), currentDomain);
		driverMap.put(MedExpressCenters_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + MedExpressCenters_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
	
	
	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"centers-sitemap"})
	public void elementVisibilityCheck(String url){
		skipNonExistingComponent(url);
		
			 
			mydriver.get(url);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(medExpressCenterSection));
			scrollToElement(mydriver, medExpressCenterSection, logger);
			customTestLogs.get().add("Verify Med Express Center Section : " + medExpressCenterSection.isDisplayed());
			customTestLogs.get().add("Verify Med Express Center : " + medExpresscenterList.get(0).isDisplayed());
			hardAssert.assertEquals(medExpressCenterSection.isDisplayed(), true);
			hardAssert.assertEquals(medExpresscenterList.get(0).isDisplayed(), true);
	}
	
	@Test(priority =2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"centers-sitemap"})
	public void verifyCenterDetails(String url){
			mydriver.get(url);
			scrolltillvisibilityMedex(mydriver, medExpressCenterSection, logger);
			WebDriverWait wait = new WebDriverWait(mydriver,60);
			wait.until(ExpectedConditions.visibilityOf(medExpressCenterSection));
			scrollToElement(mydriver, medExpressCenterSection, logger);
			hardAssert.assertEquals(verifyCenterList(mydriver , logger), true);
	}
	
}

