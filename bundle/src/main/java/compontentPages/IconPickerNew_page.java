package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class IconPickerNew_page extends Base {
	
	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class=\"segment__heading--headline \"]")
	protected static WebElement abc;
	
	protected static String iconField = "//*[contains(@class,\"icon-picker-v2\")]/div[contains(@class,'align')]";
	
	protected static String iconWithLink = "//*[contains(@class,\"icon-picker-v2\")]/div[contains(@class,'align')]/a";
	
	public IconPickerNew_page() {

	PageFactory.initElements(mydriver, this);
	}


}
