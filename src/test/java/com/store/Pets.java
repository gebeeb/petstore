package com.store;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;

import com.init.LogInitializer;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Pets {
	protected Logger log;
	String baseUri = "https://petstore.swagger.io/v2";
	String basePath = "/pet/";
	String endpoint;
	
	public Pets() {
		log = LogInitializer.getLogger();
	}
	
	public void getPetById(int petId) {
		log.debug("Inside getPetById");
		log.info(baseUri + basePath + petId);
		
	    Response response = given()
	       .headers("Accept",ContentType.JSON)
           .when()
           .get(baseUri + basePath + petId);
		
		log.debug("Response:");
		response.then().statusCode(200)
					   .header("Content-Type", "application/json").and().log().all().extract().response();
	}
	
	public void getPetsByStatus(String status) {
		log.debug("Inside getPetsByStatus");
		endpoint = basePath + "findByStatus";
	    Response response = given()
	    	.baseUri(baseUri)
	    	.basePath(endpoint)
	 	    .headers("Accept",ContentType.JSON)
	 	    .queryParam("status", status)
	        .when()
	        .get();	
		log.debug("Response:");
		response.then().statusCode(200)
					   .header("Content-Type", "application/json").and().log().all().extract().response();
	}

}
