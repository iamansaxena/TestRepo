package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class MaterialCardInfographic_page extends Base {

	protected static WebDriver mydriver;
	protected static String cards = "//*[@class=\"material-card-info-graphic section\"]";
	protected static String images = "//*[contains(@class,\"mcard__thumb cell col-4 col-6--tablet\")]/img";
	protected static String titles = "//*[@class=\"mcard__content col-7 col-5--tablet\"]/h4";
	protected static String descriptions = "//*[@class=\"mcard__content col-7 col-5--tablet\"]/h4/following-sibling::p";
	protected static String cardButton = "//*[@class=\"mcard__content col-7 col-5--tablet\"]/h4/following-sibling::a";

	public MaterialCardInfographic_page() {

		PageFactory.initElements(mydriver, this);
	}
}
