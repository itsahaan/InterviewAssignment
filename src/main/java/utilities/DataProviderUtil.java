package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUtil {

	/**
	 * Provides test data for login tests by reading data from an Excel file.
	 *
	 * @return A two-dimensional object array containing login test data.
	 * @throws IOException If the Excel file cannot be read.
	 */
	@DataProvider(name = "loginData")
	public Object[][] loginData() throws IOException {
		String excelFilePath = System.getProperty("user.dir") + "/src/test/resources/LoginData.xlsx";
		return readExcelData(excelFilePath, "LoginData");
	}

	/**
	 * Reads data from the specified Excel file and sheet, returning it as a
	 * two-dimensional object array.
	 *
	 * @param filePath  The path to the Excel file.
	 * @param sheetName The name of the sheet to read data from.
	 * @return A two-dimensional object array containing the data from the Excel
	 *         sheet.
	 * @throws IOException If the Excel file cannot be read.
	 */

	private Object[][] readExcelData(String filePath, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetName);

		List<Object[]> data = new ArrayList<Object[]>();
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next(); // Skip the header row

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			String username = getCellValue(row.getCell(0));
			String password = getCellValue(row.getCell(1));
			boolean isSuccessExpected = row.getCell(2).getBooleanCellValue();
			data.add(new Object[] { username, password, isSuccessExpected });
		}

		workbook.close();
		fis.close();

		return data.toArray(new Object[0][]);
	}

	/**
	 * Retrieves the cell value as a string. Returns an empty string if the cell is
	 * null or blank.
	 *
	 * @param cell The cell to retrieve the value from.
	 * @return The string value of the cell, or an empty string if the cell is null
	 *         or blank.
	 */

	private String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		} else {
			return cell.getStringCellValue();
		}

	}
}