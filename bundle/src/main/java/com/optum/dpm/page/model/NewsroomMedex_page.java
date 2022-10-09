package com.optum.dpm.page.model;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static com.optum.dpm.utils.DPMTestUtils.*;

import com.optum.dpm.utils.Base;

public class NewsroomMedex_page extends Base {
	private static final Logger logger = LogManager.getLogger(NewsroomMedex_page.class);
	private String author = "Rekha Vasugan";
	private String tag = "NewsroomMedex";

	@FindBy(xpath = "//*[@class=\"newsroom parbase section\"]")
	protected static WebElement newsSection;

	@FindBy(xpath = "//*[@class=\"newsroom parbase section\"]//h2[@class=\"hub-news__title\"]")
	protected static WebElement sectionTitle;

	@FindBy(xpath = "//*[@class=\"newsroom parbase section\"]//h2[@class=\"hub-news__title\"]/following-sibling::p")
	protected static WebElement sectionDescription;

	@FindBy(xpath = "//*[@class=\"newsroom parbase section\"]//*[contains(@class,'hub-news__cta')]")
	protected static WebElement sectionCtaButton;

	@FindBy(xpath = "//*[@class=\"newsroom parbase section\"]//*[contains(@class,'hub-item js-HubItem')]/a")
	protected static List<WebElement> newsCards;

	/**
	 * Used to remove main medex static header
	 */
	protected void scrolltillvisibility() {
		scrollToElement(mydriver, newsSection, logger);
		try {
			((JavascriptExecutor) mydriver)
					.executeScript("return document.getElementsByClassName('header med-header sticky')[0].remove();");
		} catch (WebDriverException e) {
		}

		scrollToElement(mydriver, newsSection, logger);
		pleaseWait(1, logger);

	}
}
