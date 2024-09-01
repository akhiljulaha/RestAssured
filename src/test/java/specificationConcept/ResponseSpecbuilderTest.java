package specificationConcept;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;   // given , then
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*; 
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecbuilderTest {
	
	public static ResponseSpecification get_resp_spec_200_OK() {
		ResponseSpecification resp_spec_200_ok = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectHeader("Server", "cloudflare")
				.build();
		return resp_spec_200_ok;
	}
	
	public static ResponseSpecification get_resp_spec_200_OK_with_Body() {
		ResponseSpecification resp_spec_200_ok = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectHeader("Server", "cloudflare")
				.expectBody("$.size()", equalTo(10))
			    .expectBody("id", hasSize(10))
				.build();
		return resp_spec_200_ok;
	}
	
	public static ResponseSpecification get_resp_Spec_401_Auth_Fail() {
		ResponseSpecification resp_spec_200_ok = new ResponseSpecBuilder()
				.expectStatusCode(401)
				.expectHeader("Server", "cloudflare")
				.build();
		return resp_spec_200_ok;

	}
	
	@Test
	public void get_user_res_401_Auth_Fail_Spec_Test() {;
		RestAssured.baseURI = "https://gorest.co.in";
		given()
		    .header("Authorization", "Bearer --159b39ff866232ff73c9d1d717585c322d07a317fefa4b8bad8ab3f408b67095")
		        .when()
		            .get("/public/v2/users")
		                .then()
		                    .assertThat()
		                        .spec(get_resp_Spec_401_Auth_Fail());
	}
	
	
	
	@Test
	public void get_user_res_200_spec_Test() {;
		RestAssured.baseURI = "https://gorest.co.in";
		given()
		    .header("Authorization", "Bearer 159b39ff866232ff73c9d1d717585c322d07a317fefa4b8bad8ab3f408b67095")
		        .when()
		            .get("/public/v2/users")
		                .then()
		                    .assertThat()
		                        .spec(get_resp_spec_200_OK_with_Body());
	}

}
