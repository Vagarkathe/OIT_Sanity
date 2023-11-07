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

public class ListDatasetsByDatasource {
	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	// SynchroOrangeCartojson = new File("src/test/resources/Payloads/SynchroOrangeCarto.json");
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	@Given("Details of list Datasets By Datasource API are sent to server")
	public void details_of_list_datasets_by_datasource_api_are_sent_to_server() {
		RestAssured.baseURI= baseUrl+ "/v2/catalog/";
		RestAssured.basePath="{environment}/datasource/{datasource}/datasets";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.pathParam("datasource", "29B")
				.basePath(RestAssured.basePath);
		scn.log("Requested URL: " + baseURI + basePath);
		scn.log(request_spec.toString());
	    
	}

	@Then("Check the response code as {int} OK and fetch the response")
	public void check_the_response_code_as_ok_and_fetch_the_response(Integer int1) {
		response = request_spec.when()
				.relaxedHTTPSValidation()
				.contentType(ContentType.JSON)
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
				.get(baseURI+basePath)
				.andReturn();
		
		response.then().log().all().extract().response();		
		int status = response.getStatusCode();
		Assert.assertEquals(status, 200);
		scn.log("Status Code:" +response.statusCode() + "Status Line:" + response.statusLine());
//		scn.log("Status: " + response.getStatusLine().toString());
		scn.log("Cookies: "+ response.detailedCookies().toString());
		scn.log("Headers: "+ response.getHeaders().toString());

		
	}

}
