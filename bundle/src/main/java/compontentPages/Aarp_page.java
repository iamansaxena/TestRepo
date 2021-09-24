package compontentPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import core.Base;

public class Aarp_page extends Base {
protected static WebDriver mydriver=null;
@FindBy(xpath = "//*[@class=\"hsa-box-content\"]/img")
	protected static WebElement boxImage;

	@FindBy(xpath = "//*[@class=\"hsa-box-content\"]/*[@class=\"hsa-box-content-heading\"]")
	protected static WebElement firstPageTitle;
	@FindBy(xpath = "//*[@class=\"hsa-box-content\"]/*[@class=\"hsa-box-content-description\"]/following-sibling::p[1]")
	protected static WebElement firstPageDescription;

	@FindBy(xpath = "//*[@role=\"tablist\"]")
	protected static WebElement navigationTabList;

	@FindBy(xpath = "//*[@class=\"hsa-content-intro-heading\"]")
	protected static WebElement basicTabHeading; 

	@FindBy(xpath = "//*[@class=\"hsa-content-intro-heading\"]/following-sibling::p[2]")
	protected static WebElement directionalCopy;

	@FindBy(xpath = "//*[@class=\"hsa-content-intro-subheading\"]")
	protected static WebElement firstPageSubHeading;

	@FindBy(xpath = "//*[@for=\"gender\"]")
	protected static WebElement individualGenderFilterLabel;
	@FindBy(xpath = "//*[@for=\"pt_gender\"]")
	protected static WebElement partnerGenderFilterLabel;

	@FindBy(xpath = "//*[@for=\"age\"]")
	protected static WebElement individualAgeFilterLabel;

	@FindBy(xpath = "//*[@id=\"individual-age\"]")
	protected static WebElement inputIndividualAgeFilterLabel;

	@FindBy(xpath = "//*[@id=\"pt_age\"]")
	protected static WebElement inputPartnerAgeFilterLabel;

	@FindBy(xpath = "//*[@for=\"pt_age\"]")
	protected static WebElement partnerAgeFilterLabel;

	@FindBy(xpath = "//*[@for=\"individual-gender-female\"]")
	protected static WebElement individualFemaleFilterLabel;
	@FindBy(xpath = "//*[@id=\"includePartner\"]")
	protected static WebElement includePartnerCheckbox;

	@FindBy(xpath = "//*[@for=\"partner-gender-female\"]")
	protected static WebElement partnerFemaleFilterLabel;

	@FindBy(xpath = "//*[@for=\"individual-gender-male\"]")
	protected static WebElement individualMaleFilterLabel;

	@FindBy(xpath = "//*[@for=\"partner-gender-male\"]")
	protected static WebElement partnerMaleFilterLabel;

	@FindBy(xpath = "//*[@for=\"retired\"]")
	protected static WebElement individualRetiredFilterLabel;

	@FindBy(xpath = "//*[@for=\"individual-retired-yes\"]")
	protected static WebElement individualRetiredYes;

	@FindBy(xpath = "//*[@for=\"individual-retired-no\"]")
	protected static WebElement individualRetiredNo;

	@FindBy(xpath = "//*[@for=\"partner-retired-no\"]")
	protected static WebElement partnerRetiredNo;

	@FindBy(xpath = "//*[@class=\" hsa-divider-top hsa-terms\"]/p/strong")
	protected static WebElement termsLabel;

	@FindBy(xpath = "//*[@class=\" hsa-divider-top hsa-terms\"]/p/strong/a")
	protected static WebElement termsLink;

	@FindBy(xpath = "//button[contains(@class,\"agreed\")]")
	protected static WebElement termsAgreed;

	@FindBy(xpath = "//*[@for=\"partner-retired-yes\"]")
	protected static WebElement partnerRetiredYes;

	@FindBy(xpath = "//*[@for=\"pt_retired\"]")
	protected static WebElement partnerRetiredFilterLabel;

