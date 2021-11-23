package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class Search_map_page extends Base {

	protected static WebDriver mydriver;	
	protected static WebElement copyrightText;
	public Search_map_page() {
	PageFactory.initElements(mydriver, this);
	}
	
	@FindBy(xpath="//div[@class='search-map section']")
	protected static WebElement search_map_section;
	
	@FindBy(xpath="//div[@class='search-map section']//*[@class='centers-cards']")
	protected static WebElement centers_cards_section;
	
	@FindBy(xpath="//div[@class='search-map section']//input[@class='find-location']")
	protected static WebElement find_location;
	
	
	@FindBy(xpath="//div[@class='search-map section']//button[@class='fa fa-search map-search-btn']")
	protected static WebElement Submit;
	
	@FindBy(xpath="(//div[@class='search-map section']//h3[@class='center-card__name'])[1]")
	protected static WebElement First_result;
	
	@FindBy(xpath="(//div[@class='search-map section']//span[@class=\"center-card__state-zip micro\"])[1]")
	protected static WebElement State_Zip;
	
	@FindBy(css="div[role=\"button\"]:first-child")
	protected static WebElement Maplocator;
	
	@FindBy(css="div[role=\"button\"]:nth-child(2)")
	protected static WebElement Maplocator2;
	
	
}
