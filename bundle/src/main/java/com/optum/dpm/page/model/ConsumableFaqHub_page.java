package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class ConsumableFaqHub_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "ConsumableFaqHub";

	@FindBy (xpath="//*[@class=\"consumable-faq-hub section\"]")
	protected static WebElement sectionContainer;
	
	@FindBy (xpath="//*[@class=\"consumable-faq-hub section\"]//*[@class=\"accordion-text subhead--small\"]")
	protected static WebElement sectionHeader;
	
	protected static String questionsExpand = "//*[@class=\"consumable-faq-hub section\"]//button[contains(@class,\"accordion__header\")]";
	protected static String questionsClose = "//*[@class=\"consumable-faq-hub section\"]//button[contains(@class,\"accordion__close\")]";
	protected static String questionsText = "//*[@class=\"consumable-faq-hub section\"]//*[@class=\"accordion-question epsilon\"]";
	protected static String answerText = "//*[@class=\"consumable-faq-hub section\"]//*[@class=\"accordion-flex-item accordion__left\"]/p[1]";
}