	@FindBy(xpath = "//button[contains(@class,\"next\")]")
	protected static WebElement nextButton;
	@FindBy(xpath = "//*[contains(@class,\"page-prev\")]")
	protected static WebElement prevButton;
	///////////// PAGE 2
	@FindBy(xpath = "//div[@class=\"hsa-details\"]//*[@class=\"hsa-user-details\"]/li[1]/span")
	protected static WebElement userDetailGenderP2;

	@FindBy(xpath = "//div[@class=\"hsa-details\"]//*[@class=\"hsa-user-details\"]/li[2]/span")
	protected static WebElement userDetailAgeP2;

	@FindBy(xpath = "//*[@id=\"heightFeet\"]")
	protected static WebElement inputHeightFt;

	@FindBy(xpath = "//*[@id=\"heightFeet\"]/following-sibling::span[@class=\"error-msg error-hidden\"]")
	protected static WebElement heightErrorMsg;

	@FindBy(xpath = "//*[@id=\"heightInches\"]")
	protected static WebElement inputHeightInch;

	@FindBy(xpath = "//*[@id=\"weight\"]")
	protected static WebElement inputWeight;

	@FindBy(xpath = "//*[@id=\"weight\"]/following-sibling::span[@class=\"error-msg error-hidden\"]")
	protected static WebElement weightErrorMsg;

	@FindBy(xpath = "//*[@for=\"toggle-smoker-no\"]")
	protected static WebElement smokerButtonYes;

	@FindBy(xpath = "//*[@for=\"toggle-smoker-no\"]")
	protected static WebElement smokerButtonNo;

	@FindBy(xpath = "//*[@id=\"toggle-smoker-no\"]/following-sibling::span[@class=\"error-msg error-msg-tgl error-hidden\"]")
	protected static WebElement smokerErrorMsg;

	@FindBy(xpath = "//*[@id=\"state\"]")
	protected static WebElement selectState;

	@FindBy(xpath = "//*[@id=\"state\"]/parent::div/following-sibling::span")
	protected static WebElement selectStateErrorMsg;

	@FindBy(xpath = "//*[@id=\"lifeExpectancy\"]")
	protected static WebElement inputAgeExpectancy;

	@FindBy(xpath = "//*[@id=\"lifeExpectancy\"]/following-sibling::span[contains(@class,\"error-msg less-than-120\")]")
	protected static WebElement ageExpectancyErrorMsg;

	@FindBy(xpath = "//*[@id=\"retirementAge\"]")
	protected static WebElement inputRetirementAge;
	//////////////////
	@FindBy(xpath = "//*[@id=\"pt_heightFeet\"]")
	protected static WebElement partnerInputHeightFt;

	@FindBy(xpath = "//*[@id=\"pt_heightFeet\"]/following-sibling::span[@class=\"error-msg error-hidden\"]")
	protected static WebElement partnerHeightErrorMsg;

	@FindBy(xpath = "//*[@id=\"pt_heightInches\"]")
	protected static WebElement partnerInputHeightInch;

	@FindBy(xpath = "//*[@id=\"pt_weight\"]")
	protected static WebElement partnerInputWeight;

	@FindBy(xpath = "//*[@id=\"pt_weight\"]/following-sibling::span[@class=\"error-msg error-hidden\"]")
	protected static WebElement partnerWeightErrorMsg;

	@FindBy(xpath = "//*[@for=\"pt_toggle-smoker-no\"]")
	protected static WebElement partnerSmokerButtonYes;

	@FindBy(xpath = "//*[@for=\"pt_toggle-smoker-no\"]")
	protected static WebElement partnerSmokerButtonNo;

	@FindBy(xpath = "//*[@id=\"pt_toggle-smoker-no\"]/following-sibling::span[@class=\"error-msg error-msg-tgl error-hidden\"]")
	protected static WebElement partnerSmokerErrorMsg;

