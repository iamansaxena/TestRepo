package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMConfigurationsUtil.Environment;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.getVisibility;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static org.testng.Assert.fail;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ColorBlock_page;

import core.CustomDataProvider;

public class ColorBlock_StepDefinition extends ColorBlock_page {

	private static final Logger logger = LogManager.getLogger(ColorBlock_StepDefinition.class);

	// NOT POSSIBLE TO HANDLE THIS TEST CASE
	// @Test(priority = 1, enabled = true,dataProvider = "data-provider",
	// dataProviderClass = CustomDataProvider.class, parameters = {"color-block"})
	// public void imageAlignmentCheck(String url) {
	// skipNonExistingComponent(cardUrls);
	//
	//
	// int i = 0;
	//
	// 
	// mydriver.get(url);
	//
	// List<WebElement> blocks = mydriver.findElements(By.xpath(blockSection));
	// for (WebElement block : blocks) {
	// int j = i + 1;
	// scrollToElement(mydriver, block);
	// WebElement imageSection = mydriver.findElement(By.xpath(
	// "(//*[@class=\"color-block section\"])[" + j +
	// "]//*[contains(@class,\"section-inner img\")]"));
	// try {
	// String image = imageSection.getAttribute("style");
	// if (!image.isEmpty() || image != null) {
	// mydriver.findElement(By.xpath(""));
	// }
	// } catch (Exception e) {
	//
	// }
	// i++;
	// }
	// }

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"color-block" })
	public void titleLabel(String url) {
		skipNonExistingComponent(url);

		int i = 0;

		
		mydriver.get(url);

		List<WebElement> blocks = mydriver.findElements(By.xpath(blockSection));
		List<WebElement> titles = mydriver.findElements(
				By.xpath("//*[@class=\"color-block section\"]//*[contains(@class,\"section-inner img\")]//h2"));

		for (WebElement block : blocks) {
			scrollToElement(mydriver, block, logger);

			int j = i + 1;
			String titleText;
			String titleTextWithLink;
			try {
				titleText = titles.get(i).getText();

				if (!titleText.isEmpty()) {

					logger.info("Title '" + j + "' => " + titleText);
				} else {
					titleTextWithLink = mydriver.findElement(By.xpath(
							"(//*[@class=\"color-block section\"]//*[contains(@class,\"section-inner img\")]//h2)[" + j
									+ "]/a"))
							.getText();
					if (!titleText.isEmpty()) {

						logger.info("Title '" + j + "' => " + titleText);
					} else {
						fail("Blank Title '" + j + "'");
					}
				}
			} catch (NoSuchElementException e) {

			}

			i++;
			titleText = null;
			titleTextWithLink = null;
		}
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"color-block" })
	public void linkButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		List<WebElement> buttons;
		int i = 0;
		
		mydriver.get(url);
		try {
			mydriver.findElement(By.xpath(linkButton)).isDisplayed();
			buttons = mydriver.findElements(By.xpath(linkButton));
		} catch (Exception f) {
			throw new SkipException("There's no link button present on the banner");
		}
		for (WebElement button : buttons) {
			buttons = mydriver.findElements(By.xpath(linkButton));
			scrollToElement(mydriver, buttons.get(i), logger);
			String hyperlink = buttons.get(i).getAttribute("href");
			int currentTabSize = mydriver.getWindowHandles().size();
			if (getDomainName(mydriver.getCurrentUrl()).equals(getDomainName(hyperlink))) {
				JavascriptExecutor jse = (JavascriptExecutor) mydriver;
				jse.executeScript("arguments[0].click()", buttons.get(i));
				logger.info(" Internal Link [" + hyperlink + "]:  Hyperlink must get open in the same tab");
				hardAssert.assertEquals(mydriver.getWindowHandles().size(), currentTabSize);
				pleaseWait(1, logger);
				mydriver.navigate().back();
			} else if (!getDomainName(mydriver.getCurrentUrl()).equals(getDomainName(hyperlink))) {
				buttons.get(i).click();
				logger.info(" External Link:  Hyperlink must get open in a new tab");
				hardAssert.assertNotEquals(mydriver.getWindowHandles().size(), currentTabSize);
			}
			i++;
		}
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"color-block" })
	public void videoButtonCheck(String url) {
		skipNonExistingComponent(url);

		if (Environment.equalsIgnoreCase("uat")) {
			throw new SkipException("Video specific TC can't be tested on UAT");
		}
		
		mydriver.get(url);
		List<WebElement> buttons;
		List<WebElement> videoModal = mydriver.findElements(By.xpath("//*[@class=\"vjs-poster\"]"));
		pleaseWait(6, logger);
		try {
			mydriver.findElement(By.xpath(videoButton)).isDisplayed();
			scrollToElement(mydriver, mydriver.findElement(By.xpath(videoButton)), logger);
			buttons = mydriver.findElements(By.xpath(videoButton));

		} catch (Exception f) {
			throw new SkipException("There's no video button present on the color-block");
		}

		int i = getRandomInteger(buttons.size(), 0);
		scrollToElement(mydriver, buttons.get(i), logger);
		buttons.get(i).click();
		getVisibility(mydriver, videoModal.get(i), 30);
		mydriver.findElements(By.xpath("(//*[@class=\"modal__close\"])")).get(i).click();

	}

}
