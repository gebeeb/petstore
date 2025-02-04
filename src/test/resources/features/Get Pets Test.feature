@test @pets
Feature: TC01 Get Pets

	@test1
	Scenario: Get Pets
		Given Pet with id 3 is existing
		
	@test2
	Scenario: Find Pets by status
		Given Find Pets by status 'available'
	
	@test3	
	Scenario: Update an existing pet
		Given Pet with id 3 is existing
		When User updates an existing pet
		      | id   | category.id | category.name    | name   | photoUrls              | tags     																																     | status    |
    		  | 3    | 458         |   doggie3        | name3  | ["photo3", "photo4"]   | [{"id": 3605, "name": "zsq3425jlqUQ"}, {"id": 5633, "name": "G8QTEWAAlw1"}]  | available |
		Then Pet record with 3 is updated