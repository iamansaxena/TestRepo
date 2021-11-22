package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class MaterialCardFade_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "MaterialCardFade";
	
	protected static String titles = "//*[contains(@class,\"mcard__fade--title\")]";
	protected static String descriptions = "//*[contains(@class,\"mcard__fade--title\")]/following-sibling::p[@class=\"mcard__fade--link\"]/preceding-sibling::p";
	protected static String buttons = "//*[contains(@class,\"mcard__fade--title\")]/following-sibling::p[@class=\"mcard__fade--link\"]";
}