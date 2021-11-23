package componentStepDef;import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.PullQuote_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class PullQuote_StepDefinition extends PullQuote_page {

	private String author = "Sai Tummala";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {
		fetchSession(PullQuote_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(PullQuote_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new PullQuote_page();		
		ExtentTestManager.startTest(PullQuote_StepDefinition.class.getName());
		setTagForTestClass("PullQuote", author, PullQuote_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(PullQuote_StepDefinition.class);
		logger.info("Urls for '" + PullQuote_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(PullQuote_StepDefinition.class.getName(), currentDomain);
		driverMap.put(PullQuote_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + PullQuote_StepDefinition.class.getName() + "' =>\n" + driverMap);
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
	
	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pull-quote"})
	public void pullQuoteBlockCheck(String url) {
		skipNonExistingComponent(url);
		
			 mydriver.get(url);
			scrollToElement(mydriver, pullQuoteSection, logger);
			focusElement(mydriver, blockQuote);
			focusElement(mydriver, blockQuoteCitation);
			hardAssert.assertTrue(verifyElementExists(logger, blockQuote,
					"Quote ::> " + blockQuote.getText()));
			hardAssert.assertFalse(blockQuote.getText().isEmpty());
			softAssert.assertTrue(verifyElementExists(logger, blockQuoteCitation,
					"Caption Citation of Quote ::> " + blockQuoteCitation.getText()));
			softAssert.assertAll();

	}
	
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pull-quote"})
	public void displayPullQuoteButtonLinkText(String url) {
		skipNonExistingComponent(url);
		
			 mydriver.get(url);
			try {
				blockQuotelink.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("link Text is not available");
			}
			hardAssert.assertFalse(blockQuotelink.getText().isEmpty());
			logger.info("Text displayed inside Button Link => "+blockQuotelink.getText());
	
		}

	
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pull-quote"})
	public void quoteLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);
		
			 mydriver.get(url);
			
			try {
				blockQuotelink.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("Hyperlink is not available");
			}
				String quoteLink = blockQuotelink.getAttribute("href");
				String handle = mydriver.getWindowHandle();
				blockQuotelink.click();
				assertRedirection(mydriver, logger, getDomainName(url), quoteLink,handle);

	}

}
