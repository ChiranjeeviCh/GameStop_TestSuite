package com.gamestop.resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ReusableMethods {

	static XSSFWorkbook book;
	static FileInputStream fis;
	static FileOutputStream fout=null;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	static String path=System.getProperty("user.dir")+"\\Testdata\\ExportExcel.xlsx";
	public static int getRowCount(String sheetName) throws IOException {
		fis = new FileInputStream(path);
		book = new XSSFWorkbook(fis);
		int index = book.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = book.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
		}
	
	public static void createSheet(String sheetname) {
		// Create a Workbook
       
		/* CreationHelper helps us create instances for various things like DataFormat,
        Hyperlink, RichTextString etc in a format (HSSF, XSSF) independent way */
     CreationHelper createHelper = book.getCreationHelper();
     Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date futureDateTime = calendar.getTime();
		System.out.println(futureDateTime);
		DateFormat dateat2 = new SimpleDateFormat("dd"); 
        Date date2 = new Date();
        String today = dateat2.format(date2); 
     // Create a Sheet
     Sheet sheet = book.createSheet(sheetname+"_"+today);

     // Create a Font for styling header cells
     Font headerFont = book.createFont();
     headerFont.setBold(true);
     headerFont.setFontHeightInPoints((short) 14);
     headerFont.setColor(IndexedColors.GREEN.getIndex());

     // Create a CellStyle with the font
     CellStyle headerCellStyle = book.createCellStyle();
     headerCellStyle.setFont(headerFont);

     // Create a Row
     Row headerRow = sheet.createRow(0);

    /*// Creating cells
     for(int i = 0; i < columns.length; i++) {
         Cell cell = headerRow.createCell(i);
         cell.setCellValue(columns[i]);
         cell.setCellStyle(headerCellStyle);
     }*/
	}	
	
	

	// find whether sheets exists
			public boolean isSheetExist(String sheetName) {
				int index = book.getSheetIndex(sheetName);
				if (index == -1) {
					index = book.getSheetIndex(sheetName.toUpperCase());
					if (index == -1)
						return false;
					else
						return true;
				} else
					return true;
			}
	public boolean exportData(String path,String sheetName, String colName, int rowNum, String data) {
		try {
			//String path=System.getProperty("user.dir")+"\\Testdata\\ExportExcel.xlsx";
			fis = new FileInputStream(path);
			book = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = book.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sheet = book.getSheetAt(index);

			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}
			if (colNum == -1)
				return false;

			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			// cell style
			// CellStyle cs = workbook.createCellStyle();
			// cs.setWrapText(true);
			// cell.setCellStyle(cs);
			cell.setCellValue(data);

			fout = new FileOutputStream(path);

			book.write(fout);
			System.out.println("data entered successfully");

			fout.close();
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public void getValuefromdropDown(WebElement element, String Value) {
			//Base.wait5Seconds();
		   Select select = new Select(element);
	        select.selectByVisibleText(Value);
	}
	
	

}
