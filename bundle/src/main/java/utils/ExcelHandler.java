package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.Base;

public class ExcelHandler extends Base{
	public static int totalRows;
	public static int totalColumns;
	public static int totalSheets;
	private static XSSFWorkbook testData;
	public static XSSFSheet basesheet;
	public ArrayList<String> componentList;
	FileInputStream fis;
	public ArrayList<ConcurrentHashMap<String, String>> dataset = new ArrayList<>();
	public ConcurrentHashMap<String, String> coloumnDataSet;
	// public ConcurrentHashMap<String, ConcurrentHashMap<String, String>>
	// runtimeData = new ConcurrentHashMap<>();
	public static HashMap<String, String> subStringDataSet;
	public ArrayList<String> dataHeader;
	static Logger logger = LoggerLog4j.startTestCase(ExcelHandler.class);

	/////////////////////////////// Logic to load file
	public ExcelHandler(String path) {

		try {
			fis = new FileInputStream(path);
			testData = new XSSFWorkbook(fis);
			basesheet = testData.getSheetAt(0);
			totalRows = basesheet.getLastRowNum();
			totalColumns = basesheet.getRow(0).getLastCellNum() - 1;
			totalSheets = testData.getNumberOfSheets();

			System.out.println("\nSheet is having " + totalRows + " rows, " + totalColumns + " columns" + " and "
					+ totalSheets + " sheets");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	////////////////////////////////////////////////////////////////// TIll Here
	public static String getData(int sheetNo, int rowIndex, int columnIndex) {
		basesheet = testData.getSheetAt(sheetNo);
		String data = basesheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();

		return data;

	}

	//////////////////////////// Logic to read all the data
	@Deprecated
	public void getDataSet(int rowFrom, int rowTo, int columnFrom, int columnTo) {
		int columnIndex = 0;
		int rowIndex = 0;
		componentList = new ArrayList<>();
		basesheet = testData.getSheetAt(0);
		dataHeader = new ArrayList<String>();
		for (rowIndex = rowFrom; rowIndex <= rowFrom; rowIndex++) {
			for (columnIndex = columnFrom; columnIndex <= columnTo; columnIndex++) {
				dataHeader.add(basesheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue());
			}
		}
		for (rowIndex = rowFrom + 1; rowIndex <= rowTo; rowIndex++) {

			coloumnDataSet = new ConcurrentHashMap<>();
			for (columnIndex = columnFrom; columnIndex <= columnTo; columnIndex++) {
				basesheet.getRow(rowIndex).getCell(columnIndex).setCellType(CellType.STRING);
				String cellvalue = basesheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();

				if (columnIndex == 0 && rowIndex != 0) {
					componentList.add(cellvalue);
				}
				coloumnDataSet.put(dataHeader.get(columnIndex), cellvalue);

			}
			dataset.add(coloumnDataSet);

		}

	}

	@Deprecated
	public void replaceCellData(String ComponentName, String URL) {
		String url = "";
		int maxUrls = 3;

		for (int rowIndex = 1; rowIndex <= totalRows; rowIndex++) {
			for (int columnIndex = 0; columnIndex < 1; columnIndex++) {
				String cellValue = basesheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
				if (cellValue.equalsIgnoreCase(ComponentName)) {
					basesheet.getRow(rowIndex).getCell(columnIndex + 1).setCellValue("Checked");
					if (maxUrls > 0) {
						if (basesheet.getRow(rowIndex).getCell(columnIndex + 2).getStringCellValue()
								.equalsIgnoreCase("n")) {
							url = "";
							url = url + URL + ";";
						} else {
							url = basesheet.getRow(rowIndex).getCell(columnIndex + 2).getStringCellValue() + URL + ";";
							basesheet.getRow(rowIndex).getCell(columnIndex + 2).setCellValue(url);
						}
						maxUrls--;
					}
				}
			}
		}
	}

	public  void replaceRuntimeCellData(String ComponentName, String URL,
			ConcurrentHashMap<String, ConcurrentHashMap<String, String>> runtimeData) {
		String url = "";
		int maxUrls = 3;
		boolean linkExists;
		if (runtimeData.get(ComponentName).get("URL").contains(URL)) {
		} else {
			runtimeData.get(ComponentName).replace("Availability", "Checked");
			int urlCount = Integer.valueOf(runtimeData.get(ComponentName).get("URL COUNT"));
			if (urlCount <= maxUrls) {
				if (runtimeData.get(ComponentName).get("URL").equalsIgnoreCase("n")) {
					url = "";
					url = url + URL + ";";
					runtimeData.get(ComponentName).replace("URL", url);
					urlCount++;
					runtimeData.get(ComponentName).replace("URL COUNT", String.valueOf(urlCount));
					logger.info("Found '" + ComponentName + "' on page => " + URL);
				} else {

					ArrayList<Character> exists = new ArrayList<>();
					for (String a : getDomainName(runtimeData.get(ComponentName).get("URL")).split(";")) {
						if (!getDomainName(URL).equals(getDomainName(a))) {
							exists.add('f');

						} else {
							exists.add('t');
						}
						
					}if (!exists.contains('t')) {
						url = runtimeData.get(ComponentName).get("URL") + ";"+URL;

						runtimeData.get(ComponentName).replace("URL", url);
						urlCount++;
						runtimeData.get(ComponentName).replace("URL COUNT", String.valueOf(urlCount));
						logger.info("Found '" + ComponentName + "' on page => " + URL);
					}

					// if (!getDomainName(URL)
					// .equals(getDomainName(runtimeData.get(ComponentName).get("URL")))) {
					//
					//
					// }

				}

			}
		}
	}

	public static boolean checkAvailability() {
		boolean status = true;
		int count = 0;
		for (int rowIndex = 1; rowIndex < totalRows; rowIndex++) {

			for (int columnIndex = 1; columnIndex < 2; columnIndex++) {
				String cellValue = basesheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();
				if (cellValue.equalsIgnoreCase("Unchecked")) {
					status = false;
					break;

				}
				if (status == false)
					break;
			}
			count++;
		}
		return status;
	}

	public ConcurrentHashMap<String, ConcurrentHashMap<String, String>> loadDataSet(int rowFrom, int rowTo,
			int columnFrom, int columnTo) {
		ConcurrentHashMap<String, ConcurrentHashMap<String, String>> runtimeData;
		int columnIndex = 0;
		int rowIndex = 0;
		String compName = null;
		componentList = new ArrayList<>();
		basesheet = testData.getSheetAt(0);

		runtimeData = new ConcurrentHashMap<>();

		dataHeader = new ArrayList<String>();
		for (rowIndex = rowFrom; rowIndex <= rowFrom; rowIndex++) {
			for (columnIndex = columnFrom; columnIndex <= columnTo; columnIndex++) {
				dataHeader.add(basesheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue());
			}
		}
		for (rowIndex = rowFrom + 1; rowIndex <= rowTo; rowIndex++) {
			// subStringDataSet = new HashMap<>();
			if (rowIndex < rowTo) {
				coloumnDataSet = new ConcurrentHashMap<>();
				for (columnIndex = columnFrom; columnIndex <= columnTo; columnIndex++) {
					basesheet.getRow(rowIndex).getCell(columnIndex).setCellType(CellType.STRING);
					String cellvalue = basesheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();

					if (columnIndex == 0 && rowIndex != 0) {
						componentList.add(cellvalue);
						compName = cellvalue;
					}
					coloumnDataSet.put(dataHeader.get(columnIndex), cellvalue);

				}
				runtimeData.put(compName, coloumnDataSet);
				dataset.add(coloumnDataSet);
			}
		}
		return runtimeData;
	}

}