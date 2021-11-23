package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ArticleBannerMedExpress_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ArticleBannerMedExpress_StepDefinition extends ArticleBannerMedExpress_page {
	private String author = "Rekha Vasugan";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(ArticleBannerMedExpress_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ArticleBannerMedExpress_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new ArticleBannerMedExpress_page();
		ExtentTestManager.startTest(ArticleBannerMedExpress_StepDefinition.class.getName());
		setTagForTestClass("article-banner", author, ArticleBannerMedExpress_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ArticleBannerMedExpress_StepDefinition.class);
		logger.info("Urls for '" + ArticleBannerMedExpress_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ArticleBannerMedExpress_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ArticleBannerMedExpress_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + ArticleBannerMedExpress_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
	}

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"article-banner" })
	public void elementVisibilityCheck(String cardUrl) {		
		skipNonExistingComponent(cardUrl);
		mydriver.get(cardUrl);
		hardAssert.assertEquals(verifyElements(logger), true);
	}
	
}


	/*@Test(priority = 1, enabled = true,  dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"article-banner"})
	public void elementVisibilityCheck(String cardUrl) {
			skipNonExistingComponent(cardUrl);
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			hardAssert.assertEquals(verifyElements(logger), true);
	}*/

	

