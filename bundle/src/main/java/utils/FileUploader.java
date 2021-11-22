package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.optum.dpm.utils.DPMConfigurationsUtil.*;

import jcifs.smb.SmbException;

/**
 * @author amohan31
 *
 */
public class FileUploader  {
	private static Logger logger = LogManager.getLogger(FileUploader.class);
	static FileReader file;

	/**
	 * This method is used to download the required file from the test server
	 * 
	 * @param fileName
	 *            name of the file need to be downloaded
	 * @return
	 */
	public static FileReader downloadFile(String fileName) {

		try {

			file = new FileReader(NASUploader.downloadUrlFileFromNAS());
		} catch (MalformedURLException e) {
			logger.fatal("issue with the NAS url.\nSystem is now exiting!");
			e.printStackTrace();
			System.exit(0);
		} catch (SmbException e) {
			logger.fatal("SMB Exception .\nSystem is now exiting!");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			logger.fatal("System is now exiting!");
			e.printStackTrace();
			System.exit(0);
		}

		return file;
	}

	/**
	 * This method is used to upload the Scanning results at a conditional path to
	 * the server
	 */
	public static void uploadUrlList() {
		String path = "COMPONENT-URL-LIST-" + Environment.trim() + ".json";

		if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Win")) {
			path = System.getProperty("user.dir") + "\\src\\main\\resources" + path;
		} else if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Lin")) {
			path = System.getProperty("user.dir") + "/src/main/resources" + path;
		}
		NASUploader.uploadReport(new File(path), "url", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

	}

	/**
	 * This method is used to upload the screenshots at a conditional path to the
	 * server
	 * 
	 * @throws IOException
	 */
	public static void uploadArtifacts() throws IOException {
		String dateName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		String path = "Report_" + clientName + "_" + dateName + "_" + browserName + ".html";
		logger.info("Current OS => " + System.getProperty("os.name"));
		if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Win")) {
			path = System.getProperty("user.dir") + "\\Reports\\" + dateName + "\\Report_" + clientName + "_" + dateName
					+ "_" + browserName + ".html";

		} else if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Lin")) {
			path = System.getProperty("user.dir") + "/Reports/" + dateName + "/Report_" + clientName + "_" + dateName
					+ "_" + browserName + ".html";
		}

		NASUploader.uploadReport(new File(path), "report", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

		logger.info("Uploading the screenshots for the failed/skipped tests!!");

		File dir1 = null;
		if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Win")) {
			dir1 = new File(System.getProperty("user.dir") + "\\FailedTestsScreenshots");
			path = System.getProperty("user.dir") + "\\FailedTestsScreenshots//";
		} else if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Lin")) {
			dir1 = new File(System.getProperty("user.dir") + "/FailedTestsScreenshots");
			path = System.getProperty("user.dir") + "/FailedTestsScreenshots/";
		}
		logger.info("Failed Test Case Dir: " + dir1);
		if (dir1.isDirectory()) {
			File[] content = dir1.listFiles();
			logger.info("Files under failed screenshot dir => " + content.length);
			if (content.length != 0) {
				for (int i = 0; i < content.length; i++) {
					if (content[i].getName().contains(".png")) {
						path = content[i].getAbsolutePath();
						NASUploader.uploadReport(new File(path), "grab",
								new SimpleDateFormat("dd-MM-yyyy").format(new Date()));

						logger.info("Uploaded: " + content[i].getName());
					}

				}
			} else {
				logger.info("There is no screenshot available in the dir");
			}
		}

	}
}
