package componentStepDef;

import static org.testng.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;
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

import compontentPages.Card300Image_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class Card300Image_StepDefinition extends Card300Image_page {
	private String author = "Sai Tummala";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {
		fetchSession(Card300Image_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(Card300Image_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new Card300Image_page();
		ExtentTestManager.startTest(Card300Image_StepDefinition.class.getName());
		setTagForTestClass("MaterialCard300", author, Card300Image_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(Card300Image_StepDefinition.class);
		logger.info("Urls for '" + Card300Image_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(Card300Image_StepDefinition.class.getName(), currentDomain);

		driverMap.put(Card300Image_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + Card300Image_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		currentDomain = "";
	}

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mcard__image300" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> cards = mydriver.findElements(By.xpath(Card300Image_page.cardsXpath));
		int i = 0;
		for (WebElement card : cards) {
			scrollToElement(mydriver, card, logger);
			WebElement title = mydriver.findElements(By.xpath(Card300Image_page.titlesXpath)).get(i);
			WebElement content = mydriver.findElements(By.xpath(Card300Image_page.contentXpath)).get(i);
			focusElement(mydriver, content);
			focusElement(mydriver, title);
			if (!title.getText().isEmpty()) {
				logger.info("Card -1 Title: '"
						+ mydriver.findElements(By.xpath(Card300Image_page.titlesXpath)).get(i).getText()
						+ "'\n\t Content ==> "
						+ mydriver.findElements(By.xpath(Card300Image_page.contentXpath)).get(i).getText());
			} else {
				fail("Material card found with blank Title/Content");
			}
			i++;
		}

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mcard__image300" })
	public void card300BrokenImageCheck(String url) {

		skipNonExistingComponent(url);

		
		mydriver.get(url);

		List<WebElement> images = mydriver.findElements(By.xpath(Card300Image_page.imagesXpath));
		int i = getRandomInteger(images.size(), 0);
		scrollToElement(mydriver, images.get(i), logger);
		String imageLink = images.get(i).getAttribute("src");
		logger.info("Card Image Link ==>" + imageLink);
		try {
			URL imgUrl = new URL(imageLink);
			logger.info("Image Link => " + imgUrl);
		} catch (MalformedURLException e) {
			fail("Image link is invalid on material Image detail card");
			logger.error("Image link is invalid on material Image detail card");
		}

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mcard__image300" })
	public void card300LinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		List<WebElement> links;

		try {
			scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@qa-handle=\"mcard-image300\"]//*[@href]")),
					logger);
		} catch (Exception e) {
			throw new SkipException("No Card Found with link");

		}
		links = mydriver.findElements(By.xpath("//*[@qa-handle=\"mcard-image300\"]//*[@href]"));
		int i = getRandomInteger(links.size(), 0);
		scrollToElement(mydriver, links.get(i), logger);
		String myTab = mydriver.getWindowHandle();
		String linkUrl = links.get(i).getAttribute("href");
		links.get(i).click();
		pleaseWait(5, logger);
		assertRedirection(mydriver, logger, getDomainName(url), linkUrl, myTab);

	}

}