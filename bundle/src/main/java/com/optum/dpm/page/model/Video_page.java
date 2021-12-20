package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class Video_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "Video";

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//video//track[@kind=\"captions\"]")
protected static WebElement captionField;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//button[@title=\"Play\"]")
protected static WebElement footerPlayButton;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//button[@title=\"Play Video\"]")
protected static WebElement bigPlayButton;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@aria-label=\"progress bar\"]")
protected static WebElement timeline;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//div[@aria-label=\"video player\"]")
protected static WebElement videoContainerStatus;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@aria-label=\"progress bar\"]")
protected static WebElement currentProgressTime;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@class=\"vjs-remaining-time-display\"]")
protected static WebElement remainingTime;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@class=\"vjs-resolution-button-staticlabel\"]")
protected static WebElement qualityControl;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@title=\"Fullscreen\"]")
protected static WebElement fullscreenButton;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@title=\"Pause\"]")
protected static WebElement footerPauseButton;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@title=\"Non-Fullscreen\"]")
protected static WebElement nonFullscreenButton;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//button[@title=\"Show Transcript\"]")
protected static WebElement transcriptButton;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//button[@class=\"video__transcript-close oi-no\"]")
protected static WebElement closeTranscriptSectionButton;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@class=\"video__transcript-title\"]")
protected static WebElement transcriptTitle;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@class=\"vjs-tech\"]")
protected static WebElement videoContainer;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@title=\"Unmute\"]")
protected static WebElement unmute;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@title=\"Mute\"]")
protected static WebElement mute;

@FindBy(xpath="(//*[@class=\"videoComponent section\"])[1]//*[@title=\"Captions\"]")
protected static WebElement captionButton;
protected static String qualityOptions = "//*[@title=\"Quality\"]//*[@class=\"vjs-menu-content\"]//li";
protected static String captionOptions = "//*[@title=\"Captions\"]//ul[@class=\"vjs-menu-content\"]/li";

}
