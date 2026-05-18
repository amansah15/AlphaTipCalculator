package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

 private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

 public static void initDriver(String browser) {

 if (browser.equalsIgnoreCase("chrome")) {
 driver.set(new ChromeDriver());
 System.out.println("✅ Chrome Launched");
 } 
 else {
 driver.set(new EdgeDriver());
 System.out.println("✅ Edge Launched");
 }
 }

 public static WebDriver getDriver() {
 return driver.get();
 }

 public static void quitDriver() {
 if (driver.get() != null) {
 driver.get().quit();
 driver.remove();
 System.out.println("✅ Browser Closed");
 }
 }
}