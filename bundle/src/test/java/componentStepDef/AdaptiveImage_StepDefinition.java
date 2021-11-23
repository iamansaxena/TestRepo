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

import compontentPages.AdaptiveImage_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class AdaptiveImage_StepDefinition extends AdaptiveImage_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(AdaptiveImage_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(AdaptiveImage_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new AdaptiveImage_page();
		ExtentTestManager.startTest(AdaptiveImage_StepDefinition.class.getName());
		setTagForTestClass("Adaptive Image", author, AdaptiveImage_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(AdaptiveImage_StepDefinition.class);
		logger.info("Urls for '" + AdaptiveImage_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(AdaptiveImage_StepDefinition.class.getName(), currentDomain);

		driverMap.put(AdaptiveImage_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + AdaptiveImage_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"adaptiveimage"})
	public void adaptiveImageFieldsCheck(String url) {
		skipNonExistingComponent(url);
			
			mydriver.get(url);
			List<WebElement> images = mydriver.findElements(By.xpath(adaptiveImages));
			hardAssert.assertEquals(images.size(), 6);
			for (WebElement e : images) {
				hardAssert.assertFalse(e.getAttribute("data-src").isEmpty());
			}
		}
}
