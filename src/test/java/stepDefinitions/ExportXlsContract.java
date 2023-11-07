package stepDefinitions;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExportXlsContract {
	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	

	@Given("Export Xls Contract {int} API endpoints are sent to server")
	public void export_xls_contract_api_endpoints_are_sent_to_server(Integer int1) {
		RestAssured.baseURI= baseUrl+ "/v2/interfacecontract/";
		RestAssured.basePath="{environment}/datasource/{datasource}/export/{version}/xls";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParams("environment", "INT","datasource","29B","version","15.0")
				.basePath(RestAssured.basePath);
		scn.log("Requested URL: " + baseURI + basePath);
	}

	@Then("Response of Export Xls Contract API contains status code {int}")
	public void response_of_export_xls_contract_api_contains_status_code(Integer int1) {
		response = request_spec
				.queryParam("sheetNameVersions", "BYPASS:0.4")
				.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.header("Accept", "text/plain")
				.header("Accept","*/*")
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
				.header("Cookie", "SESSION=NGQ5ZmUzYWUtNGYyYi00YzgwLTg2NDgtMWE0ZGQyMDM5ZGZm")
				.header("XSRF-TOKEN","ed2c8be8-d07d-4f15-8fad-c50c22b737a0")
				.get(baseURI+basePath)
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
		Assert.assertEquals(status, int1);
		scn.log("Response: "+ response.asPrettyString());
	}
}
