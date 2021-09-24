package compontentPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class MainNavMedex_page extends Base{
	
	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class=\"main-nav-medex\"]")
	protected static WebElement mainNavSection;
	
	@FindBy(xpath="//*[@class=\"main-nav-medex\"]//a[contains(@class,\"main-nav__list\")]/span[1]")
	protected static List<WebElement> navItemLabels;
	
	@FindBy(xpath="//*[@class=\"main-nav-medex\"]//*[@class=\"subnav__heading_content\"]/a")
	protected static List<WebElement> navItemMainUrl;
	
	@FindBy(xpath="//*[@class=\"main-nav-medex\"]//*[@class=\"subnav__heading_content\"]/a")
	protected static List<WebElement> navItemMainLabel;
	
	@FindBy(xpath="//*[@class=\"main-nav-medex\"]//*[@class=\"subnav__heading_content\"]/p[@class=\"subnav__heading--desc\"]")
	protected static List<WebElement> navItemMainDescription;
	
	@FindBy(xpath="//*[@class=\"main-nav-medex\"]//*[@class=\"nav__sub_container open\"]//*[@class=\"subnav__heading epsilon\"]/span")
	protected static List<WebElement> navItemSubNavColumnHeaders;
	
	@FindBy(xpath="//*[@class=\"main-nav-medex\"]//*[@class=\"nav__sub_container open\"]//section[contains(@class,'dropdown_subnav')]/ul//a")
	protected static List<WebElement> navItemSubNavItemLinks;
	@FindBy(xpath="//*[@class=\"main-nav-medex\"]//*[@class=\"nav__sub_container open\"]//section[contains(@class,'dropdown_subnav')]/ul//a/span[1]")
	protected static List<WebElement> navItemSubNavItemLabel;
	
	protected boolean isSubNavItemsAvailable(){
		boolean status = false;
		try {
			navItemSubNavItemLabel.get(0).isDisplayed();
			status = true;
		} catch (Exception e) {
			status = false;
		}
		return status;
	}
	protected boolean isSubNavTitleAvailable(){
		boolean status = false;
		try {
			navItemSubNavColumnHeaders.get(0).isDisplayed();
			status = true;
		} catch (Exception e) {
			status = false;
		}
		return status;
	}
	public MainNavMedex_page() {
		PageFactory.initElements(mydriver, this);
	}

}
