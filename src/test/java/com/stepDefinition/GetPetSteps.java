package com.stepDefinition;

import org.apache.logging.log4j.Logger;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.enums.PetStatus;
import com.init.LogInitializer;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import java.util.List;
import java.util.Map;

import com.store.Pets;

public class GetPetSteps {
	private Logger log;
	private Pets pet;
	
	public GetPetSteps() {
		log = LogInitializer.getLogger();
		pet = new Pets();
	}
	
	@Given("Pet with id {long} is existing")
	public void pet_with_id_is_existing(long id) {
		log.debug("Inside pet_with_id_is_existing");
		pet.getPetById(id);
	}
	
	@Given("Find Pets by status {string}")
	public void find_pets_by_status(String status) {
		//add tests to check that status is available, pending or sold only
		assertThat("Pet status not valid!", status, anyOf(
			    equalTo(PetStatus.AVAILABLE.getDescription()),
			    equalTo(PetStatus.PENDING.getDescription()),
			    equalTo(PetStatus.SOLD.getDescription())));
		pet.getPetsByStatus(status);
	}
	
	@When("User updates an existing pet with id {long}, category id {long}, category name {string}, name {string}, photoUrls {string}, tags {string}, status {string}")
	public void user_updates_an_existing_pet(long id, long categoryId, String categoryName, String name, String photoUrls, String tags, String status) {
		pet.updatePet(id, categoryId, categoryName, name, photoUrls, tags, status);
	}
	
	@Then("Pet record {long} is updated")
	public void pet_record_with_is_updated(Long id) {
		pet.verifyUpdatePet();
	}
	
	@When("User updates an existing pet with id {long}, name {string}, status {string}")
	public void user_updates_an_existing_pet_with_id_name_status(long id, String name, String status) {
		pet.updateSingleForm(id, name, status);
	}
	
	@Then("Pet record {int} is updated with name {string}, status {string}")
	public void pet_record_is_updated_with_name_status(Integer int1, String string, String string2) {
		pet.verifySingleForm();
	}

	@When("User adds an new pet with id {long}, category id {long}, category name {string}, name {string}, photoUrls {string}, tags {string}, status {string}")
	public void user_adds_an_new_pet(long id, long categoryId, String categoryName, String name, String photoUrls, String tags, String status) {
		pet.addPet(id, categoryId, categoryName, name, photoUrls, tags, status);
	}
	
	@Then("Pet record {int} is added")
	public void pet_record_is_added(Integer int1) {
		pet.verifyUpdatePet();
	}

	

}
