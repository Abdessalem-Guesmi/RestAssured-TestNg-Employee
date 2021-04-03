package com.restAssuredTestng.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelUtils {
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelUtils(String excelPath, String sheetName) {
		try {

			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// getRowCount();
		// getCellDataString(0,0);
		// getCellDataNumber(1,1);
	}

	public static int getRowCount() {
		int rowCount = 0;
		try {

			rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("No of rows : " + rowCount);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			;
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return rowCount;

	}

	public static int getColCount() {
		int colCount = 0;
		try {

			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("No of columns : " + colCount);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			;
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return colCount;

	}

	public static String getCellDataString(int rowNum, int colNum) {
		String cellData = null;
		try {
			DataFormatter formatter = new DataFormatter();
			cellData = formatter.formatCellValue(sheet.getRow(rowNum).getCell(colNum));
			// list.add(val); //Adding value to list

			// cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			// System.out.println(cellData);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			;
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return cellData;
	}

	public static void getCellDataNumber(int rowNum, int colNum) {
		try {

			double cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			System.out.println(cellData);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());

			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}

	@DataProvider(name = "test1data")
	public Object[][] getData() {
		String projectPath = System.getProperty("user.dir");
		String excelPath = "src/main/resources/data/data.xlsx";
		Object data[][] = testData(excelPath, "sheet2");
		return data;

	}

	public static Object[][] testData(String excelPath, String sheetName) {

		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object data[][] = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {

				String cellData = excel.getCellDataString(i, j);
				// System.out.print(cellData+" | ");
				data[i - 1][j] = cellData;

			}
			// System.out.println();
		}
		return data;

	}

}
