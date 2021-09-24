package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class AboutUs_page extends Base {
	protected static WebDriver mydriver;

	@FindBy(xpath="//*[@class=\"about-us section\"]")
	protected static WebElement aboutUsSection;
	
	protected static String tabs = "(//*[@class=\"about-us section\"])[1]//li/a";
	
	public AboutUs_page() {

		PageFactory.initElements(mydriver, this);
	}
}
