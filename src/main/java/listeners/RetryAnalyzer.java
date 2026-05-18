package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

 int count = 0;
 int maxTry = 1;


 @Override
 public boolean retry(ITestResult result) {

 if (count < maxTry) {
 count++;
 

//✅ ADD THIS LINE (IMPORTANT)
          System.out.println("Retrying test: " + result.getName() + 
                             " | Attempt: " + (count + 1));

 
 return true;
 }
 return false;
 }
}
