package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class Campaign_page extends Base {
	private String author = "Sreevidhya";
	private String tag = "Campaign";
	
	protected static WebElement copyrightText;
	@FindBy(xpath="//*[@class='campaign section']")
	protected static WebElement Campaignsection;
	
	@FindBy(xpath="//*[@class='campaign section']//input[@id='firstname']")
	protected static WebElement Firstname;
	@FindBy(xpath="//*[@class='campaign section']//input[@id='lastname']")
	protected static WebElement Lastname;
	@FindBy(xpath="//*[@class='campaign section']//input[@id='email']")
	protected static WebElement E_mail;
	
	@FindBy(xpath="//*[@class='campaign section']//span[@id='firstname_error']")
	protected static WebElement Firstname_Error;
	@FindBy(xpath="//*[@class='campaign section']//span[@id='lastname_error']")
	protected static WebElement Lastname_Error;
	@FindBy(xpath="//*[@class='campaign section']//span[@id='email_error']")
	protected static WebElement E_mail_Error;
	
	@FindBy(xpath="//*[@class='campaign section']//button[@id='campaign-submit-btn']")
	protected static WebElement Submitbtn;
	
	@FindBy(xpath="//*[@class='campaign section']//strong[@class='campaign__success-msg']")
	protected static WebElement Success_msg;
	
	@FindBy(xpath="//*[@class='campaign section']//h2[@class='campaign__heading']")
	protected static WebElement Heading;

}
