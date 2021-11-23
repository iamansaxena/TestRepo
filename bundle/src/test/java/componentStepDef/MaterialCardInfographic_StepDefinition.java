package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MaterialCardInfographic_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MaterialCardInfographic_StepDefinition extends MaterialCardInfographic_page {
	private String author = "Aman Saxena";

	private static String currentDomain = "=>";

	private static ArrayList<String> cardUrls= new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(MaterialCardInfographic_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MaterialCardInfographic_StepDefinition.class.getName());
		new MaterialCardInfographic_page();
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("material-card-infographic") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5642:8080/content/AutomationDirectory/material-card-infographic.html");
			} else if (Environment.equalsIgnoreCase("test")) {
			cardUrls.add("http://apvrt31468:4503/content/AutomationDirectory/material-card-infographic.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("material-card-infographic").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(MaterialCardInfographic_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("MaterialCardInfographic", author, currentDomain,
				MaterialCardInfographic_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MaterialCardInfographic_StepDefinition.class);
		logger.info("Urls for '" + MaterialCardInfographic_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MaterialCardInfographic_StepDefinition.class.getName(), currentDomain);

		driverMap.put(MaterialCardInfographic_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info(
				"Browser pool at '" + MaterialCardInfographic_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true)
	public void elementVisibilityCheck() {

		skipNonExistingComponent(cardUrls);
		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);

			List<WebElement> cards = mydriver.findElements(By.xpath(MaterialCardInfographic_page.cards));
			int i = 0;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				WebElement title = mydriver.findElements(By.xpath(MaterialCardInfographic_page.titles)).get(i);
				WebElement description = mydriver.findElements(By.xpath(MaterialCardInfographic_page.descriptions))
						.get(i);
				focusElement(mydriver, description);
				focusElement(mydriver, title);
				if (!description.getText().isEmpty() && !title.getText().isEmpty()) {
					logger.info(
							"Card -1 Title: '"
									+ mydriver.findElements(By.xpath(MaterialCardInfographic_page.titles)).get(i)
											.getText()
									+ "'\n\t Description ==> "
									+ mydriver.findElements(By.xpath(MaterialCardInfographic_page.descriptions)).get(i)
											.getText());
				} else {
					fail("Material Infographic card found with blank Title/Description");
				}
				i++;
			}

		}
	}

//	Disabling this as we are not covering the content 
	/*@Test(priority = 2, enabled = false)
	public void brokenImageCheck() {
		skipNonExistingComponent(cardUrls);
		for (String cardUrl : cardUrls) {
			customLogsPool.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> images = mydriver.findElements(By.xpath(MaterialCardInfographic_page.images));
			int i = getRandomInteger(images.size(), 0);
			scrollToElement(mydriver, images.get(i));
			focusElement(mydriver, images.get(i));
			String imageLink = images.get(i).getAttribute("src");
			logger.info("Card Image link ==> " + imageLink);
			customLogsPool.get().add(cardUrl); mydriver.get(imageLink);
			if (mydriver.getTitle().contains("404")) {
				fail("Broken Image Found");
			}
		}
	}*/

	@Test(priority = 3, enabled = true)
	public void redirectionLinkUnavailabilityCheck() {
		skipNonExistingComponent(cardUrls);
		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> cards = mydriver.findElements(By.xpath(MaterialCardInfographic_page.cards));
			int i = 1;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				try {
					mydriver.findElement(
							By.xpath("//*[@class=\"material-card-info-graphic section\"][" + i + "]//*[@href]"));
				} catch (Exception e) {
					try {
						focusElement(mydriver,
								mydriver.findElement(By.xpath("//*[@class=\"material-card-info-graphic section\"][" + i
										+ "]//*[@class=\"button button--reverse\"]")));
						fail("Card Button is available even without any hyperlink");
					} catch (Exception f) {

					}

				}
				i++;
			}
		}
	}

	@Test(priority = 4, enabled = true)
	public void redirectionButtonAvailabilityCheck() {
		skipNonExistingComponent(cardUrls);
		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> cards = mydriver.findElements(By.xpath(MaterialCardInfographic_page.cards));
			int i = 1;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				WebElement cardLink = mydriver.findElement(By.xpath(
						"(//*[@class=\"material-card-info-graphic section\"]//*[@class=\"button button--reverse\"])["
								+ i + "]"));
				if (cardLink.getAttribute("href").isEmpty()) {
					fail("Card Hyperlink is Empty");
				}
			}
		}
	}

	@Test(priority = 5, enabled = true)
	public void cardLinkRedirectionCheck() {
		skipNonExistingComponent(cardUrls);
		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> links;

			try {
				links = mydriver.findElements(By.xpath("//*[@class=\"material-card-info-graphic section\"]//*[@href]"));

			} catch (Exception e) {
				throw new SkipException("No Card Found with link");

			}
			int i = getRandomInteger(links.size(), 0);
			scrollToElement(mydriver, links.get(i), logger);
			String domain = getDomainName(mydriver.getCurrentUrl());
			String myTab = mydriver.getWindowHandle();
			String linkUrl = links.get(i).getAttribute("href");
			links.get(i).click();
			Set<String> allTabs = mydriver.getWindowHandles();

			if (getDomainName(linkUrl).equals(domain)) {
				hardAssert.assertEquals(mydriver.getWindowHandle(), myTab);
				logger.info("Internal Link opened in the same tab => " + linkUrl);
				mydriver.get(cardUrl);
			} else {
				allTabs.remove(myTab);
				mydriver.switchTo().window(allTabs.iterator().next());
				hardAssert.assertEquals(mydriver.getCurrentUrl(), linkUrl);
				logger.info("External Links opened in a new tab => " + linkUrl);
				mydriver.switchTo().window(myTab);

			}
		}
	}
}
// }
