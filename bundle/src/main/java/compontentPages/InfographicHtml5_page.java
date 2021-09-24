package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class InfographicHtml5_page extends Base {

	protected static WebDriver mydriver;
	@FindBy(xpath="//*[@class=\"infographic-html5 section\"]//following-sibling::script")
	protected static WebElement scripts; 
	
	public InfographicHtml5_page() {
	PageFactory.initElements(mydriver, this);
	}
}
