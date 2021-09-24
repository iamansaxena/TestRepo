package compontentPages;

import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class Sharebar_page extends Base {
protected static WebDriver mydriver;

Base obj = new Base();
@FindBy(xpath="//a[contains(@class,\"linkedin\")]")
protected static WebElement linkedinLink;

@FindBy(xpath="//a[contains(@class,\"facebook\")]")
protected static WebElement facebookLink;

@FindBy(xpath="//a[contains(@class,\"twittershare\")]")
protected static WebElement twitterLink;

@FindBy(xpath="//a[contains(@class,\"pinterest\")]")
protected static WebElement pinterestLink;

@FindBy(xpath="//a[contains(@class,\"email\")]")
protected static WebElement emailShareLink;

@FindBy(xpath="//*[contains(@class,\"share-bar\")]/*[@class=\"row\"]")
protected static WebElement sharebarContainer;

public Sharebar_page() {
PageFactory.initElements(mydriver, this);
}
public static void getBackToBaseTab(String baseTabHandle) {
	Iterator<String> it = mydriver.getWindowHandles().iterator();
	while(it.hasNext()) {
		String currentTab = it.next();
		if(!currentTab.equals(baseTabHandle)) {
			mydriver.switchTo().window(currentTab).close();;
		}
	}
	mydriver.switchTo().window(mydriver.getWindowHandles().iterator().next());

}
/*public static void scrollToElementWithoutWait(WebDriver mydriver, WebElement element) {
	((JavascriptExecutor) mydriver).executeScript("arguments[0].scrollIntoView(true);", element);

	focusElement(mydriver, element);
}*/

}
