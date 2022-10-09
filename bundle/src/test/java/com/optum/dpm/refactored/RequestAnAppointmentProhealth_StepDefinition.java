package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.jsWaitForPageToLoad;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.RequestAnAppointmentProhealth_page;

import core.CustomDataProvider;

public class RequestAnAppointmentProhealth_StepDefinition extends RequestAnAppointmentProhealth_page {
	
	private static final Logger logger = LogManager.getLogger(RequestAnAppointmentProhealth_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"request-an-appointment"})
	public void defaultFieldsVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			jsWaitForPageToLoad(50, mydriver, logger);
			switchToForm();
			scrollToElement(mydriver, submitButton, logger).click();
			ifFormFieldAvailableAndVisible(firstNameInputField, logger, getTest(), "first name");
			ifFormFieldAvailableAndVisible(lastNameInputField, logger, getTest(), "last name");
			ifFormFieldAvailableAndVisible(birthDateInputField, logger, getTest(), "birth date");
			ifFormFieldAvailableAndVisible(phoneNumberInputField, logger, getTest(), "phone number");
			ifFormFieldAvailableAndVisible(bestTimeSelectField, logger, getTest(), "best time");
			ifFormFieldAvailableAndVisible(providerLastNameInputField, logger, getTest(), "provider last name");
			ifFormFieldAvailableAndVisible(patientStatusSelectField, logger, getTest(), "patient status");
			ifFormFieldAvailableAndVisible(preferredDaySelectField, logger, getTest(), "preferred day");
			ifFormFieldAvailableAndVisible(preferredTimeSelectField, logger, getTest(), "preferred time");
			ifFormFieldAvailableAndVisible(reasonInputField, logger, getTest(), "reason");
			ifFormFieldAvailableAndVisible(agreeCheckBoxField, logger, getTest(), "agreement checkbox");
			ifFormFieldAvailableAndVisible(resetButton, logger, getTest(), "reset button");
			ifFormFieldAvailableAndVisible(submitButton, logger, getTest(), "submit button");
			
			
	}
	
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"request-an-appointment"})
	public void errorMessageVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			jsWaitForPageToLoad(50, mydriver, logger);
			switchToForm();
			scrollToElement(mydriver, submitButton, logger).click();
			isErrorMessageAvailable(getTest(),firstNameInputField,logger);
			isErrorMessageAvailable(getTest(),lastNameInputField,logger);
			isErrorMessageAvailable(getTest(),birthDateInputField,logger);
			isErrorMessageAvailable(getTest(),phoneNumberInputField,logger);
			isErrorMessageAvailable(getTest(),bestTimeSelectField,logger);
			isErrorMessageAvailable(getTest(),providerLastNameInputField,logger);
			isErrorMessageAvailable(getTest(),patientStatusSelectField,logger);
			isErrorMessageAvailable(getTest(),preferredDaySelectField,logger);
			isErrorMessageAvailable(getTest(),preferredTimeSelectField,logger);
			isErrorMessageAvailable(getTest(),reasonInputField,logger);
			isErrorMessageAvailable(getTest(),agreeCheckBoxField,logger);
			
			
	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"request-an-appointment"})
	public void invalidFormInputValidationCheck(String url) {
		skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			jsWaitForPageToLoad(50, mydriver, logger);
			switchToForm();
			fillForm(true, getTest());
			
	}
	
	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"request-an-appointment"})
	public void validFormInputValidationCheck(String url) {
		skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			jsWaitForPageToLoad(50, mydriver, logger);
			switchToForm();
			fillForm(false, getTest());
			
	}
}