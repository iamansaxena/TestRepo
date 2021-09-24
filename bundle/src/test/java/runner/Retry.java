package runner;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int counter = 0;
	int retryLimit = 1;

	 @Override
	    public boolean retry(ITestResult iTestResult) {
	        if (!iTestResult.isSuccess()) {                      //Check if test not succeed
	            if (counter < retryLimit) {                            //Check if maxtry count is reached
	                counter++;                                     //Increase the maxTry count by 1
	                iTestResult.setStatus(ITestResult.SKIP);  //Mark test as skipped
	                return true;                                 //Tells TestNG to re-run the test
	            } else {
	                iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
	            }
	        } else {
	            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
	        }
	        return false;
	    }
	 
	}