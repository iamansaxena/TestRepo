package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.PullQuote_page;

import core.CustomDataProvider;

public class PullQuote_StepDefinition extends PullQuote_page {

	private static final Logger logger = LogManager.getLogger(PullQuote_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pull-quote"})
	public void pullQuoteBlockCheck(String url) {
		skipNonExistingComponent(url);
		
			 mydriver.get(url);
			scrollToElement(mydriver, pullQuoteSection, logger);
			focusElement(mydriver, blockQuote);
			focusElement(mydriver, blockQuoteCitation);
			hardAssert.assertTrue(verifyElementExists(logger, blockQuote,
					"Quote ::> " + blockQuote.getText()));
			hardAssert.assertFalse(blockQuote.getText().isEmpty());
			softAssert.assertTrue(verifyElementExists(logger, blockQuoteCitation,
					"Caption Citation of Quote ::> " + blockQuoteCitation.getText()));
			softAssert.assertAll();

	}
	
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pull-quote"})
	public void displayPullQuoteButtonLinkText(String url) {
		skipNonExistingComponent(url);
		
			 mydriver.get(url);
			try {
				blockQuotelink.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("link Text is not available");
			}
			hardAssert.assertFalse(blockQuotelink.getText().isEmpty());
			logger.info("Text displayed inside Button Link => "+blockQuotelink.getText());
	
		}

	
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"pull-quote"})
	public void quoteLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);
		
			 mydriver.get(url);
			
			try {
				blockQuotelink.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("Hyperlink is not available");
			}
				String quoteLink = blockQuotelink.getAttribute("href");
				String handle = mydriver.getWindowHandle();
				blockQuotelink.click();
				assertRedirection(mydriver, logger, getDomainName(url), quoteLink,handle);

	}

}
