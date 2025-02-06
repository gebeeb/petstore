package com.user;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.init.LogInitializer;
import com.util.PetstoreUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class User {
	protected Logger log;
	String baseUri = "https://petstore.swagger.io/v2";
	String basePath = "/user";
	Response response;
	
	String username;
	String firstname;
	String lastname;
	String email;
	String password;
	String phone;
	int userStatus;
	int code;
	
	UserObj userObj;
	
	public User() {
		log = LogInitializer.getLogger();
	}
	
	public void addUserByInputArray(String userlist)  throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> users = objectMapper.readValue(userlist, new TypeReference<List<Map<String, Object>>>() {});
		log.info("Creating users from a list");
	    response = given()
	    		.log().all()
		    	.baseUri(baseUri)
		    	.basePath(basePath)
		 	    .headers("Accept",ContentType.JSON)
		 	    .header("Content-Type", ContentType.JSON) 
		 	    .body(users)
		 	    .when()
		        .post("/createWithList");	
	    
		log.debug("Verify Response:");
		response.then().statusCode(200)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();		
		
	}

	public void searchByUsername(String username, int code) {
		log.info("Getting a user by username");
		this.username = username;
		this.code = code;
	    response = given()
	 	       .log().all()	
	 	       .baseUri(baseUri)
	 	       .basePath(basePath)
	 	       .pathParam("username", username)
	 	       .headers("Accept",ContentType.JSON)
	            .when()
	            .get("/{username}");
	 		
	 		log.debug("Response:");
	 		response.then().statusCode(code)
	 					   .header("Content-Type", "application/json")
	 					   .and().log().all().extract().response();			
		}

	public void verifyUser() {
		log.info("Verifying a user");
		if (code == 200) 
			assertThat("Username should match", response.jsonPath().getString("username"), equalTo(username));
		
	}

	public void updateUserByUsername() {
		log.info("Updating a user");
	    response = given()
	    		.log().all()
		    	.baseUri(baseUri)
		    	.basePath(basePath)
		 	    .pathParam("username", username)
		 	    .headers("Accept",ContentType.JSON)
		 	    .header("Content-Type", ContentType.JSON) 
		 	    .body(PetstoreUtils.processObject(userObj))
		        .when()
	            .log()
	            .body()
		        .put("/{username}");	
	    
		log.debug("Verify Response:");
		response.then().statusCode(this.code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();		
		
	}


	public void userRequestBuilder(String username, String firstname, String lastname, String email, String password, String phone, int userStatus, int code) {
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userStatus = userStatus;
		this.code = code;
		
		userObj = new UserObj(username, firstname, lastname, email, password, phone, userStatus);
	}

	public void verifyUserUpdate() {
		log.info("Verifying user update");
		assertThat("Update should be success", response.jsonPath().getInt("code"), equalTo(200));
		log.debug("Assertions passed!");
	}

	public void deleteUser(String username, int code) {
		this.username = username;
		log.debug("Deleting a user by username");
	    response = given()
	       .log().all()		
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .pathParam("username", username)
           .when()
           .delete("/{username}");
		
		log.debug("Response:");
		response.then().statusCode(code);
		if (code == 200) {
			log.info(response.getBody().asString());
		}		
		
	}
	
	public void verifyUserrNotFound(String username) {
		log.info("Verifying user not found");
	    response = given()
	       .log().all()
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .pathParam("username", username)
	       .headers("Accept",ContentType.JSON)
           .when()
           .get("/{username}");
		
		log.debug("Response:");
		response.then().statusCode(404)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
		assertThat("User should not be found", response.jsonPath().getString("message"), equalTo("User not found"));

	}

	public void addUser() {
		log.info("Creating users from a list");
	    response = given()
	    		.log().all()
		    	.baseUri(baseUri)
		    	.basePath(basePath)
		 	    .headers("Accept",ContentType.JSON)
		 	    .header("Content-Type", ContentType.JSON) 
		 	    .body(PetstoreUtils.processObject(userObj))
		 	    .when()
		        .post();	
	    
		log.debug("Verify Response:");
		response.then().statusCode(this.code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();				
	}

	public void userLogin(String username, String password, int code) {
		this.username = username;
		this.password = password;
		this.code = code;
		log.info("Logs user into the system");
	    response = given()
	       .log().all()
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .headers("Accept",ContentType.JSON)
	       .queryParam("username", this.username)
	       .queryParam("password", this.password)
           .when()
           .get("/login");
		
		log.debug("Response:");
		response.then().statusCode(this.code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();
	}
	
	public void verifyUserLoggedIn() {
		assertThat("User login success", response.jsonPath().getInt("code"), equalTo(200));
        assertThat("Expires after header", response.getHeader("X-Expires-After"), notNullValue());
        assertThat("Expires after header", response.getHeader("X-Expires-After"), matchesPattern("^[A-Za-z]{3} [A-Za-z]{3} \\d{2} \\d{2}:\\d{2}:\\d{2} UTC \\d{4}$"));
        assertThat("Rate Limit header", response.getHeader("X-Rate-Limit"), notNullValue());
        assertThat("Rate Limit headers", response.getHeader("X-Rate-Limit"), matchesPattern("\\d+"));		
	}

	public void userLogout(String username, int code) {
		this.code = code;
		log.info("Logs user out the system");
	    response = given()
	       .log().all()
	       .baseUri(baseUri)
	       .basePath(basePath)
	       .headers("Accept",ContentType.JSON)
           .when()
           .get("/logout");
		
		log.debug("Response:");
		response.then().statusCode(this.code)
					   .header("Content-Type", "application/json")
					   .and().log().all().extract().response();		
		assertThat("User logout be success", response.jsonPath().getInt("code"), equalTo(200));
		assertThat("User logout be success", response.jsonPath().getString("type"), equalTo("unknown"));
		assertThat("User logout be success", response.jsonPath().getString("message"), equalTo("ok"));
        assertThat("access-control-allow-headers", response.getHeader("access-control-allow-headers"), equalTo("Content-Type, api_key, Authorization"));
        assertThat("access-control-allow-methods", response.getHeader("access-control-allow-methods"), equalTo("GET, POST, DELETE, PUT"));
        assertThat("access-control-allow-origin", response.getHeader("access-control-allow-origin"), equalTo("*"));
        assertThat("content-type", response.getHeader("content-type"), equalTo("application/json"));
        assertThat("date", response.getHeader("date"), matchesPattern("^[A-Za-z]{3}, \\d{2} [A-Za-z]{3} \\d{4} \\d{2}:\\d{2}:\\d{2} GMT$"));
        assertThat("server", response.getHeader("server"), equalTo("Jetty(9.2.9.v20150224)"));

        log.info("Assertions passed!");
	}




		
		

}
