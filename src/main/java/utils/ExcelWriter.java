package utils;

import java.io.*;
import org.apache.poi.ss.usermodel.*;

public class ExcelWriter {

 public static void write(String file, int row, String result) {

 try {

 FileInputStream fis = new FileInputStream(file);
 Workbook wb = WorkbookFactory.create(fis);
 Sheet s = wb.getSheet("TestData");

 s.getRow(row).createCell(6).setCellValue(result);

 FileOutputStream fos = new FileOutputStream(file);
 wb.write(fos);

 wb.close();

 } catch (Exception e) {
 e.printStackTrace();
 }
 }
}
