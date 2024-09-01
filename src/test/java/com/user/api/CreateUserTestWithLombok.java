package com.user.api;
import static io.restassured.RestAssured.given;
import java.util.UUID;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class CreateUserTestWithLombok {
	
	public static String getRandomEmailId() {
//		return "apiautomation"+System.currentTimeMillis()+"@mail.com";   // 1st way
		return "apiautomation"+ UUID.randomUUID()+"@gmail.com";       // 2nd way > but in this will get a hyphen
	}
	@Test
	public void createUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User("Naveen",getRandomEmailId() , "male", "active"); 
		Response response = RestAssured.given()
		    .contentType(ContentType.JSON)
		    .header("Authorization", "Bearer 159b39ff866232ff73c9d1d717585c322d07a317fefa4b8bad8ab3f408b67095")
		    .body(user)   // actual serialization happening here convert POJO to JSON
		    .when()
		       .post("/public/v2/users"); 	
		Integer userId = response.jsonPath().get("id");   //Integer capital
		System.out.println("user id : "+ userId);

		// GET API to get the same user:
		
		  
		  // 2 get the same user and verify it : GET
		  Response getResponse =	given()
		    .header("Authorization", "Bearer 159b39ff866232ff73c9d1d717585c322d07a317fefa4b8bad8ab3f408b67095")
		        .when().log().all()
		             .get("/public/v2/users/"+ userId);
		
		// de-serialization json to pojo
		  ObjectMapper mapper = new ObjectMapper();
		  try {
			User userRes = mapper.readValue(getResponse.getBody().asString(), User.class);  //de-serialization happening here convert JSON to POJO
			
			System.out.println(userRes.getId() + ": " + userRes.getEmail() + ": " + userRes.getStatus() + ": "+ userRes.getGender());
			Assert.assertEquals(user.getName(), userRes.getName());
			Assert.assertEquals(userId, userRes.getId());
			Assert.assertEquals(user.getEmail(), userRes.getEmail());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}	
	}
	
	// Builder
	@Test
	public void createUser_WithBuilderPattern_Test() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User.UserBuilder()
		     .name("Naveen")
		     .email(getRandomEmailId())
		     .status("active")
		     .gender("male")
		     .build();
		   
		Response response  = RestAssured.given().log().all()
		   .contentType(ContentType.JSON)
		    .header("Authorization", "Bearer 159b39ff866232ff73c9d1d717585c322d07a317fefa4b8bad8ab3f408b67095")
		    .body(user)   // actual serialization happening here convert POJO to JSON
		    .when()
		       .post("/public/v2/users"); 	
		Integer userId = response.jsonPath().get("id");   //Integer capital
		System.out.println("user id : "+ userId);
		
		given()
	    .header("Authorization", "Bearer 159b39ff866232ff73c9d1d717585c322d07a317fefa4b8bad8ab3f408b67095")
	    .when().log().all()
	        .get("/public/v2/users/" + userId);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.readValue(response.getBody().asString(), User.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
