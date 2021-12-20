package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class ImageResponsiveRendition_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "ImageResponsiveRendition";
	
	@FindBy(xpath="//*[@class=\"image-responsive-rendition section\"]//a")
	protected static WebElement link;
	protected static String images = "(//*[@class=\"image-responsive-rendition section\"]//picture)[1]//source";
}
