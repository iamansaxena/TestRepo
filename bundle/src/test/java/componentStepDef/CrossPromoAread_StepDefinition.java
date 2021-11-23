package componentStepDef;import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

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
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class CrossPromoAread_StepDefinition extends CrossPromoAread_page {

	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {
		fetchSession(CrossPromoAread_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(CrossPromoAread_StepDefinition.class.getName());
<<<<<<< Updated upstream
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
=======
>>>>>>> Stashed changes
		new CrossPromoAread_page();
		
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("cross-promo-area") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				urls.add("http://apsrs5642:8080/content/AutomationDirectory/CrossPromoArea.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				urls.add("http://apvrt31468:4503/content/AutomationDirectory/CrossPromoArea.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("cross-promo-area").split(",");
			for (String link : scannedUrls) {
				urls.add(link);
			}
		}

		
		ExtentTestManager.startTest(CrossPromoAread_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("CrossPromo", author, currentDomain, CrossPromoAread_StepDefinition.class.getName());
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
//		mydriver.manage().deleteAllCookies();
	}

	@Test(priority = 1, enabled = true)
	public void minimumNumberOfArticlesCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			List<WebElement> articleContainers = mydriver.findElements(By.xpath(articles));
scrollToElement(mydriver, articleContainers.get(0), logger )	;
logger.info("No of articles => "+articleContainers.size());
			hardAssert.assertTrue(articleContainers.size()>2);
			
			
		}
	}
	

	@Test(priority = 2, enabled = true)
	public void tagFieldsCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			List<WebElement> articleContainers = mydriver.findElements(By.xpath(articles));
			List<WebElement> tags = mydriver.findElements(By.xpath(tagField));
scrollToElement(mydriver, articleContainers.get(0), logger )	;
hardAssert.assertEquals(tags.size(), articleContainers.size());
		}
		
	}
	
	@Test(priority = 3, enabled = true)
	public void articleRedirectionCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			List<WebElement> reirectionLinks = mydriver.findElements(By.xpath(articleLinks));
			int i=getRandomInteger(reirectionLinks.size()-1, 0);
			String expLink = reirectionLinks.get(i).getAttribute("href");
			logger.info("Selected Article link => "+expLink);
			String handle = mydriver.getWindowHandle();
			scrollToElement(mydriver, reirectionLinks.get(i), logger).click();
			pleaseWait(5, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);;
			
		}
	}
	@Test(priority = 4, enabled = true)
	public void mainSectionButtonRedirectionCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			String expLink = null;
			String handle = mydriver.getWindowHandle();
			try {
			expLink = mainButton.getAttribute("href");
			}catch (NoSuchElementException e) {
				throw new SkipException("There's no Button to check redirection");
			}
			scrollToElement(mydriver, mainButton, logger);
			mainButton.click();
			pleaseWait(4, logger);
			jsWaitForPageToLoad(30, mydriver, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void sectionHeaderCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			String text;
			try {
			text = scrollToElement(mydriver, sectionTitle, logger).getText();
			}catch (Exception e) {
				throw new SkipException("There is no section title field available!");
			}
			
			hardAssert.assertFalse(text.isEmpty());
			logger.info("Section Header => "+text);
			
		}
	}
	
	}
