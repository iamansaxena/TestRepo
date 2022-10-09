package com.optum.dpm.refactored;

import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollPageByDimensions;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElementWithoutWait;
import static com.optum.dpm.utils.DPMTestUtils.selectByOptionIndex;
import static com.optum.dpm.utils.DPMTestUtils.selectByOptionName;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.MutualFundTable_page;

import core.CustomDataProvider;

public class MutualFundsTable_StepDefinition extends MutualFundTable_page {
	
	private static final Logger logger = LogManager.getLogger(MutualFundsTable_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"mutual-funds-table"})
	public void columnSortFunctionalityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			int i = 0;
			List<WebElement> assetColumnHead = mydriver.findElements(By.xpath(assetClassColumnHeader));
			for (WebElement assetColumn : assetColumnHead) {
				new MutualFundsTable_StepDefinition();
				scrollToElement(mydriver, mydriver.findElements(By.xpath(assetClassColumnHeader)).get(i), logger);
				String currentSortState = mydriver.findElements(By.xpath(assetClassColumnHeader)).get(i)
						.getAttribute("data-sort_direction");
				mydriver.findElements(By.xpath(assetClassColumnHeader)).get(i).click();
				pleaseWait(2,logger);
				hardAssert.assertNotEquals(mydriver.findElements(By.xpath(assetClassColumnHeader)).get(i)
						.getAttribute("data-sort_direction"), currentSortState);
				i++;
			}
			logger.info("Asset Column Sort is working fine");
			i = 0;
			List<WebElement> expenseColumnHead = mydriver.findElements(By.xpath(expenseRatioColumnHeader));
			for (WebElement expenseColumn : expenseColumnHead) {
				scrollToElement(mydriver, mydriver.findElements(By.xpath(expenseRatioColumnHeader)).get(i), logger);
				String currentSortState = mydriver.findElements(By.xpath(expenseRatioColumnHeader)).get(i)
						.getAttribute("data-sort_direction");
				mydriver.findElements(By.xpath(expenseRatioColumnHeader)).get(i).click();
				pleaseWait(2,logger);
				hardAssert.assertNotEquals(mydriver.findElements(By.xpath(expenseRatioColumnHeader)).get(i)
						.getAttribute("data-sort_direction"), currentSortState);
				i++;
			}
			logger.info("Expense Column Sort is working fine");
			i = 0;
			List<WebElement> ratingColumn = mydriver.findElements(By.xpath(ratingColumnHeader));
			for (WebElement rating : ratingColumn) {
				scrollToElement(mydriver, mydriver.findElements(By.xpath(ratingColumnHeader)).get(i), logger);
				String currentSortState = mydriver.findElements(By.xpath(ratingColumnHeader)).get(i)
						.getAttribute("data-sort_direction");
				mydriver.findElements(By.xpath(ratingColumnHeader)).get(i).click();
				pleaseWait(4,logger);
				hardAssert.assertNotEquals(
						mydriver.findElements(By.xpath(ratingColumnHeader)).get(i).getAttribute("data-sort_direction"),
						currentSortState);
				i++;
			}
			logger.info("Rating Column Sort is working fine");
			i = 0;
			List<WebElement> yearToDateColumn = mydriver.findElements(By.xpath(yearToDateColumnHeader));
			for (WebElement yearToDate : yearToDateColumn) {
				scrollToElement(mydriver, mydriver.findElements(By.xpath(yearToDateColumnHeader)).get(i), logger);
				String currentSortState = mydriver.findElements(By.xpath(yearToDateColumnHeader)).get(i)
						.getAttribute("data-sort_direction");
				mydriver.findElements(By.xpath(yearToDateColumnHeader)).get(i).click();
				pleaseWait(4,logger);
				hardAssert.assertNotEquals(mydriver.findElements(By.xpath(yearToDateColumnHeader)).get(i)
						.getAttribute("data-sort_direction"), currentSortState);

				i++;
			}
			logger.info("Performance Column Sort is working fine");

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"mutual-funds-table"})
	public void categorySearchFilterationCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			categoryFilter.click();
			selectByOptionIndex(categoryFilter, 1, logger);
			filterSubmitButton.click();

			scrollPageByDimensions(mydriver, 100, 500);
			pleaseWait(2, logger);
			hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(equityElements)).get(0),
					"Equity Table"));

	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"mutual-funds-table"})
	public void assetClassSearchFilterationCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			categoryFilter.click();
			Select select = new Select(assetClassFilter);
			int i = getRandomInteger(select.getOptions().size(), 1);
			String selectedClass = select.getOptions().get(i).getText();
			selectByOptionIndex(assetClassFilter, i, logger);
			filterSubmitButton.click();

			scrollPageByDimensions(mydriver, 100, 500);
			pleaseWait(2, logger);
			List<WebElement> assetClasses = mydriver.findElements(By.xpath("//*[@class=\"mutual-fund__asset-class\"]"));
			for (WebElement text : assetClasses) {

				hardAssert.assertEquals(text.getText(), selectedClass);
			}

	}

	@Test(priority = 4, enabled = false)
	public void elementUniquenessCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);

			List<WebElement> equityElement = mydriver.findElements(By.xpath(equityElements));
			List<WebElement> lifeStyleElement = mydriver.findElements(By.xpath(lifeStyleElements));
			List<WebElement> targetDateElement = mydriver.findElements(By.xpath(targetDateElements));
			List<WebElement> fixedIncomeElement = mydriver.findElements(By.xpath(fixedIncomeElements));
			List<WebElement> specialityElement = mydriver.findElements(By.xpath(specialityElements));
			ArrayList<String> fullSet = new ArrayList<>();
			equityElement.forEach(a -> {
				fullSet.add(a.getText());
			});
			lifeStyleElement.forEach(a -> {
				fullSet.add(a.getText());
			});
			targetDateElement.forEach(a -> {
				fullSet.add(a.getText());
			});
			fixedIncomeElement.forEach(a -> {
				fullSet.add(a.getText());
			});
			specialityElement.forEach(a -> {
				fullSet.add(a.getText());
			});
			HashMap<String, Integer> finalSet = new HashMap<>();
			fullSet.forEach(a -> {

				if (finalSet.containsKey(a)) {
					int b = finalSet.get(a) + 1;
					finalSet.replace(a, b);

				} else {
					finalSet.put(a, 0);
				}

			});

			if (finalSet.containsValue(1)) {
				String duplicateElement = "";
				for (String a : finalSet.keySet()) {
					if (finalSet.get(a) >= 1) {
						duplicateElement = duplicateElement + "\n" + finalSet.get(a);
					}
				}
				fail("Found same element in more than one table => " + duplicateElement);
			}


	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"mutual-funds-table"})
	public void searchDividingHeaderFieldCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			try {
				scrollToElement(mydriver, searchDivideHeading, logger);
				if (searchDivideHeading.getText().isEmpty()) {
					fail("Blank search divide heading");
				} else {
					logger.info("Search Divide Test => " + searchDivideHeading.getText());
				}
			} catch (NoSuchElementException e) {
				throw new SkipException("Can't execute as there's no 'search divide header field'");
			}

	}

	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"mutual-funds-table"})
	public void searchFunctionalityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			int i = getRandomInteger(mydriver.findElements(By.xpath(equityElements)).size(), 1);
			String inputText = mydriver.findElements(By.xpath(equityElements)).get(i).getText();
			logger.info("Search Input Text => " + inputText.substring(0, 4));
			scrollToElement(mydriver, searchInput, logger);
			searchInput.sendKeys(inputText.substring(0, 4));
			searchSubmitButton.click();
			pleaseWait(2,logger);
			List<WebElement> elements = mydriver.findElements(By.xpath(fundElement));
			for (WebElement e : elements) {
				scrollToElement(mydriver, e, logger);
				if (e.getText().contains(inputText.substring(0, 4))) {
					logger.info("Search result => " + e.getText());
				} else {
					fail("Search Input Key '" + inputText + "'  but result inludes => " + e.getText());
				}
			}

	}

	@Test(priority = 7, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"mutual-funds-table"})
	public void searchTagsFunctionalityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			int i = getRandomInteger(mydriver.findElements(By.xpath(equityElements)).size(), 1);
			String inputText = mydriver.findElements(By.xpath(equityElements)).get(i).getText();
			logger.info("Search Input Text => " + inputText.substring(0, 4));
			scrollToElement(mydriver, searchInput, logger);
			searchInput.sendKeys(inputText.substring(0, 4));
			searchSubmitButton.click();
			pleaseWait(2,logger);
			scrollToElement(mydriver, mydriver.findElements(By.xpath(searchTags)).get(0), logger);

			hardAssert.assertEquals(mydriver.findElements(By.xpath(searchTags)).get(0).getText(),
					inputText.substring(0, 4));
			try {
				List<WebElement> removeTags = mydriver.findElements(By.xpath(searchTagsClose));
				for (WebElement removeTag : removeTags) {
					scrollToElementWithoutWait(mydriver, removeTag);
					removeTag.click();
				}
			} catch (NoSuchElementException e) {

			}

	}

	@Test(priority = 8, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"mutual-funds-table"})
	public void seachWithBothFiltersCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			int i = getRandomInteger(mydriver.findElements(By.xpath(equityElements)).size(), 1);
			String inputText = mydriver.findElements(By.xpath(equityElements)).get(i).getText();
			logger.info("Search Input Text => " + inputText.substring(0, 4));
			scrollToElement(mydriver, searchInput, logger);
			searchInput.sendKeys(inputText.substring(0, 4));
			selectByOptionName(logger, categoryFilter, "Equity");
			int j = i + 1;
			String assetclass = mydriver.findElements(By.xpath("//*[@class=\"mutual-fund__asset-class\"]")).get(j)
					.getText();
			selectByOptionName(logger, assetClassFilter, assetclass);
			searchSubmitButton.click();
			pleaseWait(2,logger);
			List<WebElement> elements = mydriver.findElements(By.xpath(fundElement));
			j = 0;
			for (WebElement element : elements) {
				scrollToElementWithoutWait(mydriver, element);
				hardAssert.assertEquals(
						mydriver.findElements(By.xpath("//*[@class=\"mutual-fund__asset-class\"]")).get(j).getText(),
						assetclass);
				j++;
			}


	}

	@Test(priority = 9, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"mutual-funds-table"})
	public void removeFilterConditions(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			int i = getRandomInteger(mydriver.findElements(By.xpath(equityElements)).size(), 1);
			String inputText = mydriver.findElements(By.xpath(equityElements)).get(i).getText();
			logger.info("Search Input Text => " + inputText.substring(0, 4));
			scrollToElement(mydriver, searchInput, logger);
			searchInput.sendKeys(inputText.substring(0, 4));
			selectByOptionName(logger, categoryFilter, "Equity");
			int j = i + 1;
			String assetclass = mydriver.findElements(By.xpath("//*[@class=\"mutual-fund__asset-class\"]")).get(j)
					.getText();
			selectByOptionName(logger, assetClassFilter, assetclass);
			searchSubmitButton.click();
			pleaseWait(2,logger);
			List<WebElement> elements = mydriver.findElements(By.xpath(fundElement));
			scrollToElement(mydriver, categoryFilter, logger);

			selectByOptionIndex(categoryFilter, 1, logger);
			selectByOptionIndex(assetClassFilter, 1, logger);
			searchClearButton.click();
			searchClearButton.click();
			searchSubmitButton.click();
			searchSubmitButton.click();
			pleaseWait(5, logger);
			List<WebElement> elementsFull = mydriver.findElements(By.xpath(fundElement));
			hardAssert.assertNotEquals(elements, elementsFull);


	}

	@Test(priority = 10, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"mutual-funds-table"})
	public void removeSearchTagsCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			int i = getRandomInteger(mydriver.findElements(By.xpath(equityElements)).size(), 1);
			String inputText = mydriver.findElements(By.xpath(equityElements)).get(i).getText();
			logger.info("Search Input Text => " + inputText.substring(0, 4));
			scrollToElement(mydriver, searchInput, logger);
			searchInput.sendKeys(inputText.substring(0, 4));
			searchSubmitButton.click();
			List<WebElement> removeTags = mydriver.findElements(By.xpath(searchTagsClose));
			for (WebElement removeTag : removeTags) {
				scrollToElementWithoutWait(mydriver, removeTag);
				removeTag.click();
			}
			pleaseWait(2,logger);
			removeTags = mydriver.findElements(By.xpath(searchTagsClose));
			if (removeTags.size() != 0) {
				fail("Search tags are visible even after clicking remove 'x' button");
			}

			i = getRandomInteger(mydriver.findElements(By.xpath(equityElements)).size(), 1);
			inputText = mydriver.findElements(By.xpath(equityElements)).get(i).getText();
			logger.info("Search Input Text => " + inputText.substring(0, 4));
			scrollToElement(mydriver, searchInput, logger);
			searchInput.sendKeys(inputText.substring(0, 4));
			searchSubmitButton.click();
			scrollToElement(mydriver, searchClearButton, logger);
			searchClearButton.click();
			pleaseWait(2,logger);
			removeTags = mydriver.findElements(By.xpath(searchTagsClose));
			if (removeTags.size() != 0) {
				fail("Search tags are visible even after clicking 'clear' button");
			}


	}

}
