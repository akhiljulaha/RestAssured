package GETAPIs;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;

import static io.restassured.RestAssured.*;
public class GETAPIWithBDD {

@Test
public void getProductsTest() {
	given()
	    .when()
	       .get("https://fakestoreapi.com/products")
	           .then().log().all()
	              .assertThat()
	                  .statusCode(200)
	                      .and()
	                          .contentType(ContentType.JSON)
	                              .and()
	                                 .header("X-Powered-By", "Express")
	                                     .and()
	                                         .body("$.size()", equalTo(20))
	                                             .and()
	                                                 .body("id", is(notNullValue()))
	                                                     .and()
	                                                         .body("title", hasItem("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"));
	                                                		 
}

@Test
public void getProductDataWithQueryParmTest() {
	RestAssured.baseURI = "https://fakestoreapi.com";
	
	given().log().all()
	    .queryParam("limit", 4)
	    .queryParam("name", "naveen")
	        .when().log().all()
	            .get("/products")
	                .then()
	                    .assertThat()
	                       .statusCode(200);	           
}

@Test
public void getProductData_FetchTheDataForTheSpecificIndex() {
	RestAssured.baseURI = "https://fakestoreapi.com";
	
	Response response = given().log().all()
	    .queryParam("limit", 4)
	    .queryParam("name", "naveen")
	        .when().log().all()
	            .get("/products");
	
	JsonPath js = response.jsonPath();
	int firstProductTitle = js.getInt("[0].id");
	System.out.println(firstProductTitle);
	float rate = js.getFloat("[0].rating.rate");
	System.out.println();
	                
}
@Test
public void getProductData_FetchTheList() {
	RestAssured.baseURI = "https://fakestoreapi.com";
	
	Response response = given().log().all()
	    .queryParam("limit", 4)
	    .queryParam("name", "naveen")
	        .when().log().all()
	            .get("/products");
	JsonPath js = response.jsonPath();
	List<Integer> Ids = js.getList("id");
	List<String> title = js.getList("title");
	List<Float> price = js.getList("price");
	List<Object> rate = js.getList("rating.rate");
	List<Integer> count = js.getList("rating.count");
	for(int i=0;i<Ids.size();i++) {
		int id = Ids.get(i);
		String titles = title.get(i);
		float prices = price.get(i);
		Object rates = rate.get(i);
		int counts = count.get(i);
		System.out.println(id +" "+ titles +" "+ prices+" "+ rates +" " +counts);
		


	}


}
}
