package componentStepDef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.IconPickerNew_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class IconPickerNew_StepDefinition extends IconPickerNew_page {
	private String author = "Prateek Srivastava";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(IconPickerNew_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(IconPickerNew_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new IconPickerNew_page();
		ExtentTestManager.startTest(IconPickerNew_StepDefinition.class.getName());
		setTagForTestClass("IconPickerNew", author, IconPickerNew_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(IconPickerNew_StepDefinition.class);
		logger.info("Urls for '" + IconPickerNew_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(IconPickerNew_StepDefinition.class.getName(), currentDomain);

		driverMap.put(IconPickerNew_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + IconPickerNew_StepDefinition.class.getName() + "' =>\n" + driverMap);
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

	/*
	 * @Test(priority = 2, enabled = true,dataProvider = "data-provider",
	 * dataProviderClass = CustomDataProvider.class, parameters =
	 * {"icon-picker-v2"}) public void blankHeaderCheck(String url) {
	 * HashMap<String, Boolean> assertConditionMap =
	 * skipNonExistingComponent(cardUrls);
	 * 
	 * mydriver.get(cardUrl); String expURL =
	 * mydriver.findElement(By.xpath(iconField)).getAttribute("href");
	 * assertRedirection(mydriver, logger, getDomainName(cardUrl), expURL); }
	 */
	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"icon-picker-v2" })
	public void iconPickerVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);

		List<WebElement> iconPicker = mydriver.findElements(By.xpath(IconPickerNew_page.iconField));

		scrollToElement(mydriver, iconPicker.get(0), logger);

		// WebElement iconPicker =
		// mydriver.findElements(By.xpath(IconPickerNew_page.iconField));
		hardAssert.assertTrue(verifyElementExists(logger, iconPicker.get(0), "Icon Picker Icon"));

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"icon-picker-v2" })
	public void linkTextCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);

		List<WebElement> iconPickerLink = mydriver.findElements(By.xpath(IconPickerNew_page.iconWithLink));
		int i = getRandomInteger(iconPickerLink.size(), 0);
		try {
			scrollToElement(mydriver, iconPickerLink.get(i), logger);
		} catch (Exception e) {
			throw new SkipException("Icons with links are not present");
		}
		hardAssert.assertFalse(iconPickerLink.get(i).getAttribute("href").isEmpty());
		logger.info("Redirection Link ==> " + iconPickerLink.get(i).getAttribute("href"));
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"icon-picker-v2" })
	public void linkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);

		List<WebElement> iconPickerLink = mydriver.findElements(By.xpath(IconPickerNew_page.iconWithLink));
		String handle = mydriver.getWindowHandle();
		int i = getRandomInteger(iconPickerLink.size(), 0);
		try {
			scrollToElement(mydriver, iconPickerLink.get(i), logger);
		} catch (Exception e) {
			throw new SkipException("Icons with links are not present");
		}
		String expLink = iconPickerLink.get(i).getAttribute("href");

		iconPickerLink.get(i).click();

		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
	}

}
