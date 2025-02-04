package com.store;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;

import com.init.LogInitializer;
import com.lib.Tag;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.stream.Collectors;

public class Pets {
	protected Logger log;
	String baseUri = "https://petstore.swagger.io/v2";
	String basePath = "/pet";
	Response response;

	long id;
	long categoryId;
	String categoryName;
	String name;
	//String photoUrls;
	private List<String> expectedPhotoUrls;
	private List<Tag> expectedTags; 
	//String tags;
	String status;
	
	public Pets() {
		log = LogInitializer.getLogger();
	}
	
	public void getPetById(long id) {
		log.debug("Inside getPetById");
		//log.info(baseUri + basePath + petId);
		
	    response = given()
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .pathParam("id", id)
	       .headers("Accept",ContentType.JSON)
           .when()
           .get("/{id}");
		
		log.debug("Response:");
		response.then().statusCode(200)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
		assertThat(response.jsonPath().getLong("id"), equalTo(id));
	}
	
	public void getPetsByStatus(String status) {
		log.debug("Inside getPetsByStatus");
	    response = given()
	    	.baseUri(baseUri)
	    	.basePath(basePath)
	 	    .headers("Accept",ContentType.JSON)
	 	    .queryParam("status", status)
	        .when()
	        .get("/findByStatus");	
		log.debug("Response:");
		response.then().statusCode(200)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
	}

	public void updatePet(long id, long categoryId, String categoryName, String name, String photoUrls, String tags, String status) {
		log.debug("Inside updatePet");
		this.id = id;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.name = name;
		expectedPhotoUrls = parseJsonArray(photoUrls); 
		expectedTags = parseTags(tags); 
		this.status = status;
        String jsonBody = "{"
                + "\"id\": " + id + ","
                + "\"category\": {"
                + "\"id\": " + categoryId + ","
                + "\"name\": \"" + categoryName + "\""
                + "},"
                + "\"name\": \"" + name + "\","
                + "\"photoUrls\": " + expectedPhotoUrls + ","
                + "\"tags\": " + expectedTags + ","
                + "\"status\": \"" + status + "\""
                + "}";
	
	    response = given()
		    	.baseUri(baseUri)
		    	.basePath(basePath)
		 	    .headers("Accept",ContentType.JSON)
		 	    .header("Content-Type", ContentType.JSON) 
		 	    .body(jsonBody)
		        .when()
	            .log()
	            .body()
		        .put();	
	    
		log.debug("Verify Response:");
		response.then().statusCode(200)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
	}

	public void verifyUpdatePet() {
		assertThat("Pet id should be updated", response.jsonPath().getLong("id"), equalTo(this.id));
		assertThat("Pet category id should be updated", response.jsonPath().getLong("category.id"), equalTo(this.categoryId));
		assertThat("Pet category name should be updated",response.jsonPath().getString("category.name"), equalTo(this.categoryName));
		assertThat("Pet name should be updated", response.jsonPath().getString("name"), equalTo(this.name));
		List<String> actualPhotoUrls = response.jsonPath().getList("photoUrls");
		assertThat("Pet photoUrls should be updated",actualPhotoUrls.toString(), equalTo(expectedPhotoUrls.toString().replaceAll("\"", "")));
		List<?> actualTags = response.jsonPath().getList("tags");
		assertThat("Pet tags should be updated",actualTags.toString().replaceAll("=", ": "), equalTo(expectedTags.toString().replaceAll("\"", "")));
		assertThat("Pet status should be updated",response.jsonPath().getString("status"), equalTo(this.status));
		log.debug("Assertions passed!");
	}
	
	public void updateSingleForm(long id, String name, String status) {
		log.debug("Inside update single form");
		this.id = id;
		this.name = name;
		this.status = status;
		log.debug("id: " + id);
		log.debug("name: " + name);
		log.debug("status: " + status);
   	    response = given()
		    	.baseUri(baseUri)
		    	.basePath(basePath)
		 	    .headers("Accept",ContentType.JSON)
		 	    .header("Content-Type", ContentType.URLENC) 
		 	    .pathParam("id", id)
		 	    .formParam("name", name)
		 	    .formParam("status", status)
		 	    .when()
		 	    .post("/{id}");
		log.debug("Verify Response:");
		response.then().statusCode(200)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();    
		 	    
	}
	
	public void verifySingleForm() {
		assertThat("Pet id should match", response.jsonPath().getLong("message"), equalTo(this.id));
		assertThat("Status code should be 200", response.jsonPath().getInt("code"), equalTo(200));
		log.debug("Assertions passed!");
		
		getPetById(this.id);
		assertThat("Pet id should be updated", response.jsonPath().getLong("id"), equalTo(this.id));
		assertThat("Pet name should be updated", response.jsonPath().getString("name"), equalTo(this.name));
		assertThat("Pet status should be updated",response.jsonPath().getString("status"), equalTo(this.status));
		log.debug("Assertions passed!");
	}
	
	private List<String> parseJsonArray(String jsonArrayString) {
	    return Arrays.asList(jsonArrayString.replace("[", "").replace("]", "").split(","))
	        .stream()
	        .map(photoUrl -> "" + photoUrl.trim() + "")  
	        .collect(Collectors.toList());
	}


    private List<Tag> parseTags(String tagsString) {
        Gson gson = new Gson();
        return gson.fromJson(tagsString, new TypeToken<List<Tag>>(){}.getType());
    }

	public void addPet(long id, long categoryId, String categoryName, String name, String photoUrls, String tags,
			String status) {
		log.debug("Inside addPet");
		this.id = id;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.name = name;
		expectedPhotoUrls = parseJsonArray(photoUrls); 
		expectedTags = parseTags(tags); 
		this.status = status;
        String jsonBody = "{"
                + "\"id\": " + id + ","
                + "\"category\": {"
                + "\"id\": " + categoryId + ","
                + "\"name\": \"" + categoryName + "\""
                + "},"
                + "\"name\": \"" + name + "\","
                + "\"photoUrls\": " + expectedPhotoUrls + ","
                + "\"tags\": " + expectedTags + ","
                + "\"status\": \"" + status + "\""
                + "}";
	
	    response = given()
		    	.baseUri(baseUri)
		    	.basePath(basePath)
		 	    .headers("Accept",ContentType.JSON)
		 	    .header("Content-Type", ContentType.JSON) 
		 	    .body(jsonBody)
		        .when()
	            .log()
	            .body()
		        .post();	
	    
		log.debug("Verify Response:");
		response.then().statusCode(200)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
		
	}


}
