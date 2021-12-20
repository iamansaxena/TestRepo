package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class HorizontalRuleWithNoArrowDivider_page extends Base{
	
	private String author = "Sai Tummala";
	private String tag = "HorizontalRuleWithNoArrowDivider";
	
@FindBy(xpath="//*[@class=\"arrow-divider section\"]")
protected static WebElement horzontalRuleSection;

@FindBy(xpath="//*[@class=\"arrow-divider section\"]//*[@class=\"arrow-divider__heading\"]//*[@class=\"subhead\"]")
protected static WebElement horzontalRuleHeader;

@FindBy(xpath="//*[@class=\"arrow-divider section\"]//*[@class=\"divider divider--arrow false\"]")
protected static WebElement horzontalRuleArrow;
}
