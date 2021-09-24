package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class MutualFundTable_page extends Base{

	protected static WebDriver mydriver;

	@FindBy(xpath="//*[@class=\"search-mod-input\"]")
	protected static WebElement searchInput;

	@FindBy(xpath="//*[@value=\"Submit\"]")
	protected static WebElement searchSubmitButton;
	
	@FindBy(xpath="//*[@type=\"reset\"]")
	protected static WebElement searchClearButton;

	@FindBy(xpath="//*[@class=\"background-lines\"]/h2")
	protected static WebElement searchDivideHeading;

	@FindBy(xpath="//*[@name=\"category\"]")
	protected static WebElement categoryFilter;
	
	@FindBy(xpath="//*[@name=\"asset_class\"]")
	protected static WebElement assetClassFilter;
	
	@FindBy(xpath="//*[@class=\"button selects-submit js--cat-ass-submit\"]")
	protected static WebElement filterSubmitButton;
	protected static String searchTagsClose = "//*[@class=\"search-mod-results-term\"]/i";
	protected static String searchTags = "//*[@class=\"search-mod-results\"]/ul/li/span";
	protected static String mutualFundColumnHeader = "//*[@data-sort_name=\"mutualFund\"]";
	protected static String assetClassColumnHeader = "//*[@data-sort_name=\"assetClass\"]";
	protected static String expenseRatioColumnHeader = "//*[@data-sort_name=\"expenseRatio\"]";
	protected static String ratingColumnHeader = "//*[@data-sort_name=\"rating\"]";
	protected static String yearToDateColumnHeader = "//*[@data-sort_name=\"ytd\"]";
	protected static String fundElement = "//*[@class=\"mutual-fund__name\"]";
	//*[@class="mutual-fund__name"]   +    //ancestor::tr/preceding-sibling::tr[2]/td[@class="dmp-container__subheader-span category-title"]
	// element name                   +     //copy above to get its section title
	
	protected static String  equityElements = "//*[@data-category=\"Equity\"]//*[@class=\"mutual-fund__name\"]";
	protected static String  lifeStyleElements = "//*[@data-category=\"Lifestyle\"]//*[@class=\"mutual-fund__name\"]";
	protected static String  targetDateElements = "//*[@data-category=\"Target Date\"]//*[@class=\"mutual-fund__name\"]";
	protected static String  fixedIncomeElements = "//*[@data-category=\"Fixed Income\"]//*[@class=\"mutual-fund__name\"]";
	protected static String  specialityElements = "//*[@data-category=\"Specialty\"]//*[@class=\"mutual-fund__name\"]";
	
	public static String getSectionHeaderFromElement(String fundElementXpath) {
		String xpathToSectionName = fundElementXpath+"//ancestor::tr/preceding-sibling::tr[2]/td[@class=\"dmp-container__subheader-span category-title\"]";
		return xpathToSectionName;
	}
	public MutualFundTable_page() {
	PageFactory.initElements(mydriver, this);
	}
}
