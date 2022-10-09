package com.optum.dpm.page.model;

import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.SkipException;

import com.optum.dpm.utils.Base;

public class PromoMessageHubMedex_Page extends Base {
	private static final Logger logger = LogManager.getLogger(PromoMessageHubMedex_Page.class);
	
	private String author = "Rekha Vasugan";
	private String tag = "PromoMessageHubMedex";
	
	@FindBy(xpath = "//*[@class=\"promo-message-hub section\"][1]")
	protected static WebElement promoSection;

	@FindBy(xpath = "//*[@class=\"promo-message-hub section\"][1]//*[@class=\"local-message__state\"]/span")
	protected static WebElement promoIcon;

	@FindBy(xpath = "//*[@class=\"promo-message-hub section\"][1]//p")
	protected static List<WebElement> hubMessageFields;

	public static boolean isPromoMessageFieldAvailable() {
		if (hubMessageFields.size() > 2) {
			return true;
		} else {
			return false;
		}
	}

	public  boolean isTagFieldsVisible() {
		if (hubMessageFields.get(hubMessageFields.size() - 1).getText().isEmpty()) {
			throw new SkipException("There's no tag field");
		} else {
			System.out.println(hubMessageFields.get(hubMessageFields.size() - 1).getText());
			return verifyElementExists(logger, hubMessageFields.get(hubMessageFields.size() - 1),
					"Message Field");
		}

	}

	public  boolean isMessageFieldsVisible() {
boolean status = false;
		if (isPromoMessageFieldAvailable() == true) {
			if (isTagFieldsVisible() == true) {
				status =  verifyElementExists(logger, hubMessageFields.get(hubMessageFields.size() - 3),"Message Field");
			} else if (isTagFieldsVisible() == false) {
				status =  verifyElementExists(logger, hubMessageFields.get(hubMessageFields.size() - 2),"Message Field");
			}
		} else if (isPromoMessageFieldAvailable() == false) {
			throw new SkipException("There's no message field!");
		} else {
			status = false;
		}
		return status;

	}
}
