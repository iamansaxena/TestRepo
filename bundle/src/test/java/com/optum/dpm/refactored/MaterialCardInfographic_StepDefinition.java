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
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.MaterialCardInfographic_page;

import core.CustomDataProvider;

public class MaterialCardInfographic_StepDefinition extends MaterialCardInfographic_page {

	private static final Logger logger = LogManager.getLogger(MaterialCardInfographic_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-info-graphic"})
	public void elementVisibilityCheck(String cardUrl) {

		skipNonExistingComponent(cardUrl);

			 mydriver.get(cardUrl);

			List<WebElement> cards = mydriver.findElements(By.xpath(MaterialCardInfographic_page.cards));
			int i = 0;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				WebElement title = mydriver.findElements(By.xpath(MaterialCardInfographic_page.titles)).get(i);
				WebElement description = mydriver.findElements(By.xpath(MaterialCardInfographic_page.descriptions))
						.get(i);
				focusElement(mydriver, description);
				focusElement(mydriver, title);
				if (!description.getText().isEmpty() && !title.getText().isEmpty()) {
					logger.info(
							"Card -1 Title: '"
									+ mydriver.findElements(By.xpath(MaterialCardInfographic_page.titles)).get(i)
											.getText()
									+ "'\n\t Description ==> "
									+ mydriver.findElements(By.xpath(MaterialCardInfographic_page.descriptions)).get(i)
											.getText());
				} else {
					fail("Material Infographic card found with blank Title/Description");
				}
				i++;
			}


	}

//	Disabling this as we are not covering the content 
	/*@Test(priority = 2, enabled = false)
	public void brokenImageCheck() {
		skipNonExistingComponent(cardUrls);
		for (String cardUrl : cardUrls) {
			customLogsPool.get().add(cardUrl); mydriver.get(cardUrl);
			List<WebElement> images = mydriver.findElements(By.xpath(MaterialCardInfographic_page.images));
			int i = getRandomInteger(images.size(), 0);
			scrollToElement(mydriver, images.get(i));
			focusElement(mydriver, images.get(i));
			String imageLink = images.get(i).getAttribute("src");
			logger.info("Card Image link ==> " + imageLink);
			customLogsPool.get().add(cardUrl); mydriver.get(imageLink);
			if (mydriver.getTitle().contains("404")) {
				fail("Broken Image Found");
			}
		}
	}*/

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-info-graphic"})
	public void redirectionLinkUnavailabilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

			 mydriver.get(cardUrl);
			List<WebElement> cards = mydriver.findElements(By.xpath(MaterialCardInfographic_page.cards));
			int i = 1;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				try {
					mydriver.findElement(
							By.xpath("//*[@class=\"material-card-info-graphic section\"][" + i + "]//*[@href]"));
				} catch (Exception e) {
					try {
						focusElement(mydriver,
								mydriver.findElement(By.xpath("//*[@class=\"material-card-info-graphic section\"][" + i
										+ "]//*[@class=\"button button--reverse\"]")));
						fail("Card Button is available even without any hyperlink");
					} catch (Exception f) {

					}

				}
				i++;
			}

	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-info-graphic"})
	public void redirectionButtonAvailabilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

			 mydriver.get(cardUrl);
			List<WebElement> cards = mydriver.findElements(By.xpath(MaterialCardInfographic_page.cards));
			int i = 1;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				WebElement cardLink = mydriver.findElement(By.xpath(
						"(//*[@class=\"material-card-info-graphic section\"]//*[@class=\"button button--reverse\"])["
								+ i + "]"));
				if (cardLink.getAttribute("href").isEmpty()) {
					fail("Card Hyperlink is Empty");
				}
			}

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-info-graphic"})
	public void cardLinkRedirectionCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

			 mydriver.get(cardUrl);
			List<WebElement> links;

			try {
				links = mydriver.findElements(By.xpath("//*[@class=\"material-card-info-graphic section\"]//*[@href]"));

			} catch (Exception e) {
				throw new SkipException("No Card Found with link");

			}
			int i = getRandomInteger(links.size(), 0);
			scrollToElement(mydriver, links.get(i), logger);
			String domain = getDomainName(mydriver.getCurrentUrl());
			String myTab = mydriver.getWindowHandle();
			String linkUrl = links.get(i).getAttribute("href");
			links.get(i).click();
			Set<String> allTabs = mydriver.getWindowHandles();

			if (getDomainName(linkUrl).equals(domain)) {
				hardAssert.assertEquals(mydriver.getWindowHandle(), myTab);
				logger.info("Internal Link opened in the same tab => " + linkUrl);
				mydriver.get(cardUrl);
			} else {
				allTabs.remove(myTab);
				mydriver.switchTo().window(allTabs.iterator().next());
				hardAssert.assertEquals(mydriver.getCurrentUrl(), linkUrl);
				logger.info("External Links opened in a new tab => " + linkUrl);
				mydriver.switchTo().window(myTab);

			}
		}
//	}
}
// }
