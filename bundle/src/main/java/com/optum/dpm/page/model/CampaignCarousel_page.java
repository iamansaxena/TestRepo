package com.optum.dpm.page.model;

import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class CampaignCarousel_page extends Base {
	private static final Logger logger = LogManager.getLogger(CampaignCarousel_page.class);
	
	private String author = "Aman Saxena";
	private String tag= "CampaignCarousel";
	
	@FindBy(xpath = "//*[@data-type=\"prev\"]/i")
	protected static WebElement prevButton;
	@FindBy(xpath = "//*[@class=\"dpl-carousel\"]")
	protected static WebElement carouselSection;
	
	@FindBy(xpath = "//*[@data-type=\"next\"]/i")
	protected static WebElement nextButton;
	protected static String slideLinks = "//*[@aria-hidden=\"false\"]/following-sibling::a";
	protected static String slideBullets = "//*[@role=\"tablist\"]/button";
	@FindBy(xpath = "(//*[@id=\"dpl-caption-1\"])[1]")
	protected static WebElement firstSlide;
	@FindBy(xpath = "(//*[@id=\"dpl-caption-5\"])[2]")
	protected static WebElement fifthSlide;
	protected void pauseCaraousel() {
		try {
			if(mydriver.findElement(By.xpath("(//*[contains(@class,\"pause\")])[1]")).getAttribute("class").contains("playing"))
			mydriver.findElement(By.xpath("(//*[contains(@class,\"pause\")])[1]")).click();
			pleaseWait(2, logger);
		}catch (Exception e) {
			
		}
	}
}
