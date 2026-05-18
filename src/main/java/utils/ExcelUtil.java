package utils;

import java.io.InputStream;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtil {

 public static Object[][] getTestData(String file, String sheet) {

 try {

 InputStream is = ExcelUtil.class.getClassLoader()
 .getResourceAsStream(file);

 Workbook wb = WorkbookFactory.create(is);
 Sheet sh = wb.getSheet(sheet);

 int rows = sh.getPhysicalNumberOfRows();
 int cols = sh.getRow(0).getPhysicalNumberOfCells();

 Object[][] data = new Object[rows - 1][cols];

 for (int i = 1; i < rows; i++) {
 Row r = sh.getRow(i);

 for (int j = 0; j < cols; j++) {
 data[i - 1][j] = r.getCell(j).toString();
 }
 }

 return data;

 } catch (Exception e) {
 e.printStackTrace();
 return null;
 }
 }
}