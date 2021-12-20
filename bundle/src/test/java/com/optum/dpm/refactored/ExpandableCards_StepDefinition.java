package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;
import static org.testng.Assert.fail;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ExpandableCard_page;

import core.CustomDataProvider;

public class ExpandableCards_StepDefinition extends ExpandableCard_page {
	
	private static final Logger logger = LogManager.getLogger(ExpandableCards_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"expanding-card-box-v2" })
	public void elementVisiblityCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
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
		}
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"expanding-card-box-v2" })
	public void oneCardExpandedAtOnceCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

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

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"expanding-card-box-v2" })
	public void bottomExpandButtonFuntionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

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

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"expanding-card-box-v2" })
	public void cardExpandFuntionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

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

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"expanding-card-box-v2" })
	public void expandedContentCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

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

	@Test(priority = 6, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"expanding-card-box-v2" })
	public void cardContractionViaTopButtonCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

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

	@Test(priority = 7, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"expanding-card-box-v2" })
	public void cardContractionViaBottomButtonCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

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

	@Test(priority = 8, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"expanding-card-box-v2" })
	public void cardWithLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

		String currentTab;
		List<WebElement> nonExps;
		String linkUrl;
		int i = 0;
		try {
			nonExps = mydriver.findElements(By.xpath(openBottomButtonsWithLinks));

			i = getRandomInteger(nonExps.size(), 0);
			scrollToElement(mydriver, nonExps.get(i), logger);
			currentTab = mydriver.getWindowHandle();
			linkUrl = nonExps.get(i).getAttribute("href");
			nonExps.get(i).click();
		} catch (Exception e) {
			throw new SkipException("There is no card with link available");
		}
		assertRedirection(mydriver, logger, getDomainName(url), linkUrl, currentTab);
	}

}