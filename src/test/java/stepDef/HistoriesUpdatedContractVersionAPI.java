package stepDef;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HistoriesUpdatedContractVersionAPI {
	

	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	
	File updateContractjson = new File("src/test/resources/Payloads/UpdateContractVersion.json");
	
	
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	
	
	
	@Given("Interfacecontract update Contract Version")
	public void interfacecontract_update_contract_version() {
		RestAssured.baseURI= baseUrl+ "/v2/interfacecontract/";
		RestAssured.basePath="{environment}//histories/{id}";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.pathParam("id", "153")				
				.basePath(RestAssured.basePath)
				.body(updateContractjson);
		
		scn.log("Requested URL: " + baseURI + basePath);
		scn.log(request_spec.toString());
	}

	@Then("History should be updated")
	public void history_should_be_updated()  {
		response = request_spec.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("Cookie", "JSESSIONID=37FD2C5B7A97DDA739A6605E53A9FD67")
				.header("XSRF-TOKEN","2e9fcd10-872f-4ba5-8cbc-7cba8f5ae8b8")
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
				.post(baseURI+basePath)
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
		//Assert.assertEquals(status, 201);
	}

}
