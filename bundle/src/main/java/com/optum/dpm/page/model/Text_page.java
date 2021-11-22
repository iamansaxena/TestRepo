package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class Text_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "Text";
protected static String textField = "//*[@class=\"text-component text-inner\"]/p[not(./b or ./a or ./span or ./img)]";
protected static String buttons = "//*[@class=\"text-component text-inner\"]//*[contains(@class,'button') and not(contains(@class,'disabled'))]/a[@href]";
}
