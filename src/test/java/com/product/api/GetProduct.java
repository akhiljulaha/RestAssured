package com.product.api;
import static io.restassured.RestAssured.given;		

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetProduct {

	@Test
	public void getProductTest_With_POJO() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given()
		   .when()
		       .get("/products");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Product product[] = mapper.readValue(response.getBody().asString(), Product[].class);
			
			for(Product p : product) {
				System.out.println("ID : "+ p.getId());
				System.out.println("Title : "+ p.getTitle());
				System.out.println("Price : "+ p.getPrice());
				System.out.println("Description : "+ p.getDescription());
				System.out.println("category : "+ p.getCategory());
				System.out.println("Image : "+ p.getImage());
				System.out.println("Rate : "+ p.getRating().getRate());
				System.out.println("Count : "+ p.getRating().getCount());
				System.out.println("---------------------");
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		
	}
	
	@Test
	public void getProductTest_With_POJO_Lambok() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given()
		   .when()
		       .get("/products");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Productlambok[] product = mapper.readValue(response.getBody().asString(), Productlambok[].class);
			
			for(Productlambok p : product) {
				System.out.println("ID : "+ p.getId());
				System.out.println("Title : "+ p.getTitle());
				System.out.println("Price : "+ p.getPrice());
				System.out.println("Description : "+ p.getDescription());
				System.out.println("category : "+ p.getCategory());
				System.out.println("Image : "+ p.getImage());
				System.out.println("Rate : "+ p.getRating().getRate());
				System.out.println("Count : "+ p.getRating().getCount());
				System.out.println("---------------------");
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		
	}
}
