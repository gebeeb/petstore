@test @pets
Feature: TC01 Get Pets

	@test1
	Scenario: Get Pets
		Given Pet with id 10 is existing
		
	@test2
	Scenario: Find Pets by status
		Given Find Pets by status 'available'
	
	@test3	
	Scenario Outline: Update an existing pet
		Given Pet with id <id> is existing
		When User updates an existing pet with id <id>, category id <category.id>, category name '<category.name>', name '<name>', photoUrls '<photoUrls>', tags '<tags>', status '<status>'
		Then Pet record <id> is updated
		Then Pet with id <id> is existing
		
		Examples:
				  | id   | category.id | category.name    | name   | photoUrls              | tags     																																     | status    |
    		  | 1    | 458         |   dog3           | name3  | ["photo3", "photo4"]   | [{"id": 3605, "name": "zsq3425jlqUQ"}, {"id": 5633, "name": "G8QTEWAAlw1"}]  | available |
		    	| 2    | 675         |   dog4           | name4  | ["photo4", "photo5"]   | [{"id": 8751, "name": "zsq34sdflqU1"}, {"id": 9854, "name": "G8QTEWAA123"}]  | pending   |
		    	
		    	
	@test4
	 Scenario: Updates a pet in the store with from data
		Given Pet with id 4 is existing    	
		When User updates an existing pet with id 4, name 'dog2', status 'sold'
		Then Pet record 4 is updated with name 'dog2', status 'sold'
		
		
	@test5
		Scenario Outline: Add a new pet to the store		
		Given User adds an new pet with id <id>, category id <category.id>, category name '<category.name>', name '<name>', photoUrls '<photoUrls>', tags '<tags>', status '<status>'
		Then Pet record <id> is added
		Then Pet with id <id> is existing
		
		Examples:
				  | id   | category.id | category.name    | name   | photoUrls              | tags     																																     | status    |
    		  | 502  | 458         |   dog3           | name3  | ["photo3", "photo4"]   | [{"id": 3605, "name": "zsq3425jlqUQ"}, {"id": 5633, "name": "G8QTEWAAlw1"}]  | available |
    		  
   @test6
   	Scenario: Deletes a pet
   	Given User deletes a pet with id 6
   	Then  Pet record 6 is deleted
    		  
    		  
    		  