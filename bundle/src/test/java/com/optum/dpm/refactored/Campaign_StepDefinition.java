package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.Campaign_page;

import core.CustomDataProvider;

public class Campaign_StepDefinition extends Campaign_page {

	private static final Logger logger = LogManager.getLogger(Campaign_StepDefinition.class);
	private String FName = "Asiq";
	private String LName = "John";
	private String Email = "Test@gmail.com";

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"campaign"})
	public void CampaignVisibilityCheck(String url) {
		 skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			WebDriverWait wait = new WebDriverWait(mydriver, 30);
			wait.until(ExpectedConditions.visibilityOf(Firstname));
			scrollToElement(mydriver, Campaignsection, logger);
			boolean abc = false;
			softAssert.assertTrue(Campaignsection.isDisplayed());
			softAssert.assertTrue(Firstname.isDisplayed());
			softAssert.assertTrue(Lastname.isDisplayed());
			softAssert.assertTrue(E_mail.isDisplayed());
			softAssert.assertTrue(Submitbtn.isDisplayed());
			softAssert.assertTrue(Heading.isDisplayed());
			softAssert.assertAll();
			if (abc != (Heading.isDisplayed() && Firstname.isDisplayed() && Lastname.isDisplayed()
					&& E_mail.isDisplayed() && Submitbtn.isDisplayed())) {
				abc = true;
			}

			getTest().info("Verifing the elememt is displayed: " + abc);

		}
	

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"campaign"})
	public void Campaignsubmision(String url) {
		 skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			WebDriverWait wait = new WebDriverWait(mydriver, 30);
			wait.until(ExpectedConditions.visibilityOf(Firstname));

			boolean xyz = false;
			scrollToElement(mydriver, Campaignsection, logger);
			Firstname.sendKeys(FName);
			Lastname.sendKeys(LName);
			E_mail.sendKeys(Email);
			Submitbtn.click();
			pleaseWait(6, logger);
			hardAssert.assertTrue(Success_msg.isDisplayed());
			hardAssert.assertTrue(Heading.isDisplayed());
			softAssert.assertFalse(Firstname.isDisplayed());
			softAssert.assertFalse(Lastname.isDisplayed());
			softAssert.assertFalse(E_mail.isDisplayed());
			softAssert.assertFalse(Submitbtn.isDisplayed());
			softAssert.assertAll();

			if (xyz == (Firstname.isDisplayed() && Lastname.isDisplayed() && E_mail.isDisplayed()
					&& Submitbtn.isDisplayed())) {
				xyz = true;

			}

			getTest().info("Verifing the Capaign form submission: " + xyz);

		}
	

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"campaign"})
	public void Mandatorymsg(String url) {
		 skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			WebDriverWait wait = new WebDriverWait(mydriver, 30);
			wait.until(ExpectedConditions.visibilityOf(Firstname));
			boolean abc = false;
			Submitbtn.click();
			pleaseWait(6, logger);
			softAssert.assertTrue(Firstname_Error.isDisplayed());
			softAssert.assertTrue(Lastname_Error.isDisplayed());
			softAssert.assertTrue(E_mail_Error.isDisplayed());
			softAssert.assertAll();

			if (abc != (Firstname_Error.isDisplayed() && Lastname_Error.isDisplayed() && E_mail_Error.isDisplayed())) {
				abc = true;
			}

			getTest().info("Verifing the mandatory error message: " + abc);

		}
	

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"campaign"})
	public void EmailfieldCheck(String url) {
		 skipNonExistingComponent(url);

		
			
			mydriver.get(url);
			WebDriverWait wait = new WebDriverWait(mydriver, 30);
			wait.until(ExpectedConditions.visibilityOf(Campaignsection));
			scrollToElement(mydriver, Campaignsection, logger);
			E_mail.sendKeys("test@gmail.com");
			Firstname.click();
			softAssert.assertFalse(E_mail_Error.isDisplayed());
			getTest().info("Verifing the Error message is not displayed: " + E_mail_Error.isDisplayed());
			E_mail.clear();
			E_mail.sendKeys("test@gmail.");
			Firstname.click();
			softAssert.assertTrue(E_mail_Error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: " + E_mail_Error.isDisplayed());
			E_mail.clear();
			E_mail.sendKeys("test@");
			Firstname.click();
			softAssert.assertTrue(E_mail_Error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: " + E_mail_Error.isDisplayed());
			E_mail.clear();
			E_mail.sendKeys("!@#.#$%.@#$");
			Firstname.click();
			softAssert.assertTrue(E_mail_Error.isDisplayed());
			getTest().info("Verifing the Error message is displayed: " + E_mail_Error.isDisplayed());
			softAssert.assertAll();
		}
	
}
