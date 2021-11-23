package componentStepDef;import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MaterialCardFade_page;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MaterialCardFade_StepDefinition extends MaterialCardFade_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static ArrayList<String> matUrls= new ArrayList<>();
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {
		fetchSession(MaterialCardFade_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MaterialCardFade_StepDefinition.class.getName());
		new MaterialCardFade_page();
		
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);if (fetchUrl("mcard-fade") == null) {
			if (Environment.equalsIgnoreCase("stage")) {
				matUrls.add("http://apsrs5642:8080/content/AutomationDirectory/Material_Card_Fade.html");
			} else if (Environment.equalsIgnoreCase("test")) {
				matUrls.add("http://apvrt31468:4503/content/AutomationDirectory/Material_Card_Fade.html");
			}
		} else {
			String[] scannedUrls = fetchUrl("mcard-fade").split(",");
			for (String link : scannedUrls) {
				matUrls.add(link);
			}
		}

		ExtentTestManager.startTest(MaterialCardFade_StepDefinition.class.getName());
		for (String url : matUrls) {
			currentDomain = currentDomain + "[" + url + "]";
		}
		setTagForTestClass("MaterialCardFade", author, currentDomain, MaterialCardFade_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MaterialCardFade_StepDefinition.class);
		logger.info("Urls for '" + MaterialCardFade_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MaterialCardFade_StepDefinition.class.getName(), currentDomain);

		driverMap.put(MaterialCardFade_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + MaterialCardFade_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
	public void elementVisibilityCheck() {
		skipNonExistingComponent(matUrls);
		for (String matUrl : matUrls) {
			urlUnderTest.get().add(matUrl); mydriver.get(matUrl);

			currentDomain = currentDomain + "[" + matUrl + "]";
			List<WebElement> cards = mydriver.findElements(By.xpath("//*[@class=\"mcard mcard__fade\"]"));
			int i = 0;
			for (WebElement card : cards) {
				scrollToElement(mydriver, card, logger);
				hardAssert.assertFalse(mydriver.findElements(By.xpath(titles)).get(i).getText().isEmpty());
				hardAssert.assertFalse(mydriver.findElements(By.xpath(buttons)).get(i).getText().isEmpty());

			}
		}
	}

	@Test(priority = 2, enabled = true)
	public void additionalFieldsCheck() {
		skipNonExistingComponent(matUrls);
		for (String matUrl : matUrls) {
			urlUnderTest.get().add(matUrl); mydriver.get(matUrl);
			currentDomain = currentDomain + "[" + matUrl + "]";
			List<WebElement> desc = mydriver.findElements(By.xpath(descriptions));
			for (WebElement des : desc) {
				scrollToElement(mydriver, des, logger);
//				String s = des.getText();
				hardAssert.assertFalse(des.getText().isEmpty());
			}
		}
	}

	@Test(priority = 3, enabled = true)
	public void hyperlinkRedirectionCheck() {
		skipNonExistingComponent(matUrls);
		for (String matUrl : matUrls) {
			urlUnderTest.get().add(matUrl); mydriver.get(matUrl);
			currentDomain = currentDomain + "[" + matUrl + "]";
//			List<WebElement> desc = mydriver.findElements(By.xpath(descriptions));
			List<WebElement> cards = mydriver.findElements(By.xpath("(//*[@class=\"mcard mcard__fade\"])/a"));

			String currentPage = mydriver.getCurrentUrl();
			String domain = getDomainName(currentPage);
			String currentHandle = mydriver.getWindowHandle();

			int i = getRandomInteger(cards.size(), 0);

			scrollToElement(mydriver, cards.get(i), logger);
			logger.info("Checking Material-Fade card on : " + currentPage);
			String hyperlink = "";
			try {
				hyperlink = cards.get(i).getAttribute("href");
			} catch (Exception e) {
				hyperlink = null;
			}
			if (hyperlink != null) {
				cards.get(i).click();
				Set<String> allHandles = mydriver.getWindowHandles();
				String hyperlinkDomain = getDomainName(hyperlink);
				if (!hyperlinkDomain.equals(domain)) {
					allHandles.remove(currentHandle);
					mydriver.switchTo().window(allHandles.iterator().next());
					hardAssert.assertNotEquals(mydriver.getWindowHandle(), currentHandle);
					mydriver.get(currentPage);
				} else if (hyperlinkDomain.equals(domain)) {
					hardAssert.assertEquals(mydriver.getWindowHandle(), currentHandle);

				}
			}

		}

	}

}
