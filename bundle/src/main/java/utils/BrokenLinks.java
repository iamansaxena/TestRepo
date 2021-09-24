package utils;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author amohan31
 *
 */
public class BrokenLinks {

public static int isBroken(String linkUrl) {
	int status = 0 ;
	try{
		URL url = new URL(linkUrl); 
	HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
	HttpURLConnection.setFollowRedirects(true); 
	httpURLConnect.setConnectTimeout(3000); 
	httpURLConnect.connect(); 
	switch (httpURLConnect.getResponseCode()) { 
	case 200: 
	   System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage()); 
	   status= httpURLConnect.getResponseCode();
	   break;
	default: 
	   System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + httpURLConnect.getResponseCode()); 
	httpURLConnect.disconnect();
	status = httpURLConnect.getResponseCode();
	break;
	  }  
	} catch(Exception e) { 
	System.out.println("CODE STUCK SOMEWHERE or" + linkUrl + " is malformed"); 
	e.printStackTrace(); 
	}
	return status;
	
}

	
}
