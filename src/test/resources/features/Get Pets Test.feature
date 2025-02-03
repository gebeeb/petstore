@test @pets
Feature: TC01 Get Pets

	Scenario: Get Pets
		Given Pet with id 2 is existing
		
	@test1
	Scenario: Find Pets by status
		Given Find Pets by status 'available'
		
	Scenario: Update an existing pet
		Given Pet with id 2 is existing
		When User updates an existing pet
		      | id   | categoryName | name    | photoUrl | tagId | tagName | status    |
    		  | 0    | string       | doggie  | string   | 0     | string  | available |
		Then Pet record with 2 is updated