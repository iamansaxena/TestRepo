package compontentPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.Base;

public class MCL_page extends Base {
	protected static WebDriver mydriver = null;
	@FindBy(xpath = "//*[@class=\"mcl-pills__copy-btn\"]")
	protected static WebElement copyLinkButton;

	@FindBy(xpath = "//*[@id=\"mclSearch\"]")
	protected static WebElement searchInput;

	@FindBy(xpath = "//*[@class=\"button button--reverse js-clear-filters-btn\"]")
	protected static WebElement clearFilterButton;

	@FindBy(xpath = "//*[@id=\"mcl-paginator-go-page\"]")
	protected static WebElement goToPageInput;

	@FindBy(xpath = "//*[contains(@class,\"mcl-paginator__goto\")]")
	protected static WebElement goToPageSubmitButton;

	@FindBy(xpath = "//*[@id=\"mcl-sorter\"]")
	protected static WebElement sortDropDown;

	@FindBy(xpath = "//*[@class=\"mcl-search__button\"]")
	protected static WebElement searchButton;

	@FindBy(xpath = "//*[@aria-label=\"previous\"]")
	protected static WebElement paginationPrev;

	@FindBy(xpath = "//*[@aria-label=\"next\"]")
	protected static WebElement paginationNext;

	protected static String filterCategory = "//*[@class=\"mcl-tray\"]/div[@class=\"mcl-filter\"]/button";

	protected static String pages = "(//*[@class=\"mcl-paginator__pages\"]/li)[position() > 1 and not(position() > 5)]/a";

	protected static String resultCards = "//*[@class=\"mcl-card\"]";
	protected static String resultCardNames = "//*[@class=\"mcl-card\"]/div[contains(@class,\"mcl-card__content\")]/h3";
	protected static String resultCardSize = "//*[@class=\"mcl-card\"]/div[@class=\"mcl-card__content\"]/p";
	protected static String resultLastModifiedDate = "//*[@class=\"mcl-card\"]/div[@class=\"mcl-card__content\"]/div/*[@class=\"mcl-card__date\"]";
	protected static String resultMarkFavoriteButton = "//*[@class=\"mcl-card\"]/div[@class=\"mcl-card__footer\"]/a[@class=\"mcl-card__btn js-favorite-btn\"]";
	protected static String resultShareButton = "//*[@class=\"mcl-card\"]/div[@class=\"mcl-card__footer\"]/a[@class=\"mcl-card__btn js-favorite-btn\"]/following-sibling::a[1]";
	protected static String resultDownloadButton = "//*[@class=\"mcl-card\"]/div[@class=\"mcl-card__footer\"]/a[@class=\"mcl-card__btn js-favorite-btn\"]/following-sibling::a[2]";
	protected static String resultFilterTags = "//*[@class=\"mcl-pills__buttons\"]/button";
	protected static String resultImages = "//*[@class=\"mcl-card__image\"]";
	protected static String resultCardsHoverEffect = "//*[@class=\"mcl-card\"]/a/div/p";
	@FindBy(xpath = "//*[@id=\"mclCopyURL\"]")
	protected static WebElement copyLinkDisplayField;

	// ASSET DETAIL PAGE//

	@FindBy(xpath = "//*[@class=\"av-preview-img\"]")
	protected static WebElement assetPreviewField;

	@FindBy(xpath = "//*[@class=\"asset-view section\"]//*[@class=\"av-heading\"]")
	protected static WebElement assetPageHeader;

	@FindBy(xpath = "//*[@class=\"asset-view section\"]//h1[contains(@class,\"av-title\")]")
	protected static WebElement assetName;

	@FindBy(xpath = "//*[@class=\"av-toolbar-item js-HubItemFavorite\"]/span")
	protected static WebElement assetPageFavoriteButton;

	@FindBy(xpath = "//*[@class=\"av-toolbar-item js-HubItemShare\"]")
	protected static WebElement assetPageShareButton;

	@FindBy(xpath = "//*[@class=\"av-toolbar-item\"]")
	protected static WebElement assetPageDownloadButton;

	@FindBy(xpath = "//*[@class=\"av-meta av-meta-technical\"]")
	protected static WebElement technicalDetailSection;

	@FindBy(xpath = "//*[@class=\"milli owneremail\"]/*[@data-owneremail]")
	protected static WebElement assetOwnerEmail;

	protected static String assetInfoKeys = "//*[@class=\"av-meta av-meta-basic bdsm \"]/div/div[1]/span";
	protected static String assetInfoValues = "//*[@class=\"av-meta av-meta-basic bdsm \"]/div/div[2]/span";

	protected static String assetTechInfoKeys = "//*[@class=\"av-meta av-meta-technical\"]/*[@class=\"row\"]/div[1]";
	protected static String assetTechInfoValues = "//*[@class=\"av-meta av-meta-technical\"]/*[@class=\"row\"]/div[2]";
	// Main Navigation Section//

	@FindBy(xpath = "//*[@class=\"main-nav__list--main-item\" and @lpos=\"main navigation : your assets\"]")
	protected static WebElement myAssetDropDown;
	@FindBy(xpath = "//*[@class=\"subnav_item\"]/a[@href=\"/favorites.html\"]")
	protected static WebElement myFavoritesSubNav;

	protected static List<WebElement> getFiltersUnderCategory(int indexOfFilterCategory) {
		String a = "(//*[@class=\"mcl-tray\"]/div[@class=\"mcl-filter\"])[" + indexOfFilterCategory + "]//ul/li/label";
		List<WebElement> elementList = mydriver.findElements(By.xpath(a));

		return elementList;
	}

	public MCL_page() {
		PageFactory.initElements(mydriver, this);
	}

	protected void unFavAllAsset() {
		scrollToElement(mydriver, myAssetDropDown, logger);
		myAssetDropDown.click();
		myFavoritesSubNav.click();
		pleaseWait(2, logger);
		List<WebElement> assets = mydriver
				.findElements(By.xpath("//*[@class=\"asset-item__detail--desc js-HubItemTitle\"]"));
		for (WebElement asset : assets) {
			scrollToElement(mydriver, asset, logger);
			scrollToElement(mydriver, asset.findElement(By.xpath("(parent::div/following-sibling::div/div)")), logger);
			asset.findElement(By.xpath("(parent::div/following-sibling::div/div)")).click();
		}
	}

	protected static void visitMainSearchPage(String url) {
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, 60);

		wait.until(ExpectedConditions.attributeToBeNotEmpty(
				mydriver.findElement(By.xpath("//*[@class=\"assets-share--loading-bar\" ]")), "style"));
	}

	protected void clearFilters() {
		scrollToElement(mydriver, clearFilterButton, logger);
		clearFilterButton.click();
	}
}
