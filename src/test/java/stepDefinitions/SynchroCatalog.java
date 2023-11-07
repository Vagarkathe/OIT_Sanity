package stepDefinitions;

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

public class SynchroCatalog {

	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	File SynchroCatalogjson = new File("src/test/resources/Payloads/SynchroCatalog.json");
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	
	@Given("Details of Synchro Catalog API are sent to server")
	public void details_of_synchro_catalog_api_are_sent_to_server() {
		RestAssured.baseURI= baseUrl+ "/v2/catalog/";
		RestAssured.basePath="{environment}/synchro";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.basePath(RestAssured.basePath)
				.body(SynchroCatalogjson);
		scn.log("Requested URL: " + baseURI + basePath);
	}

	@Then("Check the response code as {int} and fetch the response")
	public void check_the_response_code_as_and_fetch_the_response(Integer int1) {
		response = request_spec.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
				.post(baseURI+basePath)
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
		Assert.assertEquals(status, int1);
		
		scn.log("Response:" +response.asPrettyString());
	}
}
