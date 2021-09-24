package componentStepDef;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.Hscc_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class Hscc_StepDefinition extends Hscc_page {

	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(Hscc_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(Hscc_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new Hscc_page();
		ExtentTestManager.startTest(Hscc_StepDefinition.class.getName());
		setTagForTestClass("HSA Calculator", author, Hscc_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(Hscc_StepDefinition.class);
		logger.info("Urls for '" + Hscc_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(Hscc_StepDefinition.class.getName(), currentDomain);

		driverMap.put(Hscc_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + Hscc_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, navigationTabList, logger);
		softAssert.assertEquals(verifyElementExists(logger, navigationTabList, "Navigation tabs section"), true);
		softAssert.assertEquals(
				verifyElementExists(logger, individualGenderFilterLabel, "Gender section label for individual"), true);
		softAssert.assertEquals(
				verifyElementExists(logger, individualAgeFilterLabel, "Age section label for individual"), true);
		softAssert.assertEquals(
				verifyElementExists(logger, individualRetiredFilterLabel, "Reitred section label for individual"),
				true);
		softAssert.assertEquals(verifyElementExists(logger, includePartnerCheckbox, "Age section label for individual"),
				true);
		includePartnerCheckbox.click();
		scrollToElement(mydriver, includePartnerCheckbox, logger);
		softAssert.assertEquals(
				verifyElementExists(logger, partnerGenderFilterLabel, "Gender section label for partner"), true);
		softAssert.assertEquals(verifyElementExists(logger, partnerAgeFilterLabel, "Age section label for partner"),
				true);
		softAssert.assertEquals(
				verifyElementExists(logger, partnerRetiredFilterLabel, "Reitred section label for partner"), true);

		softAssert.assertEquals(verifyElementExists(logger, termsLabel, "Terms section label"), true);
		softAssert.assertEquals(verifyElementExists(logger, termsAgreed, "Terms section button"), true);
		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void blankFormSubmitP1(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, includePartnerCheckbox, logger);
		includePartnerCheckbox.click();
		getVisibility(mydriver, termsAgreed, 3);

		try {
			termsAgreed.click();
		} catch (Exception e) {
			try {
				termsAgreed.click();
			} catch (Exception e2) {

			}
		}

		getVisibility(mydriver, nextButton, 6);

		nextButton.click();
		List<WebElement> genderErrors = mydriver.findElements(By.xpath(genderErrorMsg));
		for (WebElement genderError : genderErrors) {
			softAssert.assertEquals(verifyElementExists(logger, genderError, "Gender section error message"), true);
			softAssert.assertAll();
		}
		List<WebElement> ageErrors = mydriver.findElements(By.xpath(ageErrorMsg));
		for (WebElement ageError : ageErrors) {
			softAssert.assertEquals(verifyElementExists(logger, ageError, "Age section error message"), true);
			softAssert.assertAll();
		}
		List<WebElement> retiredErrors = mydriver.findElements(By.xpath(retiredErrorMsg));
		for (WebElement retiredError : retiredErrors) {
			softAssert.assertEquals(verifyElementExists(logger, retiredError, "Retired section error message"), true);
			softAssert.assertAll();
		}

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void nextButtonDisableWithoutAgreement(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, termsAgreed, logger);
		try {
			nextButton.click();
		} catch (Exception e) {
			logger.info("No Next button is there and user has not been agreed to the terms yet");
		}

		termsAgreed.click();
		hardAssert.assertEquals(verifyElementExists(logger, nextButton, "Next button"), true);

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void additionalFieldsForPartner(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, includePartnerCheckbox, logger);
		includePartnerCheckbox.click();
		getVisibility(mydriver, partnerAgeFilterLabel, 2);
		softAssert.assertEquals(verifyElementExists(logger, partnerAgeFilterLabel, "Partner's age filter section"),
				true);
		softAssert.assertEquals(
				verifyElementExists(logger, partnerRetiredFilterLabel, "Partner's retirement filter section"), true);
		softAssert.assertEquals(
				verifyElementExists(logger, partnerGenderFilterLabel, "Partner's gender filter section"), true);
		softAssert.assertAll();
	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void mainHeaderAndDescAvailability(String url) {
		skipNonExistingComponent(url);
		boolean headStatus;
		boolean descriptionStatus;

		
		mydriver.get(url);
		try {
			scrollToElement(mydriver, firstPageTitle, logger);
			headStatus = firstPageTitle.isDisplayed();
		} catch (Exception e) {
			headStatus = false;
		}

		try {
			scrollToElement(mydriver, firstPageDescription, logger);
			descriptionStatus = firstPageDescription.isDisplayed();
		} catch (Exception e) {
			descriptionStatus = false;
		}

		if (headStatus == true || descriptionStatus == true) {

			inputValidDataP1(logger, true);
			if (headStatus == true) {
				logger.info("Main header => " + firstPageTitle.getText());
				hardAssert.assertEquals(firstPageTitle.isDisplayed(), true);
			}
			if (descriptionStatus == true) {
				logger.info("Main description => " + firstPageDescription.getText());
				hardAssert.assertEquals(firstPageDescription.isDisplayed(), true);
			}

		} else {
			throw new SkipException("There is not main title or description");
		}
	}

	@Test(priority = 6, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void basicTabHeaderAndSubHeadingAvailability(String url) {
		skipNonExistingComponent(url);
		boolean headStatus;
		boolean descriptionStatus;

		
		mydriver.get(url);

		try {
			scrollToElement(mydriver, basicTabHeading, logger);
			headStatus = basicTabHeading.isDisplayed();
		} catch (Exception e) {
			headStatus = false;
		}

		try {
			scrollToElement(mydriver, directionalCopy, logger);
			descriptionStatus = directionalCopy.isDisplayed();
		} catch (Exception e) {
			descriptionStatus = false;
		}
		if (headStatus == true || descriptionStatus == true) {

			if (headStatus == true) {
				if (!basicTabHeading.getText().isEmpty()) {
					logger.info("Main header => " + basicTabHeading.getText());
					hardAssert.assertEquals(basicTabHeading.isDisplayed(), true);
				} else
					fail("Blank basic tab heading!!");
			}
			if (descriptionStatus == true) {
				if (!directionalCopy.getText().isEmpty()) {
					logger.info("Main description => " + directionalCopy.getText());
					hardAssert.assertEquals(directionalCopy.isDisplayed(), true);
				} else
					fail("Blank sub-header !!");
			}

		} else {
			throw new SkipException("There is not Basic tab heading or sub header");

		}

	}

	@Test(priority = 7, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void termsLinkRedirection(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		String currentHandle = mydriver.getWindowHandle();
		scrollToElement(mydriver, termsLabel, logger);
		try {
			WebElement terms = mydriver.findElement(By.xpath("//*[@class=\" hsa-divider-top hsa-terms\"]/p/strong/a"));
			terms.click();
			pleaseWait(3, logger);
			hardAssert.assertNotEquals(mydriver.getWindowHandles().size(), 1);
			// assertRedirection(mydriver, logger, getDomainName(url), expLink,
			// currentHandle);
		} catch (Exception e) {
			logger.warn("There's no resource link");
			throw new SkipException("There's no resource link");
		} finally {
			switchToPreviousTab(mydriver, logger, currentHandle);
		}

	}

	@Test(priority = 8, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void ageLimitCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		pleaseWait(10, logger);
		scrollToElementWithoutWait(mydriver, mydriver.findElement(By.xpath("//*[@class=\"hsa-box hsa__optum\"]")));
		scrollToElementWithoutWait(mydriver, inputIndividualAgeFilterLabel);
		inputValidDataP1(logger, false);
		inputIndividualAgeFilterLabel.clear();
		inputIndividualAgeFilterLabel.sendKeys("17");

		nextButton.click();
		hardAssert.assertEquals(verifyElementExists(logger, mydriver.findElement(By.xpath(ageErrorMsg)),
				"Age limit error message is available for values less than 18"), true);
		inputIndividualAgeFilterLabel.clear();
		inputIndividualAgeFilterLabel.sendKeys("119");
		nextButton.click();
		hardAssert.assertEquals(verifyElementExists(logger, mydriver.findElement(By.xpath(ageErrorMsg)),
				"Age limit error message is available for values greater than 118"), true);
	}

	@Test(priority = 9, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void userDetailsAvailabilityOverOtherPages(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		inputValidDataP1(logger, true);
		softAssert.assertEquals(userDetailAgeP2.getText(), "19");
		softAssert.assertEquals(userDetailGenderP2.getText(), "Female");
		softAssert.assertAll();
	}

	@Test(priority = 10, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void errorValidationsOverP2(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		inputValidDataP1(logger, true);
		scrollToElement(mydriver, navigationTabList, logger);
		inputAgeExpectancy.clear();
		nextButton.click();
		softAssert.assertEquals(
				verifyElementExists(logger, ageExpectancyErrorMsg, "Error message for Age Expectancy section "), true);
		softAssert.assertEquals(
				verifyElementExists(logger, selectStateErrorMsg, "Error message for State Selection section "), true);
		softAssert.assertEquals(verifyElementExists(logger, smokerErrorMsg, "Error message for isSmoker section "),
				true);
		softAssert.assertEquals(verifyElementExists(logger, weightErrorMsg, "Error message for Weight section "), true);
		softAssert.assertEquals(verifyElementExists(logger, heightErrorMsg, "Error message for Height section "), true);
		softAssert.assertAll();

	}

	@Test(priority = 11, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void blankFormSubmitP2(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, navigationTabList, logger);
		inputValidDataP1(logger, true);
		scrollToElement(mydriver, navigationTabList, logger);
		inputAgeExpectancy.clear();
		nextButton.click();
		hardAssert.assertTrue(verifyElementExists(logger, selectState, "State selection drop-down"));
	}

	@Test(priority = 12, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void updateCostP2(String url) {
		skipNonExistingComponent(url);
		if (Environment.equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this test case on test environment");
		}

		
		mydriver.get(url);
		inputValidDataP1(logger, true);
		inputValidDataP2(logger, false);
		String totalCost = totalHealthCosts.getAttribute("innerText");
		String medicarCost = costCoveredByMedicare.getAttribute("innerText");
		String shortageCost = estimatedShortage.getAttribute("innerText");
		logger.info("Costs Before Updation: \nTotal Cost => " + totalCost + "\nCovered By Medicare => " + medicarCost
				+ "\nTotal Cost Short => " + shortageCost);
		secondPageUpdateCostButton.click();
		pleaseWait(14, logger);
		hardAssert.assertNotEquals(totalHealthCosts.getAttribute("innerText"), totalCost);
		hardAssert.assertNotEquals(costCoveredByMedicare.getAttribute("innerText"), medicarCost);
		hardAssert.assertNotEquals(estimatedShortage.getAttribute("innerText"), shortageCost);
		logger.info("Costs After Updation: \nTotal Cost => " + totalHealthCosts.getAttribute("innerText")
				+ "\nCovered By Medicare => " + costCoveredByMedicare.getAttribute("innerText")
				+ "\nTotal Cost Short => " + estimatedShortage.getAttribute("innerText"));
	}

	@Test(priority = 13, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void healthConditionSelectFunctionality(String url) {
		skipNonExistingComponent(url);
		if (Environment.equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this test case on test environment");
		}

		
		mydriver.get(url);
		inputValidDataP1(logger, true);
		inputValidDataP2(logger, true);
		List<WebElement> conditions = mydriver.findElements(By.xpath(selectIndividualConditions));

		int count = 1;
		List<String> conditionChosen = new ArrayList<>();
		;
		while (count < 3) {
			conditions = mydriver.findElements(By.xpath(selectIndividualConditions));
			int i = getRandomInteger(conditions.size(), 0);
			conditionChosen.add(conditions.get(i).getText());
			logger.info("User has opted for condition => " + conditions.get(i).getText());
			conditions.get(i).click();
			scrollToElement(mydriver, addIndividualConditionButton, logger);
			addIndividualConditionButton.click();
			count++;
		}
		List<WebElement> selectedConditions = mydriver.findElements(By.xpath(Hscc_page.selectedConditions));
		int totalConditionSelected = selectedConditions.size();
		List<String> conditionNames = new ArrayList<>();
		for (WebElement selectedCondition : selectedConditions) {
			conditionNames.add(selectedCondition.getText());

		}
		if (conditionNames.equals(conditionChosen)) {
			logger.info(conditionChosen + " has been added!");
		} else {
			logger.info(conditionChosen + " hasn't been added!");
			fail(conditionChosen + " hasn't been added!");
		}
		int i = getRandomInteger(mydriver.findElements(By.xpath(removeConditionButton)).size(), 0);
		mydriver.findElements(By.xpath(removeConditionButton)).get(i).click();
		selectedConditions = mydriver.findElements(By.xpath(Hscc_page.selectedConditions));
		conditionNames = new ArrayList<>();
		if (selectedConditions.size() == totalConditionSelected) {
			fail("Condition didn't get removed");
		} else {
			for (WebElement cond : selectedConditions) {
				conditionNames.add(cond.getText());
			}
			logger.info("Conditions selected after prior removal => " + conditionNames);
		}
	}

	@Test(priority = 14, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void healthConditionUpdateCostFunctionality(String url) {
		skipNonExistingComponent(url);
		if (Environment.equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this test case on test environment");
		}

		
		mydriver.get(url);
		inputValidDataP1(logger, true);
		inputValidDataP2(logger, true);
		pleaseWait(10, logger);
		List<WebElement> conditions = mydriver.findElements(By.xpath(selectIndividualConditions));

		int count = 1;
		List<String> conditionChosen = new ArrayList<>();

		while (count < 3) {
			conditions = mydriver.findElements(By.xpath(selectIndividualConditions));
			int i = getRandomInteger(conditions.size() - 4, 0);
			conditionChosen.add(conditions.get(i).getText());
			logger.info("User has opted for condition => " + conditions.get(i).getText());
			conditions.get(i).click();
			scrollToElement(mydriver, addIndividualConditionButton, logger);
			addIndividualConditionButton.click();
			count++;
		}
		List<WebElement> selectedConditions = mydriver.findElements(By.xpath(Hscc_page.selectedConditions));
		// int totalConditionSelected = selectedConditions.size();
		List<String> conditionNames = new ArrayList<>();
		for (WebElement selectedCondition : selectedConditions) {
			conditionNames.add(selectedCondition.getText());

		}
		if (conditionNames.equals(conditionChosen)) {
			logger.info(conditionChosen + " has been added!");
		} else {
			logger.info(conditionChosen + " hasn't been added!");
			fail(conditionChosen + " hasn't been added!");
		}
		try {
			List<WebElement> radios = mydriver.findElements(By.xpath(SelectedConditionsWithRadio));
			for (WebElement radio : radios) {
				radio.click();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		String totalCost = totalHealthCosts.getAttribute("innerText");
		String medicarCost = costCoveredByMedicare.getAttribute("innerText");
		String shortageCost = estimatedShortage.getAttribute("innerText");
		logger.info("Costs Before Updation: \nTotal Cost => " + totalCost + "\nCovered By Medicare => " + medicarCost
				+ "\nTotal Cost Short => " + shortageCost);
		thirdPageUpdateCostButton.click();
		pleaseWait(14, logger);
		hardAssert.assertNotEquals(totalHealthCosts.getAttribute("innerText"), totalCost);
		hardAssert.assertNotEquals(costCoveredByMedicare.getAttribute("innerText"), medicarCost);
		hardAssert.assertNotEquals(estimatedShortage.getAttribute("innerText"), shortageCost);
		logger.info("Costs After Updation: \nTotal Cost => " + totalHealthCosts.getAttribute("innerText")
				+ "\nCovered By Medicare => " + costCoveredByMedicare.getAttribute("innerText")
				+ "\nTotal Cost Short => " + estimatedShortage.getAttribute("innerText"));
	}

	@Test(priority = 15, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void p4BlankFormSubmitCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		inputValidDataP1(logger, true);
		inputValidDataP2(logger, true);
		inputValidDataP3(logger);
		scrollToElement(mydriver, nextButton, logger);
		hardAssert.assertTrue(verifyElementExists(logger, individualHaveHsaYes, "Health Saving Section"));

	}

	@Test(priority = 16, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void healthSavingP4FunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		inputValidDataP1(logger, true);
		inputValidDataP2(logger, true);
		inputValidDataP3(logger);
		individualHaveHsaYes.click();
		getVisibility(mydriver, individualAccountTypeIndividual, 3);
		individualAccountTypeIndividual.click();
		individualInputHsaBalance.sendKeys("3");
		individualInputPlannedContribution.sendKeys("1");
		individualInputWithdrawl.sendKeys("1");
		individualInputAnnualReturn.sendKeys("1");
		scrollToElement(mydriver, nextButton, logger);
		nextButton.click();

		hardAssert.assertEquals(fifthPageNavigationTab.getAttribute("aria-selected"), "true");
		logger.info("User landed on last page");
	}

	@Test(priority = 17, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"hsa " })
	public void p4HealthSavingUpdateCost(String url) {
		skipNonExistingComponent(url);
		if (Environment.equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this test case on test environment");
		}

		
		mydriver.get(url);
		inputValidDataP1(logger, true);
		inputValidDataP2(logger, true);
		inputValidDataP3(logger);
		individualHaveHsaYes.click();
		getVisibility(mydriver, individualAccountTypeIndividual, 3);
		individualAccountTypeIndividual.click();
		individualInputHsaBalance.sendKeys("3");
		individualInputPlannedContribution.sendKeys("1");
		individualInputWithdrawl.sendKeys("1");
		individualInputAnnualReturn.sendKeys("1");

		String shortageCost = estimatedShortage.getAttribute("innerText");
		logger.info("Costs Before Updation: " + "\nTotal Cost Short => " + shortageCost);
		fourthPageUpdateCostButton.click();
		pleaseWait(14, logger);
		// hardAssert.assertNotEquals(totalHealthCosts.getText(), totalCost);
		// hardAssert.assertNotEquals(costCoveredByMedicare.getText(), medicarCost);
		logger.info("Costs After Updation: " + "\nTotal Cost Short => " + estimatedShortage.getAttribute("innerText")
				+ "\nAmount paid from HSA => " + paidFromHsaAmount.getAttribute("innerText"));
		hardAssert.assertNotEquals(estimatedShortage.getAttribute("innerText"), shortageCost);

	}

}
