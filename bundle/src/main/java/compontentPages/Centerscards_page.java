package compontentPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.Base;

public class Centerscards_page extends Base {
	protected static WebDriver mydriver=null;
	Base obj = new Base();
	
	@FindBy(xpath = "//div[@class='centers-cards section']//input[@id='find-location']")
	protected static WebElement enterCityTextBox;	
	
	@FindBy(xpath = "//div[@class='centers-cards section']//*[@class='fa fa-search map-search-btn']")
	protected static WebElement findCenterBtn;
	
	@FindBy(xpath = "//div[@class='centers-cards section']")
	protected static WebElement centersCardsSection;
	
	@FindBy(xpath = "//section/div/ul/li[contains(@class,'center-card')]")
	protected static List<WebElement> centerList;
	
	@FindBy(xpath = "//section[@class = 'centers-cards']//*[@class='centers-cards__results'][@aria-hidden='false']")
	protected static WebElement noCenterList;
	

	public Centerscards_page() {
		PageFactory.initElements(mydriver, this);
	}
	
	/**
	 * This method will return true if Center found or else it will return false
	 * @param mydriver
	 * @param location
	 * @param logger
	 * @return boolean
	 */
	public boolean findCenter(WebDriver mydriver, String location, Logger logger) {
		logger.info("Finding Center");
		if(enterCityTextBox.isDisplayed()) {
			enterCityTextBox.sendKeys(location);
			findCenterBtn.click();
			mydriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			if(centerList.isEmpty()) {
				logger.info("No center found");
				return false;
			}
			else {
				logger.info("Center found");
			}
		}		
		return true;
	}	

}
 
