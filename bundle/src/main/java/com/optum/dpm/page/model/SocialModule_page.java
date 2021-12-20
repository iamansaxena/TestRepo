package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class SocialModule_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "SocialModule";
	
	@FindBy(xpath="//*[@class=\"social-module section\"]")
	protected static WebElement moduleSection;

	@FindBy(xpath="//*[@class=\"social-module section\"]//h2[contains(@class,'social-module__header')]")
	protected static WebElement mainSectionHeader;

	@FindBy(xpath="//*[@class=\"social-module section\"]//p[contains(@class,'module__description')]")
	protected static WebElement mainSectionDescription;
	
	@FindBy(xpath="//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]/*[contains(@class,'left')]")
	protected static WebElement socialCardLeft;
	
	@FindBy(xpath="//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]/*[contains(@class,'center')]")
	protected static WebElement socialCardCenter;
	
	@FindBy(xpath="//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]/*[contains(@class,'right')]")
	protected static WebElement socialCardRight;
	
	protected static String mainSocialIconLinks = "//*[@class=\"social-module section\"]//*[contains(@class,'header_social_icons')]/a";
	
	protected static String socialCards = "//*[contains(@class,'social-module__item')][@role][@data-link]";
	
	protected static String socialCardTitles = "//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]//*[contains(@class,'module__item__title small')]";
	
	protected static String socialCardImages = "//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]//img";
	
	protected static String socialCardIcons = "//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]//span[contains(@class,'social-module__item__icon')]";
	
	protected static String socialCardContents = "//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]//p";
	
	protected static String socialCardLinks = "//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]//a";

}
