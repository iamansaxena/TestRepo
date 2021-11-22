package com.optum.dpm.page.model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class AccordionV2_page extends Base{

private String author = "Aman Saxena";
private String tag = "AccordionV2";

@FindBy(xpath="//*[@class=\"accordion-v2 section\"]")
protected static WebElement accordionSection;

@FindBy(xpath="//*[@class=\"accordion-v2 section\"]//*[@class=\"accordion-header\"]")
protected static WebElement header;

@FindBy(xpath="//*[@class=\"accordion-v2 section\"]//*[@class=\"accordion-header\"]/following-sibling::p")
protected static WebElement mainDescription;


protected static String accordionNodes= "//*[@class=\"accordion-v2 section\"]//*[@class=\"accordion-list-items\"]/button";


protected static String accordionNodeLabels = "//*[@class=\"accordion-v2 section\"]//*[@class=\"accordion-list-items\"]//h3";

protected static String columnLeftParents = "//*[@class=\"accordion-v2 section\"]//*[@class=\"accordion-list-items\"]//*[contains(@class,\"accordion_content\")]//*[contains(@class,\"accordion__left\")]/p//ancestor::*[contains(@class,\"accordion-list-items\")]";
protected static String accordionNodeLeftColumn = "//*[@class=\"accordion-v2 section\"]//*[@class=\"accordion-list-items\"]//*[contains(@class,\"accordion_content\")]//*[contains(@class,\"accordion__left\")]/p";

protected static String accordionNodeRightColumn = "//*[@class=\"accordion-v2 section\"]//*[@class=\"accordion-list-items\"]//*[contains(@class,\"accordion_content\")]//*[contains(@class,\"accordion__right\")]/p";

protected static String accordionNodeCloseButto = "//*[@class=\"accordion-v2 section\"]//*[@class=\"accordion-list-items\"]//button[contains(@class,\"accordion__close\")]";

}
