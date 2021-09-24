package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class ThoughLeadership_page extends Base {

	protected static WebDriver mydriver;
	
	protected static String subsectionTitles = "//*[@class=\"tl-container\"]//*[contains(@class, \"tl-content\" )]/h2";
	protected static String subsectionDescription = "//*[@class=\"tl-container\"]//*[contains(@class, \"tl-content\" )]/p";
	protected static String subsectionWithImages = "//*[@class=\"tl-container\"]//*[@class=\"tl-item\" and contains(@style,\"url('/\")]";
	protected static String subsectionButtons = "//*[@class=\"tl-container\"]//*[contains(@class, \"tl-content\" )]/a";
	
	public ThoughLeadership_page() {
	PageFactory.initElements(mydriver, this);
	}
}
