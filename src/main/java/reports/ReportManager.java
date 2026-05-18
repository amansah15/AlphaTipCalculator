package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportManager {

 private static ExtentReports extent;

 public static ExtentReports getReportInstance() {

 if (extent == null) {

// ExtentSparkReporter reporter =
// new ExtentSparkReporter("test-output/Report.html");
		 ExtentSparkReporter reporter =
		    new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/Report.html");

 reporter.config().setDocumentTitle("Tip Calculator Report");
 reporter.config().setReportName("Automation Results");

 extent = new ExtentReports();
 extent.attachReporter(reporter);
 }

 return extent;
 }
}