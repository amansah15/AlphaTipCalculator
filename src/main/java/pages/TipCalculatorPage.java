package pages;

import org.openqa.selenium.*;
import utils.DriverFactory;

public class TipCalculatorPage {

 WebDriver driver = DriverFactory.getDriver();

 public void enterBill(String bill) {
 WebElement el = driver.findElement(By.id("billAmount"));
 el.clear();
 el.sendKeys(bill);
 }

 public void selectCurrency(String c) {
 WebElement drop = driver.findElement(By.id("currency"));
 drop.click();
 drop.findElement(By.xpath("//option[@value='" + c + "']")).click();
 }

 public void enterPeople(String p) {
 WebElement el = driver.findElement(By.id("people"));
 el.clear();
 el.sendKeys(p);
 }

 public void selectRating(int r) {
 driver.findElement(By.cssSelector("button[data-value='" + r + "']")).click();
 }

 public void submit() {
 driver.findElement(By.id("calcBtn")).click();
 driver.findElement(By.id("confirmOk")).click();
 }

 public void reset() {
 driver.findElement(By.id("resetBtn")).click();
 }

 public String billError() {
 return driver.findElement(By.id("billAmountError")).getText();
 }

 public String peopleError() {
 return driver.findElement(By.id("peopleError")).getText();
 }
}