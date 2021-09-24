package componentStepDef;import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.Sharebar_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class Sharebar_StepDefinition extends Sharebar_page {

	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(Sharebar_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(Sharebar_StepDefinition.class.getName());
		new Sharebar_page();
		ExtentTestManager.startTest(Sharebar_StepDefinition.class.getName());
		setTagForTestClass("Sharebar", author, Sharebar_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(Sharebar_StepDefinition.class);
		logger.info("Urls for '" + Sharebar_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(Sharebar_StepDefinition.class.getName(), currentDomain);

		driverMap.put(Sharebar_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + Sharebar_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		mydriver.manage().deleteAllCookies();
	}

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"share-bar"})
	public void supportedShareOptionsAvailabilityCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, sharebarContainer, logger);
			try {
				facebookLink.isDisplayed();
				hardAssert.assertTrue(facebookLink.getAttribute("href").contains("facebook"));
			} catch (Exception e) {
				customTestLogs.get().add("facebook share option not available");
			}
			try {
				linkedinLink.isDisplayed();
				hardAssert.assertTrue(linkedinLink.getAttribute("href").contains("linkedin"));
			} catch (Exception e) {
				customTestLogs.get().add("LinkedIn share option not available");
			}

			try {
				emailShareLink.isDisplayed();
				hardAssert.assertTrue(emailShareLink.getAttribute("href").contains("mailto"));
			} catch (Exception e) {
				customTestLogs.get().add("Email share option not available");
			}
			try {
				pinterestLink.isDisplayed();
				hardAssert.assertTrue(pinterestLink.getAttribute("href").contains("pinterest"));
			} catch (Exception e) {
				customTestLogs.get().add("Pinterest share option not available");
			}
			try {
				twitterLink.isDisplayed();
				hardAssert.assertTrue(twitterLink.getAttribute("href").contains("twitter"));
			} catch (Exception e) {
				customTestLogs.get().add("Twitter share option not available");
			}

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"share-bar"})
	public void shareInNewTabCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			String baseTab = mydriver.getWindowHandle();
			scrollToElement(mydriver, sharebarContainer, logger);

			try {
				facebookLink.click();
				pleaseWait(4, logger);
				hardAssert.assertEquals(mydriver.getWindowHandles().size(), 2);
				getBackToBaseTab(baseTab);
			} catch (Exception e) {
				customTestLogs.get().add("facebook share option is not available");
			}

			try {
				linkedinLink.click();
				pleaseWait(4, logger);
				hardAssert.assertEquals(mydriver.getWindowHandles().size(), 2);
				getBackToBaseTab(baseTab);
			} catch (Exception e) {
				customTestLogs.get().add("LinkedIn share option is not available");
			}

			try {
				pinterestLink.click();
				pleaseWait(4, logger);
				hardAssert.assertEquals(mydriver.getWindowHandles().size(), 2);
				getBackToBaseTab(baseTab);
			} catch (Exception e) {
				customTestLogs.get().add("Pinterest share option is not available");
			}
			try {
				twitterLink.click();
				pleaseWait(4, logger);
				hardAssert.assertEquals(mydriver.getWindowHandles().size(), 2);
				getBackToBaseTab(baseTab);
			} catch (Exception e) {
				customTestLogs.get().add("Twitter share option is not available");
			}


	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"share-bar"})
	public void redirectionCheck(String url) {
		skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			scrollToElement(mydriver, sharebarContainer, logger);
			String handle = mydriver.getWindowHandle();			
			try {
				facebookLink.click();
				pleaseWait(4, logger);
				assertStaticRedirection(mydriver, logger, getDomainName(url), "https://www.facebook.com","facebook", handle);
			} catch (Exception e) {
				customTestLogs.get().add("facebook share option is not available");
			}

			try {
				linkedinLink.click();
				pleaseWait(4, logger);
				assertStaticRedirection(mydriver, logger, getDomainName(url), "https://www.linkedin.com",
						"linkedin", handle);
			} catch (Exception e) {
				customTestLogs.get().add("LinkedIn share option is not available");
			}

			try {
				pinterestLink.click();
				pleaseWait(4, logger);
				assertStaticRedirection(mydriver, logger, getDomainName(url), "https://www.pinterest.com",
						"pinterest", handle);
			} catch (Exception e) {
				customTestLogs.get().add("Pinterest share option is not available");
			}
			try {
				twitterLink.click();
				pleaseWait(4, logger);
				assertStaticRedirection(mydriver, logger, getDomainName(url), "https://twitter.com", "twitter",
						handle);
			} catch (Exception e) {
				customTestLogs.get().add("Twitter share option is not available");
			}
//			}
	}
}