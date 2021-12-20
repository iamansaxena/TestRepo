package utils;

import static com.optum.dpm.utils.DPMConfigurationsUtil.Environment;
import static com.optum.dpm.utils.DPMConfigurationsUtil.componentData;
import static com.optum.dpm.utils.DPMConfigurationsUtil.upload;
import static com.optum.dpm.utils.DPMTestUtils.getActions;
import static com.optum.dpm.utils.DPMTestUtils.getWebDriverWait;
import static com.optum.dpm.utils.DPMTestUtils.loginTestEnv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebsiteScanner {

	private static Logger logger = LogManager.getLogger(WebsiteScanner.class);

	private static WebDriver mydriver;
	private static String username = "psriva28";//"amohan31";
	private static String password = "Optum@123";
	public static HashMap<String, ArrayList<String>> url_map = new HashMap<>();
	@FindBy(xpath = "//input[@list=\"compolist\"]")
	private static WebElement componentInput;

	@FindBy(xpath = "//input[@name=\"pagepath\"]")
	private static WebElement pagePath;

	@FindBy(xpath = "//a[@id=\"submit\"]")
	private static WebElement submit;
	@FindBy(xpath = "//div[@id=\"displaydata\"]")
	private static WebElement resultTable;

	@FindBy(xpath = "//div[@id=\"displaydata\"]//tr/td")
	private static List<WebElement> resultFields;

	private static String defaultPagePath = "/content";

	public WebsiteScanner() {
		PageFactory.initElements(mydriver, this);
	}

	private static int i = 0;
	// public static JSONObject URL_JSON ;

	@SuppressWarnings("unchecked")

	public static void startScanUtil() throws FileNotFoundException {
		String componentNames = "";
		JSONParser jsonList = new JSONParser();
		JSONObject componentKeys = null;
		Scanner scan = new Scanner(new File("src/main/resources/COMPONENT-URL-DEFAULT-LIST.json"));
		while (scan.hasNext()) {
			componentNames = componentNames + scan.next();
		}
		scan.close();
		try {

			componentKeys = (JSONObject) jsonList.parse(componentNames);
		} catch (ParseException e) {
			logger.fatal("Unable to parse component names for scanning \n" + componentNames);
			e.printStackTrace();
			System.exit(0);
		}
		Iterator<String> it = componentKeys.keySet().iterator();

		while (it.hasNext()) {
			String name = it.next();
			if (!findUrl(name)) {
				mydriver.navigate().refresh();
				findUrl(name);

			}
		}
		finalized();
	}

	public static synchronized boolean findUrl(String componentName) {
		boolean status = false;
		ArrayList<String> urls = new ArrayList<>();
		if (i == 0) {
			i++;

			if (System.getProperty("os.name").toLowerCase().contains("lin")) {
				System.out.println("OS => " + System.getProperty("os.name").toLowerCase());
				WebDriverManager wdm = WebDriverManager.phantomjs();
				wdm.setup();
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setJavascriptEnabled(true);
				caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, wdm.getBinaryPath());
				mydriver = new PhantomJSDriver();
			} else {
				System.out.println("OS => " + System.getProperty("os.name").toLowerCase());
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setJavascriptEnabled(true);
				caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "Drivers/phantomjs.exe");
				mydriver = new PhantomJSDriver(caps);
			}
			mydriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
			mydriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			if(Environment.equals("stage")) {mydriver.get("http://apvrs31488:8080/content/fetchComponentPages.html");}
			else if(Environment.equals("uat")) {mydriver.get("http://apvrs80296:8080/content/fetchComponentPages.html");}
			loginTestEnv(mydriver, username, password, logger);
		}
		new WebsiteScanner();
		getWebDriverWait(mydriver, 30).until(ExpectedConditions.visibilityOf(componentInput));
		componentInput.clear();
		try {
			String exactPath = mydriver
					.findElement(
							By.xpath("//datalist[@id=\"compolist\"]/option[contains(@value,'" + componentName + "')]"))
					.getAttribute("value");
			logger.info("Searching URLs for :>   " + exactPath);
			getActions(mydriver).sendKeys(exactPath).perform();
			pagePath.clear();
			pagePath.sendKeys(defaultPagePath);

			submit.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			getWebDriverWait(mydriver, 5).until(ExpectedConditions.visibilityOf(resultTable));
			new WebsiteScanner();
			if (resultFields.size() != 0) {
				try {getWebDriverWait(mydriver, 5).until(ExpectedConditions.visibilityOf(resultFields.get(0)));
				for (WebElement e : resultFields) {
					new WebsiteScanner();
				
						urls.add(e.getText());
						status = true;

					
				}} catch (Exception e2) {
					logger.fatal("Stale ::>" + componentName);
					status = false;
				}
			}

			url_map.put(componentName, urls);

		} catch (NoSuchElementException e) {
			logger.fatal("Unable to find URL for ::> " + componentName);
			url_map.put(componentName, urls);

		}
		return status;

	}

	private static void finalized() {
		Iterator<String> it = url_map.keySet().iterator();
		while (it.hasNext()) {
			String a = it.next();
			String url = "";
			for (String s : url_map.get(a)) {
				url = url+"\"" + s+"\",";
			}
			
			try{if(!url.isEmpty()) {url = url.substring(0,url.length()-1);}}catch (StringIndexOutOfBoundsException e) {
System.out.println(url);
System.exit(0);
			}
			componentData.put(a, url);
		}
		
		logger.info(componentData);
		Path p3 = Paths.get(System.getProperty("user.dir") + "/src/main/resources/COMPONENT-URL-LIST-"
				+ Environment.trim() + ".json");
		try {
			FileWriter a = new FileWriter(new File(p3.toString()));
			Set<String> dataKey = componentData.keySet();
			a.write("{\n");
			Iterator<String> it2 = dataKey.iterator();
			int i = 0;
			while (it2.hasNext()) {

				String key = it2.next();
				if (i < dataKey.size() - 1) {
					a.write("\"" + key + "\": [" + componentData.get(key) + "],\n");
				}

				else if (i == dataKey.size() - 1) {
					a.write("\"" + key + "\": [" + componentData.get(key) + "]\n");
				}
				i++;
			}
			a.write("}");
			a.flush();
			logger.info("URL List is ready to be uploaded!");

			logger.info("Uploading the fresh url list for " + Environment);
			Thread.sleep(2000);
			if (upload == true) {
				FileUploader.uploadUrlList();
			} else {
				List<String> lines = Files.readAllLines(p3, StandardCharsets.UTF_8);
				logger.info("Not uploading the Scanning results as the 'Upload function is disabled'");
				logger.info("URLS going to be used are:");
				String urlList = "";
				for (String jLines : lines) {
					urlList = urlList + jLines + "\n";
				}
				logger.info(urlList);
			}
			a.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		mydriver.quit();
	}
}
