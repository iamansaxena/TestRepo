package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.assertRedirection;
import static com.optum.dpm.utils.DPMTestUtils.getDomainName;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.selectByOptionIndex;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.CenterAccordion_page;

import core.CustomDataProvider;

public class CenterAccordion_StepDefinition extends CenterAccordion_page {

	private static final Logger logger = LogManager.getLogger(CenterAccordion_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void mainDefaultElementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		int i = 1;
		List<WebElement> totalBlades = mydriver.findElements(By.xpath(blades));
		for (WebElement blade : totalBlades) {
			scrollToElement(mydriver, blade, logger);
			softAssert.assertTrue(verifyElementExists(logger, blade, "Blade " + i));
			getTest().info("Checked if blade-" + i + " available: " + verifyElementExists(logger, blade, "Blade " + i));
			i++;

		}
		if (i != 4) {
			softAssert.fail("Expected '3' expanding blades but found '" + i + "' blades");
		}
		i = 1;

		softAssert.assertTrue(verifyElementExists(logger, blade4CtaLink, "CTA Blade "));
		getTest().info("Checked if CTA blade available: " + verifyElementExists(logger, blade4CtaLink, "CTA Blade "));

		List<WebElement> bladelabels = mydriver.findElements(By.xpath(bladeLabels));
		for (WebElement blade : bladelabels) {
			scrolltillvisibility();
			softAssert.assertTrue(verifyElementExists(logger, blade, "Blade label " + i));
			softAssert.assertFalse(blade.getAttribute("innerText").isEmpty());
			getTest().info("Checked if blade-" + i + " label available: "
					+ verifyElementExists(logger, blade, "Blade label " + i));
			i++;
		}
		i = 1;
		// Default elements specific to Blade 1
		scrolltillvisibility();
		bladelabels.get(0).click();
		pleaseWait(3, logger);

		List<WebElement> blade1Icons = mydriver.findElements(By.xpath(blade1icons));
		List<WebElement> blade1Headers = mydriver.findElements(By.xpath(blade1ColumnHeaders));
		for (WebElement icon : blade1Icons) {
			softAssert.assertTrue(verifyElementExists(logger, icon, "Blade 1's Icon-" + i));
			getTest().info("Checked if blade-1's Icon-" + i + " available: "
					+ verifyElementExists(logger, icon, "Blade 1's Icon-" + i));
			softAssert
					.assertTrue(verifyElementExists(logger, blade1Headers.get(i - 1), "Blade 1's Column Header-" + i));
			softAssert.assertFalse(blade1Headers.get(i - 1).getAttribute("innerText").isEmpty());

			getTest().info("Checked if blade-1's Column-" + i + " header available: "
					+ verifyElementExists(logger, blade1Headers.get(i - 1), "Blade 1's Column Header-" + i));

			i++;
		}
		i = 1;
		// Default elements specific to Blade 2
		scrolltillvisibility();
		bladelabels.get(1).click();
		pleaseWait(2, logger);

		softAssert.assertTrue(verifyElementExists(logger, blade2ContentDescription, "Blade 2's content description"));
		softAssert.assertFalse(blade2ContentDescription.getAttribute("innerText").isEmpty());
		getTest().info("Checked if Blade 2's content description available: "
				+ verifyElementExists(logger, blade2ContentDescription, "Blade 2's content description"));
		softAssert.assertTrue(verifyElementExists(logger, blade2ContentHeader, "Blade 2's content header"));
		softAssert.assertFalse(blade2ContentHeader.getAttribute("innerText").isEmpty());
		getTest().info("Checked if Blade 2's content header available: "
				+ verifyElementExists(logger, blade2ContentHeader, "Blade 2's content header"));
		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void bladeExpandableFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		List<WebElement> totalBlades = mydriver.findElements(By.xpath(blades));
		totalBlades.get(0).click();
		scrollToElement(mydriver, totalBlades.get(1), logger).click();
		List<WebElement> expanded = mydriver.findElements(By.xpath(expandedBlade));
		getTest().info("Checking if only one blade expands at a time: " + String.valueOf(expanded.size()).contains("1"));
		hardAssert.assertEquals(expanded.size(), 1);
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void blade1ViewAllRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		mydriver.findElements(By.xpath(blades)).get(0).click();
		String expLink = viewAllButton.getAttribute("href");
		String handle = mydriver.getWindowHandle();
		scrollToElement(mydriver, viewAllButton, logger);
		viewAllButton.click();
		pleaseWait(5, logger);
		getTest().info("Checking 'view all' button redirection");
		getTest().info("Expected Link: " + expLink);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void ctaBladeRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		String expLink = blade4CtaLink.getAttribute("href");
		String handle = mydriver.getWindowHandle();
		scrollToElement(mydriver, blade4CtaLink, logger).click();
		getTest().info("Checking CTA Blade redirection");
		getTest().info("Expected Link: " + expLink);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void blade1ServiceListCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		mydriver.findElements(By.xpath(blades)).get(0).click();
		boolean isAvailable = false;

		try {
			mydriver.findElement(By.xpath(blade1ListElements)).isDisplayed();
			isAvailable = true;
		} catch (Exception e) {
			getTest().info("Checking if service list is avaialable: " + isAvailable);
			throw new SkipException("There're no service list available");
		}
		getTest().info("Checking if service list is avaialable: " + isAvailable);
		for (WebElement service : mydriver.findElements(By.xpath(blade1ListElements))) {
			scrollToElement(mydriver, service, logger);
			hardAssert.assertTrue(verifyElementExists(logger, service, service.getAttribute("innerText")));
			hardAssert.assertFalse(service.getAttribute("innerText").isEmpty());
		}
	}

