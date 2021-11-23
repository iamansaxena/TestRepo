package componentStepDef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ArticleInPage_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ArticleInPage_StepDefinition extends ArticleInPage_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(ArticleInPage_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ArticleInPage_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new ArticleInPage_page();
		ExtentTestManager.startTest(ArticleInPage_StepDefinition.class.getName());
		setTagForTestClass("ArticleInPage", author, ArticleInPage_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ArticleInPage_StepDefinition.class);
		logger.info("Urls for '" + ArticleInPage_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ArticleInPage_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ArticleInPage_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + ArticleInPage_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
			"article-in-page-nav" })
	public void navigationTabStaticPositionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> subsections = mydriver.findElements(By.xpath(subSection));
		scrollToElement(mydriver, subsections.get(subsections.size() - 1), logger);
		hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElement(By.xpath(navTabs)), "Navigation Tabs"));

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"article-in-page-nav" })
	public void tabsHighlightOnScrollCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		pleaseWait(5, logger);
		List<WebElement> subsections = mydriver.findElements(By.xpath(subSection));
		List<WebElement> navigationsTabs = mydriver.findElements(By.xpath(navTabs));

		int i = getRandomInteger(subsections.size(), 0);
		scrollToElement(mydriver, subsections.get(i), logger);
		hardAssert.assertTrue(navigationsTabs.get(i).getAttribute("class").equals("active"));

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"article-in-page-nav" })
	public void scrollToSectionOnClickCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		pleaseWait(4, logger);
		getVisibility(mydriver, mydriver.findElements(By.xpath(subSection)).get(0), 30);
		List<WebElement> subsections = mydriver.findElements(By.xpath(subSection));
		List<WebElement> navigationsTabs = mydriver.findElements(By.xpath(navTabs));

		int i = getRandomInteger(navigationsTabs.size(), 0);
		scrollToElement(mydriver, navigationsTabs.get(i), logger).click();
		pleaseWait(4, logger);
		hardAssert.assertTrue(verifyElementExists(logger, subsections.get(i), "Sub-section"));
		logger.info("User scrolled successfully!");

	}

}
