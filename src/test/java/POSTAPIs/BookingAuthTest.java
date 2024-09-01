package POSTAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.File;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BookingAuthTest {

	@Test
	public void getBookingAuthTokenTest_With_JSON_String() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		String tokenId = given()
		   .contentType(ContentType.JSON)
		   .body("{\r\n"
		   		+ "    \"username\" : \"admin\",\r\n"
		   		+ "    \"password\" : \"password123\"\r\n"
		   		+ "}")
		   .when()
		       .post("/auth")
		           .then()
		               .assertThat()
		                   .statusCode(200)
		                       .extract()
		                           .path("token");
		System.out.println(tokenId);
		Assert.assertNotNull(tokenId);	
	}
	
	@Test
	public void getBookingAuthTokenTest_With_JSON_File() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		 String tokenId = given()
		   .contentType(ContentType.JSON)
		   .body(new File("./src/test/resources/data/basicauth.json"))
		   .when()
		   .post("/auth")
           .then()
               .assertThat()
                   .statusCode(200)
                       .extract()
                           .path("token");
System.out.println(tokenId);
Assert.assertNotNull(tokenId);	
}
	
	/*
	 * POST --- add a user > user ID = 123 --- assert(201, body)
	 * GET  --- get a user > /users/123 --- assert userId = 123
	 */
	
	@Test
	public void addUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		// 1. add a user - POST
		int userId = given().log().all()
		    .contentType(ContentType.JSON)
		    .body(new File("./src/test/resources/data/adduser.json"))
		    .header("Authorization", "Bearer 159b39ff866232ff73c9d1d717585c322d07a317fefa4b8bad8ab3f408b67095").
       when()
          .post("/public/v2/users/"). 
          then().log().all()
              .assertThat()
                  .statusCode(201)
                  .and()
                  .body("name", equalTo("Naveen"))
                  .extract()
                     .path("id");
		System.out.println("user id -> "+ userId);
          
	
	// 2 get the same user and verify it : GET
	given()
    .header("Authorization", "Bearer 159b39ff866232ff73c9d1d717585c322d07a317fefa4b8bad8ab3f408b67095")
        .when().log().all()
             .get("/public/v2/users/"+ userId)
                 .then()
                    .assertThat()
                        .statusCode(200)
                            .and()
                               .body("id", equalTo(userId));
                 
	}
	
}
