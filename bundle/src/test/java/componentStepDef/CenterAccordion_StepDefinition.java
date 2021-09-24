package componentStepDef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.CenterAccordion_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class CenterAccordion_StepDefinition extends CenterAccordion_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(CenterAccordion_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(CenterAccordion_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new CenterAccordion_page();
		ExtentTestManager.startTest(CenterAccordion_StepDefinition.class.getName());
		setTagForTestClass("Center Accordion [Medex]", author, CenterAccordion_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(CenterAccordion_StepDefinition.class);
		logger.info("Urls for '" + CenterAccordion_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(CenterAccordion_StepDefinition.class.getName(), currentDomain);

		driverMap.put(CenterAccordion_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + CenterAccordion_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
	}

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void mainDefaultElementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		int i = 1;
		List<WebElement> totalBlades = mydriver.findElements(By.xpath(blades));
		for (WebElement blade : totalBlades) {
			scrollToElement(mydriver, blade, logger);
			softAssert.assertTrue(verifyElementExists(logger, blade, "Blade " + i));
			customTestLogs.get()
					.add("Checked if blade-" + i + " available: " + verifyElementExists(logger, blade, "Blade " + i));
			i++;

		}
		if (i != 4) {
			softAssert.fail("Expected '3' expanding blades but found '" + i + "' blades");
		}
		i = 1;

		softAssert.assertTrue(verifyElementExists(logger, blade4CtaLink, "CTA Blade "));
		customTestLogs.get()
				.add("Checked if CTA blade available: " + verifyElementExists(logger, blade4CtaLink, "CTA Blade "));

		List<WebElement> bladelabels = mydriver.findElements(By.xpath(bladeLabels));
		for (WebElement blade : bladelabels) {
			scrolltillvisibility();
			softAssert.assertTrue(verifyElementExists(logger, blade, "Blade label " + i));
			softAssert.assertFalse(blade.getAttribute("innerText").isEmpty());
			customTestLogs.get().add("Checked if blade-" + i + " label available: "
					+ verifyElementExists(logger, blade, "Blade label " + i));
			i++;
		}
		i = 1;
		// Default elements specific to Blade 1
		scrolltillvisibility();
		bladelabels.get(0).click();
		pleaseWait(3, logger);

		List<WebElement> blade1Icons = mydriver.findElements(By.xpath(blade1icons));
		List<WebElement> blade1Headers = mydriver.findElements(By.xpath(blade1ColumnHeaders));
		for (WebElement icon : blade1Icons) {
			softAssert.assertTrue(verifyElementExists(logger, icon, "Blade 1's Icon-" + i));
			customTestLogs.get().add("Checked if blade-1's Icon-" + i + " available: "
					+ verifyElementExists(logger, icon, "Blade 1's Icon-" + i));
			softAssert
					.assertTrue(verifyElementExists(logger, blade1Headers.get(i - 1), "Blade 1's Column Header-" + i));
			softAssert.assertFalse(blade1Headers.get(i - 1).getAttribute("innerText").isEmpty());

			customTestLogs.get().add("Checked if blade-1's Column-" + i + " header available: "
					+ verifyElementExists(logger, blade1Headers.get(i - 1), "Blade 1's Column Header-" + i));

			i++;
		}
		i = 1;
		// Default elements specific to Blade 2
		scrolltillvisibility();
		bladelabels.get(1).click();
		pleaseWait(2, logger);

		softAssert.assertTrue(verifyElementExists(logger, blade2ContentDescription, "Blade 2's content description"));
		softAssert.assertFalse(blade2ContentDescription.getAttribute("innerText").isEmpty());
		customTestLogs.get().add("Checked if Blade 2's content description available: "
				+ verifyElementExists(logger, blade2ContentDescription, "Blade 2's content description"));
		softAssert.assertTrue(verifyElementExists(logger, blade2ContentHeader, "Blade 2's content header"));
		softAssert.assertFalse(blade2ContentHeader.getAttribute("innerText").isEmpty());
		customTestLogs.get().add("Checked if Blade 2's content header available: "
				+ verifyElementExists(logger, blade2ContentHeader, "Blade 2's content header"));
		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void bladeExpandableFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		List<WebElement> totalBlades = mydriver.findElements(By.xpath(blades));
		totalBlades.get(0).click();
		scrollToElement(mydriver, totalBlades.get(1), logger).click();
		List<WebElement> expanded = mydriver.findElements(By.xpath(expandedBlade));
		customTestLogs.get()
				.add("Checking if only one blade expands at a time: " + String.valueOf(expanded.size()).contains("1"));
		hardAssert.assertEquals(expanded.size(), 1);
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void blade1ViewAllRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		mydriver.findElements(By.xpath(blades)).get(0).click();
		String expLink = viewAllButton.getAttribute("href");
		String handle = mydriver.getWindowHandle();
		scrollToElement(mydriver, viewAllButton, logger);
		viewAllButton.click();
		pleaseWait(5, logger);
		customTestLogs.get().add("Checking 'view all' button redirection");
		customTestLogs.get().add("Expected Link: " + expLink);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void ctaBladeRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		String expLink = blade4CtaLink.getAttribute("href");
		String handle = mydriver.getWindowHandle();
		scrollToElement(mydriver, blade4CtaLink, logger).click();
		customTestLogs.get().add("Checking CTA Blade redirection");
		customTestLogs.get().add("Expected Link: " + expLink);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void blade1ServiceListCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		mydriver.findElements(By.xpath(blades)).get(0).click();
		boolean isAvailable = false;

		try {
			mydriver.findElement(By.xpath(blade1ListElements)).isDisplayed();
			isAvailable = true;
		} catch (Exception e) {
			customTestLogs.get().add("Checking if service list is avaialable: " + isAvailable);
			throw new SkipException("There're no service list available");
		}
		customTestLogs.get().add("Checking if service list is avaialable: " + isAvailable);
		for (WebElement service : mydriver.findElements(By.xpath(blade1ListElements))) {
			scrollToElement(mydriver, service, logger);
			hardAssert.assertTrue(verifyElementExists(logger, service, service.getAttribute("innerText")));
			hardAssert.assertFalse(service.getAttribute("innerText").isEmpty());
		}
	}

	@Test(priority = 6, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void blade1ServicesLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();

		scrollToElement(mydriver, mydriver.findElements(By.xpath(blades)).get(0), logger).click();
		boolean isAvailable = false;

		try {
			mydriver.findElement(By.xpath(blade1ListElements)).isDisplayed();
			isAvailable = true;
		} catch (Exception e) {
			customTestLogs.get().add("Checking if service list is avaialable: " + isAvailable);
			throw new SkipException("There're no service list available");
		}
		customTestLogs.get().add("Checking if service list is avaialable: " + isAvailable);
		List<WebElement> links = mydriver.findElements(By.xpath(blade1ListElements));
		int i = getRandomInteger(links.size(), 0);
		scrollToElement(mydriver, links.get(i), logger);
		String expLink = links.get(i).getAttribute("href");
		String handle = mydriver.getWindowHandle();
		links.get(i).click();
		pleaseWait(5, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 7, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void blade2InvestmentOptionsListCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		mydriver.findElements(By.xpath(blades)).get(1).click();
		boolean isAvailable = false;
		try {
			mydriver.findElement(By.xpath(blade2InsuranceResultElements)).isDisplayed();
			isAvailable = true;
		} catch (Exception e) {
			customTestLogs.get().add("Checking if insurance options list is avaialable: " + isAvailable);
			throw new SkipException("There're no Insurance option available");
		}
		customTestLogs.get().add("Checking if insurance options list is avaialable: " + isAvailable);
		for (WebElement element : mydriver.findElements(By.xpath(blade2InsuranceResultElements))) {
			scrollToElement(mydriver, element, logger);
			hardAssert.assertTrue(verifyElementExists(logger, element, element.getAttribute("innerText")));
			hardAssert.assertFalse(element.getAttribute("innerText").isEmpty());
		}

	}

	@Test(priority = 8, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"centers-accordion" })
	public void blade2StateSelectionFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, centerAccordionSection, logger);
		scrolltillvisibility();
		mydriver.findElements(By.xpath(blades)).get(1).click();
		boolean isAvailable = false;
		try {
			if (hiddenSearch.isDisplayed() == false) {
				isAvailable = false;
				throw new Exception();
			} else {
				isAvailable = true;
			}

		} catch (Exception e) {
			customTestLogs.get().add("Checking if insurance options list is avaialable: " + isAvailable);
			throw new SkipException("There're no Insurance option available");
		}
		customTestLogs.get().add("Checking if insurance options list is avaialable: " + isAvailable);
		scrollToElement(mydriver, blade2InsuranceStateSelect, logger);
		selectByOptionIndex(blade2InsuranceStateSelect, 3, logger);
		Select ele = new Select(blade2InsuranceStateSelect);
		hardAssert.assertEquals(ele.getFirstSelectedOption(),
				selectByOptionIndex(blade2InsuranceStateSelect, 3, logger));
	}

}
