package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class LocalMessage_page extends Base {
	
	private String author = "Aman Saxena";
	private String tag = "LocalMessage";

	@FindBy(xpath="//*[@class=\"local-message-hub section\"]//*[contains(@class,\"medexpress-plus\")]")
	protected static WebElement plusIcon;
	
	@FindBy(xpath="//*[@class=\"local-message-hub section\"]//*[@class=\"local-message__state\"]")
	protected static WebElement stateField;
	
	@FindBy(xpath="//*[@class=\"local-message-hub section\"]//*[@class=\"local-message__text-box\"]//p[@class=\"local-message__message-text\"]/following-sibling::p[not(contains(@class,\"local-message__tags\"))][1]")
	protected static WebElement messageFields;
	
	@FindBy(xpath="//*[@class=\"local-message-hub section\"]//*[@class=\"local-message__message-header\"]")
	protected static WebElement messageHeader;
	
	@FindBy(xpath="//*[@class=\"local-message-hub section\"]")
	protected static WebElement messageSection;
	
	@FindBy(xpath="//*[@class=\"local-message-hub section\"]//*[@class=\"local-message__text-box\"]//a")
	protected static WebElement ctaLink;

	@FindBy(xpath="//*[@class=\"local-message-hub section\"]//*[@class=\"local-message__text-box\"]//*[contains(@class,'button')]")
	protected static WebElement ctaButton;
}
