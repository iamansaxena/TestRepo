package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.IconPickerNew_page;
import compontentPages.IconStrip_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class IconPickerNew_StepDefinition extends IconPickerNew_page  {
	private String author = "Prateek Srivastava";
	private static String currentDomain = "=>";
	private static ArrayList<String> cardUrls = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(IconPickerNew_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(IconPickerNew_StepDefinition.class.getName());
		new IconPickerNew_page();

		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("icon-picker-v2") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				cardUrls.add("http://apsrs5642:8080/content/AutomationDirectory/IconPickerNew.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				cardUrls.add("http://apvrt31468:4503/content/AutomationDirectory/IconPickerNew.html");
			}

		} else {
			String[] scannedUrls = fetchUrl("icon-picker-v2").split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
		}

		ExtentTestManager.startTest(IconPickerNew_StepDefinition.class.getName());
		for (String url : cardUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("IconPickerNew", author, currentDomain,
				IconPickerNew_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(IconPickerNew_StepDefinition.class);
		logger.info("Urls for '" + IconPickerNew_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(IconPickerNew_StepDefinition.class.getName(), currentDomain);

		driverMap.put(IconPickerNew_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + IconPickerNew_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
		// mydriver.manage().deleteAllCookies();
	}

	/*@Test(priority = 2, enabled = true)
	public void blankHeaderCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			mydriver.get(cardUrl);
			String expURL = mydriver.findElement(By.xpath(iconField)).getAttribute("href");
		assertRedirection(mydriver, logger, getDomainName(cardUrl), expURL);
		}
	}*/
	
	@Test(priority = 1, enabled = true)
	public void iconPickerVisibilityCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			
			List<WebElement> iconPicker = mydriver.findElements(By.xpath(IconPickerNew_page.iconField));
			
			scrollToElement(mydriver, iconPicker.get(0), logger);
			
			//WebElement iconPicker = mydriver.findElements(By.xpath(IconPickerNew_page.iconField));
			hardAssert.assertTrue(verifyElementExists(logger, iconPicker.get(0),"Icon Picker Icon"));
			
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void linkTextCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			urlUnderTest.get().add(cardUrl);
			mydriver.get(cardUrl);
			
			List<WebElement> iconPickerLink = mydriver.findElements(By.xpath(IconPickerNew_page.iconWithLink));
			int i=getRandomInteger(iconPickerLink.size(), 0);
			try {
				scrollToElement(mydriver, iconPickerLink.get(i), logger);
			} catch (Exception e) {
				throw new SkipException("Icons with links are not present");
			}
				hardAssert.assertFalse(iconPickerLink.get(i).getAttribute("href").isEmpty());
				logger.info("Redirection Link ==> " + iconPickerLink.get(i).getAttribute("href"));
		}
	}

	
	
	@Test(priority = 3, enabled = true)
	public void linkRedirectionCheck() {
		HashMap<String, Boolean> assertConditionMap = skipNonExistingComponent(cardUrls);

		for (String cardUrl : cardUrls) {
			mydriver.get(cardUrl);
			

			List<WebElement> iconPickerLink = mydriver.findElements(By.xpath(IconPickerNew_page.iconWithLink));
			String handle = mydriver.getWindowHandle();
			int i=getRandomInteger(iconPickerLink.size(), 0);
			try {
				scrollToElement(mydriver, iconPickerLink.get(i), logger);
			} catch (Exception e) {
				throw new SkipException("Icons with links are not present");
			}
			String expLink = iconPickerLink.get(i).getAttribute("href");
			
			iconPickerLink.get(i).click();
			
			assertRedirection(mydriver, logger, getDomainName(cardUrl), expLink, handle);
		}
	}

}
