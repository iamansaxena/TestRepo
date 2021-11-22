package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.SkipException;

import com.optum.dpm.utils.Base;

public class ArticleCrossLinkMedex_page extends Base {
	
	private String author = "Aman Saxena";
	
	@FindBy(xpath = "//*[contains(@class,'article-xlink')]")
	protected static WebElement section;

	@FindBy(xpath = "//*[contains(@class,'article-xlink')]//h3")
	protected static WebElement sectionTitle;

	@FindBy(xpath = "//*[contains(@class,'article-xlink')]//p")
	protected static WebElement sectionCopy;

	@FindBy(xpath = "//*[contains(@class,'article-xlink')]//a")
	protected static WebElement sectionCtaLink;

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
