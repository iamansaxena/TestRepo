package com.optum.dpm.page.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.optum.dpm.utils.Base;

public class UtilityNav_page extends Base{

	protected static WebDriver mydriver;	
	protected static WebElement copyrightText;
	public UtilityNav_page() {
	PageFactory.initElements(mydriver, this);
	}
	@FindBy(xpath="//nav[@class='dpl-utility']")
	protected static WebElement dpl_utility;
	
	@FindBy(css="div.dpl-utility-nav li.dpl-utility__item")
	protected static List<WebElement> NavItems;
	
	@FindBy(css="div.dpl-utility-nav li.dpl-utility__item:nth-child(4)")
	protected static WebElement aboutUsDropdown;
	
	@FindBy(xpath="(//li[@class='dpl-utility__item']//a)[1]")
	protected static WebElement Inheritance;
	
	

	@FindBy(css="div.dpl-utility-nav li.dpl-utility__item:nth-child(4) a span.dpl-utility__subtext")
	protected static List<WebElement> NavList;
	@FindBy(css="div.dpl-utility-nav li.dpl-utility__item:nth-child(4) a")
	protected static List<WebElement> NavListURl;
	
	public String  SelectNavitems(WebDriver mydriver, int item, Logger logger) {
		logger.info("Selecting nav items");
		String loginLink = null;
		if(dpl_utility.isDisplayed()) {	
		
				 
				 By cssSelector = By.cssSelector("div.dpl-utility-nav li.dpl-utility__item:nth-child(" + item + ") a");
				 loginLink= mydriver.findElement(cssSelector).getAttribute("href");
				 mydriver.findElement(cssSelector).click();
				 ArrayList<String> tabs = new ArrayList<String>(mydriver.getWindowHandles());
				 System.out.println("No. of tabs: " + tabs.size());				 
				
			      mydriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
		}		
		
		return loginLink;
	}	

	
	
	
	
}
