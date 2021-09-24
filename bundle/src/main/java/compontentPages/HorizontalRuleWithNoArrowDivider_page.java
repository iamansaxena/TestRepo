package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class HorizontalRuleWithNoArrowDivider_page extends Base{
public static WebDriver mydriver ;

@FindBy(xpath="//*[@class=\"arrow-divider section\"]")
protected static WebElement horzontalRuleSection;

@FindBy(xpath="//*[@class=\"arrow-divider section\"]//*[@class=\"arrow-divider__heading\"]//*[@class=\"subhead\"]")
protected static WebElement horzontalRuleHeader;

@FindBy(xpath="//*[@class=\"arrow-divider section\"]//*[@class=\"divider divider--arrow false\"]")
protected static WebElement horzontalRuleArrow;



public HorizontalRuleWithNoArrowDivider_page() {
PageFactory.initElements(mydriver, this);
}
	
}
