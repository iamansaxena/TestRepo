package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.AdaptiveImage_page;

import core.CustomDataProvider;

public class AdaptiveImage_StepDefinition extends AdaptiveImage_page {
	
	private static final Logger logger = LogManager.getLogger(AdaptiveImage_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"adaptiveimage"})
	public void adaptiveImageFieldsCheck(String url) {
		skipNonExistingComponent(url);
			
			mydriver.get(url);
			List<WebElement> images = mydriver.findElements(By.xpath(adaptiveImages));
			hardAssert.assertEquals(images.size(), 6);
			for (WebElement e : images) {
				hardAssert.assertFalse(e.getAttribute("data-src").isEmpty());
			}
		}
}
