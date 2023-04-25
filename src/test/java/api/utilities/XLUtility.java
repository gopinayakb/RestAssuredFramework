package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	public String path;
	
	XLUtility(String path){
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		
		wb.close();
		fi.close();
		
		return rowCount;
	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		
		wb.close();
		fi.close();
		
		return cellCount;
		
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		//DataFromatter contains the methods for formatting the value stored in cell.
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);//returns the cell data as String regardless of the cell type.
		}
		catch(Exception e){
			data = "";
		}
		
		wb.close();
		fi.close();
		
		return data;
	}
	
	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		File xlfile = new File(path);
		//if the given path doesn't have excel file then it will creates new one, with same name & in the same location.
		if(!xlfile.exists()) {
			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		
		//if the workbook don't have the sheet then it will creates the new sheet.
		if(wb.getSheetIndex(sheetName)==-1) {
			wb.createSheet(sheetName);
			sheet = wb.getSheet(sheetName);
		}
		
		//if the sheet not have active rows then it will creates new rows based on the the given no.of rows.
		if(sheet.getRow(rowNum)==null) {
			sheet.createRow(rowNum);
		}
		
		row = sheet.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(data);//inserts the data in the given cell
		fo = new FileOutputStream(path);//fo creates the connection to the file to write mode.
		wb.write(fo); //it writes the data to an output stream.
		wb.close();
		fi.close();
		fo.close();
	}
}
