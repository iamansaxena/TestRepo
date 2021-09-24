package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class BioCard_page extends Base {
	protected static WebDriver mydriver;
	protected static String cards = "//*[contains(@class,\"cardbionumber\")]";
	protected static String expandButton = "//*[contains(@class,\"cardbionumber\")]/div[@class=\"card__front\"]/button/span";
	protected static String collapseButton = "//*[contains(@class,\"cardbionumber\")]/div[contains(@class,\"card__back\")]/button/span";
	protected static String speakerNameBack = "//*[contains(@class,\"front\")]//*[@class=\"speaker__name small\"]";
	protected static String speakerNameFront = "//*[contains(@class,\"front\")]//*[@class=\"speaker__name small\"]";
	protected static String speakerTitleFront = "//*[contains(@class,\"front\")]//*[@class=\"speaker__title ul-style\"]";
	protected static String twitterHandle = "//*[@class=\"speaker__twitter ul-style\"]";

	public BioCard_page() {
		PageFactory.initElements(mydriver, this);
	}
}
