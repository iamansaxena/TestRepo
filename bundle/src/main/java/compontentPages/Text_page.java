package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class Text_page extends Base{
protected static WebDriver mydriver;

protected static String textField = "//*[@class=\"text-component text-inner\"]/p[not(./b or ./a or ./span or ./img)]";
protected static String buttons = "//*[@class=\"text-component text-inner\"]//*[contains(@class,'button') and not(contains(@class,'disabled'))]/a[@href]";


public Text_page() {
PageFactory.initElements(mydriver, this);
}
}
