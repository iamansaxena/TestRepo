package compontentPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class ErServicesMedex_page extends Base{

	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]")
	protected static WebElement erServiceSection;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"er__heading\"]")
	protected static WebElement erServiceSectionTitle;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"er__desc__copy\"]")
	protected static WebElement erServiceSectionCopy;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"med-service\"]/p")
	protected static WebElement medexSectionHeading;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"med-service\"]/ul/li/span[contains(@class,'icon icon')]")
	protected static List<WebElement> medexSectionItemIcon;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"med-service\"]/ul/li/span[contains(@class,'icon_desc')]")
	protected static List<WebElement> medexSectionItemLabel;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"er-service\"]/p")
	protected static WebElement emergencySectionHeading;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"er-service\"]/ul/li/span[contains(@class,'icon icon')]")
	protected static List<WebElement> emergencySectionItemIcon;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"er-service\"]/ul/li/span[contains(@class,'icon_desc')]")
	protected static List<WebElement> emergencySectionItemLabel;
	
	@FindBy(xpath="//*[@class=\"er-services section\"]//*[@class=\"reference-cite\"]/p")
	protected static List<WebElement> referenceSectionTextField;
	
	public ErServicesMedex_page() {
		PageFactory.initElements(mydriver, this);
	}
}
