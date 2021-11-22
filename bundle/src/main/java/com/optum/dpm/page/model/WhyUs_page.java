package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class WhyUs_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "WhyUs";

	@FindBy(xpath="//*[@class=\"why-us section\"]")
	protected static WebElement whyUsSection;
	
	@FindBy(xpath="//*[@class=\"why-us section\"]//div[contains(@class,'row' )]/h2")
	protected static WebElement sectionHeader;
	
	protected static String columnIcons = "//*[@class=\"why-us section\"]//div[contains(@class,'col')]//*[contains(@class,'why-us__icon')]";
	
	protected static String columnTitle = "//*[@class=\"why-us section\"]//div[contains(@class,'col')]//*[contains(@class,'why-us__icon')]/following-sibling::h3";
	
	protected static String columnDescriptions = "//*[@class=\"why-us section\"]//div[contains(@class,'col')]/p";
	
	protected static String columnLinks = "//*[@class=\"why-us section\"]//div[contains(@class,'col')]/a";
}
