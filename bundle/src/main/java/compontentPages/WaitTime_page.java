package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class WaitTime_page extends Base{

	protected static WebDriver mydriver ;
	
	@FindBy(xpath="//*[@class=\"wait-time section\"]//*[@class=\"wait-time_header\"]")
	protected static WebElement componentTitle;
	
	@FindBy(xpath="//*[@class=\"wait-time section\"]//*[@class=\"convenient_care\"]/h3")
	protected static WebElement convenientCareTitle;

	@FindBy(xpath="//*[@class=\"wait-time section\"]//*[@class=\"convenient_care\"]/p")
	protected static WebElement convenientCareDesc;

	@FindBy(xpath="//*[@class=\"wait-time section\"]//*[@class=\"urgent_care\"]/h3")
	protected static WebElement urgentCareTitle;

	@FindBy(xpath="//*[@class=\"wait-time section\"]//*[@class=\"urgent_care\"]/p")
	protected static WebElement urgentCareDesc;

	@FindBy(xpath="//*[@class=\"wait-time section\"]//*[contains(@class,\"wait-time_clock\")]")
	protected static WebElement clockIcon;
	
	protected static String convenientCareCenterNames = "//*[@class=\"wait-time section\"]//*[@class=\"convenient_care-details\"]//*[@class=\"details--item\"]/h4" ;
	protected static String convenientCareCenterAddress = "//*[@class=\"wait-time section\"]//*[@class=\"convenient_care-details\"]//*[@class=\"details--item\"]/*[@class=\"convenient_care-address\"]" ;
	protected static String convenientCareCenterStatus= "//*[@class=\"wait-time section\"]//*[@class=\"convenient_care-details\"]//*[@class=\"details--item\"]/following-sibling::*[contains(@class,\"convenient_care-waittime\")]/*[contains(@class,\"hasTime\") or contains(@class,\"wait-time_min\") or contains(@class,\"noTime\")]" ;
	protected static String urgentCareCenterStatus= "//*[@class=\"wait-time section\"]//*[@class=\"urgent_care-details\"]//*[@class=\"details--item\"]/following-sibling::*[contains(@class,\"urgent_care-waittime\")]/*[contains(@class,\"hasTime\") or contains(@class,\"wait-time_min\") or contains(@class,\"noTime\")]" ;
	protected static String urgentCareCenterAddress = "//*[@class=\"wait-time section\"]//*[@class=\"urgent_care-details\"]//*[@class=\"details--item\"]/*[@class=\"urgent_care-address\"]" ;
	protected static String urgentCareCenterNames = "//*[@class=\"wait-time section\"]//*[@class=\"urgent_care-details\"]//*[@class=\"details--item\"]/h4" ;
	protected static String urgentCareCenters= "//*[@class=\"wait-time section\"]//*[@class=\"urgent_care-details\"]" ;
	protected static String convenientCareCenters= "//*[@class=\"wait-time section\"]//*[@class=\"convenient_care-details\"]" ;
	public WaitTime_page() {
		PageFactory.initElements(mydriver, this);
	}
}
