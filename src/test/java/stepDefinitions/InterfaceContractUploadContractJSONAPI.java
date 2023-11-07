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

public class InterfaceContractUploadContractJSONAPI {
	
	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	
	
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	
	@Given("Interfacecontract upload the json")
	public void interfacecontract_upload_the_json() {
		RestAssured.baseURI= baseUrl+ "/v2/interfacecontract/";
		RestAssured.basePath="{environment}/upload/json";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")	
				.basePath(RestAssured.basePath);
		
		scn.log("Requested URL: " + baseURI + basePath);
		scn.log(request_spec.toString());
	    
	}

	@Then("JOSN Version should be update")
	public void josn_version_should_be_update() {
		response = request_spec
				.queryParam("upgradeMajorVersion", "true")	
				.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.header("Content-Type", "multipart/form-data")
				.multiPart("file", new File("src/test/resources/Payloads/EDH-InterfaceContract-V16.0-DSIEP-29B(Dinamo)-v0.4.json"))
				//.header("Content-Type", "application/json")				
//				.header("Cookie", "SESSION=ZWJkNTBiZmUtMGY5Yy00NWY2LTgwMDItYTc3NzUwNGI1NjAw")
//				.header("XSRF-TOKEN","8eda2094-2dc5-4075-9545-0b86493c5975")
 			    .header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")		
				//.body("UploadContractXls")
				.post(baseURI+basePath)			
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
	}

}
