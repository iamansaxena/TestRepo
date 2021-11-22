package com.optum.dpm.page.model;

import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

/**
 * @author amohan31
 *
 */
public class ServicesSectionMedex_page extends Base{
	private static final Logger logger = LogManager.getLogger(ServicesSectionMedex_page.class);
	
	private String author = "Aman Saxena";
	private String tag = "ServicesSectionMedex";
	
	@FindBy(xpath="//*[@class='services-section section']")
	protected static WebElement servicesSection;

	@FindBy(xpath="//*[@class='services-section section']//*[@class=\"view-button\"]/a")
	protected static WebElement viewAllButton;

	@FindBy(xpath="//*[@class='services-section section']//*[contains(@class,\"list__items services\")]//span[contains(@class,'icon')]")
	protected static List<WebElement> serviceSectionCoulmnIcons;
	
	@FindBy(xpath="//*[@class='services-section section']//*[contains(@class,\"list__items services\")]//span[contains(@class,'icon')]/following-sibling::h3")
	protected static List<WebElement> serviceSectionColumnHeaders;
	
	@FindBy(xpath="//*[@class='services-section section']//*[@class=\"list-items active\"]//ul[@class=\"services-list\"]//li[contains(@class,'list-elements')]/a[@href]")
	protected static List<WebElement> serviceSectionListElements ;
	
/*
 * This is to remove main header overlapping other elements
 * 
 * */	
	protected  void scrolltillvisibility(){
		try { ((JavascriptExecutor) mydriver).executeScript("return document.getElementsByClassName('header med-header sticky')[0].remove();");}
		catch (WebDriverException e) {
		}
			scrollToElement(mydriver, servicesSection, logger);
				pleaseWait(1, logger);
	}
}
