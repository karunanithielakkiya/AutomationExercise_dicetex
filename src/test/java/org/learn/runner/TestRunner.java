package org.learn.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"org.learn.stedefinitions"},
        plugin = {"pretty",
                "html:target/cucumber-reports.html",
                "timeline:test-output-thread/"
        }

)

public class TestRunner {


}
