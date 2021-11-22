package com.optum.dpm.page.model;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class NearbyLocationMedex_page extends Base {
	
	private String author = "Aman Saxena";
	private String tag = "NearbyLocationMedex";

	@FindBy(xpath = "//*[@class=\"near-location section\"]")
	protected static WebElement nearSection;

	@FindBy(xpath = "//*[@class=\"near-location section\"]//h2[@class='h3']")
	protected static WebElement sectionHeader;

	@FindBy(xpath = "//*[@class=\"near-location section\"]//p[contains(@class,'centers-cards__')]")
	protected static List<WebElement> noCenterSection;

	@FindBy(xpath = "//*[@class=\"near-location section\"]//ul[contains(@class,'centers-cards__')]")
	protected static WebElement centerCardSection;

	@FindBy(xpath = "//*[@class=\"near-location section\"]//*[@class=\"nearby-locations animated fadeInFast\"]")
	protected static WebElement sectionImageField;

	/**
	 * This method is used to verify if there is an image path available for the
	 * background image or not
	 * 
	 * @return status
	 * 
	 */
	protected boolean isBgrImageAvailable() {
		if (sectionImageField.getAttribute("style").split("background-image: url(")[0].length() >= 1) {
			return true;
		} else {
			return false;
		}
	}
}
