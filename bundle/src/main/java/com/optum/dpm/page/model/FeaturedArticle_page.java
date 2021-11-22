package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class FeaturedArticle_page extends Base{
	private String author = "Priyanka Lajpal";
	private String tag = "FeaturedArticle";
	
	@FindBy(xpath = "//*[@class=\"featured-articles section\"]//*[contains(@class,'featured-article__title ')]")
	protected static WebElement title;

	protected static String cardimage = "//*[contains(@class,\"featured-article__img js-HubItemImg\")]";
	protected static String cards = "//*[@class=\"featured-article js-HubItemCard\"]";
	protected static String cardtitle= "//*[@class=\"featured-article__title js-HubItemTitle\"]";
	protected static String description= "//*[@class=\"featured-article__desc js-HubItemDesc\"]";
}