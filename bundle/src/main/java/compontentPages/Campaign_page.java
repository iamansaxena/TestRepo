package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class Campaign_page extends Base {
	protected static WebDriver mydriver;	
	protected static WebElement copyrightText;
	public Campaign_page() {
	PageFactory.initElements(mydriver, this);
	}
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
