package cucumber.Options;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;

@CucumberOptions(features="src/test/java/features", glue="stepDefinations"
,plugin = "json:target/jsonReports/cucumber-report.json")
public class TestRunner extends AbstractTestNGCucumberTests {
  @AfterClass
    public void reportName(){
        System.out.print("Cucumber Test Report ------------->" +
                System.getProperty("user.dir") + "/target/overview-features.html");
    }
}
