package compontentPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import core.Base;

public class EmployeeCarouselMedex_page extends Base {

	protected static WebDriver mydriver;

	@FindBy(xpath = "//*[@class=\"employee-carousel section\"]")
	protected static WebElement carouselSection;

	@FindBy(xpath = "//*[@class='employee-carousel section']//*[@class=\"carouseller__right\"]")
	protected static WebElement carouselNextButton;

	@FindBy(xpath = "//*[@class='employee-carousel section']//*[@class=\"carouseller__left\"]")
	protected static WebElement carouselPrevButton;

	@FindBy(xpath = "//*[@class='employee-carousel section']//*[@class=\"carouseller__list\"]/*[contains(@class,'car')]")
	protected static List<WebElement> employeeColumns;

	@FindBy(xpath = "//*[@class='employee-carousel section']//*[@class=\"carouseller__list\"]/*[contains(@class,'car')]//*[contains(@class,'team-header')]")
	protected static List<WebElement> employeeHeaders;

	@FindBy(xpath = "//*[@class='employee-carousel section']//*[@class=\"carouseller__list\"]/*[contains(@class,'car')]//*[contains(@class,'employee-name')]")
	protected static List<WebElement> employeeDesignations;

	@FindBy(xpath = "//*[@class='employee-carousel section']//*[@class=\"carouseller__list\"]/*[contains(@class,'car')]//*[contains(@class,'location')]")
	protected static List<WebElement> employeeLocations;

	@FindBy(xpath = "//*[@class='employee-carousel section']//*[@class=\"carouseller__list\"]//*[contains(@class,'team-data')]//*[@class=\"employee-carousel__employee-details detail-copy\"]")
	protected static List<WebElement> employeeDetails;
	protected static int columnNumber = 0;
	protected static String employeeQuestions;

	protected static String employeeAnswers;

	@FindBy(xpath = "//*[@class='employee-carousel section']//*[@class=\"employee-carousel__employee-image\"]")
	protected static List<WebElement> employeeImages;

	protected  void scrollToEmployeeColumn(WebElement column) {
		scrollToElementWithoutWait(mydriver, column);
		while (!column.isDisplayed()) {
			pleaseWait(1, logger);
			carouselNextButton.click();
			pleaseWait(2, logger);
		}
	}

	protected static void setQuestionAnswerIndex(int i) {
		employeeQuestions = "//*[@class='employee-carousel section']//*[@class=\"carouseller__list\"]/*[contains(@class,'car_')]["
				+ i + "]//*[contains(@class,'slide-facts')]/h4";
		employeeAnswers = "//*[@class='employee-carousel section']//*[@class=\"carouseller__list\"]/*[contains(@class,'car_')]["
				+ i + "]//*[contains(@class,'slide-facts')]/p";
	}

	protected static SoftAssert softAssert = new SoftAssert();

	protected  void assertEmployeeQuestion(int columnNumber, WebElement column, Logger logger) {
		scrollToEmployeeColumn(column);
		setQuestionAnswerIndex(columnNumber);
		try {
			mydriver.findElement(By.xpath(employeeQuestions)).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("There's no question available for employee column '" + columnNumber + "'");

		}
		int i = 0;
		List<WebElement> answers = mydriver.findElements(By.xpath(employeeAnswers));
		for (WebElement question : mydriver.findElements(By.xpath(employeeQuestions))) {
			softAssert.assertTrue(verifyElementExists(logger, question, question.getAttribute("innerText")));
			softAssert.assertTrue(
					verifyElementExists(logger, answers.get(i), answers.get(i).getAttribute("innerText")));
			softAssert.assertAll();
			i++;
		}

	}

	protected  void slideCarouselNext(int slideNumber) {
		while (verifyElementExists(logger, employeeImages.get(slideNumber), " ")==false) {
			scrollToElement(mydriver, carouselSection, logger);
			pleaseWait(2, logger);
			System.out.println(employeeImages.get(slideNumber).getLocation().x);
			if (employeeImages.get(slideNumber).isDisplayed()) {
				break;
			}
try {
	scrollToElement(mydriver, employeeImages.get(slideNumber), logger);
	break;
} catch (Exception e) {
	
}
			carouselNextButton.click();
			focusElement(mydriver, carouselNextButton);
			pleaseWait(2, logger);
		}

	}

	protected  void slideCarouselPrev(int slideNumber) {
		carouselNextButton.click();
		while (verifyElementExists(logger, employeeImages.get(slideNumber), " ")==false){
			scrollToElement(mydriver, carouselSection, logger);
			System.out.println(employeeImages.get(slideNumber).getLocation().x);
			pleaseWait(2, logger);
			if (employeeImages.get(slideNumber).isDisplayed()) {
				break;
			}
			try {
				scrollToElement(mydriver, employeeImages.get(slideNumber), logger);
				break;
			} catch (Exception e) {
				
			}
			focusElement(mydriver, carouselPrevButton);
			carouselPrevButton.click();
			pleaseWait(2, logger);
		}

	}

	public EmployeeCarouselMedex_page() {
		PageFactory.initElements(mydriver, this);
	}

	protected void scrolltillvisibility() {
		scrollToElement(mydriver, carouselSection, logger);
		try {
			((JavascriptExecutor) mydriver)
					.executeScript("return document.getElementsByClassName('header med-header sticky')[0].remove();");
		} catch (WebDriverException e) {
		}

		scrollToElement(mydriver, carouselSection, logger);
		pleaseWait(1, logger);

	}
}
