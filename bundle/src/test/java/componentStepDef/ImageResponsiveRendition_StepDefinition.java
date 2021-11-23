package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ImageResponsiveRendition_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ImageResponsiveRendition_StepDefinition extends ImageResponsiveRendition_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(ImageResponsiveRendition_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ImageResponsiveRendition_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new ImageResponsiveRendition_page();
		ExtentTestManager.startTest(ImageResponsiveRendition_StepDefinition.class.getName());
		setTagForTestClass("Image Responsive Rendition", author, ImageResponsiveRendition_StepDefinition.class.getName());
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
	// Priyanka Review Note: This TC is not required as the images are not a mandate
	// and will be taken automatically from DAM if not proided
	// @Test(priority = 1, enabled = true)
	// public void blankImageCheck() {
	// skipNonExistingComponent(urls);
	// for (String url : urls) {
	// 
	// mydriver.get(url);
	// try {
	// mydriver.findElement(By.xpath(images)).isDisplayed();
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new SkipException("There's no image authored");
	// }
	// for (WebElement image : mydriver.findElements(By.xpath(images))) {
	// hardAssert.assertFalse(image.getAttribute("srcset").isEmpty());
	// }

	// }
	// }

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"image-responsive-rendition" })
	public void linkRedirectionCheck(String url) {
		skipNonExistingComponent(url);
		
		mydriver.get(url);
		String expUrl = null;
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
