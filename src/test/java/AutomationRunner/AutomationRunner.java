package AutomationRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\main\\resources\\AutomationFeatures", glue = "EcommerceStepDefinitions",plugin = { "pretty", "html:target/Ecommerce_reports/4report.html" },monochrome = true, tags = "@EcommerceAutomationTesting")

public class AutomationRunner {
}
