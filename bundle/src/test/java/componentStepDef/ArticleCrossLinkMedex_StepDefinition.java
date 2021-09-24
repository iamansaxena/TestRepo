package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ArticleCrossLinkMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ArticleCrossLinkMedex_StepDefinition extends ArticleCrossLinkMedex_page {
	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(ArticleCrossLinkMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ArticleCrossLinkMedex_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		new ArticleCrossLinkMedex_page();
		ExtentTestManager.startTest(ArticleCrossLinkMedex_StepDefinition.class.getName());
		setTagForTestClass("Article Xlink [Medex]", author, ArticleCrossLinkMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ArticleCrossLinkMedex_StepDefinition.class);
		logger.info("Urls for '" + ArticleCrossLinkMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ArticleCrossLinkMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ArticleCrossLinkMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + ArticleCrossLinkMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);

	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
	}

	@Test(priority = 1, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-link1 article-xlink" })
	public void titleVisibilityCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, section, logger);
		ifElementExists(sectionTitle, "Section title is not authored");
		customTestLogs.get().add("Is title field visible: " + sectionTitle.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionTitle, "Section Title"));
		customTestLogs.get().add("Title: " + sectionTitle.getText());
		hardAssert.assertFalse(sectionTitle.getText().isEmpty());

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-link1 article-xlink" })
	public void copyVisibilityCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, section, logger);
		ifElementExists(sectionCopy, "Section copy is not authored");
		customTestLogs.get().add("Is Copy field visible: " + sectionCopy.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionCopy, "Section Copy"));
		customTestLogs.get().add("Copy: " + sectionCopy.getText());
		hardAssert.assertFalse(sectionCopy.getText().isEmpty());

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"cross-link1 article-xlink" })
	public void ctaFieldRedirectionCheck(String url) {
		
		mydriver.get(url);
		scrolltillvisibilityMedex(mydriver, section, logger);

		ifElementExists(sectionCtaLink, "Section link is not authored");
		customTestLogs.get().add("Is link field visible: " + sectionCtaLink.isDisplayed());
		hardAssert.assertTrue(verifyElementExists(logger, sectionCtaLink, "Section cta field"));
		customTestLogs.get().add("CTA Label: " + sectionCtaLink.getText());
		hardAssert.assertFalse(sectionCtaLink.getText().isEmpty());
		hardAssert.assertFalse(sectionCtaLink.getAttribute("href").isEmpty());
		String expLink = sectionCtaLink.getAttribute("href");
		sectionCtaLink.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, mydriver.getWindowHandle());

	}

}
