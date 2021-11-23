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

import compontentPages.IconStrip_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class IconStrip_StepDefinition extends IconStrip_page {
	private String author = "Priyanka Lajpal";

	private static String currentDomain = "=>";

	private static ArrayList<String> iconstrip = new ArrayList<>();
	private static Logger logger;

	@BeforeClass
	public void setup() {
		fetchSession(IconStrip_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(IconStrip_StepDefinition.class.getName());
<<<<<<< Updated upstream
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
=======
>>>>>>> Stashed changes
		new IconStrip_page();
		
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("icon-strip") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				iconstrip.add("http://apsrs5642:8080/content/AutomationDirectory/icon-strip.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				iconstrip.add("http://apvrt31468:4503/content/AutomationDirectory/icon-strip.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("icon-strip").split(",");
			for (String link : scannedUrls) {
				iconstrip.add(link);
			}
		}

		ExtentTestManager.startTest(IconStrip_StepDefinition.class.getName());
		for (String url : iconstrip) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("IconStrip", author, currentDomain, IconStrip_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(IconStrip_StepDefinition.class);
		logger.info("Urls for '" + IconStrip_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(IconStrip_StepDefinition.class.getName(), currentDomain);

		driverMap.put(IconStrip_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + IconStrip_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true)
	public void elementVisibilityCheck() {

		skipNonExistingComponent(iconstrip);
		for (String strip : iconstrip) {
			urlUnderTest.get().add(strip); mydriver.get(strip);
		}
		List<WebElement> cards = mydriver.findElements(By.xpath(IconStrip_page.segments));
		int i = 0;
		for (WebElement card : cards) {
			scrollToElement(mydriver, card, logger);
			WebElement title = mydriver.findElements(By.xpath(IconStrip_page.segmenttitle)).get(i);
			WebElement icon = mydriver.findElements(By.xpath(IconStrip_page.segmenticon)).get(i);
			focusElement(mydriver, icon);
			focusElement(mydriver, title);
			if (!title.getText().isEmpty()) {
				logger.info(" 'Segment " + i + " Title: '"
						+ mydriver.findElements(By.xpath(IconStrip_page.segmenttitle)).get(i).getText());
			} else {
				fail("Icon Strip segment found with blank Title");
			}
			i++;

		}

	}

	@Test(priority = 2, enabled = true)
	public void titleVisibilityCheck() {
		skipNonExistingComponent(iconstrip);
		for (String strip : iconstrip) {
			urlUnderTest.get().add(strip); mydriver.get(strip);
			getVisibility(mydriver, title, 10);
			softAssert.assertTrue(verifyElementExists(logger, title, "Icon Segment Title"));
			softAssert.assertAll();
			if (IconStrip_page.title.getText() != null) {
				logger.info("Icon Strip Title ==>" + title.getText());
			} else {
				fail("Icon Strip segment has missing header");
			}
		}
	}

	@Test(priority = 3, enabled = true)
	public void cardredirectionAvailabilityCheck() {
		skipNonExistingComponent(iconstrip);
		for (String strip : iconstrip) {
			urlUnderTest.get().add(strip); mydriver.get(strip);
		}
		List<WebElement> segments = mydriver.findElements(By.xpath(IconStrip_page.segments));
		int i = 1;
		for (WebElement segment : segments) {
			scrollToElement(mydriver, segment, logger);
			WebElement segmentLink = mydriver.findElement(By.xpath("(//*[@class=\"iconstrip__item\"]/a)[" + i + "]"));
			if (segmentLink.getAttribute("href").isEmpty()) {
				fail("Segment Hyperlink is Empty");
			} else {
				logger.info("Redirection Link ==> " + segmentLink.getAttribute("href"));
			}
			i++;
		}

	}
	
	
	@Test(priority = 4, enabled = true)
	public void cardLinkRedirectionCheck() {
		skipNonExistingComponent(iconstrip);
		for (String strip : iconstrip) {
			urlUnderTest.get().add(strip); mydriver.get(strip);

			List<WebElement> links;

			try {
				scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@class=\"iconstrip__item\"]/a")), logger);
				links = mydriver.findElements(By.xpath("//*[@class=\"iconstrip__item\"]/a"));

			} catch (Exception e) {
				throw new SkipException("No Segment Found with link");

			}
			int i = getRandomInteger(links.size(), 0);
			scrollToElement(mydriver, links.get(i), logger);
			String linkUrl = links.get(i).getAttribute("href");
//			getActions(mydriver).click(links.get(i)).perform();
			String handle = mydriver.getWindowHandle();
			links.get(i).click();
			pleaseWait(4, logger);
			jsWaitForPageToLoad(10,mydriver, logger);
			assertRedirection(mydriver, logger, getDomainName(strip), linkUrl, handle);
		}

	}
}