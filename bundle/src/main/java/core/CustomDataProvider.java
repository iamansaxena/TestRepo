package core;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.LoggerLog4j;

public class CustomDataProvider extends Base {
	static Logger logger = LoggerLog4j.startTestCase(CustomDataProvider.class);

	/**
	 * This method used to fetch url and pass the same to Test
	 * 
	 * @param test
	 *            method
	 * @return obj
	 */
	@DataProvider(name = "data-provider")
	public static Object[][] dataProviderMethod(Method m) {
		ArrayList<String> cardUrls = new ArrayList<>();
		String component = ((Test) m.getAnnotation(Test.class)).parameters()[0];

		String url = jsonHandler(component);
		String[] urls = url.substring(1, (url.length()) - 1).split(",");
		if (fetchUrl(component) == null) {
			switch (Environment.toLowerCase()) {
			case "stage":
				return new Object[][] { { urls[0].substring(1, (urls[0].length()) - 1) } };
			case "test":
				return new Object[][] { { urls[1].substring(1, (urls[1].length()) - 1) } };
			}
		} else {
			String[] scannedUrls = fetchUrl(component).split(",");
			for (String link : scannedUrls) {
				cardUrls.add(link);
			}
			if(cardUrls.size()>1) {
			return new Object[][] { { cardUrls.get(0) }, { cardUrls.get(1) } };}
			else{
				return new Object[][] { { cardUrls.get(0) } };}
		}
		return null;
	}

	/**
	 * This method used to get url from json file using component(qa handle)
	 * 
	 * @param component
	 *            - qa handle
	 * @return String stage and test url as single string
	 */
	public synchronized static String jsonHandler(String component) {
		JSONParser json = new JSONParser();
		JSONObject obj = null;
		String url = "";
		try {
			obj = (JSONObject) json.parse(new FileReader("./src/main/resources/COMPONENT-URL-DEFAULT-LIST.json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.fatal("Unable to find the list with default URLs");
			System.exit(0);
		}
			
		try {
			url = obj.get(component).toString().replace("\\", "");	
		} catch (Exception e) {
			logger.fatal("Unable to find URL for ==> "+ component);
			
		}	
				if(url==null||url.isEmpty()) {
					throw new SkipException("Unable to find URL for ==> "+ component);
				}
		
		return url;

	}
}
