package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class ForumHeroBanner_page extends Base {
	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class='mega-hero-banner section']")
	protected static WebElement bannerSection;
	
	@FindBy(xpath="//*[@class='mega-hero-banner section']//*[@class=\"mg-banner__breadcrumb\"]/a")
	protected static WebElement breadcrumbField;
	
	@FindBy(xpath="//*[@class=\"mega-hero-banner section\"]//*[contains(@class,'headerlogo')]/img")
	protected static WebElement logoField;
	
	@FindBy(xpath="//*[@class=\"mega-hero-banner section\"]//*[@class=\"banner-heading\"]")
	protected static WebElement bannerHeading;
	
	@FindBy(xpath="//*[@class=\"mega-hero-banner section\"]//*[@class=\"firstline\"]")
	protected static WebElement bannerFirstLine;
	
	@FindBy(xpath="//*[@class=\"mega-hero-banner section\"]//*[@class=\"secondline\"]")
	protected static WebElement bannerSecondLine;
	
	@FindBy(xpath="//*[@class=\"mega-hero-banner section\"]//*[@class=\"thirdline\"]")
	protected static WebElement bannerThirdLine;
	
	@FindBy(xpath="//*[@class=\"mega-hero-banner section\"]//*[contains(@class,'content-cta')]/a")
	protected static WebElement bannerCtaButton;
	
	@FindBy(xpath="//*[@class=\"mega-hero-banner section\"]//*[@class=\"mega-hero-banner__bgimage\"]")
	protected static WebElement bannerBgImageField;
	
	
	public ForumHeroBanner_page() {
		PageFactory.initElements(mydriver, this);
	}
	

}
