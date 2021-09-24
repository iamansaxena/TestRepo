package componentStepDef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.CrossPromoAread_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class CrossPromoAread_StepDefinition extends CrossPromoAread_page {

	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {
		fetchSession(CrossPromoAread_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(CrossPromoAread_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new CrossPromoAread_page();
		ExtentTestManager.startTest(CrossPromoAread_StepDefinition.class.getName());
		setTagForTestClass("CrossPromo", author, CrossPromoAread_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(CrossPromoAread_StepDefinition.class);
		logger.info("Urls for '" + CrossPromoAread_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(CrossPromoAread_StepDefinition.class.getName(), currentDomain);

		driverMap.put(CrossPromoAread_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + CrossPromoAread_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
			"cross-promo-area" })
	public void minimumNumberOfArticlesCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> articleContainers = mydriver.findElements(By.xpath(articles));
		scrollToElement(mydriver, articleContainers.get(0), logger);
		logger.info("No of articles => " + articleContainers.size());
		hardAssert.assertTrue(articleContainers.size() > 2);

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-promo-area" })
	public void tagFieldsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> articleContainers = mydriver.findElements(By.xpath(articles));
		List<WebElement> tags = mydriver.findElements(By.xpath(tagField));
		scrollToElement(mydriver, articleContainers.get(0), logger);
		hardAssert.assertEquals(tags.size(), articleContainers.size());
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-promo-area" })
	public void articleRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> reirectionLinks = mydriver.findElements(By.xpath(articleLinks));
		int i = getRandomInteger(reirectionLinks.size() - 1, 0);
		String expLink = reirectionLinks.get(i).getAttribute("href");
		logger.info("Selected Article link => " + expLink);
		String handle = mydriver.getWindowHandle();
		scrollToElement(mydriver, reirectionLinks.get(i), logger).click();
		pleaseWait(5, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
		;

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-promo-area" })
	public void mainSectionButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		String expLink = null;
		String handle = mydriver.getWindowHandle();
		try {
			expLink = mainButton.getAttribute("href");
		} catch (NoSuchElementException e) {
			throw new SkipException("There's no Button to check redirection");
		}
		scrollToElement(mydriver, mainButton, logger);
		mainButton.click();
		pleaseWait(4, logger);
		jsWaitForPageToLoad(30, mydriver, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-promo-area" })
	public void sectionHeaderCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		String text;
		try {
			text = scrollToElement(mydriver, sectionTitle, logger).getText();
		} catch (Exception e) {
			throw new SkipException("There is no section title field available!");
		}

		hardAssert.assertFalse(text.isEmpty());
		logger.info("Section Header => " + text);

	}
}
