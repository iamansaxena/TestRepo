package componentStepDef;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.AtGlanceMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class AtGlanceMedex_StepDefinition extends AtGlanceMedex_page {

	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(AtGlanceMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(AtGlanceMedex_StepDefinition.class.getName());
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new AtGlanceMedex_page();
		ExtentTestManager.startTest(AtGlanceMedex_StepDefinition.class.getName());
		setTagForTestClass("At A Glance", author, AtGlanceMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(AtGlanceMedex_StepDefinition.class);
		logger.info("Urls for '" + AtGlanceMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(AtGlanceMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(AtGlanceMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);
		pleaseWait(1, logger);
		logger.info("Browser pool at '" + AtGlanceMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);

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
			"at-a-glance-carousel" })
	public void defaultElementVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, glanceSection, logger);
		try {
			slides.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no slide authored");
		}
		int i = 1;
		int j = 0;
		for (WebElement slide : slides) {
			switchToNextSlide(slide, logger);
			customTestLogs.get().add("Is Slide '" + i + "' Header visible: "
					+ verifyElementExists(logger, slideHeader.get(j), "Slide '" + i + "' header"));
			customTestLogs.get().add("Slide '" + i + " header': " + slideHeader.get(j).getAttribute("innerText"));
			softAssert.assertTrue(verifyElementExists(logger, slideHeader.get(j), "Slide '" + i + "' header"));
			softAssert.assertFalse(slideHeader.get(j).getAttribute("innerText").isEmpty());

			customTestLogs.get().add("Is Slide '" + i + "' Copy visible: "
					+ verifyElementExists(logger, slideCopy.get(j), "Slide '" + i + "' copy"));
			customTestLogs.get().add("Slide '" + i + " copy': " + slideCopy.get(j).getAttribute("innerText"));
			softAssert.assertTrue(verifyElementExists(logger, slideCopy.get(j), "Slide '" + i + "' copy"));
			softAssert.assertFalse(slideCopy.get(j).getAttribute("innerText").isEmpty());

			customTestLogs.get().add("Is Slide '" + i + "' Col-1 copy visible: "
					+ verifyElementExists(logger, getCurrentColumnHeaders(i).get(0), "Slide '" + i + "' col-1copy"));
			customTestLogs.get().add(
					"Slide '" + i + " Col-1 copy': " + getCurrentColumnHeaders(i).get(0).getAttribute("innerText"));
			softAssert.assertTrue(
					verifyElementExists(logger, getCurrentColumnHeaders(i).get(0), "Slide '" + i + "' col-1copy"));
			softAssert.assertFalse(getCurrentColumnHeaders(i).get(0).getAttribute("innerText").isEmpty());

			customTestLogs.get().add("Is Slide '" + i + "' Col-2 copy visible: "
					+ verifyElementExists(logger, getCurrentColumnHeaders(i).get(1), "Slide '" + i + "' col-2copy"));
			customTestLogs.get().add(
					"Slide '" + i + " Col-2 copy': " + getCurrentColumnHeaders(i).get(1).getAttribute("innerText"));
			softAssert.assertTrue(
					verifyElementExists(logger, getCurrentColumnHeaders(i).get(1), "Slide '" + i + "' col-2copy"));
			softAssert.assertFalse(getCurrentColumnHeaders(i).get(1).getAttribute("innerText").isEmpty());

			customTestLogs.get().add("Is Slide '" + i + "' Col-3 copy visible: "
					+ verifyElementExists(logger, getCurrentColumnHeaders(i).get(2), "Slide '" + i + "' col-3copy"));
			customTestLogs.get().add(
					"Slide '" + i + " Col-3 copy': " + getCurrentColumnHeaders(i).get(2).getAttribute("innerText"));
			softAssert.assertTrue(
					verifyElementExists(logger, getCurrentColumnHeaders(i).get(2), "Slide '" + i + "' col-3copy"));
			softAssert.assertFalse(getCurrentColumnHeaders(i).get(2).getAttribute("innerText").isEmpty());

			softAssert.assertAll();

			i++;
			j++;

		}

	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"at-a-glance-carousel" })
	public void columnStatAndIconCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, glanceSection, logger);
		try {
			slides.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no slide authored");
		}
		customTestLogs.get().add("Verifying that if column stat is visible then column icon should not be visible");
		int totalCount = slideColumnIcons.size() + slideColumnHeaders.size();
		int actualCount = totalCount / 3; // dividing with total no. of columns in each slide
		customTestLogs.get().add("total no. stat and icon: " + totalCount);
		hardAssert.assertEquals(actualCount, slides.size());
	}

	@Test(priority = 3, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"at-a-glance-carousel" })
	public void sliderFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, glanceSection, logger);
		try {
			nextButton.isDisplayed();
		} catch (Exception e) {
			throw new SkipException("Only one slide is authored");
		}
		int i = getRandomInteger(slides.size(), 1);
		int j = i + 1;
		switchToNextSlide(slides.get(i), logger);
		hardAssert.assertTrue(verifyElementExists(logger, slides.get(i), "Slide '" + j + "'"));
		switchToPrevSlide(slides.get(0), logger);
		hardAssert.assertTrue(verifyElementExists(logger, slides.get(0), "Slide '" + 0 + "'"));
	}

	@Test(priority = 4, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"at-a-glance-carousel" })
	public void slideButtonVisibilityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, glanceSection, logger);
		try {
			slides.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no slide authored");
		}
		if (slides.size() == 1) {
			throw new SkipException("Only one slide is authored");
		} else {
			customTestLogs.get()
					.add("Is next button visible: " + verifyElementExists(logger, nextButton, "next button"));
			hardAssert.assertTrue(verifyElementExists(logger, nextButton, "next button"));
			customTestLogs.get()
					.add("Is Prev button visible: " + verifyElementExists(logger, prevButton, "prev button"));
			hardAssert.assertTrue(verifyElementExists(logger, prevButton, "prev button"));
		}
	}

	@Test(priority = 5, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {
			"at-a-glance-carousel" })
	public void slideBulletFunctionalityCheck(String url) {
		skipNonExistingComponent(url);

		
		mydriver.get(url);
		scrollToElement(mydriver, glanceSection, logger);
		try {
			slides.get(0).isDisplayed();
		} catch (Exception e) {
			throw new SkipException("There's no slide authored");
		}
		if (slides.size() == 1) {
			throw new SkipException("Only one slide is authored");
		} else {
			int i = getRandomInteger(slideBulletButtons.size(), 0);
			customTestLogs.get().add("Clicking slide bullet: " + 2);
			switchSlideByBullet(1, logger);
			hardAssert.assertTrue(verifyElementExists(logger, slides.get(1), "slide 2"));
			customTestLogs.get().add("Clicking slide bullet: " + 1);
			switchSlideByBullet(0, logger);
			hardAssert.assertTrue(verifyElementExists(logger, slides.get(0), "slide 1"));
		}
	}

}
