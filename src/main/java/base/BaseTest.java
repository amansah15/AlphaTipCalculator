package base;

import org.testng.annotations.*;
import utils.DriverFactory;

public class BaseTest {

 @Parameters("browser")
 @BeforeClass
// public void setup(String browser) {
 public void setup(@Optional("chrome") String browser) {

 DriverFactory.initDriver(browser);
 DriverFactory.getDriver().get("https://tipcal-navy.vercel.app/");
 }
 
 

//✅ ADD HERE (after setup method)
@BeforeMethod
public void beforeScenario() {

DriverFactory.getDriver().get("https://tipcal-navy.vercel.app/");
}

// ✅ ADD HERE
@AfterMethod
public void afterScenario() {
// optional for cucumber reset handling
}


 @AfterClass
 public void tearDown() {

 DriverFactory.quitDriver();
 }
}