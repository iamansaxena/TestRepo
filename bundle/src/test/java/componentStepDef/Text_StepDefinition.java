package componentStepDef;import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.Text_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class Text_StepDefinition extends Text_page{
	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> urls= new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(Text_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(Text_StepDefinition.class.getName());
		new Text_page();
		
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("text-component") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				urls.add("http://apsrs5642:8080/content/AutomationDirectory/text.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				urls.add("http://apvrt31468:4503/content/AutomationDirectory/text.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("text-component").split(",");
			for (String link : scannedUrls) {
				urls.add(link);
			}
		}

		
		ExtentTestManager.startTest(Text_StepDefinition.class.getName());
		for (String url : urls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("Text Component", author, currentDomain, Text_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(Text_StepDefinition.class);
		logger.info("Urls for '" + Text_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(Text_StepDefinition.class.getName(), currentDomain);

		driverMap.put(Text_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + Text_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
//		mydriver.manage().deleteAllCookies();
	}

	@Test(priority = 1, enabled = true)
	public void textFiledsVisibilityAndEmptyFieldCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {

			urlUnderTest.get().add(url); mydriver.get(url);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(textField)), logger);
			} catch (Exception e) {
				throw new SkipException("Blank text box");
			}
			List<WebElement> texts = mydriver.findElements(By.xpath(textField));
			int i = 0;
			for(WebElement text : texts){
				hardAssert.assertTrue(verifyElementExists(logger, text, text.toString()+" -> "+i));	
				hardAssert.assertFalse(text.getAttribute("innerText").isEmpty());
			i++;
			}
			
		}
	}
	@Test(priority = 2, enabled = true)
	public void paraSpacingCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(textField)), logger);
			} catch (Exception e) {
				throw new SkipException("Blank text box");
			}
			List<WebElement> texts = mydriver.findElements(By.xpath(textField));
			for(WebElement text : texts){
				if (text.getText().getClass().getName().equals("java.lang.String")) {
					logger.info("Para::> "+ text.getText().getClass().getName());
					logger.info("Para::> "+ text.getAttribute("innerText"));
				}
				else if(text.getText().isEmpty()) {
					fail("P tag can not be blank!!");
				}
			}
		}
	}
	@Test(priority = 3, enabled = true)
	public void buttonFunctionalityCheck() {
		skipNonExistingComponent(urls);
		for (String url : urls) {
			urlUnderTest.get().add(url); mydriver.get(url);
			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath(buttons)), logger);
			} catch (Exception e) {
				throw new SkipException("There is no button available");
			}
			List<WebElement> buttonsWithLink = mydriver.findElements(By.xpath(buttons));
			int i = getRandomInteger(buttonsWithLink.size(), 0);
			getPresence(mydriver, buttonsWithLink.get(i).toString().split("xpath: ")[1], 20);
			focusElement(mydriver, buttonsWithLink.get(i));
			String expLink = buttonsWithLink.get(i).getAttribute("href");
			customTestLogs.get().add("URL Fetched: "+expLink);
			logger.info(expLink);
			String handle = mydriver.getWindowHandle();
			buttonsWithLink.get(i).click();
			pleaseWait(5, logger);
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
		}
	}
	
		
}





