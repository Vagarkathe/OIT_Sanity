package stepDef;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateDatasourceAPI {
	
	RequestSpecification request_spec;
	Response response;
	
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	File UpdateDatasourcejson = new File("src/test/resources/Payloads/updateSource.json");
	Scenario scn;
	
	@Given("Send the Update Datasource API with its details to the server")
	public void send_the_update_datasource_api_with_its_details_to_the_server() {
		RestAssured.baseURI= baseUrl+ "/v2/catalog/";
		RestAssured.basePath="{environment}/datasource/{datasourceId}";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.pathParam("datasourceId", "569")
				.basePath(RestAssured.basePath)
		        .body(UpdateDatasourcejson);
	//	scn.log("Requested URL: " + baseURI + basePath);
	//	scn.log(request_spec.toString());
	}

	@Then("Validate {int} OK as the status code and Fetch the response")
	public void validate_ok_as_the_status_code_and_fetch_the_response(Integer int1) {
		response = request_spec.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("Cookie", "JSESSIONID=37FD2C5B7A97DDA739A6605E53A9FD67")
				.header("XSRF-TOKEN","2e9fcd10-872f-4ba5-8cbc-7cba8f5ae8b8")
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
				.put(baseURI+basePath)
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
		Assert.assertEquals(status, int1);
	}

}
