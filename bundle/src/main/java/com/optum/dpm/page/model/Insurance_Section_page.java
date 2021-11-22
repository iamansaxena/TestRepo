package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class Insurance_Section_page extends Base {
	
	private String author = "Sreevidhya";
	private String tag = "Insurance_Section";
	
	protected static WebElement copyrightText;
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
