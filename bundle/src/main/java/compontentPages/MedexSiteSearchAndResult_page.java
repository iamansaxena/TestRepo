package compontentPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class MedexSiteSearchAndResult_page extends Base {

	protected static WebDriver mydriver;

	@FindBy(xpath = "//*[@class=\"site-search section\"]")
	protected static WebElement siteSearchSection;

	@FindBy(xpath = "//*[@class=\"site-search section\"]//h2")
	protected static WebElement headingField;

	@FindBy(xpath = "//*[@class=\"site-search section\"]//p[2]")
	protected static WebElement directionalCopyField;

	@FindBy(xpath = "//*[@class=\"site-search section\"]//input[@type=\"search\"]")
	protected static WebElement searchInputField;

	@FindBy(xpath = "//*[@class=\"site-search section\"]//input[@type=\"search\"]/following-sibling::button")
	protected static WebElement searchButton;

	@FindBy(xpath = "//ul[contains(@class,'autocomplete ui-front')][2]")
	protected static WebElement searchSugestionBox;

	@FindBy(xpath = "//ul[contains(@class,'autocomplete ui-front')]/li[@class=\"ui-menu-item\"]")
	protected static List<WebElement> searchSugestionItems;

	@FindBy(xpath = "//ul[contains(@class,'autocomplete ui-front')]/li[@class=\"ui-menu-item\"]/div")
	protected static List<WebElement> searchSugestionLabel;

	/*
	 * Search Result Page
	 * 
	 */

	@FindBy(xpath = "//*[@class=\"search-keyword-num\"]/strong[1]")
	protected static WebElement noOfResultItems;

	@FindBy(xpath = "//*[@class=\"search-keyword-num\"]/strong[2]")
	protected static WebElement searchedKeyword;

	@FindBy(xpath = "//*[@class=\"search-keyword-num\"]")
	protected static WebElement resultPageInfoHeader;

	@FindBy(xpath = "(//*[@class=\"result-count subhead\"])[1]")
	protected static WebElement resultPageFooterResultItemCount;

	@FindBy(xpath = "//ul[@class=\"results unstyled\"]/li")
	protected static List<WebElement> resultItems;

	@FindBy(xpath = "(//*[@class=\"sr__pagination_nav\"])[1]")
	protected static WebElement paginationSection;

	@FindBy(xpath = "(//*[@class=\"sr__pagination_nav\"])[1]//li[@class='page__item pg-of']")
	protected static WebElement paginationCurrentPage;

	@FindBy(xpath = "(//*[@class=\"sr__pagination_nav\"])[1]//li[@class=\"page__item pg-next\"]")
	protected static WebElement paginationNextButton;

	@FindBy(xpath = "(//*[@class=\"sr__pagination_nav\"])[1]//li[@class=\"page__item pg-prev\"]")
	protected static WebElement paginationPrevButton;

	protected static int getResultCountFromFooter(Logger logger) {
		logger.info("Footer Result Count : "
				+ Integer.valueOf(resultPageFooterResultItemCount.getAttribute("innerText").split("of ")[1]));
		return Integer.valueOf(resultPageFooterResultItemCount.getAttribute("innerText").split("of ")[1]);
	}

	protected static boolean isNoResultMessageAvailable(Logger logger) {
		String exp = "No results found";
		return resultPageInfoHeader.getAttribute("innerText").toLowerCase().contains(exp.toLowerCase());

	}

	protected static boolean moveToNextPage(Logger logger) {
		String currentPage = paginationCurrentPage.getAttribute("innerText");
		logger.info("Current page status: " + currentPage);
		scrolltillvisibility();
		scrollToElement(mydriver, paginationNextButton, logger).click();
		pleaseWait(6, logger);
		return !(currentPage.equals(paginationCurrentPage.getAttribute("innerText")));
	}

	protected static boolean moveToPrevPage(Logger logger) {
		String currentPage = paginationCurrentPage.getAttribute("innerText");
		logger.info("Current page status: " + currentPage);
		scrolltillvisibility();
		scrollToElement(mydriver, paginationPrevButton, logger).click();
		pleaseWait(6, logger);
		return !(currentPage.equals(paginationCurrentPage.getAttribute("innerText")));
	}

	protected static void scrolltillvisibility() {
		scrollToElement(mydriver, paginationSection, logger);
		try {
			((JavascriptExecutor) mydriver)
					.executeScript("return document.getElementsByClassName('header med-header sticky')[0].remove();");
		} catch (WebDriverException e) {
		}

		scrollToElement(mydriver, paginationSection, logger);
		pleaseWait(1, logger);

	}

	public MedexSiteSearchAndResult_page() {
		PageFactory.initElements(mydriver, this);
	}

}
