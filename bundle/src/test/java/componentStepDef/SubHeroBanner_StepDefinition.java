package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.SubHeroBanner_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class SubHeroBanner_StepDefinition extends SubHeroBanner_page {
	private String author = "Aman Saxena";

	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {
		fetchSession(SubHeroBanner_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(SubHeroBanner_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new SubHeroBanner_page();
		ExtentTestManager.startTest(SubHeroBanner_StepDefinition.class.getName());
		setTagForTestClass("SubHeroBanner", author, SubHeroBanner_StepDefinition.class.getName());

		logger = LoggerLog4j.startTestCase(SubHeroBanner_StepDefinition.class);
		logger.info("Urls for '" + SubHeroBanner_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(SubHeroBanner_StepDefinition.class.getName(), currentDomain);

		driverMap.put(SubHeroBanner_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + SubHeroBanner_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"sub-banner"})
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);
		
			 mydriver.get(url);

			List<WebElement> banners = mydriver.findElements(By.xpath(SubHeroBanner_page.banners));
			int i = 0;
			for (WebElement banner : banners) {
				scrollToElement(mydriver, banner, logger);
				String imagePath = mydriver.findElements(By.xpath(bannerImage)).get(i).getAttribute("value");
				logger.info("Sub-Banner '" + i + "' Image => " + imagePath);
				i++;
			}


	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"sub-banner"})
	public void blankBannerImageCheck(String url) throws MalformedURLException {
		skipNonExistingComponent(url);
		
			 mydriver.get(url);

			List<WebElement> banners = mydriver.findElements(By.xpath(SubHeroBanner_page.banners));
			int i = 0;
			for (WebElement banner : banners) {
				scrollToElement(mydriver, banner, logger);
				String imagePath = mydriver.findElements(By.xpath(bannerImage)).get(i).getAttribute("value");
				if (imagePath.isEmpty()) {
					fail("Banner Image " + i + " is empty");
				} else {

					logger.info("Banner '" + i + "' Image => " + imagePath);
				}
				i++;
			}


	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"sub-banner"})
	public void buttonLabelCheck(String url) {
		skipNonExistingComponent(url);
		
			 mydriver.get(url);

			List<WebElement> buttons;
			try {
				buttons = mydriver.findElements(By.xpath(SubHeroBanner_page.linkButtons));

			} catch (NoSuchElementException e) {
				try {
					buttons = mydriver.findElements(By.xpath(SubHeroBanner_page.videoButtons));

				} catch (NoSuchElementException f) {
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

	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"sub-banner"})
	public void buttonBlankHyperlinkCheck(String url) {
		skipNonExistingComponent(url);
		
			 mydriver.get(url);
			List<WebElement> buttons;
			boolean isVideo = false;
			try {
				buttons = mydriver.findElements(By.xpath(SubHeroBanner_page.linkButtons));
				isVideo = false;
			} catch (Exception e) {
				try {
					buttons = mydriver.findElements(By.xpath(SubHeroBanner_page.videoButtons));
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

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"sub-banner"})
	public void videoModalCheck(String url) {
		skipNonExistingComponent(url);
		
			List<WebElement> buttons;
			 mydriver.get(url);
			try {
				mydriver.findElement(By.xpath(videoButtons)).isDisplayed();
				buttons = mydriver.findElements(By.xpath(videoButtons));
				if(buttons==null||buttons.isEmpty()||buttons.size()==0) {
					throw new Exception();
				}
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

	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"sub-banner"})
	public void videoModalCloseCheck(String url) {
		skipNonExistingComponent(url);
		
			List<WebElement> buttons;
			 mydriver.get(url);
			try {
				mydriver.findElement(By.xpath(videoButtons)).isDisplayed();
				buttons = mydriver.findElements(By.xpath(videoButtons));
				if(buttons==null||buttons.isEmpty()||buttons.size()==0) {
					throw new Exception();
				}
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

	@Test(priority = 7, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"sub-banner"})
	public void videoModalFunctionalCheck(String url) {
		skipNonExistingComponent(url);
		
			List<WebElement> buttons;
			 mydriver.get(url);
			try {
				mydriver.findElement(By.xpath(videoButtons)).isDisplayed();
				buttons = mydriver.findElements(By.xpath(videoButtons));
				if(buttons==null||buttons.isEmpty()||buttons.size()==0) {
					throw new Exception();
				}
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
			videoNonFullScreenButton.click();
			try {
				videoFullScreenMode.isDisplayed();
				fail("Modal is still in fullscreen mode even after clicking minimize button");
			} catch (Exception e) {

			}

	}

	@Test(priority = 8, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"sub-banner"})
	public void linkButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);
		
			List<WebElement> buttons;
			 mydriver.get(url);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(linkButtons)), logger);
			} catch (NoSuchElementException f) {
				throw new SkipException("There's no link button present on the banner");
			}
			buttons = mydriver.findElements(By.xpath(linkButtons));
			int i = 0;
			for (WebElement button : buttons) {
				if (i == 0) {
					scrollToElement(mydriver, button, logger);
					String hyperlink = button.getAttribute("href");
					String handle = mydriver.getWindowHandle();
					button.click();
//					getActions(mydriver).click(button).perform();
					pleaseWait(4, logger);
					jsWaitForPageToLoad(10,mydriver, logger);
					
					assertRedirection(mydriver, logger, getDomainName(url), hyperlink, handle);
					i++;
				}
			}


	}

	
	
	@Test(priority = 9, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"sub-banner"})
	public void bannerContentCheck(String url) {
		skipNonExistingComponent(url);
		
			List<WebElement> contents = null;
			 mydriver.get(url);

			try {
				contents = mydriver.findElements(By.xpath(descriptions));
				if (contents == null) {
					throw new Exception();
				}
			} catch (Exception e) {
				logger.info("There is no content section available for this banner");
				throw new SkipException("There is no content section available for this banner");
			}
			for (WebElement content : contents) {
				scrollToElement(mydriver, content, logger);
				if (content.getText().isEmpty()) {
					fail("Blank content");
				} else {
					logger.info("Sub Banner content " + content.getText());
				}
			}


	}

	@Test(priority = 10, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"sub-banner"})
	public void bannerTopSecondCheck(String url) {
		skipNonExistingComponent(url);
		
			List<WebElement> topLines = null;
			List<WebElement> banners;
			List<WebElement> secondLines = null;
			 mydriver.get(url);
			try {
				topLines = mydriver.findElements(By.xpath(SubHeroBanner_page.topLines));
			} catch (Exception e) {
				logger.info("There is no top line");
			}
			try {
				secondLines = mydriver.findElements(By.xpath(SubHeroBanner_page.secondLines));
			} catch (Exception e) {
				logger.info("There is no second line");
			}
			int i = 0;
			banners = mydriver.findElements(By.xpath(SubHeroBanner_page.banners));
			for (WebElement banner : banners) {
				scrollToElement(mydriver, banner, logger);
				if (topLines != null) {
					if (i < topLines.size()) {
						scrollToElement(mydriver, topLines.get(i), logger);
						if (topLines.get(i).getText() == null) {
							fail("Topline " + i + " is blank");
						}
					}
				}
				if (secondLines != null) {
					if (i < secondLines.size()) {
						scrollToElement(mydriver, secondLines.get(i), logger);
						if (secondLines.get(i).getText().isEmpty()) {
							fail("SecondLine " + i + " is blank");
						}
					}
				}
				i++;
			}


	}

}
