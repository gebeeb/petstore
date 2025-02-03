package com.store;

import lib.Authorization;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;

import com.init.LogInitializer;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Pets /*extends Authorization*/ {
	protected Logger log;
	String baseUri = "https://petstore.swagger.io/v2";
	String basePath = "/pet/";
	
	public Pets() {
		log = LogInitializer.getLogger();
	}
	
	public void getPetById(int petId) {
		System.out.println("\nInside getPetById");
		System.out.println(baseUri + basePath + petId);
		
	       Response response = RestAssured.given()
	               .when()
	               .get(baseUri + basePath + petId);
		
		log.debug("Response:");
		response.then().statusCode(200).and().log().all().extract().response();
	}

}
