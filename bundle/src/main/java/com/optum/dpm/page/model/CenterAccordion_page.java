package com.optum.dpm.page.model;

import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class CenterAccordion_page extends Base{
	private static final Logger logger = LogManager.getLogger(CenterAccordion_page.class);
	
	private String author = "Aman Saxena";
	private String tag = "CenterAccordion";

	@FindBy(xpath="(//*[contains(@class,'centers-accordion')])[1]")
	protected static WebElement centerAccordionSection;
	

	@FindBy(xpath="(//*[contains(@class,'centers-accordion')])[1]//*[@class=\"view-button\"]/a")
	protected static WebElement viewAllButton;
	
	protected static String blades = "(//*[contains(@class,'centers-accordion')])[1]//*[contains(@class,\"accordion-blade\")]";
	
	protected static String bladeLabels = "(//*[contains(@class,'centers-accordion')])[1]//*[contains(@class,\"accordion-blade\")]/span";
	
	protected static String blade1icons = "(//*[contains(@class,'centers-accordion')])[1]//*[contains(@class,\"list__items services\")]//span[contains(@class,'icon')]";
	
	protected static String blade1ColumnHeaders = "(//*[contains(@class,'centers-accordion')])[1]//*[contains(@class,\"list__items services\")]//span[contains(@class,'icon')]/following-sibling::h3";
	
	protected static String blade1ListElements = "(//*[contains(@class,'centers-accordion')])[1]//*[@class=\"list-items active\"]//ul[@class=\"services-list\"]//li[contains(@class,'list-elements')]/a[@href]";
	
	protected static String blade2InsuranceResultElements = "(//*[contains(@class,'centers-accordion')])[1]//ul[1]//*[contains(@class,'ins-list' ) and contains(@class,'item') ]/parent::ul[not(contains(@class,'hide'))]/li";
	
	protected static String expandedBlade = "(//*[contains(@class,'centers-accordion')])[1]//*[@aria-expanded='true']";
	
	@FindBy(xpath="(//*[contains(@class,'centers-accordion')])[1]//*[@class=\"accordion_content\"]//*[@class=\"ins_headline\"]")
	protected static WebElement blade2ContentHeader;
	
	@FindBy(xpath="(//*[contains(@class,'centers-accordion')])[1]//*[@class=\"accordion_content\"]//*[@class=\"insurance\"]/p")
	protected static WebElement blade2ContentDescription;
	
	@FindBy(xpath="(//*[contains(@class,'centers-accordion')])[1]//*[@id=\"ins-state\"]")
	protected static WebElement blade2InsuranceStateSelect;
	
	@FindBy(xpath="//*[@class=\"ins__search hidden\"]")
	protected static WebElement hiddenSearch;
	
	@FindBy(xpath="(//*[contains(@class,'centers-accordion')])[1]//*[@class=\"accordion_content\"]//*[@class=\"insurance\"]//p[@class=\"ins-disclaimer\"]")
	protected static WebElement blade2InsuranceDisclaimer;
	
	@FindBy(xpath="(//*[contains(@class,'centers-accordion')])[1]//*[@class=\"accordion_content\"]//*[@class=\"insurance\"]//*[@class=\"ins__search\"]/h3")
	protected static WebElement blade2InsuranceSeearch;
	
	@FindBy(xpath="(//*[contains(@class,'centers-accordion')])[1]//*[@class=\"accordion_content\"]//p[@class=\"accordion_subtext\"]")
	protected static WebElement blade3Description;
	
	protected static String blade3ColumnHeaders= "(//*[contains(@class,'centers-accordion')])[1]//*[@class=\"visit-list\"]//h3[@class=\"list-header\"]";
	
	protected static String blade3ColumnItems= "(//*[contains(@class,'centers-accordion')])[1]//*[@class=\"visit-list\"]//ul/li";
	
	@FindBy(xpath="(//*[contains(@class,'centers-accordion')])[1]//*[@class=\"list-items accordion-cta\"]//span/parent::a")
	protected static WebElement blade4CtaLink;
	
	@FindBy(xpath="(//*[contains(@class,'centers-accordion')])[1]//*[@class=\"list-items accordion-cta\"]//span")
	protected static WebElement blade4Label;
	
	protected  void scrolltillvisibility(){
		try { ((JavascriptExecutor) mydriver).executeScript("return document.getElementsByClassName('header med-header sticky')[0].remove();");}
		catch (WebDriverException e) {
		}
		scrollToElement(mydriver, centerAccordionSection, logger);
		pleaseWait(1, logger);
	}
}
