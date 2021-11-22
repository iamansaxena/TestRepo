package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class PullQuote_page extends Base {
	
	private String author = "Sai Tummala";
	private String tag = "PullQuote";
	
	protected static String feeds = "//*[@class='pull-quote section']";
	
	@FindBy(xpath = "//*[@class='pull-quote section']")
	protected static WebElement pullQuoteSection;
	
	@FindBy(xpath = "//*[@class='pq']/p")
	protected static WebElement blockQuote;
	
	@FindBy(xpath = "//*[@class='pq']/cite")
	protected static WebElement blockQuoteCitation;
	
	@FindBy(xpath = "//*[@class='pq']/a")
	protected static WebElement blockQuotelink;
	
	/*protected static String blockQuotelink = "//*[@class='pq']/a";*/
}
