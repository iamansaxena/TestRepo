package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class BrokerHub_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "BrokerHub";
	
	@FindBy(xpath="//*[@class=\"find-rep-map\"]//*[local-name() = 'svg']")
	protected static WebElement map;
	
	protected static String mapLocaitonsClickable= "svg>path[fill='#000000']";
	protected static String mapLocaitons= "svg>path[fill=\"#86888f\"]";
	
	protected static String mapMiniButtons= "svg>text>tspan";
	protected static String mapMiniButtonsClickable = "svg>rect[opacity]";
	
	@FindBy(xpath="//span[@class=\"find-rep-map-label\"]/strong[@class=\"find-rep-state\"]")
	protected static WebElement selectedState;
	
	@FindBy(xpath="//*[@class=\"broker-hub section\"]")
	protected static WebElement hubSection;
	
	@FindBy(xpath="//*[@id=\"state-select\"]")
	protected static WebElement stateSelectionDropDown;
	
	protected static String stateOptions="//*[@id=\"state-select\"]/option";
	@FindBy(xpath="//*[@class=\"find-rep-search-heading\"]")
	protected static WebElement sectionHeading;
	
	@FindBy(xpath="//*[@class=\"find-rep-search-desc\"]")
	protected static WebElement sectionDesc;
	
	@FindBy(xpath="//*[@class=\"oi-sms-texting find-rep-search-icon\"]")
	protected static WebElement sectionIcon;
	
}
