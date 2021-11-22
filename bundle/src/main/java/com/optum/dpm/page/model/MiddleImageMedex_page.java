package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class MiddleImageMedex_page extends Base{
	
	private String author = "Aman Saxena";
	private String tag = "MiddleImageMedex";

	@FindBy(xpath="//*[@class=\"middle-image section\"]")
	protected static WebElement middleImageSection;
	
	@FindBy(xpath="//*[@class=\"middle-image section\"]//h2")
	protected static WebElement middleImageSectionTitle;
	
	@FindBy(xpath="//*[@class='middle-image section']//*[contains(@class,'middle-image__center-image')]")
	protected static WebElement middleImageSectionCenterImage;
	
	protected static String graphicCirles = "//*[@class='middle-image section']//*[contains(@class,'image__item__graphic')]";
	
	protected static String imageSectionTitles = "//*[@class='middle-image section']//*[contains(@class,'middle-image__item')]//h3";
	
	protected static String imageSectionCopies = "//*[@class='middle-image section']//*[contains(@class,'middle-image__item')]//p[@class='small']";
	
	protected static String imageSectionServiceCopies = "//*[@class='middle-image section']//*[contains(@class,'middle-image__item')]//p[contains(@class,'small middle')]";
	
	protected static String imageSectionExpandButtons = "//*[@class='middle-image section']//*[contains(@class,'middle-image__item')]//span[@class='closed']";
	
	protected static String imageSectionCloseButtons = "//*[@class='middle-image section']//*[contains(@class,'middle-image__item')]//span[@class='opened']";
}
