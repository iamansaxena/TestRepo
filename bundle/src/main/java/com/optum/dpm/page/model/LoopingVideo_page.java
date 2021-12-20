package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class LoopingVideo_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "LoopingVideo";
	
	@FindBy(xpath = "//*[contains(@id,\"loop-video-tag\")]")
	protected static WebElement videoFrame ;
	
	@FindBy(xpath = "//button[contains(@id,\"js-loop-pause-play\")]")
	protected static WebElement playPauseButton ;
	
	protected static String text = "//*[contains(@class,\"large-text \")]";
}
