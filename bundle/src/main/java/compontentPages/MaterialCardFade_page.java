package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class MaterialCardFade_page extends Base {
	protected static  WebDriver mydriver;
	protected static String titles = "//*[contains(@class,\"mcard__fade--title\")]";
	protected static String descriptions = "//*[contains(@class,\"mcard__fade--title\")]/following-sibling::p[@class=\"mcard__fade--link\"]/preceding-sibling::p";
	protected static String buttons = "//*[contains(@class,\"mcard__fade--title\")]/following-sibling::p[@class=\"mcard__fade--link\"]";

	public MaterialCardFade_page() {
		PageFactory.initElements(mydriver, this);
	}
	}