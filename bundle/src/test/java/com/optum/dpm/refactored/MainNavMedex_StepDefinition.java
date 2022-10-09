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

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.MainNavMedex_page;

import core.CustomDataProvider;

public class MainNavMedex_StepDefinition extends MainNavMedex_page {
	
	private static final Logger logger = LogManager.getLogger(MainNavMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-nav-medex"})
	public void mainDefaultElementVisibilityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, mainNavSection, logger);
			try {
				navItemMainLabel.get(0).isDisplayed();
			} catch (Exception e) {
				// TODO: handle exception
			}
			int i = getRandomInteger(navItemMainLabel.size(), 0);
			navItemLabels.get(i).click();
			pleaseWait(4, logger);
			System.out.println(i);
			String expURL = navItemMainUrl.get(i).getAttribute("href");
			String handle = mydriver.getWindowHandle();
			getTest().info("Exp URL: "+expURL);
			navItemMainUrl.get(i).click();
			assertRedirection(mydriver, logger, getDomainName(url), expURL, handle);

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-nav-medex"})
	public void mainNavTitleAndDescCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, mainNavSection, logger);
			try {
				navItemMainLabel.get(0).isDisplayed();
			} catch (Exception e) {
				// TODO: handle exception
			}
			int i = getRandomInteger(navItemMainLabel.size(), 0);
			navItemLabels.get(i).click();
			pleaseWait(4, logger);
			getTest().info("Is main label field visible: " + navItemMainLabel.get(i).isDisplayed() + " ["
					+ navItemMainLabel.get(i).getText() + "] ");
			hardAssert.assertTrue(
					verifyElementExists(logger, navItemMainLabel.get(i), navItemMainLabel.get(i).getText()));
			hardAssert.assertFalse(navItemMainLabel.get(i).getText().isEmpty());


	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-nav-medex"})
	public void subNavTitleAndDescCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, mainNavSection, logger);
			ArrayList<Boolean> availability = new ArrayList<Boolean>();
			for (WebElement tab : navItemLabels) {
				tab.click();
				pleaseWait(4, logger);
				if (isSubNavTitleAvailable()) {
					availability.add(true);
					int i = getRandomInteger(navItemSubNavColumnHeaders.size(), 0);
					getTest().info("Is sub nav title field visible: " + navItemSubNavColumnHeaders.get(i).isDisplayed()
									+ " [" + navItemSubNavColumnHeaders.get(i).getText() + "] ");
					hardAssert.assertTrue(verifyElementExists(logger, navItemSubNavColumnHeaders.get(i),
							navItemSubNavColumnHeaders.get(i).getText()));
					hardAssert.assertFalse(navItemSubNavColumnHeaders.get(i).getText().isEmpty());
				} else {
					availability.add(false);
				}
			}
			if (!availability.contains(true)) {
				throw new SkipException("No sub nav field is authored");
			}


	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"main-nav-medex"})
	public void subNavItemTitleAndRedirectionCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, mainNavSection, logger);
			ArrayList<Boolean> availability = new ArrayList<Boolean>();
			int j = 0;
			for (WebElement tab : navItemLabels) {
				new MainNavMedex_page();
				navItemLabels.get(j).click();
				pleaseWait(4, logger);
				if (isSubNavItemsAvailable()) {
					availability.add(true);
					int i = getRandomInteger(navItemSubNavItemLabel.size(), 0);
					getTest().info("Is sub nav item title field visible: " + navItemSubNavItemLabel.get(i).isDisplayed()
									+ " [" + navItemSubNavItemLabel.get(i).getText() + "] ");
					hardAssert.assertTrue(verifyElementExists(logger, navItemSubNavItemLabel.get(i),
							navItemSubNavItemLabel.get(i).getText()));
					hardAssert.assertFalse(navItemSubNavItemLabel.get(i).getText().isEmpty());

					String expLink = navItemSubNavItemLinks.get(i).getAttribute("href");
					String handle = mydriver.getWindowHandle();

					navItemSubNavItemLabel.get(i).click();
					mydriver = assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
					mydriver.get(url);
					j++;
				} else {
					availability.add(false);
				}
			}
			if (!availability.contains(true)) {
				throw new SkipException("No sub nav field is authored");
			}


	}
}
