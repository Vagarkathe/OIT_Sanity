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

public class UndeployAll {
	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	

	@Given("Undeploy All {int} API valid endpoints are sent to server")
	public void undeploy_all_api_valid_endpoints_are_sent_to_server(Integer int1) {
		RestAssured.baseURI= baseUrl+ "/v2/interfacecontract/";
		RestAssured.basePath="{environment}/undeployAll";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.basePath(RestAssured.basePath);
		scn.log("Requested URL: " + baseURI + basePath);
	}

	@Then("Check the status code as {int} for Undeploy All API")
	public void check_the_status_code_as_for_undeploy_all_api(Integer int1) {
		response = request_spec
				.queryParam("ids", "4739")
				.queryParam("options", "%7B%22version%22%3A%226.5.0-SNAPSHOT%22%2C%22ingestion%22%3Afalse%2C%22"
						+ "cleaning%22%3Afalse%2C%22catalog%22%3Atrue%2C%22purge%22%3Afalse%2C%22lineage%22%3Afalse"
						+ "%2C%22init%22%3Afalse%2C%22migrate%22%3Afalse%2C%22sla%22%3Afalse%2C%22forceUpdate%22%3"
						+ "Afalse%2C%22nifi11%22%3Afalse%2C%22noDirectories%22%3Afalse%2C%22noFiles%22%3Afalse%2C%22"
						+ "noHive%22%3Afalse%2C%22noLocalfile%22%3Afalse%2C%22noOozie%22%3Afalse%2C%22noNifi%22%3A"
						+ "false%2C%22noAtlas%22%3Afalse%2C%22noSpark%22%3Afalse%2C%22debug%22%3Afalse%2C%22undeploy"
						+ "%22%3Afalse%7D")
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
