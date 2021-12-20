package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class FooterLegalBar_page extends Base{
	private String author = "Aman Saxena";
	private String tag = "FooterLegalBar";
	
	protected static String privacyLink = "//*[contains(@class,\"footer-legal-bar\")]//*[@class=\"terms__privacy horizontal-list\"]/li[@class=\"terms__privacy__list\"]/a";
	protected static String legalNoticeLink = "//*[contains(@class,\"footer-legal-bar\")]//li[@class=\"lang-notice__item\"]/a";
	@FindBy(xpath="//*[contains(@class,\"footer-legal-bar\")]//*[@class=\"footer-copyright\"]")
	protected static WebElement copyrightText;
	
}
