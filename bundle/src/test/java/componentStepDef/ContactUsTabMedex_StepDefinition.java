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

import compontentPages.ContactUsTabMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ContactUsTabMedex_StepDefinition extends ContactUsTabMedex_page {
	private String author = "Aman Srivastava";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(ContactUsTabMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ContactUsTabMedex_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new ContactUsTabMedex_page();
		ExtentTestManager.startTest(ContactUsTabMedex_StepDefinition.class.getName());
		setTagForTestClass("Contact Us Tab [Medex]", author, ContactUsTabMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ContactUsTabMedex_StepDefinition.class);
		logger.info("Urls for '" + ContactUsTabMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ContactUsTabMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ContactUsTabMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + ContactUsTabMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);
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
			"contactus-tab" })
	public void feedbackFormIntroCopyFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		try {
			formsIntroCopies.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Form 1 Intro Copy field is not available");
		}
		customTestLogs.get().add("Is Intro Copy field authored: " + formsIntroCopies.get(0).isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, formsIntroCopies.get(0), "Form-1 Intro Copy"));
		customTestLogs.get().add("Content: " + formsIntroCopies.get(0).getAttribute("innerText"));
		softAssert.assertFalse(formsIntroCopies.get(0).getAttribute("innerText").isEmpty());
		softAssert.assertAll();
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void feedbackFormDirectionalCopyFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		try {
			formsDirectionalCopies.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Form 1 Directional Copy field is not available");
		}
		customTestLogs.get().add("Is Directional Copy field authored: " + formsDirectionalCopies.get(0).isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, formsDirectionalCopies.get(0), "Form-1 Directional Copy"));
		customTestLogs.get().add("Content: " + formsDirectionalCopies.get(0).getAttribute("innerText"));
		softAssert.assertFalse(formsDirectionalCopies.get(0).getAttribute("innerText").isEmpty());
		softAssert.assertAll();
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void feedbackFormErrorOnInvalidDataCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		inputInvalidData(formsSwitchingTabs, 0, logger);
		int i = 1;
		assertForm1ErrorMessage();
		for (WebElement error : formErrorMessages) {
			customTestLogs.get().add("Error " + i + " => " + error.getAttribute("innerText") + " '"
					+ verifyElementExists(logger, error, "Feedback Form Error " + i + "") + "' ");
			hardAssert.assertTrue(verifyElementExists(logger, error, "Feedback Form Error " + i + ""));
			i++;
		}
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void careerFormIntroCopyFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		try {
			formsIntroCopies.get(1).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Form 2 Intro Copy field is not available");
		}
		scrolltillvisibility(formsSwitchingTabs.get(1));
		formsSwitchingTabs.get(1).click();
		customTestLogs.get().add("Is Intro Copy field authored: " + formsIntroCopies.get(1).isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, formsIntroCopies.get(1), "Form-2 Intro Copy"));
		customTestLogs.get().add("Content: " + formsIntroCopies.get(1).getAttribute("innerText"));
		softAssert.assertFalse(formsIntroCopies.get(1).getAttribute("innerText").isEmpty());
		softAssert.assertAll();
	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void careerFormDirectionalCopyFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);

		try {
			formsDirectionalCopies.get(1).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Form 2 Directional Copy field is not available");
		}
		scrolltillvisibility(formsSwitchingTabs.get(1));
		formsSwitchingTabs.get(1).click();
		customTestLogs.get().add("Is Directional Copy field authored: " + formsDirectionalCopies.get(1).isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, formsDirectionalCopies.get(1), "Form-2 Directional Copy"));
		customTestLogs.get().add("Content: " + formsDirectionalCopies.get(1).getAttribute("innerText"));
		softAssert.assertFalse(formsDirectionalCopies.get(1).getAttribute("innerText").isEmpty());
		softAssert.assertAll();
	}

	@Test(priority = 6, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void careerFormErrorOnInvalidDataCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		inputInvalidData(formsSwitchingTabs, 1, logger);
		int i = 1;

		assertForm2ErrorMessage();
		for (WebElement error : formErrorMessages) {
			customTestLogs.get().add("Error " + i + " => " + error.getAttribute("innerText") + " '"
					+ verifyElementExists(logger, error, "Career Form Error " + i + "") + "' ");
			hardAssert.assertTrue(verifyElementExists(logger, error, "Career Form Error " + i + ""));
			i++;
		}
	}

	/////////////////////////////////////////////

	@Test(priority = 7, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void employerFormIntroCopyFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		try {
			formsIntroCopies.get(2).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Form 2 Intro Copy field is not available");
		}
		scrolltillvisibility(formsSwitchingTabs.get(2));
		formsSwitchingTabs.get(2).click();
		customTestLogs.get().add("Is Intro Copy field authored: " + formsIntroCopies.get(2).isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, formsIntroCopies.get(2), "Form-3 Intro Copy"));
		customTestLogs.get().add("Content: " + formsIntroCopies.get(2).getAttribute("innerText"));
		softAssert.assertFalse(formsIntroCopies.get(2).getAttribute("innerText").isEmpty());
		softAssert.assertAll();
	}

	@Test(priority = 8, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void employerFormDirectionalCopyFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		try {
			formsDirectionalCopies.get(2).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Form 3 Directional Copy field is not available");
		}
		scrolltillvisibility(formsSwitchingTabs.get(2));
		scrollToElement(mydriver, formsSwitchingTabs.get(2), logger).click();
		customTestLogs.get().add("Is Directional Copy field authored: " + formsDirectionalCopies.get(2).isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, formsDirectionalCopies.get(2), "Form-3 Directional Copy"));
		customTestLogs.get().add("Content: " + formsDirectionalCopies.get(2).getAttribute("innerText"));
		softAssert.assertFalse(formsDirectionalCopies.get(2).getAttribute("innerText").isEmpty());
		softAssert.assertAll();
	}

	@Test(priority = 9, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void employerFormErrorOnInvalidDataCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		inputInvalidData(formsSwitchingTabs, 2, logger);
		int i = 1;
		assertForm2ErrorMessage();
		for (WebElement error : formErrorMessages) {
			customTestLogs.get().add("Error " + i + " => " + error.getAttribute("innerText") + " '"
					+ verifyElementExists(logger, error, "Employer Form Error " + i + "") + "' ");
			hardAssert.assertTrue(verifyElementExists(logger, error, "Employer Form Error " + i + ""));
			i++;
		}
	}

	/////////////////////////////////////////////

	@Test(priority = 10, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void billingFormIntroCopyFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		try {
			formsIntroCopies.get(3).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Form 2 Intro Copy field is not available");
		}
		scrollToElement(mydriver, formsSwitchingTabs.get(3), logger).click();
		customTestLogs.get().add("Is Intro Copy field authored: " + formsIntroCopies.get(3).isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, formsIntroCopies.get(3), "Form-4 Intro Copy"));
		customTestLogs.get().add("Content: " + formsIntroCopies.get(3).getAttribute("innerText"));
		softAssert.assertFalse(formsIntroCopies.get(3).getAttribute("innerText").isEmpty());
		softAssert.assertAll();
	}

	@Test(priority = 11, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void billingFormDirectionalCopyFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		try {
			formsDirectionalCopies.get(3).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Form 3 Directional Copy field is not available");
		}
		scrolltillvisibility(formsSwitchingTabs.get(2));
		scrollToElement(mydriver, formsSwitchingTabs.get(3), logger).click();
		customTestLogs.get().add("Is Directional Copy field authored: " + formsDirectionalCopies.get(3).isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, formsDirectionalCopies.get(3), "Form-4 Directional Copy"));
		customTestLogs.get().add("Content: " + formsDirectionalCopies.get(3).getAttribute("innerText"));
		softAssert.assertFalse(formsDirectionalCopies.get(3).getAttribute("innerText").isEmpty());
		softAssert.assertAll();
	}

	@Test(priority = 12, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void billingFormErrorOnInvalidDataCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		inputInvalidData(formsSwitchingTabs, 3, logger);
		int i = 1;
		assertForm4ErrorMessage();
		for (WebElement error : formErrorMessages) {
			customTestLogs.get().add("Error " + i + " => " + error.getAttribute("innerText") + " '"
					+ verifyElementExists(logger, error, "Billing Form Error " + i + "") + "' ");
			hardAssert.assertTrue(verifyElementExists(logger, error, "Billing Form Error " + i + ""));
			i++;
		}
	}

	@Test(priority = 13, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void generalEnquiryFormErrorOnInvalidDataCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		inputInvalidData(formsSwitchingTabs, 4, logger);
		int i = 1;
		assertForm5ErrorMessage();
		for (WebElement error : formErrorMessages) {
			customTestLogs.get().add("Error " + i + " => " + error.getAttribute("innerText") + " '"
					+ verifyElementExists(logger, error, "General Enquiry Form Error " + i + "") + "' ");
			hardAssert.assertTrue(verifyElementExists(logger, error, "General Enquiry Form Error " + i + ""));
			i++;
		}
	}

	@Test(priority = 14, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void mainSectionHeaderFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		try {
			sectionHeading.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Main section header is not available");
		}
		customTestLogs.get().add("Is main section header visible: " + sectionHeading.isDisplayed());
		softAssert.assertTrue(verifyElementExists(logger, sectionHeading, "Section Header"));
		customTestLogs.get().add("Content: " + sectionHeading.getAttribute("innerText"));
		softAssert.assertFalse(sectionHeading.getAttribute("innerText").isEmpty());
		softAssert.assertAll();
	}

	@Test(priority = 15, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void careerFormDistributionListCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		customTestLogs.get().add("Distribution list: " + form2DistributionList.getAttribute("value"));
		hardAssert.assertFalse(form2DistributionList.getAttribute("value").isEmpty());
	}

	@Test(priority = 16, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void billingFormDistributionListCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		customTestLogs.get().add("Distribution list: " + form4DistributionList.getAttribute("value"));
		hardAssert.assertFalse(form4DistributionList.getAttribute("value").isEmpty());
	}

	@Test(priority = 16, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"contactus-tab" })
	public void formSubmissionFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrolltillvisibility(contactUsSection);
		inputValidDataForm1(formsSwitchingTabs, logger);
		customTestLogs.get().add("Successfully Submitted Form 1");
		inputValidDataForm2(formsSwitchingTabs, logger);
		customTestLogs.get().add("Successfully Submitted Form 2");
		inputValidDataForm3(formsSwitchingTabs, logger);
		customTestLogs.get().add("Successfully Submitted Form 3");
		inputValidDataForm4(formsSwitchingTabs, logger);
		customTestLogs.get().add("Successfully Submitted Form 4");
		inputValidDataForm5(formsSwitchingTabs, logger);
		customTestLogs.get().add("Successfully Submitted Form 5");

	}

}
