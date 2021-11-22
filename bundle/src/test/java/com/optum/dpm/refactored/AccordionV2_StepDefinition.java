package com.optum.dpm.refactored;

import static com.optum.dpm.reports.ExtentTestManager.getTest;
import static com.optum.dpm.utils.DPMTestUtils.getRandomInteger;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;
import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;
import static com.optum.dpm.utils.DPMTestUtils.skipNonExistingComponent;
import static com.optum.dpm.utils.DPMTestUtils.verifyElementExists;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.AccordionV2_page;

import core.CustomDataProvider;

public class AccordionV2_StepDefinition extends AccordionV2_page {
	private static final Logger logger = LogManager.getLogger(AccordionV2_StepDefinition.class);

	// Disabling this as this is a wrong TC
	// @Test(priority = 1, enabled = true,dataProvider = "data-provider",
	// dataProviderClass = CustomDataProvider.class, parameters = {"accordion-v2"})
	// public void defaultFieldsVisibilityCheck() {
	// skipNonExistingComponent(urls);
	//
	// 
	// mydriver.get(url);
	//// scrollToElement(mydriver, accordionSection, logger);
	// scrollToElement(mydriver, header, logger);
	// getTest().info("Checking if Section Title field is available: "
	// + verifyElementExists(logger, header, "Section Title"));
	// softAssert.assertTrue(verifyElementExists(logger, header, "Section Title"));
	// customTestLogs.get()
	// .add("Checking if Section Title field blank: " +
	// header.getAttribute("innerText").isEmpty());
	// softAssert.assertFalse(header.getAttribute("innerText").isEmpty());
	//
	// getTest().info("Checking if Section Description field is available:
	// "
	// + verifyElementExists(logger, mainDescription, "Section Description"));
	// softAssert.assertTrue(verifyElementExists(logger, mainDescription, "Section
	// Description"));
	// getTest().info("Checking if Section Description field blank: "
	// + mainDescription.getAttribute("innerText").isEmpty());
	// softAssert.assertFalse(header.getAttribute("innerText").isEmpty());
	//
	// softAssert.assertAll();
	// }
	// }

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"accordion-v2" })
	public void nodeLabelFieldsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, accordionSection, logger);
		List<WebElement> nodeLabels = mydriver.findElements(By.xpath(accordionNodeLabels));
		try {
			getTest().info("Checking if Accordion Node is available: "
					+ verifyElementExists(logger, nodeLabels.get(0), "First accordion node"));
			scrollToElement(mydriver, nodeLabels.get(0), logger);
		} catch (Exception e) {
			throw new SkipException("There're no nodes available!");
		}

		softAssert.assertTrue(verifyElementExists(logger, nodeLabels.get(0), "First accordion node"));
		getTest().info("Checking if Accordion Node's Label is blank: "
				+ nodeLabels.get(0).getAttribute("innerText").isEmpty());
		softAssert.assertFalse(nodeLabels.get(0).getAttribute("innerText").isEmpty());
		softAssert.assertAll();

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"accordion-v2" })
	public void nodeExpandContractFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, accordionSection, logger);
		List<WebElement> node = mydriver.findElements(By.xpath(accordionNodes));
		try {
			getTest().info("Checking if Accordion Node is available: "
					+ verifyElementExists(logger, node.get(0), "Accordion node"));
			scrollToElement(mydriver, node.get(0), logger);
		} catch (Exception e) {
			throw new SkipException("There're no nodes available!");
		}

		int i = getRandomInteger(node.size(), 0);
		scrollToElement(mydriver, node.get(i), logger);
		node.get(i).click();
		pleaseWait(3, logger);
		softAssert.assertTrue(node.get(i).getAttribute("aria-expanded").equals("true"));
		getTest().info("Checking if Accordion Node " + i + " is expanded: " + node.get(i));
		scrollToElement(mydriver, node.get(i), logger);
		node.get(i).click();
		pleaseWait(3, logger);
		softAssert.assertTrue(node.get(i).getAttribute("aria-expanded").equals("false"));
		getTest().info("Checking if Accordion Node " + i + " is Contracted: " + node.get(i));

		softAssert.assertAll();

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"accordion-v2" })
	public void nodeColumnFieldsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, accordionSection, logger);
		List<WebElement> node = mydriver.findElements(By.xpath(accordionNodeLeftColumn));
		try {
			scrollToElement(mydriver, mydriver.findElements(By.xpath(columnLeftParents)).get(0), logger).click();
			// new WebDriverWait(mydriver,
			// 30).until(ExpectedConditions.visibilityOfAllElements(node));
			getTest().info(
					"Checking if Node Column is available: " + verifyElementExists(logger, node.get(0), "Node Column"));
			scrollToElement(mydriver, node.get(0), logger);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SkipException("There're no nodes available!");

		}
		getTest().info("Checking if Node Column field is blank: " + node.get(0).getAttribute("innerText").isEmpty());
		softAssert.assertFalse(node.get(0).getAttribute("innerText").isEmpty());
		softAssert.assertAll();

	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"accordion-v2" })
	public void nodeExpandContractFunctionalityViaCloseButtonCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, accordionSection, logger);
		List<WebElement> node = mydriver.findElements(By.xpath(accordionNodes));
		try {
			getTest().info("Checking if Accordion Node is available: "
					+ verifyElementExists(logger, node.get(0), "Accordion node"));
			scrollToElement(mydriver, node.get(0), logger);
		} catch (Exception e) {
			throw new SkipException("There're no nodes available!");
		}
		List<WebElement> closeButton = mydriver.findElements(By.xpath(accordionNodeCloseButto));
		int i = getRandomInteger(node.size(), 0);
		scrollToElement(mydriver, node.get(i), logger);
		node.get(i).click();
		pleaseWait(3, logger);
		softAssert.assertTrue(node.get(i).getAttribute("aria-expanded").equals("true"));
		getTest().info("Checking if Accordion Node " + i + " is expanded: " + node.get(i));
		getTest().info("Checking if Accordion Node's close button " + i + " is available: "
				+ verifyElementExists(logger, closeButton.get(i), "Close button " + i + ""));
		scrollToElement(mydriver, closeButton.get(i), logger);
		closeButton.get(i).click();
		pleaseWait(3, logger);
		softAssert.assertTrue(node.get(i).getAttribute("aria-expanded").equals("false"));
		getTest().info("Checking if Accordion Node " + i + " is Contracted: "
				+ node.get(i).getAttribute("aria-expanded").equals("false"));

		softAssert.assertAll();

	}

}
