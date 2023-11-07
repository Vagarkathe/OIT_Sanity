@Sanity
Feature: Interface Contract API Sanity

  Scenario: DataSource Export Json Contract by Version
    Given Export Json Contract by Version 4 API endpoints
    Then Validate the response code for Contract by Version API as 201

  Scenario: Export Json Contract 
    Given Export Json Contract 4 valid endpoints
    Then Send the API to server and check the response of Json Contract API contains status code as 201
    
  Scenario: Export Xls Contract 
    Given Export Xls Contract 4 API endpoints are sent to server
    Then Response of Export Xls Contract API contains status code 201
    
  Scenario: Export Multiple Json Contract 
    Given Export Multiple Json Contract 4 API endpoints are sent
    Then Validate the response of Export Multiple Json Contract API contains status code as 201   
    
   Scenario: Remove Version API 
    Given Remove Version 4 API endpoints are sent to server
    Then Response of Remove Version API contains the status code 200
    
    Scenario: Mark to Deploy API 
    Given Mark to Deploy 4 API endpoints sent to server
    Then Check the response of Mark to Deploy API contains the status code 200   
    
    Scenario: Get Datasource API 
    Given Get Datasource 4 API valid endpoints sent to server
    Then Fetch the response of Get Datasource API and check the response code as 200  
    
    Scenario: Get All Data Set Version API 
    Given Get All Data Set Version 4 API valid endpoints sent to server
    Then Check the response of All Data Set Version API code as 200 and fetch the response
    
    Scenario: Get Deploy Options API 
    Given Get Deploy Options 4 API valid endpoints sent to server
    Then Get the response of Deploy Options API and check the code as 200 
    
    Scenario: Get All Data Set API 
    Given Get All Data Set 4 API valid endpoints are sent to server
    Then Check the response of All Data Set API code as 200
     
    Scenario: Get Product Installed Versions API 
    Given Get Product Installed Versions 4 API valid endpoints are sent to server
    Then Validate 200 as the status code and Fetch the response of Product Installed Versions API
    
    Scenario: Get Product Installed Prod Versions API 
    Given Get Product Installed Prod Versions 4 API valid endpoints are sent to server
    Then Validate the response code as 200 and Fetch the response of Product Installed Prod Versions API
    
    Scenario: Export Template API 
    Given Export Template 4 API valid endpoints are sent to server
    Then Validate the response code as 201 and get the response of Export Template API
    
    Scenario: Get Nb Excel Contract Possible To Export API 
    Given Get Nb Excel Contract Possible To Export 4 API valid endpoints are sent to server
    Then Response of Nb Excel Contract API contains 200
    
    Scenario: Undeploy All API 
    Given Undeploy All 4 API valid endpoints are sent to server
    Then Check the status code as 200 for Undeploy All API
    
    Scenario: Get Log API 
    Given Get Log 4 API valid endpoints are sent to server
    Then Validate the response of Log API contains 200
    
    Scenario: Deploy All API 
    Given Deploy All 4 API valid endpoints are sent to server
    Then Validate the status code as 200 for Deploy All API
    
    Scenario: Get All Data Sources API 
    Given Get All Data Sources 4 API valid endpoints are sent to server
    Then Status Code contains 200 for All Data Sources API
    
    Scenario: Get Available Contract Versions For Export API 
    Given Get Available Contract Versions For Export 4 API valid endpoints are sent to server
    Then Check the status code contains 200 for Available Contract Versions API
    
                          