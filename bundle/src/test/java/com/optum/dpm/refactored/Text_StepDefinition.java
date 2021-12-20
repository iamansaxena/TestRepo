package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.focusElement;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getPresence;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;
import static org.testng.Assert.fail;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.Text_page;

import core.CustomDataProvider;

public class Text_StepDefinition extends Text_page{
	
	private static final Logger logger = LogManager.getLogger(Text_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"text"})
	public void textFiledsVisibilityAndEmptyFieldCheck(String url) {
		skipNonExistingComponent(url);


			 mydriver.get(url);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(textField)), logger);
			} catch (Exception e) {
				throw new SkipException("Blank text box");
			}
			List<WebElement> texts = mydriver.findElements(By.xpath(textField));
			int i = 0;
			for(WebElement text : texts){
				hardAssert.assertTrue(verifyElementExists(logger, text, text.toString()+" -> "+i));	
				hardAssert.assertFalse(text.getAttribute("innerText").isEmpty());
			i++;
			}
			

	}
	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"text"})
	public void paraSpacingCheck(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(textField)), logger);
			} catch (Exception e) {
				throw new SkipException("Blank text box");
			}
			List<WebElement> texts = mydriver.findElements(By.xpath(textField));
			for(WebElement text : texts){
				if (text.getText().getClass().getName().equals("java.lang.String")) {
					logger.info("Para::> "+ text.getText().getClass().getName());
					logger.info("Para::> "+ text.getAttribute("innerText"));
				}
				else if(text.getText().isEmpty()) {
					fail("P tag can not be blank!!");
				}
			}

	}
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"text"})
	public void buttonFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

			 mydriver.get(url);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(buttons)), logger);
			} catch (Exception e) {
				throw new SkipException("There is no button available");
			}
			List<WebElement> buttonsWithLink = mydriver.findElements(By.xpath(buttons));
			int i = getRandomInteger(buttonsWithLink.size(), 0);
			getPresence(mydriver, buttonsWithLink.get(i).toString().split("xpath: ")[1], 20);
			focusElement(mydriver, buttonsWithLink.get(i));
			String expLink = buttonsWithLink.get(i).getAttribute("href");
			getTest().info("URL Fetched: "+expLink);
			logger.info(expLink);
			String handle = mydriver.getWindowHandle();
			buttonsWithLink.get(i).click();
			pleaseWait(5, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}
	
		
}





