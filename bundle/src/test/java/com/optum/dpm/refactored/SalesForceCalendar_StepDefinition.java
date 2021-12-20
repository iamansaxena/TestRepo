package com.optum.dpm.refactored;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.optum.dpm.page.model.salesForceCalendar_page;
import static com.optum.dpm.utils.DPMTestUtils.*;

import core.CustomDataProvider;

public class SalesForceCalendar_StepDefinition extends salesForceCalendar_page {
	private static final Logger logger = LogManager.getLogger(SalesForceCalendar_StepDefinition.class);

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		softAssert.assertTrue(sfSection.isDisplayed());
		logger.info("Verifying the Salesforce section present or not : " + sfSection.isDisplayed());

		softAssert.assertTrue(calendarPanel.isDisplayed());
		logger.info("Verifying the Calendar panel present or not : " + calendarPanel.isDisplayed());
		softAssert.assertTrue(datePickerMonth.isDisplayed());
		logger.info("Verifying the month present or not : " + datePickerMonth.isDisplayed());
		softAssert.assertTrue(datePickerYear.isDisplayed());
		logger.info("Verifying the year present or not : " + datePickerYear.isDisplayed());
		softAssert.assertTrue(previousMonth.isDisplayed());
		logger.info("Verifying the previous month icon present or not : " + previousMonth.isDisplayed());
		softAssert.assertTrue(nextMonth.isDisplayed());
		logger.info("Verifying the next month icon present or not : " + nextMonth.isDisplayed());
		softAssert.assertTrue(eventSection.isDisplayed());
		logger.info("Verifying the event section present or not : " + eventSection.isDisplayed());
		softAssert.assertTrue(calendarSidepanel.isDisplayed());
		logger.info("Verifying the calendar sidepanel present or not : " + calendarSidepanel.isDisplayed());
		/*
		 * for(WebElement element : calendarSidepanelList) {
		 * softAssert.assertTrue(element.isDisplayed());
		 * logger.info("Verifying the sidepanel dropdown present or not : " +
		 * element.isDisplayed()); }
		 */
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyEventsInAscendingOrder(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		hardAssert.assertEquals(verifyDateDisplayed(), true);

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyViewTab(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		hardAssert.assertEquals(currentViewSelection.getText().equalsIgnoreCase("All events"), true);
		logger.info("Verifying default selection in view tab is 'All Events' : " + currentViewSelection.getText());
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyThisWeekTab(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		List<String> date = new ArrayList<String>();

		Calendar calendar = Calendar.getInstance();
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			calendar.add(Calendar.DATE, -1);
		}
		SimpleDateFormat format1 = new SimpleDateFormat("EEEE, MMM. dd");
		date.add(format1.format(calendar.getTime()));
		for (int i = 0; i < 6; i++) {
			calendar.add(Calendar.DATE, 1);
			date.add(format1.format(calendar.getTime()));
		}
		hardAssert.assertEquals(verifyViewDropDown(date, "This week"), true);
		logger.info("after selecting \"This week\" in view tab, current week events is displayed"
				+ currentViewSelection.getText());
	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyThisMonthTab(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		List<String> date = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat format1 = new SimpleDateFormat("EEEE, MMM. dd");
		date.add(format1.format(calendar.getTime()));
		for (int i = 0; i < 30; i++) {
			calendar.add(Calendar.DATE, 1);
			date.add(format1.format(calendar.getTime()));
		}
		hardAssert.assertEquals(verifyViewDropDown(date, "This month"), true);
	}

	@Test(priority = 6, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyTodayTab(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		List<String> date = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("EEEE, MMM. dd");
		date.add(format1.format(calendar.getTime()));
		hardAssert.assertEquals(verifyViewDropDown(date, "Today"), true);
	}

	@Test(priority = 7, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyInPersonEvent(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		hardAssert.assertEquals(verifyEventType("In-Person Event"), true);
	}

	@Test(priority = 8, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyVirtualEvent(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		hardAssert.assertEquals(verifyEventType("Virtual Event"), true);
	}

	@Test(priority = 9, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifypreviousDay(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		List<WebElement> columns = calendar.findElements(By.tagName("td"));
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("dd");
		String previousDate = String.valueOf((Integer.valueOf(format1.format(calendar.getTime())) - 2));
		System.out.println(previousDate);
		for (WebElement cell : columns) {
			if (cell.getText().equals(previousDate)) {
				if (cell.getAttribute("class").contains("disabled")) {
					logger.info("Previous date is disabled as expected");
					break;
				}
			}
		}

	}

	@Test(priority = 10, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyPreviousIcon(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		if (previousMonth.getAttribute("class").contains("disabled")) {
			logger.info("Previous Month is disabled as expected");
		} else {
			logger.info("Previous Month is enabled");
		}
	}

	@Test(priority = 11, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifynextIcon(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		if (nextMonth.isDisplayed()) {
			if (!nextMonth.getAttribute("class").contains("disabled")) {
				nextMonth.click();
				if (!previousMonth.getAttribute("class").contains("disabled")) {
					logger.info("Next month icon is enabled");
				}
			} else {
				logger.info("Next month is disabled");
			}
		}
	}

	@Test(priority = 12, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyCalendarDisabledIfNoEvent(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		hardAssert.assertEquals(verifyDisabledCalendar(), true,
				"Verifying that calendar date is disabled, if there is no event for that particular date");
	}

	@Test(priority = 13, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyfilterByLocationusingZip(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		String zip = fetchAddress("zip");
		hardAssert.assertEquals(verifyEnterZiporCityName(zip, false), true);
		hardAssert.assertEquals(verifyEventFilterByCityorPin(zip), true);
	}

	@Test(priority = 14, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyfilterByLocationusingCityName(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		String city = fetchAddress("city");
		hardAssert.assertEquals(verifyEnterZiporCityName(city, false), true);
		hardAssert.assertEquals(verifyEventFilterByCityorPin(city), true);
	}

	@Test(priority = 15, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyremoveIconinTextbox(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		hardAssert.assertEquals(checkRemoveIcon("Verify Icon"), true);
	}

	@Test(priority = 16, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyfilterByLocationusingMiles(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		String zip = fetchAddress("zip");
		hardAssert.assertEquals(verifyEnterZiporCityName(zip, true), true);
		hardAssert.assertEquals(verifyEventFilterByCityorPin(zip), true);
	}

	@Test(priority = 17, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyEventTopic(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		hardAssert.assertEquals(eventTopicVerification(), true);
	}

	@Test(priority = 18, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifynoEventMessage(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		hardAssert.assertEquals(verifyEnterZiporCityName("no event", false), true);
		hardAssert.assertEquals(verifyEventFilterByCityorPin(""), true);
	}

	@Test(priority = 19, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"calendar-salesforce" })
	public void verifyViewTabAfterFBL(String url) {
		skipNonExistingComponent(url);
		mydriver.get(url);
		String currentEvent = currentViewSelection.getText();
		if (currentEvent.equalsIgnoreCase("All events")) {
			logger.info("Event selected : " + currentEvent);
			String zip = fetchAddress("zip");
			hardAssert.assertEquals(verifyEnterZiporCityName(zip, false), true);
			hardAssert.assertEquals(currentEvent.equalsIgnoreCase(currentViewSelection.getText()), true);
		}
	}
}
