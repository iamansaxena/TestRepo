package componentStepDef;import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.SiteMap_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class SiteMap_StepDefinition extends SiteMap_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(SiteMap_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(SiteMap_StepDefinition.class.getName());
		new SiteMap_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
<<<<<<< Updated upstream
=======
		if (fetchUrl("sitemap") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				urls.add("http://apsrs5642:8080/content/AutomationDirectory/sitemap.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				urls.add("http://apvrt31468:4503/content/AutomationDirectory/sitemap.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("sitemap").split(",");
			for (String link : scannedUrls) {
				urls.add(link);
			}
		}

>>>>>>> Stashed changes
		ExtentTestManager.startTest(SiteMap_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("SiteMap", author, currentDomain, SiteMap_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(SiteMap_StepDefinition.class);
		logger.info("Urls for '" + SiteMap_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(SiteMap_StepDefinition.class.getName(), currentDomain);

		driverMap.put(SiteMap_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + SiteMap_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
	public void textFiledsVisibilityAndEmptyFieldCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {

			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, siteMapSection, logger);
			try {
				mydriver.findElement(By.xpath(links)).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's no link under SiteMap Component");
			}
			List<WebElement> hyperlinks = mydriver.findElements(By.xpath(links));
			int i = getRandomInteger(hyperlinks.size(), 0);
			String expLink = hyperlinks.get(i).getAttribute("href");
			String handle = mydriver.getWindowHandle();
			hyperlinks.get(i).click();
			pleaseWait(5, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

		}
	}
}
