@test @pets
Feature: TC01 All Pet Tests

		Scenario Outline: Add a new pet to the store		
		Given User adds an new pet with category id <category.id>, category name '<category.name>', name '<name>', photoUrls '<photoUrls>', tags '<tags>', status '<status>', code <code>
		Then Pet record is added
		Then Pet record is existing
	
		Examples:
				  | category.id | category.name    | name   | photoUrls              | tags     																																     | status    | code |
    		  | 458         |   dog5           | name5  | ["photo5", "photo6"]   | [{"id": 3605, "name": "zsq3425jlqUQ"}, {"id": 5633, "name": "G8QTEWAAlw1"}]  | available | 200  |
    		  | 459         |   dog6           | name6  | ["photo7"]             | [{"id": 26232, "name": "aabbbcccc"}, {"id": 6435, "name": "SDHSDF"}, {"id": 6745, "name": "QWERTY"}]  | pending | 200  |


		Scenario Outline: Add a new pet to the store with id		
		Given User adds an new pet with id <id> category id <category.id>, category name '<category.name>', name '<name>', photoUrls '<photoUrls>', tags '<tags>', status '<status>', code <code>
		Then Pet record is added
		Then Pet record is existing
	
		Examples:
				  | id  | category.id | category.name    | name   | photoUrls              | tags     																																     | status    | code |
    		  | 501 | 458         |   dog5           | name5  | ["photo5", "photo6"]   | [{"id": 3605, "name": "zsq3425jlqUQ"}, {"id": 5633, "name": "G8QTEWAAlw1"}]  | available | 200  |
    		  | 502 | 945         |   dog6           | name6  | ["photo7"]             | [{"id": 26232, "name": "aabbbcccc"}, {"id": 6435, "name": "SDHSDF"}, {"id": 6745, "name": "QWERTY"}]  | pending | 200  |  
 
  
	Scenario Outline: Find pet by ID
		Given Pet with id <id> is existing, code <code>
		
    Examples:
    		| id  |  code |
    		| 501 |  200  |
    		| 502 |  200  |    		
    		| 356 |  404  |

		
	@test3
	Scenario Outline: Find Pets by status
		Given Find Pets by status '<status>', code <code>
  
    Examples:
    		| status     |  code |
    		| available  |  200  |
    		| pending    |  200  |	
		
	
	Scenario Outline: Update an existing pet
		Given Pet with id <id> is existing, code <code>
		When User updates an existing pet with id <id>, category id <category.id>, category name '<category.name>', name '<name>', photoUrls '<photoUrls>', tags '<tags>', status '<status>', code <code>
		Then Pet record <id> is updated
		Then Pet with id <id> is existing, code <code>
	
		Examples:
				  | id   | category.id | category.name    | name   | photoUrls              | tags     																																     | status    |  code  |
    		  | 501  | 458         |   dog3           | name3  | ["photo3", "photo4"]   | [{"id": 3605, "name": "zsq3425jlqUQ"}, {"id": 5633, "name": "G8QTEWAAlw1"}]  | available |  200   |
		    	| 502  | 675         |   dog4           | name4  | ["photo4", "photo5"]   | [{"id": 8751, "name": "zsq34sdflqU1"}, {"id": 9854, "name": "G8QTEWAA123"}]  | pending   |  200   |
		    	
		    	
	@test5
	 Scenario Outline: Updates a pet in the store with from data
		Given Pet with id <id> is existing, code <code>    	
		When User updates an existing pet with id <id>, name '<name>', status '<status>', code <code>
		Then Pet record <id> is updated with name '<name>' , status '<status>'
 
    Examples:
    		| id   | name    |  status     |  code |
    		| 501  | dog44   | available   |  200  |
    		| 502  | dog55   |  pending    |	200  |


	@test6
	Scenario Outline: Uploads an image
	 Given  Pet with id <id> is existing, code <code> 
	 When User uploads an image to id <id>, additionalMetadata '<additionalMetadata>', file '<file>', code <code>
	
	 Examples:
	    	| id   | additionalMetadata |  file       |  code |
    		| 501  | testimage2  				| dog1.png    |  200  |
    		| 502  | testimage3   			| dog2.png    |	 200  | 
	 
    		  
   @test7
   	Scenario Outline: Deletes a pet
   	Given User deletes a pet with id <id>, code <code>
   	Then  Pet record <id> is deleted

    Examples:
    		| id  |  code |
    		| 501 |  200  |
    		| 502 |  200  |    		
    		| 356 |  404  |
 
   	 		  
    		  