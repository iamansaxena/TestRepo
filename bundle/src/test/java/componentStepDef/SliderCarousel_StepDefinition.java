package componentStepDef;import java.util.concurrent.TimeUnit;

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

import compontentPages.SliderCarousel_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class SliderCarousel_StepDefinition extends SliderCarousel_page{
	private String author = "Aman Saxena";
	private static Logger logger;
	//private static ArrayList<String> urls= new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(SliderCarousel_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(SliderCarousel_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new SliderCarousel_page();
		ExtentTestManager.startTest(SliderCarousel_StepDefinition.class.getName());
		setTagForTestClass("SliderCarousel", author, SliderCarousel_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(SliderCarousel_StepDefinition.class);
		logger.info("Urls for '" + SliderCarousel_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(SliderCarousel_StepDefinition.class.getName(), currentDomain);

		driverMap.put(SliderCarousel_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + SliderCarousel_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"slider"})
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);
		

			 mydriver.get(url);
			
			List<WebElement> altTags = mydriver.findElements(By.xpath(cardImages));
			List<WebElement> titles = mydriver.findElements(By.xpath(cardTitles));
			List<WebElement> card = mydriver.findElements(By.xpath(cards));
			
			hardAssert.assertEquals(titles.size(), card.size());
			hardAssert.assertEquals(altTags.size(), card.size());


	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"slider"})
	public void sliderFunctionalityCheck(String url) {
		skipNonExistingComponent(url);
		

			 mydriver.get(url);
			List<WebElement> card = mydriver.findElements(By.xpath(cards));
			scrollToElement(mydriver, card.get(0), logger);	
			if(!(card.size()>3)) {
					throw new SkipException("Only '"+card.size()+"' cards are available ");
			}
			hardAssert.assertTrue(verifyElementExists(logger, nextButton, "Next button"));
			nextButton.click();
			nextButton.click();
			pleaseWait(6, logger);
			hardAssert.assertTrue(verifyElementExists(logger, card.get(4), "Fourth Card"));
			hardAssert.assertTrue(verifyElementExists(logger, prevButton, "Previous button"));
			prevButton.click();
			prevButton.click();
			pleaseWait(6, logger);
			if(!localBrowser.equals("-ie")||!remoteBrowser.equals("-ie")) {
				hardAssert.assertFalse(verifyElementExists(logger, card.get(4), "Fourth Card"));
			}
			

	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"slider"})
	public void conditionalSliderButtonAvailabilityCheck(String url) {
		skipNonExistingComponent(url);
		

			 mydriver.get(url);
			List<WebElement> card = mydriver.findElements(By.xpath(cards));
			if(card.size()>=4) {
				scrollToElement(mydriver, nextButton, logger);
				hardAssert.assertTrue(verifyElementExists(logger, nextButton, "Slider Button"));
			}
			

	}


	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"slider"})
	public void mainButtonAvailabilityCheck(String url) {
		skipNonExistingComponent(url);
		

			 mydriver.get(url);
			try {
				scrollToElement(mydriver, mainButton, logger);
			} catch (Exception e) {
				throw new SkipException("Main Button is not available");
			}
			hardAssert.assertFalse(mainButton.getAttribute("href").isEmpty());

	}



	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"slider"})
	public void mainButtonBlankLabelCheck(String url) {
		skipNonExistingComponent(url);
		

			 mydriver.get(url);
			try {
				scrollToElement(mydriver, mainButton, logger);
			} catch (Exception e) {
				throw new SkipException("Main Button is not available");
			}
			hardAssert.assertFalse(mainButton.getText().isEmpty());

	}

	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"slider"})
	public void mainButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);
		

			 mydriver.get(url);
			try {
				scrollToElement(mydriver, mainButton, logger);
			} catch (Exception e) {
				throw new SkipException("Main Button is not available");
			}
				String expUrl = mainButton.getAttribute("href");
				String handle = mydriver.getWindowHandle();
				mainButton.click();
				pleaseWait(5, logger);
				assertRedirection(mydriver, logger, getDomainName(url), expUrl,handle);
//				hardAssert.assertEquals(mydriver.getCurrentUrl(), expUrl);

	}


}
