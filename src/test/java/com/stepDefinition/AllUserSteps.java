package com.stepDefinition;

import java.io.IOException;
import com.user.User;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AllUserSteps {
	private User user;
	
	public AllUserSteps() {
		user = new User();
	}
	

	@Given("Userlist {string}")
	public void userlist(String userlist) throws IOException {
	    user.addUser(userlist);
	}

	@Given("Search for user by username {string} code {int}")
	public void search_for_user_by_username__code(String username, int code) {
		user.searchByUsername(username, code);
	}

	@Then("User is retrieved")
	public void user_is_retrieved() {
		user.verifyUser();
	}


	@Given("A user is existing with username {string} code {int}")
	public void a_user_is_existing_with_username_code(String username, int code) {
		user.searchByUsername(username, code);
	}
	
	@When("A user is updated with firstname {string} lastname {string} email {string} password {string} phone {string} userStatus {int} code {int}")
	public void a_user_is_updated_with_firstname_lastname_email_password_phone_user_status_code(String firstname, String lastname, String email, String password, String phone, Integer userStatus, Integer code) {
		user.userRequestBuilder(firstname, lastname, email, password, phone, userStatus, code);
		user.updateUserByUsername();

	}
	
	@Then("The user record is updated")
	public void the_user_record_is_updated() {
		user.verifyUserUpdate();

	}
	
	@Given("User is deleted with username {string}, code {int}")
	public void user_is_deleted_with_username_code(String username, int code) {
		user.deleteUser(username, code);
	}
	
	@Then("User record {string} is deleted")
	public void user_record_is_deleted(String username) {


	}











}
