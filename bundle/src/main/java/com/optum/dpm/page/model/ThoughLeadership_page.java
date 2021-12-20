package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class ThoughLeadership_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "ThoughLeadership";
	
	protected static String subsectionTitles = "//*[@class=\"tl-container\"]//*[contains(@class, \"tl-content\" )]/h2";
	protected static String subsectionDescription = "//*[@class=\"tl-container\"]//*[contains(@class, \"tl-content\" )]/p";
	protected static String subsectionWithImages = "//*[@class=\"tl-container\"]//*[@class=\"tl-item\" and contains(@style,\"url('/\")]";
	protected static String subsectionButtons = "//*[@class=\"tl-container\"]//*[contains(@class, \"tl-content\" )]/a";
}
