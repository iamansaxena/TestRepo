package com.optum.dpm.utils;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.aventstack.extentreports.model.service.util.ExceptionUtil;

import static com.optum.dpm.utils.DPMConfigurationsUtil.*;

import utils.FileUploader;

/**
 * @author amohan31
 *
 */
public class DPMTestUtils {

	private static Logger utilLogger = LogManager.getLogger(DPMTestUtils.class);
	
	/**
	 * This method is used to wait explicitly
	 * 
	 * @param durationInSeconds
	 *            time for which wscript need to wait
	 * @param logger
	 *            logger object of the requesting class
	 */
	@SuppressWarnings("static-access")
	public static void pleaseWait(Integer durationInSeconds, Logger logger) {
		utilLogger.info("Forced wait");
		try {
			Thread.currentThread().sleep(durationInSeconds * 500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is to focus and highlight the required WebElement using red
	 * outline
	 * 
	 * @param driver
	 *            driver object of the requesting script
	 * @param element
	 *            WebElement needs to be highlighted
	 */
	public static void focusElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver)
				.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", element);
	}

	/**
	 * This method is used to select an option using select class with the help of
	 * visible text of the option
	 * 
	 * @param logger
	 * @param element
	 *            WebElement [Drop down]
	 * @param optionTextToSelect
	 *            visible text of element to be selected
	 */
	public static void selectByOptionName(Logger logger, WebElement element, String optionTextToSelect) {
		new Select(element).selectByVisibleText(optionTextToSelect);
		utilLogger.info("Selected by name '" + optionTextToSelect + "' from dropdown  " + element.getText());
	}

	/**
	 * This method is used to select an option using select class with the help of
	 * value of the 'value' attribute of the option
	 * 
	 * @param logger
	 * @param element
	 *            WebElement [Drop down]
	 * @param optionTextToSelect
	 *            'value' attribute of the element to be selected
	 */
	public static void selectByValue(Logger logger, WebElement element, String optionTextToSelect) {
		new Select(element).selectByValue(optionTextToSelect);
		utilLogger.info("Selected by value '" + optionTextToSelect + "' from dropdown  " + element.getText());
	}

	/**
	 * This method is used to select an option using select class with the help of
	 * index no. of the option
	 * 
	 * @param element
	 *            element WebElement [Drop down]
	 * @param optionIndex
	 *            index of the webelement to be selected
	 * @param logger
	 * @return
	 */
	public static String selectByOptionIndex(WebElement element, Integer optionIndex, Logger logger) {
		new Select(element).selectByIndex(optionIndex);
		utilLogger.info("Selected by index: '"
				+ new Select(element).getOptions().get(optionIndex).getText()
				+ "' from dropdown  \n" + new Select(element).getFirstSelectedOption().getText());

		return element.getAttribute("innterText");
	}

	/**
	 * This method is used to check availability and visibility of a WebElement in a
	 * handled way
	 * 
	 * @param logger
	 * @param element
	 *            WebElement who's existence needs to be asserted
	 * @param elementName
	 *            Name for reference
	 * @return boolean
	 */
	public static boolean verifyElementExists(Logger logger, WebElement element, String elementName) {
		try {
			if (element.isDisplayed()) {
				utilLogger.info("Element '" + elementName + "' exists");
				return true;
			} else {
				utilLogger.error("Element '" + elementName + "' exists but not visible!");
				return false;
			}
		} catch (Exception e) {
			utilLogger.error("Element " + elementName + " doesn't exists!");
			return false;
		}
	}

	/**
	 * This method is used to compare the actual and expected visible text of a
	 * WebElement
	 * 
	 * @param element
	 *            WebElement who's text to be verified
	 * @param expectedText
	 *            WebElement's inner text
	 * @return boolean
	 */
	public boolean verifyElementText(WebElement element, String expectedText) {
		try {
			String actualText = element.getText();
			if (actualText.trim().equalsIgnoreCase(actualText))
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used to compare the actual and expected attribute value of a
	 * WebElement
	 * 
	 * @param element
	 *            WebElement who's attribute to be verified
	 * @param attributeName
	 *            Attribute who's value to be asserted
	 * @param expectedText
	 * @return
	 */
	public  boolean verifyAttributeValue(WebElement element, String attributeName, String expectedText) {
		boolean status = false;
		try {
			utilLogger.info("Verifying element attribute " + attributeName);
			if (element.getAttribute(attributeName) != null) {
				if (element.getAttribute(attributeName).trim().equalsIgnoreCase(expectedText)) {
					status = true;

					utilLogger.info("Expected and Actual attribute values are similar");
				} else {
					utilLogger.error("Expected and Actual values are '" + element.getAttribute(attributeName) + "' and '"
							+ expectedText + "' respectively");
					status = false;
				}
			}

			else {

				utilLogger.error("Css Attribute '" + attributeName + "' does not exists");
				status = false;
			}
		} catch (Exception e) {
			status = false;
			utilLogger.fatal("Some thing went wrong while reading attribute: " + attributeName);
			e.printStackTrace();
		}
		return status;
	}

	/**
	 * This method is used to compare the actual and expected CSS attribute value of
	 * a WebElement
	 * 
	 * @param element
	 *            WebElement who's attribute to be verified
	 * @param cssAttributeName
	 *            Attribute who's value to be asserted
	 * @param expectedValue
	 *            expected attribute value
	 * @return boolean
	 */
	public static boolean verifyCSSValue(WebElement element, String cssAttributeName, String expectedValue) {
		boolean status = false;
		try {

			utilLogger.info("Verifying element's css attribute " + cssAttributeName);
			if (element.getCssValue(cssAttributeName) != null) {

				if (element.getCssValue(cssAttributeName).trim().contains(expectedValue)) {
					status = true;
					utilLogger.info("Expected and Actual css values are similar ");
				} else {
					utilLogger.error("Expected and Actual values are '" + element.getAttribute(cssAttributeName) + "' and '"
							+ expectedValue + "' respectively");
					status = false;
				}
			} else {
				utilLogger.error("Css Attribute '" + cssAttributeName + "' does not exists");
				status = false;
			}
		} catch (Exception e) {
			status = false;
			utilLogger.fatal("Some thing went wrong while reading attribute: " + cssAttributeName);
		}
		return status;
	}

	/**
	 * This method is used to hover the mouse cursor over the required WebElement
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param element
	 *            WebElent on which mouse t be hovered
	 * @return WebElement
	 */
	public static WebElement moveMouseOnToElement(WebDriver mydriver, WebElement element) {
		try {
			new Actions(mydriver).moveToElement(element).perform();
			focusElement(mydriver, element);// build().perform();
		} catch (Exception e) {

			utilLogger.fatal("Unable to hover mouse cursor over: " + element);
		}
		return element;
	}

	/**
	 * This method is used to scroll the page by pre-defined coordinates in pixels
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param i
	 *            X - Coordinate
	 * @param j
	 *            Y - Coordinate
	 */
	public static void scrollPageByDimensions(WebDriver mydriver, int pixelX, int pixelY) {
		JavascriptExecutor js = (JavascriptExecutor) mydriver;
		js.executeScript("window.scrollBy(" + pixelX + "," + pixelY + ")");
	}

	/**
	 * This method is used to scroll the page to a particular WebElement and wait
	 * till its visibility
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param element
	 *            WebElement to which page needs to be scrolled
	 * @param logger
	 * @return WebElement
	 */
	public static WebElement scrollToElement(WebDriver mydriver, WebElement element, Logger logger) {
		System.out.println(" ======> " + mydriver);
		((JavascriptExecutor) mydriver).executeScript("arguments[0].scrollIntoView()",
				element);

		getVisibility(mydriver, element, 10);

		pleaseWait(4,logger);
		focusElement(mydriver, element);
		return element;
	}

	/**
	 * This method is used to scroll the page to a particular WebElement without
	 * waiting for its visibility Under this method page will be scrolled without
	 * waiting for visibility
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param element
	 *            WebElement to which page needs to be scrolled
	 */
	public static void scrollToElementWithoutWait(WebDriver mydriver, WebElement element) {
		/*((JavascriptExecutor) mydriver).executeScript("arguments[0].scrollIntoView(true);",
			mydriver);
		focusElement(mydriver, element);*/
		scrollToElement(mydriver, element, utilLogger);
	}

	/**
	 * This method is used to wait for the visibility of a particular WebElement
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param e
	 *            WebElement needs to be visible
	 * @param timeout
	 *            Time interval for which script needs to wait
	 * @return WebElement
	 */
	public static WebElement getVisibility(WebDriver mydriver, WebElement e, Integer timeout) {
		return new WebDriverWait(mydriver, 30).until(ExpectedConditions.visibilityOf(e));
	}

	/**
	 * This method is used to wait till the availability or presence in the DOM of a
	 * WebElement
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param xpath
	 *            Xpath of the webelement
	 * @param timeout
	 *            Time interval for which script needs to wait
	 * @return WEbElement
	 */
	public static WebElement getPresence(WebDriver mydriver, String xpath, Integer timeout) {
		String xpathTemp = xpath;
		if (xpath.contains("']]") || xpath.contains("\"]]")) {
			xpathTemp = xpath.substring(0, xpath.length() - 1);
		} else if (xpath.contains("]]") || !xpath.substring(xpath.length() - 2, xpath.length()).contains("']")
				|| !xpath.substring(xpath.length() - 2, xpath.length()).contains("\"]")
				|| !!xpath.substring(xpath.length() - 2, xpath.length()).contains(")")) {
			xpathTemp = xpath.substring(0, xpath.length() - 1);
		}
		return new WebDriverWait(mydriver, timeout)
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathTemp)));
	}

