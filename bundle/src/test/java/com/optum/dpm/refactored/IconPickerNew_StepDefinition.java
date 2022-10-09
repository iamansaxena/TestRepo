package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.IconPickerNew_page;

import core.CustomDataProvider;

public class IconPickerNew_StepDefinition extends IconPickerNew_page {
	
	private static final Logger logger = LogManager.getLogger(IconPickerNew_StepDefinition.class);

	/*
	 * @Test(priority = 2, enabled = true,dataProvider = "data-provider",
	 * dataProviderClass = CustomDataProvider.class, parameters =
	 * {"icon-picker-v2"}) public void blankHeaderCheck(String url) {
	 * HashMap<String, Boolean> assertConditionMap =
	 * skipNonExistingComponent(cardUrls);
	 * 
	 * mydriver.get(cardUrl); String expURL =
	 * mydriver.findElement(By.xpath(iconField)).getAttribute("href");
	 * assertRedirection(mydriver, logger, getDomainName(cardUrl), expURL); }
	 */
	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"icon-picker-v2" })
	public void iconPickerVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);

		List<WebElement> iconPicker = mydriver.findElements(By.xpath(IconPickerNew_page.iconField));

		scrollToElement(mydriver, iconPicker.get(0), logger);

		// WebElement iconPicker =
		// mydriver.findElements(By.xpath(IconPickerNew_page.iconField));
		hardAssert.assertTrue(verifyElementExists(logger, iconPicker.get(0), "Icon Picker Icon"));

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"icon-picker-v2" })
	public void linkTextCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);

		List<WebElement> iconPickerLink = mydriver.findElements(By.xpath(IconPickerNew_page.iconWithLink));
		int i = getRandomInteger(iconPickerLink.size(), 0);
		try {
			scrollToElement(mydriver, iconPickerLink.get(i), logger);
		} catch (Exception e) {
			throw new SkipException("Icons with links are not present");
		}
		hardAssert.assertFalse(iconPickerLink.get(i).getAttribute("href").isEmpty());
		logger.info("Redirection Link ==> " + iconPickerLink.get(i).getAttribute("href"));
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"icon-picker-v2" })
	public void linkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);

		List<WebElement> iconPickerLink = mydriver.findElements(By.xpath(IconPickerNew_page.iconWithLink));
		String handle = mydriver.getWindowHandle();
		int i = getRandomInteger(iconPickerLink.size(), 0);
		try {
			scrollToElement(mydriver, iconPickerLink.get(i), logger);
		} catch (Exception e) {
			throw new SkipException("Icons with links are not present");
		}
		String expLink = iconPickerLink.get(i).getAttribute("href");

		iconPickerLink.get(i).click();

		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
	}

}
