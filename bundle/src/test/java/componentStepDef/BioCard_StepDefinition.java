package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.BioCard_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class BioCard_StepDefinition extends BioCard_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(BioCard_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(BioCard_StepDefinition.class.getName());
		new BioCard_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("bio-card") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5642:8080/content/AutomationDirectory/bio-card.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("http://apvrt31468:4503/content/AutomationDirectory/bio-card.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("bio-card").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(BioCard_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("BioCard", author, currentDomain, BioCard_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(BioCard_StepDefinition.class);
		logger.info("Urls for '" + BioCard_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(BioCard_StepDefinition.class.getName(), currentDomain);

		driverMap.put(BioCard_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + BioCard_StepDefinition.class.getName() + "' =>\n" + driverMap);
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

	@Test(priority = 1, enabled = true)
	public void elementVisiblityCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			int i = 0;
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> cards = mydriver.findElements(By.xpath(BioCard_page.cards));
			List<WebElement> buttons = mydriver.findElements(By.xpath(expandButton));
			List<WebElement> speakerNames = mydriver.findElements(By.xpath(speakerNameFront));
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				scrollToElement(mydriver, buttons.get(i), logger);
				int j = i;
				hardAssert.assertTrue(
						verifyElementExists(logger, buttons.get(i), "Bio card expand button '" + ++j + "' "));
				scrollToElement(mydriver, speakerNames.get(i), logger);
				if (speakerNames.get(i).getText().isEmpty()) {
					fail("Speaker Name '" + i + "' is blank");
				} else {
					logger.info("Speaker Name '" + i + "' => " + speakerNames.get(i).getText());
				}
				i++;
			}
		}
	}

	@Test(priority = 2, enabled = true)
	public void bioButtonBioSlideInCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			int j = 1;
			int i = 0;
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> cards = mydriver.findElements(By.xpath(BioCard_page.cards));
			List<WebElement> buttons = mydriver.findElements(By.xpath(expandButton));
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				scrollToElement(mydriver, buttons.get(i), logger);
				buttons.get(i).click();
				getVisibility(mydriver,
						mydriver.findElement(By.xpath("(//*[contains(@class,\"card__back\")])[" + j + "]")), 4);

				i++;
				j++;
			}
		}
	}

	@Test(priority = 3, enabled = true)
	public void twitterHandleField() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			int j = 1;
			int i = 0;
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> cards = mydriver.findElements(By.xpath(BioCard_page.cards));
			List<WebElement> buttons = mydriver.findElements(By.xpath(expandButton));
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				scrollToElement(mydriver, buttons.get(i), logger);
				buttons.get(i).click();
				getVisibility(mydriver,
						mydriver.findElement(By.xpath("(//*[contains(@class,\"card__back\")])[" + j + "]")), 4);
				try {
					scrollToElement(mydriver, mydriver.findElements(By.xpath(twitterHandle)).get(i), logger);
					if (mydriver.findElements(By.xpath(twitterHandle)).get(i).getText().isEmpty()) {
						fail("Twitter handle '" + i + "': field is blank");
					} else {
						logger.info("Twitter handle '" + i + "' => "
								+ mydriver.findElements(By.xpath(twitterHandle)).get(i).getText());
					}
				} catch (NoSuchElementException e) {
					// handling for cards with no twitter handle
				} catch (IndexOutOfBoundsException e) {
					// handling for cards with no twitter handle
				}

				i++;
				j++;
			}
		}

	}

	@Test(priority = 4, enabled = true)
	public void speakerTitleField() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			int i = 0;
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> cards = mydriver.findElements(By.xpath(BioCard_page.cards));
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				try {
					mydriver.findElements(By.xpath(speakerTitleFront)).get(i);
					scrollToElement(mydriver, mydriver.findElements(By.xpath(speakerTitleFront)).get(i), logger);
					if (mydriver.findElements(By.xpath(speakerTitleFront)).get(i).getText().isEmpty()) {
						
					} else {
						logger.info("Speaker Title '" + i + "' => "
								+ mydriver.findElements(By.xpath(speakerTitleFront)).get(i).getText());
					}
				} catch (AssertionError e) {
					fail("Speaker Title is available but blank");
				} catch (Exception e) {
 
				}
				i++;
			}
		}
	}

	@Test(priority = 5, enabled = true)
	public void elementVisibilityCheckBioSection() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			int i = 0;
			int j = 0;
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver, 30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(cards)));
			List<WebElement> cards = mydriver.findElements(By.xpath(BioCard_page.cards));
			i = getRandomInteger(cards.size(), 0);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(expandButton)).get(i), logger);
			i = 1;
			mydriver.findElements(By.xpath(expandButton)).get(i).click();
			j = i + 1;
			getVisibility(mydriver, mydriver.findElements(By.xpath(collapseButton)).get(i), 4);
			hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(collapseButton)).get(i),
					"Collapse button for card '" + j + "'"));
			hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(speakerNameBack)).get(i),
					"Speaker Name on bio section for card '" + j + "'"));
			try {
				verifyElementExists(logger,
						mydriver.findElement(By.xpath("(//*[contains(@class,\"card__back\")])[" + j + "]/p")),
						"Bio for card '" + j + "'");
				logger.info("Bio for card '" + j + "' => " + mydriver
						.findElement(By.xpath("(//*[contains(@class,\"card__back\")])[" + j + "]/p")).getText());

			} catch (Exception e) {
				verifyElementExists(logger,
						mydriver.findElement(By.xpath("(//*[contains(@class,\"card__back\")])[" + j + "]/button")),
						"Default Bio for card '" + j + "'");
				logger.info("Default Bio for card '" + j + "' => " + mydriver
						.findElement(By.xpath("(//*[contains(@class,\"card__back\")])[" + j + "]/button")).getText());
			}

		}
	}

	@Test(priority = 6, enabled = true)
	public void collapseBioButton() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			int i = 0;
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> cards = mydriver.findElements(By.xpath(BioCard_page.cards));
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				mydriver.findElements(By.xpath(expandButton)).get(i).click();
				getVisibility(mydriver, mydriver.findElements(By.xpath(collapseButton)).get(i), 4);
				mydriver.findElements(By.xpath(collapseButton)).get(i).click();
				getVisibility(mydriver, mydriver.findElements(By.xpath(speakerNameFront)).get(i), 4);
				i++;
			}
		}
	}
}