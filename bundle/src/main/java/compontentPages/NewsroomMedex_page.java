package compontentPages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class NewsroomMedex_page extends Base {

	protected static WebDriver mydriver;

	@FindBy(xpath = "//*[@class=\"newsroom parbase section\"]")
	protected static WebElement newsSection;

	@FindBy(xpath = "//*[@class=\"newsroom parbase section\"]//h2[@class=\"hub-news__title\"]")
	protected static WebElement sectionTitle;

	@FindBy(xpath = "//*[@class=\"newsroom parbase section\"]//h2[@class=\"hub-news__title\"]/following-sibling::p")
	protected static WebElement sectionDescription;

	@FindBy(xpath = "//*[@class=\"newsroom parbase section\"]//*[contains(@class,'hub-news__cta')]")
	protected static WebElement sectionCtaButton;

	@FindBy(xpath = "//*[@class=\"newsroom parbase section\"]//*[contains(@class,'hub-item js-HubItem')]/a")
	protected static List<WebElement> newsCards;

	public NewsroomMedex_page() {
		PageFactory.initElements(mydriver, this);
	}

	/**
	 * Used to remove main medex static header
	 */
	protected void scrolltillvisibility() {
		scrollToElement(mydriver, newsSection, logger);
		try {
			((JavascriptExecutor) mydriver)
					.executeScript("return document.getElementsByClassName('header med-header sticky')[0].remove();");
		} catch (WebDriverException e) {
		}

		scrollToElement(mydriver, newsSection, logger);
		pleaseWait(1, logger);

	}
}
