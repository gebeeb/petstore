package com.pet;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.Logger;

import com.init.LogInitializer;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.util.PetstoreUtils;

public class Petstore {
	protected Logger log;
	String baseUri = "https://petstore.swagger.io/v2";
	String basePath = "/pet";
	Response response;
	Pet pet;
	
	long id;
	long categoryId;
	String categoryName;
	String name;
	String status;
	int code;
	
	public Petstore() {
		log = LogInitializer.getLogger();
	}
	
	public void getPetById(long id, int code) {
		log.debug("Getting a pet by Id");
	    response = given()
	       .log().all()	
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .pathParam("id", id)
	       .headers("Accept",ContentType.JSON)
           .when()
           .get("/{id}");
		
		log.debug("Response:");
		response.then().statusCode(code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
		if (code == 200)
			assertThat(response.jsonPath().getLong("id"), equalTo(id));
	}
	
	public void addPet() {
		log.info("Adding a pet");
	    response = given()
	    		.log().all()
		    	.baseUri(baseUri)
		    	.basePath(basePath)
		 	    .headers("Accept",ContentType.JSON)
		 	    .header("Content-Type", ContentType.JSON) 
		 	    .body(PetstoreUtils.processObject(pet))
		 	    .when()
		        .post();	
	    
		log.debug("Verify Response:");
		response.then().statusCode(this.code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
		this.id = response.jsonPath().getLong("id");
	}
	
	public void getPetByGeneratedId() {
		log.debug("Getting a pet by Generated Id");
	    response = given()
	       .log().all()	
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .pathParam("id", this.id)
	       .headers("Accept",ContentType.JSON)
           .when()
           .get("/{id}");
		
		log.debug("Response:");
		response.then().statusCode(code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
		if (code == 200)
			assertThat(response.jsonPath().getLong("id"), equalTo(this.id));
	}
	
	public void getPetsByStatus(String status, int code) {
		log.debug("Getting a pet by status");
	    response = given()
	    	.log().all()	
	    	.baseUri(baseUri)
	    	.basePath(basePath)
	 	    .headers("Accept",ContentType.JSON)
	 	    .queryParam("status", status)
	        .when()
	        .get("/findByStatus");	
		log.debug("Response:");
		response.then().statusCode(code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
	}
	
	public void verifyAddPet() {
		log.info("Verifying add pet");
		assertThat("Pet id should match", response.jsonPath().getLong("id"), equalTo(this.id));
		assertThat("Pet category id should match", response.jsonPath().getLong("category.id"), equalTo(pet.getCategory().getId()));
		assertThat("Pet category name should match",response.jsonPath().getString("category.name"), equalTo(pet.getCategory().getName()));
		assertThat("Pet name should match", response.jsonPath().getString("name"), equalTo(pet.getName()));
        List<String> responsePhotoUrls = response.jsonPath().getList("photoUrls");
        assertThat("Photo URLs should match", responsePhotoUrls, equalTo(pet.getPhotoUrls()));

        List<Tag> petTags = pet.getTags();
        List<Map<String, Object>> responseTags = response.jsonPath().getList("tags");

        for (int i = 0; i < petTags.size(); i++) {
        	assertThat("Tag id should match", ((Number) responseTags.get(i).get("id")).longValue(), equalTo(petTags.get(i).getId()));
            assertThat("Tag name should match", responseTags.get(i).get("name"), equalTo(petTags.get(i).getName()));
        }
		assertThat("Pet status should match",response.jsonPath().getString("status"), equalTo(this.status));
		log.debug("Assertions passed!");
	}

	public void verifyUpdatePet() {
		log.info("Verifying update success");
		assertThat("Pet id should be updated", response.jsonPath().getLong("id"), equalTo(pet.getId()));
		assertThat("Pet category id should be updated", response.jsonPath().getLong("category.id"), equalTo(pet.getCategory().getId()));
		assertThat("Pet category name should be updated",response.jsonPath().getString("category.name"), equalTo(pet.getCategory().getName()));
		assertThat("Pet name should be updated", response.jsonPath().getString("name"), equalTo(pet.getName()));
        List<String> responsePhotoUrls = response.jsonPath().getList("photoUrls");
        assertThat("Photo URLs should match", responsePhotoUrls, equalTo(pet.getPhotoUrls()));

        List<Tag> petTags = pet.getTags();
        List<Map<String, Object>> responseTags = response.jsonPath().getList("tags");

        for (int i = 0; i < petTags.size(); i++) {
        	assertThat("Tag id should match", ((Number) responseTags.get(i).get("id")).longValue(), equalTo(petTags.get(i).getId()));
            assertThat("Tag name should match", responseTags.get(i).get("name"), equalTo(petTags.get(i).getName()));
        }
		assertThat("Pet status should be updated",response.jsonPath().getString("status"), equalTo(this.status));
		log.debug("Assertions passed!");
	}
	
	public void updateSingleForm(long id, String name, String status, int code) {
		log.debug("Updating a pet by single form");
		this.id = id;
		this.name = name;
		this.status = status;
		this.code = code;
		log.debug("id: " + id);
		log.debug("name: " + name);
		log.debug("status: " + status);
   	    response = given()
   	    		.log().all()
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
		response.then().statusCode(this.code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();    
		 	    
	}
	
	public void verifySingleForm() {
		log.info("Verifying update pet by single form");
		assertThat("Pet id should match", response.jsonPath().getLong("message"), equalTo(this.id));
		assertThat("Status code should be 200", response.jsonPath().getInt("code"), equalTo(this.code));
		log.debug("Assertions passed!");
		
		getPetById(this.id, this.code);
		assertThat("Pet id should be updated", response.jsonPath().getLong("id"), equalTo(this.id));
		assertThat("Pet name should be updated", response.jsonPath().getString("name"), equalTo(this.name));
		assertThat("Pet status should be updated",response.jsonPath().getString("status"), equalTo(this.status));
		log.debug("Assertions passed!");
	}
	
	public void deletePet(long id, int code) {
		log.debug("Deleting a pet by id");
	    response = given()
	       .log().all()		
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .pathParam("petId", id)
	       .headers("api_key", "")
           .when()
           .delete("/{petId}");
		
		log.debug("Response:");
		response.then().statusCode(code);
		if (code == 200) {
			log.info(response.getBody().asString());
		}
	}

	public void verifyPetNotFound() {
		log.info("Verifying pet not found");
	    response = given()
	       .log().all()
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .pathParam("id", id)
	       .headers("Accept",ContentType.JSON)
           .when()
           .get("/{id}");
		
		log.debug("Response:");
		response.then().statusCode(404)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
		assertThat("Pet should not be found", response.jsonPath().getString("message"), equalTo("Pet not found"));
	}

	@SuppressWarnings("unchecked")
	public void petRequestBuilder(long id, long categoryId, String categoryName, String name, String photoUrls,
			String tags, String status, int code) {
		this.id = id;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.name = name;
		this.status = status;
		this.code = code;

        Category category = new Category(categoryId, categoryName);

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> photoUrlsList = null;
        try {
            photoUrlsList = objectMapper.readValue(photoUrls, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Tag> tagList = null;
        try {
            tagList = objectMapper.readValue(tags, objectMapper.getTypeFactory().constructCollectionType(List.class, Tag.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        pet = new Pet(id, category, name, photoUrlsList, tagList, status);
	}
	
	public void updatePet() {
		log.info("Updating a pet");
	    response = given()
	    		.log().all()
		    	.baseUri(baseUri)
		    	.basePath(basePath)
		 	    .headers("Accept",ContentType.JSON)
		 	    .header("Content-Type", ContentType.JSON) 
		 	    .body(PetstoreUtils.processObject(pet))
		        .when()
	            .log()
	            .body()
		        .put();	
	    
		log.debug("Verify Response:");
		response.then().statusCode(this.code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();		
	}

	public void uploadImage(long id, String additionalMetadata, String file, int code) {
		log.info("Uploading an image");
		URL fileUrl = Petstore.class.getClassLoader().getResource(file);
        if (fileUrl != null) {
            File fileToUpload = new File(fileUrl.getFile());

            response = given()
            	.baseUri(baseUri)
            	.basePath(basePath)
            	.pathParam("id", id)
                .multiPart("file", fileToUpload)  
                .contentType(ContentType.MULTIPART)  
                .when()
                .post("/{id}/uploadImage");  
        } else {
            log.info("File not found in resources folder!");
        }
		log.debug("Verify Response:");
		response.then().statusCode(code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();		
	}



}
