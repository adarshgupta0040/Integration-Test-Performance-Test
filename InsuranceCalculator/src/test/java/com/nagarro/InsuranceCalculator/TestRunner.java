package com.nagarro.InsuranceCalculator;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "insurance.feature",
    glue = "com.nagarro.InsuranceCalculator",
    plugin = {
        "pretty:target/cucumber-reports/pretty.txt",
        "progress:target/cucumber-reports/progress.txt",
        "html:target/cucumber-reports/html",
        "json:target/cucumber-reports/cucumber.json"
    },
    monochrome = true
)
public class TestRunner {
}

//import org.junit.runner.RunWith;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(features="insurance.feature", glue="com.nagarro.InsuranceCalculator")
//public class TestRunner {
//
//}