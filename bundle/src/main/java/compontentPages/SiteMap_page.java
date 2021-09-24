package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class SiteMap_page extends Base {
	protected static WebDriver mydriver;

	protected static String links = "//*[@class=\"sitemap parbase section\"]//ul[@class=\"site-map\"]/li/a";
	@FindBy(xpath = "//*[@class=\"sitemap parbase section\"]")
	protected static WebElement siteMapSection;

	public SiteMap_page() {
		PageFactory.initElements(mydriver, this);
	}
}
