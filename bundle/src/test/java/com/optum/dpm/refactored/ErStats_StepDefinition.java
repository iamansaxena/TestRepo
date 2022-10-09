package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import java.util.ArrayList;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ErStats_page;

import core.CustomDataProvider;

public class ErStats_StepDefinition extends ErStats_page {

	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static final Logger logger = LogManager.getLogger(ErStats_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-stats" })
	public void elementFieldCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		scrollToElement(mydriver, erStatTopSection, logger);
		if (isFieldAvailable(ModuleTitle,logger))
		{
			getTest().info("Verifying if Module Title visible: "+ModuleTitle.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, ModuleTitle,"ModuleTITLE"));
			getTest().info("Verifying if Module Title blank: "+ModuleTitle.getText().isEmpty());
			softAssert.assertFalse(ModuleTitle.getText().isEmpty());

		}
		if (isFieldAvailable(StatIntroCopy,logger))
		{
			getTest().info("Verifying if Stat Intro visible: "+StatIntroCopy.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, StatIntroCopy,"StatIntroCOPY"));
			getTest().info("Verifying if Stat Intro blank: "+StatIntroCopy.getText().isEmpty());
			softAssert.assertFalse(StatIntroCopy.getText().isEmpty());

		}
		if (isFieldAvailable(StatSubHeading,logger))
		{	
			getTest().info("Verifying if Stat Sub Heading visible: "+StatSubHeading.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, StatSubHeading,"StatSubHEADING"));
			getTest().info("Verifying if Stat Sub Heading blank: "+StatSubHeading.getText().isEmpty());
			softAssert.assertFalse(StatSubHeading.getText().isEmpty());

		}
		if (isFieldAvailable(StatSubCopy,logger))
		{
			getTest().info("Verifying if Stat Sub Copy visible: "+StatSubCopy.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, StatSubCopy,"StatSubCOPY"));
			getTest().info("Verifying if Stat Sub Copy blank: "+StatSubCopy.getText().isEmpty());
			softAssert.assertFalse(StatSubCopy.getText().isEmpty());

		}
		if (isFieldAvailable(StatSubheadingOne,logger))
		{
			getTest().info("Verifying if Stat Sub Heading visible: "+StatSubheadingOne.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, StatSubheadingOne,"StatSubheadingONE"));
			getTest().info("Verifying if Stat Sub Heading blank: "+StatSubheadingOne.getText().isEmpty());
			softAssert.assertFalse(StatSubheadingOne.getText().isEmpty());

		}
		if (isFieldAvailable(ColorCopyBold,logger))
		{
			getTest().info("Verifying if Color Copy Bold visible: "+ColorCopyBold.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, ColorCopyBold,"ColorCopyBOLD"));
			getTest().info("Verifying if Color Copy Bold blank: "+ColorCopyBold.getText().isEmpty());
			softAssert.assertFalse(ColorCopyBold.getText().isEmpty());

		}
		if (isFieldAvailable(ColorCopy,logger))
		{
			getTest().info("Verifying if Color Copy visible: "+ColorCopy.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, ColorCopy,"ColorCOPY"));
			getTest().info("Verifying if Color Copy blank: "+ColorCopy.getText().isEmpty());
			softAssert.assertFalse(ColorCopy.getText().isEmpty());

		}
		if (isFieldAvailable(Image,logger))
		{
			getTest().info("Verifying if Image visible: "+Image.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, Image,"IMAGE"));
			getTest().info("Verifying if Image blank: "+Image.getAttribute("src").isEmpty());
			softAssert.assertFalse(Image.getAttribute("src").isEmpty());

		}
		if (isFieldAvailable(Reference,logger))
		{
			getTest().info("Verifying if Reference visible: "+Reference.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, Reference,"REFERENCE"));
			getTest().info("Verifying if Reference blank: "+Reference.getText().isEmpty());
			softAssert.assertFalse(Reference.getText().isEmpty());

		}

         softAssert.assertAll();	

	}
}
