package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class CtaModuleMedex_page extends Base{

	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class=\"cta-module section\"]")
	protected static WebElement ctaModuleSection;
	
	@FindBy(xpath="//*[@class=\"cta-module section\"]//*[contains(@class,'cta-copy')]/h2")
	protected static WebElement ctaModuleTitle;
	
	@FindBy(xpath="//*[@class=\"cta-module section\"]//*[contains(@class,'cta-copy')]/p")
	protected static WebElement ctaModuleCopy;
	
	@FindBy(xpath="//*[@class=\"cta-module section\"]//*[contains(@class,\"cta-button\")]")
	protected static WebElement ctaModuleCtaLabel;
	
	@FindBy(xpath="//*[@class=\"cta-module section\"]//*[@href]")
	protected static WebElement ctaModuleCtaLink;
	
	public CtaModuleMedex_page() {
		PageFactory.initElements(mydriver, this);
	}
}
