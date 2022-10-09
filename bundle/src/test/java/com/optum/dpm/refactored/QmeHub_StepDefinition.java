package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMConfigurationsUtil.Environment;
import static com.optum.dpm.utils.DPMTestUtils.capitalizeHyphenString;
import static com.optum.dpm.utils.DPMTestUtils.capitalizeWhiteString;
import static com.optum.dpm.utils.DPMTestUtils.getVisibility;
import static com.optum.dpm.utils.DPMTestUtils.moveMouseOnToElement;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
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

import com.optum.dpm.page.model.QmeHub_page;

import core.CustomDataProvider;

public class QmeHub_StepDefinition extends QmeHub_page {

	private static final Logger logger = LogManager.getLogger(QmeHub_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"qme-hub"})
	public void elementVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);


			 mydriver.get(cardUrl);

			softAssert.assertTrue(verifyElementExists(logger, searchInput, "SearchModule"));
			scrollToElement(mydriver, accountTypeSectionLabel, logger);
			softAssert.assertTrue(verifyElementExists(logger, accountTypeSectionLabel, "Account type filter section"));
			scrollToElement(mydriver, expenseTypeSectionLabel, logger);
			softAssert.assertTrue(verifyElementExists(logger, expenseTypeSectionLabel, "Expense type filter section"));
			getResults(logger);
			logger.info("Result Set ==>\n" + results);
			softAssert.assertAll();

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"qme-hub"})
	public void expenseTypeFilterFunctionality(String cardUrl) {
		skipNonExistingComponent(cardUrl);
		if(Environment.trim().equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this method on test environment");
		}

			 mydriver.get(cardUrl);
			
			if(expenseTypeSectionLabel.getAttribute("aria-expanded").equals("false")) {
				expenseTypeSectionLabel.click();
			}
			
			scrollToElement(mydriver, expenseTypeFilter2, logger);
			expenseTypeFilter2.click();
			String filterLabel = expenseTypeFilter2.getText();
			pleaseWait(1, logger);
			List<WebElement> iconsCategory = mydriver.findElements(By.xpath(resultIconCategory));
			List<WebElement> resultLabel = mydriver.findElements(By.xpath(resultElement));
			int i = 0;
			for (WebElement icon : iconsCategory) {
				scrollToElement(mydriver, resultLabel.get(i), logger);
				i++;
				icon.getAttribute("innerHTML");

				hardAssert.assertEquals(icon.getAttribute("innerHTML"),
						capitalizeWhiteString(filterLabel).substring(0, 18));
			}
			i = 0;
			expenseTypeFilter3.click();
			filterLabel = expenseTypeFilter3.getText();
			pleaseWait(1, logger);
			resultLabel = mydriver.findElements(By.xpath(resultElement));
			iconsCategory = mydriver.findElements(By.xpath(resultIconCategory));
			for (WebElement icon : iconsCategory) {
				scrollToElement(mydriver, resultLabel.get(i), logger);
				i++;
				hardAssert.assertEquals(icon.getAttribute("innerHTML"), capitalizeHyphenString(
						capitalizeWhiteString(filterLabel).substring(0, filterLabel.length() - 1)));
			}

			i = 0;
			expenseTypeFilter4.click();
			filterLabel = expenseTypeFilter4.getText();
			pleaseWait(1, logger);
			resultLabel = mydriver.findElements(By.xpath(resultElement));
			iconsCategory = mydriver.findElements(By.xpath(resultIconCategory));
			for (WebElement icon : iconsCategory) {
				scrollToElement(mydriver, resultLabel.get(i), logger);
				i++;
				hardAssert.assertEquals(capitalizeHyphenString(icon.getAttribute("innerHTML")), capitalizeHyphenString(
						capitalizeWhiteString(filterLabel).substring(0, filterLabel.length() - 1)));
			}

	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"qme-hub"})
	public void accountTypeFilterFunctionality(String cardUrl) {
		skipNonExistingComponent(cardUrl);
		if(Environment.trim().equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this method on test environment");
		}

			 
			mydriver.get(cardUrl);

			hardAssert.assertNotEquals(results.get("HSA or MSA"), results.get("Health care FSA"));
			hardAssert.assertNotEquals(results.get("HSA or MSA"), results.get("Dependent care FSA"));
			hardAssert.assertNotEquals(results.get("Health care FSA"), results.get("Dependent care FSA"));
			hardAssert.assertNotEquals(results.get("Limited purpose FSA"), results.get("HSA or MSA"));
			hardAssert.assertNotEquals(results.get("Limited purpose FSA"), results.get("Health care FSA"));
			hardAssert.assertNotEquals(results.get("Limited purpose FSA"), results.get("Dependent care FSA"));


	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"qme-hub"})
	public void resultTags(String cardUrl) {
		skipNonExistingComponent(cardUrl);
		if(Environment.trim().equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this method on test environment");
		}

			 mydriver.get(cardUrl);
			scrollToElement(mydriver, accountTypeFilter2, logger);
			accountTypeFilter2.click();
			scrollToElement(mydriver, accountTypeTag, logger);
			hardAssert.assertEquals(accountTypeFilter2.getText(), accountTypeTag.getText());

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"qme-hub"})
	public void collapseFilterSection(String cardUrl) {
		skipNonExistingComponent(cardUrl);


			 mydriver.get(cardUrl);
			scrollToElement(mydriver, accountTypeSectionLabel, logger);
			
			pleaseWait(1, logger);
			if(accountTypeSectionLabel.getAttribute("aria-expanded").equals("true")) {
				accountTypeSectionLabel.click();
			}
			hardAssert.assertEquals(accountTypeSectionLabel.getAttribute("aria-expanded"), "false");
			scrollToElement(mydriver, expenseTypeSectionLabel, logger);
			
			if(expenseTypeSectionLabel.getAttribute("aria-expanded").equals("true")) {
				expenseTypeSectionLabel.click();
			}
			pleaseWait(1, logger);
			hardAssert.assertEquals(expenseTypeSectionLabel.getAttribute("aria-expanded"), "false");


	}

	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"qme-hub"})
	public void searchFunctionality(String cardUrl) {
		skipNonExistingComponent(cardUrl);
		if(Environment.trim().equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this method on test environment");
		}

			 mydriver.get(cardUrl);
			getVisibility(mydriver, searchInput, 30);
			scrollToElement(mydriver, searchInput, logger);
			String input = mydriver.findElements(By.xpath(resultElement)).get(0).getText();

			searchInput.sendKeys(input);
			searchButton.click();

			hardAssert.assertEquals(mydriver.findElements(By.xpath(resultElement)).get(0).getText(), input);

	}

	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"qme-hub"})
	public void tooltipOnHover(String cardUrl) {
		skipNonExistingComponent(cardUrl);
		if(Environment.trim().equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this method on test environment");
		}

			 mydriver.get(cardUrl);
			getVisibility(mydriver, mydriver.findElements(By.xpath(resultElement)).get(0), 30);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(resultElement)).get(0), logger);
			moveMouseOnToElement(mydriver , mydriver.findElements(By.xpath(resultElement)).get(0));
			pleaseWait(2, logger);
			hardAssert.assertTrue(
					verifyElementExists(logger, mydriver.findElement(By.xpath("//*[@tooltip]")), "ToolTip"));
			logger.info("ToolTip text => " + mydriver.findElement(By.xpath("//*[@tooltip]")).getAttribute("tooltip"));

	}
}
