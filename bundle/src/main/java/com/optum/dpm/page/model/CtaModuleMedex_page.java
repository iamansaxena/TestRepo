package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class CtaModuleMedex_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "CtaModuleMedex";

	@FindBy(xpath="//*[@class=\"cta-module section\"]")
	protected static WebElement ctaModuleSection;
	
	@FindBy(xpath="//*[@class=\"cta-module section\"]//*[contains(@class,'cta-copy')]/h2")
	protected static WebElement ctaModuleTitle;
	
	@FindBy(xpath="//*[@class=\"cta-module section\"]//*[contains(@class,'cta-copy')]/p")
	protected static WebElement ctaModuleCopy;
	
	@FindBy(xpath="//*[@class=\"cta-module section\"]//*[contains(@class,\"cta-button\")]")
	protected static WebElement ctaModuleCtaLabel;
	
	@FindBy(xpath="//*[@class=\"cta-module section\"]//*[@href]")
	protected static WebElement ctaModuleCtaLink;

}
