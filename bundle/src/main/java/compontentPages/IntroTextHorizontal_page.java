package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class IntroTextHorizontal_page extends Base {
	
	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class=\"segment__heading--headline \"]")
	protected static WebElement test;
	
	@FindBy(xpath="//*[@class='intro-arrowtext-divider section']//h2//span")
	protected static WebElement longDivider;
	
	@FindBy(xpath="//*[@class='intro-arrowtext-divider section']//*[@calss='directional-copy']/p")
	protected static WebElement introDirectionalCopy;
	
	@FindBy(xpath="//*[@class='intro-arrowtext-divider section']//span[contains(@class,'align-center')]/h2")
	protected static WebElement shortDivider;
	
	
	public IntroTextHorizontal_page() {

		PageFactory.initElements(mydriver, this);
		}

}
