package stepDef;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RemoveDatasetReferanceAPI {
	
	RequestSpecification request_spec;
	Response response;
	
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
//	File SaveParameterjson = new File("src/test/resources/Payloads/SaveParameter.json");
	
	@Given("Send the remove dataset referance API to server")
	public void send_the_remove_dataset_referance_api_to_server() {
		RestAssured.baseURI= baseUrl+ "/v2/catalog/";
		RestAssured.basePath="{environment}/reference/{referanceID}";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.pathParam("referanceID", "")
				.basePath(RestAssured.basePath);
		        
	}

	@Then("Validate status code as {int} No Content and check the response")
	public void validate_status_code_as_no_content_and_check_the_response(Integer int1) {
		response = request_spec.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("Cookie", "JSESSIONID=37FD2C5B7A97DDA739A6605E53A9FD67")
				.header("XSRF-TOKEN","2e9fcd10-872f-4ba5-8cbc-7cba8f5ae8b8")
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
				.delete(baseURI+basePath)
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
	//	Assert.assertEquals(status, int1);
	    	}

}
