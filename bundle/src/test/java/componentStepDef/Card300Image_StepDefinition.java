package componentStepDef;import static org.testng.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class Card300Image_StepDefinition extends Card300Image_page {
	private String author = "Sai Tummala";
	private static Logger logger;
	private static ArrayList<String> matUrls= new ArrayList<>();
	//private static String[] matUrls = { "http://apsrs5642:8080/content/AutomationDirectory/MaterialCard300Image.html" };
	private static String currentDomain = "=>";
	
	@BeforeClass
	public void setup() {
		fetchSession(Card300Image_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(Card300Image_StepDefinition.class.getName());
		new Card300Image_page();
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("mcard-300") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
			matUrls.add("http://apsrs5642:8080/content/AutomationDirectory/materialcard300image.html");
		} else if (Environment.equalsIgnoreCase("test")) {
			matUrls.add("http://apvrt31468:4503/content/AutomationDirectory/materialcard300image.html");
		}
		} else {
			String[] scannedUrls = fetchUrl("mcard-300").split(",");
			for (String link : scannedUrls) {
				matUrls.add(link);
			}
		}

		ExtentTestManager.startTest(Card300Image_StepDefinition.class.getName());
		for (String url : matUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("MaterialCard300", author, currentDomain, Card300Image_StepDefinition.class.getName());
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
	
	@Test(priority = 1, enabled = true)
	public void elementVisibilityCheck() {
		skipNonExistingComponent(matUrls);
		for (String cardUrl : matUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> cards = mydriver.findElements(By.xpath(Card300Image_page.cardsXpath));
			int i = 0;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				WebElement title = mydriver.findElements(By.xpath(Card300Image_page.titlesXpath)).get(i);
				WebElement content = mydriver.findElements(By.xpath(Card300Image_page.contentXpath))
						.get(i);
				focusElement(mydriver, content);
				focusElement(mydriver, title);
				if (!content.getText().isEmpty() && !title.getText().isEmpty()) {
					logger.info(
							"Card -1 Title: '"
									+ mydriver.findElements(By.xpath(Card300Image_page.titlesXpath)).get(i)
									.getText()
									+ "'\n\t Content ==> "
									+ mydriver.findElements(By.xpath(Card300Image_page.contentXpath)).get(i)
									.getText());
				} else {
					fail("Material card found with blank Title/Content");
				}
				i++;
			}

		}
	}
	
	@Test(priority = 2, enabled = true)
	public void card300BrokenImageCheck() {

		skipNonExistingComponent(matUrls);
		for (String cardUrl : matUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);

			List<WebElement> images = mydriver.findElements(By.xpath(Card300Image_page.imagesXpath));
			int i = getRandomInteger(images.size(), 0);
			scrollToElement(mydriver, images.get(i), logger);
			String imageLink = images.get(i).getAttribute("src");
			logger.info("Card Image Link ==>" + imageLink);
			urlUnderTest.get().add(cardUrl); mydriver.get(imageLink);
			try {
				URL url = new URL(imageLink);
				logger.info("Image Link => " + url);
			} catch (MalformedURLException e) {
				fail("Image link is invalid on material Image detail card");
				logger.error("Image link is invalid on material Image detail card");
			}

		}
	}
	
	@Test(priority = 3, enabled = true)
	public void card300LinkRedirectionCheck() {
		skipNonExistingComponent(matUrls);
		for (String cardUrl : matUrls) {
			urlUnderTest.get().add(cardUrl); 
			mydriver.get(cardUrl);
			List<WebElement> links;

			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@qa-handle=\"mcard-image300\"]//*[@href]")), logger);
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
			assertRedirection(mydriver, logger, getDomainName(cardUrl), linkUrl, myTab);
			
		}
	}


}