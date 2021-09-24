package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class ColorBlock_page extends Base {
	protected static  WebDriver mydriver;
	protected static String title="//*[@class=\"color-block section\"]//*[contains(@class,\"section-inner img\")]//h2/a";
protected static String videoButton="//*[@class=\"banner__main-hero--cta\"]/button";
protected static String blockSection="//*[@class=\"color-block section\"]";
protected static String linkButton="//*[contains(@class,\"section-inner img\")]/div/a";


public ColorBlock_page() {
PageFactory.initElements(mydriver, this);
}

}
