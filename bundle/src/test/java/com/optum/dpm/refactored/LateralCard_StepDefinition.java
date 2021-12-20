package com.optum.dpm.refactored;

import org.apache.logging.log4j.LogManager;
import static com.optum.dpm.utils.DPMTestUtils.*;
import org.apache.logging.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.LateralCard_page;

import core.CustomDataProvider;
public class LateralCard_StepDefinition extends LateralCard_page {
	private static final Logger logger = LogManager.getLogger(LateralCard_StepDefinition.class);
	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"dpl-lateral-card" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, cardSection, logger);
		logger.info("Verify Section Heading is visible: " + cardSectionTitle.isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, cardSectionTitle, "Card Section Title"));
		logger.info("Verify Section Heading text: " + cardSectionTitle.getText());
		softAssert.assertFalse(cardSectionTitle.getText().isEmpty());
		logger.info("Verify Card Image feld is not blank: " + cardItemImage.getAttribute("src"));
		softAssert.assertFalse(cardItemImage.getAttribute("src").isEmpty());

		logger.info("Verify card item heading is visible: " + cardItemTitle.isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, cardItemTitle, "Card Item Title"));
		logger.info("Verify card item heading text: " + cardItemTitle.getText());
		softAssert.assertFalse(cardItemTitle.getText().isEmpty());

		logger.info("Verify card item body is visible: " + cardItemBody.isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, cardItemBody, "Card Item body"));
		logger.info("Verify card item body text: " + cardItemBody.getText());
		softAssert.assertFalse(cardItemBody.getText().isEmpty());

		logger.info("Verify cta link is not blank: " + cardItemButton.getAttribute("href"));
		softAssert.assertTrue(verifyElementExists(logger, cardItemButton, "Card Item button link"));
		softAssert.assertAll();

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"dpl-lateral-card" })
	public void redirectionCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, cardSection, logger);
		hardAssert.assertTrue(verifyElementExists(logger, cardItemButton, "CTA Button"));
		String handle = mydriver.getWindowHandle();
		String expLink = cardItemButton.getAttribute("href");
		logger.info("Verify cta link is not blank: " + expLink);
		cardItemButton.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"dpl-lateral-card" })
	public void featuredTagCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, cardSection, logger);
		if (ifFeaturedTagPresent()) {
			hardAssert.assertTrue(verifyElementExists(logger, cardItemBadge, "Featured Badge"));
		} else
			throw new SkipException("No Featured Badge Authored");
	}
}
