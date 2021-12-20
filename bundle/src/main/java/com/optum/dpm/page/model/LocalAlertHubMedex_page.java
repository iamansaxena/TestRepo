package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class LocalAlertHubMedex_page extends Base{
	
	private String author = "Aman Saxena";
	private String tag = "LocalAlertHubMedex";
	
	@FindBy(xpath="//*[@class=\"local-alert-hub section\"]")
	protected static WebElement alertSection;
	
	@FindBy(xpath="//*[@class=\"local-alert-hub section\"]//*[@class=\"local-alert__message\"]/following-sibling::p[1]")
	protected static WebElement alertMessage;
	
	@FindBy(xpath="//*[@class=\"local-alert-hub section\"]//*[@class=\"local-alert__wrapper\"][2]/p")
	protected static WebElement alertTags;

	@FindBy(xpath="//*[@class=\"local-alert-hub section\"]//*[contains(@class,'alert-icon')]")
	protected static WebElement alertIcon;
	
	protected static boolean isTagFieldAvailable() {
		try {
			alertTags.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	protected static boolean isMessageFieldAvailable() {
		try {
			alertMessage.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
