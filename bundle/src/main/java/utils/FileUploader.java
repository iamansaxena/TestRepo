package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import core.Base;

public class FileUploader extends Base {
	static Logger logger = LoggerLog4j.startTestCase(FileUploader.class);
	static FileReader file;

	public static void main(String[] args) {
		try {
			uploadArtifacts();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// downloadFile("server.py");

	}

	public static FileReader downloadFile(String fileName) {
		String command = "curl "+serverIP + fileName + " -o " + fileName;
		ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
		processBuilder.directory(new File(System.getProperty("user.dir")));

		Process process = null;
		try {
			process = processBuilder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		processBuilder.redirectErrorStream(true);
		processBuilder.redirectOutput();

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		process.destroy();
		while (process.isAlive()) {
			System.out.println("Curl process is still alive");
		}
		int j = process.exitValue();
		if (j != 0) {
			logger.fatal("Failed to download " + fileName + " from " + serverIP + "\nError Code: " + j
					+ "\n** Please make sure that the environment specific json file is available on the server"
					+ "\nSytem is now exiting!!");
			System.exit(0);
		} else {
			logger.info("Downloaded " + fileName + " at => " + System.getProperty("user.dir"));

			try {
				file = new FileReader(new File(System.getProperty("user.dir") + "/" + fileName));

			} catch (FileNotFoundException e) {
				System.out.println("Unable to file json file.\nSystem is now exiting!");
				e.printStackTrace();
				System.exit(0);
			}
		}
		return file;
	}

	/*
	 * public static void uploadUrlList() { CUrl curl = new CUrl(serverIP); //
	 * curl.form("file", new CUrl.FileIO(System.getProperty("user.dir") +
	 * "\\src\\main\\resources\\" // + "COMPONENT-URL-LIST-" + Environment.trim() +
	 * ".json")); curl.exec("curl -F file=@"+"COMPONENT-URL-LIST-" +
	 * Environment.trim() + ".json "+serverIP);
	 * 
	 * if (curl.getHttpCode() == 200) { logger.info("COMPONENT-URL-LIST-" +
	 * Environment.trim() + ".json " + "uploaded successfully!"); } else {
	 * logger.fatal("Failed to upload " + "COMPONENT-URL-LIST-" + Environment.trim()
	 * + ".json"); } }
	 */

	public static void uploadUrlList() {
		logger = LoggerLog4j.startTestCase(FileUploader.class);
		String path = "COMPONENT-URL-LIST-" + Environment.trim() + ".json";
		String command = "curl -F file=@" + path + " " + serverIP;
		ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

		if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Win")) {
			processBuilder.directory(new File(System.getProperty("user.dir") + "\\src\\main\\resources"));
		} else if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Lin")) {
			processBuilder.directory(new File(System.getProperty("user.dir") + "/src/main/resources"));
		}

		Process process = null;

		processBuilder.redirectErrorStream(true);
		processBuilder.redirectOutput();
		try {
			process = processBuilder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(System.getProperty("line.separator"));

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Current directory => " + processBuilder.directory());
		logger.info("\n" + builder);
		process.destroy();
		while (process.isAlive()) {

		}
		int j = process.exitValue();
		if (j != 0) {
			logger.fatal("Failed to upload the updated " + path + " over " + serverIP + "\n Error Code: " + j);
		} else {
			logger.info("Successfully uploaded " + path + " over " + serverIP);
		}

	}

	public static void uploadArtifacts() throws IOException {
		logger = LoggerLog4j.startTestCase(FileUploader.class);
		String dateName = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		clientName = "OPTUM";
		// dateName = "21-12-2020";
		String path = "Report_" + clientName + "_" + dateName + "_" + browserName + ".html";
		String command = "curl -F file=@" + path + " " + serverIP;

		if (Environment.equalsIgnoreCase("test")) {
			command = command + "/Test%20Environment%20Results/";
		} else if (Environment.equalsIgnoreCase("stage")) {
			if (System.getProperty("regression").equalsIgnoreCase("true")) {
				command = command + "/Regression%20Results/Stage%20Regression/";
			} else {
				command = command + "/Stage%20Environment%20Results/";
			}
		}

		else if (Environment.equalsIgnoreCase("uat")) {
			if (System.getProperty("regression").equalsIgnoreCase("true")) {
				command = command + "/Regression%20Results/UAT%20Regression/";
			} else {
				command = command + "/UAT%20Environment%20Results/";
			}
		} else if (Environment.equalsIgnoreCase("prod")) {
			command = command + "/Prod%20Environment%20Results/";
		}

		logger.info("Executing post command for upload artifacts: " + command);

		// CUrl curl = new CUrl(serverIP);
		logger.info("Current OS => " + System.getProperty("os.name"));
		if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Win")) {
			path = System.getProperty("user.dir") + "\\Reports\\" + dateName + "\\Report_" + clientName + "_" + dateName
					+ ".html";

		} else if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Lin")) {
			path = System.getProperty("user.dir") + "/Reports/" + dateName + "/Report_" + clientName + "_" + dateName
					+ ".html";
		}

		ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

