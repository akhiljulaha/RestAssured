package com.pet.api;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetLombok.Category;
import com.pet.api.PetLombok.Tag;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class CreatePetTest {
	@Test
	public void createTest() {
		
		RestAssured.baseURI = "https://petStore.swagger.io";
		Category category = new Category(1, "Dog");
		List<String> photoUrl = Arrays.asList("https://www.dog.com","https://www.dog1.com");
		Tag tag = new Tag(5, "Red");
		Tag tag1 = new Tag(4, "Blue");
		List<Tag> tags = Arrays.asList(tag, tag1);

		
		PetLombok pet = new PetLombok(300,category,"Ronney", photoUrl, tags, "available");
		
		Response response = RestAssured.given()
				                  .contentType(ContentType.JSON)
				                        .body(pet)
				                            .when()
                                                .post("/v2/pet");
		System.out.println(response.statusCode());
		response.prettyPrint();
				
				
				
				
				
				
				
				
				
				
	}
}
