package LetsTrySelenium.Rainbow_Selenium_framework.excelRead;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.jna.platform.win32.WinDef.WPARAM;

public class ExcelRead {

	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public String datasets [][];
	public FileOutputStream fiout;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	
	public ExcelRead(String path) throws IOException {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	public String[][] getData(String sheetName)
	{
	   sheet = workbook.getSheet(sheetName);
	   
	   int total_row = sheet.getLastRowNum();
	  
	
	   int total_column = sheet.getRow(0).getLastCellNum();
	  
	   datasets = new String[total_row][total_column];
	   
	   for (int i = 0; i <= total_row-1; i++) {
		   
		   row = sheet.getRow(i+1);
		   
		   for (int j = 0; j < total_column; j++) {
			XSSFCell cell = row.getCell(j);
			
			if (cell.getCellType()==cell.CELL_TYPE_STRING) {
				datasets[i][j]=cell.getStringCellValue();
			}
			else if (cell.getCellType()==cell.CELL_TYPE_NUMERIC) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				datasets[i][j] = cellText;
			}
			else {
				String cellText = String.valueOf(cell.getBooleanCellValue());
				datasets[i][j] = cellText;
			}
			   
		}
		
	}
		
	return datasets;
		
	}
	
	public void getCellData()
	{
		
		
		
	}

}
