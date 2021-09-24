package componentStepDef;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.FooterLegalBar_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class FooterLegalBar_StepDefinition extends FooterLegalBar_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(FooterLegalBar_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(FooterLegalBar_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new FooterLegalBar_page();
		ExtentTestManager.startTest(FooterLegalBar_StepDefinition.class.getName());
		setTagForTestClass("Footer Legal Bar", author, FooterLegalBar_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(FooterLegalBar_StepDefinition.class);
		logger.info("Urls for '" + FooterLegalBar_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(FooterLegalBar_StepDefinition.class.getName(), currentDomain);

		driverMap.put(FooterLegalBar_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + FooterLegalBar_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"footer-legal-bar" })
	public void blankLegalNoticeLabelsCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, mydriver.findElement(By.xpath(legalNoticeLink)), logger);
		List<WebElement> legalNoticeLabels = mydriver.findElements(By.xpath(legalNoticeLink));
		for (WebElement label : legalNoticeLabels) {
			hardAssert.assertFalse(label.getAttribute("innerText").isEmpty());
		}
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"footer-legal-bar" })
	public void legalNoticeRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, mydriver.findElement(By.xpath(legalNoticeLink)), logger);
		List<WebElement> legalNoticeLabels = mydriver.findElements(By.xpath(legalNoticeLink));
		int i = getRandomInteger(legalNoticeLabels.size(), 0);
		String expLink = legalNoticeLabels.get(i).getAttribute("href");
		String handle = mydriver.getWindowHandle();
		legalNoticeLabels.get(i).click();
		pleaseWait(6, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"footer-legal-bar" })
	public void privacyLinkRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, mydriver.findElement(By.xpath(privacyLink)), logger);
		List<WebElement> privacyLabels = mydriver.findElements(By.xpath(privacyLink));
		for (WebElement ele : privacyLabels) {
			hardAssert.assertFalse(ele.getAttribute("innerText").isEmpty());
		}

		int i = getRandomInteger(privacyLabels.size(), 0);
		String expLink = privacyLabels.get(i).getAttribute("href");
		String handle = mydriver.getWindowHandle();
		privacyLabels.get(i).click();
		pleaseWait(6, logger);
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"footer-legal-bar" })
	public void blankCopyrightTextCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, copyrightText, logger);
		hardAssert.assertFalse(copyrightText.getAttribute("innerText").isEmpty());
	}

}
