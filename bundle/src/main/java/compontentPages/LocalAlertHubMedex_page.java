package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class LocalAlertHubMedex_page extends Base{
	
	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class=\"local-alert-hub section\"]")
	protected static WebElement alertSection;
	
	@FindBy(xpath="//*[@class=\"local-alert-hub section\"]//*[@class=\"local-alert__message\"]/following-sibling::p[1]")
	protected static WebElement alertMessage;
	
	@FindBy(xpath="//*[@class=\"local-alert-hub section\"]//*[@class=\"local-alert__wrapper\"][2]/p")
	protected static WebElement alertTags;

	@FindBy(xpath="//*[@class=\"local-alert-hub section\"]//*[contains(@class,'alert-icon')]")
	protected static WebElement alertIcon;
	
	protected static boolean isTagFieldAvailable() {
		try {
			alertTags.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	protected static boolean isMessageFieldAvailable() {
		try {
			alertMessage.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public LocalAlertHubMedex_page() {
		PageFactory.initElements(mydriver, this);
	}

}
