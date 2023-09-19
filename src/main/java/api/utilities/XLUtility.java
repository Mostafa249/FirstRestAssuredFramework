package api.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {
    public FileInputStream fileInput;
    public FileOutputStream fileOutput;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFCell cell;
    public XSSFRow row;
    public XSSFCellStyle style;
    String path;

    public XLUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fileInput.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rowNum) throws IOException {
        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fileInput.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rowNum);
        cell = row.getCell(colNum);
        DataFormatter formatter = new DataFormatter();
        String data;
        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }
        workbook.close();
        fileInput.close();
        return data;
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        File xlFile = new File(path);
        if (!xlFile.exists()) { //if file not exist then create file
            workbook = new XSSFWorkbook();
            fileOutput = new FileOutputStream(path);
            workbook.write(fileOutput);
        }
        fileInput = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInput);
        if (workbook.getSheetIndex(sheetName) == -1) { //if sheet not exists then create sheet
            workbook.createSheet(sheetName);
            sheet = workbook.getSheet(sheetName);
        }
        if (sheet.getRow(rowNum) == null) //if row not exist then create row
            sheet.createRow(rowNum);
        row = sheet.getRow(rowNum);

        cell = row.createCell(colNum);
        cell.setCellValue(data);
        fileOutput = new FileOutputStream(path);
        workbook.write(fileOutput);
        workbook.close();
        fileInput.close();
        fileOutput.close();
    }
}
