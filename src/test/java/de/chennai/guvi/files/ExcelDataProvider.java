package de.chennai.guvi.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataProvider {

    public static Workbook book;
    public static Sheet sheet;

    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(PathDirectory.exceldata);
            book = WorkbookFactory.create(file);
            sheet = book.getSheet(sheetName);
            
            // Calculate the number of rows and columns
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            Object[][] data = new Object[rowCount - 1][colCount]; // Exclude header row
            
            // Populate the data array
            for (int i = 1; i < rowCount; i++) { // Start from 1 to skip header
                for (int j = 0; j < colCount; j++) {
                    data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
                }
            }
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new Object[0][0]; // Return empty array on error
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0][0]; // Return empty array on error
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}