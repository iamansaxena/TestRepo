package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MutualFundTable_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MutualFundsTable_StepDefinition extends MutualFundTable_page {
	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(MutualFundsTable_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MutualFundsTable_StepDefinition.class.getName());
		new MutualFundTable_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("mutual-fund-table") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5642:8080/content/AutomationDirectory/mutual-funds-table-.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("http://apvrt31468:4503/content/AutomationDirectory/mutual-funds-table-.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("mutual-fund-table").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(MutualFundsTable_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("BioCard", author, currentDomain, MutualFundsTable_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MutualFundsTable_StepDefinition.class);
		logger.info("Urls for '" + MutualFundsTable_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MutualFundsTable_StepDefinition.class.getName(), currentDomain);

		driverMap.put(MutualFundsTable_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + MutualFundsTable_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		// mydriver.manage().deleteAllCookies();
	}

	@Test(priority = 1, enabled = true)
	public void columnSortFunctionalityCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
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
	}

	@Test(priority = 2, enabled = true)
	public void categorySearchFilterationCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
			categoryFilter.click();
			selectByOptionIndex(categoryFilter, 1, logger);
			filterSubmitButton.click();

			scrollPageByDimensions(mydriver, 100, 500);
			pleaseWait(2, logger);
			hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(equityElements)).get(0),
					"Equity Table"));
		}
	}

	@Test(priority = 3, enabled = true)
	public void assetClassSearchFilterationCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
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
	}

	@Test(priority = 4, enabled = false)
	public void elementUniquenessCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);

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
	}

	@Test(priority = 5, enabled = true)
	public void searchDividingHeaderFieldCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
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
	}

	@Test(priority = 6, enabled = true)
	public void searchFunctionalityCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
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
	}

	@Test(priority = 7, enabled = true)
	public void searchTagsFunctionalityCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
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
	}

	@Test(priority = 8, enabled = true)
	public void seachWithBothFiltersCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
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
	}

	@Test(priority = 9, enabled = true)
	public void removeFilterConditions() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
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
			searchSubmitButton.click();
			pleaseWait(5, logger);
			List<WebElement> elementsFull = mydriver.findElements(By.xpath(fundElement));
			hardAssert.assertNotEquals(elements, elementsFull);
		}

	}

	@Test(priority = 10, enabled = true)
	public void removeSearchTagsCheck() {
		skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl); mydriver.get(cardUrl);
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

}
