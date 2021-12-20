package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class QuickContact_page extends Base{
	private String author = "Sreevidhya";
	private String tag = "QuickContact";
	
	protected static WebElement copyrightText;
	@FindBy(xpath="//*[@class='quick-contact-sf section']")
	protected static WebElement quickapplysection;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//h2[@class='qf__heading']")
	protected static WebElement quickapplyheading;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//input[@id='firstname']")
	protected static WebElement firstname;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//input[@id='lastname']")
	protected static WebElement lastname;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//select[@id='state-visit']")
	protected static WebElement states;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//input[@id='phone']")
	protected static WebElement phone;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//input[@id='email']")
	protected static WebElement mail;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//textarea[@id='description']")
	protected static WebElement description;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//select[@id='number-of-employees']")
	protected static WebElement Numofemp;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//select[@id='industry']")
	protected static WebElement industry;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//input[@id='companyname']")
	protected static WebElement companyname;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//p[@class='qf-fm-msg']")
	protected static WebElement Mandatorymsg;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//button[@class='button qf-fm-btn']")
	protected static WebElement sendbtn;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//strong[contains(text(),'Thank you')]")
	protected static WebElement Thankyou;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//span[@id='firstname_error']")
	protected static WebElement firstname_error;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//span[@id='lastname_error']")
	protected static WebElement lastname_error;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//span[@id='phone_error']")
	protected static WebElement phone_error;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//span[@id='email_error']")
	protected static WebElement email_error;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//span[@id='companyname_error']")
	protected static WebElement companyname_error;
	@FindBy(xpath="//*[@class='quick-contact-sf section']//span[@id='description_error']")
	protected static WebElement description_error;
}
