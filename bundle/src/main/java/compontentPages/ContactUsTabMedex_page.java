package compontentPages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import core.Base;

public class ContactUsTabMedex_page extends Base{
	protected static SoftAssert softAssert= new SoftAssert(); 
	protected static WebDriver mydriver;
	@FindBy(xpath="//*[@class='contactus-tab section']")
	protected static WebElement contactUsSection;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//h2[@class='heading h2']")
	protected static WebElement sectionHeading;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//ul[@class=\"tabs__list\"]/li/button")
	protected static List<WebElement> formsSwitchingTabs;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//*[@class=\"tab-intro\"]/p[2]")
	protected static List<WebElement> formsIntroCopies;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//*[@class=\"form-submission-msg align-center\"]")
	protected static WebElement formsSubmissionSuccess;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//h3[@class=\"form-intro\"]")
	protected static List<WebElement> formsDirectionalCopies;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//button[@type=\"submit\"]")
	protected static List<WebElement> formSubmitButton;
	
	/* Visit Feedback Form */
	
	@FindBy(xpath="//*[@class='contactus-tab section']//select[@id=\"state-visit\"]")
	protected static WebElement form1SelectState;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//select[@id=\"center-visit\"]")
	protected static WebElement form1SelectCenter;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id=\"fullname-visit\"]")
	protected static WebElement form1FullNameInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id=\"phone-visit\"]")
	protected static WebElement form1PhoneInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id=\"email-visit\"]")
	protected static WebElement form1EmailInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//textarea[@id='message-visit']")
	protected static WebElement form1MessageInput;
	
	/* Career Form */
	
	@FindBy(xpath="//*[@class='contactus-tab section']//select[@id='state--career']")
	protected static WebElement form2SelectState;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//select[@id='job-career']")
	protected static WebElement form2SelectJobCategory;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='fullname-career']")
	protected static WebElement form2FullNameInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='phone-career']")
	protected static WebElement form2PhoneInput;

	@FindBy(xpath="//*[@class='contactus-tab section']//textarea[@id='message-career']")
	protected static WebElement form2MessageInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='email-career']")
	protected static WebElement form2EmailInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='applied-yes']")
	protected static WebElement form2RadioYes;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='applied-no']")
	protected static WebElement form2RadioNo;
	
	/* Employer Form */
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='meContactFormESFirstName']")
	protected static WebElement form3FirstNameInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='meContactFormESLastName']")
	protected static WebElement form3LastNameInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='meContactFormESPhone']")
	protected static WebElement form3PhoneInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='meContactFormESEmail']")
	protected static WebElement form3EmailInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='meContactFormESCompanyName']")
	protected static WebElement form3CompanyNameInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//textarea[@id='meContactFormESYourMessage']")
	protected static WebElement form3MessageInput;

	@FindBy(xpath="//*[@class='contactus-tab section']//select[@id='meContactFormESIndustry']")
	protected static WebElement form3SelectIndustry;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//select[@id='meContactFormESNumEmployees']")
	protected static WebElement form3SelectNoOfEmployees;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//select[@id='meContactFormESState']")
	protected static WebElement form3SelectState;
	
	
	/* Billing Form */
	
	@FindBy(xpath="//*[@class='contactus-tab section']//select[@id='state-billing']")
	protected static WebElement form4SelectState;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//select[@id='center-billing']")
	protected static WebElement form4SelectCenter;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='fullname-billing']")
	protected static WebElement form4FullNameInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='phone-billing']")
	protected static WebElement form4PhoneInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='email-billing']")
	protected static WebElement form4EmailInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//textarea[@id='message-billing']")
	protected static WebElement form4MessageInput;
	
	/* General Inquries */
	
	@FindBy(xpath="//*[@class='contactus-tab section']//select[@id='reason-general']")
	protected static WebElement form5SelectReason;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='fullname-general']")
	protected static WebElement form5FullNameInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='companyname-general']")
	protected static WebElement form5CompanyNameInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='phone-general']")
	protected static WebElement form5PhoneInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@id='email-general']")
	protected static WebElement form5EmailInput;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//textarea[@id='message-general']")
	protected static WebElement form5MessageInput;
	
	/* Distribution Lists */
	
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@name=\"billinglist\"]")
	protected static WebElement form4DistributionList;
	
	@FindBy(xpath="//*[@class='contactus-tab section']//input[@name=\"careerlist\"]")
	protected static WebElement form2DistributionList;
	
	
	public ContactUsTabMedex_page() {
		PageFactory.initElements(mydriver, this);
	}

	public static ArrayList<WebElement> assertForm1ErrorMessage() {
		formErrorMessages = new  ArrayList<>();
		formErrorMessages.add(getForm1FullNameInputError());
		formErrorMessages.add(getForm1MessageInputError());
		formErrorMessages.add(getForm1PhoneInputError());
		formErrorMessages.add(getForm1SelectCenterError());
		formErrorMessages.add(getForm1SelectStateError());
		return formErrorMessages;
	}
	
	
	
	
	public static ArrayList<WebElement> assertForm2ErrorMessage() {
		formErrorMessages = new  ArrayList<>();
		formErrorMessages.add(getForm2EmailInputError());
		formErrorMessages.add(getForm2FullNameInputError());
		formErrorMessages.add(getForm2MessageInputError());
		formErrorMessages.add(getForm2PhoneInputError());
		formErrorMessages.add(getForm2SelectJobCategoryError());
		formErrorMessages.add(getForm2SelectStateError());
		return formErrorMessages;
	}
	protected static ArrayList<WebElement> formErrorMessages ;
	public static ArrayList<WebElement> assertForm5ErrorMessage() {
		formErrorMessages = new  ArrayList<>();
		formErrorMessages.add(getForm5CompanyNameInputError());
		formErrorMessages.add(getForm5EmailInputError());
		formErrorMessages.add(getForm5FullNameInputError());
		formErrorMessages.add(getForm5MessageInputError());
		formErrorMessages.add(getForm5PhoneInputError());
		formErrorMessages.add(getForm5SelectReasonError());
		return formErrorMessages;
	}
	
	public static ArrayList<WebElement> assertForm3ErrorMessage() {
		formErrorMessages = new  ArrayList<>();
		formErrorMessages.add(getForm3CompanyNameInputError());
		formErrorMessages.add(getForm3EmailInputError());
		formErrorMessages.add(getForm3FirstNameInputError());
		formErrorMessages.add(getForm3LastNameInputError());
		formErrorMessages.add(getForm3MessageInputError());
		formErrorMessages.add(getForm3PhoneInputError());
		formErrorMessages.add(getForm3SelectIndustryError());
		formErrorMessages.add(getForm3SelectNoOfEmployeesError());
		formErrorMessages.add(getForm3SelectStateError());
		return formErrorMessages;
	}
	

	
	public static ArrayList<WebElement> assertForm4ErrorMessage() {
		formErrorMessages = new  ArrayList<>();
		formErrorMessages.add(getForm4EmailInputError());
		formErrorMessages.add(getForm4FullNameInputError());
		formErrorMessages.add(getForm4MessageInputError());
		formErrorMessages.add(getForm4PhoneInputError());
		formErrorMessages.add(getForm4SelectCenterError());
		formErrorMessages.add(getForm4SelectStateError());
		
		return formErrorMessages;
	}
	
	
	public static WebElement getForm1SelectStateError() {
		return form1SelectState.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm1SelectCenterError() {
		return form1SelectCenter.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm1FullNameInputError() {
		return form1FullNameInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm1PhoneInputError() {
		return form1PhoneInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm1MessageInputError() {
		return form1MessageInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm2SelectStateError() {
		return form2SelectState.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm2SelectJobCategoryError() {
		return form2SelectJobCategory.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm2FullNameInputError() {
		return form2FullNameInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm2PhoneInputError() {
		return form2PhoneInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm2MessageInputError() {
		return form2MessageInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm2EmailInputError() {
		return form2EmailInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm2RadioYesError() {
		return form2RadioYes.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm2RadioNoError() {
		return form2RadioNo.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm3FirstNameInputError() {
		return form3FirstNameInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm3LastNameInputError() {
		return form3LastNameInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm3PhoneInputError() {
		return form3PhoneInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm3EmailInputError() {
		return form3EmailInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm3CompanyNameInputError() {
		return form3CompanyNameInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm3MessageInputError() {
		return form3MessageInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm3SelectIndustryError() {
		return form3SelectIndustry.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm3SelectNoOfEmployeesError() {
		return form3SelectNoOfEmployees.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm3SelectStateError() {
		return form3SelectState.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm4SelectStateError() {
		return form4SelectState.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm4SelectCenterError() {
		return form4SelectCenter.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm4FullNameInputError() {
		
		return form4FullNameInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm4PhoneInputError() {
		return form4PhoneInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm4EmailInputError() {
		return form4EmailInput.findElement(By.xpath("//span[@class='error']"));
	}

	public static WebElement getForm4MessageInputError() {
		return form4MessageInput.findElement(By.xpath("//span[@class='error']"));
	}

	private static WebElement getForm5SelectReasonError() {
		return form5SelectReason.findElement(By.xpath("//span[@class='error']"));
	}

	private static WebElement getForm5FullNameInputError() {
		return form5FullNameInput.findElement(By.xpath("//span[@class='error']"));
	}

	private static WebElement getForm5CompanyNameInputError() {
		return form5CompanyNameInput.findElement(By.xpath("//span[@class='error']"));
	}

	private static WebElement getForm5PhoneInputError() {
		return form5PhoneInput.findElement(By.xpath("//span[@class='error']"));
	}

	private static WebElement getForm5EmailInputError() {
		return form5EmailInput.findElement(By.xpath("//span[@class='error']"));
	}

	private static WebElement getForm5MessageInputError() {
		return form5MessageInput.findElement(By.xpath("//span[@class='error']"));
	}

	
	protected void inputInvalidData(List<WebElement> formTab,int index ,Logger logger) {
		new ContactUsTabMedex_page().scrolltillvisibilityMedex(mydriver,formTab.get(index),logger);
		scrollToElement(mydriver, formTab.get(index), logger).click();
		scrollToElement(mydriver, formSubmitButton.get(index), logger).click();
	}
	protected static Select select;
	public static int getRandomOptionIndexFromList(WebElement element) {
		select = new Select(element);
	return	getRandomInteger(select.getOptions().size(), 1);
	}
	protected void inputValidDataForm1(List<WebElement> formTab,Logger logger) {
		
		new ContactUsTabMedex_page().scrolltillvisibilityMedex(mydriver,formTab.get(0),logger);
		scrollToElement(mydriver, formTab.get(0), logger).click();
		selectByOptionIndex(form1SelectState,getRandomOptionIndexFromList(form1SelectState) , logger);
		selectByOptionIndex(form1SelectCenter,getRandomOptionIndexFromList(form1SelectCenter) , logger);
		form1FullNameInput.sendKeys("Test User");
		form1PhoneInput.sendKeys("9898989898");
		form1EmailInput.sendKeys("test@user.com");
		form1MessageInput.sendKeys("Lorrem Ipsum Dummy Text");
		scrollToElement(mydriver, formSubmitButton.get(0), logger).click();
	}
	
	protected void inputValidDataForm2(List<WebElement> formTab,Logger logger) {
		
		new ContactUsTabMedex_page().scrolltillvisibilityMedex(mydriver,formTab.get(1),logger);
		scrollToElement(mydriver, formTab.get(1), logger).click();
		selectByOptionIndex(form2SelectState,getRandomOptionIndexFromList(form2SelectState) , logger);
		selectByOptionIndex(form2SelectJobCategory,getRandomOptionIndexFromList(form2SelectJobCategory) , logger);
		form2FullNameInput.sendKeys("Test User");
		form2PhoneInput.sendKeys("9898989898");
		form2EmailInput.sendKeys("test@user.com");
		form2MessageInput.sendKeys("Lorrem Ipsum Dummy Text");
		form2RadioYes.click();
		scrollToElement(mydriver, formSubmitButton.get(1), logger).click();
	}
	
	protected void inputValidDataForm3(List<WebElement> formTab,Logger logger) {
		
		new ContactUsTabMedex_page().scrolltillvisibilityMedex(mydriver,formTab.get(2),logger);
		scrollToElement(mydriver, formTab.get(2), logger).click();
		selectByOptionIndex(form3SelectIndustry,getRandomOptionIndexFromList(form3SelectIndustry) , logger);
		selectByOptionIndex(form3SelectNoOfEmployees,getRandomOptionIndexFromList(form3SelectNoOfEmployees) , logger);
		form3FirstNameInput.sendKeys("Test");
		form3LastNameInput.sendKeys("User");
		form3PhoneInput.sendKeys("9898989898");
		form3EmailInput.sendKeys("test@user.com");
		form3CompanyNameInput.sendKeys("Fake Company");
		form3MessageInput.sendKeys("Lorrem Ipsum Dummy Text");
		selectByOptionIndex(form3SelectState,getRandomOptionIndexFromList(form3SelectState) , logger);
		scrollToElement(mydriver, formSubmitButton.get(2), logger).click();
	}
	
	protected void inputValidDataForm4(List<WebElement> formTab,Logger logger) {
		
		new ContactUsTabMedex_page().scrolltillvisibilityMedex(mydriver,formTab.get(3),logger);
		scrollToElement(mydriver, formTab.get(3), logger).click();
		selectByOptionIndex(form4SelectState,getRandomOptionIndexFromList(form4SelectState) , logger);
		selectByOptionIndex(form4SelectCenter,getRandomOptionIndexFromList(form4SelectCenter) , logger);
		form4FullNameInput.sendKeys("Test User");
		form4PhoneInput.sendKeys("9898989898");
		form4EmailInput.sendKeys("test@user.com");
		form4MessageInput.sendKeys("Lorrem Ipsum Dummy Text");
		scrollToElement(mydriver, formSubmitButton.get(3), logger).click();
	}
	
	protected void inputValidDataForm5(List<WebElement> formTab,Logger logger) {
		
		new ContactUsTabMedex_page().scrolltillvisibilityMedex(mydriver,formTab.get(4),logger);
		scrollToElement(mydriver, formTab.get(4), logger).click();
		selectByOptionIndex(form5SelectReason,getRandomOptionIndexFromList(form5SelectReason) , logger);
		form5FullNameInput.sendKeys("Test User");
		form5PhoneInput.sendKeys("9898989898");
		form5EmailInput.sendKeys("test@user.com");
		form5MessageInput.sendKeys("Lorrem Ipsum Dummy Text");
		form5CompanyNameInput.sendKeys("Fake Company");
		scrollToElement(mydriver, formSubmitButton.get(4), logger).click();
	}
	

/*
 * This is to remove main header overlapping other elements
 * 
 * */	
//	protected  void scrolltillvisibilityMedex(mydriver,WebElement ele){
//		scrollToElement(mydriver, ele, logger);
//		try { ((JavascriptExecutor) mydriver).executeScript("return document.getElementsByClassName('header med-header sticky')[0].remove();");}
//		catch (WebDriverException e) {
//		}
//			scrollToElement(mydriver, ele, logger);
//				pleaseWait(1, logger);
//	}	
}
