package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.selectByOptionName;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.QuickContact_page;

import core.CustomDataProvider;

public class QuickContact_StepDefinition extends QuickContact_page {

	private static final Logger logger = LogManager.getLogger(QuickContact_StepDefinition.class);
	private String numofemploye ="250-499";
	private String FName ="Asiq";
	private String LName ="John";
	
	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"quick-contact-sf"})
	public void QuickContactVisibilityCheck(String cardUrl) {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrl);


			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(quickapplysection));
			scrollToElement(mydriver, quickapplysection, logger);
			boolean abc = false;
			
				softAssert.assertTrue(quickapplyheading.isDisplayed());
				softAssert.assertTrue(firstname.isDisplayed());
				softAssert.assertTrue(lastname.isDisplayed());
				softAssert.assertTrue(states.isDisplayed());
				softAssert.assertTrue(phone.isDisplayed());			
				softAssert.assertTrue(description.isDisplayed());
				softAssert.assertTrue(mail.isDisplayed());
				softAssert.assertTrue(Numofemp.isDisplayed());
				softAssert.assertTrue(companyname.isDisplayed());
				softAssert.assertTrue(Mandatorymsg.isDisplayed());
				softAssert.assertAll();
				if (abc != ( quickapplyheading.isDisplayed() && firstname.isDisplayed() && lastname.isDisplayed() && states.isDisplayed() && phone.isDisplayed()&& phone.isDisplayed() && description.isDisplayed() && mail.isDisplayed() && Numofemp.isDisplayed() && companyname.isDisplayed() && Mandatorymsg.isDisplayed()))
				{
					abc = true;
				}
				
				
				getTest().info("Verifing the elememt is displayed: "+abc);
			
			

	}
	
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"quick-contact-sf"})
	public void QuickContactCheck(String cardUrl) {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrl);


			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(quickapplysection));
			scrollToElement(mydriver, quickapplysection, logger);			
			firstname.sendKeys(FName);
			lastname.sendKeys(LName);			
			selectByOptionName(logger,states,"Indiana");
			phone.sendKeys("1234567890");
			description.sendKeys("Testing Purpose");
			mail.sendKeys("Test@gmail.com");			
			selectByOptionName(logger,Numofemp,numofemploye);
			//Verifying drop down value are selected correctly  
			Select comboBox1 = new Select(Numofemp);
			String selectedComboValue1 = comboBox1.getFirstSelectedOption().getText();
			softAssert.assertEquals(numofemploye, selectedComboValue1);	
			getTest().info("Checking if prev pager functionality working: " + selectedComboValue1.equals(numofemploye));
			selectByOptionName(logger,industry,"Education");
			//Verifying drop down value are selected correctly
			Select comboBox = new Select(industry);
			String selectedComboValue = comboBox.getFirstSelectedOption().getText();
			softAssert.assertEquals("Education", selectedComboValue);
			companyname.sendKeys("UHG");
			pleaseWait(30,logger);
			sendbtn.click();
			sendbtn.click();			
			WebDriverWait wait2 = new WebDriverWait(mydriver,60);
			wait2.until(ExpectedConditions.visibilityOf(Thankyou));			
			softAssert.assertTrue(Thankyou.isDisplayed());
			getTest().info("Verifing the form is submitted: "+Thankyou.isDisplayed());
			System.out.println(Thankyou.getText());
			softAssert.assertAll();
			

	}
	
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"quick-contact-sf"})
	public void QuickContactmadatoryCheck(String cardUrl) {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrl);


			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(quickapplysection));
			scrollToElement(mydriver, quickapplysection, logger);	
			sendbtn.click();
			softAssert.assertTrue(firstname_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+firstname_error.isDisplayed());
			firstname.sendKeys(FName);
			description.click();			
			softAssert.assertFalse(firstname_error.isDisplayed());
			getTest().info("Verifing then Error message is not displayed: "+firstname_error.isDisplayed());
			softAssert.assertTrue(lastname_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+lastname_error.isDisplayed());
			lastname.sendKeys(LName);
			description.click();
			softAssert.assertFalse(lastname_error.isDisplayed());
			getTest().info("Verifing the Error message is not  displayed: "+lastname_error.isDisplayed());
			softAssert.assertTrue(phone_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+phone_error.isDisplayed());
			softAssert.assertTrue(email_error.isDisplayed());
			getTest().info("Verifing the the Error message is displayed: "+email_error.isDisplayed());
			softAssert.assertTrue(companyname_error.isDisplayed());
			getTest().info("Verifing the  Error message is displayed: "+companyname_error.isDisplayed());
			companyname.sendKeys("UHG");
			description.click();
			softAssert.assertFalse(companyname_error.isDisplayed());
			getTest().info("Verifing the  Error message is not displayed: "+companyname_error.isDisplayed());
			softAssert.assertTrue(description_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+description_error.isDisplayed());
			description.sendKeys("Testing purpose");
			lastname.click();
			softAssert.assertFalse(description_error.isDisplayed());
			getTest().info("Verifing the Error message is not displayed : "+description_error.isDisplayed());
			softAssert.assertAll();

	}
	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"quick-contact-sf"})
	public void EmailfieldCheck(String cardUrl) {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrl);


			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(quickapplysection));
			scrollToElement(mydriver, quickapplysection, logger);
			mail.sendKeys("test@gmail.com");
			description.click();
			softAssert.assertFalse(email_error.isDisplayed());
			getTest().info("Verifing the Error message is not displayed: "+email_error.isDisplayed());
			mail.clear();
			mail.sendKeys("test@gmail.");
			description.click();
			softAssert.assertTrue(email_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+email_error.isDisplayed());
			mail.clear();
			mail.sendKeys("test@");
			description.click();
			softAssert.assertTrue(email_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+email_error.isDisplayed());
			mail.clear();
			mail.sendKeys("!@#.#$%.@#$");
			description.click();
			softAssert.assertTrue(email_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+email_error.isDisplayed());
			softAssert.assertAll();

	}
	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"quick-contact-sf"})
	public void PhonefieldCheck(String cardUrl) {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrl);


			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(quickapplysection));
			scrollToElement(mydriver, quickapplysection, logger);
			phone.sendKeys("12365");
			description.click();
			softAssert.assertTrue(phone_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+phone_error.isDisplayed());
			phone.clear();
			phone.sendKeys("1236547890");
			description.click();
			softAssert.assertFalse(phone_error.isDisplayed());
			getTest().info("Verifing the Error message is not displayed: "+phone_error.isDisplayed());
			phone.clear();
			phone.sendKeys("1236547890789654");
			description.click();
			softAssert.assertTrue(phone_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+phone_error.isDisplayed());
			phone.clear();
			phone.sendKeys("abcegd");
			description.click();
			softAssert.assertTrue(phone_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+phone_error.isDisplayed());
			phone.clear();
			phone.sendKeys("%&%$*@^*$!");
			description.click();
			softAssert.assertTrue(phone_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+phone_error.isDisplayed());
			softAssert.assertAll();
			

	}
	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"quick-contact-sf"})
	public void NamefieldCheck(String cardUrl) {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrl);		
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);
			wait.until(ExpectedConditions.visibilityOf(quickapplysection));
			scrollToElement(mydriver, quickapplysection, logger);
			firstname.sendKeys("AM");
			description.click();
			softAssert.assertTrue(firstname_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: "+firstname_error.isDisplayed());
			firstname.clear();
			firstname.sendKeys("AMr");
			description.click();
			softAssert.assertFalse(firstname_error.isDisplayed());
			getTest().info("Verifing the Error message is not displayed : "+firstname_error.isDisplayed());
			lastname.sendKeys("AM");
			description.click();
			softAssert.assertTrue(lastname_error.isDisplayed());
			getTest().info("Verifing the Error message is displayed  : "+lastname_error.isDisplayed());
			lastname.clear();
			lastname.sendKeys("Tes");
			description.click();
			softAssert.assertFalse(lastname_error.isDisplayed());
			getTest().info("Verifing the Error message is not displayed : "+lastname_error.isDisplayed());
			softAssert.assertAll();

	}
	
}
