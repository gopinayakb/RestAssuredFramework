package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException {
		
		//path will contains the UserData file location.
		String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
		
		//xl is instance of XLUtility class and path is constructor parameter.
		XLUtility xl = new XLUtility(path);
		
		int rowCount = xl.getRowCount("Sheet1");//returns row count present in the sheet.
		int colCount = xl.getCellCount("Sheet1", 1);//returns columns present in the sheet.
		
		//2D array for storing the data extracted from the sheet.
		String[][] apiData = new String[rowCount][colCount];
		
		//Note: In excel sheet first row is referred as zero index and also first column is referred as zero index.
		//In sheet first row is header, so we don't need that row for that we take i=1.
		for(int i=1; i<=rowCount; i++) {
			
			for(int j=0; j<colCount; j++) {
				//In array index start from zero, but row i=1.So to make index to zero we give i-1.
				apiData[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		
		return apiData;//returning the 2D array apiData for test driven data.
	}
	
	
	@DataProvider(name="userName")
	public String[] getUserName() throws IOException {
		//path will contains the UserData file location.
				String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
				
				//xl is instance of XLUtility class and path is constructor parameter.
				XLUtility xl = new XLUtility(path);
				
				int rowCount = xl.getRowCount("Sheet1");//returns row count present in the sheet.
				
				//1D array for storing the user name from the sheet.
				String[] apidata = new String[rowCount];
				
				for(int i=1; i<=rowCount; i++) {
						apidata[i-1] = xl.getCellData("Sheet1", i,1);//here one is column number.
				}
				
				return apidata;
	}
	
	
}
