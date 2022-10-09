package com.optum.dpm.page.model;

import static com.optum.dpm.reports.ExtentTestManager.getTest;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.optum.dpm.utils.Base;

public class MedExpressCenters_page extends Base {
	private static final Logger logger = LogManager.getLogger(MedExpressCenters_page.class);
	
	private String author = "Rekha Vasugan";
	private String tag = "MedExpressCenters";
	
	@FindBy(xpath = "//*[@class='centers-sitemap parbase section']")
	protected static WebElement medExpressCenterSection;
	
	@FindBy(xpath = "//*[@class='centers-sitemap parbase section']//a[contains(@href,'medexpress')]")
	protected static List<WebElement> medExpresscenterList;
	
	@FindBy(xpath = "//div[@class='medcenter']//h1[@class='medcenter__title']")
	protected static WebElement medExpresscenterListTitle;
	
	/**
	 * This method will return true if center Link text is same as location page's title
	 * Main purpose of this method to verify all center links are clickable
	 * @param mydriver
	 * @param logger
	 * @return boolean
	 */
	public boolean verifyCenterList(WebDriver mydriver, Logger logger) {
		logger.info("verifying Center");
		boolean returnValue = true;
		int flag =0;		
		for(WebElement element : medExpresscenterList) {
			WebDriverWait wait = new WebDriverWait(mydriver, 10); 
			wait.until(ExpectedConditions.elementToBeClickable(element));
			String mainCenterName = element.getText();
			String[] centerName = mainCenterName.split(","); // Center name is same in both pages, Split center name alone
			JavascriptExecutor js = (JavascriptExecutor)mydriver;
			js.executeScript("arguments[0].click()", element);
			mydriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String parentWindow = mydriver.getWindowHandle();
			Set<String> childWindow = mydriver.getWindowHandles();
			Iterator<String> itr = childWindow.iterator();
			while(itr.hasNext()) {
				String window = itr.next();
				if (!parentWindow.equalsIgnoreCase(window)) {
					mydriver.switchTo().window(window);
					wait.until(ExpectedConditions.visibilityOf(medExpresscenterListTitle));
					if(medExpresscenterListTitle.getText().contains(centerName[1])) {
						getTest().info("MedExpress Center name is same as medCenter Title : " + mainCenterName );
					}
					else {
						getTest().info("MedExpress Center name is not same as medCenter Title :\n MedExpress Center name " + mainCenterName + "\n medCenter Title : "+medExpresscenterListTitle.getText());
						returnValue = false;
					}
					mydriver.close();
					mydriver.switchTo().window(parentWindow);
				}				
			}
			flag++;
			if(flag == 5) {
				break;
			}		
		}
	return returnValue;
}
	
	
}
	 
