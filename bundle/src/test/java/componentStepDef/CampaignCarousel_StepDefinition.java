package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.CampaignCarousel_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class CampaignCarousel_StepDefinition extends CampaignCarousel_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> campUrls= new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(CampaignCarousel_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(CampaignCarousel_StepDefinition.class.getName());
<<<<<<< Updated upstream
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
=======
>>>>>>> Stashed changes
		new CampaignCarousel_page();
		
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("dpl-cc-app") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				campUrls.add(
						"http://apsrs5642:8080/content/AutomationDirectory/campaign-carousel.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				campUrls.add(
						"http://apvrt31468:4503/content/AutomationDirectory/campaign-carousel.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("dpl-cc-app").split(",");
			for (String link : scannedUrls) {
				campUrls.add(link);
			}
		}

		ExtentTestManager.startTest(CampaignCarousel_StepDefinition.class.getName());
		for (String url : campUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("CampaignCarousel", author, currentDomain, CampaignCarousel_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(CampaignCarousel_StepDefinition.class);
		logger.info("Urls for '" + CampaignCarousel_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(CampaignCarousel_StepDefinition.class.getName(), currentDomain);
		driverMap.put(CampaignCarousel_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + CampaignCarousel_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true)
	public void elementVisibilityCheck() {
		skipNonExistingComponent(campUrls);
		for (String camUrl : campUrls) {
			urlUnderTest.get().add(camUrl); mydriver.get(camUrl);pauseCaraousel();
			currentDomain = currentDomain + "[" + camUrl + "]";
			try {
				mydriver.findElement(By.xpath("//*[@data-slide=2]"));
			} catch (Exception e) {
				throw new SkipException("Only One Slide is available");
			}
			scrollToElement(mydriver, nextButton, logger);
			if (verifyElementExists(logger, nextButton, "nextButton") == true) {
				hardAssert.assertTrue(verifyElementExists(logger, prevButton, "prevButton"));
				hardAssert.assertTrue(
						verifyElementExists(logger, mydriver.findElement(By.xpath(slideBullets)), "slideBullets"));
			} else if (verifyElementExists(logger, prevButton, "prevButton") == true) {
				hardAssert.assertTrue(verifyElementExists(logger, nextButton, "nextButton"));
				hardAssert.assertTrue(
						verifyElementExists(logger, mydriver.findElement(By.xpath(slideBullets)), "slideBullets"));
			}

		}
	}

	@Test(priority = 2, enabled = true)
	public void maxAndMinSlideCheck() {
		skipNonExistingComponent(campUrls);
		for (String camUrl : campUrls) {
			urlUnderTest.get().add(camUrl); mydriver.get(camUrl);pauseCaraousel();
			List<WebElement> slide = null;
//			hardAssert.assertEquals(firstSlide.isDisplayed(), true);
			int i = 4;
			try {
				mydriver.findElement(By.xpath("//*[@data-slide=2]"));
				logger.info("First slide is available");
				i = 0;
				slide = mydriver.findElements(By.xpath(slideBullets));
			} catch (Exception e) {
				i = 1;
			}
			if (i == 0) {
				if (slide.size() >= 6) {
					fail("No of Slides is more than 5");
					logger.error("No of Slides is more than 5");
				}

			}
		}
	}

	@Test(priority = 3, enabled = true)
	public void slideClickabilityAndHyperLinkCheck() {
		skipNonExistingComponent(campUrls);
		LinkedList<String> hyperLinks;
		for (String camUrl : campUrls) {
			urlUnderTest.get().add(camUrl); mydriver.get(camUrl);pauseCaraousel();
			currentDomain = currentDomain + "[" + camUrl + "]";
			hyperLinks = new LinkedList<>();
			try {
				mydriver.findElements(By.xpath(slideBullets)).get(0).click();
			} catch (Exception e) {
				logger.info("User is on the first slide");
			}
			List<WebElement> links = mydriver.findElements(By.xpath(slideLinks));
			if(links==null ||links.size()==0) {
				throw new SkipException("There's no links associated with the slides");
			}
			int i = getRandomInteger(hyperLinks.size() - 1, 0);

			try {
				URL url = new URL(links.get(i).getAttribute("href"));
				logger.info("Slide link => " + url);
			} catch (MalformedURLException e) {
				fail("Broken Link ==> " + links.get(i).getAttribute("href"));
			}
		}
	}

	@Test(priority = 4, enabled = true)
	public void slideBulletFunctionalityCheck() {
		skipNonExistingComponent(campUrls);
		int i = 1;
		for (String camUrl : campUrls) {
			urlUnderTest.get().add(camUrl); mydriver.get(camUrl);pauseCaraousel();
			currentDomain = currentDomain + "[" + camUrl + "]";
			try {
				mydriver.findElement(By.xpath("(//*[@role=\"tablist\"]/button)[" + 1 + "]")).isDisplayed();
				int noOfSlides = mydriver.findElements(By.xpath(slideBullets)).size();
				i = getRandomInteger(noOfSlides, 1);
			} catch (Exception e) {
				throw new SkipException("There is only one slide");
			}

			WebElement bullet = mydriver.findElement(By.xpath("(//*[@role=\"tablist\"]/button)[" + i + "]"));
			WebElement slide = mydriver.findElement(By.xpath("//*[@data-slide=\"" + i + "\"]"));
			scrollToElement(mydriver, nextButton, logger);
			JavascriptExecutor jse = (JavascriptExecutor)mydriver;
			jse.executeScript("arguments[0].click()", bullet);

			pleaseWait(1, logger);
			switch (i) {
			case 1:
				hardAssert.assertTrue(firstSlide.isDisplayed());
				break;
			case 2:
				hardAssert.assertTrue(slide.isDisplayed());
				break;
			case 3:
				hardAssert.assertTrue(slide.isDisplayed());
				break;
			case 4:
				hardAssert.assertTrue(slide.isDisplayed());
				break;
			case 5:
				hardAssert.assertTrue(fifthSlide.isDisplayed());
				break;
			}

		}
	}

	@Test(priority = 5)
<<<<<<< Updated upstream
	public void slideBulletHighlightCheck(String url) {
		skipNonExistingComponent(url);
=======
	public void slideBulletHighlightCheck() {
		skipNonExistingComponent(campUrls);
>>>>>>> Stashed changes
		int i = 1;
		for (String camUrl : campUrls) {
			urlUnderTest.get().add(camUrl); mydriver.get(camUrl);pauseCaraousel();
			try {
				int noOfSlides = mydriver.findElements(By.xpath(slideBullets)).size();
				i = getRandomInteger(noOfSlides, 2);
//				i = 4;
			} catch (Exception e) {
				throw new SkipException("There is only one slide");
			}
			int a = 0;
			try {
				while (a != i) {
					nextButton.click();
					a++;
				}

			} catch (Exception e) {
				throw new SkipException("There is only one slide available");
			}
			scrollToElement(mydriver, nextButton, logger);
			pleaseWait(1, logger);

			int j = i + 1;
			JavascriptExecutor jse = (JavascriptExecutor)mydriver;
			
			switch (i) {
			case 1:
				jse.executeScript("arguments[0].click()", mydriver.findElements(By.xpath(slideBullets)).get(i));
//				hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
				hardAssert.assertTrue(mydriver.findElement(By.xpath("(//*[@role=\"tablist\"]/button)[" + j + "]"))
						.getAttribute("class").contains("active"));
				break;
			case 2:
				jse.executeScript("arguments[0].click()", mydriver.findElements(By.xpath(slideBullets)).get(i));
//				hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
				hardAssert.assertTrue(mydriver.findElement(By.xpath("(//*[@role=\"tablist\"]/button)[" + j + "]"))
						.getAttribute("class").contains("active"));
				break;
			case 3:
				jse.executeScript("arguments[0].click()", mydriver.findElements(By.xpath(slideBullets)).get(i));
//				hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
				hardAssert.assertTrue(mydriver.findElement(By.xpath("(//*[@role=\"tablist\"]/button)[" + j + "]"))
						.getAttribute("class").contains("active"));
				break;
			case 4:
				jse.executeScript("arguments[0].click()", mydriver.findElements(By.xpath(slideBullets)).get(i));
//				hardAssert.assertTrue(fifthSlide.isDisplayed());
				hardAssert.assertTrue(mydriver.findElement(By.xpath("(//*[@role=\"tablist\"]/button)[" + j + "]"))
						.getAttribute("class").contains("active"));
				break;
			case 5:
				jse.executeScript("arguments[0].click()", mydriver.findElements(By.xpath(slideBullets)).get(i));
//				hardAssert.assertTrue(firstSlide.isDisplayed());
				hardAssert.assertTrue(mydriver.findElement(By.xpath("(//*[@role=\"tablist\"]/button)[" + j + "]"))
						.getAttribute("class").contains("active"));
				break;
			}
		}
	}

	// @Test(priority = 6,enabled=false)
	// public void bannerImageCheck() {
	// int i = 1;
	// for (String camUrl : campUrls) {
	// get(mydriver,camUrl,logger);
	// currentDomain = currentDomain + "[" + camUrl + "]";
	// // dsds
	// }
	// }

	@Test(priority = 7)
<<<<<<< Updated upstream
	public void previousandNextButtonCheck(String url) {
		skipNonExistingComponent(url);
=======
	public void previousandNextButtonCheck() {
		skipNonExistingComponent(campUrls);
>>>>>>> Stashed changes
		int i = 1;
		for (String camUrl : campUrls) {
			urlUnderTest.get().add(camUrl); mydriver.get(camUrl);
			pauseCaraousel();
			
			currentDomain = currentDomain + "[" + camUrl + "]";
			try {
				int noOfSlides = mydriver.findElements(By.xpath(slideBullets)).size();
				if(noOfSlides!=0) {
				i = getRandomInteger(noOfSlides, 1);
				i = 3;
				mydriver.findElements(By.xpath(slideBullets)).get(0).click();
				pleaseWait(2, logger);
				}else {
					throw new SkipException("There is only one slide");
				}
			} catch (Exception e) {
				throw new SkipException("There is only one slide");
			}
<<<<<<< Updated upstream
		} catch (Exception e) {
			throw new SkipException("There is only one slide");
		}

		int a = 0;
		try {
			while (a != i) {
				nextButton.click();
				a++;
			}

		} catch (Exception e) {
			throw new SkipException("There is only one slide available");
		}
		scrollToElement(mydriver, nextButton, logger);
		pleaseWait(3, logger);

		int j = i + 1;
		switch (i) {
		case 1:
			scrollToElement(mydriver, carouselSection, logger);
			hardAssert
					.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
			prevButton.click();
			pleaseWait(4, logger);
			hardAssert
					.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
			break;
		case 2:
			scrollToElement(mydriver, carouselSection, logger);
			hardAssert
					.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
			prevButton.click();
			pleaseWait(4, logger);
			hardAssert
					.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
			break;
		case 3:
			scrollToElement(mydriver, carouselSection, logger);
			List<WebElement> slide = mydriver.findElements(By.xpath("//*[@data-slide=\"" + j + "\"]"));
			hardAssert
					.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
			prevButton.click();
			pleaseWait(4, logger);
			hardAssert
					.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
			break;
		case 4:
			scrollToElement(mydriver, carouselSection, logger);
			hardAssert.assertTrue(fifthSlide.isDisplayed());
			prevButton.click();
			pleaseWait(4, logger);
			hardAssert
					.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
			break;
		case 5:
			scrollToElement(mydriver, carouselSection, logger);
			hardAssert.assertTrue(firstSlide.isDisplayed());
			prevButton.click();
			pleaseWait(4, logger);
			hardAssert.assertTrue(fifthSlide.isDisplayed());
			break;
		}

	}

	@Test(priority = 8)
	public void endlessCarouselLoopCheck(String url) {
		skipNonExistingComponent(url);
		int i = 1;
=======
>>>>>>> Stashed changes

			int a = 0;
			try {
				while (a != i) {
					nextButton.click();
					a++;
				}

			} catch (Exception e) {
				throw new SkipException("There is only one slide available");
			}
			scrollToElement(mydriver, nextButton, logger);
			pleaseWait(3, logger);

			int j = i + 1;
			switch (i) {
			case 1:
				scrollToElement(mydriver, carouselSection, logger);
				hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
				prevButton.click();
				pleaseWait(4, logger);
				hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
				break;
			case 2:
				scrollToElement(mydriver, carouselSection, logger);
				hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
				prevButton.click();
				pleaseWait(4, logger);
				hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
				break;
			case 3:
				scrollToElement(mydriver, carouselSection, logger);
				List<WebElement> slide = mydriver.findElements(By.xpath("//*[@data-slide=\"" + j + "\"]"));
				hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
				prevButton.click();
				pleaseWait(4, logger);
				hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
				break;
			case 4:
				scrollToElement(mydriver, carouselSection, logger);
				hardAssert.assertTrue(fifthSlide.isDisplayed());
				prevButton.click();
				pleaseWait(4, logger);
				hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
				break;
			case 5:
				scrollToElement(mydriver, carouselSection, logger);
				hardAssert.assertTrue(firstSlide.isDisplayed());
				prevButton.click();
				pleaseWait(4, logger);
				hardAssert.assertTrue(fifthSlide.isDisplayed());
				break;
			}

		}
	}

<<<<<<< Updated upstream
	@Test(priority = 9)
	public void singleSlideCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		pauseCaraousel();
		try {
			mydriver.findElement(By.xpath("//*[@data-slide=2]"));
			throw new SkipException("More Than One Slide Are Available");
		} catch (NoSuchElementException e) {
=======
	@Test(priority = 8)
	public void endlessCarouselLoopCheck() {
		skipNonExistingComponent(campUrls);
		int i = 1;
		for (String camUrl : campUrls) {
			urlUnderTest.get().add(camUrl); mydriver.get(camUrl);pauseCaraousel();
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(slideBullets)));
			int noOfSlides=0; 
>>>>>>> Stashed changes
			try {
				noOfSlides = mydriver.findElements(By.xpath(slideBullets)).size();
				mydriver.findElements(By.xpath(slideBullets)).get(0).click();
				if (noOfSlides == 0)
					throw new SkipException("There is only one slide");
			} catch (Exception e) {
				throw new SkipException("There is only one slide");
			}
			
			while (i < noOfSlides) {
				scrollToElement(mydriver, nextButton, logger);
				nextButton.click();
				pleaseWait(1, logger);
				int j = i + 1;
				switch (i) {
				case 1:
					
					scrollToElement(mydriver, carouselSection, logger);
					hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
					break;
				case 2:
					scrollToElement(mydriver, carouselSection, logger);
					hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
					break;
				case 3:
					scrollToElement(mydriver, carouselSection, logger);
					hardAssert.assertTrue(mydriver.findElement(By.xpath("//*[@data-slide=\"" + j + "\"][last()]")).isDisplayed());
					break;
				case 4:
					scrollToElement(mydriver, carouselSection, logger);
					hardAssert.assertTrue(fifthSlide.isDisplayed());
					break;
				case 5:
					scrollToElement(mydriver, carouselSection, logger);
					scrollToElement(mydriver, firstSlide, logger);
					hardAssert.assertTrue(firstSlide.isDisplayed());
					break;
				}
				
				i++;
			}
		}
	}

	@Test(priority = 9)
	public void singleSlideCheck() {
		skipNonExistingComponent(campUrls);
		for (String camUrl : campUrls) {
			urlUnderTest.get().add(camUrl); mydriver.get(camUrl);pauseCaraousel();
			try {
				mydriver.findElement(By.xpath("//*[@data-slide=2]"));
				throw new SkipException("More Than One Slide Are Available");
			} catch (NoSuchElementException e) {
				try {
					focusElement(mydriver, mydriver.findElement(By.xpath(slideBullets)));
					
					fail("SlideBullets are available even when there is only one slide");
				} catch (NoSuchElementException f) {
					logger.info("Passed - No Bullets for single slide");
				}
			}
			

		}
	}

}