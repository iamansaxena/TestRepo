package com.optum.dpm.utils;

import static com.optum.dpm.reports.ExtentTestManager.endTest;
import static com.optum.dpm.reports.ExtentTestManager.startTest;
import static com.optum.dpm.utils.DPMTestUtils.pleaseWait;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public abstract class Base extends DPMTestUtils{
	public SoftAssert softAssert;
	public Assertion hardAssert;
	public WebDriver mydriver;
	
	private static final Logger logger = LogManager.getLogger(Base.class);
	
	@BeforeClass
	public void setup() {
		logger.info("Initilization Base setup for {} ",getTestName());
		mydriver = DriverSessionHandlerUtil.getWebdriverInstance(); //create WebDriver/Session
		PageFactory.initElements(mydriver,this); //initialize PageElements 
		startTest(getTestName()); // Reporting 
		pleaseWait(1, logger);
	}
	
	@AfterClass
	public void tearDown() {
		mydriver.quit();
		endTest();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		softAssert = new SoftAssert();
		hardAssert = new Assertion();
	}

	public String getTestName() {
		String testName = this.getClass().getName();
		return StringUtils.substring(testName, testName.lastIndexOf(".")+1);
	}
}
