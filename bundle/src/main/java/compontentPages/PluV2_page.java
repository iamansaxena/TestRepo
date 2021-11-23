package compontentPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class PluV2_page extends Base {
	Base obj = new  Base();
	protected static String ComponentUrl;
	protected static WebDriver mydriver;
	@FindBy(xpath = "//*[@class=\"pluv2-results__info pluv2__col\"]//span")
	protected static WebElement totalResultsText;
	protected static String errorValidationMsgText = "Enter a valid ZIP code or City and State.";

	@FindBy(xpath = "//*[@class=\"pluv2-search__heading pluv2-search__content \"]")
	protected static WebElement searchCardHeader;

	@FindBy(xpath = "//*[@id=\"search-field\"]")
	protected static WebElement searchField;

	@FindBy(xpath = "//*[@id=\"search-radius\"]")
	protected static WebElement selectRadius;

	@FindBy(xpath = "//*[@class=\"button button--full-width js-submit-form-btn\"]")
	protected static WebElement searchButton;

	@FindBy(xpath = "//*[@class=\"pluv2-intake-component__banner-image pluv2__col\"]")
	protected static WebElement bannerImage;

	@FindBy(xpath = "//*[@for=\"search-field\"]")
	protected static WebElement topSearchFieldLabel;

	@FindBy(xpath = "//*[@id=\"pluv2-search__helper-text\"]")
	protected static WebElement searchHelperLabel;
	@FindBy(xpath = "//*[@id=\"pluv2-search__helper-text\"]")
	protected static WebElement errorValidationMsg;

	@FindBy(xpath = "//*[@class=\"pluv2-checkbox__icon fa fa-check-square\"]")
	protected static WebElement mainSectionCheckbox;

	@FindBy(xpath = "//*[@class=\"pluv2-options__more-label\"]")
	protected static WebElement moreSearchDD;

	@FindBy(xpath = "//*[@class=\"pluv2-options__few-label\"]")
	protected static WebElement fewSearchDD;

	@FindBy(xpath = "(//*[@class=\"pluv2-options__radio-label pluv2-radio__label\"])[1]")
	protected static WebElement findProviderMainLabelRB;

	@FindBy(xpath = "(//*[@class=\"pluv2-options__radio-label pluv2-radio__label\"])[2]")
	protected static WebElement findLocationMainLabelRB;

	@FindBy(xpath = "(//*[@class=\"pluv2-options__radio-label pluv2-radio__label\"])[1]//em")
	protected static WebElement findProviderSubLabel;

	@FindBy(xpath = "(//*[@class=\"pluv2-options__radio-label pluv2-radio__label\"])[2]//em")
	protected static WebElement findLocationSubLabel;

	@FindBy(xpath = "(//*[@class=\"pluv2-options__label\"])[1]")
	protected static WebElement findProviderSearchFieldLabel;

	@FindBy(xpath = "(//*[@class=\"pluv2-options__label\"])[2]")
	protected static WebElement findLocationSearchFieldLabel;

	@FindBy(xpath = "//*[@name=\"providers-name\"]")
	protected static WebElement findProviderSearchField;

	@FindBy(xpath = "//*[@name=\"locations-name\"]")
	protected static WebElement findLocationSearchField;

	@FindBy(xpath = "(//*[@class=\"pluv2-options__label pluv2-options__list-label\"])[1]")
	protected static WebElement findProviderOptionSectionLabel;

	@FindBy(xpath = "(//*[@class=\"pluv2-options__label pluv2-options__list-label\"])[2]")
	protected static WebElement findLocationOptionSectionLabel;

	@FindBy(xpath = "(//*[@class=\"pluv2-checkbox__icon fa fa-square-o\"])[2]")
	protected static WebElement findLocationOption1CB;

	@FindBy(xpath = "(//*[@class=\"pluv2-radio__label\"])[1]")
	protected static WebElement findProviderOption1CB;
	@FindBy(xpath = "(//*[@class=\"pluv2-radio__label\"])[2]")
	protected static WebElement findProviderOption2CB;
	@FindBy(xpath = "(//*[@class=\"pluv2-radio__label\"])[3]")
	protected static WebElement findProviderOption3CB;

	/*
	 * Search result
	 */

	@FindBy(xpath = "//button[@id=\"providers-labelledby\"]")
	protected static WebElement providerTab;
	@FindBy(xpath = "//button[@id=\"locations-labelledby\"]")
	protected static WebElement locationTab;

	@FindBy(xpath = "//*[@class=\"pluv2-serp__heading\"]")
	protected static WebElement resultsPageHeader;

	@FindBy(xpath = "(//*[@class=\"pluv2-tabs__inner\"])[1]")
	protected static WebElement providerTabDesc;

	@FindBy(xpath = "(//*[@class=\"pluv2-tabs__inner\"])[2]")
	protected static WebElement locationTabDesc;

	@FindBy(xpath = "//*[@id=\"results-sort\"]")
	protected static WebElement resultSort;

	@FindBy(xpath = "//*[@id=\"accordion-specialties\"]")
	protected static WebElement resultFilterSpeciality;

	@FindBy(xpath = "//*[@id=\"accordion-gender\"]")
	protected static WebElement resultFilterGender;

	@FindBy(xpath = "//*[@id=\"accordion-languages\"]")
	protected static WebElement resultFilterLanguages;
	@FindBy(xpath = "//*[@class=\"pluv2-pagination__prev\"]//button")
	protected static WebElement paginationPrev;
	@FindBy(xpath = "//*[@class=\"pluv2-pagination__next\"]//button")
	protected static WebElement paginationNext;

	@FindBy(xpath = "//*[@class=\"pluv2-results__info pluv2__col\"]//span")
	protected static WebElement totalProviderResultItem;

	// Specialities-filterDynamic xpath for cb =
	// //*[@name="filter-specialties-0"]//i

	// protected static String
	// specialitiesOptionLabels="//*[@id=\"accordion-drawer-specialties\"]//*[@class=\"pluv2-checkbox__label\"]";

	protected static String specialitiesOptionCBs = "//*[@id=\"accordion-drawer-specialties\"]//*[contains(@name,\"filter-specialties\")]//i[@class=\"pluv2-checkbox__icon fa fa-square-o\"]";

	protected static String languageOptionLabels = "//*[@id=\"accordion-drawer-languages\"]//*[@class=\"pluv2-checkbox__label\"]//span";
	protected static String genderOptionLabels = "//*[@class=\"pluv2-filters__gender \"]//label//span";

	@FindBy(xpath = "//*[@id=\"accordion-drawer-languages\"]//*[contains(@name,\"filter-languages\")]//i[@class=\"pluv2-checkbox__icon fa fa-square-o\"]")
	protected static WebElement languageOptionCBs;

	protected static String resultTags = "(//*[@class=\"pluv2-pills__inner\"])//*[@class=\"pluv2-pills__btn js-toggle-pill-btn\"]";

	/*
	 * Provider cards
	 */
	protected static String resultProviderCardNames = "(//*[@class=\"pluv2-team__member\"]//h3)";

	protected static String resultProviderCardSpecialities = "//*[@class=\"pluv2-team__specialties\"]";

	@FindBy(xpath = "//*[@class=\"pluv2-team__status\"]")
	protected static WebElement resultProviderCardMainChecboxFiltrations;

	protected static String resultProviderCardAddresses = "//*[@class=\"pluv2-team__address\"]";;

	protected static String resultProviderCardDistances = "//*[@class=\"pluv2-team__distance\"]";

	protected static String resultProviderCardImageContainers = "//*[@class=\"pluv2-team__photo pluv2__col pluv2__flex justify-content-end\"]";

	// DYNAMIC XPATH TO VERIFY IMAGE FOR EACH CARD AT RUNTIME :::
	// (//*[@class="pluv2-team__photo pluv2__col pluv2__flex
	// justify-content-end"])[2]//img;

	protected static String resultLocationCardNames = "//*[@class=\"pluv2-locations__card-heading\"]";

	/*
	 * location results
	 */

	protected static String clusterIcons = "//*//div[3]//*[contains(@style,'position:')]//span";
	protected static String spotLights = "//img[contains(@src,'https://maps.gstatic.com/mapfiles/api-3/images/spotlight-poi2_hdpi.png')]";

	@FindBy(xpath = "//*[@class=\"pluv2-map__heading pluv2-map__anchor\"]")
	protected static WebElement mapSpolightPopup;

	/* Location Details page */

	@FindBy(xpath = "//*[@class=\"pluv2-map__link pluv2__colauto\"]//a")
	protected static WebElement locationLink;

	protected static String locationCtaLinks = "//*[@class=\"pluv2-section__inner\"]/a";
	protected static String locationSpecialities = "//*[@class=\"pluv2-specialties pluv2-section\"]//strong";
	protected static String locationLanguages = "//*[@class=\"pluv2-location-languages pluv2-section\"]//strong";
	protected static String locationMoreLinks = "//*[@class=\"pluv2-more__link\"]/a";
	@FindBy(xpath = "//*[@class=\"pluv2-address__link\"]/a")
	protected static WebElement locationAddress;
	@FindBy(xpath = "//*[@class=\"pluv2-heading\"]")
	protected static WebElement locationHeader;
	@FindBy(xpath = "//*[@class=\"pluv2-parking__list\"]//ul//li")
	protected static WebElement locationParkingDetail;
	@FindBy(xpath = "//*[@id=\"pluv2-map__interactive\"]")
	protected static WebElement locationMap;
	protected static String locationContactOptions = "//*[@class=\"pluv2-contact pluv2-section\"]//h2/following-sibling::div";
	protected static String locationAboutParas = "//*[@class=\"pluv2-about-us pluv2-section pluv2-about-us__tablet\"]//*[@class=\"pluv2-about-us__section\"]";
	protected static String careTeamCards = "//*[@class=\"pluv2-team__member\"]//a"; // -1
	protected static String careTeamNames = "//*[@class=\"pluv2-team__member\"]//a//h3";
	protected static String locationServices = "//*[@class=\"pluv2-services pluv2-section\"]//strong";
	@FindBy(xpath = "//*[@class=\"pluv2-network-membership pluv2-section\"]")
	protected static WebElement networkMembershipSection;
	@FindBy(xpath = "//*[@class=\"pluv2-network-membership__list\"]/i/following-sibling::div")
	protected static WebElement networkMembershipSubHeader;
	protected static String locationNetworkMemberships = "//*[@class=\"pluv2-network-membership__list\"]/i/following-sibling::ul//strong";

	/*
	 * provider details page
	 * 
	 */

	@FindBy(xpath = "//*[@class=\"pluv2-main__heading\"]")
	protected static WebElement providerNameDetail;
	@FindBy(xpath = "//*[@class=\"pluv2-details__specialties-heading\"]")
	protected static WebElement providerSpecialityDetail;
	@FindBy(xpath = "//*[@class=\"pluv2-profile pluv2-section pluv2-profile__tablet-img\"]//*[@class=\"pluv2-profile__image-container\"]")
	protected static WebElement providerImageDetail;
	@FindBy(xpath = "//*[@class=\"pluv2-details__status-heading\"]")
	protected static WebElement providerMainCheckConditionDetail;
	@FindBy(xpath = "//*[@class=\"pluv2__col pluv2-details__board-certifications\"]")
	protected static WebElement providerBoardCertSectionDetail;
	protected static String providerBoardCertListDetails = "//*[@class=\"pluv2-details__board-cert-list\"]//strong";
	@FindBy(xpath = "//*[@class=\"pluv2-overview pluv2-section\"]")
	protected static WebElement providerOverviewSectionDetail;
	protected static String providerOverviewSubHeadingDetails = "//*[@class=\"pluv2-overview__section\"]//h3//following-sibling::p";
	protected static String providerOverviewSubSectionParaDetails = "//*[@class=\"pluv2-overview__section\"]//h3//following-sibling::p";
	@FindBy(xpath = "//*[@class=\"pluv2-overview__bio-button\"]")
	protected static WebElement providerBioExpandButtonDetail;
	protected static String providerOverviewExpandedParaDetails = "//div[@id=\"bio-1\"]/p";
	@FindBy(xpath = "//*[@class=\"pluv2-qualifications pluv2-section\"]")
	protected static WebElement providerQualificationSectionDetail;
	protected static String providerQualificationSubHeadingsDetails = "//*[@class=\"pluv2-qualifications__col\"]";
	protected static String providerQualificationParaDetails = "//*[@class=\"pluv2-qualifications__item\"]";
	@FindBy(xpath = "//*[@class=\"pluv2-qualifications__more-text\"]")
	protected static WebElement providerQualificationExpandButtonDetail;
	@FindBy(xpath = "//*[@class=\"pluv2-health-plan pluv2__col\"]")
	protected static WebElement providerHealthPlanSectionDetail;
	@FindBy(xpath = "//*[@class=\"pluv2-health-plan__heading js-toggle-mobile-accordion\"]")
	protected static WebElement providerHealthPlanSectionHeaderDetail;
	@FindBy(xpath = "//*[@class=\"pluv2-network__heading js-toggle-mobile-accordion\"]")
	protected static WebElement providerNetworkSectionHeaderDetail;
	protected static String providerHealthPlanListDetails = "//*[@class=\"pluv2-health-plan__list-item\"]";
	@FindBy(xpath = "//*[@class=\"pluv2-network pluv2__col\"]")
	protected static WebElement providerNetworkMemberSectionDetail;
	protected static String providerNetworkMembershipListDetails = "//*[@class=\"pluv2-network__list-item\"]";
	@FindBy(xpath = "//*[@class=\"pluv2-affiliations pluv2__col\"]")
	protected static WebElement providerHospAffiliationSectionDetail;
	@FindBy(xpath = "//*[@class=\"pluv2-affiliations__heading js-toggle-mobile-accordion\"]")
	protected static WebElement providerHospAffiliationSectionHeaderDetail;

	protected static String providerHospAffiliationListSectionDetail = "//*[@class=\"pluv2-affiliations__list-item\"]";
	@FindBy(xpath = "//*[@class=\"pluv2-languages pluv2__col\"]")
	protected static WebElement providerLanguageSectionDetail;
	protected static String providerLanguagesListDetail = "//*[@class=\"pluv2-languages__item\"]//strong";
	@FindBy(xpath = "//*[@class=\"pluv2-gender pluv2__col\"]")
	protected static WebElement providerGenderSectionDetail;
	@FindBy(xpath = "//*[@class=\"pluv2-gender__item\"]")
	protected static WebElement providerGenderDetail;
	@FindBy(xpath = "//*[@class=\"pluv2-more pluv2-section pluv2-profile__tablet-img\"]")
	protected static WebElement providerLinksSectionDetail;
	protected static String providerLinksSectionListDetail = "//*[@class=\"pluv2-more pluv2-section pluv2-profile__tablet-img\"]//a";

	protected static String providerContactOptionsListDetails = "//*[@class=\"pluv2-contact pluv2-section\"]//h2/following-sibling::div";
	@FindBy(xpath = "//*[@class=\"pluv2-contact__icon fa fa-phone\"]/parent::div")
	protected static WebElement providerPhoneNoDetail;
	protected static String providerFaxNosDetail = "//*[@class=\"pluv2-contact__icon fa fa-fax\"]/parent::div/ul//strong";
	@FindBy(xpath = "//*[@class=\"pluv2-locations pluv2-section\"]")
	protected static WebElement providerLocationSectionDetail;
	protected static String providerLocationNameDetail = "//*[@class=\"pluv2-locations__card-heading\"]";
	@FindBy(xpath = "//*[@class=\"pluv2-more-details pluv2-section\"]")
	protected static WebElement providerMoreDetailSectionDetail;

	public PluV2_page() {
		PageFactory.initElements(mydriver, this);
//		ComponentUrl = "http://apsrs5642:8080/content/AutomationDirectory/plu-v2-feature/intake-form-prohealth-ct.html";
	}

	public static void selectRadius(int value) throws NoSuchElementException, StaleElementReferenceException {
		mydriver.findElement(By.xpath("//*[@id=\"search-radius\"]")).click();
		mydriver.findElement(By.xpath("//*[@id=\"search-radius\"]//option[@value='" + value + "']")).click();
	}

	public  void getBaseSerpPage(String urlToIntakeForm) {
		mydriver.get(urlToIntakeForm);
		obj.scrollToElement(mydriver, mydriver.findElement(By.xpath("//*[@id=\"pluv2-search__helper-text\"]")), logger);
		String input = searchHelperLabel.getText().split("or ")[1];
		obj.scrollToElement(mydriver, searchField, logger);
		searchField.click();
		searchField.click();
		searchField.sendKeys(input);

		searchButton.click();
		obj.scrollToElement(mydriver, resultsPageHeader, logger);

	}

	public  void getLocationDetailsPage(String urlToIntakeForm) {
		getBaseSerpPage(urlToIntakeForm);
		locationTab.click();
		obj.pleaseWait(1, logger);

		List<WebElement> locationCards = mydriver.findElements(By.xpath(resultLocationCardNames));
		int maxCard = locationCards.size() - 1;
		locationCards.get(getRandomInteger(maxCard, 0)).click();
		obj.scrollToElement(mydriver, locationHeader, logger);

	}

	public void getProviderDetailsPage(String urlToIntakeForm) {
		getBaseSerpPage(urlToIntakeForm);
		providerTab.click();
		obj.pleaseWait(1, logger);

		List<WebElement> providerCards = mydriver.findElements(By.xpath(resultProviderCardNames));
		int maxCard = providerCards.size() - 1;
		providerCards.get(getRandomInteger(maxCard, 0)).click();
		obj.scrollToElement(mydriver, providerNameDetail, logger);
	}

}
