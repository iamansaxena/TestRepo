package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class IconVerticalLabel_page extends Base {
	private String author = "Prateek Srivastava";
	private String tag = "IconVerticalLabel";
	
	@FindBy(xpath="//*[@class=\"segment__heading--headline \"]")
	protected static WebElement headline;

}
