package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class ExpandableCard_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "ExpandableCard";
	
	protected static String closeTopButtons = "//*[@class=\"expanding__card-expand-icon oi-box-close\"]";

	protected static String openTopButtons = "//div[contains(@class,\"cardnumber\")]//span[@class=\"oi-box-open expanding__card-expand-icon\"]";
	protected static String cards = "//*[contains(@class,'cardnumber-')]";
	protected static String cardImages = "//*[contains(@class,'cardnumber')]//div[contains(@class,\"image\")]/img";
	protected static String cardWithoutImages = "//*[contains(@class,'cardnumber') and contains(@class,'expanding')]/div/div[@class=\"expanding-card__icon\"]/span";
	protected static String cardTitles = "//*[contains(@class,'cardnumber') and contains(@class,'expanding')]//*[contains(@class,\"expanding-card__heading\")]";
	protected static String cardDescriptions = "//*[contains(@class,'cardnumber') and contains(@class,'expanding')]//*[contains(@class,\"expanding-card__heading\")]/following-sibling::p";
	protected static String openBottomButtons = "//*[contains(@class,'cardnumber')]//*[ contains(@class,'expanding-card__open') and contains(@class, 'full')]";
	@FindBy(xpath = "(//*[contains(@class,\"card-Z\") and contains(@class,\"expanded\")]/div[contains(@class,\"card__content\")])[last()]")
	protected static WebElement expandedCardListContents;
	protected static String closeBottomButtons = "(//*[contains(@class,\"card-Z\") ]//button[contains(@class,\"open-button\")])[last()]";
	protected static String openBottomButtonsWithLinks = "//*[contains(@class,'cardnumber') ]/div[contains(@class,\"card__content\")][1]/a";
	protected static String expandedCardDescriptions = "(//*[contains(@class,\"card-Z\")]/div[contains(@class,\"expanded\")])[last()]";
}
