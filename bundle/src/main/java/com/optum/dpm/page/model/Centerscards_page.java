package com.optum.dpm.page.model;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;


public class Centerscards_page extends Base {
	
	private static final Logger logger = LogManager.getLogger(Centerscards_page.class);
	
	private String author = "Rekha Vasugan";
	private String tag = "Centerscards";
	
	@FindBy(xpath = "//div[@class='centers-cards section']//input[@id='find-location']")
	protected static WebElement enterCityTextBox;	
	
	@FindBy(xpath = "//div[@class='centers-cards section']//*[@class='fa fa-search map-search-btn']")
	protected static WebElement findCenterBtn;
	
	@FindBy(xpath = "//div[@class='centers-cards section']")
	protected static WebElement centersCardsSection;
	
	@FindBy(xpath = "//section/div/ul/li[contains(@class,'center-card')]")
	protected static List<WebElement> centerList;
	
	@FindBy(xpath = "//section[@class = 'centers-cards']//*[@class='centers-cards__results'][@aria-hidden='false']")
	protected static WebElement noCenterList;
	
	/**
	 * This method will return true if Center found or else it will return false
	 * @param mydriver
	 * @param location
	 * @param logger
	 * @return boolean
	 */
	public boolean findCenter(WebDriver mydriver, String location, Logger logger) {
		logger.info("Finding Center");
		if(enterCityTextBox.isDisplayed()) {
			enterCityTextBox.sendKeys(location);
			findCenterBtn.click();
			mydriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if(centerList.isEmpty()) {
				logger.info("No center found");
				return false;
			}
			else {
				logger.info("Center found");
			}
		}		
		return true;
	}	

}
 
