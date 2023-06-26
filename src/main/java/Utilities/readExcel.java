package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class readExcel {

	public static final String currentDir = System.getProperty("user.dir");

	
	public static ArrayList<String> readColumnFromExcel(String fileName, int sheetname, int columnIndex) throws IOException {
        ArrayList<String> columnData = new ArrayList<String>();
		String Data= null;
		 String ExcelPath_ElementLocation =currentDir + "\\src\\main\\java\\Utilities\\"+fileName;

			File file = new File(ExcelPath_ElementLocation);   //creating a new file instance  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet1 = wb.getSheetAt(sheetname);
            for (Row row : sheet1) {
                Cell cell = (Cell) row.getCell(columnIndex);
                if (cell != null) {
                    columnData.add(cell.toString());
                }
            	
            }
        return columnData;
    }
	
	public  ArrayList<String> convert( ArrayList<String> excelData)  {
	
        ArrayList<String> convertedList = new ArrayList<String>();
        for (String str : excelData) {
            double num = Double.parseDouble(str);
            convertedList.add(Double.toString(num));
        }
		
		return convertedList;
		
		
		
		
	}

}
