package com.stepDefinition;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.enums.PetStatus;
import com.pet.Petstore;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AllPetSteps {
	private Petstore pet;
	
	public AllPetSteps() {
		pet = new Petstore();
	}
	
	@Given("Pet with id {long} is existing, code {int}")
	public void pet_with_id_is_existing(long id, int code) {
		pet.getPetById(id, code);
	}
	
	@Then("Pet record is existing")
	public void pet_record_is_existing() {
		pet.getPetByGeneratedId();
	}
	
	@Given("Find Pets by status {string}, code {int}")
	public void find_pets_by_status(String status, int code) {
		assertThat("Pet status not valid!", status, anyOf(
			    equalTo(PetStatus.AVAILABLE.getDescription()),
			    equalTo(PetStatus.PENDING.getDescription()),
			    equalTo(PetStatus.SOLD.getDescription())));
		pet.getPetsByStatus(status, code);
	}
	
	@When("User updates an existing pet with id {long}, category id {long}, category name {string}, name {string}, photoUrls {string}, tags {string}, status {string}, code {int}")
	public void user_updates_an_existing_pet(long id, long categoryId, String categoryName, String name, String photoUrls, String tags, String status, int code) {
		pet.petRequestBuilder(id, categoryId, categoryName, name, photoUrls, tags, status, code);
		pet.updatePet();
	}
	
	@Then("Pet record {long} is updated")
	public void pet_record_with_is_updated(Long id) {
		pet.verifyUpdatePet();
	}
	
	@When("User updates an existing pet with id {long}, name {string}, status {string}, code {int}")
	public void user_updates_an_existing_pet_with_id_name_status(long id, String name, String status, int code) {
		pet.updateSingleForm(id, name, status, code);
	}
	
	@Then("Pet record {long} is updated with name {string} , status {string}")
	public void pet_record_is_updated_with_name_status(long id, String name, String status) {
		pet.verifySingleForm();
	}

	@When("User adds an new pet with category id {long}, category name {string}, name {string}, photoUrls {string}, tags {string}, status {string}, code {int}")
	public void user_adds_an_new_pet(long categoryId, String categoryName, String name, String photoUrls, String tags, String status, int code) {
		pet.petRequestBuilder(0, categoryId, categoryName, name, photoUrls, tags, status, code);
		pet.addPet();
	}
	
	@When("User adds an new pet with id {long} category id {long}, category name {string}, name {string}, photoUrls {string}, tags {string}, status {string}, code {int}")
	public void user_adds_an_new_pet_with_id(long id, long categoryId, String categoryName, String name, String photoUrls, String tags, String status, int code) {
		pet.petRequestBuilder(id, categoryId, categoryName, name, photoUrls, tags, status, code);
		pet.addPet();
	}
	
	@Then("Pet record is added")
	public void pet_record_is_added() {
		pet.verifyAddPet();
	}
	
	@Given("User deletes a pet with id {long}, code {int}")
	public void user_deletes_a_pet_with_id(long id, int code) {
		pet.deletePet(id, code);
	}
	@Then("Pet record {long} is deleted")
	public void pet_record_is_deleted(Long id) {
		pet.verifyPetNotFound();
	}

	@When("User uploads an image to id {long}, additionalMetadata {string}, file {string}, code {int}")
	public void user_uploads_an_image_to_id_additional_metadata_file_code(long id, String additionalMetadata, String file, int code) {
		pet.uploadImage(id, additionalMetadata, file, code);
	}

}
