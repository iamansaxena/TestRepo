package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class CareerSearch_page extends Base {
	private String author = "Sreevidhya";
	private String tag = "CareerSearch";

	protected static WebElement copyrightText;
	@FindBy(xpath="//*[@class='career-search section']")
	protected static WebElement Careersearchsection;
	
	@FindBy(xpath="//*[@class='career-search section']//select[@id='category']")
	protected static WebElement categorydropdown;
	@FindBy(xpath="//*[@class='career-search section']//input[@id='location']")
	protected static WebElement locationdropdown;
	@FindBy(xpath="//*[@class='career-search section']//select[@id='distance']")
	protected static WebElement distancedropdown;
	@FindBy(xpath="//*[@class='career-search section']//button//span[@class='career-search-form__button-text']")
	protected static WebElement Searchbutton;
	@FindBy(xpath="//*[@class='career-search section']//div[@class='career-search-results__inner']//child::h3[1]")
	protected static WebElement Searchresult;
	
	@FindBy(xpath="//*[@class='career-search section']//a[@class='button careerButtonURL']")
	protected static WebElement Careeropp;
	
	@FindBy(xpath="//*[@class='career-search section']//div[@id='category_error']")
	protected static WebElement Mandatorycategorymsg;
	@FindBy(xpath="//*[@class='career-search section']//div[@id='location_error']")
	protected static WebElement Mandatorylocationmsg;
	@FindBy(xpath="//*[@class='career-search section']//span[@class='careerButtonFilteredURL']//child::a")
	protected static WebElement Viewposition;
	
	@FindBy(xpath="//section[@id='content']//input[@class='fusion-button button-default button-small quicksearch-submit']")
	protected static WebElement Searchjobbtn;

}
