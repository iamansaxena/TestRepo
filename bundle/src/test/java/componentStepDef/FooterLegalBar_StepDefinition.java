package componentStepDef;import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.FooterLegalBar_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class FooterLegalBar_StepDefinition extends FooterLegalBar_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(FooterLegalBar_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(FooterLegalBar_StepDefinition.class.getName());
		new FooterLegalBar_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("footer-legal-bar") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				urls.add("http://apsrs5642:8080/content/AutomationDirectory/footerlegalbar.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				urls.add("http://apvrt31468:4503/content/AutomationDirectory/footerlegalbar.html");
			}

		} else {
			String[] scannedUrls = fetchUrl("footer-legal-bar").split(",");
			for (String link : scannedUrls) {
				urls.add(link);
			}
		}

		ExtentTestManager.startTest(FooterLegalBar_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("Footer Legal Bar", author, currentDomain, FooterLegalBar_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(FooterLegalBar_StepDefinition.class);
		logger.info("Urls for '" + FooterLegalBar_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(FooterLegalBar_StepDefinition.class.getName(), currentDomain);

		driverMap.put(FooterLegalBar_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + FooterLegalBar_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true)
	public void blankLegalNoticeLabelsCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, mydriver.findElement(By.xpath(legalNoticeLink)), logger);
			List<WebElement> legalNoticeLabels = mydriver.findElements(By.xpath(legalNoticeLink));
			for (WebElement label : legalNoticeLabels) {
				hardAssert.assertFalse(label.getAttribute("innerText").isEmpty());
			}
		}
	}

	@Test(priority = 2, enabled = true)
	public void legalNoticeRedirectionCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, mydriver.findElement(By.xpath(legalNoticeLink)), logger);
			List<WebElement> legalNoticeLabels = mydriver.findElements(By.xpath(legalNoticeLink));
			int i = getRandomInteger(legalNoticeLabels.size(), 0);
			String expLink = legalNoticeLabels.get(i).getAttribute("href");
			String handle = mydriver.getWindowHandle();
			legalNoticeLabels.get(i).click();
			pleaseWait(6, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
		}
	}

	@Test(priority = 3, enabled = true)
	public void privacyLinkRedirectionCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, mydriver.findElement(By.xpath(privacyLink)), logger);
			List<WebElement> privacyLabels = mydriver.findElements(By.xpath(privacyLink));
			for(WebElement ele : privacyLabels) {
				hardAssert.assertFalse(ele.getAttribute("innerText").isEmpty());
			}
			
			int i = getRandomInteger(privacyLabels.size(), 0);
			String expLink = privacyLabels.get(i).getAttribute("href");
			String handle = mydriver.getWindowHandle();
			privacyLabels.get(i).click();
			pleaseWait(6, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
		}
	}

	@Test(priority = 4, enabled = true)
	public void blankCopyrightTextCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, copyrightText, logger);
			hardAssert.assertFalse(copyrightText.getAttribute("innerText").isEmpty());
		}
	}
	}
