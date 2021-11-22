package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class CentersContentMedex_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "CentersContentMedex";

	@FindBy(xpath = "//*[@class=\"centers-content\"]")
	protected static WebElement contentSection;
	
	@FindBy(xpath = "//*[@class=\"centers-content\"]//*[@class=\"branded-message__header\"]/following-sibling::p[1]")
	protected static WebElement brandedCopy;
	
	@FindBy(xpath = "//*[@class=\"centers-content\"]//a[@class=\"js-accLink\"]")
	protected static WebElement brandedHyperlink;
	
	@FindBy(xpath = "//*[@class=\"centers-content\"]//section[contains(@style,'background-image: url')]")
	protected static WebElement sectionImage;
	
	@FindBy(xpath = "//*[@class=\"centers-content\"]//*[@class=\"text-component text-inner\"]")
	protected static WebElement sectionTextComponent;
	
	@FindBy(xpath = "//*[@class=\"centers-content\"]//*[@class=\"text-component text-inner\"]/p")
	protected static WebElement sectionTextComponentContent;
}
