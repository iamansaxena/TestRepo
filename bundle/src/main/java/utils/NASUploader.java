package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.aventstack.extentreports.utils.ExceptionUtil;

import core.Base;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class NASUploader extends Base {

	public static String getNASPath(String fileType, String date) {
		if (fileType.equals("report")) {
			if (isRegression == true) {
				if (Environment.equals("stage")) {
					return nasPath+"/stage_regression/" + date
							+ "/reports/";
				} else if (Environment.equals("uat")) {
					return nasPath+"/uat_regression/" + date
							+ "/reports/";
				} else {
					return nasPath+"/test_regression/" + date
							+ "/reports/";
				}
			} else {
				if (Environment.equals("stage")) {
					return nasPath+"/stage/" + date + "/reports/";
				} else if (Environment.equals("uat")) {
					return nasPath+"/uat/" + date + "/reports/";
				}
				if (Environment.equals("test")) {
					return nasPath+"/test/" + date + "/reports/";
				}
				else {
					return nasPath+"/test/" + date + "/reports/";
				}
			}
		} else if (fileType.equals("grab")) {
			if (isRegression == true) {
				if (Environment.equals("stage")) {
					return nasPath+"/stage_regression/" + date
							+ "/grabs/";
				} else if (Environment.equals("uat")) {
					return nasPath+"/uat_regression/" + date + "/grabs/";
				} else {
					return nasPath+"/test_regression/" + date + "/grabs/";
				}
			} else {
				if (Environment.equals("stage")) {
					return nasPath+"/stage/" + date + "/grabs/";
				} else if (Environment.equals("uat")) {
					return nasPath+"/uat/" + date + "/grabs/";
				}
				if (Environment.equals("test")) {
					return nasPath+"/test/" + date + "/grabs/";
				}
				else {
					return nasPath+"/test/" + date + "/grabs/";
				}
			}
		} else {
			return nasPath+"/dev_share/";
		}

	}

	private static NtlmPasswordAuthentication getNASAuth() {
		return new NtlmPasswordAuthentication("", System.getenv("msUsername"), System.getenv("msPassword"));
	}

	public static void uploadFile(File sourceFile, String nasPath) {
		try {
			SmbFile nasFile = new SmbFile(nasPath + sourceFile.getName(), getNASAuth());
			SmbFileOutputStream out = new SmbFileOutputStream(nasFile);
			FileInputStream fis = new FileInputStream(sourceFile);
			out.write(IOUtils.toByteArray(fis));
			out.close();
			logger.debug("Uploaded to NAS : " + nasFile.getDfsPath());
		} catch (Exception ex) {
			logger.error("Failed to Upload Resource to NAS  localFile : " + sourceFile.getAbsolutePath() + " to NAS "
					+ nasPath + sourceFile.getName() + "\n,  Exception " + ExceptionUtil.getStackTrace(ex));
		}

	}

	public static void uploadReport(File sourceFile, String fileType, String date) {
		try {
			String nasPath = getNASPath(fileType, date);
			createNASPath(nasPath);
			uploadFile(sourceFile, nasPath);
		} catch (Exception ex) {
			logger.error("Failed to Upload Resource to NAS  localFile :  " + sourceFile.getAbsolutePath()
					+ "\n,  Exception " + ExceptionUtil.getStackTrace(ex));
		}

	}

	public static void createNASPath(String nasPath) throws Exception {
		SmbFile nasFolder = new SmbFile(nasPath, getNASAuth());
		if (!nasFolder.isDirectory()) {
			nasFolder.mkdirs();
		}
	}

	public static File downloadUrlFileFromNAS() throws MalformedURLException, SmbException, IOException {
		String fileContent = IOUtils.toString(new SmbFileInputStream(new SmbFile(
				nasPath+"/dev_share/COMPONENT-URL-LIST-" + Environment + ".json", getNASAuth())),
				StandardCharsets.UTF_8.name());
		File file = new File(System.getProperty("user.dir") + "/" + "COMPONENT-URL-LIST-" + Environment + ".json");
		FileWriter a = new FileWriter(file);
		a.write(fileContent);
		a.flush();
		// System.out.println(fileContent);
		a.close();
		return file;
	}

}
