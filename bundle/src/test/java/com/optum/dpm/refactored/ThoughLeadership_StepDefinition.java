package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ThoughLeadership_page;

import core.CustomDataProvider;

public class ThoughLeadership_StepDefinition extends ThoughLeadership_page {

	private static final Logger logger = LogManager.getLogger(ThoughLeadership_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"thought-leadership"})
	public void defaultElementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			List<WebElement> titles = mydriver.findElements(By.xpath(subsectionTitles));
			List<WebElement> descriptions = mydriver.findElements(By.xpath(subsectionDescription));
			int i = 0;
			for (WebElement title : titles) {
				scrollToElement(mydriver, title, logger);
				hardAssert.assertTrue(verifyElementExists(logger, title, title.getAttribute("innerText")));
				hardAssert.assertTrue(verifyElementExists(logger, descriptions.get(i),
						descriptions.get(i).getAttribute("innerText")));
				i++;
			}
			i=0;
			for (WebElement title : titles) {
				scrollToElement(mydriver, title, logger);
				hardAssert.assertFalse(title.getAttribute("innerText").isEmpty());
				hardAssert.assertFalse(descriptions.get(i).getAttribute("innerText").isEmpty());
				i++;
			}


	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"thought-leadership"})
	public void imageVisiblityCheck(String url) {
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			List<WebElement> images = mydriver.findElements(By.xpath(subsectionWithImages));
			for(WebElement image: images) {
				scrollToElement(mydriver, image, logger);
				hardAssert.assertTrue(verifyElementExists(logger, image, "image field"));
			}
			

	}
	
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"thought-leadership"})
	public void buttonVisibilityAndRedirectionCheck(String url) {
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			List<WebElement> buttons = mydriver.findElements(By.xpath(subsectionButtons));
			int i = getRandomInteger(buttons.size(), 0);
			scrollToElement(mydriver, buttons.get(i), logger);
			hardAssert.assertTrue(verifyElementExists(logger, buttons.get(i), "Thought Leadership Button"));
			hardAssert.assertFalse(buttons.get(i).getAttribute("href").isEmpty());
			String handle = mydriver.getWindowHandle();
			String expUrl = buttons.get(i).getAttribute("href");
			buttons.get(i).click();
			pleaseWait(5, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expUrl, handle);
			

	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"thought-leadership"})
	public void blankButtonLabelCheck(String url) {
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			List<WebElement> buttons = mydriver.findElements(By.xpath(subsectionButtons));
			for(WebElement label: buttons) {
				scrollToElement(mydriver, label, logger);
				hardAssert.assertFalse(label.getAttribute("innerText").isEmpty());
			}

	}
}
