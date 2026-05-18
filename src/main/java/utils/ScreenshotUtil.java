package utils;

import org.openqa.selenium.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

 public static String capture(String tcId) {

 try {
 new File("screenshots").mkdirs();

 File src = ((TakesScreenshot) DriverFactory.getDriver())
 .getScreenshotAs(OutputType.FILE);

 String path = "screenshots/" + tcId + ".png";

 FileUtils.copyFile(src, new File(path));

 return path;

 } catch (Exception e) {
 return "Screenshot Failed";
 }
 }
}