package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadTestDataXLS {

	public static String[][] readXls(String fileName, String tabName) throws IOException {

		String path = "C:\\Users\\Prasad Chitale\\Documents\\java_selenium\\HybridFramework_Practice_Prasad_C\\resources\\testdata\\"
				+ fileName + "";
		File file = new File(path);
		FileInputStream inputstream = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
		Sheet sheet = workbook.getSheet(tabName);
		int NumberOfRows = sheet.getLastRowNum();
		System.out.println("Number of rows in test data XLS: " + NumberOfRows);
		int numberOfColumns = sheet.getRow(0).getLastCellNum();
		System.out.println("Number of columns in test data XLS: " + numberOfColumns);

		String data[][] = new String[NumberOfRows][numberOfColumns];

		for (int rowIndex = 1; rowIndex <= NumberOfRows; rowIndex++) {

			for (int columnIndex = 0; columnIndex < numberOfColumns; columnIndex++) {
				Cell cell = sheet.getRow(rowIndex).getCell(columnIndex);
				if (cell.getCellType() == CellType.NUMERIC) {
					double temp = cell.getNumericCellValue();
					int intTemp = (int) temp;
					String value = String.valueOf(intTemp);
					data[rowIndex - 1][columnIndex] = value;
				} else {
					String value = cell.getStringCellValue();
					data[rowIndex - 1][columnIndex] = value;
				}
			}
		}

		return data;
	}

}
