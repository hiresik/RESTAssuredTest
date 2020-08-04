package files;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	@SuppressWarnings("static-access")
	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception {

		String[][] tabArray = null;

		try {

			FileInputStream ExcelFile = new FileInputStream(FilePath);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);
//			ExcelWBook.setMissingCellPolicy(Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			System.out.println("ExcelUtils");
			System.out.println("----------");
			System.out.println("");

			int startRow = 1;

			int startCol = 0;

			int ci, cj;

			 int totalRows = ExcelWSheet.getLastRowNum();
//			int totalRows = 3;

			// you can write a function as well to get Column count

			// int totalCols = 7;
			int totalCols = ExcelWSheet.getRow(0).getLastCellNum();

			System.out.println("Total Rows: " + totalRows);
			System.out.println("Total Cols: " + totalCols);
			System.out.println("");

			tabArray = new String[totalRows][totalCols];

			ci = 0;

			for (int i = startRow; i <= startRow + totalRows - 1; i++, ci++) {

				cj = 0;
				System.out.print("Row " + i + " > ");

				for (int j = startCol; j < totalCols; j++, cj++) {

					tabArray[ci][cj] = getCellData(i, j);

					System.out.print(tabArray[ci][cj] + "|");

				}
				System.out.println("");
			}

		}

		catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}

	@SuppressWarnings({ "deprecation", "deprecation" })
	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			@SuppressWarnings("deprecation")
			int dataType = Cell.getCellType();

			String CellData = "";
			if (Cell != null) { // the cell does not have a blank value
				// Set the Map key to the crf header

				switch (Cell.getCellType()) {

				case XSSFCell.CELL_TYPE_BLANK:
					CellData = "";
					break;
				case XSSFCell.CELL_TYPE_ERROR:
					CellData = "";
					break;
				case XSSFCell.CELL_TYPE_STRING:
					CellData = Cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					// CellData = Double.toString(Cell.getNumericCellValue());
					if (DateUtil.isCellDateFormatted(Cell)) {
						// ??????
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						CellData = sdf.format(Cell.getDateCellValue());
						// CellData = Cell.getDateCellValue() + "";
					} else {
						// ????
						CellData = NumberToTextConverter.toText(Cell.getNumericCellValue());
					}
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					CellData = Boolean.toString(Cell.getBooleanCellValue());
					break;
				case XSSFCell.CELL_TYPE_FORMULA:
					CellData = Cell.getCellFormula();
					// CellData = readNumericCell(Cell);
					break;
				}

			} else {
				System.out.println("Outside if !=null ");
				CellData = null;
			}
			return CellData;
		} catch (Exception e) {

			System.out.println(e.getMessage());

			throw (e);

		}
	}
}
