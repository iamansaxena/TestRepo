package com.optum.dpm.page.model;
import static com.optum.dpm.reports.ExtentTestManager.getTest;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.optum.dpm.utils.Base;

public class LoginBanner_page extends Base{

	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class=\"login-banner section\"]")
	protected static WebElement loginBannerSection;
	
	@FindBy(xpath="//*[@class=\"login-banner section\"]//h2[@class=\"login-view-heading\"]")
	protected static WebElement mainTitle;
	
	@FindBy(xpath="//*[@class=\"login-banner section\"]//*[@class=\"login-box\"]/a[contains(@class,\"button\")]")
	protected static WebElement loginOptionButton;
	
	@FindBy(xpath="//*[@class=\"login-banner section\"]//*[@class=\"login-box\"]/a[not(contains(@class,\"button\"))]")
	protected static WebElement registerCtaLink;
	
	@FindBy(xpath="//*[@class=\"login-banner section\"]//*[@class=\"login-bottom\"]/h3")
	protected static WebElement mainSubHeading;
	
	@FindBy(xpath="//*[@class=\"login-banner section\"]//*[@class=\"login-bottom\"]/a")
	protected static WebElement enrollNowCtaButton;
	
	public LoginBanner_page() {
		
		PageFactory.initElements(mydriver, this);
	}
	protected static boolean isElementPresent(WebElement e ,WebDriver mydriver,String elementName, Logger logger) {
		getTest().info("Is '"+elementName+"' visible: "+ e.isDisplayed());
		logger.info("Is '"+elementName+"' visible: "+ e.isDisplayed());
		if(e.isDisplayed())
			return true;
		else 
			return false;
	}
	protected static boolean isElementBlank(WebElement e ,WebDriver mydriver,String elementName, Logger logger) {
		getTest().info("Is '"+elementName+"' blank: "+ e.getText().isEmpty());
		logger.info("Is '"+elementName+"' blank: "+ e.getText().isEmpty());
		if(!(e.getText().isEmpty()))
			{logger.info("Element Text: "+e.getText());
			return true;}
		else {
			return false;}
	}
	
}
