package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"_UI/step_definition", "_API/step_definition"},
        plugin = {
                "json:target/cucumber.json",
                "rerun:target/re-run.txt"
        },
        tags = "@userMgt_RegistrationAPI or @userRegistrationAPI or @userMgt"
        ,dryRun = false
)
public class RunnerTest extends AbstractTestNGCucumberTests {
        @DataProvider(parallel = true)
        public Object[][] scenarios(){
                return super.scenarios();
        }
}
