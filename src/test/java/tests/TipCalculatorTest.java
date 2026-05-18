//package tests;
//
//import org.testng.annotations.*;
//import org.testng.Assert;
//import java.time.Duration;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.*;
//
//import base.BaseTest;
//import pages.TipCalculatorPage;
//import utils.*;
//
//public class TipCalculatorTest extends BaseTest {
//
// @DataProvider(name = "data")
// public Object[][] data() {
// return ExcelUtil.getTestData("TipCalculatorData.xlsx", "TestData");
// }
//
// private void handleOverlays() {
//
// WebDriver driver = DriverFactory.getDriver();
//
// try {
// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("celebrateOverlay")));
// } catch (Exception e) {}
//
// try {
// WebElement cancel = driver.findElement(By.id("feedbackCancel"));
// if (cancel.isDisplayed()) {
// cancel.click();
// }
// } catch (Exception e) {}
// }
//
// @Test(dataProvider = "data")
// public void runTest(Object... row) {
//
// String tcId = row[0].toString();
// String bill = row[1].toString();
// String currency = row[2].toString();
// String people = row[3].toString();
// String rating = row[4].toString();
// String type = row[5].toString();
//
// TipCalculatorPage page = new TipCalculatorPage();
//
// synchronized (System.out) {
// System.out.println("\n=========================================");
// System.out.println("🧪 TEST CASE: " + tcId);
// System.out.println("📊 DATA → Bill=" + bill + ", Currency=" + currency +
// ", People=" + people + ", Rating=" + rating);
// }
//
// try {
//
// handleOverlays();
//
// if (!bill.equals("NA")) page.enterBill(bill);
// if (!currency.equals("NA")) page.selectCurrency(currency);
// if (!people.equals("NA")) page.enterPeople(people);
//
// if (!rating.equals("NA")) {
// int r = (int) Double.parseDouble(rating);
// page.selectRating(r);
// }
//
// page.submit();
//
// handleOverlays();
//
// // ✅ VALIDATION
// if (type.equals("InvalidBill") && page.billError().isEmpty())
// throw new Exception("Bill validation missing");
//
// if (type.equals("InvalidPeople") && page.peopleError().isEmpty())
// throw new Exception("People validation missing");
//
// synchronized (System.out) {
// System.out.println("✅ RESULT: PASS");
// System.out.println("✔ Reason: " + type + " working correctly");
// System.out.println("=========================================");
// }
//
// } catch (Exception e) {
//
// String path = ScreenshotUtil.capture(tcId);
//
// synchronized (System.out) {
// System.out.println("❌ RESULT: FAIL");
// System.out.println("⚠ Reason: " + e.getMessage());
// System.out.println("📸 Screenshot: " + path);
// System.out.println("=========================================");
// }
//
// Assert.fail(tcId + " FAILED");
// }
//
// // ✅ RESET AFTER EACH TEST (VERY IMPORTANT)
// try {
// page.reset();
// } catch (Exception e) {}
// }
//}






package tests;

import org.testng.annotations.*;
import org.testng.Assert;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import base.BaseTest;
import pages.TipCalculatorPage;
import utils.*;

// ✅ NEW IMPORTS FOR REPORT
import reports.ReportManager;
import com.aventstack.extentreports.*;

public class TipCalculatorTest extends BaseTest {

    // ✅ REPORT OBJECTS
    ExtentReports extent;
    ExtentTest test;

    // ✅ INITIALIZE REPORT
    @BeforeClass
    public void setupReport() {
        extent = ReportManager.getReportInstance();
    }

    // ✅ FLUSH REPORT
    @AfterClass
    public void flushReport() {
        extent.flush();
    }

    @DataProvider(name = "data")
    public Object[][] data() {
        return ExcelUtil.getTestData("TipCalculatorData.xlsx", "TestData");
    }

    private void handleOverlays() {

        WebDriver driver = DriverFactory.getDriver();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("celebrateOverlay")));
        } catch (Exception e) {}

        try {
            WebElement cancel = driver.findElement(By.id("feedbackCancel"));
            if (cancel.isDisplayed()) {
                cancel.click();
            }
        } catch (Exception e) {}
    }

//    @Test(dataProvider = "data")
    @Test(dataProvider = "data", retryAnalyzer = listeners.RetryAnalyzer.class)
    
    public void runTest(Object... row) {

        String tcId = row[0].toString();
        String bill = row[1].toString();
        String currency = row[2].toString();
        String people = row[3].toString();
        String rating = row[4].toString();
        String type = row[5].toString();

        TipCalculatorPage page = new TipCalculatorPage();

        // ✅ CREATE TEST IN REPORT
        test = extent.createTest(tcId);

        // ✅ LOG INPUT DATA
        test.info("DATA → Bill=" + bill + ", Currency=" + currency +
                ", People=" + people + ", Rating=" + rating);

        synchronized (System.out) {
            System.out.println("\n=========================================");
            System.out.println("🧪 TEST CASE: " + tcId);
            System.out.println("📊 DATA → Bill=" + bill + ", Currency=" + currency +
                    ", People=" + people + ", Rating=" + rating);
        }

        try {

            handleOverlays();

            if (!bill.equals("NA")) page.enterBill(bill);
            if (!currency.equals("NA")) page.selectCurrency(currency);
            if (!people.equals("NA")) page.enterPeople(people);

            if (!rating.equals("NA")) {
                int r = (int) Double.parseDouble(rating);
                page.selectRating(r);
            }

            page.submit();

            handleOverlays();

            // ✅ VALIDATION (UNCHANGED)
            if (type.equals("InvalidBill") && page.billError().isEmpty())
                throw new Exception("Bill validation missing");

            if (type.equals("InvalidPeople") && page.peopleError().isEmpty())
                throw new Exception("People validation missing");

            // ✅ REPORT PASS
            test.pass("✅ Test Passed: " + type);

            synchronized (System.out) {
                System.out.println("✅ RESULT: PASS");
                System.out.println("✔ Reason: " + type + " working correctly");
                System.out.println("=========================================");
            }

        } catch (Exception e) {

            // ✅ TAKE SCREENSHOT
            String path = ScreenshotUtil.capture(tcId);

            // ✅ REPORT FAIL
            test.fail("❌ " + e.getMessage());

            try {
                test.addScreenCaptureFromPath(path);
            } catch (Exception ex) {}

            synchronized (System.out) {
                System.out.println("❌ RESULT: FAIL");
                System.out.println("⚠ Reason: " + e.getMessage());
                System.out.println("📸 Screenshot: " + path);
                System.out.println("=========================================");
            }

            Assert.fail(tcId + " FAILED");
        }

        // ✅ RESET AFTER EACH TEST
        try {
            page.reset();
        } catch (Exception e) {}
    }
}

