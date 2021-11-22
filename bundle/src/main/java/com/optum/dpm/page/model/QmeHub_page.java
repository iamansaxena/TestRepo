package com.optum.dpm.page.model;


import static com.optum.dpm.utils.DPMTestUtils.scrollToElement;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optum.dpm.utils.Base;

public class QmeHub_page extends Base {
	private static final Logger logger = LogManager.getLogger(QmeHub_page.class);
	
	private String author = "Aman Saxena";
	private String tag = "QmeHub";
	
	protected static HashMap<String, List<WebElement>> results = new HashMap<>();
	protected static HashMap<String, String> resultsSubSet = new HashMap<>();
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

	private static List<WebElement> resultLabels;
	private static List<WebElement> resultIcons;
	private static int i = 0;

	protected HashMap<String, List<WebElement>> getResults(Logger logger) {
		
		scrollToElement(mydriver, accountTypeSectionLabel, logger);
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
		scrollToElement(mydriver, accountTypeFilter2, logger);
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
		scrollToElement(mydriver, accountTypeFilter3, logger);
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
	scrollToElement(mydriver, accountTypeFilter4, logger);
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
