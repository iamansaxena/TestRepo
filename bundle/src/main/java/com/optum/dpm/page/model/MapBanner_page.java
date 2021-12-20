package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class MapBanner_page extends Base {
	
	private String author = "Sreevidhya";
	private String tag = "MapBanner";

	protected static WebElement copyrightText;
	@FindBy(xpath="//*[@class='map-banner__find-location']")
	protected static WebElement map_banner_section;
	
	@FindBy(xpath="//*[@class='map-banner__find-location']//label[@id='find-location-label']")
	protected static WebElement Findlocation;
	
	@FindBy(xpath="//*[@class='map-banner__find-location']//input[@id='find-location']")
	protected static WebElement Findlocationinput;
	
	@FindBy(xpath="//*[@class='map-banner__find-location']//span[text()='a Center Near You']")
	protected static WebElement Center;
	
}
