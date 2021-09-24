package componentStepDef;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MedexSiteSearchAndResult_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MedexSiteSearchAndResult_StepDefinition extends MedexSiteSearchAndResult_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	//private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(MedexSiteSearchAndResult_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MedexSiteSearchAndResult_StepDefinition.class.getName());
		new MedexSiteSearchAndResult_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		ExtentTestManager.startTest(MedexSiteSearchAndResult_StepDefinition.class.getName());
		setTagForTestClass("Medex-Site-Search-And-Result", author, MedexSiteSearchAndResult_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MedexSiteSearchAndResult_StepDefinition.class);
		logger.info("Urls for '" + MedexSiteSearchAndResult_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MedexSiteSearchAndResult_StepDefinition.class.getName(), currentDomain);

		driverMap.put(MedexSiteSearchAndResult_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info(
				"Browser pool at '" + MedexSiteSearchAndResult_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
	}

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void elementVisiblityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			customTestLogs.get().add("Is search input field visible: " + searchInputField.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, searchInputField, "Search Input Field"));
			customTestLogs.get().add("Is search button visible" + searchButton.isDisplayed());
			softAssert.assertTrue(verifyElementExists(logger, searchButton, "Search Button"));
			customTestLogs.get()
					.add("Is placeholder text available" + !(searchInputField.getAttribute("innerText").isEmpty()));
			softAssert.assertFalse(searchInputField.getAttribute("placeholder").isEmpty());
			softAssert.assertAll();

	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void headerAndDirectionalCopyCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			boolean header = false;
			boolean directionalCopy = false;
			try {
				headingField.isDisplayed();
				header = true;
			} catch (Exception e) {
			}

			try {
				directionalCopyField.isDisplayed();
				directionalCopy = true;
			} catch (Exception e) {
			}

			if (header == true) {
				customTestLogs.get().add("Header: " + headingField.getAttribute("innerText"));
				softAssert.assertFalse(headingField.getAttribute("innerText").isEmpty());
			}
			if (directionalCopy == true) {
				customTestLogs.get().add("Directional Copy: " + directionalCopyField.getAttribute("innerText"));
				softAssert.assertFalse(directionalCopyField.getAttribute("innerText").isEmpty());
			}


	
	}

	
	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void searchSuggestionCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			searchInputField.sendKeys("s");
			getWebDriverWait(mydriver, 5).until(ExpectedConditions.visibilityOf(searchSugestionBox));
			customTestLogs.get().add("Is Search suggestion box visible: "+ searchSugestionBox.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, searchSugestionBox, "search suggestion box"));
			

	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void searchSuggestionFunctionalityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			searchInputField.sendKeys("s");
			getWebDriverWait(mydriver, 5).until(ExpectedConditions.visibilityOf(searchSugestionBox));
			int i = getRandomInteger(searchSugestionItems.size(),0);
			String selectedSearchOption = searchSugestionLabel.get(i).getAttribute("innerText");
			customTestLogs.get().add("Input Selected from suggestion: "+ selectedSearchOption);
			searchSugestionLabel.get(i).click();
			customTestLogs.get().add("Landing page header: "+ resultPageInfoHeader.getAttribute("innerText"));
			hardAssert.assertTrue(resultPageInfoHeader.getAttribute("innerText").contains(selectedSearchOption));

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void searchFunctionalityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			String selectedSearchOption = "s";
			searchInputField.sendKeys(selectedSearchOption);
			customTestLogs.get().add("Search Input: "+ selectedSearchOption);
			searchButton.click();
			customTestLogs.get().add("Landing page header: "+ resultPageInfoHeader.getAttribute("innerText"));
			hardAssert.assertTrue(resultPageInfoHeader.getAttribute("innerText").contains(selectedSearchOption));

	}


	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void noResultsSearchFunctionalityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			String selectedSearchOption = "fdfdfdfdofofofofofofofofofofofofoofoeoreoreoroeroo";
			searchInputField.sendKeys(selectedSearchOption);
			customTestLogs.get().add("Search Input: "+ selectedSearchOption);
			searchButton.click();
			customTestLogs.get().add("Landing page header: "+ resultPageInfoHeader.getAttribute("innerText"));
			pleaseWait(3, logger);
			hardAssert.assertTrue(isNoResultMessageAvailable(logger));

	}


	@Test(priority = 7, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void paginationFunctionalityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			String selectedSearchOption = "s";
			searchInputField.sendKeys(selectedSearchOption);
			customTestLogs.get().add("Search Input: "+ selectedSearchOption);
			searchButton.click();
			customTestLogs.get().add("Landing page header: "+ resultPageInfoHeader.getAttribute("innerText"));
			try {
				paginationSection.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("No pagination Found");
			}
			hardAssert.assertTrue(moveToNextPage(logger));
			hardAssert.assertTrue(moveToPrevPage(logger));
			
//			hardAssert.assertTrue();

	}






	@Test(priority = 8, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"site-search"})
	public void searchResultItemCountCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, siteSearchSection, logger);
			String selectedSearchOption = "s";
			searchInputField.sendKeys(selectedSearchOption);
			customTestLogs.get().add("Search Input: "+ selectedSearchOption);
			searchButton.click();
			customTestLogs.get().add("Landing page header: "+ resultPageInfoHeader.getAttribute("innerText"));
			hardAssert.assertEquals(Integer.valueOf(noOfResultItems.getAttribute("innerText")).intValue(),getResultCountFromFooter(logger));

	}








}
