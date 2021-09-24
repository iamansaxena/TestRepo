package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author amohan31
 *
 */
public class ExecutionTImeCalculator {

	/**
	 * To get the current system time
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	/**
	 * HEre we calculate the exact time taken for the execution to be completed for
	 * reporting purpose
	 * 
	 * @param startTime
	 *            Time at which the execution is started
	 * @param endTime
	 *            Time at which the execuion ended
	 * @return
	 * @throws ParseException
	 */
	public static Double getTotalExecutionTime(String startTime, String endTime) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

		Date date1 = format.parse(startTime);

		Date date2 = format.parse(endTime);
		Double a = ((double) (date2.getTime() - date1.getTime()) / 60000);
		return a;
	}
}
