package stepDef;

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

public class DatasourceByNameAPI {
	
	RequestSpecification request_spec;
	Response response;
	String baseUrl = "https://portal-int-edh.bigdata.intraorange/api";
	// SynchroOrangeCartojson = new File("src/test/resources/Payloads/SynchroOrangeCarto.json");
	
	Scenario scn;
	
	@Before
	public void Before(Scenario s) {
		this.scn=s;
	}
	
	@Given("Name Datasources API are sent to server")
	public void name_datasources_api_are_sent_to_server() {
		RestAssured.baseURI= baseUrl+ "/v2/catalog/";
		RestAssured.basePath="{environment}/datasource/{datasource}";
		request_spec = given().baseUri(RestAssured.baseURI)
				.pathParam("environment", "INT")
				.pathParam("datasource", "FUNCTEST4")
				.basePath(RestAssured.basePath);
		scn.log("Requested URL: " + baseURI + basePath);
		scn.log(request_spec.toString());
	}

	@Then("Fetch the response and check the response code as {int} OK")
	public void fetch_the_response_and_check_the_response_code_as_ok(Integer int1) {
		 response = request_spec.when()
					.relaxedHTTPSValidation()
					.contentType(ContentType.JSON)
					.header("Content-Type", "application/json;charset=UTF-8")
					.header("Cookie", "JSESSIONID=A4ECDD85EB9E61874F2DE33A18E54C82")
					.header("XSRF-TOKEN","20ba1612-fe31-4a0e-ad9b-08c3203b26b5")
					.header("Authorization", "Basic VEVTVF9NQU5BTDpyd0grLjQqcX5ATEV1KjVN")
					.get(baseURI+basePath)
					.andReturn();
			
			response.then().log().all().extract().response();		
			int status = response.getStatusCode();
			Assert.assertEquals(status, 200);
			scn.log("Status Code:" +response.statusCode() + "Status Line:" + response.statusLine());
//			scn.log("Status: " + response.getStatusLine().toString());
			scn.log("Cookies: "+ response.detailedCookies().toString());
			scn.log("Headers: "+ response.getHeaders().toString());

	}
	

}
