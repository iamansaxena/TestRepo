package componentStepDef;

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

import compontentPages.AccordionV2_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class AccordionV2_StepDefinition extends AccordionV2_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(AccordionV2_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(AccordionV2_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new AccordionV2_page();
		ExtentTestManager.startTest(AccordionV2_StepDefinition.class.getName());
		setTagForTestClass("Accordion v2", author, AccordionV2_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(AccordionV2_StepDefinition.class);
		logger.info("Urls for '" + AccordionV2_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(AccordionV2_StepDefinition.class.getName(), currentDomain);

		driverMap.put(AccordionV2_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + AccordionV2_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
	// customTestLogs.get().add("Checking if Section Title field is available: "
	// + verifyElementExists(logger, header, "Section Title"));
	// softAssert.assertTrue(verifyElementExists(logger, header, "Section Title"));
	// customTestLogs.get()
	// .add("Checking if Section Title field blank: " +
	// header.getAttribute("innerText").isEmpty());
	// softAssert.assertFalse(header.getAttribute("innerText").isEmpty());
	//
	// customTestLogs.get().add("Checking if Section Description field is available:
	// "
	// + verifyElementExists(logger, mainDescription, "Section Description"));
	// softAssert.assertTrue(verifyElementExists(logger, mainDescription, "Section
	// Description"));
	// customTestLogs.get().add("Checking if Section Description field blank: "
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
			customTestLogs.get().add("Checking if Accordion Node is available: "
					+ verifyElementExists(logger, nodeLabels.get(0), "First accordion node"));
			scrollToElement(mydriver, nodeLabels.get(0), logger);
		} catch (Exception e) {
			throw new SkipException("There're no nodes available!");
		}

		softAssert.assertTrue(verifyElementExists(logger, nodeLabels.get(0), "First accordion node"));
		customTestLogs.get().add("Checking if Accordion Node's Label is blank: "
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
			customTestLogs.get().add("Checking if Accordion Node is available: "
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
		customTestLogs.get().add("Checking if Accordion Node " + i + " is expanded: " + node.get(i));
		scrollToElement(mydriver, node.get(i), logger);
		node.get(i).click();
		pleaseWait(3, logger);
		softAssert.assertTrue(node.get(i).getAttribute("aria-expanded").equals("false"));
		customTestLogs.get().add("Checking if Accordion Node " + i + " is Contracted: " + node.get(i));

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
			customTestLogs.get().add(
					"Checking if Node Column is available: " + verifyElementExists(logger, node.get(0), "Node Column"));
			scrollToElement(mydriver, node.get(0), logger);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SkipException("There're no nodes available!");

		}
		customTestLogs.get()
				.add("Checking if Node Column field is blank: " + node.get(0).getAttribute("innerText").isEmpty());
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
			customTestLogs.get().add("Checking if Accordion Node is available: "
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
		customTestLogs.get().add("Checking if Accordion Node " + i + " is expanded: " + node.get(i));
		customTestLogs.get().add("Checking if Accordion Node's close button " + i + " is available: "
				+ verifyElementExists(logger, closeButton.get(i), "Close button " + i + ""));
		scrollToElement(mydriver, closeButton.get(i), logger);
		closeButton.get(i).click();
		pleaseWait(3, logger);
		softAssert.assertTrue(node.get(i).getAttribute("aria-expanded").equals("false"));
		customTestLogs.get().add("Checking if Accordion Node " + i + " is Contracted: "
				+ node.get(i).getAttribute("aria-expanded").equals("false"));

		softAssert.assertAll();

	}

}
