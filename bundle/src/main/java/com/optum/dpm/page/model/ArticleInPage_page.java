package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class ArticleInPage_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "ArticleInPage";
	
	protected static String navTabs = "//*[@id=\"articleInPgNav\"]/ul/li";
	protected static String subSection = "//*[contains(@class,\"content_items\")]";
	
}
