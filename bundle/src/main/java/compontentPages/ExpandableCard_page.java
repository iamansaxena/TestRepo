package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class ExpandableCard_page extends Base {
	protected static WebDriver mydriver;
	protected static String closeTopButtons = "//*[@class=\"expanding__card-expand-icon oi-box-close\"]";

	protected static String openTopButtons = "//div[contains(@class,\"cardnumber\")]//span[@class=\"oi-box-open expanding__card-expand-icon\"]";
	protected static String cards = "//*[contains(@class,'cardnumber')]";
	protected static String cardImages = "//*[contains(@class,'cardnumber')]//div[@class=\"expanding-card__icon expanding-card__icon--image\"]/img";
	protected static String cardWithoutImages = "//*[contains(@class,'cardnumber')]/div/div[@class=\"expanding-card__icon\"]/span";
	protected static String cardTitles = "//*[contains(@class,'cardnumber')]//*[contains(@class,\"expanding-card__heading\")]";
	protected static String cardDescriptions = "//*[contains(@class,'cardnumber')]//*[contains(@class,\"expanding-card__heading\")]/following-sibling::p";
	protected static String openBottomButtons = "//*[contains(@class,'cardnumber') and not(contains(@class,\"card-z\"))]//button[contains(@class,\"open-button\")]";
	@FindBy(xpath = "(//*[contains(@class,\"card-Z\") and contains(@class,\"expanded\")]/div[contains(@class,\"card__content\")])[last()]")
	protected static WebElement expandedCardListContents;
	protected static String closeBottomButtons = "(//*[contains(@class,\"card-Z\") ]//button[contains(@class,\"open-button\")])[last()]";
	protected static String openBottomButtonsWithLinks = "//*[contains(@class,'cardnumber') ]/div[contains(@class,\"card__content\")][1]/a";
	protected static String expandedCardDescriptions = "(//*[contains(@class,\"card-Z\")]/div[contains(@class,\"expanded\")])[last()]";

	public ExpandableCard_page() {
		PageFactory.initElements(mydriver, this);
	}
}
