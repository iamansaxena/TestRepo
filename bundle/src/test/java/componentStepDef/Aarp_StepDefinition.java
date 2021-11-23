package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.Aarp_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class Aarp_StepDefinition extends Aarp_page {

	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> urls = new ArrayList<>();
	// { "https://www.myaarphsa.com/resources/healthsavingscheckup.html" };
	// {"https://stg-www.optum.com/content/aarp/en/aarp-hsc.html"};
	// {"http://apvrt31468:4503/content/AutomationDirectory/hscc-calculator.html" };
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(Aarp_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(Aarp_StepDefinition.class.getName());
		new Aarp_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("aarp-hsa") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				urls.add("http://apsrs5642:8080/content/AutomationDirectory/aarp-hsc.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				urls.add("http://apvrt31468:4503/content/AutomationDirectory/aarp-hsc.html");
			}

		} else {
			String[] scannedUrls = fetchUrl("aarp-hsa").split(",");
			for (String link : scannedUrls) {
				urls.add(link);
			}
		}

		ExtentTestManager.startTest(Aarp_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("AARP-HSA", author, currentDomain, Aarp_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(Aarp_StepDefinition.class);
		logger.info("Urls for '" + Aarp_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(Aarp_StepDefinition.class.getName(), currentDomain);

		driverMap.put(Aarp_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + Aarp_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
	public void elementVisibilityCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, navigationTabList, logger);
			softAssert.assertEquals(verifyElementExists(logger, navigationTabList, "Navigation tabs section"), true);
			softAssert.assertEquals(
					verifyElementExists(logger, individualGenderFilterLabel, "Gender section label for individual"),
					true);
			softAssert.assertEquals(
					verifyElementExists(logger, individualAgeFilterLabel, "Age section label for individual"), true);
			softAssert.assertEquals(
					verifyElementExists(logger, individualRetiredFilterLabel, "Reitred section label for individual"),
					true);
			softAssert.assertEquals(
					verifyElementExists(logger, includePartnerCheckbox, "Age section label for individual"), true);
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
	}

	@Test(priority = 2, enabled = true)
	public void blankFormSubmitP1() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
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
			}
			List<WebElement> ageErrors = mydriver.findElements(By.xpath(ageErrorMsg));
			for (WebElement ageError : ageErrors) {
				softAssert.assertEquals(verifyElementExists(logger, ageError, "Age section error message"), true);
			}
			List<WebElement> retiredErrors = mydriver.findElements(By.xpath(retiredErrorMsg));
			for (WebElement retiredError : retiredErrors) {
				softAssert.assertEquals(verifyElementExists(logger, retiredError, "Retired section error message"),
						true);
			}
		}
		softAssert.assertAll();
	}

	@Test(priority = 3, enabled = true)
	public void nextButtonDisableWithoutAgreement() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
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
	}

	@Test(priority = 4, enabled = true)
	public void additionalFieldsForPartner() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, includePartnerCheckbox, logger);
			includePartnerCheckbox.click();
			getVisibility(mydriver, partnerAgeFilterLabel, 2);
			softAssert.assertEquals(verifyElementExists(logger, partnerAgeFilterLabel, "Partner's age filter section"),
					true);
			softAssert.assertEquals(
					verifyElementExists(logger, partnerRetiredFilterLabel, "Partner's retirement filter section"),
					true);
			softAssert.assertEquals(
					verifyElementExists(logger, partnerGenderFilterLabel, "Partner's gender filter section"), true);
			softAssert.assertAll();
		}
	}

	@Test(priority = 5, enabled = true)
	public void mainHeaderAndDescAvailability() {
		skipNonExistingComponent(urls);
		boolean headStatus;
		boolean descriptionStatus;
		for (String url : urls) {
			urlUnderTest.get().add(url);
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
	}

	@Test(priority = 6, enabled = true)
	public void basicTabHeaderAndSubHeadingAvailability() {
		skipNonExistingComponent(urls);
		boolean headStatus;
		boolean descriptionStatus;
		for (String url : urls) {
			urlUnderTest.get().add(url);
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
	}

	@Test(priority = 7, enabled = true)
	public void termsLinkRedirection() {
		skipNonExistingComponent(urls);

		for (String url : urls) {

			urlUnderTest.get().add(url); mydriver.get(url);
			String currentHandle = mydriver.getWindowHandle();
			scrollToElement(mydriver, termsLabel, logger);
			try {
				WebElement terms = mydriver.findElement(By.xpath("//*[@class=\" hsa-divider-top hsa-terms\"]/p/strong/a"));
				String expLink = terms.getAttribute("href");
				terms.click();
				pleaseWait(3, logger);
				hardAssert.assertNotEquals(mydriver.getWindowHandles().size(), 1);
//				assertRedirection(mydriver, logger, getDomainName(url), expLink, currentHandle);
			} catch (Exception e) {
				logger.warn("There's no resource link");
				throw new SkipException("There's no resource link");
			}finally {
				switchToPreviousTab(mydriver, logger);
			}

		}
	}

	@Test(priority = 8, enabled = true)
	public void ageLimitCheck() {
		skipNonExistingComponent(urls);

		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			pleaseWait(15, logger);
			scrollToElementWithoutWait(mydriver, mydriver.findElement(By.xpath("//*[@class=\"progress-bar\"]")));
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
	}

	@Test(priority = 9, enabled = true)
	public void userDetailsAvailabilityOverOtherPages() {
		skipNonExistingComponent(urls);

		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, navigationTabList, logger);
			inputValidDataP1(logger, true);
			softAssert.assertEquals(userDetailAgeP2.getText(), "19");
			softAssert.assertEquals(userDetailGenderP2.getText(), "Female");
			softAssert.assertAll();
		}
	}

	@Test(priority = 10, enabled = true)
	public void errorValidationsOverP2() {
		skipNonExistingComponent(urls);

		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElement(mydriver, navigationTabList, logger);
			inputValidDataP1(logger, true);
			scrollToElement(mydriver, navigationTabList, logger);
			inputAgeExpectancy.clear();
			nextButton.click();
			softAssert.assertEquals(
					verifyElementExists(logger, ageExpectancyErrorMsg, "Error message for Age Expectancy section "),
					true);
			softAssert.assertEquals(
					verifyElementExists(logger, selectStateErrorMsg, "Error message for State Selection section "),
					true);
			softAssert.assertEquals(verifyElementExists(logger, smokerErrorMsg, "Error message for isSmoker section "),
					true);
			softAssert.assertEquals(verifyElementExists(logger, weightErrorMsg, "Error message for Weight section "),
					true);
			softAssert.assertEquals(verifyElementExists(logger, heightErrorMsg, "Error message for Height section "),
					true);
			softAssert.assertAll();

		}
	}

	@Test(priority = 11, enabled = true)
	public void blankFormSubmitP2() {
		skipNonExistingComponent(urls);

		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			scrollToElementWithoutWait(mydriver, navigationTabList);
			getVisibility(mydriver, navigationTabList, 40);
			inputValidDataP1(logger, true);
			scrollToElement(mydriver, navigationTabList, logger);
			inputAgeExpectancy.clear();
			nextButton.click();
			hardAssert.assertTrue(verifyElementExists(logger, selectState, "State selection drop-down"));
		}
	}

	@Test(priority = 12, enabled = true)
	public void updateCostP2() {
		skipNonExistingComponent(urls);
		if (Environment.equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this test case on test environment");
		}
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			inputValidDataP1(logger, true);
			inputValidDataP2(logger, false);
			String totalCost = totalHealthCosts.getAttribute("innerText");
			String medicarCost = costCoveredByMedicare.getAttribute("innerText");
			String shortageCost = estimatedShortage.getAttribute("innerText");
			logger.info("Costs Before Updation: \nTotal Cost => " + totalCost + "\nCovered By Medicare => "
					+ medicarCost + "\nTotal Cost Short => " + shortageCost);
			secondPageUpdateCostButton.click();
			pleaseWait(6, logger);
			hardAssert.assertNotEquals(totalHealthCosts.getAttribute("innerText"), totalCost);
			hardAssert.assertNotEquals(costCoveredByMedicare.getAttribute("innerText"), medicarCost);
			hardAssert.assertNotEquals(estimatedShortage.getAttribute("innerText"), shortageCost);
			logger.info("Costs After Updation: \nTotal Cost => " + totalHealthCosts.getAttribute("innerText")
					+ "\nCovered By Medicare => " + costCoveredByMedicare.getAttribute("innerText")
					+ "\nTotal Cost Short => " + estimatedShortage.getAttribute("innerText"));
		}
	}

	@Test(priority = 13, enabled = true)
	public void healthConditionSelectFunctionality() {
		skipNonExistingComponent(urls);
		if (Environment.equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this test case on test environment");
		}
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			inputValidDataP1(logger, true);
			inputValidDataP2(logger, true);
			List<WebElement> conditions = mydriver.findElements(By.xpath(selectIndividualConditions));

			int count = 1;
			List<String> conditionChosen = new ArrayList<>();
			;
			while (count < 3) {
				int i = getRandomInteger(conditions.size(), 0);
				conditionChosen.add(conditions.get(i).getText());
				logger.info("User has opted for condition => " + conditions.get(i).getText());
				conditions.get(i).click();
				scrollToElement(mydriver, addIndividualConditionButton, logger);
				addIndividualConditionButton.click();
				count++;
			}
			List<WebElement> selectedConditions = mydriver.findElements(By.xpath(Aarp_page.selectedConditions));
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
			selectedConditions = mydriver.findElements(By.xpath(Aarp_page.selectedConditions));
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
	}

	@Test(priority = 14, enabled = true)
	public void healthConditionUpdateCostFunctionality() {
		skipNonExistingComponent(urls);
		if (Environment.equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this test case on test environment");
		}
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			inputValidDataP1(logger, true);
			inputValidDataP2(logger, true);
			List<WebElement> conditions = mydriver.findElements(By.xpath(selectIndividualConditions));

			int count = 1;
			List<String> conditionChosen = new ArrayList<>();
			;
			while (count < 3) {
				int i = getRandomInteger(conditions.size() - 4, 0);
				conditionChosen.add(conditions.get(i).getText());
				logger.info("User has opted for condition => " + conditions.get(i).getText());
				conditions.get(i).click();
				scrollToElement(mydriver, addIndividualConditionButton, logger);
				addIndividualConditionButton.click();
				count++;
			}
			List<WebElement> selectedConditions = mydriver.findElements(By.xpath(Aarp_page.selectedConditions));
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
			logger.info("Costs Before Updation: \nTotal Cost => " + totalCost + "\nCovered By Medicare => "
					+ medicarCost + "\nTotal Cost Short => " + shortageCost);
			thirdPageUpdateCostButton.click();
			pleaseWait(6, logger);
			hardAssert.assertNotEquals(totalHealthCosts.getAttribute("innerText"), totalCost);
			hardAssert.assertNotEquals(costCoveredByMedicare.getAttribute("innerText"), medicarCost);
			hardAssert.assertNotEquals(estimatedShortage.getAttribute("innerText"), shortageCost);
			logger.info("Costs After Updation: \nTotal Cost => " + totalHealthCosts.getAttribute("innerText")
					+ "\nCovered By Medicare => " + costCoveredByMedicare.getAttribute("innerText")
					+ "\nTotal Cost Short => " + estimatedShortage.getAttribute("innerText"));
		}
	}

	@Test(priority = 15, enabled = true)
	public void p4BlankFormSubmitCheck() {
		skipNonExistingComponent(urls);

		for (String url : urls) {
			if (Environment.equalsIgnoreCase("test")) {
				throw new SkipException("Can't execute this test case on test environment");
			}
			urlUnderTest.get().add(url);
			mydriver.get(url);
			inputValidDataP1(logger, true);
			inputValidDataP2(logger, true);
			inputValidDataP3(logger);
			scrollToElement(mydriver, nextButton, logger);
			hardAssert.assertTrue(verifyElementExists(logger, healthCondition, "Health Saving Section"));

		}
	}

	@Test(priority = 16, enabled = true)
	public void healthSavingP4FunctionalityCheck() {
		skipNonExistingComponent(urls);

		for (String url : urls) {
			if (Environment.equalsIgnoreCase("test")) {
				throw new SkipException("Can't execute this test case on test environment");
			}
			urlUnderTest.get().add(url);
			mydriver.get(url);
			inputValidDataP1(logger, true);
			inputValidDataP2(logger, true);
			inputValidDataP3(logger);
			healthConditionCheckBox.click();
			pleaseWait(2, logger);
			hardAssert.assertTrue(healthConditionCheckBox.isSelected());

			scrollToElement(mydriver, nextButton, logger);
			nextButton.click();

			hardAssert.assertEquals(fifthPageNavigationTab.getAttribute("aria-selected"), "true");
			logger.info("User landed on last page");
		}

	}

	@Test(priority = 17, enabled = true)
	public void p4HealthSavingUpdateCost() {
		skipNonExistingComponent(urls);
		if (Environment.equalsIgnoreCase("test")) {
			throw new SkipException("Can't execute this test case on test environment");
		}
		for (String url : urls) {
			urlUnderTest.get().add(url);
			mydriver.get(url);
			inputValidDataP1(logger, true);
			inputValidDataP2(logger, true);
			inputValidDataP3(logger);
			healthConditionCheckBox.click();
			pleaseWait(2, logger);

			String shortageCost = estimatedShortage.getAttribute("innerText");
			logger.info("Costs Before Updation: " + "\nTotal Cost Short => " + shortageCost);
			scrollToElement(mydriver, fourthPageUpdateCostButton, logger);
			fourthPageUpdateCostButton.click();
			pleaseWait(6, logger);
			hardAssert.assertNotEquals(estimatedShortage.getAttribute("innerText"), shortageCost);
			logger.info(
					"Costs After Updation: " + "\nTotal Cost Short => " + estimatedShortage.getAttribute("innerText")
							+ "\nAmount paid from HSA => " + paidFromHsaAmount.getAttribute("innerText"));

		}
	}
}
