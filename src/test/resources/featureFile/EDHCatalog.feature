@Sanity
Feature: Catalog API Sanity

  Scenario: DataLayer Dataset 
    Given Datalayer dataset API details
    Then Hit the API, fetch response and validate the response code as OK

  Scenario: Export Sample Catelog
    Given Export Sample catelog Endpoint
    Then Send the API to server and check the response contains status as OK.
    
  Scenario: Sample Catelog API
    Given Hit sample Catelog API
    Then Response contains status code 200 OK
    
 Scenario: List Columns By Datasource & Dataset API
    Given Valid endpoints of List Columns API are sent to server
    Then Response contains response code 200 OK

 Scenario: List Dataset Reference API
    Given Valid endpoints of list Dataset Reference API are sent to server
    Then Response contains 200 OK

 Scenario: List Docs By Datasource & Dataset API
    Given Valid endpoint of List docs by subdomain API are sent to server
    Then Fetch the response and validate response contains 200 OK 
 
 Scenario: Name Datasource Simple
    Given Name Datasources API are sent to server
    Then Fetch the response and check the response code as 200 OK  
    
 Scenario: List Perimeters By Datasource
    Given Datasources By Perimeters API are sent to server
    Then Fetch the response body and check the response as 200 OK  
    
 Scenario: Export Sample Datasource
    Given Endpoints of export Sample Datasource API are sent to server
    Then Get the response and check the code as 200 OK      
    
 Scenario: List Docs By Datasource API
    Given Endpoints of list Docs By Datasource are sent to server
    Then Check the response code as 200 OK 
      
 Scenario: List Datasets By Datasource API
    Given Details of list Datasets By Datasource API are sent to server
    Then Check the response code as 200 OK and fetch the response
    
 Scenario: List Contacts By Datasource API
    Given Endpoints of list Contacts By Datasource API are sent to server
    Then Check the response code 200 OK      
    
 #Scenario: Datasource With Orange Cartographie API
    #Given Valid Endpoints of Datasource With Orange Cartographie API are sent
    #Then Check the response code as 200 OK and fetch the response
    
 Scenario: Save Perimeter API
    Given Endpoints of Save Perimeter API are sent to server
    Then Fetch the response and validate the code as 201 OK      
    
 #Scenario: Upload Datasource File API
    #Given Hit the upload Datasource File Simple API
    #Then Fetch the response and validate 201 OK as response code
    
 Scenario: Save Doc API
    Given Hit the Save Doc API with its headers and parameters
    Then Fetch the response and validate 201 OK as status code 
    
 Scenario: Save Contact API
    Given Hit the Save Contact API with its details
    Then Fetch the response and validate 201 OK as the response code  
    
 Scenario: Create Datasource API
    Given Send the Create Datasource API with its details to the server
    Then Validate 201 OK as the response code and Fetch the response   
    
 Scenario: Update Datasource API
    Given Send the Update Datasource API with its details to the server
    Then Validate 200 OK as the status code and Fetch the response 
    
 Scenario: Synchro Sample Catelog API
    Given Send the synchro sample catelog API to the server
    Then Validate the response code as 201 OK and Fetch the response  
    
 Scenario: Bulk Sample Catelog API
    Given Send the bulk sample catelog API to server
    Then Validate the response code as 201 OK and get the response      
    
 Scenario: Save Dataset Referance API
    Given Send the Dataset Referance API to server
    Then Validate the status code as 201 Created and check the response 
    
 Scenario: Get Dataset Referance API
    Given Send the get dataset referance API to server
    Then Validate status code as 200 OK and check the response 
    
 Scenario: Remove Dataset Referance API
    Given Send the remove dataset referance API to server
    Then Validate status code as 204 No Content and check the response  
                     