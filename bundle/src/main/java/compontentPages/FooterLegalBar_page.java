package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class FooterLegalBar_page extends Base{
	
	protected static WebDriver mydriver;
	protected static String privacyLink = "//*[contains(@class,\"footer-legal-bar\")]//*[@class=\"terms__privacy horizontal-list\"]/li[@class=\"terms__privacy__list\"]/a";
	protected static String legalNoticeLink = "//*[contains(@class,\"footer-legal-bar\")]//li[@class=\"lang-notice__item\"]/a";
	@FindBy(xpath="//*[contains(@class,\"footer-legal-bar\")]//*[@class=\"footer-copyright\"]")
	protected static WebElement copyrightText;
	public FooterLegalBar_page() {
	PageFactory.initElements(mydriver, this);
	}
	
	
	
}
