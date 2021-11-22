package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class InfographicHtml5_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "InfographicHtml5";
	
	@FindBy(xpath="//*[@class=\"infographic-html5 section\"]//following-sibling::script")
	protected static WebElement scripts; 
}
