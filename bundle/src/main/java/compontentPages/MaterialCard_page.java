package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class MaterialCard_page extends Base {
	protected static WebDriver mydriver;
	protected static String titles = "//*[@class=\"material-card__title\"]";
	protected static String subTitles = "//*[@class=\"material-card__title\"]/following-sibling::p[@class=\"ul-style\"]";
	protected static String descriptions = "//*[@class=\"material-card__title\"]/following-sibling::p[contains(@class,\"description\")]";
	protected static String buttons = "//*[@class=\"material-card__title\"]/following-sibling::a[contains(@class,\"button\")]";

	public MaterialCard_page() {
		PageFactory.initElements(mydriver, this);
	}
}
