package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class SocialModule_page extends Base{

	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class=\"social-module section\"]")
	protected static WebElement moduleSection;

	@FindBy(xpath="//*[@class=\"social-module section\"]//h2[contains(@class,'social-module__header')]")
	protected static WebElement mainSectionHeader;

	@FindBy(xpath="//*[@class=\"social-module section\"]//p[contains(@class,'module__description')]")
	protected static WebElement mainSectionDescription;
	
	@FindBy(xpath="//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]/*[contains(@class,'left')]")
	protected static WebElement socialCardLeft;
	
	@FindBy(xpath="//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]/*[contains(@class,'center')]")
	protected static WebElement socialCardCenter;
	
	@FindBy(xpath="//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]/*[contains(@class,'right')]")
	protected static WebElement socialCardRight;
	
	protected static String mainSocialIconLinks = "//*[@class=\"social-module section\"]//*[contains(@class,'header_social_icons')]/a";
	
	protected static String socialCards = "//*[contains(@class,'social-module__item')][@role][@data-link]";
	
	protected static String socialCardTitles = "//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]//*[contains(@class,'module__item__title small')]";
	
	protected static String socialCardImages = "//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]//img";
	
	protected static String socialCardIcons = "//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]//span[contains(@class,'social-module__item__icon')]";
	
	protected static String socialCardContents = "//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]//p";
	
	protected static String socialCardLinks = "//*[@class=\"social-module section\"]//*[@class=\"social-module__row\"]//a";

	public SocialModule_page() {
		PageFactory.initElements(mydriver, this);
	}


}
