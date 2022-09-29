package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination {
	RequestSpecification req;
	ResponseSpecification respSpec;
	Response response;
	RequestSpecification resps;
	static String token;
	static String spID;
	
	@Given("Valid username, password, firstName, lastName, businessName and phoneNumber")
	public void valid_username_password_first_name_last_name_business_name_and_phone_number() {
		req = new RequestSpecBuilder().setBaseUri("http://ec2-23-22-142-112.compute-1.amazonaws.com:8081")
				.setContentType(ContentType.JSON).build();
		 respSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		resps = given().spec(req)
				 .body("{\"username\":\"+923334443321\",\"password\":\"webdir123R!\",\"firstName\":\"fahad\",\"lastName\":\"awan\",\"businessName\":\"testingAPI\",\"phoneNumbers\":[{\"phoneNumber\":\"+923334443338\",\"phoneType\":\"Primary\"}]}");
	}
	
	@When("User calls Sign Up API with Put Http Request")
	public void user_calls_sign_up_api_with_put_http_request() {
		response = resps.when().put("/serviceproviders/register");
	}
	
	@Then("message in Response body is {string}")
	public void message_in_response_body_is(String string) {
		response.then().spec(respSpec).extract().response();
		
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		String message = js.get("message");
		System.out.println(message);
		System.out.println(responseString);
	}

	
	@Given("Valid username and password")
	public void valid_username_and_password() {
		//RestAssured.baseURI="http://ec2-23-22-142-112.compute-1.amazonaws.com:8081";
		 req = new RequestSpecBuilder().setBaseUri("http://ec2-23-22-142-112.compute-1.amazonaws.com:8081")
				.setContentType(ContentType.JSON).build();
		 respSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		 resps = given().spec(req)
				 .body("{\r\n"
				+ "    \"username\": \"+923334443321\",\r\n"
				+ "    \"password\": \"webdir123R!\"\r\n"
				+ "}");
		
		
	}
	
	@When("User calls login API with Post Http Request")
	public void user_calls_login_api_with_post_http_request() {
		response = resps.when().post("/serviceproviders/login");
				
	}
	
	@Then("Status in response body is {int}")
	public void status_in_response_body_is(Integer int1) {
		response.then().spec(respSpec).extract().response();
		
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		String message = js.get("message");
		token = js.get("tokenDetails.accessToken.token");
		spID = js.get("tokenDetails.refreshToken.userId");
		System.out.println(message);
		System.out.println(spID);
		System.out.println(token);
		System.out.println(responseString);
}

	@Given("Valid Service Provider ID and Bearer token for ApproveSP API")
	public void valid_service_provider_id_and_bearer_token_for_approve_sp_api() {
		req = new RequestSpecBuilder().setBaseUri("http://ec2-23-22-142-112.compute-1.amazonaws.com:8081")
				.addHeader("Authorization", "Bearer " + token)
				.setContentType(ContentType.JSON).build();
		 respSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		 resps = given().spec(req);
	}
	@When("User calls ApproveSP API with Post Http Request")
	public void user_calls_approve_sp_api_with_post_http_request() {
		response = resps.when().post("/serviceproviders/approve/id/"+ spID);
	}
	@Then("Status in response body is {int} for ApproveSP API")
	public void status_in_response_body_is_for_approve_sp_api(Integer int1) {
		response.then().spec(respSpec).extract().response();
		
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		String message = js.get("message");
		System.out.println(message);
		System.out.println(responseString);
	}


	@Given("Valid Service Provider ID and Bearer token for SPSearchByID API")
	public void valid_service_provider_id_and_bearer_token_for_sp_search_by_id_api() {
		req = new RequestSpecBuilder().setBaseUri("http://ec2-23-22-142-112.compute-1.amazonaws.com:8081")
				.addHeader("Authorization", "Bearer " + token)
				.setContentType(ContentType.JSON).build();
		 respSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		 resps = given().spec(req);
	}
	@When("User calls SPSearchByID API with Get Http Request")
	public void user_calls_sp_search_by_id_api_with_get_http_request() {
		response = resps.when().get("/serviceproviders/id/"+ spID);
	}
	@Then("Status in response body is {int} for SPSearchByID API")
	public void status_in_response_body_is_for_sp_search_by_id_api(Integer int1) {
		response.then().spec(respSpec).extract().response();
		
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		String message = js.get("message");
		System.out.println(message);
		System.out.println(responseString);
	}

	@Given("Valid Service Provider ID, search radius and Bearer token for Hirings Available API")
	public void valid_service_provider_id_search_radius_and_bearer_token_for_hirings_available_api() {
		req = new RequestSpecBuilder().setBaseUri("http://ec2-23-22-142-112.compute-1.amazonaws.com:8081")
				.addHeader("Authorization", "Bearer " + token)
				.addQueryParam("serviceProviderId", spID)
				.addQueryParam("searchRadius", 10)
				.setContentType(ContentType.JSON).build();
		 respSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		
		 resps = given().spec(req);
	}
	@When("User calls Hirings Available API with Get Http Request")
	public void user_calls_hirings_available_api_with_get_http_request() {
		response = resps.when().get("/serviceproviders/hirings/available");
	}
	@Then("Status in response body is {int} for Hirings Available API")
	public void status_in_response_body_is_for_hirings_available_api(Integer int1) {
		response.then().spec(respSpec).extract().response();
		
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		String message = js.get("message");
		System.out.println(message);
		System.out.println(responseString);
	}







	
}
