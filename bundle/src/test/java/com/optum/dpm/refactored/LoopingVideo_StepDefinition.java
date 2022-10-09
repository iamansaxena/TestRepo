package com.optum.dpm.refactored;import static com.optum.dpm.utils.DPMTestUtils.jsWaitForPageToLoad;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.LoopingVideo_page;

import core.CustomDataProvider;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class LoopingVideo_StepDefinition extends LoopingVideo_page {
	
	private static final Logger logger = LogManager.getLogger(LocalMessage_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"looping-video-container"})
	public void isVideoPlaying(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			scrollToElement(mydriver, videoFrame, logger);
			jsWaitForPageToLoad(30, mydriver, logger);
			AShot ashot = new AShot();
			ImageDiffer imgUtil = new ImageDiffer();
			Screenshot img1 = ashot.takeScreenshot(mydriver, videoFrame);
			pleaseWait(2, logger);
			Screenshot img2 = ashot.takeScreenshot(mydriver, videoFrame);
			ImageDiff flag = imgUtil.makeDiff(img1.getImage(), img2.getImage());
			hardAssert.assertTrue(flag.hasDiff());


	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"looping-video-container"})
	public void blankTextCheck(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			scrollToElement(mydriver, videoFrame, logger);
			List<WebElement> textFields = mydriver.findElements(By.xpath(text));
			for(WebElement e : textFields) {
				hardAssert.assertFalse(e.getText().isEmpty());
			}

	}
	
	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"looping-video-container"})
	public void videoPlayFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			scrollToElement(mydriver, videoFrame, logger);
			jsWaitForPageToLoad(30, mydriver, logger);
			if(playPauseButton.getAttribute("aria-label").equals("play")) {
				playPauseButton.click();
			}
			
			AShot ashot = new AShot();
			ImageDiffer imgUtil = new ImageDiffer();
			Screenshot img1 = ashot.takeScreenshot(mydriver, videoFrame);
			pleaseWait(4, logger);
			Screenshot img2 = ashot.takeScreenshot(mydriver, videoFrame);
			ImageDiff flag = imgUtil.makeDiff(img1.getImage(), img2.getImage());
			hardAssert.assertTrue(flag.hasDiff());

	}
	
}
