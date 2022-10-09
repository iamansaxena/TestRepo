package com.optum.dpm.page.model;

import static com.optum.dpm.reports.ExtentTestManager.getTest;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class AskForRefill_page extends Base {
	private String author = "Rekha Vasugan";
	private String tag = "AskForRefill";
	
	@FindBy(css = "input[id^='guideContainer'][type = 'text']")
	protected static List<WebElement> TextBox;

	@FindBy(css = "label[id^='guideContainer']")
	protected static List<WebElement> labelName;

	@FindBy(css = "button[id^='guideContainer']")
	protected static List<WebElement> button;

	@FindBy(css = "div.guideFieldError")
	protected static List<WebElement> errorMessageList;

	@FindBy(css = "div.guidetextdraw.guidefield div  h2")
	protected static WebElement successMessage;

	@FindBy(css = "div.datepicker-calendar-icon")
	protected static List<WebElement> datepicker;

	@FindBy(css = "input[id^='guideContainer'][type = 'checkbox']")
	protected static WebElement checkBox;

	@FindBy(css = "textarea[id^='guideContainer']")
	protected static WebElement textArea;

	@FindBy(css = "div li.dp-selected.dp-focus")
	protected static WebElement currentDate;

	@FindBy(css = "div.view.dp-monthview li[title='2']")
	protected static WebElement futureDate;

	@FindBy(css = "div.dp-leftnav")
	protected static WebElement previousMonth;
	
	@FindBy(css = "div.dp-rightnav")
	protected static WebElement nextMonth;

	/**
	 * This method verify WebElements
	 * @param mydriver
	 * @param logger
	 * @return
	 */
	public boolean verifyElements(WebDriver mydriver, Logger logger) {
		logger.info("Verifying elements visibility");
		boolean flag = true;
		try {
			mydriver.switchTo().frame("aemFormFrame");
			for (WebElement element : labelName) {
				getTest().info("Verify Label name: " + element.getText() + element.isDisplayed());
			}
			for (WebElement element : TextBox) {
				getTest().info("Verify Text box " + element.isDisplayed());
			}
			for (WebElement element : button) {
				getTest().info("Verify button text: " + element.getText() + element.isDisplayed());
			}
			getTest().info("Verify scuccess message: " + successMessage.getText() + successMessage.isDisplayed());

			getTest().info("Verify Label name: " + checkBox.getText() + checkBox.isDisplayed());
			System.out.println(checkBox.getText());

		} catch (ElementNotVisibleException e) {
			flag = false;
			logger.info("Element is missing");
		}
		return flag;
	}

	/**
	 * @param textValues - Strings to enter inside textbox
	 * @param logger
	 * @return true if we enter valid input and able to submit the form
	 */
	public boolean enterValuesintheForm(WebDriver mydriver, String[] textValues, Logger logger) {
		logger.info("Verify entering appropriate values");
		boolean flag = false;
		mydriver.switchTo().frame("aemFormFrame");
		logger.info("Entering appropriate values inside textbox");
		for (int value = 0; value < TextBox.size(); value++) {
			if (TextBox.get(value).getAttribute("aria-label").contains(labelName.get(value).getText())) {
				if (TextBox.get(value).getAttribute("aria-label").equalsIgnoreCase("Provider Last Name *")) {
					TextBox.get(value).clear();
					TextBox.get(value).click();
					TextBox.get(value).sendKeys(textValues[value]);
					TextBox.get(value).sendKeys(Keys.ARROW_DOWN, Keys.RETURN);
				} else if (TextBox.get(value).getAttribute("aria-label").contains("Appointment Date (m/d/yyyy) *")) {
					Date today = new Date();
					Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
					TextBox.get(value).clear();
					TextBox.get(value).click();
					TextBox.get(value).sendKeys(tomorrow.toString());
				}

				else {
					TextBox.get(value).clear();
					TextBox.get(value).sendKeys(textValues[value]);
				}
			}
		}
		if (textArea.isDisplayed() && textArea.isEnabled()) {
			logger.info("Entering appropriate values inside textbox");
			textArea.click();
			textArea.sendKeys(textValues[textValues.length - 1]);
		}
		if (checkBox.isDisplayed() && !checkBox.isSelected()) {
			checkBox.click();
		}
		if (button.get(1).isDisplayed()) {
			logger.info("Submitting the form by clicking button");
			button.get(1).click();
		}
		mydriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		mydriver.switchTo().defaultContent(); // Getting stale element reference exception in chrome browser. 
		mydriver.switchTo().frame("aemFormFrame");//So going back to parent iframe and switching back to form frame. Just like refresh.
		logger.info("Verifying Success message");
		if (successMessage.isDisplayed()) {
			if ((successMessage.getText().equalsIgnoreCase("Referral Request Submitted"))||(successMessage.getText().equalsIgnoreCase("Refill Request Submitted"))) {
				logger.info("Success message is displayed");
				flag = true;
			} else {
				logger.info("Success message is not displayed");
				flag = false;
			}
		}
		return flag;

	}

	/**
	 * This method will return true, if error message displayed while submitting empty form
	 * @param logger
	 * @return
	 */
	public boolean verifyErrorMessagebySubmittingEmptyFields(WebDriver mydriver,Logger logger) {
		int errorMessageNotDisplayed=0;
		mydriver.switchTo().frame("aemFormFrame");
		try {
			if (button.get(1).isDisplayed()) {
				logger.info("Submitting empty form");
				button.get(1).click();
			}
			logger.info("Verifying error message");
			for (int value = 0; value < labelName.size(); value++) {
				if(errorMessageList.get(value).isDisplayed()) {
				getTest().info("Verify error message for Label name: " + labelName.get(value).getText() + ":"
						+ errorMessageList.get(value).getText());
				}
				else if(!labelName.get(value).getText().contains("*")) {
					getTest().info(labelName.get(value).getText() + "is not mandatory field. So error message is not showing.");
				}
				else {
					errorMessageNotDisplayed++;
				}
			}
		} catch (ElementNotVisibleException e) {
			errorMessageNotDisplayed++;
			logger.info("Element/Error message is missing");
		}
		if(errorMessageNotDisplayed>0) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * @param input - invalid input
	 * @param label - Valid label Name
	 * @param format 
	 * @param message - Error message
	 * @param logger
	 * @return
	 */
	public boolean verifyFormat(WebDriver mydriver,String input, String label, String format, String message, Logger logger) {
		boolean flag = false;
		try {
			logger.info("Verifying error message if " + format + " format is wrong");
			for (int value = 0; value < labelName.size(); value++) {
				if (labelName.get(value).getText().contains(label)) {
					if (label.equals("Re-enter Email Address *")) {
						TextBox.get(value - 1).clear();
						TextBox.get(value - 1).sendKeys("mail@mail.com");
						TextBox.get(value).clear();
						TextBox.get(value).sendKeys(input);
					} else {
						TextBox.get(value).clear();
						TextBox.get(value).sendKeys(input);
					}
					if (button.get(1).isDisplayed()) {
						logger.info("Submitting form");
						button.get(1).click();
					}
					if (errorMessageList.get(value).isDisplayed()) {
						if (errorMessageList.get(value).getText().contains(message)) {
							getTest().info("Verify error message for Label name: "
									+ labelName.get(value).getText() + ":" + errorMessageList.get(value).getText());
							flag = true;
						}
					} else {
						getTest().info("Error message is not displayed");
						flag = false;
					}

					value = labelName.size();
				}
			}
		} catch (ElementNotVisibleException e) {
			flag = false;
			logger.info("Element/Error message is missing");
		}
		return flag;
	}

	/**
	 * @param labels - valid label Name
	 * @param date - current/previous/future
	 * @param logger
	 * @return
	 */
	public boolean verifyDatePickerFormat(WebDriver mydriver,String labels, String date, Logger logger) {
		boolean flag = false;
		int errorMessageDisplayed = 0;
		String[] label = labels.split(",");
		String[] dates = date.split(",");
		try {
			logger.info("Verifying date picker");
			for (int value = 0; value < labelName.size(); value++) {
				for (int label_arg = 0; label_arg < label.length; label_arg++) {
					if (labelName.get(value).getText().contains(label[label_arg])) {
						String aria_label = TextBox.get(value).getAttribute("aria-label").toString();
						String datePicker = "div input[aria-label^='" + aria_label + "']+div.datepicker-calendar-icon";
						WebElement datePickerElement = mydriver.findElement(By.cssSelector(datePicker));
						datePickerElement.click();
						if (dates[label_arg].equalsIgnoreCase("current")) {
							currentDate.click();
						} else if (dates[label_arg].equalsIgnoreCase("future")) {
							nextMonth.click();
							futureDate.click();
						} else if (dates[label_arg].equalsIgnoreCase("previous")) {
							previousMonth.click();
							futureDate.click();
						}
						button.get(1).click();
						if (errorMessageList.get(value).isDisplayed()) {
							errorMessageDisplayed++;
						}
					}
				}
			}
		} catch (ElementNotVisibleException e) {
			flag = false;
			logger.info("Element/Error message is missing");
		}
		if (errorMessageDisplayed > 0) {
			getTest().info("Error message should not be displayed");
			flag = false;
		} else {
			getTest().info("DatePicker is working as expected");
			flag = true;
		}
		return flag;
	}
}
