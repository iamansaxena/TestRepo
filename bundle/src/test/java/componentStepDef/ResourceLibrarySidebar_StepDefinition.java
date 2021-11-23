package componentStepDef;import java.util.concurrent.TimeUnit;

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

import compontentPages.ResourceLibrarySidebar_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ResourceLibrarySidebar_StepDefinition extends ResourceLibrarySidebar_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(ResourceLibrarySidebar_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ResourceLibrarySidebar_StepDefinition.class.getName());
		new ResourceLibrarySidebar_page();
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("resource-lib-sidebar") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5642:8080/content/AutomationDirectory/resourcelibrarysidebar.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("http://apvrt31468:4503/content/AutomationDirectory/resourcelibrarysidebar.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("resource-lib-sidebar").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(ResourceLibrarySidebar_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("Resource Library Sidebar", author, currentDomain,
				ResourceLibrarySidebar_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ResourceLibrarySidebar_StepDefinition.class);
		logger.info("Urls for '" + ResourceLibrarySidebar_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ResourceLibrarySidebar_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ResourceLibrarySidebar_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + ResourceLibrarySidebar_StepDefinition.class.getName() + "' =>\n" + driverMap);
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
	public void newsLetterLabelCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			try {
				scrollToElement(mydriver, newsLetterLabel, logger);
			} catch (Exception e) {
				throw new SkipException("No news Letter Label field is available");
			}
			hardAssert.assertFalse(newsLetterLabel.getText().isEmpty());
			logger.info("New Letter Label => " + newsLetterLabel.getText());
		}
	}

	@Test(priority = 2, enabled = true)
	public void newsLetterRedirectionCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			try {
				scrollToElement(mydriver, newsLetterButton, logger);
			} catch (Exception e) {
				throw new SkipException("No news Letter redirection buttonis available");
			}
			hardAssert.assertFalse(newsLetterButton.getText().isEmpty());
			logger.info("New Letter Label => " + newsLetterButton.getText());
			// String[] expectedLinkArr = newsLetterButton.getAttribute("href").split("/");
			// String expectedLink = expectedLinkArr[expectedLinkArr.length-1];
			String expectedLink = newsLetterButton.getAttribute("href");
			logger.info("Expected url=> " + expectedLink);
			String handle = mydriver.getWindowHandle();
			newsLetterButton.click();
			pleaseWait(5, logger);

			assertRedirection(mydriver, logger, getDomainName(cardUrl), expectedLink, handle);
			/*
			 * if(mydriver.getCurrentUrl().contains(".html")) {
			 * logger.info("Actual url for 'newsLetterRedirectionCheck' => "+mydriver.
			 * getCurrentUrl().split(".html")[0]);
			 * hardAssert.assertTrue(mydriver.getCurrentUrl().split(".html")[0].contains(
			 * expectedLink)); } else {
			 * logger.info("Actual url for 'newsLetterRedirectionCheck' => "+mydriver.
			 * getCurrentUrl().substring(0, mydriver.getCurrentUrl().length() - 1));
			 * hardAssert.assertTrue(mydriver.getCurrentUrl().contains(expectedLink)); }
			 */

		}
	}

	@Test(priority = 3, enabled = true)
	public void expertiseSectionDefaultElementsCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			int i = 0;

			try {
				scrollToElement(mydriver, expertiesSectionHeader, logger);
				softAssert.assertFalse(expertiesSectionHeader.getText().isEmpty());
			} catch (Exception e) {

			}
			try {
				scrollToElement(mydriver, expertiesSectionLabel, logger);
				softAssert.assertFalse(expertiesSectionLabel.getText().isEmpty());
			} catch (Exception e) {

			}
			softAssert.assertAll();
			List<WebElement> expSubSections = mydriver.findElements(By.xpath(expertiseSubSectionDesc));
			for (WebElement expSubSection : expSubSections) {
				scrollToElement(mydriver, expSubSection, logger);
				hardAssert.assertFalse(expSubSection.getText().isEmpty());
				logger.info("Expertise article => " + expSubSection.getText());
				softAssert.assertFalse(
						mydriver.findElements(By.xpath(expertiseSubSectionTitle)).get(i).getText().isEmpty());
				logger.info("Article's short description => "
						+ mydriver.findElements(By.xpath(expertiseSubSectionDesc)).get(i).getText());
				softAssert.assertAll();
				i++;

			}

		}

	}

	@Test(priority = 4, enabled = true)
	public void expertiseSectionRedirectionCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);

			try {
				scrollToElement(mydriver, mydriver.findElements(By.xpath(expertiseSubSectionLink)).get(0), logger);
			} catch (Exception e) {
				throw new SkipException("Expertise Section Redirection link is not available");
			}
			List<WebElement> expLinks = mydriver.findElements(By.xpath(expertiseSubSectionLink));
			int i = getRandomInteger(expLinks.size() - 1, 0);
			String[] expectedArr = expLinks.get(i).getAttribute("href").split("/");
			String expected = expectedArr[expectedArr.length - 1];
			logger.info("Expected url=> " + expected);
			scrollToElement(mydriver, expLinks.get(i), logger).click();
			pleaseWait(5, logger);
			if (mydriver.getCurrentUrl().contains(".html")) {
				hardAssert.assertTrue(mydriver.getCurrentUrl().split(".html")[0].contains(expected));
			} else {
				hardAssert.assertTrue(mydriver.getCurrentUrl().contains(expected));
			}
		}
	}

	@Test(priority = 5, enabled = true)
	public void resourceSectionRedirectionCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);

			try {
				scrollToElement(mydriver, mydriver.findElements(By.xpath(resourcesAndServicesSubsectionLink)).get(0), logger);
			} catch (Exception e) {
				throw new SkipException("Resource Section Redirection link is not available");
			}
			List<WebElement> resourceElements = mydriver.findElements(By.xpath(resourcesAndServicesSubsectionTitle));
			int i = getRandomInteger(resourceElements.size() - 1, 0);

			logger.info("Resource element => " + resourceElements.get(i).getText());
			String expLink = mydriver.findElements(By.xpath(resourcesAndServicesSubsectionLink)).get(i)
					.getAttribute("href");
			String handle = mydriver.getWindowHandle();
			scrollToElement(mydriver, resourceElements.get(i), logger).click();
			pleaseWait(5, logger);
			assertRedirection(mydriver, logger, getDomainName(cardUrl), expLink, handle );
			
			
			/*if (mydriver.getCurrentUrl().contains(".html")) {
				hardAssert.assertTrue(mydriver.getCurrentUrl().split(".html")[0].contains(expLink));
			} else {
				hardAssert.assertTrue(mydriver.getCurrentUrl().contains(expLink));
			}*/
		}
	}
}
