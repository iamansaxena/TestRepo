package componentStepDef;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.NewsroomMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class NewsroomMedex_StepDefinition extends NewsroomMedex_page {

	private String author = "Rekha Vasugan";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(NewsroomMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(NewsroomMedex_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new NewsroomMedex_page();
		ExtentTestManager.startTest(NewsroomMedex_StepDefinition.class.getName());
		setTagForTestClass("Newroom [Medex]", author, NewsroomMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(NewsroomMedex_StepDefinition.class);
		logger.info("Urls for '" + NewsroomMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(NewsroomMedex_StepDefinition.class.getName(), currentDomain);
		driverMap.put(NewsroomMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + NewsroomMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);

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

	@Test(priority = 1, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"newsroom"})
	public void elementVisibilityCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrolltillvisibility();
			customTestLogs.get().add("Verifying the presence of section title field: " + sectionTitle.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, sectionTitle, "Section Title"));
			customTestLogs.get().add("Title => " + sectionTitle.getText());
			hardAssert.assertFalse(sectionTitle.getText().isEmpty());
			customTestLogs.get()
					.add("Verifying the presence of section description field: " + sectionTitle.isDisplayed());
			hardAssert.assertTrue(verifyElementExists(logger, sectionTitle, "Section Description"));
			customTestLogs.get().add("Description => " + sectionDescription.getText());
			hardAssert.assertFalse(sectionDescription.getText().isEmpty());
	}

	@Test(priority = 2, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"newsroom"})
	public void ctaButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrolltillvisibility();
			try {
				sectionCtaButton.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("No CTA Button available");
			}

			String handle = mydriver.getWindowHandle();
			String expLink = sectionCtaButton.getAttribute("href");
			sectionCtaButton.click();
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"newsroom"})
	public void newsCardRedirectionCheck(String url) {
		skipNonExistingComponent(url);
		
			
			mydriver.get(url);
			scrolltillvisibility();
			int i = 0;
			if (newsCards.size() > 0) {
				newsCards.get(0).isDisplayed();
				i = getRandomInteger(newsCards.size(), 0);
			} else {
				throw new SkipException("No news card available");
			}

			String handle = mydriver.getWindowHandle();
			String expLink = newsCards.get(i).getAttribute("href");
			scrollToElement(mydriver, newsCards.get(i), logger).click();
			assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

}
