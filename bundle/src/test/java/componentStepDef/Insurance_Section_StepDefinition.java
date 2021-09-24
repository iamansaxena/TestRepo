package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.Insurance_Section_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class Insurance_Section_StepDefinition extends Insurance_Section_page {

	private String author = "Sreevidhya";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(Insurance_Section_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(Insurance_Section_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new Insurance_Section_page();
		ExtentTestManager.startTest(Insurance_Section_StepDefinition.class.getName());
		setTagForTestClass("Insurancet", author, Insurance_Section_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(Insurance_Section_StepDefinition.class);
		logger.info("Urls for '" + Insurance_Section_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(Insurance_Section_StepDefinition.class.getName(), currentDomain);

		driverMap.put(Insurance_Section_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + Insurance_Section_StepDefinition.class.getName() + "' =>\n" + driverMap);
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
			"insurance-section" })
	public void InsuranceVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, 30);
		wait.until(ExpectedConditions.visibilityOf(Insurancesection));
		scrollToElement(mydriver, Insurancesection, logger);
		boolean abc = false;
		softAssert.assertTrue(InsHeading.isDisplayed());
		softAssert.assertTrue(InsStatdropdown.isDisplayed());
		softAssert.assertTrue(Searchbtn.isDisplayed());
		softAssert.assertAll();
		if (abc != (InsHeading.isDisplayed() && InsStatdropdown.isDisplayed() && Searchbtn.isDisplayed())) {
			abc = true;
		}

		customTestLogs.get().add("Verifing the elememt is displayed: " + abc);

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"insurance-section" })
	public void InsuranceStateSuccessSearch(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, 30);
		wait.until(ExpectedConditions.visibilityOf(Insurancesection));
		scrollToElement(mydriver, Insurancesection, logger);
		softAssert.assertTrue(InsHeading.isDisplayed());
		softAssert.assertTrue(InsStatdropdown.isDisplayed());
		selectByValue(logger, InsStatdropdown, "FL");
		softAssert.assertTrue(Searchbtn.isDisplayed());
		Searchbtn.click();
		pleaseWait(6, logger);
		Select comboBox = new Select(InsStatdropdown);
		String selectedComboValue = comboBox.getFirstSelectedOption().getText();
		// customTestLogs.get().add("Verifingsearch result:
		// "+Successstatename.getAttribute("innerText").toString().equals(selectedComboValue));
		customTestLogs.get().add("Verifying search result: " + Successstatename.getAttribute("innerText").toString());
		hardAssert.assertTrue(Successstatename.getAttribute("innerText").toString().contains(selectedComboValue));
		softAssert.assertAll();

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"insurance-section" })
	public void NoInsuranceSearch(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, 30);
		wait.until(ExpectedConditions.visibilityOf(Insurancesection));
		scrollToElement(mydriver, Insurancesection, logger);
		softAssert.assertTrue(InsHeading.isDisplayed());
		softAssert.assertTrue(InsStatdropdown.isDisplayed());
		selectByValue(logger, InsStatdropdown, "NV");
		softAssert.assertTrue(Searchbtn.isDisplayed());
		Searchbtn.click();
		pleaseWait(6, logger);
		Select comboBox = new Select(InsStatdropdown);
		String selectedComboValue = comboBox.getFirstSelectedOption().getText();
		hardAssert.assertEquals(NoInsurancestatename.getText().toString().substring(3), selectedComboValue);
		customTestLogs.get().add("Verifingsearch result: "
				+ NoInsurancestatename.getText().toString().substring(3).equals(selectedComboValue));
		softAssert.assertAll();

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"insurance-section" })
	public void PromtPaySection(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		WebDriverWait wait = new WebDriverWait(mydriver, 30);
		wait.until(ExpectedConditions.visibilityOf(Insurancesection));
		scrollToElement(mydriver, Insurancesection, logger);
		softAssert.assertTrue(Promt_pay.isDisplayed());
		softAssert.assertTrue(Promt_pay_desc.isDisplayed());
		softAssert.assertAll();
		boolean abc = false;
		if (abc != (Promt_pay.isDisplayed() && Promt_pay_desc.isDisplayed())) {
			abc = true;
		}

		customTestLogs.get().add("Verifing the elememt is displayed: " + abc);

	}

}
