package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.getWebDriverWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.AskForAReferral_page;

import core.CustomDataProvider;

public class AskForAReferral_StepDefinition extends AskForAReferral_page {
	
	private static final Logger logger = LogManager.getLogger(AskForAReferral_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"ask-for-referral" })
	public void elementVisibilityCheck(String url) {
		
		mydriver.get(url);
		hardAssert.assertEquals(verifyElements(mydriver, logger), true);
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"ask-for-referral" })
	public void verifyFormSubmission(String url) {
		
		mydriver.get(url);
		String[] values = { "Name1", "Name2", "1/9/1992", "987-654-3210", "hello@gmail.com", "hello@gmail.com",
				"D'Esposito, Robert F., MD", "doctor", "Pulmonologist", "insurance", "current", "Sickness" };
		hardAssert.assertEquals(enterValuesintheForm(mydriver,values, logger), true);
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"ask-for-referral" })
	public void verifyErrorMessage(String url) {
		
		mydriver.get(url);
		hardAssert.assertEquals(verifyErrorMessagebySubmittingEmptyFields(mydriver,logger), true);
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"ask-for-referral" })
	public void verifyFormat(String url) {
		
		mydriver.get(url);
		getWebDriverWait(mydriver, 50).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='aemFormFrame']")));
		hardAssert.assertEquals(verifyFormat(mydriver,"13/12/1991", "Birth Date (m/d/yyyy) *", "DOB",
				"Field not filled in expected format", logger), true);
		hardAssert.assertEquals(verifyFormat(mydriver,"9876543210", "Phone Number (xxx-xxx-xxxx) *", "Phone Number",
				"Phone Number format is invalid.", logger), true);
		hardAssert.assertEquals(verifyFormat(mydriver,"mail@mail", "Email Address *", "Email", "Email Address format is invalid", logger), true);
		hardAssert.assertEquals(verifyFormat(mydriver,"mail@mail", "Re-enter Email Address *", "ReEnter Email",
				"Re-enter Email Address format is invalid", logger), true);
		hardAssert.assertEquals(verifyDatePickerFormat(mydriver,"Birth Date (m/d/yyyy) *,Appointment Date (m/d/yyyy) *",
				"previous,future", logger), true);
	}

}
