package compontentPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class ErStats_page extends Base {
	
	protected static WebDriver mydriver;
	
	
	@FindBy(xpath="//*[@class='er-stats section']")
	protected static WebElement erStatTopSection;
	
	@FindBy(xpath="//*[@class='er-stats section']//h2")
	protected static WebElement ModuleTitle;
	
	@FindBy(xpath="//*[@class='er-stats section']//p[@class='er__desc__copy']/following-sibling::p[1]")
	protected static WebElement StatIntroCopy;
	
	@FindBy(xpath="//*[@class='er-stats section']//h3[@class='er__copy-subhead']")
	protected static WebElement StatSubHeading;
	
	@FindBy(css="div.er__copy  p:nth-child(3)")
	protected static WebElement StatSubCopy;
	
	@FindBy(xpath="//*[@class='er-stats section']//div[@class='stat-chart']/span")
	protected static WebElement StatSubheadingOne;
	
	@FindBy(xpath="//*[@class='er-stats section']//div[@class='stat-chart-cb']/span[1]")
	protected static WebElement ColorCopyBold;
	
	@FindBy(xpath="//*[@class='er-stats section']//div[@class='stat-chart-cb']/span[2]")
	protected static WebElement ColorCopy;
	
	@FindBy(xpath="//*[@class='er-stats section']//img")
	protected static WebElement Image;
	
	@FindBy(xpath="//*[@class='er-stats section']//article[@class='reference-cite']")
	protected static WebElement Reference;
	
	public ErStats_page() {

		PageFactory.initElements(mydriver, this);
		}
	
	/** Method is used to verify element availability in DOM
	 * @param e WebElement variable name
	 * @param logger is used to return the True/False value
	 * @return True/False value
	 */
	protected static boolean isFieldAvailable(WebElement e, Logger logger)
	{
		try {
			e.isDisplayed();
			 return true;
		} catch (Exception e2) {
			logger.error("Element not found" + e);
			return false;
			// TODO: handle exception
		}
	}

}
