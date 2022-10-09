package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMConfigurationsUtil.browserName;
import static com.optum.dpm.utils.DPMConfigurationsUtil.localBrowser;
import static com.optum.dpm.utils.DPMConfigurationsUtil.remoteBrowser;
import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.moveMouseOnToElement;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElementWithoutWait;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;
import static org.testng.Assert.fail;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.Video_page;

import core.CustomDataProvider;
import runner.Retry;

public class Video_StepDefinition extends Video_page {

	private static final Logger logger = LogManager.getLogger(Video_StepDefinition.class);

	@Test(priority = 1, enabled = true, retryAnalyzer=Retry.class,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"videoComponent"})
	public void playPauseBigButtonControls(String url) {
		skipNonExistingComponent(url);

			 
			mydriver.get(url);
			
			scrollToElement(mydriver, videoContainer, logger);
			bigPlayButton.click();
			pleaseWait(1, logger);
			videoContainer.click();
			videoContainer.click();
			hardAssert.assertTrue(verifyElementExists(logger, footerPauseButton, "Video Playing"));
			videoContainer.click();
			videoContainer.click();
			videoContainer.click();
			pleaseWait(4, logger);
			hardAssert.assertTrue(verifyElementExists(logger, footerPlayButton, "Video Paused"));

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"videoComponent"})
	public void playPauseFooterControls(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			scrollToElement(mydriver, videoContainer, logger);
			bigPlayButton.click();
			pleaseWait(4, logger);
			moveMouseOnToElement(mydriver, footerPauseButton);
			pleaseWait(4, logger);
			footerPauseButton.click();
			pleaseWait(2, logger);
			moveMouseOnToElement(mydriver, footerPlayButton);
			pleaseWait(5, logger);
			footerPlayButton.click();
			scrollToElement(mydriver, videoContainer, logger);
			pleaseWait(2, logger);
			videoContainer.click();
			videoContainer.click();
			hardAssert.assertTrue(videoContainerStatus.getAttribute("class").contains("vjs-playing"));
			
			try {
			footerPauseButton.click();
			}catch (NoSuchElementException e) {
				logger.info("video already paused");
			}
//			videoContainer.click();
//			videoContainer.click();
			pleaseWait(2, logger);
			hardAssert.assertTrue(videoContainerStatus.getAttribute("class").contains("vjs-paused"));
			

	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"videoComponent"})
	public void captionFileAndSubTitlesFunctionality(String url) {
		skipNonExistingComponent(url);
		if(browserName.toLowerCase().equalsIgnoreCase("firefox")) {
			throw new SkipException("Can't test on firefox");
		}
			 mydriver.get(url);
			JavascriptExecutor jse = (JavascriptExecutor)mydriver;
			scrollToElement(mydriver, videoContainer, logger);
			jse.executeScript("arguments[0].click()", bigPlayButton);
//			bigPlayButton.click();
			pleaseWait(2, logger);
			Actions act = new Actions(mydriver);
			focusElement(mydriver, mydriver.switchTo().activeElement());
			act.moveByOffset(mydriver.switchTo().activeElement().getLocation().getX()+5, mydriver.switchTo().activeElement().getLocation().getX()+5).perform();
			
				try {
					captionField.isDisplayed();
				} catch (Exception e2) {
					throw new SkipException("No Caption File Available on => "+url);
				}
				
				moveMouseOnToElement(mydriver, videoContainer);
			scrollToElement(mydriver, captionButton, logger);
			moveMouseOnToElement(mydriver, captionButton);
			pleaseWait(1, logger);
			List<WebElement> captionOption = mydriver.findElements(By.xpath(captionOptions));
			jse.executeScript("arguments[0].click()", timeline);
			jse.executeScript("arguments[0].click()", captionOption.get(1));
//			captionOption.get(1).click();
			logger.info("User has selected '" + captionOption.get(1).getText() + "'");
			if(!localBrowser.equals("-ie")||!remoteBrowser.equals("-ie")) {
				hardAssert.assertFalse(captionField.getAttribute("src").isEmpty());
			}


	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"videoComponent"})
	public void transcriptFileAndSubTitlesFunctionality(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			bigPlayButton.click();
			pleaseWait(5, logger);
			try {
				transcriptButton.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There is no transcript file!");
			}

			transcriptButton.click();
			scrollToElement(mydriver, transcriptTitle, logger);
			hardAssert.assertTrue(verifyElementExists(logger, transcriptTitle, "Transcript Title"));
//			hardAssert.assertFalse(transcriptTitle.getAttribute("innerText").isEmpty());

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"videoComponent"})
	public void remainingTimeFunctionality(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			logger.info("Total Time : " + remainingTime.getAttribute("innerText").split("-")[1]);
			String totalLength = remainingTime.getAttribute("innerText").split("-")[1];

			bigPlayButton.click();
			pleaseWait(5, logger);
			moveMouseOnToElement(mydriver, footerPauseButton).click();
			pleaseWait(2, logger);
			logger.info("Current time : " + currentProgressTime.getAttribute("aria-valuetext"));
			String currentTime = currentProgressTime.getAttribute("aria-valuetext");
			logger.info("Remaining time : " + remainingTime.getAttribute("innerText").split("-")[1]);
			String remaining = remainingTime.getAttribute("innerText").split("-")[1];
			if (!(Integer.parseInt(totalLength.split(":")[1])
					- (Integer.parseInt(currentTime.split(":")[1]) + Integer.parseInt(remaining.split(":")[1])) <= 1)) {
				fail("Difference between actual and expected 'remaining time' is more than 1 ");
			}

	}

	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"videoComponent"})
	public void videoQualitySwitchingFunctionality(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			bigPlayButton.click();
			pleaseWait(5, logger);

			try {
				qualityControl.isDisplayed();
			}catch (Exception e) {
				throw new SkipException("There's no quality control option");
			}
			scrollToElementWithoutWait(mydriver, videoContainer);
			videoContainer.click();
			videoContainer.click();
			moveMouseOnToElement(mydriver, qualityControl).click();
			List<WebElement> qualtiyOptions = mydriver.findElements(By.xpath(qualityOptions));
			int i = 0;
			int j = 0;
			for (WebElement option : qualtiyOptions) {

				if (option.getAttribute("class").contains("selected")) {
					i = j;
					logger.info("Quality selected by default: " + option.getAttribute("innerText"));
					break;
				}
				j++;
			}

			j = getRandomInteger(qualtiyOptions.size(), 0);
			while (j == i) {
				j = getRandomInteger(qualtiyOptions.size(), 0);
			}

			qualtiyOptions.get(j).click();
			pleaseWait(4, logger);
			logger.info("User have selected the quality: " + qualtiyOptions.get(j).getAttribute("innerText"));
			hardAssert.assertTrue(qualtiyOptions.get(j).getAttribute("class").contains("selected"));


	}

	@Test(priority = 7, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"videoComponent"})
	public void fullscreenFunctionality(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			bigPlayButton.click();
			pleaseWait(5, logger);
			scrollToElement(mydriver, videoContainer, logger);
			videoContainer.click();
			videoContainer.click();
			moveMouseOnToElement(mydriver, fullscreenButton).click();

			pleaseWait(4, logger);
			moveMouseOnToElement(mydriver, nonFullscreenButton);
			if (!videoContainer.findElement(By.xpath("parent::div")).getAttribute("class").contains("fullscreen")) {
				fail("Fullscreen mode is not activated when the button is clicked");
			}
			videoContainer.click();
			videoContainer.click();
			pleaseWait(1, logger);
			moveMouseOnToElement(mydriver, nonFullscreenButton).click();
			pleaseWait(4, logger);
			try {
				moveMouseOnToElement(mydriver, nonFullscreenButton);
//				if (nonFullscreenButton.isDisplayed()) {
//					fail("Video is still in the full screen mode even after clicking exit button");
//				}
//				;

			} catch (Exception e) {

			}


	}

	@Test(priority = 8, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"videoComponent"})
	public void timelineHoppingFunctionality(String url) {
		skipNonExistingComponent(url);
		if(browserName.toLowerCase().equalsIgnoreCase("firefox")) {
			throw new SkipException("Can't test on firefox");
		}

			 mydriver.get(url);
			scrollToElement(mydriver, bigPlayButton, logger);
			bigPlayButton.click();
			pleaseWait(5, logger);
			String remaining = currentProgressTime.getAttribute("aria-valuetext");
			moveMouseOnToElement(mydriver, footerPauseButton).click();
			pleaseWait(5, logger);
			Actions act = new Actions(mydriver);
			JavascriptExecutor jse = (JavascriptExecutor)mydriver;
			jse.executeScript("arguments[0].click()", timeline);
			act.moveToElement(timeline).clickAndHold(timeline).moveToElement(timeline, 80, 1).release().perform();
//			act.moveToElement(timeline, 60, 1).click().perform();
			pleaseWait(10, logger);
			hardAssert.assertNotEquals(currentProgressTime.getAttribute("aria-valuetext"), remaining);


	}

}
