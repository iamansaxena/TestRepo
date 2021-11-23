package componentStepDef;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ErStats_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ErStats_StepDefinition extends ErStats_page {

	private String author = "Prateek Srivastava";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(ErStats_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ErStats_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new ErStats_page();

		ExtentTestManager.startTest(ErStats_StepDefinition.class.getName());

		setTagForTestClass("ErStats", author, ErStats_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ErStats_StepDefinition.class);
		logger.info("Urls for '" + ErStats_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ErStats_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ErStats_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + ErStats_StepDefinition.class.getName() + "' =>\n" + driverMap);
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

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"er-stats" })
	public void elementFieldCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		scrollToElement(mydriver, erStatTopSection, logger);
		if (isFieldAvailable(ModuleTitle,logger))
		{
			customTestLogs.get().add("Verifying if Module Title visible: "+ModuleTitle.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, ModuleTitle,"ModuleTITLE"));
			customTestLogs.get().add("Verifying if Module Title blank: "+ModuleTitle.getText().isEmpty());
			softAssert.assertFalse(ModuleTitle.getText().isEmpty());

		}
		if (isFieldAvailable(StatIntroCopy,logger))
		{
			customTestLogs.get().add("Verifying if Stat Intro visible: "+StatIntroCopy.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, StatIntroCopy,"StatIntroCOPY"));
			customTestLogs.get().add("Verifying if Stat Intro blank: "+StatIntroCopy.getText().isEmpty());
			softAssert.assertFalse(StatIntroCopy.getText().isEmpty());

		}
		if (isFieldAvailable(StatSubHeading,logger))
		{	
			customTestLogs.get().add("Verifying if Stat Sub Heading visible: "+StatSubHeading.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, StatSubHeading,"StatSubHEADING"));
			customTestLogs.get().add("Verifying if Stat Sub Heading blank: "+StatSubHeading.getText().isEmpty());
			softAssert.assertFalse(StatSubHeading.getText().isEmpty());

		}
		if (isFieldAvailable(StatSubCopy,logger))
		{
			customTestLogs.get().add("Verifying if Stat Sub Copy visible: "+StatSubCopy.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, StatSubCopy,"StatSubCOPY"));
			customTestLogs.get().add("Verifying if Stat Sub Copy blank: "+StatSubCopy.getText().isEmpty());
			softAssert.assertFalse(StatSubCopy.getText().isEmpty());

		}
		if (isFieldAvailable(StatSubheadingOne,logger))
		{
			customTestLogs.get().add("Verifying if Stat Sub Heading visible: "+StatSubheadingOne.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, StatSubheadingOne,"StatSubheadingONE"));
			customTestLogs.get().add("Verifying if Stat Sub Heading blank: "+StatSubheadingOne.getText().isEmpty());
			softAssert.assertFalse(StatSubheadingOne.getText().isEmpty());

		}
		if (isFieldAvailable(ColorCopyBold,logger))
		{
			customTestLogs.get().add("Verifying if Color Copy Bold visible: "+ColorCopyBold.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, ColorCopyBold,"ColorCopyBOLD"));
			customTestLogs.get().add("Verifying if Color Copy Bold blank: "+ColorCopyBold.getText().isEmpty());
			softAssert.assertFalse(ColorCopyBold.getText().isEmpty());

		}
		if (isFieldAvailable(ColorCopy,logger))
		{
			customTestLogs.get().add("Verifying if Color Copy visible: "+ColorCopy.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, ColorCopy,"ColorCOPY"));
			customTestLogs.get().add("Verifying if Color Copy blank: "+ColorCopy.getText().isEmpty());
			softAssert.assertFalse(ColorCopy.getText().isEmpty());

		}
		if (isFieldAvailable(Image,logger))
		{
			customTestLogs.get().add("Verifying if Image visible: "+Image.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, Image,"IMAGE"));
			customTestLogs.get().add("Verifying if Image blank: "+Image.getAttribute("src").isEmpty());
			softAssert.assertFalse(Image.getAttribute("src").isEmpty());

		}
		if (isFieldAvailable(Reference,logger))
		{
			customTestLogs.get().add("Verifying if Reference visible: "+Reference.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, Reference,"REFERENCE"));
			customTestLogs.get().add("Verifying if Reference blank: "+Reference.getText().isEmpty());
			softAssert.assertFalse(Reference.getText().isEmpty());

		}

         softAssert.assertAll();	

	}
}
