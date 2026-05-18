package runner;

import io.cucumber.testng.*;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        plugin = {
                "pretty",
                "html:test-output/cucumber-report.html"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)   // ✅ parallel execution
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
