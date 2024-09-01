package POSTAPIs;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;

import java.util.UUID;

public class CreateUserWithPojoTest {

	
	public static String getRandomEmail() {
		return "automation"+ UUID.randomUUID()+ "@gamil.com";
	}
	
	@Test
	public void addUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		user user = new user("Naveen", getRandomEmail(), "male", "active");
		
		int id = given().log().all()
		    .contentType(ContentType.JSON)
		       .body(user)
		       .header("Authorization", "Bearer 159b39ff866232ff73c9d1d717585c322d07a317fefa4b8bad8ab3f408b67095").
		           when()
		              .post("/public/v2/users/").
		           then().log().all()
		               .assertThat()
		                   .statusCode(201)
		                      .and()
		                        .body("name", equalTo(user.getName()))
		                            .extract()
		                               .path("id");
		System.out.println("User Id +"+ id);
		
		given()
	       .header("Authorization", "Bearer 159b39ff866232ff73c9d1d717585c322d07a317fefa4b8bad8ab3f408b67095")
	          .when().log().all()
	            .get("/public/v2/users/"+ id)
	              .then()
	                 .assertThat()
	                     .statusCode(200)
	                        .and()
	                            .body("name", equalTo(user.getName()))
	                                .and()
	                                    .body("status", equalTo(user.getStatus()));
	                                
		
		
		
	}
	
        
}
