package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MainHeroBanner_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MainHeroBanner_StepDefinition extends MainHeroBanner_page {

	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(MainHeroBanner_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MainHeroBanner_StepDefinition.class.getName());
		new MainHeroBanner_page();
		ExtentTestManager.startTest(MainHeroBanner_StepDefinition.class.getName());
		setTagForTestClass("MainHeroBanner", author, MainHeroBanner_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MainHeroBanner_StepDefinition.class);
		logger.info("Urls for '" + MainHeroBanner_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MainHeroBanner_StepDefinition.class.getName(), currentDomain);

		driverMap.put(MainHeroBanner_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + MainHeroBanner_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
//		mydriver.manage().deleteAllCookies();
	}

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-hero-banner"})
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);
		//for (String url : urls) {

			 mydriver.get(url);

			List<WebElement> banners = mydriver.findElements(By.xpath(MainHeroBanner_page.banners));
			int i = 0;
			for (WebElement banner : banners) {
				scrollToElement(mydriver, banner, logger);
				scrollToElement(mydriver, mydriver.findElements(By.xpath(topLines)).get(i), logger);
				logger.info(
						"Banner '" + i + "' Topline => " + mydriver.findElements(By.xpath(topLines)).get(i).getText());
				scrollToElement(mydriver, mydriver.findElements(By.xpath(secondLines)).get(i), logger);
				logger.info("Banner '" + i + "' Second Line => "
						+ mydriver.findElements(By.xpath(secondLines)).get(i).getText());
				String imagePath = mydriver.findElements(By.xpath(bannerImage)).get(i).getAttribute("value");
				logger.info("Banner '" + i + "' Image => " + imagePath);
				i++;
			}

		//}
	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-hero-banner"})
	public void blankMandatoryFieldCheck(String url) {
		skipNonExistingComponent(url);
		//for (String url : urls) {
			 mydriver.get(url);
			List<WebElement> banners = mydriver.findElements(By.xpath(MainHeroBanner_page.banners));
			int i = 0;
			for (WebElement banner : banners) {
				scrollToElement(mydriver, banner, logger);
				scrollToElement(mydriver, mydriver.findElements(By.xpath(topLines)).get(i), logger);
				if (mydriver.findElements(By.xpath(topLines)).get(i).getText().isEmpty()) {
					fail("Topline " + i + " is blank");
				}
				scrollToElement(mydriver, mydriver.findElements(By.xpath(secondLines)).get(i), logger);
				if (mydriver.findElements(By.xpath(secondLines)).get(i).getText().isEmpty()) {
					fail("SecondLine " + i + " is blank");
				}
				String imagePath = mydriver.findElements(By.xpath(bannerImage)).get(i).getAttribute("value");
				if (imagePath.isEmpty()) {
					fail("Banner Image " + i + " is empty");
				}
				logger.info("Banner '" + i + "' Image => " + imagePath);
				i++;
			}

		//}
	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-hero-banner"})
	public void buttonLabelCheck(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			List<WebElement> buttons;
			try {
				buttons = mydriver.findElements(By.xpath(MainHeroBanner_page.linkButtons));

			} catch (Exception e) {
				try {
					buttons = mydriver.findElements(By.xpath(MainHeroBanner_page.videoButtons));

				} catch (Exception f) {
					throw new SkipException("There's no button present on the banner");
				}

			}
			for (WebElement button : buttons) {
				scrollToElement(mydriver, button, logger);
				if (button.getText().isEmpty()) {
					fail("Button label is blank");
				} else {
					logger.info("Button label found=> " + button.getText());
				}
			}
		//}
	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-hero-banner"})
	public void buttonBlankHyperlinkCheck(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			List<WebElement> buttons;
			boolean isVideo = false;
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(linkButtons)), logger);
				buttons = mydriver.findElements(By.xpath(MainHeroBanner_page.linkButtons));
				isVideo = false;
			} catch (Exception e) {
				try {
					scrollToElement(mydriver, mydriver.findElement(By.xpath(videoButtons)), logger);
					buttons = mydriver.findElements(By.xpath(MainHeroBanner_page.videoButtons));
					isVideo = true;
				} catch (Exception f) {
					throw new SkipException("There's no button present on the banner");
				}

			}
			int i = 0;
			for (WebElement button : buttons) {
				scrollToElement(mydriver, button, logger);
				if (isVideo == true) {
					List<WebElement> videos = mydriver.findElements(By.xpath(
							"(//*[@class=\"banner__main-hero--cta\"]/button//ancestor::div[@class=\"main-hero-banner section\"])//section[@id=\"ModalId1\"]//div[contains(@class,\"vjs-default\")]"));
					if (videos.get(i).getAttribute("title").isEmpty()) {
						fail("Button hyperlink is blank");
					} else {
						logger.info("Button Hyperlink video found=> " + videos.get(i).getAttribute("title"));
					}
					;
					i++;
				} else {
					if (button.getAttribute("href").isEmpty()) {
						fail("Button hyperlink is blank");
					} else {
						logger.info("Button Hyperlink found=> " + button.getAttribute("href"));
					}
				}
			}

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-hero-banner"})
	public void videoModalCheck(String url) {
		skipNonExistingComponent(url);

			List<WebElement> buttons;
			 mydriver.get(url);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(videoButtons)), logger);
				buttons = mydriver.findElements(By.xpath(videoButtons));
				
			} catch (Exception f) {
				throw new SkipException("There's no video button present on the banner");
			}

			scrollToElement(mydriver, buttons.get(0), logger);
			buttons.get(0).click();
			pleaseWait(1, logger);
			if (videoPlayButton.isDisplayed() == false) {
				fail("Modal is not getting open on clicking the video button");
			}


	}

	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-hero-banner"})
	public void videoModalCloseCheck(String url) {
		skipNonExistingComponent(url);

			List<WebElement> buttons;
			 mydriver.get(url);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(videoButtons)), logger);
				buttons = mydriver.findElements(By.xpath(videoButtons));
			} catch (Exception f) {
				throw new SkipException("There's no video button present on the banner");
			}
			scrollToElement(mydriver, buttons.get(0), logger);
			buttons.get(0).click();
			pleaseWait(1, logger);
			if (videoPlayButton.isDisplayed() == false) {
				fail("Modal is not getting open on clicking the video button");
			}
			videoModalClose.click();
			try {
				if (videoPlayButton.isDisplayed()) {
					fail("Modal Close button is not working");
				}
			} catch (Exception e) {

			}


	}

	@Test(priority = 7, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-hero-banner"})
	public void videoModalFunctionalCheck(String url) {
		skipNonExistingComponent(url);

			List<WebElement> buttons;
			 mydriver.get(url);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(videoButtons)), logger);
				buttons = mydriver.findElements(By.xpath(videoButtons));
			} catch (Exception f) {
				throw new SkipException("There's no video button present on the banner");
			}
			scrollToElement(mydriver, buttons.get(0), logger);
			buttons.get(0).click();
			pleaseWait(1, logger);
			if (videoPlayButton.isDisplayed() == false) {
				fail("Play button is not visible");
			}
			;
			videoPlayButton.click();
			videoMuteButton.click();
			videoPauseButton.click();
			if (mydriver.findElement(By.xpath("(//*[@title=\"Play\"])[1]")).isDisplayed() == false) {
				fail("Play button is not visible");
			}
			videoFullScreenButton.click();
			if (videoFullScreenMode.isDisplayed() == false) {
				fail("Fullscreen mode is not activated when the button is clicked");
			}
			videoFullScreenMode.click();
			try {
				videoFullScreenMode.isDisplayed();
				fail("Modal is still in fullscreen mode even after clicking minimize button");
			} catch (Exception e) {

			}

	}

	@Test(priority = 8, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-hero-banner"})
	public void linkButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);

			List<WebElement> buttons;
			 mydriver.get(url);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(linkButtons)), logger);
				buttons = mydriver.findElements(By.xpath(linkButtons));
			} catch (Exception f) {
				throw new SkipException("There's no link button present on the banner");
			}
			for (WebElement button : buttons) {
				scrollToElement(mydriver, button, logger);
				String hyperlink = button.getAttribute("href");
				String currentTab = mydriver.getWindowHandle();
				if (getDomainName(mydriver.getCurrentUrl()).equals(getDomainName(hyperlink))) {
					button.click();
					logger.info(" Internal Link [" + hyperlink + "]:  Hyperlink must get open in the same tab");
					hardAssert.assertEquals(mydriver.getWindowHandle(), currentTab);
				} else if (!getDomainName(mydriver.getCurrentUrl()).equals(getDomainName(hyperlink))) {
					button.click();
					logger.info(" External Link:  Hyperlink must get open in a new tab");
					hardAssert.assertNotEquals(mydriver.getWindowHandle(), currentTab);
				}
			}
		}


}
