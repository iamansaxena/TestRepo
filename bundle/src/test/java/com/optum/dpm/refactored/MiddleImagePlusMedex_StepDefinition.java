package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.MiddleImagePlusMedex_page;

import core.CustomDataProvider;

public class MiddleImagePlusMedex_StepDefinition extends MiddleImagePlusMedex_page {
	
	private static final Logger logger = LogManager.getLogger(MiddleImagePlusMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image-plus"})
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

			
			mydriver.get(url);
			scrollToElement(mydriver, middleImageSection, logger);
			getTest().info("Is central headline is available: "
					+ verifyElementExists(logger, centralHeading, "Central Heading"));
			softAssert.assertTrue(verifyElementExists(logger, centralHeading, "Central Heading"));

			getTest().info("Central headline: " + centralHeading.getAttribute("innerText"));
			softAssert.assertFalse(centralHeading.getAttribute("innerText").isEmpty());

			getTest().info("Is Left headline is available: "
					+ verifyElementExists(logger, leftColumnHeading, "Left Column Heading"));
			softAssert.assertTrue(verifyElementExists(logger, leftColumnHeading, "Left Column Heading"));

			getTest().info("Left headline: " + leftColumnHeading.getAttribute("innerText"));
			softAssert.assertFalse(leftColumnHeading.getAttribute("innerText").isEmpty());

			getTest().info("Left copy: " + leftColumnCopy.getAttribute("innerText"));
			softAssert.assertFalse(leftColumnCopy.getAttribute("innerText").isEmpty());

			getTest().info("Is Right headline is available: "
					+ verifyElementExists(logger, rightColumnHeading, "Right Column Heading"));
			softAssert.assertTrue(verifyElementExists(logger, rightColumnHeading, "Right Column Heading"));

			getTest().info("Right headline: " + rightColumnHeading.getAttribute("innerText"));
			softAssert.assertFalse(rightColumnHeading.getAttribute("innerText").isEmpty());

			getTest().info("Right copy: " + rightColumnCopy.getAttribute("innerText"));
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
			getTest().info(
					"Is CTA button visible: " + verifyElementExists(logger, rightColumnCtaLinkAndLabel, "Right CTA"));
			softAssert.assertTrue(verifyElementExists(logger, rightColumnCtaLinkAndLabel, "Right CTA"));

			getTest().info("Cta Label: " + rightColumnCtaLinkAndLabel.getAttribute("innerText"));
			softAssert.assertFalse(rightColumnCtaLinkAndLabel.getAttribute("innerText").isEmpty());

			getTest().info("redirection link: " + rightColumnCtaLinkAndLabel.getAttribute("href"));
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
			getTest().info(
					"Is CTA button visible: " + verifyElementExists(logger, leftColumnCtaLinkAndLabel, "left CTA"));
			softAssert.assertTrue(verifyElementExists(logger, leftColumnCtaLinkAndLabel, "left CTA"));

			getTest().info("Cta Label: " + leftColumnCtaLinkAndLabel.getAttribute("innerText"));
			softAssert.assertFalse(leftColumnCtaLinkAndLabel.getAttribute("innerText").isEmpty());

			getTest().info("redirection link: " + leftColumnCtaLinkAndLabel.getAttribute("href"));
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
			
			getTest().info("Is strog copy field visible: "+verifyElementExists(logger, leftColumnStrongCopy, "left strong copy"));
			softAssert.assertTrue(verifyElementExists(logger, leftColumnStrongCopy, "left strong copy"));
			getTest().info("Strong Copy: "+leftColumnStrongCopy.getAttribute("innerText"));
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
			
			getTest().info("Is strog copy field visible: "+verifyElementExists(logger, rightColumnStrongCopy, "right strong copy"));
			softAssert.assertTrue(verifyElementExists(logger, rightColumnStrongCopy, "right strong copy"));
			getTest().info("Strong Copy: "+rightColumnStrongCopy.getAttribute("innerText"));
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
			
			getTest().info("Is central sub heading field visible: "+verifyElementExists(logger, centralSubHeading, "central sub heading"));
			softAssert.assertTrue(verifyElementExists(logger, centralSubHeading, "central sub heading "));
			getTest().info("central sub heading: "+centralSubHeading.getAttribute("innerText"));
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
			
			getTest().info("Is central image visible: "+verifyElementExists(logger, centralImage, "central image"));
			softAssert.assertTrue(verifyElementExists(logger, centralImage, "central image"));
			getTest().info("Image: "+centralImage.getAttribute("src"));
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
			
			getTest().info("Is left column image visible: "+verifyElementExists(logger, leftColumnImage, "left column image"));
			softAssert.assertTrue(verifyElementExists(logger, leftColumnImage, "left column image"));
			getTest().info("Image: "+leftColumnImage.getAttribute("src"));
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
			
			getTest().info("Is right column image visible: "+verifyElementExists(logger, rightColumnImage, "right column image"));
			softAssert.assertTrue(verifyElementExists(logger, rightColumnImage, "right column image"));
			getTest().info("Image: "+rightColumnImage.getAttribute("src"));
			softAssert.assertFalse(rightColumnImage.getAttribute("src").isEmpty());
			
			softAssert.assertAll();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
