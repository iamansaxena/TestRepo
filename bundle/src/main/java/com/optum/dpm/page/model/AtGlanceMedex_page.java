package com.optum.dpm.page.model;

import java.util.List;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.optum.dpm.utils.Base;


/**
 * @author amohan31
 *
 */
public class AtGlanceMedex_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "AtGlanceMedex";
	@FindBy(xpath = "//*[@class=\"at-a-glance-carousel section\"]")
	protected static WebElement glanceSection;
	@FindBy(xpath = "//*[@class=\"at-a-glance-carousel section\"]//*[@class=\"glance-slider-list\"]")
	protected static List<WebElement> slides;
	@FindBy(xpath = "//*[@class=\"at-a-glance-carousel section\"]//*[@class=\"bx-prev\"]")
	protected static WebElement prevButton;
	@FindBy(xpath = "//*[@class=\"at-a-glance-carousel section\"]//*[@class=\"bx-next\"]")
	protected static WebElement nextButton;
	@FindBy(xpath = "//*[@class=\"at-a-glance-carousel section\"]//*[@class=\"glance-slider-list\"]//*[@class=\"glance-column1\"]//h5")
	protected static List<WebElement> slideHeader;
	@FindBy(xpath = "//*[@class=\"at-a-glance-carousel section\"]//*[@class=\"glance-slider-list\"]//*[@class=\"glance-column1\"]//p")
	protected static List<WebElement> slideCopy;
	@FindBy(xpath = "//*[@class=\"at-a-glance-carousel section\"]//*[@class=\"glance-slider-list\"]//*[@class=\"glance-column2\"]//h2[not(descendant::span)]")
	protected static List<WebElement> slideColumnHeaders;
	@FindBy(xpath = "//*[@class=\"at-a-glance-carousel section\"]//*[@class=\"glance-slider-list\"]//*[@class=\"glance-column2\"]//h2/span/span")
	protected static List<WebElement> slideColumnIcons;
	@FindBy(xpath = "//*[@class=\"at-a-glance-carousel section\"]//*[@class=\"bx-pager-item\"]")
	protected static List<WebElement> slideBulletButtons;

	/** * @param ele * slide to which you want to switch by clicking next button */
	public void switchToNextSlide(WebElement ele, Logger logger) {
		try {
			for (WebElement e : slides) {
				if (ele.isDisplayed()) {
					break;
				}
				nextButton.click();
			}
		} catch (Exception e) {
			logger.warn("Unable to click on next button");
		}
	}

	/** * @param ele * slide to which you want to switch by clicking prev button */
	public void switchToPrevSlide(WebElement ele, Logger logger) {
		try {
			for (WebElement e : slides) {
				if (ele.isDisplayed()) {
					break;
				}
				prevButton.click();
			}
		} catch (Exception e) {
			logger.warn("Unable to click on prev button");
		}
	}

	/**
	 * * @param i * index of required slide * @return list<WebElement> - List of
	 * coulmns copyies of required slides index
	 */
	public List<WebElement> getCurrentColumnHeaders(int i) {
		return mydriver
				.findElements(By.xpath("//*[@class=\"at-a-glance-carousel section\"]//*[@class=\"glance-slider-list\"]["
						+ i + "]//*[@class=\"glance-column2\"]//p"));
	}

	/**
	 * @param i  slide bullet required to be clicked
	 * @param logger
	 */
	public void switchSlideByBullet(int i, Logger logger) {
		try {
			for (WebElement e : slides) {
				if (slides.get(i).isDisplayed()) {
					break;
				}
				slideBulletButtons.get(i).click();
			}
		} catch (Exception e) {
			logger.warn("Unable to click on next button");
		}
	}
}