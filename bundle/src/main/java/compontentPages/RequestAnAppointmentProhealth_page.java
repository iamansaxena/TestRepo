package compontentPages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import core.Base;

public class RequestAnAppointmentProhealth_page extends Base {

	protected static WebDriver mydriver;

	@FindBy(xpath = "(//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')])[1]")
	protected static WebElement appointmentSection;

	// @FindBy(xpath=
	// "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"First
	// Name\")]")
	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"First Name\")]")
	protected static WebElement firstNameInputField;

	@FindBy(xpath = "(//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"Last Name\")])[1]")
	public static WebElement lastNameInputField;

	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"Birth Date\")]")
	protected static WebElement birthDateInputField;

	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"Phone\")]")
	protected static WebElement phoneNumberInputField;

	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"Best\")]")
	protected static WebElement bestTimeSelectField;

	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"Provider Last\")]")
	protected static WebElement providerLastNameInputField;

	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"Patient Status\")]")
	protected static WebElement patientStatusSelectField;

	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"Preferred day\")]")
	protected static WebElement preferredDaySelectField;

	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"Preferred time\")]")
	protected static WebElement preferredTimeSelectField;

	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"Reason\")]")
	protected static WebElement reasonInputField;

	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"I agree\")]")
	protected static WebElement agreeCheckBoxField;

	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"Reset\")]")
	protected static WebElement resetButton;

	@FindBy(xpath = "//*[contains(@data-path,'/content/forms/af/Marketing_Forms/Adaptive_Forms/Appointment_Request')]//*[contains(@aria-label,\"Submit\")]")
	protected static WebElement submitButton;

	@FindBy(xpath = "//*[@class=\"logo__img logo__img--220\"]")
	protected static WebElement testEle;
	
	@FindBy(xpath = "(//*[contains(@data-path,\"Appointment_Request_Confirmation\")])[1]")
	public static WebElement confirmationMessage;
	
	@FindBy(xpath="//*[@class=\"aemformcontainer\"]/ancestor::*//li[@class=\"ui-menu-item\"]")
	protected static List<WebElement> lastNameSuggestionOptions;

	public RequestAnAppointmentProhealth_page() {
		PageFactory.initElements(mydriver, this);
	}

	protected static void switchToForm() {
		mydriver.switchTo().frame("aemFormFrame");
	}

	protected static String isErrorMessageAvailable(ArrayList<String> reportLogs,WebElement e, Logger logger) {
		String errorXpath = "ancestor::*/following-sibling::*[@class=\"guideFieldError\"]";
		logger.info("Error Message for '" + e.getAttribute("aria-label") + "'  ==>  "
				+ e.findElement(By.xpath(errorXpath)).getText());
		reportLogs.add("Error for "+e.getAttribute("aria-label")+" => "+verifyElementExists(logger, e.findElement(By.xpath(errorXpath)),"Error Message for [" + e.getAttribute("aria-label") + "]"));
		if (e.getAttribute("aria-label").contains("*")) {
			focusElement(mydriver, scrollToElement(mydriver, e.findElement(By.xpath(errorXpath)), logger));
			hardAssert.assertTrue(verifyElementExists(logger, e.findElement(By.xpath(errorXpath)),"Error Message for [" + e.getAttribute("aria-label") + "]"));
			return e.findElement(By.xpath(errorXpath)).getText();
		} else {
			return null;
		}
	}

	protected void ifFormFieldAvailableAndVisible(WebElement field, Logger logger, ArrayList<String> reportLogs,
			String fieldName) {
		focusElement(mydriver, scrollToElement(mydriver, field, logger));
		reportLogs
				.add("Is '" + fieldName + "' available and visible: " + verifyElementExists(logger, field, fieldName));
		hardAssert.assertTrue(verifyElementExists(logger, field, fieldName));

	}
	
	protected void fillForm(boolean toBeInvalid, ArrayList<String> reportLogs) {
		if(toBeInvalid==true) {
			birthDateInputField.sendKeys("23/23/1234");
			phoneNumberInputField.sendKeys("sdsdasfsdfa#4400556677");
			providerLastNameInputField.sendKeys("Abcdefgh IJklmn opqrst");
			focusElement(mydriver, scrollToElement(mydriver, submitButton, logger));
			submitButton.click();
			isErrorMessageAvailable(reportLogs,firstNameInputField,logger);
			isErrorMessageAvailable(reportLogs,lastNameInputField,logger);
			isErrorMessageAvailable(reportLogs,birthDateInputField,logger);
			isErrorMessageAvailable(reportLogs,phoneNumberInputField,logger);
			isErrorMessageAvailable(reportLogs,bestTimeSelectField,logger);
			isErrorMessageAvailable(reportLogs,providerLastNameInputField,logger);
			isErrorMessageAvailable(reportLogs,patientStatusSelectField,logger);
			isErrorMessageAvailable(reportLogs,preferredDaySelectField,logger);
			isErrorMessageAvailable(reportLogs,preferredTimeSelectField,logger);
			isErrorMessageAvailable(reportLogs,reasonInputField,logger);
			isErrorMessageAvailable(reportLogs,agreeCheckBoxField,logger);
			
		}
		else if(toBeInvalid==false) {
			
			scrollToElement(mydriver, firstNameInputField, logger).sendKeys("Test");
			scrollToElement(mydriver, lastNameInputField, logger).sendKeys("User");
			scrollToElement(mydriver, birthDateInputField, logger).clear();
			birthDateInputField.sendKeys("09/26/1996");
			scrollToElement(mydriver, phoneNumberInputField, logger)
		.sendKeys("444-444-4444");
			scrollToElement(mydriver, bestTimeSelectField, logger);
		selectByOptionName(logger, bestTimeSelectField, "Morning");
		scrollToElement(mydriver, providerLastNameInputField, logger);
		providerLastNameInputField.sendKeys("a");
		getWebDriverWait(mydriver, 20).until(ExpectedConditions.visibilityOf(lastNameSuggestionOptions.get(0)));
		lastNameSuggestionOptions.get(0).click();
		scrollToElement(mydriver, patientStatusSelectField, logger);
		selectByOptionName(logger, patientStatusSelectField, "New");
		scrollToElement(mydriver, preferredDaySelectField, logger);
		selectByOptionName(logger, preferredDaySelectField, "Monday");
		scrollToElement(mydriver, preferredTimeSelectField, logger);
		selectByOptionName(logger, preferredTimeSelectField, "1 week");
		scrollToElement(mydriver, reasonInputField, logger).sendKeys("Dummy Reason");
		scrollToElement(mydriver, agreeCheckBoxField, logger).click();
		submitButton.click();
		getWebDriverWait(mydriver, 60).until(ExpectedConditions.visibilityOf(confirmationMessage));
		hardAssert.assertTrue(verifyElementExists(logger, confirmationMessage, "Confirmation Page"));
		
		}
	}
}
