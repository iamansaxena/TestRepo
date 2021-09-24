package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class Card300Image_page extends Base {
	protected static WebDriver mydriver;
	protected static String cardsXpath = "//*[@class=\"card-box section\"]";
	protected static String imagesXpath = "//*[@class='mcard__img300-img']/img";

	protected static String titlesXpath = "//*[@class=\"mcard__image300__content--titlearrow\"]/h3";
	protected static String contentXpath = "//*[@class=\"mcard__image300-subcontent\"]/p";
	protected static String arrowButtonXpath = "//*[@class=\"mcard__content--arrow\"]/i";
	
	public Card300Image_page() {
		PageFactory.initElements(mydriver, this);
	}

}