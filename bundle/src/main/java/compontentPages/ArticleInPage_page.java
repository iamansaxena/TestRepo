package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class ArticleInPage_page extends Base {
	protected static WebDriver mydriver;
	
	protected static String navTabs = "//*[@id=\"articleInPgNav\"]/ul/li";
	protected static String subSection = "//*[contains(@class,\"content_items\")]";
	
	
	
	
	public ArticleInPage_page() {
		PageFactory.initElements(mydriver, this);
	}
}
