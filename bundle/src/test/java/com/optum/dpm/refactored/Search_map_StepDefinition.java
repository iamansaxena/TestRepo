package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.scrolltillvisibilityMedex;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.Search_map_page;

import core.CustomDataProvider;

public class Search_map_StepDefinition extends Search_map_page {
	
	private static final Logger logger = LogManager.getLogger(Search_map_StepDefinition.class);
	
	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"search-map"})
	public void SearchMapVisibilityCheck(String cardUrl) {
		

		
			//urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,60);			
			wait.until(ExpectedConditions.visibilityOf(search_map_section));
			scrolltillvisibilityMedex(mydriver, search_map_section, logger);
			//scrollToElement(mydriver, search_map_section, logger);
			scrolltillvisibilityMedex(mydriver, Submit, logger);
			softAssert.assertTrue(search_map_section.isDisplayed());		
			softAssert.assertTrue(centers_cards_section.isDisplayed());
			softAssert.assertTrue(find_location.isDisplayed());
			
			softAssert.assertAll();
	
	}
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"search-map"})
	public void SearchMap(String cardUrl) {
		

		
			//urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(search_map_section));
			scrolltillvisibilityMedex(mydriver, search_map_section, logger);
			//scrollToElement(mydriver, search_map_section, logger);	
			
			softAssert.assertTrue(find_location.isDisplayed());
			scrolltillvisibilityMedex(mydriver, Submit, logger);
			find_location.click();
			String StateZip1 = "55421";
			find_location.sendKeys(StateZip1);
			pleaseWait(6,logger);
			scrolltillvisibilityMedex(mydriver, Submit, logger);
			Submit.click();
			pleaseWait(6,logger);
			
			String Card_name = First_result.getText();
			System.out.println("Card name="+ Card_name);
			
			String StateZip =  State_Zip.getText();
			System.out.println("State Zip="+ StateZip.substring(StateZip.length()-5));
			//Assert.assertEquals((StateZip.length()-5), StateZip1);
			softAssert.assertEquals(StateZip.substring(StateZip.length()-5), StateZip1 );
			getTest().info("Verifing search result: "+ StateZip1.equals(StateZip.length()-5));
			softAssert.assertTrue(Maplocator.isDisplayed());
			softAssert.assertAll();
	
	}
	
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"search-map"})
	public void SelectLocationFromMap(String cardUrl) {
		

		
			//urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(search_map_section));
			scrollToElement(mydriver, search_map_section, logger);	
			
			softAssert.assertTrue(find_location.isDisplayed());
			//find_location.click();
			scrolltillvisibilityMedex(mydriver, Submit, logger);
			find_location.sendKeys("55421");
			Submit.click();
			pleaseWait(6,logger);
			String abc = First_result.getText();
			System.out.println("Search result ="+ abc);
			softAssert.assertTrue(Maplocator.isDisplayed());
			Maplocator2.click();
			String XYZ = First_result.getText();
			System.out.println("Search result 2 ="+ XYZ);	
			softAssert.assertNotEquals(abc, XYZ);
			softAssert.assertAll();
	
	}
	
	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"search-map"})
	public void Selectlocation(String cardUrl) {
		

		
			//urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,30);			
			wait.until(ExpectedConditions.visibilityOf(search_map_section));
			scrollToElement(mydriver, search_map_section, logger);	
			
			softAssert.assertTrue(find_location.isDisplayed());	
			scrolltillvisibilityMedex(mydriver, Submit, logger);
			find_location.sendKeys("55421");
			Submit.click();
			pleaseWait(6,logger);
			First_result.click();
		
			pleaseWait(6,logger);
			boolean flag = false;
			if((!(mydriver.getCurrentUrl().equalsIgnoreCase(cardUrl)))) {
				flag = true;
			}
			
			hardAssert.assertEquals(flag, true);
			
			softAssert.assertAll();
	
	}
}
