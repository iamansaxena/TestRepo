package componentStepDef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.AboutUs_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class AboutUs_StepDefinition extends AboutUs_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(AboutUs_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(AboutUs_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new AboutUs_page();
		ExtentTestManager.startTest(AboutUs_StepDefinition.class.getName());
		setTagForTestClass("About-Us", author, AboutUs_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(AboutUs_StepDefinition.class);
		logger.info("Urls for '" + AboutUs_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(AboutUs_StepDefinition.class.getName(), currentDomain);
		driverMap.put(AboutUs_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + AboutUs_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
	"about-us" })
	public void minTwoTabsAvailabilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> Tabs = mydriver.findElements(By.xpath(tabs));
		hardAssert.assertEquals(Tabs.size(), 2);

	}

	@Test(priority = 2, enabled = false, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"about-us" })
	public void tabRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> Tabs = mydriver.findElements(By.xpath(tabs));
		int i = getRandomInteger(Tabs.size(), 0);
		String expUrl = Tabs.get(i).getAttribute("href");
		scrollToElement(mydriver, Tabs.get(i), logger);
		WebDriverWait wait = new WebDriverWait(mydriver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(Tabs.get(i)));
		JavascriptExecutor jse = (JavascriptExecutor) mydriver;
		jse.executeScript("arguments[0].click()", Tabs.get(i));
		// Actions action = new Actions(mydriver);
		// action.moveToElement(Tabs.get(i)).click().perform();
		// Tabs.get(i).click();
		pleaseWait(10, logger);
		if (mydriver.getCurrentUrl().contains("?wcmmode=disabled")) {
			hardAssert.assertEquals(mydriver.getCurrentUrl().split("//?wcmmode=disabled")[0], expUrl);
		} else {
			hardAssert.assertTrue(mydriver.getCurrentUrl().contains(expUrl));
		}

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
	"about-us" })
	public void blankTabLabelCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> Tabs = mydriver.findElements(By.xpath(tabs));

		for (WebElement tab : Tabs) {
			scrollToElement(mydriver, tab, logger);
			hardAssert.assertFalse(tab.getText().isEmpty());
		}
	}
}
