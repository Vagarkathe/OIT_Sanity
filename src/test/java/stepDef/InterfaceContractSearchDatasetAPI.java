package stepDef;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import com.google.common.net.MediaType;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class InterfaceContractSearchDatasetAPI {
	
	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	
	//File UpdateDatasetVersion = new File("src/test/resources/Payloads/UpdateDatasetVersion.json");
	
	
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	
	@Given("Interfacecontract search datset")
	public void interfacecontract_search_datset() {
		RestAssured.baseURI= baseUrl+ "/v2/interfacecontract/";
		RestAssured.basePath="{environment}/datasets";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				//.queryParam("search", "datasource%3D%25")				
				.basePath(RestAssured.basePath);
		
		scn.log("Requested URL: " + baseURI + basePath);
		scn.log(request_spec.toString());
	  
	}

	@Then("Datset should be search")
	public void datset_should_be_search() {
		response = request_spec
				.queryParam("search", " datasource%3D%25")	
				.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
			//	.header("Content-Type", "application/json;charset=UTF-8")
				.header("Content-Type", "application/json")				
			//	.header("accept", MediaType.APPLICATION_BINARY)
				.header("Accept", "*/*")
				.header("Cookie", "SESSION=ZWJkNTBiZmUtMGY5Yy00NWY2LTgwMDItYTc3NzUwNGI1NjAw")
				.header("XSRF-TOKEN","8eda2094-2dc5-4075-9545-0b86493c5975")
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")		
				.body("")
				.get(baseURI+basePath)			
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
		
	    
	}


}
