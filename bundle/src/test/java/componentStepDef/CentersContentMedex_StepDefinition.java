package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.CentersContentMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class CentersContentMedex_StepDefinition extends CentersContentMedex_page {

	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(CentersContentMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(CentersContentMedex_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new CentersContentMedex_page();
		ExtentTestManager.startTest(CentersContentMedex_StepDefinition.class.getName());
		setTagForTestClass("Centers Content [Medex]", author, CentersContentMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(CentersContentMedex_StepDefinition.class);
		logger.info("Urls for '" + CentersContentMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(CentersContentMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(CentersContentMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + CentersContentMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
			"centers-content" })
	public void defaultCopyFieldVisibilityCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, contentSection, logger);
		customTestLogs.get().add("Is branded copy field available: " + brandedCopy.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, brandedCopy, "Branded copy field"));
		customTestLogs.get().add("Branded Copy: " + brandedCopy.getText());
		hardAssert.assertFalse(brandedCopy.getText().isEmpty());
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-content" })
	public void backgroundFieldAvailabilityCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, contentSection, logger);
		customTestLogs.get().add("Is Image Field is available: " + sectionImage.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionImage, "Section Image"));

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-content" })
	public void brandedLinkLabelAndRedirectionCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, contentSection, logger);
		try {
			brandedHyperlink.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Hyperlink not authored");
		}
		customTestLogs.get().add("Is Image Field is available: " + brandedHyperlink.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, brandedHyperlink, "Branded hyperLink and Label"));
		customTestLogs.get().add("Hyperlink: " + brandedHyperlink.getAttribute("href"));
		hardAssert.assertFalse(brandedHyperlink.getAttribute("href").isEmpty());
		customTestLogs.get().add("Label: " + brandedHyperlink.getAttribute("innerText"));
		hardAssert.assertFalse(brandedHyperlink.getAttribute("innerText").isEmpty());
		String expLink = brandedHyperlink.getAttribute("href");
		brandedHyperlink.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, mydriver.getWindowHandle());

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-content" })
	public void internalTextComponentCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, contentSection, logger);
		try {
			sectionTextComponent.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Hyperlink not authored");
		}
		customTestLogs.get().add("Is Text content field available: " + sectionTextComponentContent.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionTextComponentContent, "Text content field"));
		customTestLogs.get().add("Content: " + sectionTextComponentContent.getText());
		hardAssert.assertFalse(sectionTextComponentContent.getText().isEmpty());
	}

}
