package componentStepDef;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ServicesSectionMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

/**
 * @author amohan31
 *
 */
public class ServicesSectionMedex_StepDefinition extends ServicesSectionMedex_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(ServicesSectionMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ServicesSectionMedex_StepDefinition.class.getName());
		new ServicesSectionMedex_page();

		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		ExtentTestManager.startTest(ServicesSectionMedex_StepDefinition.class.getName());
		setTagForTestClass("Services Section [Medex]", author, ServicesSectionMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ServicesSectionMedex_StepDefinition.class);
		logger.info("Urls for '" + ServicesSectionMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ServicesSectionMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ServicesSectionMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + ServicesSectionMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
	}

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"services-section"})
	public void mainDefaultElementVisibilityCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, servicesSection, logger);

			int i = 1;
			scrolltillvisibility();
			pleaseWait(3, logger);

			for (WebElement icon : serviceSectionCoulmnIcons) {
				softAssert.assertTrue(verifyElementExists(logger, icon, "Blade 1's Icon-" + i));
				customTestLogs.get()
				.add("Checked if Icon-" + i + " available: " + verifyElementExists(logger, icon, "Icon-" + i));
				softAssert.assertTrue(
						verifyElementExists(logger, serviceSectionColumnHeaders.get(i - 1), "Column Header-" + i));
				softAssert.assertFalse(serviceSectionColumnHeaders.get(i - 1).getAttribute("innerText").isEmpty());
				customTestLogs.get().add("Checked if Column-" + i + " header available: "
						+ verifyElementExists(logger, serviceSectionColumnHeaders.get(i - 1), "Column Header-" + i));

				i++;
			}


	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"services-section"})
	public void viewAllButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, servicesSection, logger);
			scrolltillvisibility();
			String expLink = viewAllButton.getAttribute("href");
			String handle = mydriver.getWindowHandle();
			scrollToElement(mydriver, viewAllButton, logger);
			viewAllButton.click();
			pleaseWait(5, logger);
			customTestLogs.get().add("Checking 'view all' button redirection");
			customTestLogs.get().add("Expected Link: " + expLink);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"services-section"})
	public void blade1ServiceListCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, servicesSection, logger);
			scrolltillvisibility();

			try {
				serviceSectionListElements.get(0).isDisplayed();
			} catch (Exception e) {
				customTestLogs.get().add("Checking if service list is avaialable: " + false);
				throw new SkipException("There're no service list available");
			}
			customTestLogs.get().add("Checking if service list is avaialable: " + true);
			for (WebElement service : serviceSectionListElements) {
				scrollToElement(mydriver, service, logger);
				hardAssert.assertTrue(verifyElementExists(logger, service, service.getAttribute("innerText")));
				hardAssert.assertFalse(service.getAttribute("innerText").isEmpty());
			}

	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"services-section"})
	public void blade1ServicesLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);


			
			mydriver.get(url);
			scrollToElement(mydriver, servicesSection, logger);
			scrolltillvisibility();

			try {
				serviceSectionListElements.get(0).isDisplayed();
			} catch (Exception e) {
				customTestLogs.get().add("Checking if service list is avaialable: " + false);
				throw new SkipException("There're no service list available");
			}
			customTestLogs.get().add("Checking if service list is avaialable: " + true);
			int i = getRandomInteger(serviceSectionListElements.size(), 0);
			scrollToElement(mydriver, serviceSectionListElements.get(i), logger);
			String expLink = serviceSectionListElements.get(i).getAttribute("href");
			String handle = mydriver.getWindowHandle();
			serviceSectionListElements.get(i).click();
			pleaseWait(5, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);


	}

}
