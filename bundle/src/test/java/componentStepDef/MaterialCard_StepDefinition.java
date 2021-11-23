package componentStepDef;import static org.testng.Assert.fail;

import java.net.URISyntaxException;
import java.util.ArrayList;
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

import compontentPages.MaterialCard_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MaterialCard_StepDefinition extends MaterialCard_page {
	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() throws URISyntaxException, InterruptedException {

		fetchSession(MaterialCard_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MaterialCard_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new MaterialCard_page();
		ExtentTestManager.startTest(MaterialCard_StepDefinition.class.getName());
		setTagForTestClass("Material Card", author, MaterialCard_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MaterialCard_StepDefinition.class);
		logger.info("Urls for '" + MaterialCard_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MaterialCard_StepDefinition.class.getName(), currentDomain);

		driverMap.put(MaterialCard_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + MaterialCard_StepDefinition.class.getName() + "' =>\n" + driverMap);

		// matUrls = getComponentUrl("pluv2-intake");

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

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card section"})
	public void elementVisibilityCheck(String matUrl) {
		skipNonExistingComponent(matUrl);
			 mydriver.get(matUrl);
			currentDomain = currentDomain + "[" + matUrl + "]";
			List<WebElement> cards = mydriver
					.findElements(By.xpath("//*[@class=\"material-card section mcard--handled cq-Editable-dom\"]"));
			int i = 0;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				hardAssert.assertFalse(mydriver.findElements(By.xpath(titles)).get(i).getText().isEmpty());
				hardAssert.assertFalse(mydriver.findElements(By.xpath(buttons)).get(i).getText().isEmpty());

			}
	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card section"})
	public void additionalFieldsVisibilityCheck(String matUrl) {
		skipNonExistingComponent(matUrl);
		
			 mydriver.get(matUrl);
			currentDomain = currentDomain + "[" + matUrl + "]";
			List<WebElement> cards = mydriver
					.findElements(By.xpath("//*[@class=\"material-card section mcard--handled cq-Editable-dom\"]"));
			List<WebElement> subtitles = null;
			List<WebElement> descriptions = null;

			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				try {
					subtitles = mydriver.findElements(By.xpath(subTitles));

				} catch (Exception e) {
					logger.error("subtitles are not available");
					fail("subtitles are not available");
				}
				for (WebElement subtitle : subtitles) {
					scrollToElement(mydriver, subtitle, logger);
					focusElement(mydriver, subtitle);
					hardAssert.assertFalse(subtitle.getText().isEmpty());
				}
				try {
					descriptions = mydriver.findElements(By.xpath(MaterialCard_page.descriptions));

				} catch (Exception e) {
					logger.error("Descriptions are not available");
					fail("Descriptions are not available");
				}
				for (WebElement description : descriptions) {
					scrollToElement(mydriver, description, logger);
					focusElement(mydriver, description);
					hardAssert.assertFalse(description.getText().isEmpty());
				}

			}
		
	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card section"})
	public void buttonRedirectionCheck(String matUrl) {
		skipNonExistingComponent(matUrl);
		
			int i = 0;
			 mydriver.get(matUrl);
			currentDomain = currentDomain + "[" + matUrl + "]";
			List<WebElement> cards = mydriver.findElements(By.xpath("//*[contains(@class,\"material-card section mcard\") ]/a[@href]"));
			i = getRandomInteger(cards.size(), 0);
			scrollToElement(mydriver, cards.get(i), logger);
			String expLink = cards.get(i).getAttribute("href");
//			clickWithJS(cards.get(i), mydriver);
//			getActions(mydriver).click(cards.get(i)).perform();
			String handle = mydriver.getWindowHandle();
			cards.get(i).click();
			pleaseWait(4, logger);
			jsWaitForPageToLoad(10,mydriver, logger);
			assertRedirection(mydriver, logger, getDomainName(matUrl), expLink,handle);
			
		
	}

}