	@Test(priority = 6, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void blade1ServicesLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();

		scrollToElement(mydriver, mydriver.findElements(By.xpath(blades)).get(0), logger).click();
		boolean isAvailable = false;

		try {
			mydriver.findElement(By.xpath(blade1ListElements)).isDisplayed();
			isAvailable = true;
		} catch (Exception e) {
			getTest().info("Checking if service list is avaialable: " + isAvailable);
			throw new SkipException("There're no service list available");
		}
		getTest().info("Checking if service list is avaialable: " + isAvailable);
		List<WebElement> links = mydriver.findElements(By.xpath(blade1ListElements));
		int i = getRandomInteger(links.size(), 0);
		scrollToElement(mydriver, links.get(i), logger);
		String expLink = links.get(i).getAttribute("href");
		String handle = mydriver.getWindowHandle();
		links.get(i).click();
		pleaseWait(5, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 7, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void blade2InvestmentOptionsListCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		mydriver.findElements(By.xpath(blades)).get(1).click();
		boolean isAvailable = false;
		try {
			mydriver.findElement(By.xpath(blade2InsuranceResultElements)).isDisplayed();
			isAvailable = true;
		} catch (Exception e) {
			getTest().info("Checking if insurance options list is avaialable: " + isAvailable);
			throw new SkipException("There're no Insurance option available");
		}
		getTest().info("Checking if insurance options list is avaialable: " + isAvailable);
		for (WebElement element : mydriver.findElements(By.xpath(blade2InsuranceResultElements))) {
			scrollToElement(mydriver, element, logger);
			hardAssert.assertTrue(verifyElementExists(logger, element, element.getAttribute("innerText")));
			hardAssert.assertFalse(element.getAttribute("innerText").isEmpty());
		}

	}

	@Test(priority = 8, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void blade2StateSelectionFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		mydriver.findElements(By.xpath(blades)).get(1).click();
		boolean isAvailable = false;
		try {
			if (hiddenSearch.isDisplayed() == false) {
				isAvailable = false;
				throw new Exception();
			} else {
				isAvailable = true;
			}

		} catch (Exception e) {
			getTest().info("Checking if insurance options list is avaialable: " + isAvailable);
			throw new SkipException("There're no Insurance option available");
		}
		getTest().info("Checking if insurance options list is avaialable: " + isAvailable);
		scrollToElement(mydriver, blade2InsuranceStateSelect, logger);
		selectByOptionIndex(blade2InsuranceStateSelect, 3, logger);
		Select ele = new Select(blade2InsuranceStateSelect);
		hardAssert.assertEquals(ele.getFirstSelectedOption(),
				selectByOptionIndex(blade2InsuranceStateSelect, 3, logger));
	}

}
