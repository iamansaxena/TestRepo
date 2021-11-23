package componentStepDef;import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.LoopingVideo_page;
import core.CustomDataProvider;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class LoopingVideo_StepDefinition extends LoopingVideo_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	//private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {
		fetchSession(LoopingVideo_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(LoopingVideo_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new LoopingVideo_page();

		ExtentTestManager.startTest(LoopingVideo_StepDefinition.class.getName());
		setTagForTestClass("Looping Video Container", author, LoopingVideo_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(LoopingVideo_StepDefinition.class);
		logger.info("Urls for '" + LoopingVideo_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(LoopingVideo_StepDefinition.class.getName(), currentDomain);

		driverMap.put(LoopingVideo_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + LoopingVideo_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		// mydriver.manage().deleteAllCookies();
	}

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
