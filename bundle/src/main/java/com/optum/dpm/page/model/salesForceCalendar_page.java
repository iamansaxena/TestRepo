package com.optum.dpm.page.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;

import com.optum.dpm.utils.Base;

public class salesForceCalendar_page extends Base {
	private String author = "Aman Saxena";
	private String tag = "LoginBanner";
	private static final Logger logger = LogManager.getLogger(salesForceCalendar_page.class);

	@FindBy(css = "section#calendar-salesforce-container")
	protected static WebElement sfSection;

	@FindBy(css = "div.calendar-sidepanel")
	protected static WebElement calendarPanel;

	@FindBy(css = "div.calendar-sidepanel div.ui-datepicker-title span.ui-datepicker-month")
	protected static WebElement datePickerMonth;

	@FindBy(css = "div.calendar-sidepanel div.ui-datepicker-title span.ui-datepicker-year")
	protected static WebElement datePickerYear;

	@FindBy(css = "div.calendar-sidepanel a.ui-datepicker-prev")
	protected static WebElement previousMonth;

	@FindBy(css = "div.calendar-sidepanel a.ui-datepicker-next")
	protected static WebElement nextMonth;

	@FindBy(css = "table.ui-datepicker-calendar")
	protected static WebElement calendar;

	@FindBy(css = "div.calendar-sf-results")
	protected static WebElement eventSection;

	@FindBy(css = "div.calendar-sf-results ul li.calendar-sf-results__card")
	protected static List<WebElement> eventList;

	@FindBy(css = "ul.calendar-sf-results__cards")
	protected static WebElement events;

	@FindBy(css = "div.calendar-sidepanel")
	protected static WebElement calendarSidepanel;

	@FindBy(css = "input#location-search")
	protected static WebElement CS_enterCityBox;

	@FindBy(css = "div.calendar-sf-filter-tray__location-search i.calendar-sf-filter-tray__location-search-remove-icon")
	protected static WebElement CS_removeIcon;

	@FindBy(css = "div.calendar-sidepanel select#location-radius-select")
	protected static WebElement CS_withinDropdown;

	@FindBy(css = "button#location-apply-btn")
	protected static WebElement CS_filterBtn;

	@FindBy(css = "location-clear-btn")
	protected static WebElement CS_clearBtn;

	@FindBy(css = "div#event-radius")
	protected static WebElement filterByLocation;

	@FindBy(css = "div.calendar-sf-results__date")
	protected static List<WebElement> eventDateList;

	@FindBy(css = "div.calendar-sf-results__time")
	protected static List<WebElement> eventTimeList;

	@FindBy(css = "div.calendar-sf-results__address-link a")
	protected static List<WebElement> eventaddressList;

	@FindBy(css = "h3.calendar-sf-results__name")
	protected static List<WebElement> eventNameList;

	@FindBy(css = "calendar-sf-results__virtual")
	protected static List<WebElement> virtualEventList;

	@FindBy(css = "td[data-handler='selectDay']")
	protected static WebElement currentFutureDays;

	@FindBy(css = "select#search-views")
	protected static WebElement ViewOptions;

	@FindBy(css = "select#search-views option[selected]")
	protected static WebElement currentViewSelection;

	@FindBy(css = "input#in-person-event")
	protected static WebElement inPersonEventchkBox;

	@FindBy(css = "input#virtual-event")
	protected static WebElement virtualEventChkBox;

	@FindBy(css = "div#event-type")
	protected static WebElement eventType;

	@FindBy(css = "div#event-topic")
	protected static WebElement eventTopic;

	@FindBy(css = "h2#noResultMsg")
	protected static WebElement noResultMsg;

	@FindBy(css = "div#event-topic+ div input[type = 'checkbox']")
	protected static List<WebElement> eventTopicChkBox;

	@FindBy(css = "div#event-topic+ div input[type = 'checkbox']+label")
	protected static List<WebElement> eventTopicChkBoxLabel;

