package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.SiteMap_page;

import core.CustomDataProvider;

public class SiteMap_StepDefinition extends SiteMap_page {
	
	private static final Logger logger = LogManager.getLogger(SiteMap_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"sitemap"})
	public void textFiledsVisibilityAndEmptyFieldCheck(String url) {
		skipNonExistingComponent(url);
		

			
			mydriver.get(url);
			scrollToElement(mydriver, siteMapSection, logger);
			try {
				mydriver.findElement(By.xpath(links)).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's no link under SiteMap Component");
			}
			List<WebElement> hyperlinks = mydriver.findElements(By.xpath(links));
			int i = getRandomInteger(hyperlinks.size(), 0);
			String expLink = hyperlinks.get(i).getAttribute("href");
			String handle = mydriver.getWindowHandle();
			hyperlinks.get(i).click();
			pleaseWait(5, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
	}
}
