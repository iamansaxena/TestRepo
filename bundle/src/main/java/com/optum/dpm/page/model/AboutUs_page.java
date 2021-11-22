package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.optum.dpm.utils.Base;

public class AboutUs_page extends Base {
	
	private String author = "Aman Saxena";
	private String tag = "AboutUs";

	@FindBy(xpath="//*[@class=\"about-us section\"]")
	protected static WebElement aboutUsSection;
	
	protected static String tabs = "(//*[@class=\"about-us section\"])[1]//li/a";
}
