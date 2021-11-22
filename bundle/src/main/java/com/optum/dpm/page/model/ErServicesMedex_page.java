package com.optum.dpm.page.model;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class ErServicesMedex_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "ErServicesMedex";

	@FindBy(xpath="//*[@class=\"er-services section\"]")
	protected static WebElement erServiceSection;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"er__heading\"]")
	protected static WebElement erServiceSectionTitle;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"er__desc__copy\"]")
	protected static WebElement erServiceSectionCopy;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"med-service\"]/p")
	protected static WebElement medexSectionHeading;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"med-service\"]/ul/li/span[contains(@class,'icon icon')]")
	protected static List<WebElement> medexSectionItemIcon;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"med-service\"]/ul/li/span[contains(@class,'icon_desc')]")
	protected static List<WebElement> medexSectionItemLabel;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"er-service\"]/p")
	protected static WebElement emergencySectionHeading;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"er-service\"]/ul/li/span[contains(@class,'icon icon')]")
	protected static List<WebElement> emergencySectionItemIcon;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"er-service\"]/ul/li/span[contains(@class,'icon_desc')]")
	protected static List<WebElement> emergencySectionItemLabel;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"reference-cite\"]/p")
	protected static List<WebElement> referenceSectionTextField;
	
}
