package componentStepDef;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.PromoMessageHubMedex_Page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class PromoMessageHubMedex_StepDefinition extends PromoMessageHubMedex_Page{


	private String author = "Rekha Vasugan";
	private static Logger logger;
	private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(PromoMessageHubMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(PromoMessageHubMedex_StepDefinition.class.getName());
		new PromoMessageHubMedex_Page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		ExtentTestManager.startTest(PromoMessageHubMedex_StepDefinition.class.getName());
		setTagForTestClass("Promo Message Hub [Medex]", author, PromoMessageHubMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(PromoMessageHubMedex_StepDefinition.class);
		logger.info("Urls for '" + PromoMessageHubMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(PromoMessageHubMedex_StepDefinition.class.getName(), currentDomain);
		driverMap.put(PromoMessageHubMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + PromoMessageHubMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
	
	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"promo-message-hub"})
	public void elementVisibilityCheck(String url){
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			scrollToElement(mydriver, promoSection, logger);
			customTestLogs.get().add("Verifying if mandatory icon visible: " + promoIcon.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, promoIcon, "Promo Icon"));
			

	}

	
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"promo-message-hub"})
	public void messageFieldVisibilityCheck(String url){
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			scrollToElement(mydriver, promoSection, logger);
			customTestLogs.get().add("Verifying if message field visible: " + isMessageFieldsVisible());
			hardAssert.assertTrue(isMessageFieldsVisible());

	}


	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"promo-message-hub"})
	public void tagsFieldVisibilityCheck(String url){
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			scrollToElement(mydriver, promoSection, logger);
			customTestLogs.get().add("Verifying if tag field visible: " + isTagFieldsVisible());
			hardAssert.assertTrue(isTagFieldsVisible());
		}

	
	
}
