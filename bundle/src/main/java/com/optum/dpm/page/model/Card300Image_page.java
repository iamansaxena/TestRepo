package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class Card300Image_page extends Base {
	private String author = "Sai Tummala";
	private String tag = "Card300Image";
	
	protected static String cardsXpath = "//*[@class=\"card-box section\"]";
	protected static String imagesXpath = "//*[@class='mcard__img300-img']/img";

	protected static String titlesXpath = "//*[@class=\"mcard__image300__content--titlearrow\"]/h3";
	protected static String contentXpath = "//*[@class=\"mcard__image300-subcontent\"]/p";
	protected static String arrowButtonXpath = "//*[@class=\"mcard__content--arrow\"]/i";

}