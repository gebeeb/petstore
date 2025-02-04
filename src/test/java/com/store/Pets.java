package com.store;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;

import com.init.LogInitializer;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

public class Pets {
	protected Logger log;
	String baseUri = "https://petstore.swagger.io/v2";
	String basePath = "/pet/";
	String endpoint;
	Response response;
	private Map<String, String> petData;
	
	public Pets() {
		log = LogInitializer.getLogger();
	}
	
	public void getPetById(long petId) {
		log.debug("Inside getPetById");
		log.info(baseUri + basePath + petId);
		
	    response = given()
	       .headers("Accept",ContentType.JSON)
           .when()
           .get(baseUri + basePath + petId);
		
		log.debug("Response:");
		response.then().statusCode(200)
					   .header("Content-Type", "application/json")
					   //.body("id", equalTo((int)petId)) //limitation when petId is cast to in, cannot assert against the api response
					   .and().log().all().extract().response();
	}
	
	public void getPetsByStatus(String status) {
		log.debug("Inside getPetsByStatus");
		endpoint = basePath + "findByStatus";
	    response = given()
	    	.baseUri(baseUri)
	    	.basePath(endpoint)
	 	    .headers("Accept",ContentType.JSON)
	 	    .queryParam("status", status)
	        .when()
	        .get();	
		log.debug("Response:");
		response.then().statusCode(200)
					   .header("Content-Type", "application/json")
					   .body("status", equalTo(status))
					   .and().log().all().extract().response();
		
	}

	public void updatePet(List<Map<String, String>> petDetailsList) {
		log.debug("Inside updatePet");
		endpoint = basePath;
		log.info(baseUri + endpoint);
        for (Map<String, String> petData : petDetailsList) {
            String jsonBody = String.format("{\n" +
                    "  \"id\": %s,\n" +
                    "  \"category\": {\n" +
                    "    \"id\": %s,\n" +
                    "    \"name\": \"%s\"\n" +
                    "  },\n" +
                    "  \"name\": \"%s\",\n" +
                    "  \"photoUrls\": %s,\n" +
                    "  \"tags\": %s,\n" +
                    "  \"status\": \"%s\"\n" +
                    "}",
                    petData.get("id"),
                    petData.get("category.id"),
                    petData.get("category.name"),
                    petData.get("name"),
                    petData.get("photoUrls"),
                    petData.get("tags"),
                    petData.get("status"));
		
    	    response = given()
    		    	.baseUri(baseUri)
    		    	.basePath(endpoint)
    		 	    .headers("Accept",ContentType.JSON)
    		 	    .header("Content-Type", ContentType.JSON) 
    		 	    .body(jsonBody)
    		        .when()
		            .log()
		            .body()
    		        .put();	
    	    
    		log.debug("Response:");
    		response.then().statusCode(200)
    					   .header("Content-Type", "application/json")
    					   .and().log().all().extract().response();
        }
            
	}

}
