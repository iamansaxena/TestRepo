package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class SiteMap_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "SiteMap";
	
	protected static String links = "//*[@class=\"sitemap parbase section\"]//ul[@class=\"site-map\"]/li/a";
	@FindBy(xpath = "//*[@class=\"sitemap parbase section\"]")
	protected static WebElement siteMapSection;
}
