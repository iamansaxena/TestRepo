package componentStepDef;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.TestimonialCarousel_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class TestimonialCarousel_StepDefinition extends TestimonialCarousel_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	//private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(TestimonialCarousel_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(TestimonialCarousel_StepDefinition.class.getName());
		new TestimonialCarousel_page();
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		ExtentTestManager.startTest(TestimonialCarousel_StepDefinition.class.getName());
		setTagForTestClass("Testimonial Carousel", author, TestimonialCarousel_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(TestimonialCarousel_StepDefinition.class);
		logger.info("Urls for '" + TestimonialCarousel_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(TestimonialCarousel_StepDefinition.class.getName(), currentDomain);

		driverMap.put(TestimonialCarousel_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + TestimonialCarousel_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"testimonial-carousel"})
	public void slideSwitchingButtonFunctionalityCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, testimonialSection, logger);
			try {
				mydriver.findElements(By.xpath(slides)).get(1).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's only one slide available");
			}
			String currentSlide = mydriver.findElement(By.xpath(activeSlide)).getAttribute("data-slide-index");
			nextAngularButton.click();
			pleaseWait(2, logger);
			String newSlide = mydriver.findElement(By.xpath(activeSlide)).getAttribute("data-slide-index");
			customTestLogs.get().add("Checking if next button functionality working: " + !(newSlide.equals(currentSlide)));
			hardAssert.assertNotEquals(newSlide, currentSlide);
			currentSlide = mydriver.findElement(By.xpath(activeSlide)).getAttribute("data-slide-index");
			nextAngularButton.click();
			pleaseWait(2, logger);
			newSlide = mydriver.findElement(By.xpath(activeSlide)).getAttribute("data-slide-index");
			customTestLogs.get().add("Checking if prev button functionality working: " +!(newSlide.equals(currentSlide)));
			hardAssert.assertNotEquals(newSlide, currentSlide);

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"testimonial-carousel"})
	public void slideSwitchingPagerFunctionalityCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, testimonialSection, logger);
			try {
				mydriver.findElements(By.xpath(slides)).get(1).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's only one slide available");
			}
			String currentSlide = mydriver.findElement(By.xpath(activeSlide)).getAttribute("data-slide-index");
			mydriver.findElements(By.xpath(pagerButtons)).get(1).click();
			pleaseWait(2, logger);
			String newSlide = mydriver.findElement(By.xpath(activeSlide)).getAttribute("data-slide-index");
			customTestLogs.get().add("Checking if next pager functionality working: " + !(newSlide.equals(currentSlide)));
			hardAssert.assertNotEquals(newSlide, currentSlide);
			currentSlide = mydriver.findElement(By.xpath(activeSlide)).getAttribute("data-slide-index");
			mydriver.findElements(By.xpath(pagerButtons)).get(0).click();
			pleaseWait(2, logger);
			newSlide = mydriver.findElement(By.xpath(activeSlide)).getAttribute("data-slide-index");
			customTestLogs.get().add("Checking if prev pager functionality working: " + !(newSlide.equals(currentSlide)));
			hardAssert.assertNotEquals(newSlide, currentSlide);

	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"testimonial-carousel"})
	public void sliderAndSocialCopyFieldCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, testimonialSection, logger);
			int i = 1;
			boolean isAvailable = false;

			try {
				mydriver.findElement(By.xpath(slideContent)).isDisplayed();
				isAvailable = true;
			} catch (Exception e) {
				isAvailable = false;
			}
			if (isAvailable == true) {
				for (WebElement content : mydriver.findElements(By.xpath(slideContent))) {
					hardAssert.assertFalse(content.getAttribute("innerText").isEmpty());
					customTestLogs.get()
							.add("Check slider copy '" + i + "' field: " + content.getAttribute("innerText"));
					i++;
				}
			}
			try {
				mydriver.findElement(By.xpath(socialSharingSlideLinks)).isDisplayed();
				isAvailable = true;
			} catch (Exception e) {
				isAvailable = false;
			}
			i = 1;
			if (isAvailable == true) {
				for (WebElement content : mydriver.findElements(By.xpath(socialSharingSlideLinks))) {

					hardAssert.assertFalse(content.getAttribute("innerText").isEmpty());
					customTestLogs.get()
							.add("Check social copy '" + i + "' field: " + content.getAttribute("innerText"));
					i++;
				}
			}

	}


	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"testimonial-carousel"})
	public void videoSlideModalOpenFunctionalityCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, testimonialSection, logger);
			
			try {
				mydriver.findElement(By.xpath(slidesWithVideo)).isDisplayed();
			} catch (Exception e) {
			throw new SkipException("There is no video slide");
			}
			while(mydriver.findElement(By.xpath(slidesWithVideo)).isDisplayed()!=true) {
				nextAngularButton.click();
				pleaseWait(2, logger);
			}
			mydriver.findElement(By.xpath(slidesWithVideo)).click();
			pleaseWait(2, logger);
			customTestLogs.get().add("Checking if video modal opens on clicking the slide: "+verifyElementExists(logger, videoModal, "Video Modal"));
			hardAssert.assertTrue(verifyElementExists(logger, videoModal, "Video Modal"));
			

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"testimonial-carousel"})
	public void socialLinkRedirectionCheck(String url) {
			skipNonExistingComponent(url);
			
				
				mydriver.get(url);
				scrollToElement(mydriver, testimonialSection, logger);
				try {
					mydriver.findElement(By.xpath(socialSharingSlideLinks)).isDisplayed();
				} catch (Exception e) {
					throw new SkipException("There's no social link available");
				}
				List<WebElement> socialLink = mydriver.findElements(By.xpath(socialSharingSlideLinks));
				List<WebElement> socialLinkLabels = mydriver.findElements(By.xpath(socialSharingSlideLinksLabel));
				int i = getRandomInteger(socialLink.size(), 0);
				
				while(!socialLinkLabels.get(i).isDisplayed()) {
					nextAngularButton.click();
					pleaseWait(2, logger);
					if(socialLinkLabels.get(i).isDisplayed()) {
						break;
					}
					
					
				}
				String expLink = socialLink.get(i).getAttribute("href");
				String handle = mydriver.getWindowHandle();
				String socialCopy = socialLink.get(i).getAttribute("innerText");
				scrollToElement(mydriver, testimonialSection, logger);
				
				socialLink.get(i).click();
				pleaseWait(5, logger);
				customTestLogs.get().add("Checking social link redirection '" + i + "' field: " + socialCopy);
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
			
			}

}
