package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class MiddleImagePlusMedex_page extends Base{
	protected static WebDriver mydriver;
	
	@FindBy(xpath="//*[@class='middle-image-plus section']")
	protected static WebElement middleImageSection;
	
	@FindBy(xpath="//*[@class='middle-image-plus section']//*[contains(@class,'middle-image-plus__header ')]")
	protected static WebElement centralHeading;
	
	@FindBy(xpath="//*[@class='middle-image-plus section']//*[contains(@class,'middle-image-plus__subheader')]")
	protected static WebElement centralSubHeading;
	
	@FindBy(xpath="//*[@class='middle-image-plus section']//*[@class=\"middle-image-plus__row\"]/*[@class=\"middle-image-plus__item\"]/img")
	protected static WebElement centralImage;
	
	@FindBy(xpath="//*[@class='middle-image-plus section']//*[@class=\"middle-image-plus__item\"]/following-sibling::h3[contains(@class,'left')]")
	protected static WebElement leftColumnHeading;
	
	@FindBy(xpath="//*[@class='middle-image-plus section']//*[@class=\"middle-image-plus__item\"]/following-sibling::h3[contains(@class,'right')]")
	protected static WebElement rightColumnHeading;

	@FindBy(xpath="(//*[@class='middle-image-plus section']//*[@class=\"middle-image-plus__item\"]//strong)[1]")
	protected static WebElement leftColumnStrongCopy;

	@FindBy(xpath="(//*[@class='middle-image-plus section']//*[@class=\"middle-image-plus__item\"]//strong)[2]")
	protected static WebElement rightColumnStrongCopy;

	@FindBy(xpath="(//*[@class='middle-image-plus section']//*[@class=\"middle-image-plus__item\"]//p)[1]")
	protected static WebElement leftColumnCopy;
	
	@FindBy(xpath="(//*[@class='middle-image-plus section']//*[@class=\"middle-image-plus__item\"]//p)[2]")
	protected static WebElement rightColumnCopy;

	@FindBy(xpath="(//*[@class='middle-image-plus section']//*[@class=\"middle-image-plus__item\"]//a)[1]")
	protected static WebElement leftColumnCtaLinkAndLabel;
	
	@FindBy(xpath="(//*[@class='middle-image-plus section']//*[@class=\"middle-image-plus__item\"]//a)[2]")
	protected static WebElement rightColumnCtaLinkAndLabel;

	@FindBy(xpath="(//*[@class='middle-image-plus section']//*[@class=\"middle-image-plus__item\"]/img[@class=\"middle-image-plus__item__image\"])[1]")
	protected static WebElement leftColumnImage;

	@FindBy(xpath="(//*[@class='middle-image-plus section']//*[@class=\"middle-image-plus__item\"]/img[@class=\"middle-image-plus__item__image\"])[2]")
	protected static WebElement rightColumnImage;

	
	public MiddleImagePlusMedex_page() {
		PageFactory.initElements(mydriver, this);
	}

}
