package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class BlogFeed_page extends Base {
	protected static WebDriver mydriver;
	protected static String feeds = "//*[@class=\"blog-post\"]";
	protected static String feedTitles = "//*[@class=\"blog-post__title\"]";
	protected static String feedCategories = "//*[@class=\"blog-post__category\"]";
	protected static String feedPublishDate = "//*[@class=\"blog-post__date\"]";
	protected static String feedContentGlimpse = "//*[@class=\"blog-post__excerpt\"]/p";
	protected static String feedReadMoreLinks = "//*[@class=\"blog-post__read-more\"]";
	protected static String paginationPages = "//*[contains(@aria-label,\"Go to page \")]";
	
	@FindBy(xpath = "//*[contains(@id,\"authorError\")]")
	protected static WebElement commentTextFieldErrorMessage;
	
	@FindBy(xpath = "//*[@class=\"blog-form__msg is--success\"]")
	protected static WebElement commentSuccessMsg;
	
	@FindBy(xpath = "//*[@id=\"emailError2\"]")
	protected static WebElement commentEmailFieldErrorMessage;
	
	@FindBy(xpath = "//input[@id=\"email\"]")
	protected static WebElement commentEmailInput;
	
	@FindBy(xpath = "//*[@name=\"comment\"]")
	protected static WebElement commentNameInput;
	
	@FindBy(xpath = "//*[@id=\"authorError1\"]")
	protected static WebElement commentNameFieldErrorMessage;
	
	@FindBy(xpath = "//*[@class=\"pagination__next\"]")
	protected static WebElement paginationNext;
	
	@FindBy(xpath = "//*[@class=\"pagination__prev\"]")
	protected static WebElement paginationPrev;
	
	
	@FindBy(xpath = "//*[@type=\"submit\" and @value=\"Go\"]")
	protected static WebElement paginationGo;
	@FindBy(xpath = "//*[@id=\"go-to-page-number\"]")
	protected static WebElement paginationInput;

	@FindBy(xpath = "//*[@class=\"blog-header__title\"]")
	protected static WebElement blogPageTitle;

	@FindBy(xpath = "//*[@class=\"blog-header__category\"]")
	protected static WebElement blogPageCategory;
	@FindBy(xpath = "//*[@class=\"blog-social-bar__date\"]")
	protected static WebElement blogPagePublishDate;

	@FindBy(xpath = "//button[contains(@class,\"likes\")]/span")
	protected static WebElement blogPageLikeButton;
	
	@FindBy(xpath = "//*[@class=\"blog-detail__content\"]/p[1]")
	protected static WebElement blogPageContent;

	protected static String blogPageTagsLink = "//*[@class=\"blog-tags\"]/ul/li/a";

	@FindBy(xpath = "//*[@class=\"blog-form__form\"]/textarea")
	protected static WebElement commentFormCommentInputField;

	@FindBy(xpath = "//*[@class=\"blog-form__form\"]/button")
	protected static WebElement commentFormSubmitButton;

	@FindBy(xpath = "//*[@class=\"blog-form\"]")
	protected static WebElement blogpageUserCommentsSection;

	@FindBy(xpath = "//*[@class=\"blog-comments__list js-comments-drawer hidden\"]//following-sibling::button")
	protected static WebElement blogpageShowOrHideComments;

	@FindBy(xpath = "//input[@id=\"password\"]")
	protected static WebElement wpPasswordInput;
	
	@FindBy(xpath = "//*[@class=\"js-likes-counter\"]")
	protected static WebElement blogLikeCounter;
	
	@FindBy(xpath = "//button[@type=\"submit\"]")
	protected static WebElement wpSubmitButton;
	
	@FindBy(xpath = "//input[@id=\"usernameOrEmail\"]")
	protected static WebElement wpEmailInput;
	public BlogFeed_page() {
		PageFactory.initElements(mydriver, this);
	}

}
