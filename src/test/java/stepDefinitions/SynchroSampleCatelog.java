package stepDefinitions;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SynchroSampleCatelog {
	RequestSpecification request_spec;
	Response response;
	
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	File SynchroSamplejson = new File("src/test/resources/Payloads/SynchroSample.json");
	Scenario scn;
	
	@Given("Send the synchro sample catelog API to the server")
	public void send_the_synchro_sample_catelog_api_to_the_server() {
		RestAssured.baseURI= baseUrl+ "/v2/catalog/";
		RestAssured.basePath="{environment}/samplecatalog/synchro";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
		//		.pathParam("datasourceId", "569")
				.basePath(RestAssured.basePath)
		        .body(SynchroSamplejson);
	}

	@Then("Validate the response code as {int} OK and Fetch the response")
	public void validate_the_response_code_as_ok_and_fetch_the_response(Integer int1) {
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
	}


}
