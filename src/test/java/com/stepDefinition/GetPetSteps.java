package com.stepDefinition;

import org.apache.logging.log4j.Logger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
	
	@Given("Pet with id {int} is existing")
	public void pet_with_id_is_existing(int petId) {
		log.debug("Inside pet_with_id_is_existing");
		pet.getPetById(petId);
	}
	
	@Given("Find Pets by status {string}")
	public void find_pets_by_status(String status) {
		//add tests to check that status is available, pending or sold only
		assertThat("Pet status not valid!", status, anyOf(
			    equalTo("available"),
			    equalTo("pending"),
			    equalTo("sold")));
		pet.getPetsByStatus(status);
	}
	
	@When("User updates an existing pet")
	public void user_updates_an_existing_pet() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("Pet record with {int} is updated")
	public void pet_record_with_is_updated(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	

}
