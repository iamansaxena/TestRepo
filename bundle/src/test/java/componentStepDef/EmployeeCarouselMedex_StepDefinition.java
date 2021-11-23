package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.EmployeeCarouselMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class EmployeeCarouselMedex_StepDefinition extends EmployeeCarouselMedex_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {
		fetchSession(EmployeeCarouselMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(EmployeeCarouselMedex_StepDefinition.class.getName());
		new EmployeeCarouselMedex_page();

		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		ExtentTestManager.startTest(EmployeeCarouselMedex_StepDefinition.class.getName());
		setTagForTestClass("Employee Carousel [Medex]", author, EmployeeCarouselMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(EmployeeCarouselMedex_StepDefinition.class);
		logger.info("Urls for '" + EmployeeCarouselMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(EmployeeCarouselMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(EmployeeCarouselMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + EmployeeCarouselMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
			"employee-carousel" })
	public void employeesDetailsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility();
		try {
			employeeColumns.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no employee column available");
		}

		customTestLogs.get().add("Employee Columns available" + employeeColumns.size());
		int i = 0;
		int j = 1;
		for (WebElement empColumn : employeeColumns) {
			scrollToEmployeeColumn(empColumn);
			customTestLogs.get().add("Verifying employee column details for card : '" + j + "'");
			softAssert.assertTrue(verifyElementExists(logger, employeeHeaders.get(i), "employee Header '" + j + "'"));
			softAssert.assertTrue(
					verifyElementExists(logger, employeeDesignations.get(i), "employee designation '" + j + "'"));
			softAssert.assertTrue(verifyElementExists(logger, employeeDetails.get(i), "employee details '" + j + "'"));
			softAssert.assertTrue(verifyElementExists(logger, employeeImages.get(i), "employee images '" + j + "'"));
			softAssert
					.assertTrue(verifyElementExists(logger, employeeLocations.get(i), "employee location '" + j + "'"));
			assertEmployeeQuestion(j, empColumn, logger);

			i++;
			j++;
		}

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"employee-carousel" })
	public void sliderFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility();

		if (employeeColumns.size() > 2) {
			int i = getRandomInteger(employeeColumns.size() - 1, 0);
			int j = i + 1;
			logger.info("selected employee column for next button: '" + j + "'");
			slideCarouselNext(i);
			customTestLogs.get().add("Checking if next button working fine");
			hardAssert.assertTrue(verifyElementExists(logger, employeeColumns.get(i), "Column no. '" + j + "'"));
			i = getRandomInteger(employeeColumns.size() - 1, 0);
			logger.info("selected employee column for prev button: '" + j + "'");
			slideCarouselPrev(i);
			j = i + 1;
			customTestLogs.get().add("Checking if prev button working fine");
			hardAssert.assertTrue(verifyElementExists(logger, employeeColumns.get(i), "Column no. '" + j + "'"));
		}

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"employee-carousel" })
	public void sliderEndlessLoopCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility();
		if (employeeColumns.size() > 2) {
			carouselNextButton.click();
			slideCarouselNext(0);
			customTestLogs.get().add("Checking if endless loop is working fine");
			hardAssert.assertTrue(verifyElementExists(logger, employeeColumns.get(0), "Column no. '" + 1 + "'"));
		}

	}

}
