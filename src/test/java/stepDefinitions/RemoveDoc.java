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

public class RemoveDoc {

	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	

	@Given("Send the Remove Doc API to server")
	public void send_the_remove_doc_api_to_server() {
		RestAssured.baseURI= baseUrl+ "/v2/catalog/";
		RestAssured.basePath="{environment}/doc/{docId}";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParams("environment", "INT","docId","10")
				.basePath(RestAssured.basePath);
		scn.log("Requested URL: " + baseURI + basePath);
	}

	@Then("Validate the status code as {int} and check the response")
	public void validate_the_status_code_as_and_check_the_response(Integer int1) {
		response = request_spec
				.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
				.header("Cookie","SESSION=OTg1ZTU3NTYtMjAxMC00ZWJiLWI2OGQtM2E5NmViMzgwMTc5")
				.header("JSESSIONID", "139026AE2E5B939775F44045A90EEE1E")
				.header("XSRF-TOKEN","54ba8c6c-ca0a-4604-bcb9-fed8af345992")
				.delete()
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
		Assert.assertEquals(status, int1);
		scn.log("Response: "+ response.asPrettyString());
	}
}
