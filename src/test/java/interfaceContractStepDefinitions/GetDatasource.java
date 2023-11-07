package interfaceContractStepDefinitions;

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

public class GetDatasource {
	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	

	@Given("Get Datasource {int} API valid endpoints sent to server")
	public void get_datasource_api_valid_endpoints_sent_to_server(Integer int1) {
		RestAssured.baseURI= baseUrl+ "/v2/interfacecontract/";
		RestAssured.basePath="{environment}/datasource/{id}";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParams("environment", "INT","id","3464")
				.basePath(RestAssured.basePath);
		scn.log("Requested URL: " + baseURI + basePath);
	}

	@Then("Fetch the response of Get Datasource API and check the response code as {int}")
	public void fetch_the_response_of_get_datasource_api_and_check_the_response_code_as(Integer int1) {
		response = request_spec
				.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
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
