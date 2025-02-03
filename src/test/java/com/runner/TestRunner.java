package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", 
				glue = { "com.stepDefinition" }, 
				plugin = {/* "pretty", */
						"html:target/cucumber/report.html", 
						"json:target/cucumber/report.json"/*,
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"*/ }, //enable for extentreports, add -Dorg.freemarker.loggerLibrary=none in cmd 
				//dryRun = true,
				tags = "@test", monochrome = true)

public class TestRunner {

}
