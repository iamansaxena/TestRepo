package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class IconPickerNew_page extends Base {
	private String author = "Prateek Srivastava";
	private String tag = "IconPickerNew";
	
	@FindBy(xpath="//*[@class=\"segment__heading--headline \"]")
	protected static WebElement abc;
	
	protected static String iconField = "//*[contains(@class,\"icon-picker-v2\")]/div[contains(@class,'align')]";
	
	protected static String iconWithLink = "//*[contains(@class,\"icon-picker-v2\")]/div[contains(@class,'align')]/a";

}
