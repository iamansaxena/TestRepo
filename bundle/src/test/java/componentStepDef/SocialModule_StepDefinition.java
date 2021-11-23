package componentStepDef;

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

import compontentPages.SocialModule_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class SocialModule_StepDefinition extends SocialModule_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(SocialModule_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(SocialModule_StepDefinition.class.getName());
		new SocialModule_page();
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		if (fetchUrl("social-module") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				urls.add("http://apsrs5642:8080/content/medexpressautomationdirectory/social-module.html");

			} else if (Environment.equalsIgnoreCase("test")) {
				urls.add("http://apvrt31468:4503/content/medexpressautomationdirectory/social-module.html");
			}

		} else {
			String[] scannedUrls = fetchUrl("social-module").split(",");
			for (String link : scannedUrls) {
				urls.add(link);
			}
		}

		ExtentTestManager.startTest(SocialModule_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("Social Module [MEDEX]", author, currentDomain,
				SocialModule_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(SocialModule_StepDefinition.class);
		logger.info("Urls for '" + SocialModule_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(SocialModule_StepDefinition.class.getName(), currentDomain);

		driverMap.put(SocialModule_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + SocialModule_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
	}

	@Test(priority = 1, enabled = true)
	public void mandatoryFieldsVisibilityCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, moduleSection, logger);
			customTestLogs.get().add("Is main section title available: " +verifyElementExists(logger, mainSectionHeader, "Main Title"));
			softAssert.assertTrue(verifyElementExists(logger, mainSectionHeader, "Main Title"));
			softAssert.assertFalse(mainSectionHeader.getAttribute("innerText").isEmpty());
			
			customTestLogs.get().add("Is main section description available: " +verifyElementExists(logger, mainSectionDescription, "Main Description"));
			softAssert.assertTrue(verifyElementExists(logger, mainSectionDescription, "Main Description"));
			softAssert.assertFalse(mainSectionDescription.getAttribute("innerText").isEmpty());
			
			customTestLogs.get().add("Is left social card is available: " +verifyElementExists(logger, socialCardLeft, "Social Card Left"));
			softAssert.assertTrue(verifyElementExists(logger, socialCardLeft, "Social Card Left"));
			
			
			customTestLogs.get().add("Is center social card is vailable: " +verifyElementExists(logger, socialCardCenter, "Social Card Center"));
			softAssert.assertTrue(verifyElementExists(logger, socialCardCenter, "Social Card Center"));
			
			customTestLogs.get().add("Is right social card is available: " +verifyElementExists(logger, socialCardRight, "Social Card Right"));
			softAssert.assertTrue(verifyElementExists(logger, socialCardRight, "Social Card Right"));
			
			
			softAssert.assertAll();
			}
	}


	@Test(priority = 2, enabled = true)
	public void mandatorySocialCardFieldsFieldsVisibilityCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, moduleSection, logger);
			int i = 1; 
			for(WebElement title : mydriver.findElements(By.xpath(socialCardTitles))) {
			
			customTestLogs.get().add("Is Card '"+i+"' Title is available: " +verifyElementExists(logger, title, "Card '"+i+"' Title"));
			softAssert.assertTrue(verifyElementExists(logger, title, "Card '"+i+"' Title"));
			softAssert.assertFalse(title.getAttribute("innerText").isEmpty());
			i++;
			}
			i = 1;
			for(WebElement image : mydriver.findElements(By.xpath(socialCardImages))) {
				
				customTestLogs.get().add("Is Card '"+i+"' image is available: " +verifyElementExists(logger, image, "Card '"+i+"' image"));
				softAssert.assertTrue(verifyElementExists(logger, image, "Card '"+i+"' image"));
				softAssert.assertFalse(image.getAttribute("src").isEmpty());
				i++;
				}
			i = 1;
			for(WebElement icon : mydriver.findElements(By.xpath(socialCardIcons))) {
				
				customTestLogs.get().add("Is Card '"+i+"' icon is available: " +verifyElementExists(logger, icon, "Card '"+i+"' icon"));
				softAssert.assertTrue(verifyElementExists(logger, icon, "Card '"+i+"' icon"));
				i++;
				}
			
			softAssert.assertAll();
			
		}
	}



	@Test(priority = 3, enabled = true)
	public void socialCardDescriptionCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, moduleSection, logger);
			
			try {
				mydriver.findElements(By.xpath(socialCardContents)).get(0).isDisplayed();
				
			} catch (Exception e) {
				throw new SkipException("Description Fields is not available");
			}
			
			int i = 1;
			for(WebElement content : mydriver.findElements(By.xpath(socialCardContents))) {
				
				customTestLogs.get().add("Is Card '"+i+"' content is available: " +content.getAttribute("innerText"));
				softAssert.assertTrue(verifyElementExists(logger, content, "Card '"+i+"' content"));
				softAssert.assertFalse(content.getAttribute("innerText").isEmpty());
				i++;
				}
			
		}
		softAssert.assertAll();
	}


	@Test(priority = 4, enabled = true)
	public void socialCardIconCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, moduleSection, logger);
			
			int i = 1;
			for(WebElement icon : mydriver.findElements(By.xpath(socialCardIcons))) {
				
				customTestLogs.get().add("Is Card '"+i+"' icon is available: " +verifyElementExists(logger, icon, "Card '"+i+"' icon"));
				softAssert.assertTrue(verifyElementExists(logger, icon, "Card '"+i+"' icon"));
				i++;
				}
		}
	}



	@Test(priority = 5, enabled = true)
	public void mainSectionSocialIconRedirectionCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, moduleSection, logger);
			List<WebElement> links = mydriver.findElements(By.xpath(mainSocialIconLinks));			
			int i = getRandomInteger(links.size(), 0);
				int j = i+1;
				String expLink = links.get(i).getAttribute("href");
				customTestLogs.get().add("Main social icon '"+j+"' redirection link: " +links.get(i).getAttribute("href"));
				scrollToElement(mydriver, links.get(i), logger);
				String handle = mydriver.getWindowHandle();
				scrollToElement(mydriver, mainSectionHeader, logger);
				links.get(i).click();
				switchToNextTab(mydriver, logger, handle);
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
				switchToPreviousTab(mydriver, logger);
				
		}
	}



	@Test(priority = 6, enabled = true)
	public void socialCardHyperRedirectionCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, moduleSection, logger);
			try {
				mydriver.findElement(By.xpath(socialCardLinks)).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's no card with link available");
			}
			List<WebElement> links = mydriver.findElements(By.xpath(socialCardLinks));			
			int i = getRandomInteger(links.size(), 0);
				int j = i+1;
				String expLink = links.get(i).getAttribute("href");
				customTestLogs.get().add("Social Card hyperlink '"+j+"' redirection link: " +links.get(i).getAttribute("href"));
				scrollToElement(mydriver, links.get(i), logger);
				String handle = mydriver.getWindowHandle();
				scrollToElement(mydriver, mainSectionHeader, logger);
				links.get(i).click();
				switchToNextTab(mydriver, logger, handle);
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
				switchToPreviousTab(mydriver, logger);
				
		}
	}

	@Test(priority = 7, enabled = true)
	public void socialCardRedirectionCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			int j = 1; 
			scrollToElement(mydriver, moduleSection, logger);
			try {
				mydriver.findElement(By.xpath(socialCards)).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's no card with link available");
			}
			List<WebElement> links = mydriver.findElements(By.xpath(socialCardLinks));
			List<WebElement> cards = mydriver.findElements(By.xpath(socialCards));			
			int i = getRandomInteger(links.size(), 0);
				j= i+j;
				String expLink = links.get(i).getAttribute("href");
				customTestLogs.get().add("Social Card hyperlink '"+j+"' redirection link: " +links.get(i).getAttribute("href"));
				scrollToElement(mydriver, links.get(i), logger);
				String handle = mydriver.getWindowHandle();
				scrollToElement(mydriver, mainSectionHeader, logger);
				cards.get(i).click();
				switchToNextTab(mydriver, logger, handle);
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
				switchToPreviousTab(mydriver, logger);
				
		}
	}

}
