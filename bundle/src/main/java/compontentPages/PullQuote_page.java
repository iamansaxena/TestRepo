package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class PullQuote_page extends Base {
	protected static WebDriver mydriver;
	
	protected static String feeds = "//*[@class='pull-quote section']";
	
	
	@FindBy(xpath = "//*[@class='pull-quote section']")
	protected static WebElement pullQuoteSection;
	
	@FindBy(xpath = "//*[@class='pq']/p")
	protected static WebElement blockQuote;
	
	@FindBy(xpath = "//*[@class='pq']/cite")
	protected static WebElement blockQuoteCitation;
	
	@FindBy(xpath = "//*[@class='pq']/a")
	protected static WebElement blockQuotelink;
	
	/*protected static String blockQuotelink = "//*[@class='pq']/a";*/
	
	
	
	public PullQuote_page() {
		PageFactory.initElements(mydriver, this);
	}

}
