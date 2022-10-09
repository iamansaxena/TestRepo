package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.jsWaitForPageToLoad;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.Centerscards_page;

import core.CustomDataProvider;

public class CentersCards_StepDefinition extends Centerscards_page {

	private static final Logger logger = LogManager.getLogger(CentersCards_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-cards" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		mydriver.get(url);
		jsWaitForPageToLoad(50, mydriver, logger);
		scrollToElement(mydriver, centersCardsSection, logger);
		WebDriverWait wait = new WebDriverWait(mydriver, 30);
		wait.until(ExpectedConditions.visibilityOf(centersCardsSection));
		getTest().info("Verify Enter City text box: " + enterCityTextBox.isDisplayed());
		getTest().info("Verify Find Center Submit Button: " + findCenterBtn.isDisplayed());
		hardAssert.assertEquals(enterCityTextBox.isDisplayed(), true);
		hardAssert.assertEquals(findCenterBtn.isDisplayed(), true);
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-cards" })
	public void findCenterUsingCityName(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		jsWaitForPageToLoad(50, mydriver, logger);
		hardAssert.assertEquals(findCenter(mydriver, "Eden Prairie,MN", logger), true);
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-cards" })
	public void findCenterUsingZip(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		jsWaitForPageToLoad(50, mydriver, logger);
		hardAssert.assertEquals(findCenter(mydriver, "55346", logger), true);
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-cards" })
	public void findCenterUsingInvalidString(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		jsWaitForPageToLoad(50, mydriver, logger);
		hardAssert.assertNotEquals(findCenter(mydriver, "He$$o", logger), true);
	}

}
