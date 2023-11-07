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

public class UpdateDataset {

	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	File UpdateDatasetjson = new File("src/test/resources/Payloads/UpdateDataset.json");
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	
	@Given("Endpoints of Update Dataset {int} API are sent to server")
	public void endpoints_of_update_dataset_api_are_sent_to_server(Integer int1) {
		RestAssured.baseURI= baseUrl+ "/v2/catalog/";
		RestAssured.basePath="{environment}/dataset/{datasetId}";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParams("environment", "INT", "datasetId","10065")
				.basePath(RestAssured.basePath)
				.body(UpdateDatasetjson);
		scn.log("Requested URL: " + baseURI + basePath);
	}

	@Then("Check the response code as {int}")
	public void check_the_response_code_as(Integer int1) {
		response = request_spec.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.header("Content-Type", "application/json")
				.header("Accept", "*/*")
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
				.put(baseURI+basePath)
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
		Assert.assertEquals(status, int1);
		
		scn.log("Response:" +response.asPrettyString());
	}
}
