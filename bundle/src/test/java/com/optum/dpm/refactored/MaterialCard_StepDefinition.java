package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.jsWaitForPageToLoad;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static org.testng.Assert.fail;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.MaterialCard_page;

import core.CustomDataProvider;

public class MaterialCard_StepDefinition extends MaterialCard_page {
	
	private static String currentDomain = "=>";
	private static final Logger logger = LogManager.getLogger(LocalMessage_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card section"})
	public void elementVisibilityCheck(String matUrl) {
		skipNonExistingComponent(matUrl);
			 mydriver.get(matUrl);
			currentDomain = currentDomain + "[" + matUrl + "]";
			List<WebElement> cards = mydriver
					.findElements(By.xpath("//*[@class=\"material-card section mcard--handled cq-Editable-dom\"]"));
			int i = 0;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				hardAssert.assertFalse(mydriver.findElements(By.xpath(titles)).get(i).getText().isEmpty());
				hardAssert.assertFalse(mydriver.findElements(By.xpath(buttons)).get(i).getText().isEmpty());

			}
	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card section"})
	public void additionalFieldsVisibilityCheck(String matUrl) {
		skipNonExistingComponent(matUrl);
		
			 mydriver.get(matUrl);
			currentDomain = currentDomain + "[" + matUrl + "]";
			List<WebElement> cards = mydriver
					.findElements(By.xpath("//*[@class=\"material-card section mcard--handled cq-Editable-dom\"]"));
			List<WebElement> subtitles = null;
			List<WebElement> descriptions = null;

			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				try {
					subtitles = mydriver.findElements(By.xpath(subTitles));

				} catch (Exception e) {
					logger.error("subtitles are not available");
					fail("subtitles are not available");
				}
				for (WebElement subtitle : subtitles) {
					scrollToElement(mydriver, subtitle, logger);
					focusElement(mydriver, subtitle);
					hardAssert.assertFalse(subtitle.getText().isEmpty());
				}
				try {
					descriptions = mydriver.findElements(By.xpath(MaterialCard_page.descriptions));

				} catch (Exception e) {
					logger.error("Descriptions are not available");
					fail("Descriptions are not available");
				}
				for (WebElement description : descriptions) {
					scrollToElement(mydriver, description, logger);
					focusElement(mydriver, description);
					hardAssert.assertFalse(description.getText().isEmpty());
				}

			}
		
	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card section"})
	public void buttonRedirectionCheck(String matUrl) {
		skipNonExistingComponent(matUrl);
		
			int i = 0;
			 mydriver.get(matUrl);
			currentDomain = currentDomain + "[" + matUrl + "]";
			List<WebElement> cards = mydriver.findElements(By.xpath("//*[contains(@class,\"material-card section mcard\") ]/a[@href]"));
			i = getRandomInteger(cards.size(), 0);
			scrollToElement(mydriver, cards.get(i), logger);
			String expLink = cards.get(i).getAttribute("href");
//			clickWithJS(cards.get(i), mydriver);
//			getActions(mydriver).click(cards.get(i)).perform();
			String handle = mydriver.getWindowHandle();
			cards.get(i).click();
			pleaseWait(4, logger);
			jsWaitForPageToLoad(10,mydriver, logger);
			assertRedirection(mydriver, logger, getDomainName(matUrl), expLink,handle);
			
		
	}

}