	@FindBy(xpath = "//*[@id=\"pt_state\"]")
	protected static WebElement partnerSelectState;

	@FindBy(xpath = "//*[@id=\"pt_state\"]/parent::div/following-sibling::span")
	protected static WebElement partnerSelectStateErrorMsg;

	@FindBy(xpath = "//*[@id=\"pt_lifeExpectancy\"]")
	protected static WebElement partnerInputAgeExpectancy;

	@FindBy(xpath = "//*[@id=\"pt_lifeExpectancy\"]/following-sibling::span[contains(@class,\"error\")]")
	protected static WebElement partnerAgeExpectancyErrorMsg;

	@FindBy(xpath = "//*[@class=\"c3-zoom-rect\"]")
	protected static WebElement partnerInputRetirementAge;

	@FindBy(xpath = "//*[@class=\"c3-zoom-rect\"]")
	protected static WebElement graph;

	@FindBy(xpath = "//*[@class=\"hsachart-total-health-care-costs\"]")
	protected static WebElement totalHealthCosts;

	@FindBy(xpath = "//*[@class=\"hsachart-covered-by-medicare\"]")
	protected static WebElement costCoveredByMedicare;

	@FindBy(xpath = "//*[@class=\"hsachart-estimated-shortage\"]")
	protected static WebElement estimatedShortage;
	protected static String removeConditionButton = "//*[@class=\"hsa-button rm-condition\"]/span[1]";
	protected static String retiredErrorMsg = "//*[contains(@for,\"retired-no\")]//following-sibling::span[@class=\"error-msg error-msg-tgl error-hidden\"]";
	protected static String ageErrorMsg = "//*[contains(@id,\"age\")]//following-sibling::span[@class=\"error-msg error-hidden\"]";
	protected static String genderErrorMsg = "//*[contains(@id,\"gender\")]//following-sibling::span[@class=\"error-msg error-msg-tgl error-hidden\"]";

	@FindBy(xpath = "(//button[contains(@class,\"hsa-button hsa-button-sm hsa-button-secondary\" ) ])[1]")
	protected static WebElement secondPageUpdateCostButton;
	@FindBy(xpath = "(//button[contains(@class,\"hsa-button hsa-button-sm hsa-button-secondary hsa-button-right\" ) ])[1]")
	protected static WebElement thirdPageUpdateCostButton;
	@FindBy(xpath = "(//button[contains(@class,\"hsa-button hsa-button-sm hsa-button-secondary\" ) ])[7]")
	protected static WebElement fourthPageUpdateCostButton;
	protected static String selectIndividualConditions = "//*[@name=\"conditionsSelect\"]/option";
	protected static String selectPartnerConditions = "//*[@name=\"pt_conditionsSelect\"]/option";

	@FindBy(xpath = "//*[@class=\"hsa-button hsa-button-secondary condition-add\"]")
	protected static WebElement addIndividualConditionButton;

	@FindBy(xpath = "//*[@class=\"hsa-button hsa-button-secondary pt_condition-add\"]")
	protected static WebElement addPartnerConditionButton;

	protected static String selectedConditions = "//*[@class=\"hsa-conditions-select\"]/span[1]";

	protected static String SelectedConditionsWithRadio = "//*[@class=\"hsa-conditions-select\"]/fieldset/input[@type=\"radio\"][1]";

	/////// PAGE 3

	@FindBy(xpath = "//*[contains(@class,\"get-healthier__item--list\") and not(contains(@class,\"hidden\"))]")
	protected static WebElement healthCondition;
	
	@FindBy(xpath = "//*[contains(@class,\"get-healthier__item--list\") and not(contains(@class,\"hidden\"))]/input")
	protected static WebElement healthConditionCheckBox;

	@FindBy(xpath = "//*[@for=\"pt_have-hsa-yes\"]")
	protected static WebElement partnerHaveHsaYes;

