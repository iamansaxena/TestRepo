package com.optum.dpm.page.model;

import com.optum.dpm.utils.Base;

public class BioCard_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "BioCard";
	
	protected static String cards = "//*[contains(@class,\"cardbionumber\")]";
	protected static String expandButton = "//*[contains(@class,\"cardbionumber\")]/div[@class=\"card__front\"]/button/span";
	protected static String collapseButton = "//*[contains(@class,\"cardbionumber\")]/div[contains(@class,\"card__back\")]/button/span";
	protected static String speakerNameBack = "//*[contains(@class,\"front\")]//*[@class=\"speaker__name small\"]";
	protected static String speakerNameFront = "//*[contains(@class,\"front\")]//*[@class=\"speaker__name small\"]";
	protected static String speakerTitleFront = "//*[contains(@class,\"front\")]//*[@class=\"speaker__title ul-style\"]";
	protected static String twitterHandle = "//*[@class=\"speaker__twitter ul-style\"]";
}