	public salesForceCalendar_page() {
		PageFactory.initElements(mydriver, this);
	}

	/**
	 * This metjod verify events in ascending order
	 * @return boolean
	 */
	public boolean verifyDateDisplayed() {

		String currentmonth = null, previousMonth = null, fullDate = null;
		int currentDate = 0, previousDate = 0;
		if (eventDateList.get(0).isDisplayed()) {

			for (WebElement element : eventDateList) {
				String date = element.getText();
				String month = date.substring(date.indexOf(",") + 1).trim();
				currentDate = Integer.valueOf(month.substring(month.lastIndexOf(".") + 1).trim());
				currentmonth = month.substring(0, 3);
				if (currentmonth.equalsIgnoreCase(previousMonth)) {
					if (!(currentDate >= previousDate)) {
						logger.info("Not in ascending order" + date);
						return false;
					} else {
						logger.info("In Ascending Order: Current Date" + date + " Previous Date " + fullDate);
						previousDate = currentDate;
						previousMonth = currentmonth;
						fullDate = date;
					}

				} else {
					logger.info("Next month started" + date);
					logger.info("In Ascending Order: Current Date" + date + " Previous Date " + fullDate);
					previousMonth = currentmonth;
					fullDate = date;
					previousDate = 0;
				}

			}
		} else {
			logger.info("Event list is not displayed");
		}

		return true;

	}

	/**
	 * This method used to select event view format from 'view' dropdown 
	 * @param days
	 * @param viewFormat
	 * @return boolean
	 */
	public boolean verifyViewDropDown(List days, String viewFormat) {
		boolean flag = true;
		Select value = new Select(ViewOptions);
		System.out.println(value.getOptions().get(0).getText());
		for (WebElement element : value.getOptions()) {
			if (element.getText().equalsIgnoreCase(viewFormat)) {
				if (element.isEnabled()) {
					value.selectByVisibleText(viewFormat);
					break;
				} else {
					throw new SkipException(viewFormat + " is not enabled in dropdown");
				}
			}
		}
		if (eventDateList.get(0).isDisplayed()) {
			for (WebElement element : eventDateList) {
				if (days.contains(element.getText())) {
					logger.info("Event displayed from " + viewFormat + ":" + element.getText());
				} else {
					logger.info("Event is not displayed from " + viewFormat + ":" + element.getText());
					flag = false;
				}
			}
		} else {
			logger.info("Event list is not displayed");
		}
		return flag;
	}

	/**
	 * This method used to verify 'Event type'
	 * @param event_type
	 * @return
	 */
	public boolean verifyEventType(String event_type) {
		boolean flag = true;
		if (eventType.isDisplayed()) {
			logger.info("Event type is displayed");
			eventType.click();
		}
		if (event_type.equalsIgnoreCase("In-Person Event")) {
			if ((inPersonEventchkBox.isDisplayed()) && !(inPersonEventchkBox.isSelected())) {
				inPersonEventchkBox.click();
			}
			if (events.isDisplayed()) {
				for (int i = 0; i < eventList.size(); i++) {
					if (eventDateList.get(i).isDisplayed() && eventaddressList.get(i).isDisplayed()
							&& eventTimeList.get(i).isDisplayed()) {
						logger.info("Date,Time,Location displayed for the in-person event "
								+ eventNameList.get(i).getText());
					} else {
						logger.info("Date,Time,Location is not displayed for the in-person event "
								+ eventNameList.get(i).getText());
						flag = false;
					}
				}
			} else {
				logger.info("Events are not displayed");
			}
		} else if (event_type.equalsIgnoreCase("Virtual Event")) {
			if ((virtualEventChkBox.isDisplayed()) && !(virtualEventChkBox.isSelected())) {
				virtualEventChkBox.click();
			}
			if (events.isDisplayed()) {
				for (int i = 0; i < eventList.size(); i++) {
					if (eventDateList.get(i).isDisplayed() && eventTimeList.get(i).isDisplayed()) {
						logger.info("Date,Time,virtual event displayed for the Virtual event "
								+ eventNameList.get(i).getText());
					} else {
						logger.info("Date,Time,virtual event is not displayed for the Virtual event "
								+ eventNameList.get(i).getText());
						flag = false;
					}
				}
			} else {
				logger.info("Events are not displayed");
			}

		}
		return flag;
	}

