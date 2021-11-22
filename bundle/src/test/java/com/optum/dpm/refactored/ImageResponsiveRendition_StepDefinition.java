package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ImageResponsiveRendition_page;

import core.CustomDataProvider;

public class ImageResponsiveRendition_StepDefinition extends ImageResponsiveRendition_page {
	
	private static final Logger logger = LogManager.getLogger(ImageResponsiveRendition_StepDefinition.class);

	// Priyanka Review Note: This TC is not required as the images are not a mandate
	// and will be taken automatically from DAM if not proided
	// @Test(priority = 1, enabled = true)
	// public void blankImageCheck() {
	// skipNonExistingComponent(urls);
	// for (String url : urls) {
	// 
	// mydriver.get(url);
	// try {
	// mydriver.findElement(By.xpath(images)).isDisplayed();
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new SkipException("There's no image authored");
	// }
	// for (WebElement image : mydriver.findElements(By.xpath(images))) {
	// hardAssert.assertFalse(image.getAttribute("srcset").isEmpty());
	// }

	// }
	// }

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"image-responsive-rendition" })
	public void linkRedirectionCheck(String url) {
		skipNonExistingComponent(url);
		
		mydriver.get(url);
		String expUrl = null;
		String handle = mydriver.getWindowHandle();
		try {
			expUrl = link.getAttribute("href");
		} catch (Exception e) {
			throw new SkipException("There's no redirect link authored");
		}
		link.click();
		assertRedirection(mydriver, logger, getDomainName(url), expUrl, handle);
	}
}
