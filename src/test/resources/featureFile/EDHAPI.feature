@Sanity
Feature: Catalog API Sanity

  Scenario: Synchro Orange Carto 
    Given Synchro Orange Carto API details
    Then Hit the API, fetch response and validate the response code as 201

  Scenario: Bulk Orange Carto 
    Given Bulk Orange Carto Endpoint
    Then Send the API to server and check the response contains status code as 200
    
  Scenario: Filter API
    Given Search full API endpoints are sent to server
    Then Response contains status code 200
    
 Scenario: Filter dataset API
    Given Valid endpoints of search dataset 7 API are sent to server
    Then Response contains response code 200

 Scenario: List Datasources API
    Given Valid endpoints of list datasources API are sent to server
    Then Response contains 200

 Scenario: List Datasources By Subdomain API
    Given Endpoints of list data sources by subdomain API are sent to server
    Then Fetch the response and validate response contains 200   
 
 Scenario: List Datasources Simple
    Given Datasources API are sent to server
    Then Fetch the response and check the response code as 200    
    
 Scenario: List Datasources By Domain
    Given Datasources By Domain API are sent to server
    Then Fetch the response and check the response as 200  
    
 Scenario: List Datasources With Orange Cartographie
    Given Endpoints of List Datasources With Orange Cartographie API are sent to server
    Then Get the response and check the code as 200     
    
 Scenario: Update Dataset7 API
    Given Endpoints of Update Dataset 7 API are sent to server
    Then Check the response code as 200 
      
 Scenario: Synchro Catalog API
    Given Details of Synchro Catalog API are sent to server
    Then Check the response code as 201 and fetch the response
    
 Scenario: Bulk Delete Catalog API
    Given Endpoints of Bulk Delete Catalog API are sent to server
    Then Check the response code 204     
    
 Scenario: Bulk Catalog API
    Given Valid Endpoints of Bulk Catalog API are sent
    Then Check the response and validate the code as 201
    
 Scenario: List Subdomains Simple API
    Given Endpoints of List Subdomains Simple API are sent to server
    Then Fetch the response and validate the code as 200       
    
 Scenario: List Sids API
    Given Hit the List Subdomains Simple API
    Then Fetch the response and validate 200 as response code
    
 Scenario: List Datasets By Sid API
    Given Hit the List Datasets By Sid API with its headers and parameters
    Then Fetch the response and validate 200 as status code 
    
 Scenario: List Datasets API
    Given Hit the List Datasets API with its details
    Then Fetch the response and validate 200 as the response code  
    
 Scenario: Export All Catalog API
    Given Send the Export All Catalog API with its details to the server
    Then Validate 200 as the response code and Fetch the response   
    
 Scenario: List Domains Simple API
    Given Send the List Domains Simple API with its details to the server
    Then Validate 200 as the status code and Fetch the response 
    
 Scenario: List Datasets Simple API
    Given Send the List Datasets Simple API to the server
    Then Validate the response code as 200 and Fetch the response  
    
 Scenario: Remove Perimeter API
    Given Send the Remove Perimeter API to server
    Then Validate the response code as 404 and get the response      
    
 Scenario: Remove Doc API
    Given Send the Remove Doc API to server
    Then Validate the status code as 404 and check the response 
    
 Scenario: Remove Contact API
    Given Send the Remove Contact API to server
    Then Validate status code as 404 and check the response  
                     