package componentStepDef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.BrokerHub_page;
import core.CustomDataProvider;
import runner.Retry;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class BrokerHub_StepDefinition extends BrokerHub_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(BrokerHub_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(BrokerHub_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new BrokerHub_page();
		ExtentTestManager.startTest(BrokerHub_StepDefinition.class.getName());
		setTagForTestClass("BrokerHub", author, BrokerHub_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(BrokerHub_StepDefinition.class);
		logger.info("Urls for '" + BrokerHub_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(BrokerHub_StepDefinition.class.getName(), currentDomain);

		driverMap.put(BrokerHub_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + BrokerHub_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
			"broker-hub" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, hubSection, logger);
		hardAssert.assertTrue(
				verifyElementExists(logger, sectionDesc, "Section Description ::> " + sectionDesc.getText()));
		hardAssert.assertTrue(
				verifyElementExists(logger, sectionHeading, "Section Heading ::> " + sectionHeading.getText()));
		hardAssert.assertTrue(verifyElementExists(logger, sectionIcon, "Section Icon"));
		hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElement(By.cssSelector(mapMiniButtons)),
				"Map mini location buttons"));
		hardAssert.assertTrue(verifyElementExists(logger, map, "Map"));
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"broker-hub" })
	public void blankHeadingAndDescCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, hubSection, logger);
		hardAssert.assertFalse(sectionDesc.getAttribute("innerText").isEmpty());
		hardAssert.assertFalse(sectionHeading.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"broker-hub" })
	public void stateSelectionFromDDCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, hubSection, logger);
		int i = getRandomInteger(mydriver.findElements(By.xpath(stateOptions)).size(), 1);
		String expStateName = mydriver.findElements(By.xpath(stateOptions)).get(i).getAttribute("innerText");
		selectByOptionName(logger, stateSelectionDropDown, expStateName);
		pleaseWait(4, logger);
		hardAssert.assertEquals(selectedState.getAttribute("innerText"), expStateName);
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"broker-hub" })
	public void stateSelectionFromMapEffectOnDDCheck(String url) {
		skipNonExistingComponent(url);
		if (browserName.toLowerCase().equals("firefox")) {
			throw new SkipException(
					"Can't execute this test on FireFox as actions class is not supported by this browser");
		}

		
		mydriver.get(url);
		scrollToElement(mydriver, hubSection, logger);
		List<WebElement> locations = mydriver.findElements(By.cssSelector(mapLocaitonsClickable));
		int i = getRandomInteger(locations.size(), 0);

		// List<WebElement> clickableLocation =
		// mydriver.findElements(By.cssSelector(mapMiniButtonsClickable));
		((JavascriptExecutor) mydriver).executeScript(
				"arguments[0].dispatchEvent(new MouseEvent('click', {view: window, bubbles:true, cancelable: true}))",
				locations.get(i));

		pleaseWait(5, logger);

		String expLocationName = mydriver.findElements(By.cssSelector(mapLocaitons)).get(i).getAttribute("id");
		Select a = new Select(stateSelectionDropDown);
		logger.info("State displayed in DD: " + a.getFirstSelectedOption().getText() + "\nState selected from Map: "
				+ expLocationName);
		hardAssert.assertTrue(a.getFirstSelectedOption().getText().toLowerCase()
				.contains(expLocationName.toLowerCase().subSequence(0, 1))
				&& a.getFirstSelectedOption().getText().toLowerCase()
						.contains(expLocationName.toLowerCase().subSequence(1, 2)));
	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"broker-hub" })
	public void stateSelectionFromMapEffectOnSelectedStateFieldCheck(String url) {
		skipNonExistingComponent(url);
		if (browserName.toLowerCase().equals("firefox")) {
			throw new SkipException(
					"Can't execute this test on FireFox as actions class is not supported by this browser");
		}

		
		mydriver.get(url);
		scrollToElement(mydriver, hubSection, logger);
		List<WebElement> locations = mydriver.findElements(By.cssSelector(mapLocaitonsClickable));
		int i = getRandomInteger(locations.size(), 0);
		// List<WebElement> clickableLocation =
		// mydriver.findElements(By.cssSelector(mapMiniButtonsClickable));
		((JavascriptExecutor) mydriver).executeScript(
				"arguments[0].dispatchEvent(new MouseEvent('click', {view: window, bubbles:true, cancelable: true}))",
				locations.get(i));
		getVisibility(mydriver, selectedState, 10);
		String expLocationName = mydriver.findElements(By.cssSelector(mapLocaitons)).get(i).getAttribute("id");
		Select a = new Select(stateSelectionDropDown);
		logger.info("You selected: " + selectedState.getAttribute("innerText") + "\nState selected from Map: "
				+ expLocationName);
		hardAssert.assertTrue(selectedState.getAttribute("innerText").toLowerCase()
				.contains(expLocationName.toLowerCase().subSequence(0, 1))
				&& a.getFirstSelectedOption().getText().toLowerCase()
						.contains(expLocationName.toLowerCase().subSequence(1, 2)));
	}

	@Test(priority = 6, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"broker-hub" }, retryAnalyzer = Retry.class)
	public void locationHighlightONSelectionFromMapCheck(String url) {
		skipNonExistingComponent(url);
		if (browserName.toLowerCase().equals("firefox")) {
			throw new SkipException(
					"Can't execute this test on FireFox as actions class is not supported by this browser");
		}

		
		mydriver.get(url);
		scrollToElement(mydriver, hubSection, logger);
		List<WebElement> locations = mydriver.findElements(By.cssSelector(mapLocaitonsClickable));
		int i = getRandomInteger(locations.size(), 1);
		String expColor = locations.get(i).getCssValue("fill");
		// focusElement(mydriver, locations.get(i));
		// List<WebElement> clickableLocation =
		// mydriver.findElements(By.cssSelector(mapMiniButtonsClickable));
		((JavascriptExecutor) mydriver).executeScript(
				"arguments[0].dispatchEvent(new MouseEvent('click', {view: window, bubbles:true, cancelable: true}))",
				locations.get(i));
		pleaseWait(8, logger);
		String actualColor = mydriver.findElements(By.cssSelector(mapLocaitons)).get(i).getCssValue("fill");
		logger.info("Color Before Selection: " + expColor + "\nColor after selection: " + actualColor);
		hardAssert.assertNotEquals(actualColor, expColor);
	}

	@Test(priority = 7, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"broker-hub" })
	public void locationSelectionFromMiniMapButtonCheck(String url) {
		skipNonExistingComponent(url);
		if (browserName.toLowerCase().equals("firefox")) {
			throw new SkipException(
					"Can't execute this test on FireFox as actions class is not supported by this browser");
		}

		
		mydriver.get(url);
		scrollToElement(mydriver, hubSection, logger);
		List<WebElement> locations = mydriver.findElements(By.cssSelector(mapMiniButtons));
		List<WebElement> clickableLocation = mydriver.findElements(By.cssSelector(mapMiniButtonsClickable));
		int i = getRandomInteger(locations.size(), 0);
		((JavascriptExecutor) mydriver).executeScript(
				"arguments[0].dispatchEvent(new MouseEvent('click', {view: window, bubbles:true, cancelable: true}))",
				clickableLocation.get(i));
		// Actions act = new Actions(mydriver);
		// act.click(locations.get(i)).build().perform();
		// getActions(mydriver).click(locations.get(i)).perform();
		// locations.get(i).click();
		pleaseWait(3, logger);
		String miniLocName = locations.get(i).getText();
		String expLocationName = new Select(stateSelectionDropDown).getFirstSelectedOption().getAttribute("innerText");
		logger.info("Mini location selected : " + miniLocName + "\nState selected in DD: " + expLocationName);
		hardAssert.assertTrue(expLocationName.toLowerCase().contains(miniLocName.toLowerCase().subSequence(0, 1))
				&& expLocationName.toLowerCase().contains(miniLocName.toLowerCase().subSequence(1, 2)));
	}

}