	/**
	 * This method verify day is disabled in calendar if there is no vent on that day
	 * @return true
	 */
	public boolean verifyDisabledCalendar() {
		boolean flag = false, eventExisted = false;
		List<String> date = new ArrayList<String>();
		HashMap<String, String> WeekDateAndDay = new HashMap<String, String>();

		Calendar calendar = Calendar.getInstance();
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			calendar.add(Calendar.DATE, -1);
		}
		SimpleDateFormat format1 = new SimpleDateFormat("EEEE, MMM. dd");
		SimpleDateFormat format2 = new SimpleDateFormat("dd");
		WeekDateAndDay.put(format1.format(calendar.getTime()), format2.format(calendar.getTime()));
		for (int i = 0; i < 6; i++) {
			calendar.add(Calendar.DATE, 1);
			// date.add(format1.format(calendar.getTime()));
			WeekDateAndDay.put(format1.format(calendar.getTime()), format2.format(calendar.getTime()));
		}
		System.out.println(WeekDateAndDay);
		if (eventDateList.get(0).isDisplayed()) {
			for (String Date : WeekDateAndDay.keySet()) {
				for (WebElement eventDay : eventDateList) {
					if (eventDay.getText().equalsIgnoreCase(WeekDateAndDay.get(Date))) {
						eventExisted = true;
						break;
					}
				}
				if (!eventExisted) {
					logger.info("Event not existed for " + WeekDateAndDay.get(Date));
					logger.info("Event not existed for " + WeekDateAndDay.get(Date));
					flag = disabledEvent(WeekDateAndDay.get(Date));
					break;
				}
			}

		} else {
			logger.info("Event list is not displayed");
		}
		return flag;
	}

	/**
	 * Verify previous day is disabled in calendar
	 * @param day
	 * @return
	 */
	public boolean disabledEvent(String day) {
		boolean flag = false;
		logger.info("Verifying disabled event in calendar " + day);
		List<WebElement> columns = calendar.findElements(By.tagName("td"));
		for (WebElement cell : columns) {
			if (cell.getText().equals(day)) {
				if (cell.getAttribute("class").contains("disabled")) {
					logger.info("Date is disabled as expected for the day " + day);
					logger.info("Date is disabled as expected for the day " + day);
					flag = true;
					break;
				} else {
					flag = false;
					logger.info("Date is enabled for the day" + day + " in calendar");
					logger.info("Date is enabled for the day" + day + " in calendar");

				}
			}
		}
		return flag;
	}

	/**
	 * This method return address of the first event from event list
	 * @param information
	 * @return
	 */
	public String fetchAddress(String information) {
		logger.info("Getting address from EventList");
		String zipOrCity = "";
		if (eventType.isDisplayed()) {
			logger.info("Event type is displayed");
			eventType.click();
			logger.info("Event type is clicked");
		}
		if (events.isDisplayed()) {
			for (int i = 0; i < eventaddressList.size(); i++) {
				if (eventaddressList.get(i).isDisplayed()) {
					String address = eventaddressList.get(i).getText();
					if (information.equalsIgnoreCase("zip")) {
						zipOrCity = address.substring(address.lastIndexOf(",") + 1).trim();
					} else if (information.equalsIgnoreCase("city")) {
						zipOrCity = address.substring(address.indexOf(",") + 1).trim();
						zipOrCity = zipOrCity.split(",")[0];
					}
					if (!zipOrCity.equalsIgnoreCase("")) {
						break;
					}
				}
			}
		} else {
			logger.info("Events are not displayed");
		}
		return zipOrCity;
	}

	/**
	 * This method enters zip,city inside filter by location text box and selecting miles from drop down
	 * @param pinorCity
	 * @param miles
	 * @return
	 */
	public boolean verifyEnterZiporCityName(String pinorCity, boolean miles) {
		boolean flag = true;
		try {
			if (filterByLocation.isDisplayed()) {
				logger.info("Filter by location is displayed");
				filterByLocation.click();
				logger.info("Filter by location is clicked");
			} else {
				flag = false;
				logger.info("Filter by location is not displayed");
				logger.info("Filter by location is not displayed");
			}
			if (miles) {
				Select value = new Select(CS_withinDropdown);
				try {
					value.selectByVisibleText("100 miles");
				} catch (Exception e) {
					flag = false;
					throw new SkipException("'10 miles' is not enabled in within dropdown");
				}
			}
			if (CS_enterCityBox.isDisplayed()) {
				CS_enterCityBox.clear();
				CS_enterCityBox.sendKeys(pinorCity);
				logger.info("Entering Zip or City in text box");
				logger.info("Entering Zip or City in text box");
			} else {
				flag = false;
				logger.info("Zip or City text box is missing");
				logger.info("Zip or City text box is missing");
			}

			if (CS_filterBtn.isDisplayed() && CS_filterBtn.isEnabled()) {
				logger.info("Clicking filer button");
				logger.info("Clicking filer button");
				JavascriptExecutor js = (JavascriptExecutor) mydriver;
				js.executeScript("arguments[0].click();", CS_filterBtn);
				logger.info("Filter button clicked");
				logger.info("Filter button clicked");
			} else {
				logger.info("Filter button is not enabled");
				logger.info("Filter button is not enabled");
				flag = false;
			}

		} catch (Exception E) {
			flag = false;
			logger.info("Entering Zip or City in Filter by location is not successful");
			logger.info("Entering Zip or City in Filter by location is not successful");
		}
		return flag;
	}

	/**
	 * This method verify event list displayed based on the values enter in Filter by Location
	 * @param pinOrCity
	 * @return
	 */
	public boolean verifyEventFilterByCityorPin(String pinOrCity) {
		boolean flag = true;
		try {
			if (eventaddressList.size() > 0) {
				if (eventaddressList.get(0).isDisplayed()) {
					for (int i = 0; i < eventaddressList.size(); i++) {
						if (eventaddressList.get(i).isDisplayed()) {
							if (eventaddressList.get(i).getText().contains(pinOrCity)) {
								logger.info(
										eventNameList.get(i).getText() + " contains " + pinOrCity + " as expected");
								logger.info(eventNameList.get(i).getText() + " contains " + pinOrCity + " as expected");
							}
						} else {
							logger.info(
									eventNameList.get(i).getText() + " not contains " + pinOrCity + " as expected");
							logger.info(eventNameList.get(i).getText() + " not contains " + pinOrCity + " as expected");
							flag = false;
						}
					}
				}
			} else if (noResultMsg.isDisplayed()) {
				logger.info("No events displayed for thus pin/city/miles");
				logger.info("No events displayed for thus pin/city/miles");
			}
		} catch (Exception e) {
			flag = false;
			logger.info("Event list is not displayed as expected");
			logger.info("Event list is not displayed as expected");
		}

		return flag;
	}

	/**
	 * This method verify cross icon in Filter by location text box
	 * @param pinorCity
	 * @return
	 */
	public boolean checkRemoveIcon(String pinorCity) {
		boolean flag = false;
		try {
			if (filterByLocation.isDisplayed()) {
				logger.info("Filter by location is displayed");
				filterByLocation.click();
				logger.info("Filter by location is clicked");
			}
			if (CS_enterCityBox.isDisplayed()) {
				CS_enterCityBox.clear();
				CS_enterCityBox.sendKeys(pinorCity);
				logger.info("Entering Zip or City in text box");
				logger.info("Entering Zip or City in text box");
				logger.info("String entered in text box: " + CS_enterCityBox.getText());
				logger.info("String entered in text box: " + CS_enterCityBox.getText());
			}
			if (CS_removeIcon.isDisplayed()) {
				logger.info("Clicking remove cross icon");
				logger.info("Clicking remove cross icon");
				JavascriptExecutor js = (JavascriptExecutor) mydriver;
				js.executeScript("arguments[0].click();", CS_removeIcon);
				logger.info("remove cross icon clicked");
				logger.info("remove cross icon clicked");
				if (CS_enterCityBox.getText().equalsIgnoreCase("")) {
					logger.info("After clicking remove icon entered string cleared");
					logger.info("After clicking remove icon entered string cleared");
					flag = true;
				} else {
					logger.info("After clicking remove icon entered string not cleared" + CS_enterCityBox.getText());
					logger.info("After clicking remove icon entered string not cleared" + CS_enterCityBox.getText());
					flag = false;
				}
			}
		} catch (Exception E) {
			flag = false;
			logger.info("Entering Zip or City and remove icon in Filter by location is not successful");
			logger.info("Entering Zip or City and remove icon in Filter by location is not successful");
		}
		return flag;
	}

	/**
	 * This method verify Event Topic
	 * @return
	 */
	public boolean eventTopicVerification() {
		boolean flag = false;
		int result = 0;
		String checkBoxText = "";
		List<String> existingEventList = new ArrayList<String>();
		List<String> currentList = new ArrayList<String>();
		existingEventList = fetchEventList();
		if (eventTopic.isDisplayed()) {
			logger.info("Clicking Event topic");
			logger.info("Clicking Event topic");
			eventTopic.click();
			logger.info("Event topic clicked");
			logger.info("Event topic clicked");
		}
		mydriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if (eventTopicChkBox.get(1).isDisplayed()) {
			if (!eventTopicChkBox.get(1).isSelected()) {
				eventTopicChkBox.get(1).click();
				checkBoxText = eventTopicChkBoxLabel.get(1).getText();
				logger.info(checkBoxText + " check box is clicked under event topic");
				logger.info(checkBoxText + " check box is clicked under event topic");
			}
			mydriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			currentList = fetchEventList();
			if (currentList.size() > 0 && existingEventList.size() > 0) {
				if (currentList.containsAll(existingEventList)) {
					logger.info("All events are displayed after clicking event topic which is unexpected");
					logger.info("All events are displayed after clicking event topic which is unexpected");
					result -= 1;
				} else if (currentList.get(0).contains(checkBoxText)) {
					logger.info(
							currentList.get(0) + " is displayed after clicking " + checkBoxText + " which is expected");
					logger.info(
							currentList.get(0) + " is displayed after clicking " + checkBoxText + " which is expected");
					result += 1;
				}
			} else if (existingEventList.size() > currentList.size()) {
				logger.info("There is no event for this " + checkBoxText + "check box");
			}
			logger.info("Unchecking " + checkBoxText + "and verify event list");
			if (eventTopicChkBox.get(1).isSelected()) {
				eventTopicChkBox.get(1).click();
				currentList = fetchEventList();
				if (currentList.containsAll(existingEventList)) {
					result += 1;
					logger.info("After unchecking " + checkBoxText + " event section displays all events");
					logger.info("After unchecking " + checkBoxText + " event section displays all events");
				} else {
					logger.info("After unchecking " + checkBoxText + " event section not displayed all events");
					logger.info("After unchecking " + checkBoxText + " event section not displayed all events");
					result -= 1;
				}
			}
		}
		System.out.println(result);
		if (result == 2) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * This method used to return event name displayed in event list page
	 * @return
	 */
	public List<String> fetchEventList() {
		List<String> eventList = new ArrayList<String>();
		for (WebElement element : eventNameList) {
			eventList.add(element.getText());
		}
		return eventList;
	}
}