	@FindBy(xpath = "//*[@for=\"have-hsa-no\"]")
	protected static WebElement individualHaveHsaNo;

	@FindBy(xpath = "//*[@for=\"pt_have-hsa-no\"]")
	protected static WebElement partnerHaveHsaNo;

	@FindBy(xpath = "//*[@for=\"account-type-family\"]/following-sibling::label")
	protected static WebElement individualAccountTypeFamily;

	@FindBy(xpath = "//*[@id=\"account-type-individual\"]/following-sibling::label")
	protected static WebElement individualAccountTypeIndividual;

	@FindBy(xpath = "//*[@for=\"pt_account-type-family\"]/following-sibling::label")
	protected static WebElement partnerAccountTypeFamily;

	@FindBy(xpath = "//*[@id=\"account-type-individual\"]/following-sibling::label")
	protected static WebElement partnerAccountTypeIndividual;

	@FindBy(xpath = "//*[@id=\"current-hsa-balance\"]")
	protected static WebElement individualInputHsaBalance;

	@FindBy(xpath = "//*[@id=\"annual-contribution\"]")
	protected static WebElement individualInputPlannedContribution;

	@FindBy(xpath = "//*[@id=\"annual-withdrawl\"]")
	protected static WebElement partnerInputPlannedContribution;

	@FindBy(xpath = "//*[@id=\"pt_annual-withdrawl\"]")
	protected static WebElement partnerInputWithdrawl;

	@FindBy(xpath = "//*[@id=\"annual-withdrawl\"]")
	protected static WebElement individualInputWithdrawl;

	@FindBy(xpath = "//*[@id=\"annual-return\"]")
	protected static WebElement individualInputAnnualReturn;

	@FindBy(xpath = "//*[@id=\"pt_annual-return\"]")
	protected static WebElement partnerInputAnnualReturn;

	@FindBy(xpath = "//*[@class=\"hsachart-paid-from-hsa\"]")
	protected static WebElement paidFromHsaAmount;

	@FindBy(xpath = "(//*[@class=\"icon-circle\"])[6]/parent::button")
	protected static WebElement fifthPageNavigationTab;

	public Aarp_page() {
		PageFactory.initElements(mydriver, this);
	}

	protected  void inputValidDataP1(Logger logger, boolean moveToNextPage) {
		scrollToElement(mydriver,inputIndividualAgeFilterLabel, logger);
		inputIndividualAgeFilterLabel.sendKeys("19");
		individualFemaleFilterLabel.click();
		individualRetiredYes.click();
		termsAgreed.click();
		if (moveToNextPage == true) {
			getVisibility(mydriver,nextButton, 2);
			nextButton.click();
			scrollToElement(mydriver,navigationTabList, logger);
			getVisibility(mydriver,userDetailAgeP2, 5);
			logger.info("User landed on page 2");
		}
		
		pleaseWait(1, logger);
		
		
	}

	protected  void inputValidDataP2(Logger logger, boolean moveToNextPage) {
		;
		scrollToElement(mydriver,navigationTabList, logger);
		inputHeightFt.sendKeys("5");
		inputHeightInch.sendKeys("10");
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inputWeight.sendKeys("160");
		smokerButtonNo.click();
		Select select = new Select(selectState);
		select.getOptions().get(2).click();
		if (moveToNextPage == true) {
			nextButton.click();
			logger.info("User landed on page 3");
		}
	}
	protected  void inputValidDataP3(Logger logger) {
		;
		scrollToElement(mydriver,navigationTabList, logger);
		scrollToElement(mydriver,nextButton, logger);
		nextButton.click();
		logger.info("User landed on page 4");
		scrollToElement(mydriver,navigationTabList, logger);
	}
	/*public static void scrollToElementWithoutWait(WebDriver mydriver, WebElement element) {
		((JavascriptExecutor) mydriver).executeScript("arguments[0].scrollIntoView(true);", element);

		focusElement(mydriver, element);
	}*/
	}
 