package compontentPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

import core.Base;

public class PromoMessageHubMedex_Page extends Base {

	protected static WebDriver mydriver;

	@FindBy(xpath = "//*[@class=\"promo-message-hub section\"][1]")
	protected static WebElement promoSection;

	@FindBy(xpath = "//*[@class=\"promo-message-hub section\"][1]//*[@class=\"local-message__state\"]/span")
	protected static WebElement promoIcon;

	@FindBy(xpath = "//*[@class=\"promo-message-hub section\"][1]//p")
	protected static List<WebElement> hubMessageFields;

	public static boolean isPromoMessageFieldAvailable() {
		if (hubMessageFields.size() > 2) {
			return true;
		} else {
			return false;
		}
	}

	public  boolean isTagFieldsVisible() {
		if (hubMessageFields.get(hubMessageFields.size() - 1).getText().isEmpty()) {
			throw new SkipException("There's no tag field");
		} else {
			System.out.println(hubMessageFields.get(hubMessageFields.size() - 1).getText());
			return verifyElementExists(logger, hubMessageFields.get(hubMessageFields.size() - 1),
					"Message Field");
		}

	}

	public  boolean isMessageFieldsVisible() {
boolean status = false;
		if (isPromoMessageFieldAvailable() == true) {
			if (isTagFieldsVisible() == true) {
				status =  verifyElementExists(logger, hubMessageFields.get(hubMessageFields.size() - 3),"Message Field");
			} else if (isTagFieldsVisible() == false) {
				status =  verifyElementExists(logger, hubMessageFields.get(hubMessageFields.size() - 2),"Message Field");
			}
		} else if (isPromoMessageFieldAvailable() == false) {
			throw new SkipException("There's no message field!");
		} else {
			status = false;
		}
		return status;

	}

	public PromoMessageHubMedex_Page() {
		PageFactory.initElements(mydriver, this);
	}
}
