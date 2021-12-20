package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class DynamicAlert_page extends Base {
	private String author = "Sai Tummala";
	private String tag = "DynamicAlert_page";
	
	@FindBy(xpath = "//*[@class='dynamic-alerts__heading']")
	protected static WebElement alerttitle;

	@FindBy(xpath = "//*[@class='dynamic-alerts__desc']")
	protected static WebElement description;

	@FindBy(xpath = "//*[@class= 'dynamic-alerts__icon oi-alert']")
	protected static WebElement alerticon; 

	@FindBy(xpath = "//*[@class= 'dynamic-alerts__close oi-no']")
	protected static WebElement closeicon; 

	@FindBy(xpath = "//*[@class= 'button button--reverse']")
	protected static WebElement button;
	
	@FindBy(xpath = "//*[@class='dynamic-alerts__container']")
	protected static WebElement alertwindowbackgroundcolor;
	
	@FindBy(xpath = "//*[@class='dynamic-alerts__container']")
	protected static WebElement alertwindowcontainer;
	
	@FindBy(xpath = "//*[@class='dynamic-alerts__icon oi-alert']")
	protected static WebElement alerticoncolor;
}