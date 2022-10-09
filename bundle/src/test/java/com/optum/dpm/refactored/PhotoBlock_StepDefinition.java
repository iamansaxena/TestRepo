package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.PhotoBlock_page;

import core.CustomDataProvider;

public class PhotoBlock_StepDefinition extends PhotoBlock_page {

	private static final Logger logger = LogManager.getLogger(PhotoBlock_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"photo-block" })
	public void elementVisibilityCheckForSectionOne(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, photoblockSection, logger);
		if (isSectionAvailable(blockOneSection, logger)) {
			if (ifVisibleAssertTrue(blockOneSectionContentHeadline)) {
				softAssert.assertTrue(
						verifyElementExists(logger, blockOneSectionContentHeadline, "Section 1 Content heading"));
				softAssert.assertFalse(blockOneSectionContentHeadline.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockOneSectionCopy)) {
				softAssert.assertTrue(verifyElementExists(logger, blockOneSectionCopy, "Section 1 Copy"));
				softAssert.assertFalse(blockOneSectionCopy.getText().isEmpty());
			}

			if (ifVisibleAssertTrue(blockOneSectionHeading)) {
				softAssert.assertTrue(verifyElementExists(logger, blockOneSectionHeading, "Section 1 Heading"));
				softAssert.assertFalse(blockOneSectionHeading.getText().isEmpty());
			}

			if (ifVisibleAssertTrue(blockOneSectionLink)) {
				softAssert.assertTrue(verifyElementExists(logger, blockOneSectionLink, "Section 1 Link"));
				softAssert.assertFalse(blockOneSectionLink.getAttribute("href").isEmpty());
			}
			softAssert.assertAll();
		} else {
			throw new SkipException("Section one is not authored");
		}

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"photo-block" })
	public void elementVisibilityCheckForSectionTwo(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, photoblockSection, logger);
		if (isSectionAvailable(blockTwoSection, logger)) {

			if (ifVisibleAssertTrue(blockTwo2_0_ContentHeadline)) {
				softAssert.assertTrue(
						verifyElementExists(logger, blockTwo2_0_ContentHeadline, "Section 2.0 Content heading"));
				softAssert.assertFalse(blockTwo2_0_ContentHeadline.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockTwo2_0_Copy)) {
				softAssert.assertTrue(verifyElementExists(logger, blockTwo2_0_Copy, "Section 2.0 Copy"));
				softAssert.assertFalse(blockTwo2_0_Copy.getText().isEmpty());
			}

			if (ifVisibleAssertTrue(blockTwo2_0_Headline)) {
				softAssert.assertTrue(verifyElementExists(logger, blockTwo2_0_Headline, "Section 2.0 Heading"));
				softAssert.assertFalse(blockTwo2_0_Headline.getText().isEmpty());
			}

			if (ifVisibleAssertTrue(blockTwo2_0_Link)) {
				softAssert.assertTrue(verifyElementExists(logger, blockTwo2_0_Link, "Section 2.0 Link"));
				softAssert.assertFalse(blockTwo2_0_Link.getAttribute("href").isEmpty());
			}

			if (ifVisibleAssertTrue(blockTwo2_1_ContentHeadline)) {
				softAssert.assertTrue(
						verifyElementExists(logger, blockTwo2_1_ContentHeadline, "Section 2.1 Content Headline"));
				softAssert.assertFalse(blockTwo2_1_ContentHeadline.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockTwo2_1_Copy)) {
				softAssert.assertTrue(verifyElementExists(logger, blockTwo2_1_Copy, "Section 2.1 Copy"));
				softAssert.assertFalse(blockTwo2_1_Copy.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockTwo2_1_Headline)) {
				softAssert.assertTrue(verifyElementExists(logger, blockTwo2_1_Headline, "Section 2.1 Headline"));
				softAssert.assertFalse(blockTwo2_1_Headline.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockTwo2_1_Link)) {
				softAssert.assertTrue(verifyElementExists(logger, blockTwo2_1_Link, "Section 2.1 link"));
				softAssert.assertFalse(blockTwo2_1_Link.getAttribute("href").isEmpty());
			}

			softAssert.assertAll();
		} else {
			throw new SkipException("Section one is not authored");
		}
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"photo-block" })
	public void elementVisibilityCheckForSectionThree(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, photoblockSection, logger);
		if (isSectionAvailable(blockThreeSection, logger)) {
			if (ifVisibleAssertTrue(blockThree3_0_ContentHeadline)) {
				softAssert.assertTrue(
						verifyElementExists(logger, blockThree3_0_ContentHeadline, "Section 3.0 Content heading"));
				softAssert.assertFalse(blockThree3_0_ContentHeadline.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockThree3_0_Copy)) {
				softAssert.assertTrue(verifyElementExists(logger, blockThree3_0_Copy, "Section 3.0 Copy"));
				softAssert.assertFalse(blockThree3_0_Copy.getText().isEmpty());
			}

			if (ifVisibleAssertTrue(blockThree3_0_Headline)) {
				softAssert.assertTrue(verifyElementExists(logger, blockThree3_0_Headline, "Section 3.0 Heading"));
				softAssert.assertFalse(blockThree3_0_Headline.getText().isEmpty());
			}

			if (ifVisibleAssertTrue(blockThree3_0_Link)) {
				softAssert.assertTrue(verifyElementExists(logger, blockThree3_0_Link, "Section 3.0 Link"));
				softAssert.assertFalse(blockThree3_0_Link.getAttribute("href").isEmpty());
			}

			if (ifVisibleAssertTrue(blockThree3_1_ContentHeadline)) {
				softAssert.assertTrue(
						verifyElementExists(logger, blockThree3_1_ContentHeadline, "Section 3.1 Content Headline"));
				softAssert.assertFalse(blockThree3_1_ContentHeadline.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockThree3_1_Copy)) {
				softAssert.assertTrue(verifyElementExists(logger, blockThree3_1_Copy, "Section 3.1 Copy"));
				softAssert.assertFalse(blockThree3_1_Copy.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockThree3_1_Headline)) {
				softAssert.assertTrue(verifyElementExists(logger, blockThree3_1_Headline, "Section 3.1 Headline"));
				softAssert.assertFalse(blockThree3_1_Headline.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockThree3_1_Link)) {
				softAssert.assertTrue(verifyElementExists(logger, blockThree3_1_Link, "Section 3.1 link"));
				softAssert.assertFalse(blockThree3_1_Link.getAttribute("href").isEmpty());
			}

			if (ifVisibleAssertTrue(blockThree3_2_ContentHeadline)) {
				softAssert.assertTrue(
						verifyElementExists(logger, blockThree3_2_ContentHeadline, "Section 3.2 Content Headline"));
				softAssert.assertFalse(blockThree3_2_ContentHeadline.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockThree3_2_Copy)) {
				softAssert.assertTrue(verifyElementExists(logger, blockThree3_2_Copy, "Section 3.2 Copy"));
				softAssert.assertFalse(blockThree3_2_Copy.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockThree3_2_Headline)) {
				softAssert.assertTrue(verifyElementExists(logger, blockThree3_2_Headline, "Section 3.2 Headline"));
				softAssert.assertFalse(blockThree3_2_Headline.getText().isEmpty());
			}
			if (ifVisibleAssertTrue(blockThree3_2_Link)) {
				softAssert.assertTrue(verifyElementExists(logger, blockThree3_2_Link, "Section 3.2 link"));
				softAssert.assertFalse(blockThree3_2_Link.getAttribute("href").isEmpty());
			}

			softAssert.assertAll();
		} else {
			throw new SkipException("Section one is not authored");
		}
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"photo-block" })
	public void redirectionCheckSectionOne(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, photoblockSection, logger);
		if (isSectionAvailable(blockOneSection, logger)) {
			if (ifVisibleAssertTrue(blockOneSectionLink)) {
				scrollToElement(mydriver, blockOneSectionLink, logger);
				String expLink = blockOneSectionLink.getAttribute("href");
				getTest().info("CTA Link (Section 1)" + expLink);
				String handle = mydriver.getWindowHandle();
				blockOneSectionLink.click();
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
			}
		}

	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"photo-block" })
	public void redirectionCheckSectionTwo_0(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, photoblockSection, logger);
		if (isSectionAvailable(blockTwoSection, logger)) {
			if (ifVisibleAssertTrue(blockTwo2_0_Link)) {
				scrollToElement(mydriver, blockTwo2_0_Link, logger);
				String expLink = blockTwo2_0_Link.getAttribute("href");
				getTest().info("CTA Link (Section 2.0)" + expLink);
				String handle = mydriver.getWindowHandle();
				blockOneSectionLink.click();
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
			}
		}

	}

	@Test(priority = 6, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"photo-block" })
	public void redirectionCheckSectionTwo_1(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, photoblockSection, logger);
		if (isSectionAvailable(blockTwoSection, logger)) {
			if (ifVisibleAssertTrue(blockTwo2_1_Link)) {
				scrollToElement(mydriver, blockTwo2_1_Link, logger);
				String expLink = blockTwo2_1_Link.getAttribute("href");
				getTest().info("CTA Link (Section 2.1)" + expLink);
				String handle = mydriver.getWindowHandle();
				blockOneSectionLink.click();
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
			}
		}

	}

	@Test(priority = 7, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"photo-block" })
	public void redirectionCheckSectionThree_0(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, photoblockSection, logger);
		if (isSectionAvailable(blockThreeSection, logger)) {
			if (ifVisibleAssertTrue(blockThree3_0_Link)) {
				scrollToElement(mydriver, blockThree3_0_Link, logger);
				String expLink = blockThree3_0_Link.getAttribute("href");
				getTest().info("CTA Link (Section 3.0)" + expLink);
				String handle = mydriver.getWindowHandle();
				blockOneSectionLink.click();
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
			}
		}

	}

	@Test(priority = 8, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"photo-block" })
	public void redirectionCheckSectionThree_1(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, photoblockSection, logger);
		if (isSectionAvailable(blockThreeSection, logger)) {
			if (ifVisibleAssertTrue(blockThree3_1_Link)) {
				scrollToElement(mydriver, blockThree3_1_Link, logger);
				String expLink = blockThree3_1_Link.getAttribute("href");
				getTest().info("CTA Link (Section 3.1)" + expLink);
				String handle = mydriver.getWindowHandle();
				blockOneSectionLink.click();
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
			}
		}

	}

	@Test(priority = 9, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"photo-block" })
	public void redirectionCheckSectionThree_2(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, photoblockSection, logger);
		if (isSectionAvailable(blockThreeSection, logger)) {
			if (ifVisibleAssertTrue(blockThree3_2_Link)) {
				scrollToElement(mydriver, blockThree3_2_Link, logger);
				String expLink = blockThree3_2_Link.getAttribute("href");
				getTest().info("CTA Link (Section 3.2)" + expLink);
				String handle = mydriver.getWindowHandle();
				blockOneSectionLink.click();
				assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
			}
		}

	}

}
