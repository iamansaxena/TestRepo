package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class Logo_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "Logo";
	
	@FindBy(xpath="//*[@class=\"logo\"]/div[@data-favicon]")
	protected static WebElement favIcon;
	
	@FindBy(xpath="//*[@class=\"logo\"]/a")
	protected static WebElement logoLink;
	
	@FindBy(xpath="//*[@class=\"logo\"]//img")
	protected static WebElement logoImg;

	@FindBy(xpath="//*[@class=\"logo\"]")
	protected static WebElement logo;
	
	@FindBy(xpath="//*[@class=\"logo\"]/div[@class=\"logo__tag delta\"]")
	protected static WebElement logoTagLine;
}
