package com.stepDefinition;

import org.apache.logging.log4j.Logger;

import com.init.LogInitializer;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.store.Pets;

public class GetPetSteps {
	private Logger log;
	private Pets pet;
	
	public GetPetSteps() {
		log = LogInitializer.getLogger();
		pet = new Pets();
	}
	
	@Given("Get pet using petid")
	public void get_pet_using_petid() {
		log.debug("Inside get pets using petid");
		pet.getPetById(2);
	}

}
