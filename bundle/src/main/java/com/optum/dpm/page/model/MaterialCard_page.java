package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class MaterialCard_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "MaterialCard";
	
	protected static String titles = "//*[@class=\"material-card__title\"]";
	protected static String subTitles = "//*[@class=\"material-card__title\"]/following-sibling::p[@class=\"ul-style\"]";
	protected static String descriptions = "//*[@class=\"material-card__title\"]/following-sibling::p[contains(@class,\"description\")]";
	protected static String buttons = "//*[@class=\"material-card__title\"]/following-sibling::a[contains(@class,\"button\")]";
}
