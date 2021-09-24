package compontentPages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

/**
 * @author amohan31
 *
 */
public class ServicesSectionMedex_page extends Base{
	protected static WebDriver mydriver;
	@FindBy(xpath="//*[@class='services-section section']")
	protected static WebElement servicesSection;

	@FindBy(xpath="//*[@class='services-section section']//*[@class=\"view-button\"]/a")
	protected static WebElement viewAllButton;

	@FindBy(xpath="//*[@class='services-section section']//*[contains(@class,\"list__items services\")]//span[contains(@class,'icon')]")
	protected static List<WebElement> serviceSectionCoulmnIcons;
	
	@FindBy(xpath="//*[@class='services-section section']//*[contains(@class,\"list__items services\")]//span[contains(@class,'icon')]/following-sibling::h3")
	protected static List<WebElement> serviceSectionColumnHeaders;
	
	@FindBy(xpath="//*[@class='services-section section']//*[@class=\"list-items active\"]//ul[@class=\"services-list\"]//li[contains(@class,'list-elements')]/a[@href]")
	protected static List<WebElement> serviceSectionListElements ;
	
	
/*
 * This is to remove main header overlapping other elements
 * 
 * */	
	protected  void scrolltillvisibility(){
		try { ((JavascriptExecutor) mydriver).executeScript("return document.getElementsByClassName('header med-header sticky')[0].remove();");}
		catch (WebDriverException e) {
		}
			scrollToElement(mydriver, servicesSection, logger);
				pleaseWait(1, logger);
	}
	
	public ServicesSectionMedex_page() {
		PageFactory.initElements(mydriver, this); 
	}
}
