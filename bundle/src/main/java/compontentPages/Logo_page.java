package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class Logo_page extends Base{
protected static WebDriver mydriver;
	@FindBy(xpath="//*[@class=\"logo\"]/div[@data-favicon]")
	protected static WebElement favIcon;
	
	@FindBy(xpath="//*[@class=\"logo\"]/a")
	protected static WebElement logoLink;
	
	@FindBy(xpath="//*[@class=\"logo\"]//img")
	protected static WebElement logoImg;

	@FindBy(xpath="//*[@class=\"logo\"]")
	protected static WebElement logo;
	
	@FindBy(xpath="//*[@class=\"logo\"]/div[@class=\"logo__tag delta\"]")
	protected static WebElement logoTagLine;
	
	
	
	public Logo_page() {
		PageFactory.initElements(mydriver, this);
	}
}
