package org.learn.stedefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

    private static ExtentReports extent;
    private static ExtentTest test;

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Before");
        if (extent == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        // Start logging the test scenario
        test = extent.createTest(scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            test.fail("Scenario failed: " + scenario.getName());
        } else {
            test.pass("Scenario passed: " + scenario.getName());
        }
        // End the test and flush the report
        extent.flush();
    }
}
