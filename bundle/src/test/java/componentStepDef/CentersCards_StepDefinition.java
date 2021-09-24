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

import compontentPages.Centerscards_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class CentersCards_StepDefinition extends Centerscards_page {

	private String author = "Rekha Vasugan";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(CentersCards_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(CentersCards_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new Centerscards_page();
		ExtentTestManager.startTest(CentersCards_StepDefinition.class.getName());
		setTagForTestClass("Centerscards", author, CentersCards_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(CentersCards_StepDefinition.class);
		logger.info("Urls for '" + CentersCards_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(CentersCards_StepDefinition.class.getName(), currentDomain);
		driverMap.put(CentersCards_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + CentersCards_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-cards" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		jsWaitForPageToLoad(50, mydriver, logger);
		scrollToElement(mydriver, centersCardsSection, logger);
		WebDriverWait wait = new WebDriverWait(mydriver, 30);
		wait.until(ExpectedConditions.visibilityOf(centersCardsSection));
		customTestLogs.get().add("Verify Enter City text box: " + enterCityTextBox.isDisplayed());
		customTestLogs.get().add("Verify Find Center Submit Button: " + findCenterBtn.isDisplayed());
		hardAssert.assertEquals(enterCityTextBox.isDisplayed(), true);
		hardAssert.assertEquals(findCenterBtn.isDisplayed(), true);
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-cards" })
	public void findCenterUsingCityName(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		jsWaitForPageToLoad(50, mydriver, logger);
		scrollToElement(mydriver, centerList.get(0), logger);
		WebDriverWait wait = new WebDriverWait(mydriver, 50);
		wait.until(ExpectedConditions.visibilityOf(centerList.get(0)));

		hardAssert.assertEquals(findCenter(mydriver, "Eden Prairie,MN", logger), true);
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-cards" })
	public void findCenterUsingZip(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		jsWaitForPageToLoad(50, mydriver, logger);
		scrollToElement(mydriver, centerList.get(0), logger);
		WebDriverWait wait = new WebDriverWait(mydriver, 50);
		wait.until(ExpectedConditions.visibilityOf(centerList.get(0)));
		hardAssert.assertEquals(findCenter(mydriver, "55346", logger), true);
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-cards" })
	public void findCenterUsingInvalidString(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		jsWaitForPageToLoad(50, mydriver, logger);
		scrollToElement(mydriver, centerList.get(0), logger);
		WebDriverWait wait = new WebDriverWait(mydriver, 50);
		wait.until(ExpectedConditions.visibilityOf(centerList.get(0)));
		hardAssert.assertNotEquals(findCenter(mydriver, "He$$o", logger), true);
	}

}
