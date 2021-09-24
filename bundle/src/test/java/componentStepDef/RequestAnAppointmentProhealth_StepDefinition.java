package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.RequestAnAppointmentProhealth_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class RequestAnAppointmentProhealth_StepDefinition extends RequestAnAppointmentProhealth_page {
	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(RequestAnAppointmentProhealth_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(RequestAnAppointmentProhealth_StepDefinition.class.getName());
		new RequestAnAppointmentProhealth_page();
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		ExtentTestManager.startTest(RequestAnAppointmentProhealth_StepDefinition.class.getName());
		setTagForTestClass("Request An Appointment [Prohealth]", author, RequestAnAppointmentProhealth_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(RequestAnAppointmentProhealth_StepDefinition.class);
		logger.info(
				"Urls for '" + RequestAnAppointmentProhealth_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(RequestAnAppointmentProhealth_StepDefinition.class.getName(), currentDomain);

		driverMap.put(RequestAnAppointmentProhealth_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + RequestAnAppointmentProhealth_StepDefinition.class.getName() + "' =>\n"
				+ driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		new RequestAnAppointmentProhealth_page();
	}

	
	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"request-an-appointment"})
	public void defaultFieldsVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			jsWaitForPageToLoad(50, mydriver, logger);
			switchToForm();
			scrollToElement(mydriver, submitButton, logger).click();
			ifFormFieldAvailableAndVisible(firstNameInputField, logger, customTestLogs.get(), "first name");
			ifFormFieldAvailableAndVisible(lastNameInputField, logger, customTestLogs.get(), "last name");
			ifFormFieldAvailableAndVisible(birthDateInputField, logger, customTestLogs.get(), "birth date");
			ifFormFieldAvailableAndVisible(phoneNumberInputField, logger, customTestLogs.get(), "phone number");
			ifFormFieldAvailableAndVisible(bestTimeSelectField, logger, customTestLogs.get(), "best time");
			ifFormFieldAvailableAndVisible(providerLastNameInputField, logger, customTestLogs.get(), "provider last name");
			ifFormFieldAvailableAndVisible(patientStatusSelectField, logger, customTestLogs.get(), "patient status");
			ifFormFieldAvailableAndVisible(preferredDaySelectField, logger, customTestLogs.get(), "preferred day");
			ifFormFieldAvailableAndVisible(preferredTimeSelectField, logger, customTestLogs.get(), "preferred time");
			ifFormFieldAvailableAndVisible(reasonInputField, logger, customTestLogs.get(), "reason");
			ifFormFieldAvailableAndVisible(agreeCheckBoxField, logger, customTestLogs.get(), "agreement checkbox");
			ifFormFieldAvailableAndVisible(resetButton, logger, customTestLogs.get(), "reset button");
			ifFormFieldAvailableAndVisible(submitButton, logger, customTestLogs.get(), "submit button");
			
			
	}
	
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"request-an-appointment"})
	public void errorMessageVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			jsWaitForPageToLoad(50, mydriver, logger);
			switchToForm();
			scrollToElement(mydriver, submitButton, logger).click();
			isErrorMessageAvailable(customTestLogs.get(),firstNameInputField,logger);
			isErrorMessageAvailable(customTestLogs.get(),lastNameInputField,logger);
			isErrorMessageAvailable(customTestLogs.get(),birthDateInputField,logger);
			isErrorMessageAvailable(customTestLogs.get(),phoneNumberInputField,logger);
			isErrorMessageAvailable(customTestLogs.get(),bestTimeSelectField,logger);
			isErrorMessageAvailable(customTestLogs.get(),providerLastNameInputField,logger);
			isErrorMessageAvailable(customTestLogs.get(),patientStatusSelectField,logger);
			isErrorMessageAvailable(customTestLogs.get(),preferredDaySelectField,logger);
			isErrorMessageAvailable(customTestLogs.get(),preferredTimeSelectField,logger);
			isErrorMessageAvailable(customTestLogs.get(),reasonInputField,logger);
			isErrorMessageAvailable(customTestLogs.get(),agreeCheckBoxField,logger);
			
			
	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"request-an-appointment"})
	public void invalidFormInputValidationCheck(String url) {
		skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			jsWaitForPageToLoad(50, mydriver, logger);
			switchToForm();
			fillForm(true, customTestLogs.get());
			
	}
	
	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"request-an-appointment"})
	public void validFormInputValidationCheck(String url) {
		skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			jsWaitForPageToLoad(50, mydriver, logger);
			switchToForm();
			fillForm(false, customTestLogs.get());
			
	}
}