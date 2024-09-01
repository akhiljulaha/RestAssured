package POSTAPIs;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.credentials;

public class BookingAuthWithPojoTest {

	
	@Test
	public void getBookingAuthTokenTest_With_JSON_String() {
		RestAssured.baseURI ="https://restful-booker.herokuapp.com";
		credentials cred = new credentials("admin", "password123");
		String c = given().log().all()
		     .contentType(ContentType.JSON)
		        .body(cred)
		            .when()
		                .post("/auth")
		                    .then().log().all()
		                        .assertThat()
		                            .statusCode(200)
		                                .extract()
		                                   .path("token");
		System.out.println(c);
		Assert.assertNotNull(c);
		
}
}
