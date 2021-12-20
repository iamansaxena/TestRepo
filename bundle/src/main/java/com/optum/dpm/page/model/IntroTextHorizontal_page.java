package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class IntroTextHorizontal_page extends Base {
	private String author = "Prateek Srivastava";
	private String tag = "IntroTextHorizontal";
	
	@FindBy(xpath="//*[@class=\"segment__heading--headline \"]")
	protected static WebElement test;
	
	@FindBy(xpath="//*[@class='intro-arrowtext-divider section']//h2//span")
	protected static WebElement longDivider;
	
	@FindBy(xpath="//*[@class='intro-arrowtext-divider section']//*[@calss='directional-copy']/p")
	protected static WebElement introDirectionalCopy;
	
	@FindBy(xpath="//*[@class='intro-arrowtext-divider section']//span[contains(@class,'align-center')]/h2")
	protected static WebElement shortDivider;

}
