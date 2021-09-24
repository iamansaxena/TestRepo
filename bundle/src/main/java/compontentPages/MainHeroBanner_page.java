package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class MainHeroBanner_page extends Base {
	protected static WebDriver mydriver;
	protected static String banners = "//*[@class=\"main-hero-banner section\"]";
	protected static String topLines = "//*[@class=\"subhead banner__block\"]";
	protected static String secondLines = "//*[@class=\"gamma banner__block\"]";
	protected static String videoButtons = "//*[@class=\"banner__main-hero--cta\"]/button";
	protected static String bannerImage = "//*[@class=\"row\"]//input[@class=\"js-main-hero-mobile-image\"]";
	protected static String linkButtons = "//*[@class=\"banner__main-hero--cta\"]/a";
	@FindBy(xpath = "(//*[@class=\"vjs-big-play-button\"])[1]")
	protected static WebElement videoPlayButton;
	@FindBy(xpath = "((//*[@class=\"vjs-control-bar\"])[1]//div[contains(@class,\"vjs-volume\")])[1]")
	protected static WebElement videoMuteButton;
	@FindBy(xpath = "(//*[contains(@class,\"vjs-fullscreen-control\") and @title=\"Non-Fullscreen\"])[1]")
	protected static WebElement videoFullScreenMode;
//	@FindBy(xpath = "//button[@title=\"Non-Fullscreen\"]")
//	protected static WebElement videoNonFullScreenButton;

	@FindBy(xpath = "(//button[@title=\"Fullscreen\"])[1]")
	protected static WebElement videoFullScreenButton;
	@FindBy(xpath = "(//*[@class=\"vjs-remaining-time-display\"])[1]")
	protected static WebElement videoRemainingTime;
	@FindBy(xpath = "//*[@class=\"modal__close\"]")
	protected static WebElement videoModalClose;
	@FindBy(xpath = "(//*[@title=\"Pause\"])[1]")
	protected static WebElement videoPauseButton;
	public MainHeroBanner_page() {
		PageFactory.initElements(mydriver, this);
	}
	
}
