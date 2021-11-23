package componentStepDef;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ImageResponsiveRendition_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ImageResponsiveRendition_StepDefinition extends ImageResponsiveRendition_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> urls = new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(ImageResponsiveRendition_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ImageResponsiveRendition_StepDefinition.class.getName());
<<<<<<< Updated upstream
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
=======
>>>>>>> Stashed changes
		new ImageResponsiveRendition_page();
		if (fetchUrl("image-responsive-rendition") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				urls.add("http://apsrs5642:8080/content/AutomationDirectory/image-responsive-rendition.html");

			} else if (Environment.equalsIgnoreCase("test")) {
				urls.add("http://apvrt31468:4503/content/AutomationDirectory/image-responsive-rendition.html");
			}

		} else {
			String[] scannedUrls = fetchUrl("image-responsive-rendition").split(",");
			for (String link : scannedUrls) {
				urls.add(link);
			}
		}

		ExtentTestManager.startTest(ImageResponsiveRendition_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("Image Responsive Rendition", author, currentDomain,
				ImageResponsiveRendition_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ImageResponsiveRendition_StepDefinition.class);
		logger.info("Urls for '" + ImageResponsiveRendition_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ImageResponsiveRendition_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ImageResponsiveRendition_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info(
				"Browser pool at '" + ImageResponsiveRendition_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
// Priyanka Review Note: This TC is not required as the images are not a mandate and will be taken automatically from DAM if not proided
// 	@Test(priority = 1, enabled = true)
// 	public void blankImageCheck() {
// 		skipNonExistingComponent(urls);
// 		for (String url : urls) {
// 			urlUnderTest.get().add(url);
// 			mydriver.get(url);
// 			try {
// 				mydriver.findElement(By.xpath(images)).isDisplayed();
// 			} catch (Exception e) {
// 				e.printStackTrace();
// 				throw new SkipException("There's no image authored");
// 			}
// 			for (WebElement image : mydriver.findElements(By.xpath(images))) {
// 				hardAssert.assertFalse(image.getAttribute("srcset").isEmpty());
// 			}

// 		}
// 	}

	@Test(priority = 2, enabled = true)
	public void linkRedirectionCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			String expUrl =null;
			String handle = mydriver.getWindowHandle();
			try {
				expUrl = link.getAttribute("href");
			} catch (Exception e) {
				throw new SkipException("There's no redirect link authored");
			}
			link.click();
			assertRedirection(mydriver, logger, getDomainName(url), expUrl, handle);
		}
	}
}
