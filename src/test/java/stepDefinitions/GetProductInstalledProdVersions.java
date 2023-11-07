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

public class GetProductInstalledProdVersions {
	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	

	@Given("Get Product Installed Prod Versions {int} API valid endpoints are sent to server")
	public void get_product_installed_prod_versions_api_valid_endpoints_are_sent_to_server(Integer int1) {
		RestAssured.baseURI= baseUrl+ "/v2/interfacecontract/";
		RestAssured.basePath="{environment}/product/installed/prodversions";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.basePath(RestAssured.basePath);
		scn.log("Requested URL: " + baseURI + basePath);
	}

	@Then("Validate the response code as {int} and Fetch the response of Product Installed Prod Versions API")
	public void validate_the_response_code_as_and_fetch_the_response_of_product_installed_prod_versions_api(Integer int1) {
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
