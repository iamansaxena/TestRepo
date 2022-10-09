package com.optum.dpm.refactored;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.UtilityNav_page;

import core.CustomDataProvider;
import junit.framework.Assert;

public class UtilityNav_StepDefinition extends UtilityNav_page {

	private static final Logger logger = LogManager.getLogger(ContactUsTabMedex_StepDefinition.class);
	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"Utility"})
	public void UtilityNavVisibilityCheck(String cardUrl) {
		

		
			
			mydriver.get(cardUrl);
			WebDriverWait wait = new WebDriverWait(mydriver,60);			
			wait.until(ExpectedConditions.visibilityOf(dpl_utility));		   		
			softAssert.assertTrue(dpl_utility.isEnabled());
			System.out.println("size = "+ NavItems.size());			
			softAssert.assertAll();
			
			
	
	}
	
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"Utility"})
	public void UtilityNavitemselctorNewTab(String cardUrl) {
		

		
			
			mydriver.get(cardUrl);
			String handle =mydriver.getWindowHandle();
		
			WebDriverWait wait = new WebDriverWait(mydriver,60);			
			wait.until(ExpectedConditions.visibilityOf(dpl_utility));		   		
			softAssert.assertTrue(dpl_utility.isEnabled());
			String loginLink = SelectNavitems(mydriver,3,logger);										
			softAssert.assertAll();
			 assertRedirection( mydriver,  logger,  getDomainName(cardUrl), loginLink,handle);
			
	
	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"Utility"})
	public void UtilityNavitemselctorSameTab(String cardUrl) {

		
		    mydriver.get(cardUrl);
			String handle =mydriver.getWindowHandle();
			WebDriverWait wait = new WebDriverWait(mydriver,60);			
			wait.until(ExpectedConditions.visibilityOf(dpl_utility));		   		
			softAssert.assertTrue(dpl_utility.isEnabled());
			String loginLink = SelectNavitems(mydriver,6,logger);			
			
			 assertRedirection( mydriver,  logger,  getDomainName(cardUrl), loginLink,handle);
								
			softAssert.assertAll();
	
	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"Utility"})
	public void UtilityNavitemItemList(String cardUrl) {

		
		    mydriver.get(cardUrl);
			String handle =mydriver.getWindowHandle();
			WebDriverWait wait = new WebDriverWait(mydriver,60);			
			wait.until(ExpectedConditions.visibilityOf(dpl_utility));		   		
			softAssert.assertTrue(dpl_utility.isEnabled());
			String loginLink=null;
			if(aboutUsDropdown.isDisplayed()) {
				aboutUsDropdown.click();
			}
			int count =0;
			
			for (WebElement element : NavList) {
			  
			
				 if("Leadership".equals(element.getText()))	
				 {
					 String url = "div.dpl-utility-nav li.dpl-utility__item:nth-child(4) li:nth-child("+count+") a";
					 WebElement url1 = mydriver.findElement(By.cssSelector(url));
					 loginLink= url1.getAttribute("href");
					 element.click();
					
				 }
				 count++;
				
			   
				}			
			
		//	String loginLink = SelectNavitems(mydriver,5,logger);			
			
			assertRedirection( mydriver,  logger,  getDomainName(cardUrl), loginLink,handle);
								
			softAssert.assertAll();
	
	}
	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"Utility"})
	public void UtilityNavitemInheritance(String cardUrl) {

		
		mydriver.get(cardUrl);
		String handle =mydriver.getWindowHandle();
	
		WebDriverWait wait = new WebDriverWait(mydriver,60);			
		wait.until(ExpectedConditions.visibilityOf(dpl_utility));		   		
		softAssert.assertTrue(dpl_utility.isEnabled());
		 
		String Nav2= Inheritance.getText();
		String loginLink = SelectNavitems(mydriver,5,logger);					
		wait.until(ExpectedConditions.visibilityOf(dpl_utility));		   		
		softAssert.assertTrue(dpl_utility.isEnabled());
		 
		pleaseWait(6,logger);
		softAssert.assertAll();
		String Nav2Child= Inheritance.getText();
	    Assert.assertNotSame(Nav2Child, Nav2);
	    System.out.println("Nav2 " + Nav2);
	    System.out.println("Nav2Child " + Nav2Child);
		 assertRedirection( mydriver,  logger,  getDomainName(cardUrl), loginLink,handle);;
	    
	}


}
