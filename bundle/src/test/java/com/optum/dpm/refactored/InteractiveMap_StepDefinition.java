package com.optum.dpm.refactored;import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;
import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.InteractiveMap_page;

import core.CustomDataProvider;
@SuppressWarnings("unused")
public class InteractiveMap_StepDefinition extends InteractiveMap_page {
	
	private String iconPrimary;
	private String iconUrgent;
	private String iconSurgical;
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static final Logger logger = LogManager.getLogger(InteractiveMap_StepDefinition.class);

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"interactive-map"})
	public void categoryFilterAvailabilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			int i = 0;
			List<WebElement> filters = mydriver.findElements(By.xpath(categoryLabel));
			List<WebElement> filterIcons = mydriver.findElements(By.xpath(categoryIcon));

			for (WebElement filter : filters) {
				scrollToElement(mydriver, filter, logger);
				hardAssert.assertTrue(verifyElementExists(logger, filter, "Filter " + i));
				hardAssert.assertTrue(verifyElementExists(logger, filterIcons.get(i), "FilterIcon " + i));

				i++;
			}
			iconPrimary = filterIcons.get(0).getAttribute("src");
			iconUrgent = filterIcons.get(1).getAttribute("src");
			iconSurgical = filterIcons.get(2).getAttribute("src");


	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"interactive-map"})
	public void categoryFilterationCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);

			scrollToElement(mydriver, mydriver.findElements(By.xpath(categoryIcon)).get(0), logger);
			mydriver.findElements(By.xpath(categoryIcon)).get(0).click();
			mydriver.findElements(By.xpath(categoryIcon)).get(1).click();
			mydriver.findElements(By.xpath(categoryIcon)).get(2).click();
			try {
				mydriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				hardAssert.assertFalse(
						verifyElementExists(logger, mydriver.findElement(By.xpath(mapLegends)), "Map Legends"));
			} catch (Exception e) {

			}
			mydriver.findElements(By.xpath(categoryIcon)).get(1).click();
			if (mydriver.findElement(By.xpath(mapLegends)).getAttribute("src").equals(iconUrgent)) {
				logger.info("Category Filters are working fine");
				mydriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			} else {
				logger.error("Category Filters are not working fine");
			}
			mydriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"interactive-map"})
	public void mapButtonVisiblityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			scrollToElement(mydriver, mapHomeButton, logger);
			hardAssert.assertTrue(verifyElementExists(logger, mapHomeButton, "Map Home Button"));
			hardAssert.assertTrue(verifyElementExists(logger, mapZoomInButton, "Map Zoom-in Button"));
			hardAssert.assertTrue(verifyElementExists(logger, mapZoomOutButton, "Map Zoom-out Button"));


	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"interactive-map"})
	public void mapButtonFunctionalityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			scrollToElement(mydriver, mapHomeButton, logger);
			mapHomeButton.click();
			mapZoomInButton.click();
			mapZoomOutButton.click();

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"interactive-map"})
	public void resultFilterFunctionality(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			scrollToElement(mydriver, gridView, logger);
			gridView.click();
			hardAssert.assertTrue(
					verifyElementExists(logger, mydriver.findElement(By.xpath(cdoButtons)), "Grid View Results"));
			listView.click();
			hardAssert.assertTrue(
					verifyElementExists(logger, mydriver.findElement(By.xpath(columnHeader)), "List View Results"));

	}

	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"interactive-map"})
	public void sideInformationPanelFunctionality(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			String cdoName;
			List<WebElement> cdoButton = mydriver.findElements(By.xpath(cdoButtons));
			try {
				scrollToElement(mydriver, gridView, logger);
				gridView.click();
			} catch (Exception e) {
				
			}
			int i = getRandomInteger(cdoButton.size(), 0);
			scrollToElement(mydriver, cdoButton.get(i), logger);
			cdoName = cdoButton.get(i).getAttribute("alt");
			cdoButton.get(i).click();
			pleaseWait(1, logger);
			if (verifyElementExists(logger, sidePanelLogo, "Side panel")) {
				softAssert.assertTrue(verifyElementExists(logger, sidePanelLogo, "Side panel"));
			} else {
				scrollToElement(mydriver, sidePanelLogo, logger);
			}
			pleaseWait(3, logger);
			hardAssert.assertTrue(sidePanelTitle.getText().equalsIgnoreCase(cdoName));
			hardAssert.assertTrue(verifyElementExists(logger, sidePanelLogo, "CDO Logo over side-panel"));
			sidePanelCloseButton.click();
			try {
				hardAssert.assertFalse(verifyElementExists(logger, sidePanelLogo, "Side panel"));
				logger.info("Side Panel Closed!!");
			} catch (Exception e) {
				logger.info("Side Panel Closed!!");
			}
			softAssert.assertAll();

	}

	@Test(priority = 7, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"interactive-map"})
	public void cdoButtonFunctionalityForMap(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);

			List<WebElement> cdoButton = mydriver.findElements(By.xpath(cdoButtons));
			pleaseWait(10, logger);
			int i = getRandomInteger(cdoButton.size(), 0);
			scrollToElement(mydriver, cdoButton.get(i), logger);
			
			cdoButton.get(i).click();
			pleaseWait(3, logger);
			hardAssert.assertTrue(verifyElementExists(logger, mapSection, "Map Section"));
			if (!verifyElementExists(logger, mydriver.findElements(By.xpath(mapLegends)).get(0), "CDO Legends")) {
				fail("CDO Legends are not visible to the user");
			}

	}

	@Test(priority = 8, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"interactive-map"})
	public void gridDescriptionFieldCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			try {
				scrollToElement(mydriver, gridDescription, logger);
			} catch (Exception e) {
				throw new SkipException("There is no Grid Description field available");
			}
			if (gridDescription.getText().isEmpty()) {
				fail("Grid Description is blank");
			} else {
				logger.info("Grid Description => " + gridDescription.getText());
			}

	}

	@Test(priority = 9, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"interactive-map"})
	public void listViewDataSegregation(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			 mydriver.get(cardUrl);
			scrollToElement(mydriver, listView, logger);
			listView.click();
			hardAssert.assertEquals(mydriver.findElements(By.xpath(columnHeader)).size(), 3);
			logger.info("CDO Data has been divided into following :\n"
					+ mydriver.findElements(By.xpath(columnHeader)).get(0).getText() + "\n"
					+ mydriver.findElements(By.xpath(columnHeader)).get(1).getText() + "\n"
					+ mydriver.findElements(By.xpath(columnHeader)).get(2).getText());

	}
}
