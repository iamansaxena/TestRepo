package componentStepDef;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MiddleImagePlusMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MiddleImagePlusMedex_StepDefinition extends MiddleImagePlusMedex_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(MiddleImagePlusMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MiddleImagePlusMedex_StepDefinition.class.getName());
		new MiddleImagePlusMedex_page();

		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		ExtentTestManager.startTest(MiddleImagePlusMedex_StepDefinition.class.getName());
		setTagForTestClass("Middle Image Plus [Medex]", author, MiddleImagePlusMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MiddleImagePlusMedex_StepDefinition.class);
		logger.info("Urls for '" + MiddleImagePlusMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MiddleImagePlusMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(MiddleImagePlusMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + MiddleImagePlusMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
	}

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image-plus"})
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			scrollToElement(mydriver, middleImageSection, logger);
			customTestLogs.get().add("Is central headline is available: "
					+ verifyElementExists(logger, centralHeading, "Central Heading"));
			softAssert.assertTrue(verifyElementExists(logger, centralHeading, "Central Heading"));

			customTestLogs.get().add("Central headline: " + centralHeading.getAttribute("innerText"));
			softAssert.assertFalse(centralHeading.getAttribute("innerText").isEmpty());

			customTestLogs.get().add("Is Left headline is available: "
					+ verifyElementExists(logger, leftColumnHeading, "Left Column Heading"));
			softAssert.assertTrue(verifyElementExists(logger, leftColumnHeading, "Left Column Heading"));

			customTestLogs.get().add("Left headline: " + leftColumnHeading.getAttribute("innerText"));
			softAssert.assertFalse(leftColumnHeading.getAttribute("innerText").isEmpty());

			customTestLogs.get().add("Left copy: " + leftColumnCopy.getAttribute("innerText"));
			softAssert.assertFalse(leftColumnCopy.getAttribute("innerText").isEmpty());

			customTestLogs.get().add("Is Right headline is available: "
					+ verifyElementExists(logger, rightColumnHeading, "Right Column Heading"));
			softAssert.assertTrue(verifyElementExists(logger, rightColumnHeading, "Right Column Heading"));

			customTestLogs.get().add("Right headline: " + rightColumnHeading.getAttribute("innerText"));
			softAssert.assertFalse(rightColumnHeading.getAttribute("innerText").isEmpty());

			customTestLogs.get().add("Right copy: " + rightColumnCopy.getAttribute("innerText"));
			softAssert.assertFalse(rightColumnCopy.getAttribute("innerText").isEmpty());

			softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image-plus"})
	public void rightColumnCtaCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, middleImageSection, logger);

			try {
				rightColumnCtaLinkAndLabel.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("Right CTA is not available");
			}
			customTestLogs.get().add(
					"Is CTA button visible: " + verifyElementExists(logger, rightColumnCtaLinkAndLabel, "Right CTA"));
			softAssert.assertTrue(verifyElementExists(logger, rightColumnCtaLinkAndLabel, "Right CTA"));

			customTestLogs.get().add("Cta Label: " + rightColumnCtaLinkAndLabel.getAttribute("innerText"));
			softAssert.assertFalse(rightColumnCtaLinkAndLabel.getAttribute("innerText").isEmpty());

			customTestLogs.get().add("redirection link: " + rightColumnCtaLinkAndLabel.getAttribute("href"));
			softAssert.assertFalse(rightColumnCtaLinkAndLabel.getAttribute("href").isEmpty());

			String expLink = rightColumnCtaLinkAndLabel.getAttribute("href");

			String handle = mydriver.getWindowHandle();

			rightColumnCtaLinkAndLabel.click();

			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

			softAssert.assertAll();


	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image-plus"})
	public void leftColumnCtaCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, middleImageSection, logger);

			try {
				leftColumnCtaLinkAndLabel.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("left CTA is not available");
			}
			customTestLogs.get().add(
					"Is CTA button visible: " + verifyElementExists(logger, leftColumnCtaLinkAndLabel, "left CTA"));
			softAssert.assertTrue(verifyElementExists(logger, leftColumnCtaLinkAndLabel, "left CTA"));

			customTestLogs.get().add("Cta Label: " + leftColumnCtaLinkAndLabel.getAttribute("innerText"));
			softAssert.assertFalse(leftColumnCtaLinkAndLabel.getAttribute("innerText").isEmpty());

			customTestLogs.get().add("redirection link: " + leftColumnCtaLinkAndLabel.getAttribute("href"));
			softAssert.assertFalse(leftColumnCtaLinkAndLabel.getAttribute("href").isEmpty());

			String expLink = leftColumnCtaLinkAndLabel.getAttribute("href");

			String handle = mydriver.getWindowHandle();

			leftColumnCtaLinkAndLabel.click();

			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

			softAssert.assertAll();


	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image-plus"})
	public void leftColumnStrongCopyCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, middleImageSection, logger);

			try {
				leftColumnStrongCopy.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("left strong copy field is not available");
			}
			
			customTestLogs.get().add("Is strog copy field visible: "+verifyElementExists(logger, leftColumnStrongCopy, "left strong copy"));
			softAssert.assertTrue(verifyElementExists(logger, leftColumnStrongCopy, "left strong copy"));
			customTestLogs.get().add("Strong Copy: "+leftColumnStrongCopy.getAttribute("innerText"));
			softAssert.assertFalse(leftColumnStrongCopy.getAttribute("innerText").isEmpty());
			
			softAssert.assertAll();

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image-plus"})
	public void rightColumnStrongCopyCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, middleImageSection, logger);

			try {
				rightColumnStrongCopy.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("right strong copy field is not available");
			}
			
			customTestLogs.get().add("Is strog copy field visible: "+verifyElementExists(logger, rightColumnStrongCopy, "right strong copy"));
			softAssert.assertTrue(verifyElementExists(logger, rightColumnStrongCopy, "right strong copy"));
			customTestLogs.get().add("Strong Copy: "+rightColumnStrongCopy.getAttribute("innerText"));
			softAssert.assertFalse(rightColumnStrongCopy.getAttribute("innerText").isEmpty());
			
			softAssert.assertAll();

	}

	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image-plus"})
	public void centralSubHeadingCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, middleImageSection, logger);

			try {
				centralSubHeading.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("central sub heading field is not available");
			}
			
			customTestLogs.get().add("Is central sub heading field visible: "+verifyElementExists(logger, centralSubHeading, "central sub heading"));
			softAssert.assertTrue(verifyElementExists(logger, centralSubHeading, "central sub heading "));
			customTestLogs.get().add("central sub heading: "+centralSubHeading.getAttribute("innerText"));
			softAssert.assertFalse(centralSubHeading.getAttribute("innerText").isEmpty());
			
			softAssert.assertAll();

	}


	@Test(priority = 7, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image-plus"})
	public void centralImageVisibilityCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, middleImageSection, logger);

			try {
				centralImage.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("central image field is not available");
			}
			
			customTestLogs.get().add("Is central image visible: "+verifyElementExists(logger, centralImage, "central image"));
			softAssert.assertTrue(verifyElementExists(logger, centralImage, "central image"));
			customTestLogs.get().add("Image: "+centralImage.getAttribute("src"));
			softAssert.assertFalse(centralImage.getAttribute("src").isEmpty());
			
			softAssert.assertAll();

	}


	
	@Test(priority = 8, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image-plus"})
	public void leftColumnImageVisibilityCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, middleImageSection, logger);

			try {
				leftColumnImage.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("left column image field is not available");
			}
			
			customTestLogs.get().add("Is left column image visible: "+verifyElementExists(logger, leftColumnImage, "left column image"));
			softAssert.assertTrue(verifyElementExists(logger, leftColumnImage, "left column image"));
			customTestLogs.get().add("Image: "+leftColumnImage.getAttribute("src"));
			softAssert.assertFalse(leftColumnImage.getAttribute("src").isEmpty());
			
			softAssert.assertAll();

	}
	
	
	@Test(priority = 9, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image-plus"})
	public void rightColumnImageVisibilityCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrollToElement(mydriver, middleImageSection, logger);

			try {
				rightColumnImage.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("right column image field is not available");
			}
			
			customTestLogs.get().add("Is right column image visible: "+verifyElementExists(logger, rightColumnImage, "right column image"));
			softAssert.assertTrue(verifyElementExists(logger, rightColumnImage, "right column image"));
			customTestLogs.get().add("Image: "+rightColumnImage.getAttribute("src"));
			softAssert.assertFalse(rightColumnImage.getAttribute("src").isEmpty());
			
			softAssert.assertAll();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
