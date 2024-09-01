package POSTAPIs;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class OAuth2Test {

static String accessToken;
	
	@BeforeMethod
	public void getAccessToken() {
		// 1. POST - get the access token
		RestAssured.baseURI = "https://test.api.amadeus.com";
		
		 accessToken = given()
		    .header("Content-Type", "application/x-www-form-urlencoded")
		    .formParam("grant_type", "client_credentials")
		    .formParam("client_id", "vfPBqHSNnTCU3yGgzvp1ValAYEz0Yqny")
		    .formParam("client_secret", "O50C35MULSJQzdda")
		.when()
		    .post("/v1/security/oauth2/token")
		.then()
		    .assertThat()
		        .statusCode(200)
		        .extract().path("access_token");
		    
		    System.out.println(accessToken);
	}
	
	@Test
	public void getFlightInfoTest() {
		Response flightDataResponse = given().log().all()
		.header("Authorization", "Bearer "+ accessToken)
		.queryParam("origin", "PAR")
		.queryParam("maxPrice", 200)
		  .when().log().all()
		     .get("v1/shopping/flight-destinations")
		     .then().log().all()
		        .assertThat()
		           .statusCode(200)
		              .and()
		                 .extract()
		                    .response();
		JsonPath js = flightDataResponse.jsonPath();
		String type = js.get("data[0].type");
		System.out.println(type);
	}
}
