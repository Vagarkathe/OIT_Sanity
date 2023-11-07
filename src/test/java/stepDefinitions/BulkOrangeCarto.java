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

public class BulkOrangeCarto {

	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	File BulkOrangeCartojson = new File("src/test/resources/Payloads/BulkOrangeCarto.json");
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	
	@Given("Bulk Orange Carto Endpoint")
	public void bulk_orange_carto_endpoint() {
		RestAssured.baseURI= baseUrl+ "/v2/catalog/";
		RestAssured.basePath="{environment}/orangecarto/bulk";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.basePath(RestAssured.basePath)
				.body(BulkOrangeCartojson);
		scn.log("Requested URL: " + baseURI + basePath);
	}

	@Then("Send the API to server and check the response contains status code as {int}")
	public void send_the_api_to_server_and_check_the_response_contains_status_code_as(Integer int1) {
		response = request_spec.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
				.header("Cookie", "JSESSIONID=89897F180AF3EED53F0732204B8DE35C")
				.header("XSRF-TOKEN","2e9fcd10-872f-4ba5-8cbc-7cba8f5ae8b8")	
				.post(baseURI+basePath)
				.andReturn();
		
		response.then().log().all().extract().response();	
		int status = response.getStatusCode();
		scn.log("Status: " +response.statusLine());
		Assert.assertEquals(status, int1);
		scn.log("Response: " +response.asPrettyString());
	}
	
}
