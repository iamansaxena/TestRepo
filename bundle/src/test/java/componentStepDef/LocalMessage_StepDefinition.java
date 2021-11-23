package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.LocalMessage_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class LocalMessage_StepDefinition extends LocalMessage_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(LocalMessage_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(LocalMessage_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new LocalMessage_page();
		ExtentTestManager.startTest(LocalMessage_StepDefinition.class.getName());
		setTagForTestClass("Local Message Medex", author, LocalMessage_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(LocalMessage_StepDefinition.class);
		logger.info("Urls for '" + LocalMessage_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(LocalMessage_StepDefinition.class.getName(), currentDomain);

		driverMap.put(LocalMessage_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + LocalMessage_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
			"local-message-hub" })
	public void defaultElementAvailabilityCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		scrollToElement(mydriver, messageSection, logger);
		softAssert.assertTrue(verifyElementExists(logger, stateField, "State Field"));
		customTestLogs.get().add(
				"Checked if 'state field' is available: " + verifyElementExists(logger, stateField, "State Field"));

		softAssert.assertTrue(verifyElementExists(logger, messageHeader, "Message header field"));
		customTestLogs.get().add("Checked if 'message header field' is available: "
				+ verifyElementExists(logger, messageHeader, "Message header field"));
		softAssert.assertTrue(verifyElementExists(logger, plusIcon, "plusIcon"));
		customTestLogs.get().add(
				"Checked if 'plusIcon field' is available: " + verifyElementExists(logger, stateField, "plusIcon"));
		softAssert.assertAll();

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"local-message-hub" })
	public void messageFieldContentCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		scrollToElement(mydriver, messageSection, logger);
		try {
			messageFields.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("The message text field is not authored");
		}
		customTestLogs.get()
				.add("Checking if the message field is empty: " + messageFields.getAttribute("innerText").isEmpty());
		hardAssert.assertFalse(messageFields.getAttribute("innerText").isEmpty());

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"local-message-hub" })
	public void ctaLabelAvailabitiyCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, messageSection, logger);
		try {
			ctaLink.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Can't execute this TC as there is no CTA Link available");
		}
		customTestLogs.get().add("Verifying when CTA link is there then if Cta label is there: "
				+ verifyElementExists(logger, ctaButton, "Cta button"));
		hardAssert.assertTrue(verifyElementExists(logger, ctaButton, "Cta button"));

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"local-message-hub" })
	public void ctaLabelUnavailabitiyCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		scrollToElement(mydriver, messageSection, logger);
		try {
			ctaLink.isDisplayed();
			throw new SkipException("Can't execute this TC as the CTA Link is available");
		} catch (NoSuchElementException e) {
			customTestLogs.get().add("Verifying that if CTA link is not avaialable then if button is visible: "
					+ verifyElementExists(logger, ctaButton, "CTA button"));
			hardAssert.assertFalse(verifyElementExists(logger, ctaButton, "CTA button"));
		}

	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"local-message-hub" })
	public void ctaLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		scrollToElement(mydriver, messageSection, logger);

		try {
			ctaLink.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Can't execute this TC as there is no CTA Link available");
		}
		String expLink = ctaLink.getAttribute("href");
		String handle = mydriver.getWindowHandle();
		ctaButton.click();
		customTestLogs.get().add("User is redirecting to : " + expLink);
		pleaseWait(5, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}
}
