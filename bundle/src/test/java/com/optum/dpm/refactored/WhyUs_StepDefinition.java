package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.WhyUs_page;

import core.CustomDataProvider;

public class WhyUs_StepDefinition extends WhyUs_page {

	private static final Logger logger = LogManager.getLogger(WhyUs_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"why-us"})
	public void elementVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			
			mydriver.get(cardUrl);
			scrollToElement(mydriver, whyUsSection, logger);
			List<WebElement> icons = mydriver.findElements(By.xpath(columnIcons));
			softAssert.assertTrue(verifyElementExists(logger, sectionHeader, "Section Header"));
			getTest().info("Checked if the section header is available: "
					+ verifyElementExists(logger, sectionHeader, "Section Header"));

			softAssert.assertTrue(verifyElementExists(logger, icons.get(0), "Column-1 icon"));
			getTest().info("Checked if the Column-1 icon is available: "
					+ verifyElementExists(logger, icons.get(0), "Column-1 Icon"));

			softAssert.assertTrue(verifyElementExists(logger, icons.get(1), "Column-2 icon"));
			getTest().info("Checked if the Column-2 icon is available: "
					+ verifyElementExists(logger, icons.get(1), "Column-2 Icon"));

			softAssert.assertTrue(verifyElementExists(logger, icons.get(2), "Column-3 icon"));
			getTest().info("Checked if the Column-3 icon is available: "
					+ verifyElementExists(logger, icons.get(2), "Column-3 Icon"));
			softAssert.assertTrue(verifyElementExists(logger, icons.get(3), "Column-4 icon"));
			getTest().info("Checked if the Column-4 icon is available: "
					+ verifyElementExists(logger, icons.get(3), "Column-4 Icon"));

			softAssert.assertAll();

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"why-us"})
	public void copyFieldVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			
			mydriver.get(cardUrl);
			scrollToElement(mydriver, whyUsSection, logger);
			ArrayList<WebElement> authoredCopyFields = new ArrayList<>();

			try {
				for (WebElement ele : mydriver.findElements(By.xpath(columnDescriptions))) {
					if (ele.isDisplayed()) {
						authoredCopyFields.add(ele);
					}
				}
				if (authoredCopyFields.isEmpty()) {
					throw new Exception();
				}

			} catch (Exception e) {
				throw new SkipException("There is no copy field available");
			}
			int i = 1;
			for (WebElement field : authoredCopyFields) {
				hardAssert.assertTrue(verifyElementExists(logger, field, "Copy field " + i));
				getTest().info("Checked if column-" + i + " copy field is available: "
						+ verifyElementExists(logger, field, "Copy field " + i));
				i++;

			}


	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"why-us"})
	public void titleLinkRedirectionCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			
			mydriver.get(cardUrl);
			scrollToElement(mydriver, whyUsSection, logger);

			try {
				mydriver.findElement(By.xpath(columnTitle)).isDisplayed();
				mydriver.findElement(By.xpath(columnLinks)).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There is no Title field available");
			}

			List<WebElement> titles = mydriver.findElements(By.xpath(columnTitle));
			List<WebElement> links = mydriver.findElements(By.xpath(columnLinks));
			int i = getRandomInteger(titles.size(), 0);
			String expLink = links.get(i).getAttribute("href");
			String handle = mydriver.getWindowHandle();
			titles.get(i).click();
			pleaseWait(4, logger);
			assertRedirection(mydriver, logger, getDomainName(cardUrl), expLink, handle);

	}
}
