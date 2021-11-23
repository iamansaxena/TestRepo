package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import utils.FileUploader;
import utils.LoggerLog4j;

/**
 * @author amohan31
 *
 */
public class Configurator extends Base {

	/**
	 * This method is used to load the environment specific configuration files
	 */
	public static void loadConfig(String filePath) {
		property = new Properties();

		try {
			property.load(new FileInputStream(filePath));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			LoggerLog4j.logger.fatal("Configurations file not found");
			System.exit(0);
		} catch (IOException e) {

			e.printStackTrace();
			logger.fatal("Unable to load cofiguration file");
			System.exit(0);
		}
	}

	/**
	 * This method is used to initialize the configuration vars by fetching the
	 * values provided in the environment specific configuration files. It is also
	 * used to perform scanning process if enabled and then generate the test data.
	 *
	 */
	public static void setConfig() {
		url = property.getProperty("URL_FOR_AUT").split(";");

		clientName = property.getProperty("client");
		DriverSessionHandler.setBrowser();

		remoteVersion = property.getProperty("remoteVersion");

		if (System.getProperty("remoteExecution") == null) {
			remoteExecution = property.getProperty("remoteExecution");
		} else {
			remoteExecution = System.getProperty("remoteExecution");
		}

		if (System.getProperty("sauceUsername") == null) {
			sauceUserName = property.getProperty("sauceUserName");
		} else {
			sauceUserName = System.getProperty("sauceUsername");
		}

		if (System.getProperty("sauceKey") == null) {
			sauceAccessKey = property.getProperty("sauceAccessKey");
		} else {
			sauceAccessKey = System.getProperty("sauceKey");
		}
		nasPath = property.getProperty("nasPath");
		if (System.getProperty("upload") == null || !(System.getProperty("upload").trim().equalsIgnoreCase("true"))) {
			upload = false;
			
		} else if (System.getProperty("upload").equalsIgnoreCase("true")) {
			upload = true;
		}
		if (System.getProperty("regression") == null
				|| !(System.getProperty("regression").trim().equalsIgnoreCase("true"))) {
			System.setProperty("regression", "false");
			isRegression = false;
		} else if (System.getProperty("regression").trim().equalsIgnoreCase("true")) {
			isRegression = true;
		}

		platform = property.getProperty("platform");
		chrome_path = property.getProperty("ChromeDriverPath");
		firefox_path = property.getProperty("FirefoxDriverPath");
		IeDriverPath = property.getProperty("IeDriverPath");
		edgeDriverPath = property.getProperty("edgeDriverPath");
		AUT = property.getProperty("AUT");
		Executor = property.getProperty("Executor");
		Environment = property.getProperty("Environment").trim();
		logger.info("Current Environment is : " + Environment);
		capability = DriverSessionHandler.getCapabilities();

		if (System.getProperty("scan") == null || !(System.getProperty("scan").trim().equalsIgnoreCase("true"))) {

			if (System.getProperty("defaultExecution") == null
					|| !(System.getProperty("defaultExecution").trim().equalsIgnoreCase("true"))) {
				logger.info(
						"User has not opted for scanning. Hence, we'll pick from pre-defined list of component available at "
								+ serverIP);
				logger.info("Downloading the updated file from the server..");
				getComponentUrl(logger);
			} else {
				logger.info("Execution with static URLs is starting...");
			}

		} else if (System.getProperty("scan").equalsIgnoreCase("true")) {
			Scanner xpathProperties = null;

			try {
				xpathProperties = new Scanner(new File("src/main/resources/XpathList.properties"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			if (System.getProperty("scanWith") == null
					|| System.getProperty("scanWith").toLowerCase().contains("xpath")) {
				while (xpathProperties.hasNextLine()) {
					componentData.put(xpathProperties.nextLine(), "-");
				}
			} else {

				try {
					componentProperties.load(new FileInputStream("src/main/resources/ComponentList.properties"));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Set<Object> componentList = componentProperties.keySet();
				for (Object key : componentList) {

					if (!componentProperties.getProperty(key.toString()).isEmpty()
							|| componentProperties.getProperty(key.toString()) != null) {
						componentData.put(key.toString().trim(), componentProperties.getProperty(key.toString()));
					} else {
						componentData.put(key.toString().trim(), "-");
					}

				}
			}

			for (String url : Base.url) {
				new CrawlerService(url);

			}
			for (Thread t : CrawlerService.threadPool) {
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Path p3 = Paths.get(System.getProperty("user.dir") + "/src/main/resources/COMPONENT-URL-LIST-"
					+ Environment.trim() + ".json");
			try {
				FileWriter a = new FileWriter(new File(p3.toString()));
				Set<String> dataKey = componentData.keySet();
				a.write("{\n");
				Iterator<String> it = dataKey.iterator();
				int i = 0;
				while (it.hasNext()) {

					String key = it.next();
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

		}

	}
}
