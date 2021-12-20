package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.switchToNextTab;
import static com.optum.dpm.utils.DPMTestUtils.switchToPreviousTab;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.SocialModule_page;

import core.CustomDataProvider;

public class SocialModule_StepDefinition extends SocialModule_page {
	
	private static final Logger logger = LogManager.getLogger(SocialModule_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"social-module"})
	public void mandatoryFieldsVisibilityCheck(String url) {
		skipNonExistingComponent(url);
//		skipNonExistingComponent(cardUrl);
			
			mydriver.get(url);
			scrollToElement(mydriver, moduleSection, logger);
			getTest().info("Is main section title available: " +verifyElementExists(logger, mainSectionHeader, "Main Title"));
			softAssert.assertTrue(verifyElementExists(logger, mainSectionHeader, "Main Title"));
			softAssert.assertFalse(mainSectionHeader.getAttribute("innerText").isEmpty());
			
			getTest().info("Is main section description available: " +verifyElementExists(logger, mainSectionDescription, "Main Description"));
			softAssert.assertTrue(verifyElementExists(logger, mainSectionDescription, "Main Description"));
			softAssert.assertFalse(mainSectionDescription.getAttribute("innerText").isEmpty());
			
			getTest().info("Is left social card is available: " +verifyElementExists(logger, socialCardLeft, "Social Card Left"));
			softAssert.assertTrue(verifyElementExists(logger, socialCardLeft, "Social Card Left"));
			
			
			getTest().info("Is center social card is vailable: " +verifyElementExists(logger, socialCardCenter, "Social Card Center"));
			softAssert.assertTrue(verifyElementExists(logger, socialCardCenter, "Social Card Center"));
			
			getTest().info("Is right social card is available: " +verifyElementExists(logger, socialCardRight, "Social Card Right"));
			softAssert.assertTrue(verifyElementExists(logger, socialCardRight, "Social Card Right"));
			
			
			softAssert.assertAll();
//			}
	}


	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"social-module"})
	public void mandatorySocialCardFieldsFieldsVisibilityCheck(String url) {
		skipNonExistingComponent(url);
//		skipNonExistingComponent(cardUrl);
			
			mydriver.get(url);
			scrollToElement(mydriver, moduleSection, logger);
			int i = 1; 
			for(WebElement title : mydriver.findElements(By.xpath(socialCardTitles))) {
			
			getTest().info("Is Card '"+i+"' Title is available: " +verifyElementExists(logger, title, "Card '"+i+"' Title"));
			softAssert.assertTrue(verifyElementExists(logger, title, "Card '"+i+"' Title"));
			softAssert.assertFalse(title.getAttribute("innerText").isEmpty());
			i++;
			}
			i = 1;
			for(WebElement image : mydriver.findElements(By.xpath(socialCardImages))) {
				
				getTest().info("Is Card '"+i+"' image is available: " +verifyElementExists(logger, image, "Card '"+i+"' image"));
				softAssert.assertTrue(verifyElementExists(logger, image, "Card '"+i+"' image"));
				softAssert.assertFalse(image.getAttribute("src").isEmpty());
				i++;
				}
			i = 1;
			for(WebElement icon : mydriver.findElements(By.xpath(socialCardIcons))) {
				
				getTest().info("Is Card '"+i+"' icon is available: " +verifyElementExists(logger, icon, "Card '"+i+"' icon"));
				softAssert.assertTrue(verifyElementExists(logger, icon, "Card '"+i+"' icon"));
				i++;
				}
			
			softAssert.assertAll();
			

	}



	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"social-module"})
	public void socialCardDescriptionCheck(String url) {
		skipNonExistingComponent(url);
//		skipNonExistingComponent(cardUrl);
			
			mydriver.get(url);
			scrollToElement(mydriver, moduleSection, logger);
			
			try {
				mydriver.findElements(By.xpath(socialCardContents)).get(0).isDisplayed();
				
			} catch (Exception e) {
				throw new SkipException("Description Fields is not available");
			}
			
			int i = 1;
			for(WebElement content : mydriver.findElements(By.xpath(socialCardContents))) {
				
				getTest().info("Is Card '"+i+"' content is available: " +content.getAttribute("innerText"));
				softAssert.assertTrue(verifyElementExists(logger, content, "Card '"+i+"' content"));
				softAssert.assertFalse(content.getAttribute("innerText").isEmpty());
				i++;
				}
			

		softAssert.assertAll();
	}


	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"social-module"})
	public void socialCardIconCheck(String url) {
		skipNonExistingComponent(url);
//		skipNonExistingComponent(cardUrl);
			
			mydriver.get(url);
			scrollToElement(mydriver, moduleSection, logger);
			
			int i = 1;
			for(WebElement icon : mydriver.findElements(By.xpath(socialCardIcons))) {
				
				getTest().info("Is Card '"+i+"' icon is available: " +verifyElementExists(logger, icon, "Card '"+i+"' icon"));
				softAssert.assertTrue(verifyElementExists(logger, icon, "Card '"+i+"' icon"));
				i++;
				}

	}



	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"social-module"})
	public void mainSectionSocialIconRedirectionCheck(String url) {
		skipNonExistingComponent(url);
//		skipNonExistingComponent(cardUrl);
			
			mydriver.get(url);
			scrollToElement(mydriver, moduleSection, logger);
			List<WebElement> links = mydriver.findElements(By.xpath(mainSocialIconLinks));			
			int i = getRandomInteger(links.size(), 0);
				int j = i+1;
				String expLink = links.get(i).getAttribute("href");
				getTest().info("Main social icon '"+j+"' redirection link: " +links.get(i).getAttribute("href"));
				scrollToElement(mydriver, links.get(i), logger);
				String handle = mydriver.getWindowHandle();
				scrollToElement(mydriver, mainSectionHeader, logger);
				links.get(i).click();
				switchToNextTab(mydriver, logger, handle);
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
				switchToPreviousTab(mydriver, logger, handle);
				

	}



	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"social-module"})
	public void socialCardHyperRedirectionCheck(String url) {
		skipNonExistingComponent(url);
//		skipNonExistingComponent(cardUrl);
			
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
				getTest().info("Social Card hyperlink '"+j+"' redirection link: " +links.get(i).getAttribute("href"));
				scrollToElement(mydriver, links.get(i), logger);
				String handle = mydriver.getWindowHandle();
				scrollToElement(mydriver, mainSectionHeader, logger);
				links.get(i).click();
				switchToNextTab(mydriver, logger, handle);
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
				switchToPreviousTab(mydriver, logger, handle);
				

	}

	@Test(priority = 7, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"social-module"})
	public void socialCardRedirectionCheck(String url) {
		skipNonExistingComponent(url);
//		skipNonExistingComponent(cardUrl);
			
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
				getTest().info("Social Card hyperlink '"+j+"' redirection link: " +links.get(i).getAttribute("href"));
				scrollToElement(mydriver, links.get(i), logger);
				String handle = mydriver.getWindowHandle();
				scrollToElement(mydriver, mainSectionHeader, logger);
				cards.get(i).click();
				switchToNextTab(mydriver, logger, handle);
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
				switchToPreviousTab(mydriver, logger, handle);
				

	}

}
