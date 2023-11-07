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

public class ListSids {

	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	

	@Given("Hit the List Subdomains Simple API")
	public void hit_the_list_subdomains_simple_api() {
		RestAssured.baseURI= baseUrl+ "/v2/catalog/";
		RestAssured.basePath="{environment}/sids";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.basePath(RestAssured.basePath);
		scn.log("Requested URL: " + baseURI + basePath);
	}

	@Then("Fetch the response and validate {int} as response code")
	public void fetch_the_response_and_validate_as_response_code(Integer int1) {
		response = request_spec
				.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.header("Accept", "*/*")
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
				.get(baseURI+basePath)
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
		Assert.assertEquals(status, int1);
		scn.log("Response: "+ response.asPrettyString());
	}
}
