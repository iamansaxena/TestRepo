package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class ResourceLibrarySidebar_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "ResourceLibrarySidebar";
	
	@FindBy(xpath="//*[@class=\"aside__cta__heading\"]")
	protected static WebElement newsLetterLabel;
	
	@FindBy(xpath="//*[@class=\"aside__cta__heading\"]/following-sibling::a")
	protected static WebElement newsLetterButton;
	
	@FindBy(xpath="//*[@class=\"side-bar section\"]//*[@class=\"aside__expert__heading delta\"]/strong")
	protected static WebElement expertiesSectionHeader;
	
	@FindBy(xpath="//*[@class=\"side-bar section\"]//*[@class=\"aside__expert__heading delta\"]")
	protected static WebElement expertiesSectionLabel;
	
	protected static String  expertiseSubSectionImages = "//*[@class=\"aside__expert__cardbox\"]/div/a/img";
	
	protected static String  expertiseSubSectionLink = "//*[@class=\"aside__expert__cardbox\"]/div/a";
	
	protected static String  expertiseSubSectionTitle = "//*[@class=\"aside__expert__nav-title\"]";
	
	protected static String  expertiseSubSectionDesc = "//*[@class=\"aside__expert__sub-title\"]";
	
	protected static String  resourcesAndServicesSubsectionLink = "//*[@class=\"aside__resource__cards\"]/a";
	
	protected static String  resourcesAndServicesSubsectionTitle = "//*[@class=\"aside__resource__nav-title\"]";

}
