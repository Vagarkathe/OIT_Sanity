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

public class HistoriesFindAllHistoryByDatasourceIdAPI {
	
	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	
	File AddContractjson = new File("src/test/resources/Payloads/AddContractVersionHistory4.json");
	
	
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	
	
	@Given("Histories dataset API")
	public void histories_dataset_api() {
		
		RestAssured.baseURI= baseUrl+ "/v2/interfacecontract/";
		RestAssured.basePath="{environment}/histories";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.basePath(RestAssured.basePath)
				.body(AddContractjson);
		
		scn.log("Requested URL: " + baseURI + basePath);
		scn.log(request_spec.toString());
	}

	@Then("The streaming interface contract history should be displayed")
	public void the_streaming_interface_contract_history_should_be_displayed() {
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
		Assert.assertEquals(status, 201);
	}

}
