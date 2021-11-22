package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.SkipException;

import com.optum.dpm.utils.Base;

public class RealtimeMedex_page extends Base{
	
	private String author = "Aman Saxena";
	private String tag = "RealtimeMedex";
	
	@FindBy(xpath="//*[@class=\"realtime\"]")
	protected static WebElement realtimeSection;
	
	@FindBy(xpath="//*[@class=\"realtime\"]//img[@data-day or @data-dusk]")
	protected static WebElement imageSection;
	
	public static boolean ifElementExists(WebElement element, String errorMessage) {
		boolean status = false;
		try {
			element.isDisplayed();
			status = true;
		} catch (Exception e) {
			status = false;
			throw new SkipException(errorMessage);
		}
		return status;
	}
}
