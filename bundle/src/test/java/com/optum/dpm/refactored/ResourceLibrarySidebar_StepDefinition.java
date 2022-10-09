package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ResourceLibrarySidebar_page;

import core.CustomDataProvider;

public class ResourceLibrarySidebar_StepDefinition extends ResourceLibrarySidebar_page {

	private static final Logger logger = LogManager.getLogger(ResourceLibrarySidebar_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"right_aside"})
	public void newsLetterLabelCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);


			
			mydriver.get(cardUrl);
			try {
				scrollToElement(mydriver, newsLetterLabel, logger);
			} catch (Exception e) {
				throw new SkipException("No news Letter Label field is available");
			}
			hardAssert.assertFalse(newsLetterLabel.getText().isEmpty());
			logger.info("New Letter Label => " + newsLetterLabel.getText());

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"right_aside"})
	public void newsLetterRedirectionCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);


			
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

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"right_aside"})
	public void expertiseSectionDefaultElementsCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);


			
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

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"right_aside"})
	public void expertiseSectionRedirectionCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);


			
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

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"right_aside"})
	public void resourceSectionRedirectionCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);


			
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
