package com.optum.dpm.refactored;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.AskForRefill_page;

import core.CustomDataProvider;

public class AskForRefill_StepDefinition extends AskForRefill_page {
	
	private static final Logger logger = LogManager.getLogger(AskForRefill_StepDefinition.class);

	@Test(priority = 1, enabled = true,  dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"ask-for-refill"})
	public void elementVisibilityCheck(String cardUrl) {
			
			mydriver.get(cardUrl);
			hardAssert.assertEquals(verifyElements(mydriver, logger), true);
	}
	
	@Test(priority = 2, enabled = true,  dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"ask-for-refill"})
	public void verifyFormSubmission(String cardUrl) {
			
			mydriver.get(cardUrl);
			String[] values = {"Name1" , "Name2", "1/9/1992", "987-654-3210", "hello@gmail.com","hello@gmail.com" , "D'Esposito, Robert F., MD", "Medication", "Dosage", "3","Sickness" };		
			hardAssert.assertEquals(enterValuesintheForm(mydriver,values,logger), true);
	}
	
	@Test(priority = 3, enabled = true,  dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"ask-for-refill"})
	public void verifyErrorMessage(String cardUrl) {
			
			mydriver.get(cardUrl);			
			hardAssert.assertEquals(verifyErrorMessagebySubmittingEmptyFields(mydriver,logger), true);
	}
	
	@Test(priority = 4, enabled = true,  dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"ask-for-refill"})
	public void verifyFormat(String cardUrl) {
			
			mydriver.get(cardUrl);	
			mydriver.switchTo().frame("aemFormFrame");
			hardAssert.assertEquals(verifyFormat(mydriver,"13/12/1991","Birth Date (m/d/yyyy) *", "DOB","Field not filled in expected format", logger), true);
			hardAssert.assertEquals(verifyFormat(mydriver,"9876543210","Phone Number (xxx-xxx-xxxx) *", "Phone Number","Phone Number format is invalid.", logger), true);
			hardAssert.assertEquals(verifyFormat(mydriver,"mail@mail","Email Address *", "Email","Email Address format is invalid", logger), true);
			hardAssert.assertEquals(verifyFormat(mydriver,"mail@mail","Re-enter Email Address *", "ReEnter Email","Re-enter Email Address format is invalid", logger), true);
			hardAssert.assertEquals(verifyDatePickerFormat(mydriver,"Birth Date (m/d/yyyy) *","previous",  logger), true);
	}	
	
}
