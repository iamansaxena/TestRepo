package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.MaterialCardFade_page;

import core.CustomDataProvider;

public class MaterialCardFade_StepDefinition extends MaterialCardFade_page {
	
	private static final Logger logger = LogManager.getLogger(MaterialCardFade_StepDefinition.class);
	private static String currentDomain = "=>";

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-fade"})
	public void elementVisibilityCheck(String matUrl) {
		skipNonExistingComponent(matUrl);

			 mydriver.get(matUrl);

			currentDomain = currentDomain + "[" + matUrl + "]";
			List<WebElement> cards = mydriver.findElements(By.xpath("//*[@class=\"mcard mcard__fade\"]"));
			int i = 0;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				hardAssert.assertFalse(mydriver.findElements(By.xpath(titles)).get(i).getText().isEmpty());
				hardAssert.assertFalse(mydriver.findElements(By.xpath(buttons)).get(i).getText().isEmpty());

			}

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-fade"})
	public void additionalFieldsCheck(String matUrl) {
		skipNonExistingComponent(matUrl);

			 mydriver.get(matUrl);
			currentDomain = currentDomain + "[" + matUrl + "]";
			List<WebElement> desc = mydriver.findElements(By.xpath(descriptions));
			for (WebElement des : desc) {
				scrollToElement(mydriver, des, logger);
//				String s = des.getText();
				hardAssert.assertFalse(des.getText().isEmpty());
			}

	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"material-card-fade"})
	public void hyperlinkRedirectionCheck(String matUrl) {
		skipNonExistingComponent(matUrl);

			 mydriver.get(matUrl);
			currentDomain = currentDomain + "[" + matUrl + "]";
//			List<WebElement> desc = mydriver.findElements(By.xpath(descriptions));
			List<WebElement> cards = mydriver.findElements(By.xpath("(//*[@class=\"mcard mcard__fade\"])/a"));

			String currentPage = mydriver.getCurrentUrl();
			String domain = getDomainName(currentPage);
			String currentHandle = mydriver.getWindowHandle();

			int i = getRandomInteger(cards.size(), 0);

			scrollToElement(mydriver, cards.get(i), logger);
			logger.info("Checking Material-Fade card on : " + currentPage);
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
