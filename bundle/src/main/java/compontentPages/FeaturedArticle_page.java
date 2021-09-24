package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;


public class FeaturedArticle_page extends Base
{
	protected static WebDriver mydriver;
	@FindBy(xpath = "//*[@class=\"featured-articles section\"]//*[contains(@class,'featured-article__title ')]")
	protected static WebElement title;

	protected static String cardimage = "//*[contains(@class,\"featured-article__img js-HubItemImg\")]";
	protected static String cards = "//*[@class=\"featured-article js-HubItemCard\"]";
	protected static String cardtitle= "//*[@class=\"featured-article__title js-HubItemTitle\"]";
	protected static String description= "//*[@class=\"featured-article__desc js-HubItemDesc\"]";
	
	public FeaturedArticle_page() {

		PageFactory.initElements(mydriver, this);
}
}