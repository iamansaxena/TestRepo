package compontentPages;


import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import core.Base;

public class QmeHub_page extends Base {
	Base obj = new Base();
	protected static HashMap<String, List<WebElement>> results = new HashMap<>();
	protected static HashMap<String, String> resultsSubSet = new HashMap<>();
	protected static WebDriver mydriver;
	@FindBy(xpath = "//*[@type=\"search\"]")
	protected static WebElement searchInput;

	@FindBy(xpath = "//*[@type=\"reset\"]")
	protected static WebElement searchButton;

	@FindBy(xpath = "//*[@type=\"reset\"]")
	protected static WebElement searchResetButton;

	@FindBy(xpath = "//*[@class=\"qme-filter-items\"]/strong")
	protected static WebElement expenseTypeTag;

	@FindBy(xpath = "//*[@class=\"qme-filter-items\"]/strong/span")
	protected static WebElement accountTypeTag;

	@FindBy(xpath = "//*[@class=\"filter-item account-type\"]/h4")
	protected static WebElement accountTypeSectionLabel;

	@FindBy(xpath = "//*[@class=\"filter-item expense-type\"]/h4")
	protected static WebElement expenseTypeSectionLabel;

	@FindBy(xpath = "(//*[@class=\"filter-item expense-type\"]/ul/li/label)[1]")
	protected static WebElement expenseTypeFilter1;

	@FindBy(xpath = "(//*[@class=\"filter-item expense-type\"]/ul/li/label/span)[1]")
	protected static WebElement expenseTypeFilter2;

	@FindBy(xpath = "(//*[@class=\"filter-item expense-type\"]/ul/li/label/span)[2]")
	protected static WebElement expenseTypeFilter3;

	@FindBy(xpath = "(//*[@class=\"filter-item expense-type\"]/ul/li/label/span)[3]")
	protected static WebElement expenseTypeFilter4;

	@FindBy(xpath = "(//*[@class=\"filter-item account-type\"]/ul/li/label/span)[1]")
	protected static WebElement accountTypeFilter1;

	@FindBy(xpath = "(//*[@class=\"filter-item account-type\"]/ul/li/label/span)[2]")
	protected static WebElement accountTypeFilter2;

	@FindBy(xpath = "(//*[@class=\"filter-item account-type\"]/ul/li/label/span)[3]")
	protected static WebElement accountTypeFilter3;

	@FindBy(xpath = "(//*[@class=\"filter-item account-type\"]/ul/li/label/span)[4]")
	protected static WebElement accountTypeFilter4;

	protected static String resultIconCategory = "//*[@class=\"qme animated fadeInFast\"]/li/i/span";

	protected static String resultElement = "//*[@class=\"qme animated fadeInFast\"]/li/span";

	public QmeHub_page() {
		PageFactory.initElements(mydriver, this);
	}

	public QmeHub_page(Logger logger) {
		PageFactory.initElements(mydriver, this);
		logger.info("Object repository refreshed");
	}

	private static List<WebElement> resultLabels;
	private static List<WebElement> resultIcons;
	private static int i = 0;

	protected HashMap<String, List<WebElement>> getResults(Logger logger) {
		
		new QmeHub_page(logger);
		obj.scrollToElement(mydriver, accountTypeSectionLabel, logger);
		if(accountTypeSectionLabel.getAttribute("aria-expanded").equals("false")) {
			accountTypeSectionLabel.click();
		}
		
		accountTypeFilter1.click();
		logger.info("Getting data for Account-Type : " + accountTypeFilter1.getText());

		resultLabels = mydriver.findElements(By.xpath(resultElement));
		resultIcons = mydriver.findElements(By.xpath(resultIconCategory));
		i = 0;
//		for (WebElement resultlabel : resultLabels) {
//			scrollToElement(mydriver, resultlabel);
//			resultsSubSet.put(resultlabel.getText(), resultIcons.get(i).getText());
//			i++;
//		}
		results.put(accountTypeFilter1.getText(), resultLabels);
		logger.info("There're '"+resultLabels.size()+"' elements available under account-type '"+accountTypeFilter1.getText()+"'");
		
		
		
		
		
		resultsSubSet=new  HashMap<>();
		obj.scrollToElement(mydriver, accountTypeFilter2, logger);
		accountTypeFilter2.click();
		logger.info("Getting data for Account-Type : " + accountTypeFilter2.getText());

		resultLabels = mydriver.findElements(By.xpath(resultElement));
		resultIcons = mydriver.findElements(By.xpath(resultIconCategory));
		i = 0;
//		for (WebElement resultlabel : resultLabels) {
//			scrollToElement(mydriver, resultlabel);
//			resultsSubSet.put(resultlabel.getText(), resultIcons.get(i).getText());
//			i++;
//		}
		results.put(accountTypeFilter2.getText(), resultLabels);
		logger.info("There're '"+resultLabels.size()+"' elements available under account-type '"+accountTypeFilter2.getText()+"'");
		
		
		
		
		
		resultsSubSet=new  HashMap<>();
		obj.scrollToElement(mydriver, accountTypeFilter3, logger);
		accountTypeFilter3.click();
		logger.info("Getting data for Account-Type : " + accountTypeFilter3.getText());

		resultLabels = mydriver.findElements(By.xpath(resultElement));
		resultIcons = mydriver.findElements(By.xpath(resultIconCategory));
		i = 0;
//		for (WebElement resultlabel : resultLabels) {
//			scrollToElement(mydriver, resultlabel);
//			resultsSubSet.put(resultlabel.getText(), resultIcons.get(i).getText());
//			i++;
//		}
		results.put(accountTypeFilter3.getText(), resultLabels);
logger.info("There're '"+resultLabels.size()+"' elements available under account-type '"+accountTypeFilter3.getText()+"'");
		
		



	resultsSubSet=new  HashMap<>();
	obj.scrollToElement(mydriver, accountTypeFilter4, logger);
		accountTypeFilter4.click();
		logger.info("Getting data for Account-Type : " + accountTypeFilter4.getText());

		resultLabels = mydriver.findElements(By.xpath(resultElement));
		resultIcons = mydriver.findElements(By.xpath(resultIconCategory));
		i = 0;
//		for (WebElement resultlabel : resultLabels) {
//			scrollToElement(mydriver, resultlabel);
//			resultsSubSet.put(resultlabel.getText(), resultLabels);
//			i++;
//		}
		results.put(accountTypeFilter4.getText(), resultLabels);
		logger.info("There're '"+resultLabels.size()+"' elements available under account-type '"+accountTypeFilter4.getText()+"'");
		return results;
		}

}