		if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Win")) {
			processBuilder.directory(new File(System.getProperty("user.dir") + "\\Reports\\" + dateName));
		} else if (System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Lin")) {
			processBuilder.directory(new File(System.getProperty("user.dir") + "/Reports/" + dateName));
		}
		Process process = processBuilder.start();

		processBuilder.redirectErrorStream(true);
		processBuilder.redirectOutput();

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.getProperty("line.separator"));

		}

		process.destroy();
		while (process.isAlive()) {
			System.out.println("Curl process is still alive");
		}
		int j = process.exitValue();
		if (j == 0) {
			logger.info("Uploaded: " + "Report_" + clientName + "_" + dateName+"_"+browserName+ ".html");
		} else {
			logger.fatal(
					"Unable to upload: " + "Report_" + clientName + "_" + dateName +"_"+browserName+ ".html" + "\nError Code: " + j);
		}

		//////////////////////////////////////////////////////////////////////////////////////////////

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

						path = content[i].getName();
						command = "curl -F file=@" + path + " " + serverIP;

						if (Environment.equalsIgnoreCase("test")) {
							command = command + "/Test%20Environment%20Results/Grabs/";
						} else if (Environment.equalsIgnoreCase("stage")) {
							if (System.getProperty("regression").equalsIgnoreCase("true")) {
								command = command + "/Regression%20Results/Stage%20Regression/Grabs/";
							} else {
								command = command + "/Stage%20Environment%20Results/Grabs/";
							}
						}

						else if (Environment.equalsIgnoreCase("uat")) {
							if (System.getProperty("regression").equalsIgnoreCase("true")) {
								command = command + "/Regression%20Results/UAT%20Regression/Grabs/";
							} else {
								command = command + "/UAT%20Environment%20Results/Grabs/";
							}
						} else if (Environment.equalsIgnoreCase("prod")) {
							command = command + "/Prod%20Environment%20Results/Grabs/";
						}

						processBuilder = new ProcessBuilder(command.split(" "));

						processBuilder.directory(new File(System.getProperty("user.dir") + "/FailedTestsScreenshots"));
						process = processBuilder.start();

						processBuilder.redirectErrorStream(true);
						processBuilder.redirectOutput();

						reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
						builder = new StringBuilder();
						line = null;
						while ((line = reader.readLine()) != null) {
							builder.append(line);
							builder.append(System.getProperty("line.separator"));

						}
						process.destroy();
						while (process.isAlive()) {

						}

						j = process.exitValue();
						if (j == 0) {
							logger.info("Uploaded: " + content[i].getName());
						} else {
							logger.fatal("Unable to upload: " + content[i].getName() + "\nError Code: " + j);
						}
					}
				}
			} else {
				logger.info("There is no screenshot available in the dir");
			}
		}

	}
}
