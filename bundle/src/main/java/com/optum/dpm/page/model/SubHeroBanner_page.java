package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class SubHeroBanner_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "SubHeroBanner";
	
	protected static String banners = "//*[contains(@class,\"sub-hero-banner\")]";
	protected static String topLines = "//*[@class=\"sub-banner__mainheading\"]";
	protected static String secondLines = "//*[@class=\"sub-banner__content\"]/p[1]";
	protected static String linkButtons = "//*[@class=\"banner__main-hero--cta\"]/a";
	protected static String videoButtons = "//*[@class=\"banner__main-hero--cta\"]/button";
	protected static String bannerImage = "//*[@class=\"js-sub-hero-bgimg\"]";
	protected static String descriptions = "//*[@class=\"thirdline-desc\"]";

	@FindBy(xpath = "(//*[@class=\"vjs-big-play-button\"])[1]")
	protected static WebElement videoPlayButton;
	@FindBy(xpath = "((//*[@class=\"vjs-control-bar\"])[1]//div[contains(@class,\"vjs-volume\")])[1]")
	protected static WebElement videoMuteButton;
	@FindBy(xpath = "//*[contains(@class,\"vjs-fullscreen vjs-user-\")]")
	protected static WebElement videoFullScreenMode;
	@FindBy(xpath = "//button[@title=\"Non-Fullscreen\"]")
	protected static WebElement videoNonFullScreenButton;

	@FindBy(xpath = "(//button[@title=\"Fullscreen\"])[1]")
	protected static WebElement videoFullScreenButton;
	@FindBy(xpath = "(//*[@class=\"vjs-remaining-time-display\"])[1]")
	protected static WebElement videoRemainingTime;
	@FindBy(xpath = "//*[@class=\"modal__close\"]")
	protected static WebElement videoModalClose;
	@FindBy(xpath = "(//*[@title=\"Pause\"])[1]")
	protected static WebElement videoPauseButton;
}
