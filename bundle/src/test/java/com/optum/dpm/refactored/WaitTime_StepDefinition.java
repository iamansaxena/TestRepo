package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.WaitTime_page;

import core.CustomDataProvider;

public class WaitTime_StepDefinition extends WaitTime_page {

	private static final Logger logger = LogManager.getLogger(WaitTime_StepDefinition.class);
	
	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void clockIconVisiblityCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			scrollToElement(mydriver, clockIcon, logger);
			hardAssert.assertTrue(verifyElementExists(logger, clockIcon, "Clock Icon"));

		
	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void urgentCareCenterDetailsCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			List<WebElement> urgentCareCenter = mydriver.findElements(By.xpath(urgentCareCenters));
			int i = 0;
			for(WebElement center : urgentCareCenter) {
				scrollToElement(mydriver, center, logger);
				softAssert.assertFalse(mydriver.findElements(By.xpath(urgentCareCenterNames)).get(i)
						.getAttribute("innerText").isEmpty());
				softAssert.assertFalse(mydriver.findElements(By.xpath(urgentCareCenterStatus)).get(i).getAttribute("innerText").isEmpty());
				softAssert.assertFalse(mydriver.findElements(By.xpath(urgentCareCenterAddress)).get(i).getAttribute("innerText").isEmpty());
				softAssert.assertAll();
			i++;}

		}
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void convenientCareCenterDetailsCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			List<WebElement> convenientCareCenter = mydriver.findElements(By.xpath(convenientCareCenters));
			int i = 0;
			for(WebElement center : convenientCareCenter) {
				scrollToElement(mydriver, center, logger);
				softAssert.assertFalse(mydriver.findElements(By.xpath(convenientCareCenterNames)).get(i)
						.getAttribute("innerText").isEmpty());
				softAssert.assertFalse(mydriver.findElements(By.xpath(convenientCareCenterStatus)).get(i).getAttribute("innerText").isEmpty());
				softAssert.assertFalse(mydriver.findElements(By.xpath(convenientCareCenterAddress)).get(i).getAttribute("innerText").isEmpty());
				softAssert.assertAll();
			i++;}

		}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void convenientCareSectionHeaderAndDescCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			boolean title = false;
			boolean description = false;
			try {
			convenientCareTitle.isDisplayed();	
			}catch (Exception e) {
				title=false;
			}
			try {
				convenientCareDesc.isDisplayed();	
				}catch (Exception e) {
					description=false;
				}
			
		if(title!=false) {
			scrollToElement(mydriver, convenientCareTitle, logger);
			hardAssert.assertFalse(convenientCareTitle.getAttribute("innerText").isEmpty());
		}
		if(description!=false) {
			scrollToElement(mydriver, convenientCareDesc, logger);
			hardAssert.assertFalse(convenientCareDesc.getAttribute("innerText").isEmpty());
		}
		

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void urgentCareSectionHeaderAndDescCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			boolean title = false;
			boolean description = false;
			try {
			urgentCareTitle.isDisplayed();	
			}catch (Exception e) {
				title=false;
			}
			try {
				urgentCareDesc.isDisplayed();	
				}catch (Exception e) {
					description=false;
				}
			
		if(title!=false) {
			scrollToElement(mydriver, urgentCareTitle, logger);
			hardAssert.assertFalse(urgentCareTitle.getAttribute("innerText").isEmpty());
		}
		if(description!=false) {
			scrollToElement(mydriver, urgentCareDesc, logger);
			hardAssert.assertFalse(urgentCareDesc.getAttribute("innerText").isEmpty());
		}
		

	}
	
	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"wait-time"})
	public void componentMainHeaderAndDescCheck(String expCardUrl) {
		skipNonExistingComponent(expCardUrl);

		

			
			mydriver.get(expCardUrl);
			
			try {
			componentTitle.isDisplayed();	
			}catch (Exception e) {
				throw new SkipException("There is no component title field available!");
			}
		
			scrollToElement(mydriver, componentTitle, logger);
			hardAssert.assertFalse(componentTitle.getAttribute("innerText").isEmpty());

	}
}
