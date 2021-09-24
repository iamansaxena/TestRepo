package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class IconStrip_page extends Base {
	protected static WebDriver mydriver;
	@FindBy(xpath = "//*[@class=\"subhead iconstrip__header\"]")
	protected static WebElement title;

	protected static String segments = "//*[@class=\"iconstrip__item\"]";
	protected static String segmenttitle = "//*[@class=\"iconstrip__title\"]";
	protected static String segmenticon= "//*[@class=\"iconstrip__icon\"]";
	
	
	public IconStrip_page() {

		PageFactory.initElements(mydriver, this);

}
}
