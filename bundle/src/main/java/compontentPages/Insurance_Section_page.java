package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class Insurance_Section_page extends Base {
	protected static WebDriver mydriver;	
	protected static WebElement copyrightText;
	public Insurance_Section_page() {
	PageFactory.initElements(mydriver, this);
	}
	@FindBy(xpath="//*[@class='insurance']")
	protected static WebElement Insurancesection;
	@FindBy(xpath="//*[@class='insurance']//h2[@class='ins_headline']")
	protected static WebElement InsHeading;
	@FindBy(xpath="//*[@class='insurance']//section[@id='ins-result-success']//h3//span[@class='insStateName']")
	protected static WebElement Successstatename;
	@FindBy(xpath="//*[@class='insurance']//select[@id='ins-state']")
	protected static WebElement InsStatdropdown;
	
	@FindBy(xpath="//*[@class='insurance']//button[@class='button ins-searchbtn']")
	protected static WebElement Searchbtn;
	
	
	@FindBy(xpath="//*[@class='insurance']//section[@id='no-centers-error']//h3//span[@class='insStateName']")
	protected static WebElement NoInsurancestatename;
	
	@FindBy(xpath="//*[@class='insurance']//section[@class='prom-pay']//div[@class='prom-pay__icontext']")
	protected static WebElement Promt_pay;
	
	@FindBy(xpath="//*[@class='insurance']//section[@class='prom-pay']//p[@class='prom-pay_desc']")
	protected static WebElement Promt_pay_desc;
	
	
}
