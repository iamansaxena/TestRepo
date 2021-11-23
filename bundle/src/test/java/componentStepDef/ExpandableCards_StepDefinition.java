package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ExpandableCard_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ExpandableCards_StepDefinition extends ExpandableCard_page {
	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static ArrayList<String> expCardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() throws InterruptedException {

		fetchSession(ExpandableCards_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ExpandableCards_StepDefinition.class.getName());
<<<<<<< Updated upstream
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
=======
>>>>>>> Stashed changes
		new ExpandableCard_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("expCard-container") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				expCardUrls.add("http://apsrs5642:8080/content/AutomationDirectory/Expandable_Cards.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				expCardUrls.add("http://apvrt31468:4503/content/AutomationDirectory/Expandable_Cards.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("expCard-container").split(",");
			for (String link : scannedUrls) {
				expCardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(ExpandableCards_StepDefinition.class.getName());
		for (String url : expCardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("ExpandableCard", author, currentDomain, ExpandableCards_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ExpandableCards_StepDefinition.class);
		logger.info("Urls for '" + ExpandableCards_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ExpandableCards_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ExpandableCards_StepDefinition.class.getName().split("\\.")[1], mydriver);
		/*
		 * //Testing Concurrency int a =0; while (a == 0) { if(flag==0) {
		 * System.out.println("Not flagged"); } else { Thread.sleep(0);
		 * System.out.println(driverMap); a=1; } }
		 */
		logger.info("Browser pool at '" + ExpandableCards_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
		skipNonExistingComponent(expCardUrls);

		for (String expCardUrl : expCardUrls) {

			urlUnderTest.get().add(expCardUrl);
			mydriver.get(expCardUrl);
			int noImage = 0;
			int withImage = 0;
			pleaseWait(6, logger);
			List<WebElement> cards = mydriver.findElements(By.xpath(ExpandableCard_page.cards));
			for (int i = 0; i < cards.size(); i++) {
					scrollToElement(mydriver, cards.get(i), logger);
					focusElement(mydriver, mydriver.findElements(By.xpath(cardTitles)).get(i));
					focusElement(mydriver, mydriver.findElements(By.xpath(cardDescriptions)).get(i));
					
				
			}
			List<WebElement> bottomButtons = mydriver.findElements(By.xpath(openBottomButtons));
			int j = 0;
			for (WebElement bottomButton : bottomButtons) {
				focusElement(mydriver, bottomButton);
				focusElement(mydriver, mydriver.findElements(By.xpath(openTopButtons)).get(j));
				j++;
			}
		try {
			noImage = mydriver.findElements(By.xpath(cardWithoutImages)).size();
		} catch (Exception e) {
			logger.info("All the cards do have images");
			noImage = 0;
		}
		withImage = mydriver.findElements(By.xpath(cardImages)).size();

		if (noImage == 0) {
			hardAssert.assertEquals(withImage, cards.size());
		} else {
			int total = withImage + noImage;
			hardAssert.assertEquals(total, cards.size());
		}}

	}

	@Test(priority = 2, enabled = true)
	public void oneCardExpandedAtOnceCheck() {
		skipNonExistingComponent(expCardUrls);
		for (String expCardUrl : expCardUrls) {
			urlUnderTest.get().add(expCardUrl);
			mydriver.get(expCardUrl);

			List<WebElement> cards = mydriver.findElements(By.xpath(openTopButtons));
			int i = 0;
			try {
				mydriver.findElements(By.xpath(openTopButtons)).get(i).click();
			} catch (Exception e) {
				throw new SkipException("All the cards are non-expandable");
			}
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				
				scrollToElement(mydriver, mydriver.findElements(By.xpath(closeBottomButtons)).get(0), logger);

				hardAssert.assertEquals(mydriver.findElements(By.xpath(closeBottomButtons)).size(), 1);
				scrollToElement(mydriver, mydriver.findElement(By.xpath(closeTopButtons)), logger);
				pleaseWait(1, logger);
				mydriver.findElement(By.xpath(closeTopButtons)).click();
				i++;
			}
			// mydriver.findElements(By.xpath(closeBottomButtons)).get(0).click();
		}
	}

	@Test(priority = 3, enabled = true)
	public void bottomExpandButtonFuntionalityCheck() {
		skipNonExistingComponent(expCardUrls);
		for (String expCardUrl : expCardUrls) {
			urlUnderTest.get().add(expCardUrl);
			mydriver.get(expCardUrl);

			List<WebElement> cards = mydriver.findElements(By.xpath(ExpandableCard_page.cards));
			int nonExpandables = 0;
			int expandables = 0;
			
				scrollToElement(mydriver, cards.get(0), logger);
			
			try {
				expandables = mydriver.findElements(By.xpath(openTopButtons)).size();
				if (expandables == 0) {
					throw new Exception();
				}
			} catch (Exception e) {
				expandables = 0;
				logger.info("All the cards are non-expandable");
			}

			try {
				nonExpandables = mydriver.findElements(By.xpath(openBottomButtonsWithLinks)).size();
				if (nonExpandables == 0) {
					throw new Exception();
				}
			} catch (Exception e) {
				logger.info("There is no non-expandable cards");
				nonExpandables = 0;
			}
			if (nonExpandables == 0) {
				hardAssert.assertEquals(expandables, cards.size());
			} else if (expandables == 0) {
				hardAssert.assertEquals(nonExpandables, cards.size());
			} else {
				int total = nonExpandables + expandables;
				hardAssert.assertEquals(total, cards.size());
			}
		}
	}

	@Test(priority = 4, enabled = true)
	public void cardExpandFuntionalityCheck() {
		skipNonExistingComponent(expCardUrls);
		for (String expCardUrl : expCardUrls) {
			urlUnderTest.get().add(expCardUrl);
			mydriver.get(expCardUrl);

			List<WebElement> bottomButtons;
			try {
				mydriver.findElement(By.xpath(openBottomButtons)).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's no expandable card");
			}
			bottomButtons = mydriver.findElements(By.xpath(openBottomButtons));
			int i = getRandomInteger(bottomButtons.size(), 0);
			scrollToElement(mydriver, bottomButtons.get(i), logger);
			bottomButtons.get(i).click();
			scrollToElement(mydriver, expandedCardListContents, logger);
			hardAssert.assertTrue(expandedCardListContents.isDisplayed());
		}
	}

	@Test(priority = 5, enabled = true)
	public void expandedContentCheck() {
		skipNonExistingComponent(expCardUrls);
		for (String expCardUrl : expCardUrls) {
			urlUnderTest.get().add(expCardUrl);
			mydriver.get(expCardUrl);

			List<WebElement> bottomButtons;
			try {
				mydriver.findElement(By.xpath(openBottomButtons)).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's no expandable card");
			}
			bottomButtons = mydriver.findElements(By.xpath(openBottomButtons));
			int i = getRandomInteger(bottomButtons.size(), 0);
			scrollToElement(mydriver, bottomButtons.get(i), logger);

			// String desc = descriptions.get(i).getText();
			bottomButtons.get(i).click();
			scrollToElement(mydriver, expandedCardListContents, logger);
			hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElement(By.xpath(expandedCardDescriptions)),
					"Cards Description"));
		}
	}

	@Test(priority = 6, enabled = true)
	public void cardContractionViaTopButtonCheck() {
		skipNonExistingComponent(expCardUrls);
		for (String expCardUrl : expCardUrls) {
			urlUnderTest.get().add(expCardUrl);
			mydriver.get(expCardUrl);

			List<WebElement> bottomButtons;

			try {
				mydriver.findElement(By.xpath(openBottomButtons)).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's no expandable card");
			}
			bottomButtons = mydriver.findElements(By.xpath(openBottomButtons));
			int i = getRandomInteger(bottomButtons.size(), 0);
			scrollToElement(mydriver, bottomButtons.get(i), logger);
			bottomButtons.get(i).click();
			scrollToElement(mydriver, mydriver.findElement(By.xpath(closeTopButtons)), logger);
			pleaseWait(1, logger);
			mydriver.findElement(By.xpath(closeTopButtons)).click();
			boolean status = false;
			try {
				status = expandedCardListContents.isDisplayed();
			} catch (Exception e) {
				status = false;
			}
			if (status == true) {
				fail("Cards is not getting contract when top close button is clicked");
				logger.error("Cards is not getting contract when top close button is clicked");
			}
		}
	}

	@Test(priority = 7, enabled = true)
	public void cardContractionViaBottomButtonCheck() {
		skipNonExistingComponent(expCardUrls);
		for (String expCardUrl : expCardUrls) {
			urlUnderTest.get().add(expCardUrl);
			mydriver.get(expCardUrl);

			List<WebElement> bottomButtons;

			try {
				mydriver.findElement(By.xpath(openBottomButtons)).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's no expandable card");
			}
			bottomButtons = mydriver.findElements(By.xpath(openBottomButtons));
			int i = getRandomInteger(bottomButtons.size(), 0);
			scrollToElement(mydriver, bottomButtons.get(i), logger);
			bottomButtons.get(i).click();
			scrollToElement(mydriver, mydriver.findElement(By.xpath(closeBottomButtons)), logger);
			pleaseWait(1, logger);
			mydriver.findElement(By.xpath(closeBottomButtons)).click();
			pleaseWait(1, logger);
			boolean status = false;
			try {
				status = expandedCardListContents.isDisplayed();
			} catch (Exception e) {
				status = false;
			}
			if (status == true) {
				fail("Cards is not getting contract when top close button is clicked");
				logger.error("Cards is not getting contract when top close button is clicked");
			}
		}
	}

	@Test(priority = 8, enabled = true)
	public void cardWithLinkRedirectionCheck() {
		skipNonExistingComponent(expCardUrls);
		for (String expCardUrl : expCardUrls) {
			urlUnderTest.get().add(expCardUrl);
			mydriver.get(expCardUrl);

			String currentDomain;
			String linkType;
			String currentTab;
			List<WebElement> nonExps;
			String linkUrl;
			int i = 0;
			try {
				nonExps = mydriver.findElements(By.xpath(openBottomButtonsWithLinks));

				i = getRandomInteger(nonExps.size(), 0);
				scrollToElement(mydriver, nonExps.get(i), logger);
				linkType = getDomainName(nonExps.get(i).getAttribute("href"));
				currentDomain = getDomainName(mydriver.getCurrentUrl());
				currentTab = mydriver.getWindowHandle();
				linkUrl = nonExps.get(i).getAttribute("href");
				nonExps.get(i).click();
			} catch (Exception e) {
				throw new SkipException("There is no card with link available");
			}
			assertRedirection(mydriver, logger, getDomainName(expCardUrl), linkUrl, currentTab);
		}
	}
}