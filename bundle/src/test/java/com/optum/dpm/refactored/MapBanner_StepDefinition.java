package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.MapBanner_page;

import core.CustomDataProvider;

public class MapBanner_StepDefinition extends MapBanner_page {
	
	private static final Logger logger = LogManager.getLogger(MapBanner_StepDefinition.class);
	
	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"map-banner"})
	public void MapbannerVisibilityCheck(String cardUrl) {
			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(Center));
			scrollToElement(mydriver, Center, logger);
		
			softAssert.assertTrue(Findlocationinput.isDisplayed());
			softAssert.assertAll();
	
	}
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"map-banner"})
	public void FindCenterByZip(String cardUrl) {
	

		    String handle = mydriver.getWindowHandle();		
			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(Center));
			
			pleaseWait(9,logger);
			if(Findlocationinput.isDisplayed()) {
			Findlocationinput.click();
			Findlocationinput.sendKeys("12345");
			Center.click();
			}
			else {
				System.out.println("textbox not displayed");
			}
			pleaseWait(6,logger);
			boolean flag = false;
			if((!(mydriver.getCurrentUrl().equalsIgnoreCase(cardUrl)))|| (mydriver.getCurrentUrl().equalsIgnoreCase("http://test-medx.optum.com/search-result-answers.html?query=Centers+near+me"))) {
				flag = true;
			}
			
			hardAssert.assertEquals(flag, true);
		
	}
	
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"map-banner"})
	public void FindCenterbyCity(String cardUrl) {
		

		    String handle = mydriver.getWindowHandle();		
			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(Center));
			
			pleaseWait(9,logger);
			if(Findlocationinput.isDisplayed()) {
			Findlocationinput.click();
			Findlocationinput.sendKeys("Las Vegas");
			Center.click();
			}
			else {
				System.out.println("textbox not displayed");
			}
			pleaseWait(6,logger);
			boolean flag = false;
			if((!(mydriver.getCurrentUrl().equalsIgnoreCase(cardUrl)))|| (mydriver.getCurrentUrl().equalsIgnoreCase("http://test-medx.optum.com/search-result-answers.html?query=Centers+near+me"))) {
				flag = true;
			}
			
			hardAssert.assertEquals(flag, true);
		
	}
}
