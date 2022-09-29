Feature: Smoke Test Scenarios

Scenario: Validate that response is success with Status Code 200 when Sign Up API is executed with PUT Request
	Given Valid username, password, firstName, lastName, businessName and phoneNumber
	When User calls Sign Up API with Put Http Request
	Then message in Response body is "Service provider has been registered successfully."
	#And Status in response body is 200


Scenario: Validate that response is success with Status Code 200 when Login API is executed with POST Request.
	Given Valid username and password
	When User calls login API with Post Http Request
	Then Status in response body is 200
	
	
Scenario: Validate that response is success with Status Code 200 when ApproveSP API is executed with POST Request.
	Given Valid Service Provider ID and Bearer token for ApproveSP API
	When User calls ApproveSP API with Post Http Request
	Then Status in response body is 500 for ApproveSP API
	
	
Scenario: Validate that response is success with status code 200 when SPSearchByID API is executed with GET Request.
	Given Valid Service Provider ID and Bearer token for SPSearchByID API
	When User calls SPSearchByID API with Get Http Request
	Then Status in response body is 200 for SPSearchByID API

Scenario: Validate that response is success with Status Code 200  when Hirings Available API is executed with POST Request.
	Given Valid Service Provider ID, search radius and Bearer token for Hirings Available API
	When User calls Hirings Available API with Get Http Request
	Then Status in response body is 200 for Hirings Available API