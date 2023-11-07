package stepDefinitions;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class InterfaceContractDeleteDatasetAPI {
	
	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	
	//File UpdateDatasetVersion = new File("src/test/resources/Payloads/UpdateDatasetVersion.json");
	
	
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	
	@Given("Interfacecontract delete datset")
	public void interfacecontract_delete_datset() {
		
		RestAssured.baseURI= baseUrl+ "/v2/interfacecontract/";
		RestAssured.basePath="{environment}/datasets/{datasetid}";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.pathParam("datasetid", "3477")				
				.basePath(RestAssured.basePath);
			//	.body(UpdateDatasetVersion);
		
		scn.log("Requested URL: " + baseURI + basePath);
		scn.log(request_spec.toString());
		
		
	  
	}

	@Then("Datset should be delete")
	public void datset_should_be_delete() {
		
		response = request_spec.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				//.header("Content-Type", "application/json;charset=UTF-8")
				.header("Content-Type", "application/json")
				.header("Accept", "*/*")
				//.header("Cookie", "JSESSIONID=37FD2C5B7A97DDA739A6605E53A9FD67")
				//.header("XSRF-TOKEN","2e9fcd10-872f-4ba5-8cbc-7cba8f5ae8b8")
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
				.delete(baseURI+basePath)
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
	   
	}
	

}
