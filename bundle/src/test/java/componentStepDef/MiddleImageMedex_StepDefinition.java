package componentStepDef;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import compontentPages.MiddleImageMedex_page;
import core.CustomDataProvider;
import utils.ExtentTestManager;
import utils.LoggerLog4j;

public class MiddleImageMedex_StepDefinition extends MiddleImageMedex_page {
	private String author = "Aman Saxena";
	private static String currentDomain = "=>";
	private static Logger logger;

	@BeforeClass
	public void setup() {

		fetchSession(MiddleImageMedex_StepDefinition.class);
		mydriver = LATEST_DRIVER_POOL.get(MiddleImageMedex_StepDefinition.class.getName());
		new MiddleImageMedex_page();
		mydriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		ExtentTestManager.startTest(MiddleImageMedex_StepDefinition.class.getName());
		setTagForTestClass("Middle Image [Medex]", author, MiddleImageMedex_StepDefinition.class.getName());
		logger = LoggerLog4j.startTestCase(MiddleImageMedex_StepDefinition.class);
		logger.info("Urls for '" + MiddleImageMedex_StepDefinition.class.getName() + "' => " + currentDomain);
		testURLS.put(MiddleImageMedex_StepDefinition.class.getName(), currentDomain);

		driverMap.put(MiddleImageMedex_StepDefinition.class.getName().split("\\.")[1], mydriver);

		logger.info("Browser pool at '" + MiddleImageMedex_StepDefinition.class.getName() + "' =>\n" + driverMap);
	}

	@AfterClass
	public void tearDown() {
		mydriver.quit();
	}

	@AfterMethod
	public void checkPage() {
		softAssert = new SoftAssert();
	}

	
	@Parameters({"middle-image"})
	@Test(priority = 1, enabled = true,  dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image"})
	public void elementVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			
			mydriver.get(cardUrl);
			scrollToElement(mydriver, middleImageSection, logger);
			for (int i = 0; i <= 3; i++) {
				int j = i + 1;

				customTestLogs.get()
						.add("Checking if Graphic circle '" + j + "' is visible: " + verifyElementExists(logger,
								mydriver.findElements(By.xpath(graphicCirles)).get(i), "Graphic Circle '" + j + "'"));
				hardAssert.assertTrue(verifyElementExists(logger, mydriver.findElements(By.xpath(graphicCirles)).get(i),
						"Graphic Circle '" + j + "'"));
			
		}
	}

	@Test(priority = 2, enabled = true, dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image"})
	public void mainSectionTitleAvailabilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			
			mydriver.get(cardUrl);
			scrollToElement(mydriver, middleImageSection, logger);
			try {
				middleImageSectionTitle.isDisplayed();
			} catch (Exception e) {
				throw new SkipException("Section Title field not available ");
			}

			scrollToElement(mydriver, middleImageSectionTitle, logger);
			customTestLogs.get().add("Main section title field: " + middleImageSectionTitle.getAttribute("innerText"));
			hardAssert.assertFalse(middleImageSectionTitle.getAttribute("innerText").isEmpty());
			hardAssert.assertTrue(verifyElementExists(logger, middleImageSectionTitle, "Title field"));
		
	}

	@Test(priority = 3, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image"})
	public void subSectionTitleAvailabilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			
			mydriver.get(cardUrl);
			scrollToElement(mydriver, middleImageSection, logger);
			List<WebElement> titles = mydriver.findElements(By.xpath(imageSectionTitles));

			try {
				titles.get(0).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's no sub section title fields");
			}

			int i = 1;
			for (WebElement title : titles) {
				scrollToElement(mydriver, title, logger);
				customTestLogs.get().add("Checking if sub section title '" + i + "' is visible: "
						+ verifyElementExists(logger, title, "sub section title '" + i + "'"));
				hardAssert.assertTrue(verifyElementExists(logger, title, "sub section title '" + i + "'"));

				customTestLogs.get().add("Sub section title '" + i + "' : " + title.getAttribute("innerText"));
				hardAssert.assertFalse(title.getAttribute("innerText").isEmpty());
				i++;
			}

		
	}

