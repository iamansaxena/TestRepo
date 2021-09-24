package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class MapBanner_page extends Base {

	protected static WebDriver mydriver;	
	protected static WebElement copyrightText;
	public MapBanner_page() {
	PageFactory.initElements(mydriver, this);
	}
	@FindBy(xpath="//*[@class='map-banner__find-location']")
	protected static WebElement map_banner_section;
	
	@FindBy(xpath="//*[@class='map-banner__find-location']//label[@id='find-location-label']")
	protected static WebElement Findlocation;
	
	@FindBy(xpath="//*[@class='map-banner__find-location']//input[@id='find-location']")
	protected static WebElement Findlocationinput;
	
	@FindBy(xpath="//*[@class='map-banner__find-location']//span[text()='a Center Near You']")
	protected static WebElement Center;
	
	
	
	
}
