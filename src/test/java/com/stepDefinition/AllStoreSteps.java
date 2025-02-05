package com.stepDefinition;

import com.pet.Petstore;
import com.store.Inventory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AllStoreSteps {
	private Petstore pet;
	private Inventory inventory;
	
	public AllStoreSteps() {
		pet = new Petstore();
		inventory = new Inventory();
	}

	@When("User sends an inventory request")
	public void user_sends_an_inventory_request() {
		inventory.getStoreInventory();
	}
	
	@Then("A map of status codes to quantities is returned")
	public void a_map_of_status_codes_to_quantities_is_returned() {

	}
	
	@When("User places a store order with petId {long} quantity {int} shipdate {string} status {string} complete {string} code {int}")
	public void user_places_a_store_order_with_quantity_shipdate_status_complete_true(long petId, int quantity, String shipDate, String status, String complete, int code) {
		boolean parsedComplete = Boolean.parseBoolean(complete);
		inventory.storeRequestBuilder(0, petId, quantity, shipDate, status, parsedComplete, code);
		inventory.postStoreOrder();
	}
	
	@When("User places a store order with id {long} petId {long} quantity {int} shipdate {string} status {string} complete {string} code {int}")
	public void user_places_a_store_order_with_id_quantity_shipdate_status_complete_true(long id, long petId, int quantity, String shipDate, String status, String complete, int code) {
		boolean parsedComplete = Boolean.parseBoolean(complete);
		inventory.storeRequestBuilder(id, petId, quantity, shipDate, status, parsedComplete, code);
		inventory.postStoreOrder();
	}
	
	@Then("Order is created and retrieved")
	public void order_is_created_and_retrieved() {
		inventory.verifyAddOder();
	}

	@When("User retrieves an order with id {long} , code {int}")
	public void user_retrieves_an_order_with_id_code(long id, int code) {
		inventory.getStoreOrder(id,code);
	}
	
	@Given("User deletes an order with id {long}, code {int}")
	public void user_deletes_an_order_with_id_code(long id, int code) {
		inventory.deleteOrder(id,code);
	}
	@Then("Order record {int} is deleted")
	public void order_record_is_deleted(Integer int1) {

	}











}
