package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class CrossPromoAread_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "CrossPromoAread";

protected static String tagField = "//*[@class=\"cross-promo__tag\"]/span";

protected static String articles = "//*[@class=\"cross-promo__cardbox\"]/div[contains(@class,\"HubItemCard\")]";

protected static String articleLinks = "//*[@class=\"cross-promo__cardbox\"]/div[contains(@class,\"HubItemCard\")]/a";

@FindBy (xpath="//*[contains(@class,\"cross-promo\" )]//h2/span[@class=\"title\"]")
protected static WebElement sectionTitle;

@FindBy (xpath="//*[@class=\"cross-promo__seemore \"]/a")
protected static WebElement mainButton;

}

