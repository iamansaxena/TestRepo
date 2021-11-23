package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.ForumHeroBanner_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class ForumHeroBanner_StepDefinition extends ForumHeroBanner_page {

	private String author = "Aman Saxena";
	private static Logger logger;
	private static String currentDomain = "=>";

	@BeforeClass
	public void setup() {

		fetchSession(ForumHeroBanner_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(ForumHeroBanner_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new ForumHeroBanner_page();
		ExtentTestManager.startTest(ForumHeroBanner_StepDefinition.class.getName());
		setTagForTestClass("Forum Hero Banner", author, ForumHeroBanner_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(ForumHeroBanner_StepDefinition.class);
		logger.info("Urls for '" + ForumHeroBanner_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(ForumHeroBanner_StepDefinition.class.getName(), currentDomain);

		driverMap.put(ForumHeroBanner_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + ForumHeroBanner_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
			"mega-hero-banner" })
	public void backgroundImageCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);

		try {
			bannerBgImageField.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no bakground image");
		}
		customTestLogs.get().add("verifying if background image is visible : "
				+ verifyElementExists(logger, bannerBgImageField, "background image"));
		hardAssert.assertTrue(verifyElementExists(logger, bannerBgImageField, "background image"));
		customTestLogs.get()
				.add("verifying if background image has some image path : " + bannerBgImageField.getAttribute("style"));
		hardAssert.assertFalse(bannerBgImageField.getAttribute("style").contains("url()"));

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void breadcrumbFieldRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			breadcrumbField.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no breadcrumb field available");
		}

		customTestLogs.get().add("Breadcrumb field link : " + breadcrumbField.getAttribute("href"));
		hardAssert.assertFalse(breadcrumbField.getAttribute("href").isEmpty());
		String handle = mydriver.getWindowHandle();
		String expLink = breadcrumbField.getAttribute("href");
		breadcrumbField.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void logoFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			logoField.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no Logo field available");
		}

		customTestLogs.get().add("Is Logo field available: " + verifyElementExists(logger, logoField, "Logo"));
		hardAssert.assertTrue(verifyElementExists(logger, logoField, "Logo"));
		customTestLogs.get().add("Logo field alt text: " + logoField.getAttribute("alt"));
		hardAssert.assertFalse(logoField.getAttribute("alt").isEmpty());

	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void bannerHeadingFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			bannerHeading.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no banner heading field available");
		}
		customTestLogs.get()
				.add("Is Banner heading visible: " + verifyElementExists(logger, bannerHeading, "Banner heading"));
		hardAssert.assertTrue(verifyElementExists(logger, bannerHeading, "Banner heading"));
		customTestLogs.get().add("Heading: " + bannerHeading.getAttribute("innerText"));
		hardAssert.assertFalse(bannerHeading.getAttribute("innerText").isEmpty());

	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void topLineFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			bannerFirstLine.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no banner top line field available");
		}
		customTestLogs.get().add("Is Top line visible: " + verifyElementExists(logger, bannerFirstLine, "top line"));
		hardAssert.assertTrue(verifyElementExists(logger, bannerFirstLine, "top line"));
		customTestLogs.get().add("First Line text: " + bannerFirstLine.getAttribute("innerText"));
		hardAssert.assertFalse(bannerFirstLine.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 6, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void secondLineFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			bannerFirstLine.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no banner second line field available");
		}
		customTestLogs.get()
				.add("Is Second line visible: " + verifyElementExists(logger, bannerSecondLine, "Second line"));
		hardAssert.assertTrue(verifyElementExists(logger, bannerSecondLine, "Second line"));
		customTestLogs.get().add("Second Line text: " + bannerSecondLine.getAttribute("innerText"));
		hardAssert.assertFalse(bannerSecondLine.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 7, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void thirdLineFieldCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			bannerThirdLine.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no banner third line field available");
		}
		customTestLogs.get()
				.add("Is Third line visible: " + verifyElementExists(logger, bannerThirdLine, "Third line"));
		hardAssert.assertTrue(verifyElementExists(logger, bannerThirdLine, "Third line"));
		customTestLogs.get().add("Third Line text: " + bannerThirdLine.getAttribute("innerText"));
		hardAssert.assertFalse(bannerThirdLine.getAttribute("innerText").isEmpty());
	}

	@Test(priority = 8, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"mega-hero-banner" })
	public void ctaButtonRedirectionCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, bannerSection, logger);
		try {
			bannerCtaButton.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no cta field available");
		}
		customTestLogs.get()
				.add("Is CTA button visible : " + verifyElementExists(logger, bannerCtaButton, "Cta button"));
		customTestLogs.get().add("CTA button link : " + bannerCtaButton.getAttribute("href"));

		hardAssert.assertFalse(bannerCtaButton.getAttribute("href").isEmpty());
		String handle = mydriver.getWindowHandle();
		String expLink = bannerCtaButton.getAttribute("href");
		bannerCtaButton.click();
		assertRedirection(mydriver, logger, getDomainName(url), expLink, handle);

	}

}
