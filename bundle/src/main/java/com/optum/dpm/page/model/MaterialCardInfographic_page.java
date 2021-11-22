package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class MaterialCardInfographic_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "MaterialCardInfographic";
	
	protected static String cards = "//*[@class=\"material-card-info-graphic section\"]";
	protected static String images = "//*[contains(@class,\"mcard__thumb cell col-4 col-6--tablet\")]/img";
	protected static String titles = "//*[@class=\"mcard__content col-7 col-5--tablet\"]/h4";
	protected static String descriptions = "//*[@class=\"mcard__content col-7 col-5--tablet\"]/h4/following-sibling::p";
	protected static String cardButton = "//*[@class=\"mcard__content col-7 col-5--tablet\"]/h4/following-sibling::a";
}
