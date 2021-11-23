package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExecutionTImeCalculator {

	public static String getCurrentTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	public static Double getTotalExecutionTime(String startTime, String endTime) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss"); 
		
		Date date1 = format.parse(startTime);

		Date date2 = format.parse(endTime);
		Double a= ((double)(date2.getTime() - date1.getTime())/60000);
		return a;
	}
}
