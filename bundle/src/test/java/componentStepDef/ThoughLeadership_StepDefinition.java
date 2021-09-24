package componentStepDef;

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

import compontentPages.ThoughLeadership_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ThoughLeadership_StepDefinition extends ThoughLeadership_page {

	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {
		System.out.println("Sript's thread  ==> "+ Thread.currentThread().getId());
		fetchSession(ThoughLeadership_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ThoughLeadership_StepDefinition.class.getName());
		new ThoughLeadership_page();

		ExtentTestManager.startTest(ThoughLeadership_StepDefinition.class.getName());
		setTagForTestClass("Thought Leadership", author, ThoughLeadership_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ThoughLeadership_StepDefinition.class);
		logger.info("Urls for '" + ThoughLeadership_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ThoughLeadership_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ThoughLeadership_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + ThoughLeadership_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"thought-leadership"})
	public void defaultElementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			List<WebElement> titles = mydriver.findElements(By.xpath(subsectionTitles));
			List<WebElement> descriptions = mydriver.findElements(By.xpath(subsectionDescription));
			int i = 0;
			for (WebElement title : titles) {
				scrollToElement(mydriver, title, logger);
				hardAssert.assertTrue(verifyElementExists(logger, title, title.getAttribute("innerText")));
				hardAssert.assertTrue(verifyElementExists(logger, descriptions.get(i),
						descriptions.get(i).getAttribute("innerText")));
				i++;
			}
			i=0;
			for (WebElement title : titles) {
				scrollToElement(mydriver, title, logger);
				hardAssert.assertFalse(title.getAttribute("innerText").isEmpty());
				hardAssert.assertFalse(descriptions.get(i).getAttribute("innerText").isEmpty());
				i++;
			}


	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"thought-leadership"})
	public void imageVisiblityCheck(String url) {
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			List<WebElement> images = mydriver.findElements(By.xpath(subsectionWithImages));
			for(WebElement image: images) {
				scrollToElement(mydriver, image, logger);
				hardAssert.assertTrue(verifyElementExists(logger, image, "image field"));
			}
			

	}
	
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"thought-leadership"})
	public void buttonVisibilityAndRedirectionCheck(String url) {
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			List<WebElement> buttons = mydriver.findElements(By.xpath(subsectionButtons));
			int i = getRandomInteger(buttons.size(), 0);
			scrollToElement(mydriver, buttons.get(i), logger);
			hardAssert.assertTrue(verifyElementExists(logger, buttons.get(i), "Thought Leadership Button"));
			hardAssert.assertFalse(buttons.get(i).getAttribute("href").isEmpty());
			String handle = mydriver.getWindowHandle();
			String expUrl = buttons.get(i).getAttribute("href");
			buttons.get(i).click();
			pleaseWait(5, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expUrl, handle);
			

	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"thought-leadership"})
	public void blankButtonLabelCheck(String url) {
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			List<WebElement> buttons = mydriver.findElements(By.xpath(subsectionButtons));
			for(WebElement label: buttons) {
				scrollToElement(mydriver, label, logger);
				hardAssert.assertFalse(label.getAttribute("innerText").isEmpty());
			}

	}
}