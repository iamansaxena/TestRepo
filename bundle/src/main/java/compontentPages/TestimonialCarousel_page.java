package compontentPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class TestimonialCarousel_page extends Base{
protected static WebDriver mydriver;

@FindBy(xpath="//*[@class=\"testimonial-carousel section\"]")
protected static WebElement testimonialSection;


@FindBy(xpath="//*[@class=\"testimonial-carousel section\"]//a[@class=\"bx-prev\"]")
protected static WebElement previousAngularButton;

@FindBy(xpath="//*[@class=\"testimonial-carousel section\"]//video")
protected static WebElement videoModal;

@FindBy(xpath="//*[@class=\"testimonial-carousel section\"]//a[@class=\"bx-next\"]")
protected static WebElement nextAngularButton;

protected static String slides = "//*[@class=\"testimonial-carousel section\"]//ul[contains(@class,'slider')]/li[not(contains(@class,'clone'))]";
protected static String slidesWithVideo = "//*[@class=\"testimonial-carousel section\"]//li[not(contains(@class,'clone'))]//button[contains(@class,'video')]";

protected static String socialSharingSlideLinks = "//*[@class=\"testimonial-carousel section\"]//li[not(contains(@class,'clone'))]//*[contains(@class,'slider-text')]/p[@class]/a";

protected static String socialSharingSlideLinksLabel = "//*[@class=\"testimonial-carousel section\"]//li[not(contains(@class,'clone'))]//*[contains(@class,'slider-text')]/p[@class]//span";


protected static String slideContent = "//*[@class=\"testimonial-carousel section\"]//li[not(contains(@class,'clone'))]//*[contains(@class,'slider-text')]/p[not(@class)]";

protected static String pagerButtons = "//*[@class=\"testimonial-carousel section\"]//a[contains(@class,\"bx-pager-link\")]";
protected static String activeSlide = "//*[@class=\"testimonial-carousel section\"]//a[contains(@class,\"bx-pager-link active\")]";

public TestimonialCarousel_page() {
	PageFactory.initElements(mydriver, this);
}

}