	/**
	 * This method is to get the font size of the visible text
	 * 
	 * @param element
	 *            WebElement who's font size needs to be fetched
	 * @return Font Size
	 */
	public static String getFontSize(WebElement element) {
		String fontSize = element.getCssValue("font-size");
		return fontSize;
	}

	/**
	 * This method is to get the font color of the visible text
	 * 
	 * @param element
	 *            WebElement who's font size needs to be fetched
	 * @return String Hex color code
	 */
	public static String getHexFontColor(WebElement element) {
		String rgbaValue = element.getCssValue("background-color");
		String[] hexValue = rgbaValue.replace("rgba(", "").replace(")", "").split(",");

		hexValue[0] = hexValue[0].trim();
		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);

		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		return actualColor.toUpperCase();
	}


	/**
	 * This method is to find first 5 characters of the domain of any given URL
	 * 
	 * @param url
	 *            Hyperlink
	 * @return First 5 character of the domain
	 */
	public static String getDomainNameSubset(String url) {
		try {
			return new URI(url).getHost().substring(0, 5);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return "Invalid Domain";
		}
	}

	/**
	 * This method is to find the full domain of any given URL
	 * 
	 * @param url
	 *            Hyperlink
	 * @return Domain of the given hyperlink
	 */
	public static String getDomainName(String url) {
		URI uri = null;
		try {
			uri = new URI(url);
			return uri.getHost();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return "Invalid Domain for => " + uri;
		}
	}

	/**
	 * This method is required to be called on the very first line of each TC. It'll
	 * skip the TC if there's no URL available to run the TC
	 * 
	 * @param urls
	 *            Url data from the script
	 * @return Throws SkipException if the given Url data is empty
	 */
	public static HashMap<String, Boolean> skipNonExistingComponent(ArrayList<String> urls) {
		final HashMap<String, Boolean> map =  new HashMap<>();
		if (urls.size() == 0 || urls.toString().trim().isEmpty()) {
			throw new SkipException("Can't find Component url");
		} else {
			urls.stream().forEach(a -> {
				map.put(a, false);
			});

		}
		return map;
	}

	public static HashMap<String, Boolean> skipNonExistingComponent(String urls) {
		List<String> urlList = new ArrayList<>();
		urlList.add(urls);
		HashMap<String, Boolean> map = new HashMap<>();
		
		if (urlList.size() == 0 || urlList.toString().trim().isEmpty()) {
			throw new SkipException("Can't find Component url");
		} else {
			urlList.stream().forEach(a -> {
				map.put(a, false);
			});

		}
		return map;
	}

	/**
	 * This method is used to fetch random value within the given range where
	 * 'maximum' will be exclusive and 'minimum' will be inclusive
	 * 
	 * @param maximum
	 *            end of range (exclusive)
	 * @param minimum
	 *            start of range (inclusive)
	 * @return random integer
	 */
	public static int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}

	/**
	 * This method is used to fetch random value within the given range where
	 * 'maximum' will be exclusive and 'minimum' will be inclusive
	 * 
	 * @param start
	 *            start of range (inclusive)
	 * @param end
	 *            end of range (exclusive)
	 * @param excludes
	 *            numbers to exclude (= numbers you do not want)
	 * @return the random number within start-end but not one of excludes
	 */
	public static int getRandomIntegerWithExclusion(int start, int end, int... excludes) {
		int rangeLength = end - start - excludes.length;
		int randomInt = new Random().nextInt(rangeLength) + start;

		for (int i = 0; i < excludes.length; i++) {
			if (excludes[i] > randomInt) {
				return randomInt;
			}

			randomInt++;
		}

		return randomInt;
	}

	/**
	 * This method is used to check if the the required file is downloaded or not
	 * 
	 * @param filename
	 *            Name of the file downloaded
	 * @param mydriver
	 *            Driver object of requesting class
	 * @return boolean
	 */
	@Deprecated
	public static boolean isDownloaded(String filename, WebDriver mydriver) {
		System.setProperty("java.io.tmpdir", "./Dowloads");
		String tmpFolderPath = System.getProperty("java.io.tmpdir");
		String expectedFileName = filename;
		boolean status;
		File file = new File(tmpFolderPath + expectedFileName);
		if (file.exists()) {
			status = true;
			file.delete();
			WebDriverWait wait = new WebDriverWait(mydriver, 30);
			wait.until((ExpectedCondition<Boolean>) webDriver -> file.exists());
		} else {
			status = false;

		}
		return status;
	}

	/**
	 * This method is used to log into the AEM only to use when using unpublished
	 * urls for test purpose
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param userName
	 *            AEM login username
	 * @param password
	 *            AEM login password
	 * @param logger
	 */
	public static void loginTestEnv(WebDriver mydriver, String userName, String password, Logger logger) {
		try {
			scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@aria-label=\"User name\"]")), logger);
			mydriver.findElement(By.xpath("//*[@aria-label=\"User name\"]")).sendKeys(userName);
			mydriver.findElement(By.xpath("//*[@aria-label=\"Password\"]")).sendKeys(password);
			mydriver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			mydriver.navigate().refresh();

		}
		utilLogger.info("User Is logged into Test environment");
	}

	/**
	 * This method is used to capitalize every character before ' '
	 * 
	 * @param givenString
	 *            Capitalize every character before ' '
	 * @return
	 */
	public static String capitalizeWhiteString(String givenString) {

		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * This method is used to capitalize every character before '-' and for
	 * replacing '-' with ' '
	 * 
	 * @param givenString
	 *            Capitalize the string in camel case and replacing '-' with ' '
	 * @return
	 */
	public static String capitalizeHyphenString(String givenString) {

		String[] arr = givenString.split("-");
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0))).append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * This method is used to Switch the focus of the WebDriver to next tab
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param logger
	 * @param currentWindowHandle
	 *            Window handle of the active browser tab
	 * @return
	 */
	public static  WebDriver switchToNextTab(WebDriver mydriver, Logger logger,String currentWindowHandle) {
		Set<String> tabSet = mydriver.getWindowHandles();
		Iterator<String> it = tabSet.iterator();

		while (it.hasNext()) {
			String a = it.next();
			if (!a.equals(currentWindowHandle)) {
				utilLogger.info("Current tab title => '" + mydriver.getTitle() + "'   " + mydriver.getWindowHandle());
				mydriver.switchTo().window(a);
				utilLogger.info("Switched to prev tab with title => '" + mydriver.getTitle() + "'");
				break;
			}
		}
		return mydriver;
	}

	public static WebDriver switchToTab(WebDriver mydriver, Logger logger, String currentWindowHandle) {
		Set<String> tabSet = mydriver.getWindowHandles();
		Iterator<String> it = tabSet.iterator();

		while (it.hasNext()) {
			String a = it.next();
			if (!a.equals(currentWindowHandle)) {
				utilLogger.info("Current tab title => '" + mydriver.getTitle() + "'   " + mydriver.getWindowHandle());
				mydriver.switchTo().window(a).close();

			}
		}
		mydriver.switchTo().window(currentWindowHandle);
		return mydriver;
	}

	/**
	 * This method is used to fetch only the last node of the URL without '.html'
	 * using the current url of the provided browser session
	 * 
	 * @param mydriver
	 *            Driver object of the requesting class
	 * @return String
	 */
	public static String getLastNodeOfActualLinkWithoutHtml(WebDriver mydriver) {
		String lastNode = mydriver.getCurrentUrl().split("/")[mydriver.getCurrentUrl().split("/").length - 1]
				.split(".html")[0];
		return lastNode;
	}

	/**
	 * This method is used to fetch only the last node of the provided URL with
	 * '.html'
	 * 
	 * @param expURL
	 *            Driver object of the requesting class
	 * @return
	 */
	public static String getLastNodeOfExpectedLinkWithoutHtml(String expURL) {
		String lastNode = expURL.split(".//")[1].split("/")[expURL.split(".//")[1].split("/").length - 1]
				.split(".html")[0];
		return lastNode;
	}

	/**
	 * This is to verify if the given is not a pdf link and the domain of both the
	 * expected and actual link is same
	 * 
	 * @param expUrl
	 *            Hyperlink fetched from the WebElement
	 * @param expectedDomain
	 *            Domain of the Test URL
	 * @param actualUrl
	 *            Current Url of
	 * @return
	 */
	public static boolean isDomainSame(String expectedDomain, String expUrl) {
		if (!expUrl.contains("pdf") && getDomainName(expUrl).equalsIgnoreCase(expectedDomain)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to assert hyperlink redirecting functionality
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param logger
	 * @param testUrlDomain
	 *            Main test component url's domain
	 * @param expHyperLink
	 * @param currentWindowHandle
	 * @return
	 */
	public static WebDriver assertRedirection(WebDriver mydriver, Logger logger, String testUrlDomain, String expHyperLink, String currentWindowHandle) {
		try {
			if (isDomainSame(testUrlDomain, expHyperLink)) {
				pleaseWait(3, logger);
				logger
						.info("Expected Url path: " + getLastNodeOfExpectedLinkWithoutHtml(expHyperLink)
								+ "\nActual Url path: "
								+ getLastNodeOfActualLinkWithoutHtml(mydriver));

				assertEquals(mydriver.getWindowHandles().size(), 1);
				// hardAssert.assertNotEquals(BrokenLinks.isBroken(expUrlPool.get()), 404);
				// //temporarily disabled
				utilLogger.info("Assertion passes for same tab!");
				switchToTab(mydriver, logger, currentWindowHandle);
			} else if (testUrlDomain.contains(".pdf")
					|| !testUrlDomain.equalsIgnoreCase(getDomainName(expHyperLink))) {

				pleaseWait(3, logger);
				utilLogger.info("Expected Url path: " + expHyperLink + "\nActual Url path: "
						+ mydriver.getCurrentUrl());

				assertEquals(mydriver.getWindowHandles().size(), 2);
				utilLogger.info("Assertion passes for different tab!");
				switchToTab(mydriver, logger,
						currentWindowHandle);

			}
		} catch (Exception e) {
			utilLogger.error(ExceptionUtil.getStackTrace(e));
			switchToPreviousTab(mydriver, logger, currentWindowHandle);
		}
		return mydriver;
	}

	/**
	 * This method is used to assert hyperlink redirecting functionality where
	 * expected key in the url is known
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param logger
	 * @param testUrlDomain
	 *            Main test component url's domain
	 * @param expHyperLink
	 * @param currentWindowHandle
	 */
	public static void assertStaticRedirection(WebDriver mydriver, Logger logger, String testUrlDomain, String expHyperLink,
			String expRelativePath, String currentWindowHandle) {
		String expURL;
		try {
			expURL = Jsoup.connect(expHyperLink).followRedirects(true).ignoreContentType(true)
					.maxBodySize(48000000).ignoreHttpErrors(true).execute().url().toString();
		} catch (IOException e) {
			expURL = null;
			utilLogger.fatal(ExceptionUtil.getStackTrace(e));
		}
		
		if (testUrlDomain.equalsIgnoreCase(getDomainName(expURL))) {
			utilLogger.info("Expected Url path: " + expURL.split(".html")[0]
					+ "\nActual Url path: " + mydriver.getCurrentUrl().split(".html")[0]);
			assertTrue(
					mydriver.getCurrentUrl().split(".html")[0].contains(expRelativePath));
			utilLogger.info("Assertion passes for same tab!");
		} else {
			switchToNextTab(mydriver, logger,
					currentWindowHandle);
			pleaseWait(3, logger);
			utilLogger.info("Expected Url path: " + expURL.split(".html")[0]
					+ "\nActual Url path: " + mydriver.getCurrentUrl().split(".html")[0]);
			assertTrue(
					mydriver.getCurrentUrl().split(".html")[0].contains(expRelativePath));
			utilLogger.info("Assertion passes for deifferent tab!");
			mydriver.close();
			mydriver.switchTo().window(currentWindowHandle);
			System.out.println(mydriver.getTitle());
		}
	}

	/**
	 * Here the script will wait for the page to completely load
	 * 
	 * @param timeOutInSeconds
	 *            Time interval for which the script needs to wait
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param logger
	 */
	public static void jsWaitForPageToLoad(Integer timeOutInSeconds, WebDriver mydriver, Logger logger) {
		String jsCommand = "return document.readyState";

		if (((JavascriptExecutor) mydriver).executeScript(jsCommand).toString().equals("complete")) {
			return;
		}

		for (int i = 0; i < timeOutInSeconds; i++) {
			pleaseWait(2, logger);
			if (((JavascriptExecutor) mydriver).executeScript(jsCommand).toString()
					.equals("complete")) {
				System.out.println("LOAD COMPLETE");
				break;
			}
		}
	}

	/**
	 * This method is used to Switch the focus of the WebDriver to previous tab
	 * 
	 * 
	 * @param mydriver
	 *            Driver object of requesting class
	 * @param logger
	 * 
	 */
	public static void switchToPreviousTab(WebDriver mydriver, Logger logger, String currentWindowHandle) {
		Set<String> tabSet = mydriver.getWindowHandles();
		Iterator<String> it = tabSet.iterator();
		ArrayList<String> tabList = new ArrayList<>();

		while (it.hasNext()) {
			String a = it.next();
			tabList.add(a);
		}
		Collections.reverse(tabList);
		Iterator<String> reverseIterator = tabList.iterator();
		while (it.hasNext()) {
			String a = reverseIterator.next();
			if (!a.equals(currentWindowHandle)) {
				utilLogger.info("Current tab title => '" + mydriver.getTitle() + "'");
				mydriver.switchTo().window(a);
				utilLogger.info("Switch to next tab with title => '" + mydriver.getTitle() + "'");
				break;
			}
		}
	}

	/**
	 * Here the element will be clicked using JavaScriptExecutor
	 * 
	 * @param element
	 *            WebELement to be clicked
	 * @param mydriver
	 *            Driver object of the requesting script
	 */
	public static void clickWithJS(WebElement element, WebDriver mydriver) {
		getVisibility(mydriver, element, 30);
		new WebDriverWait(mydriver, 30).until(ExpectedConditions.elementToBeClickable(element));
		((JavascriptExecutor) mydriver).executeScript("arguments[0].click()", element);
	}

	/**
	 * Here the element will be hovered by mouse cursor using JavaScriptExecutor
	 * 
	 * @param element
	 *            WebELement to be clicked
	 * @param mydriver
	 *            Driver object of the requesting script
	 */
	public static void mouseWithJS(WebElement element, WebDriver mydriver) {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		((JavascriptExecutor) mydriver).executeScript(mouseOverScript, element);
	}


	/**
	 * Here Action Class's object will be created for specific script
	 * 
	 * @param mydriver
	 * @return Action Class's object
	 */
	public static Actions getActions(WebDriver mydriver) {
		return new Actions(mydriver);
	}

	/**
	 * This method is used to remove the static header which appears on scrolling
	 * the page and restricts the view of the elements
	 * 
	 * @param mydriver
	 *            Driver object of the requesting class
	 * @param medexComponent
	 *            Medex Component Section locator
	 * @param logger
	 */
	public static void scrolltillvisibilityMedex(WebDriver mydriver, WebElement medexComponent, Logger logger) {
		scrollToElementWithoutWait(mydriver, medexComponent);
		pleaseWait(2, logger);
		getWebDriverWait(mydriver, 50).until(ExpectedConditions.visibilityOf(medexComponent));

		try {
			((JavascriptExecutor) mydriver)
					.executeScript("return document.getElementsByClassName('header med-header sticky')[0].remove();");
		} catch (WebDriverException e) {
		}

		scrollToElementWithoutWait(mydriver, medexComponent);
		pleaseWait(2, logger);
		getWebDriverWait(mydriver, 50).until(ExpectedConditions.visibilityOf(medexComponent));
	}

	/**
	 * This method is used to get a WebDriverWait object in a Threadsafe way
	 * 
	 * @param mydriver
	 *            Driver object of the requesting class
	 * @param timeout
	 *            Max time interval for which the script need to wait
	 * @return
	 */
	public static WebDriverWait getWebDriverWait(WebDriver mydriver, int timeout) {
		return new WebDriverWait(mydriver, timeout);
	}

	/**
	 * This method is used to re use try-catch block and wait for element
	 * availability. Also it can be declared if you want to throw skip exception or
	 * not when element is not available.
	 * 
	 * @param element
	 *            Element to be found
	 * @param errorMessage
	 *            Error to be displayed if element not found
	 * @param logger
	 * @return
	 */
	public static boolean isElementExists(WebElement element, String errorMessage, Logger logger, Boolean throwErrorOrNot) {
		try {
			element.isDisplayed();
			return true;
		} catch (Exception e) {
			if (throwErrorOrNot == true) {
				throw new SkipException(errorMessage);
			}
			return false;
		}
	}
	
	/**
	 * This method is used to fetch the URLs out of the test data using the given
	 * key
	 * 
	 * @param componentName
	 *            Value from either CmponentList.properties or XpathList.properties
	 *            will be parsed by the script
	 * @param depth
	 *            how many URLs to be retrieve
	 * @return An array of Script Test URLs
	 */

	public  static String fetchUrl(String componentName, int depth) {
		if (System.getProperty("defaultExecution") == null
				|| System.getProperty("defaultExecution").equalsIgnoreCase("false")) {
			int count = 0;
			String COMPONENT = null;
			Iterator<String> it = componentData.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if (key.toLowerCase().contains(componentName.toLowerCase())) {
					COMPONENT = key;
					break;
				}
			}
			if (COMPONENT == null || !(componentData.get(COMPONENT).contains("http"))) {
				// return "ERROR WHILE FETCHING URL FOR "+componentName;
				return null;
			} else {
				if (componentData.containsKey(COMPONENT)) {
					String a = componentData.get(COMPONENT).replace("[", "").replace("]", "").replace("\"", "");
					String[] urlSet = a.split(",");
					String randomURLs = null;
					String temp = "";
					int p = 0;
					while (count != depth && p < urlSet.length && urlSet.length != 0) {
						temp = temp + urlSet[p] + ",";
						count++;
						p++;

					}

					if (!(temp.isEmpty())) {
						randomURLs = temp.substring(0, (temp.length() - 1));
						return randomURLs;
					} else {
						return null;
					}

				} else {
					return null;
				}
			}
		} else {
			return null;
		}
	}
}