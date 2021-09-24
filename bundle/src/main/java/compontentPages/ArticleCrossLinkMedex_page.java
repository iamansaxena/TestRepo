package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;

import core.Base;

public class ArticleCrossLinkMedex_page extends Base {

	protected static WebDriver mydriver;

	@FindBy(xpath = "//*[contains(@class,'article-xlink')]")
	protected static WebElement section;

	@FindBy(xpath = "//*[contains(@class,'article-xlink')]//h3")
	protected static WebElement sectionTitle;

	@FindBy(xpath = "//*[contains(@class,'article-xlink')]//p")
	protected static WebElement sectionCopy;

	@FindBy(xpath = "//*[contains(@class,'article-xlink')]//a")
	protected static WebElement sectionCtaLink;

	public ArticleCrossLinkMedex_page() {
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
