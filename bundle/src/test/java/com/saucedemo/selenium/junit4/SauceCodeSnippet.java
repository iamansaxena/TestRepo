package com.saucedemo.selenium.junit4;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.assertNull;

/**
 * Tests for running Visual Tests on Sauce.
 */
public class SauceCodeSnippet {

    protected RemoteWebDriver driver;
    public String sauceUsername = "sso-optum-aman.mohan";
    public String sauceAccessKey = "330c2b7b-bf0c-4d43-8793-6748808355aa";
    public String screenerApiKey = "94480970-67fb-4d29-aa1a-d702dc30f2ef";
    private MutableCapabilities capabilities;

    @Before
    public void setUp() {
        capabilities = new MutableCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, "latest");
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Windows 10");

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("username", sauceUsername);
        sauceOptions.setCapability("accesskey", sauceAccessKey);
        capabilities.setCapability("sauce:options", sauceOptions);
    }

    @Test
    public void testVisualE2E() throws MalformedURLException {
        MutableCapabilities visualOptions = new MutableCapabilities();
        visualOptions.setCapability("apiKey", screenerApiKey);
        visualOptions.setCapability("projectName", "visual-e2e-test");
        visualOptions.setCapability("viewportSize", "1280x1024");
        capabilities.setCapability("sauce:visual", visualOptions);
        // Visual requires validation when the browser version changes
        capabilities.setCapability(CapabilityType.BROWSER_VERSION, "94");

        URL url = new URL("https://hub.screener.io/wd/hub");
        driver = new RemoteWebDriver(url, capabilities);

        driver.get("https://saucedemo.com");

        driver.executeScript("/*@visual.init*/", "My Visual Test 2");
        driver.executeScript("/*@visual.snapshot*/", "Home");
        Map<String, Object> response = (Map<String, Object>) driver.executeScript("/*@visual.end*/");
        assertNull(response.get("message"));
    }

    @Ignore("Expected to Fail - For Demo Purposes")
    @Test
    public void visualBaselineBranching() throws MalformedURLException {
        //*
        // This example shows how a team might have a baseline snapshot in Prod
        // that is compared against a snapshot in QA
        // **/
        String projectName = "visual-examples-java";
        MutableCapabilities visualOptions = new MutableCapabilities();
        visualOptions.setCapability("apiKey", screenerApiKey);
        visualOptions.setCapability("projectName", projectName);
        visualOptions.setCapability("viewportSize", "1280x1024");
        visualOptions.setCapability("branch", "main");
        visualOptions.setCapability("baseBranch", "main");
        visualOptions.setCapability("alwaysAcceptBaseBranch", true);
        capabilities.setCapability("sauce:visual", visualOptions);

        URL url = new URL("https://hub.screener.io/wd/hub");
        driver = new RemoteWebDriver(url, capabilities);

        driver.get("https://www.saucedemo.com");

        // Capture a snapshot of a page on the main branch
        driver.executeScript("/*@visual.init*/", "My Visual Test");
        driver.executeScript("/*@visual.snapshot*/", "Branch Compare");
        assertNoVisualDifferences();

        //Capture a snapshot on a different branch
        visualOptions = new MutableCapabilities();
        visualOptions.setCapability("apiKey", screenerApiKey);
        visualOptions.setCapability("projectName", projectName);
        visualOptions.setCapability("viewportSize", "1280x1024");
        visualOptions.setCapability("branch", "newBranch");
        visualOptions.setCapability("baseBranch", "main");
        capabilities.setCapability("sauce:visual", visualOptions);

        url = new URL("https://hub.screener.io/wd/hub");
        driver = new RemoteWebDriver(url, capabilities);
        driver.get("https://www.screener.io");

        // Capture a snapshot of a page on the main branch
        driver.executeScript("/*@visual.init*/", "My Visual Test");
        driver.executeScript("/*@visual.snapshot*/", "Branch Compare");
        assertNoVisualDifferences();
    }

    private void assertNoVisualDifferences() {
        Map<String, Object> response = (Map<String, Object>) driver.executeScript("/*@visual.end*/");
        if (response.get("message") != null) {
            assertNull(response.get("message"));
        }
    }
}
