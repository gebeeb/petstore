package com.store;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.init.LogInitializer;
import com.util.PetstoreUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Inventory {
	protected Logger log;
	String baseUri = "https://petstore.swagger.io/v2";
	String basePath = "/store";
	Response response;
	
	Store store;
	
	long id;
	long petId;
	int quantity;
	String shipDate;
	String status;
	Boolean complete;
	int code;
	
	public Inventory() {
		log = LogInitializer.getLogger();
	}

	public void getStoreInventory() {
		log.debug("Getting pet inventory");
	    response = given()
	       .log().all()	
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .headers("Accept",ContentType.JSON)
           .when()
           .get("/inventory");
		
		log.debug("Response:");
		response.then().statusCode(200)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();		
		
	}
	
	public void storeRequestBuilder(long id, long petId, int quantity, String shipDate, String status, boolean parsedComplete, int code) {
		this.id = id;
		this.petId = petId;
		this.quantity = quantity;
		this.shipDate = shipDate;
		this.status = status;
		this.complete = parsedComplete;
		this.code = code;
		
		store = new Store(id, petId, quantity, shipDate, status, parsedComplete);
	}

	public void postStoreOrder() {
		log.info("Placing an order for a pet");
	    response = given()
	    		.log().all()
		    	.baseUri(baseUri)
		    	.basePath(basePath)
		 	    .headers("Accept",ContentType.JSON)
		 	    .header("Content-Type", ContentType.JSON) 
		 	    .body(PetstoreUtils.processObject(store))
		 	    .when()
		        .post("/order");	
	    
		log.debug("Verify Response:");
		response.then().statusCode(this.code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
		this.id = response.jsonPath().getLong("id");
		
	}

	public void verifyAddOrder() {
		log.info("Verifying add order");
		assertThat("Pet id should match", response.jsonPath().getLong("petId"), equalTo(store.getPetId()));
		assertThat("Quantity should match", response.jsonPath().getInt("quantity"), equalTo(store.getQuantity()));
		assertThat("ShipDate should match",response.jsonPath().getString("shipDate").replace("+0000", "Z"), equalTo(store.getShipDate()));
		assertThat("Status should match", response.jsonPath().getString("status"), equalTo(store.getStatus()));
		log.debug("Assertions passed!");
	}

	public void getStoreOrder(long id, int code) {
		log.info("Getting an order by id");
	    response = given()
	 	       .log().all()	
	 	       .baseUri(baseUri)
	 	       .basePath(basePath)
	 	       .pathParam("id", id)
	 	       .headers("Accept",ContentType.JSON)
	            .when()
	            .get("/order/{id}");
	 		
	 		log.debug("Response:");
	 		response.then().statusCode(code)
	 					   .header("Content-Type", "application/json")
	 					   .and().log().all().extract().response();	
	}

	public void deleteOrder(long id, int code) {
		log.debug("Deleting an order by id");
	    response = given()
	       .log().all()		
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .pathParam("id", id)
           .when()
           .delete("/order/{id}");
		
		log.debug("Response:");
		response.then().statusCode(code);
		if (code == 200) {
			log.info(response.getBody().asString());
		}		
	}

	public void verifyOrderNotFound(long id) {
		log.info("Verifying store not found");
	    response = given()
	       .log().all()
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .pathParam("id", id)
	       .headers("Accept",ContentType.JSON)
           .when()
           .get("/order/{id}");
		
		log.debug("Response:");
		response.then().statusCode(404)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
		assertThat("Store should not be found", response.jsonPath().getString("message"), equalTo("Order not found"));

	}
	
	

}