	@Test(priority = 4, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image"})
	public void subSectionCopyAvailabilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			
			mydriver.get(cardUrl);
			scrollToElement(mydriver, middleImageSection, logger);
			List<WebElement> copies = mydriver.findElements(By.xpath(imageSectionCopies));

			try {
				copies.get(0).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There's no sub section copy fields");
			}

			int i = 1;
			for (WebElement copy : copies) {
				scrollToElement(mydriver, copy, logger);
				customTestLogs.get().add("Checking if sub section copy '" + i + "' is visible: "
						+ verifyElementExists(logger, copy, "sub section copy '" + i + "'"));
				hardAssert.assertTrue(verifyElementExists(logger, copy, "sub section copy '" + i + "'"));

				customTestLogs.get().add("Sub section copy'" + i + "' : " + copy.getAttribute("innerText"));
				hardAssert.assertFalse(copy.getAttribute("innerText").isEmpty());
				i++;
			}
		

	}

	@Test(priority = 5, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image"})
	public void subSectionExpandContractFunctionalityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);
			mydriver.get(cardUrl);
			scrollToElement(mydriver, middleImageSection, logger);
			List<WebElement> expButtons = mydriver.findElements(By.xpath(imageSectionExpandButtons));
			List<WebElement> closeButtons = mydriver.findElements(By.xpath(imageSectionCloseButtons));

			try {
				expButtons.get(0).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There no service copy field available");
			}
			int i = getRandomInteger(expButtons.size(), 0);
			scrolltillvisibilityMedex(mydriver, middleImageSection, logger);
			pleaseWait(4, logger);
			hardAssert.assertFalse(closeButtons.get(i).isDisplayed());
			scrollToElement(mydriver, mydriver.findElements(By.xpath(graphicCirles)).get(i), logger);
			expButtons.get(i).click();
			customTestLogs.get().add("Section expanded after click? : "
					+ verifyElementExists(logger, closeButtons.get(i), "Expand button '" + i + "'"));
			hardAssert.assertTrue(verifyElementExists(logger, closeButtons.get(i), "Close button '" + i + "'"));
			scrollToElement(mydriver, closeButtons.get(i), logger);
			closeButtons.get(i).click();
			customTestLogs.get().add("Section contracted after click? : "+ verifyElementExists(logger, expButtons.get(i), "Expand button '" + i + "'"));
			hardAssert.assertTrue(verifyElementExists(logger, expButtons.get(i), "Close button '" + i + "'"));
		
	}

	@Test(priority = 6, enabled = true,dataProvider = "data-provider", dataProviderClass = CustomDataProvider.class, parameters = {"middle-image"})
	public void subSectionServiceCopyVisibilityCheck(String cardUrl) {
		skipNonExistingComponent(cardUrl);

		
			
			mydriver.get(cardUrl);
			scrollToElement(mydriver, middleImageSection, logger);
			List<WebElement> serviceCopy = mydriver.findElements(By.xpath(imageSectionServiceCopies));
			List<WebElement> expButtons = mydriver.findElements(By.xpath(imageSectionExpandButtons));
			List<WebElement> closeButtons = mydriver.findElements(By.xpath(imageSectionCloseButtons));

			try {
				mydriver.findElement(By.xpath(imageSectionServiceCopies)).isDisplayed();
			} catch (Exception e) {
				throw new SkipException("There no service copy field available");
			}
			int i = getRandomInteger(serviceCopy.size(), 0);
			scrolltillvisibilityMedex(mydriver, middleImageSection, logger);
			pleaseWait(4, logger);
			hardAssert.assertFalse(verifyElementExists(logger, closeButtons.get(i), "Expand button '" + i + "'"));
			expButtons.get(i).click();
			customTestLogs.get().add("Service Copy section expanded after click? : "
					+ verifyElementExists(logger, serviceCopy.get(i), "Service Copy '" + i + "'"));
			hardAssert.assertTrue(verifyElementExists(logger, serviceCopy.get(i), "Service Copy '" + i + "'"));
			scrollToElement(mydriver, closeButtons.get(i), logger);
			closeButtons.get(i).click();
			customTestLogs.get().add("Service Copy section contracted after click? : "
					+ !(verifyElementExists(logger, serviceCopy.get(i), "Service Copy '" + i + "'")));
			hardAssert.assertFalse(verifyElementExists(logger, serviceCopy.get(i), "Service Copy '" + i + "'"));
		
	}

}
