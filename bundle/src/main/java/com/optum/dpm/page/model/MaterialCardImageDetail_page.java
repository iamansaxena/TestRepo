package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class MaterialCardImageDetail_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "MaterialCardImageDetail";
	
	protected static String cards = "//*[contains(@class,\"material-card-image-detail\")]";
	// For cards having title use the locator without 'span' tag. Also the cards
	// would be in a loop
	/*
	 * //*[@class="material-card-image-detail-cta section"]//*[@class=
	 * "mcard__imgcta__title"]/span
	 */
	protected static String descriptions = "//*[@class=\"mcard__detail__descption\"]";
	protected static String buttonTypes = "//*[contains(@class,\"material-card-image-detail\")]/div/div[3]";
	protected static String cardImages = "//*[@class=\"mcard__image-container\"]/img";
}
