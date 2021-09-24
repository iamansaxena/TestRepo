package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

import core.Base;

public class RealtimeMedex_page extends Base{
	
	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class=\"realtime\"]")
	protected static WebElement realtimeSection;
	
	@FindBy(xpath="//*[@class=\"realtime\"]//img[@data-day or @data-dusk]")
	protected static WebElement imageSection;
	
	public RealtimeMedex_page() {
		PageFactory.initElements(mydriver, this);
	}
	public static boolean ifElementExists(WebElement element, String errorMessage) {
		boolean status = false;
		try {
			element.isDisplayed();
			status = true;
		} catch (Exception e) {
			status = false;
			throw new SkipException(errorMessage);
		}
		return status;
	}
}
