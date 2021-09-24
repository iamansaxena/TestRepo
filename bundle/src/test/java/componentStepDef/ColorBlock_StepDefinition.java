package componentStepDef;

import static org.testng.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ColorBlock_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ColorBlock_StepDefinition extends ColorBlock_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(ColorBlock_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ColorBlock_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new ColorBlock_page();
		ExtentTestManager.startTest(ColorBlock_StepDefinition.class.getName());
		setTagForTestClass("ColorBlock", author, ColorBlock_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ColorBlock_StepDefinition.class);
		logger.info("Urls for '" + ColorBlock_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ColorBlock_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ColorBlock_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + ColorBlock_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	// NOT POSSIBLE TO HANDLE THIS TEST CASE
	// @Test(priority = 1, enabled = true,dataProvider = "data-provider",
	// dataProviderClass = CustomDataProvider.class, parameters = {"color-block"})
	// public void imageAlignmentCheck(String url) {
	// skipNonExistingComponent(cardUrls);
	//
	//
	// int i = 0;
	//
	// 
	// mydriver.get(url);
	//
	// List<WebElement> blocks = mydriver.findElements(By.xpath(blockSection));
	// for (WebElement block : blocks) {
	// int j = i + 1;
	// scrollToElement(mydriver, block);
	// WebElement imageSection = mydriver.findElement(By.xpath(
	// "(//*[@class=\"color-block section\"])[" + j +
	// "]//*[contains(@class,\"section-inner img\")]"));
	// try {
	// String image = imageSection.getAttribute("style");
	// if (!image.isEmpty() || image != null) {
	// mydriver.findElement(By.xpath(""));
	// }
	// } catch (Exception e) {
	//
	// }
	// i++;
	// }
	// }

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"color-block" })
	public void titleLabel(String url) {
		skipNonExistingComponent(url);

		int i = 0;

		
		mydriver.get(url);

		List<WebElement> blocks = mydriver.findElements(By.xpath(blockSection));
		List<WebElement> titles = mydriver.findElements(
				By.xpath("//*[@class=\"color-block section\"]//*[contains(@class,\"section-inner img\")]//h2"));

		for (WebElement block : blocks) {
			scrollToElement(mydriver, block, logger);

			int j = i + 1;
			String titleText;
			String titleTextWithLink;
			try {
				titleText = titles.get(i).getText();

				if (!titleText.isEmpty()) {

					logger.info("Title '" + j + "' => " + titleText);
				} else {
					titleTextWithLink = mydriver.findElement(By.xpath(
							"(//*[@class=\"color-block section\"]//*[contains(@class,\"section-inner img\")]//h2)[" + j
									+ "]/a"))
							.getText();
					if (!titleText.isEmpty()) {

						logger.info("Title '" + j + "' => " + titleText);
					} else {
						fail("Blank Title '" + j + "'");
					}
				}
			} catch (NoSuchElementException e) {

			}

			i++;
			titleText = null;
			titleTextWithLink = null;
		}
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"color-block" })
	public void linkButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		List<WebElement> buttons;
		int i = 0;
		
		mydriver.get(url);
		try {
			mydriver.findElement(By.xpath(linkButton)).isDisplayed();
			buttons = mydriver.findElements(By.xpath(linkButton));
		} catch (Exception f) {
			throw new SkipException("There's no link button present on the banner");
		}
		for (WebElement button : buttons) {
			buttons = mydriver.findElements(By.xpath(linkButton));
			scrollToElement(mydriver, buttons.get(i), logger);
			String hyperlink = buttons.get(i).getAttribute("href");
			int currentTabSize = mydriver.getWindowHandles().size();
			if (getDomainName(mydriver.getCurrentUrl()).equals(getDomainName(hyperlink))) {
				JavascriptExecutor jse = (JavascriptExecutor) mydriver;
				jse.executeScript("arguments[0].click()", buttons.get(i));
				logger.info(" Internal Link [" + hyperlink + "]:  Hyperlink must get open in the same tab");
				hardAssert.assertEquals(mydriver.getWindowHandles().size(), currentTabSize);
				pleaseWait(1, logger);
				mydriver.navigate().back();
			} else if (!getDomainName(mydriver.getCurrentUrl()).equals(getDomainName(hyperlink))) {
				buttons.get(i).click();
				logger.info(" External Link:  Hyperlink must get open in a new tab");
				hardAssert.assertNotEquals(mydriver.getWindowHandles().size(), currentTabSize);
			}
			i++;
		}
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"color-block" })
	public void videoButtonCheck(String url) {
		skipNonExistingComponent(url);

		if (Environment.equalsIgnoreCase("uat")) {
			throw new SkipException("Video specific TC can't be tested on UAT");
		}
		
		mydriver.get(url);
		List<WebElement> buttons;
		List<WebElement> videoModal = mydriver.findElements(By.xpath("//*[@class=\"vjs-poster\"]"));
		pleaseWait(6, logger);
		try {
			mydriver.findElement(By.xpath(videoButton)).isDisplayed();
			scrollToElement(mydriver, mydriver.findElement(By.xpath(videoButton)), logger);
			buttons = mydriver.findElements(By.xpath(videoButton));

		} catch (Exception f) {
			throw new SkipException("There's no video button present on the color-block");
		}

		int i = getRandomInteger(buttons.size(), 0);
		scrollToElement(mydriver, buttons.get(i), logger);
		buttons.get(i).click();
		getVisibility(mydriver, videoModal.get(i), 30);
		mydriver.findElements(By.xpath("(//*[@class=\"modal__close\"])")).get(i).click();

	}

}