package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class IconStrip_page extends Base {
	private String author = "Priyanka Lajpal";
	private String tag = "IconStrip";
	
	@FindBy(xpath = "//*[@class=\"subhead iconstrip__header\"]")
	protected static WebElement title;

	protected static String segments = "//*[@class=\"iconstrip__item\"]";
	protected static String segmenttitle = "//*[@class=\"iconstrip__title\"]";
	protected static String segmenticon= "//*[@class=\"iconstrip__icon\"]";
}
