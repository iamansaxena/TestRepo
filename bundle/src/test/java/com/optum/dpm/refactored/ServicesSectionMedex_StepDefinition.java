package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.ServicesSectionMedex_page;

import core.CustomDataProvider;

/**
 * @author amohan31
 *
 */
public class ServicesSectionMedex_StepDefinition extends ServicesSectionMedex_page {

	private static final Logger logger = LogManager.getLogger(ServicesSectionMedex_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"services-section"})
	public void mainDefaultElementVisibilityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, servicesSection, logger);

			int i = 1;
			scrolltillvisibility();
			pleaseWait(3, logger);

			for (WebElement icon : serviceSectionCoulmnIcons) {
				softAssert.assertTrue(verifyElementExists(logger, icon, "Blade 1's Icon-" + i));
				getTest().info("Checked if Icon-" + i + " available: " + verifyElementExists(logger, icon, "Icon-" + i));
				softAssert.assertTrue(
						verifyElementExists(logger, serviceSectionColumnHeaders.get(i - 1), "Column Header-" + i));
				softAssert.assertFalse(serviceSectionColumnHeaders.get(i - 1).getAttribute("innerText").isEmpty());
				getTest().info("Checked if Column-" + i + " header available: "
						+ verifyElementExists(logger, serviceSectionColumnHeaders.get(i - 1), "Column Header-" + i));

				i++;
			}


	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"services-section"})
	public void viewAllButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, servicesSection, logger);
			scrolltillvisibility();
			String expLink = viewAllButton.getAttribute("href");
			String handle = mydriver.getWindowHandle();
			scrollToElement(mydriver, viewAllButton, logger);
			viewAllButton.click();
			pleaseWait(5, logger);
			getTest().info("Checking 'view all' button redirection");
			getTest().info("Expected Link: " + expLink);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"services-section"})
	public void blade1ServiceListCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, servicesSection, logger);
			scrolltillvisibility();

			try {
				serviceSectionListElements.get(0).isDisplayed();
			} catch (Exception e) {
				getTest().info("Checking if service list is avaialable: " + false);
				throw new SkipException("There're no service list available");
			}
			getTest().info("Checking if service list is avaialable: " + true);
			for (WebElement service : serviceSectionListElements) {
				scrollToElement(mydriver, service, logger);
				hardAssert.assertTrue(verifyElementExists(logger, service, service.getAttribute("innerText")));
				hardAssert.assertFalse(service.getAttribute("innerText").isEmpty());
			}

	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"services-section"})
	public void blade1ServicesLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, servicesSection, logger);
			scrolltillvisibility();

			try {
				serviceSectionListElements.get(0).isDisplayed();
			} catch (Exception e) {
				getTest().info("Checking if service list is avaialable: " + false);
				throw new SkipException("There're no service list available");
			}
			getTest().info("Checking if service list is avaialable: " + true);
			int i = getRandomInteger(serviceSectionListElements.size(), 0);
			scrollToElement(mydriver, serviceSectionListElements.get(i), logger);
			String expLink = serviceSectionListElements.get(i).getAttribute("href");
			String handle = mydriver.getWindowHandle();
			serviceSectionListElements.get(i).click();
			pleaseWait(5, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);


	}

}
