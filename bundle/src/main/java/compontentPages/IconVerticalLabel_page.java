package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class IconVerticalLabel_page extends Base {
	
	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class=\"segment__heading--headline \"]")
	protected static WebElement headline;
	public IconVerticalLabel_page() {

	PageFactory.initElements(mydriver, this);
	}

}
