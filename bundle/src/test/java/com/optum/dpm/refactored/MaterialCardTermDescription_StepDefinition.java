package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static org.testng.Assert.fail;

import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.MaterialCardTermDescription_Page;

import core.CustomDataProvider;

public class MaterialCardTermDescription_StepDefinition extends MaterialCardTermDescription_Page {
	private static String currentDomain = "=>";
	private static final Logger logger = LogManager.getLogger(MaterialCardTermDescription_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-term-descption"})
	public void cardElementVisibilityCheck(String mcardUrl) {

		skipNonExistingComponent(mcardUrl);

			
			mydriver.get(mcardUrl);

			List<WebElement> cards = mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mCards));
			int i = 0;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);

				WebElement title = mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mCardTitle)).get(i);
				WebElement description = mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mDescription))
						.get(i);

				focusElement(mydriver, description);
				focusElement(mydriver, title);

				if (!description.getText().isEmpty() && !title.getText().isEmpty()) {
					logger.info(" 'Card " + i + " Title: '"
							+ mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mCardTitle)).get(i)
									.getText()
							+ "'\n\t Description ==> "
							+ mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mDescription)).get(i)
									.getText());

				} else {
					fail("Material card found with blank Title/Description");
				}
				i++;

			}


	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-term-descption"})
	public void buttonVisibilityCheck(String mcardUrl) {
		skipNonExistingComponent(mcardUrl);
		List<WebElement> materialcards = mydriver
				.findElements(By.xpath("//*[@class=\"material-card-term-descption section\"]"));
		int i = 0;
		for (WebElement card : materialcards) {
			scrollToElement(mydriver, card, logger);
			hardAssert.assertFalse(mydriver.findElements(By.xpath(mButton)).get(i).getText().isEmpty());
			
		}
	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-term-descption"})
	public void learnMoreButtonRedirection(String mcardUrl) {
		skipNonExistingComponent(mcardUrl);

			int i = 0;
			String btnLink = null;
			List<WebElement> buttons;
			
			mydriver.get(mcardUrl);
			currentDomain = currentDomain + "[" + mcardUrl + "]";
			List<WebElement> materialcards = mydriver
					.findElements(By.xpath("//*[@class=\"material-card-term-descption section\"]"));
			i = getRandomInteger(materialcards.size(), 0);
			materialcards.get(i);
			buttons = mydriver.findElements(By.xpath(MaterialCardTermDescription_Page.mButton));
			try {
				while (btnLink == null || btnLink.isEmpty()) {
					btnLink = buttons.get(i).getAttribute("href");
					i = getRandomInteger(buttons.size(), 0);
				}
				scrollToElement(mydriver, buttons.get(i), logger);
			} catch (Exception e) {

			}

			if (btnLink.isEmpty()) {
				fail("Button Hyperlink is empty");
				logger.error("Button Hyperlink is empty");
			}

			if (btnLink != null) {
				scrollToElement(mydriver, buttons.get(i), logger);
				logger.info("Material Card button link: " + btnLink);
			}


	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-term-descption"})
	public void buttonHyperLinkRedirection(String mcardUrl) {
		skipNonExistingComponent(mcardUrl);

			
			mydriver.get(mcardUrl);
			currentDomain = currentDomain + "[" + mcardUrl + "]";
			// List<WebElement> desc = mydriver.findElements(By.xpath(descriptions));
			List<WebElement> cards = mydriver
					.findElements(By.xpath("//*[@class=\"material-card-term-descption section\"]"));

			String currentPage = mydriver.getCurrentUrl();
			String domain = getDomainName(currentPage);
			String currentHandle = mydriver.getWindowHandle();

			int i = getRandomInteger(cards.size(), 0);

			scrollToElement(mydriver, cards.get(i), logger);
			logger.info("Checking Material Term Desc card on : " + currentPage);
			String hyperlink = "";
			try {
				hyperlink = cards.get(i).getAttribute("href");
			} catch (Exception e) {
				hyperlink = null;
			}
			if (hyperlink != null) {
				cards.get(i).click();
				Set<String> allHandles = mydriver.getWindowHandles();
				String hyperlinkDomain = getDomainName(hyperlink);
				if (!hyperlinkDomain.equals(domain)) {
					allHandles.remove(currentHandle);
					mydriver.switchTo().window(allHandles.iterator().next());
					hardAssert.assertNotEquals(mydriver.getWindowHandle(), currentHandle);
					
					mydriver.get(currentPage);
				} else if (hyperlinkDomain.equals(domain)) {
					hardAssert.assertEquals(mydriver.getWindowHandle(), currentHandle);

				}
			}


	}
}
