package compontentPages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class PhotoBlock_page extends Base {

	protected static WebDriver mydriver;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]")
	protected static WebElement photoblockSection;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__one\"]")
	protected static WebElement blockOneSection;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__one\"]//*[@class=\"headline__heading\"]/h2")
	protected static WebElement blockOneSectionHeading;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__one\"]//h3")
	protected static WebElement blockOneSectionContentHeadline;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__one\"]//*[@class=\"directioncopy\"]/p")
	protected static WebElement blockOneSectionCopy;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__one\"]//a")
	protected static WebElement blockOneSectionLink;

	/////////////////////////// SECTION 2
	/////////////////////////// ////////////////////////////////////////////////////////

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__two \"]")
	protected static WebElement blockTwoSection;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__two \"]//*[@class=\"photo__two-a \"]//h2")
	protected static WebElement blockTwo2_0_Headline;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__two \"]//*[@class=\"photo__two-b\"]//h2")
	protected static WebElement blockTwo2_1_Headline;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__two \"]//*[@class=\"photo__two-a \"]//h3")
	protected static WebElement blockTwo2_0_ContentHeadline;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__two \"]//*[@class=\"photo__two-b\"]//h3")
	protected static WebElement blockTwo2_1_ContentHeadline;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__two \"]//*[@class=\"photo__two-a \"]//p")
	protected static WebElement blockTwo2_0_Copy;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__two \"]//*[@class=\"photo__two-b\"]//p")
	protected static WebElement blockTwo2_1_Copy;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__two \"]//*[@class=\"photo__two-a \"]//a")
	protected static WebElement blockTwo2_0_Link;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__two \"]//*[@class=\"photo__two-b\"]//a")
	protected static WebElement blockTwo2_1_Link;

	///////////////////////////// SECTION 3
	///////////////////////////// ////////////////////////////////////////////////////////
	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]")
	protected static WebElement blockThreeSection;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-a\"]//h2")
	protected static WebElement blockThree3_0_Headline;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-b1\"]//h2")
	protected static WebElement blockThree3_1_Headline;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-b2\"]//h2")
	protected static WebElement blockThree3_2_Headline;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-a\"]//h3")
	protected static WebElement blockThree3_0_ContentHeadline;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-b1\"]//h3")
	protected static WebElement blockThree3_1_ContentHeadline;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-b2\"]//h3")
	protected static WebElement blockThree3_2_ContentHeadline;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-a\"]//p")
	protected static WebElement blockThree3_0_Copy;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-b1\"]//p")
	protected static WebElement blockThree3_1_Copy;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-b2\"]//p")
	protected static WebElement blockThree3_2_Copy;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-a\"]//a")
	protected static WebElement blockThree3_0_Link;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-b1\"]//a")
	protected static WebElement blockThree3_1_Link;

	@FindBy(xpath = "//*[@class=\"photo-block section\"]//*[@class=\"pb__contnetblock photo__three\"]//*[@class=\"photo__three-b2\"]//a")
	protected static WebElement blockThree3_2_Link;

	/**
	 * This method is used to verify if particular section under photo block is
	 * present on the page or not. It is to determine the layout type
	 * 
	 * @param e
	 *            Photo-block's section WebElements
	 * @param logger
	 * @return
	 */
	protected static boolean isSectionAvailable(WebElement e, Logger logger) {
		if (e.getCssValue("display") != "none") {
//			scrollToElement(mydriver, e, logger);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to find if the optional field/element is present on the
	 * page or not
	 * 
	 * @param e
	 *            Option WebElement
	 * @return boolean
	 */
	public static boolean ifVisibleAssertTrue(WebElement e) {
		try {
			e.isDisplayed();
			return true;
		} catch (Exception exp) {

			return false;
		}

	}

	public PhotoBlock_page() {
		PageFactory.initElements(mydriver, this);
	}

}
