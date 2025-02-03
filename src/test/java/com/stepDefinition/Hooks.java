package com.stepDefinition;

import java.net.MalformedURLException;
import org.apache.logging.log4j.Logger;

import com.init.LogInitializer;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	private Logger log = LogInitializer.getLogger();
	
	@Before
	public void setup(Scenario scenario) throws MalformedURLException {
		log.info("SCENARIO: " + scenario.getName().toUpperCase());
		log.info("-----START TEST-----");
	}

	
	@After
	public void tearDown(Scenario scenario) {
		log.debug("-----Teardown Steps-----");
		log.info(scenario.getName() + ": " + scenario.getStatus().toString().toUpperCase());
		log.debug("Scenario tags: " + scenario.getSourceTagNames());
		log.debug("Scenario failed: " + scenario.isFailed());

		scenario.log(scenario.getName() + ": " + scenario.getStatus().toString().toUpperCase());
		
	}

}
