package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIWithoutBDD {
	RequestSpecification request;

	@BeforeTest
	public void setup() {
		// Request:
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "Bearer 159b39ff866232ff73c9d1d717585c322d07a317fefa4b8bad8ab3f408b67095");
	}

	@Test
	public void getAllUserAPITest() {
		Response response = request.get("/public/v2/users");

		int statusCode = response.statusCode();
		System.out.println("Status code " + statusCode);

		Assert.assertEquals(statusCode, 200);

		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

//		response.prettyPrint();

		String contentType = response.header("Content-Type");
		System.out.println(contentType);

		List<Header> headersList = response.headers().asList();
		System.out.println(headersList.size());

		for (Header h : headersList) {
			System.out.println(h.getName() + " ==== " + h.getValue());
		}

	}

	@Test
	public void getAllUsersWithQueryParamaterAPITest() {
		request.queryParam("name", "naveen");
		request.queryParam("status", "active");
		Response response = request.get("/public/v2/users");

		int statusCode = response.statusCode();
		System.out.println("Status code " + statusCode);

		Assert.assertEquals(statusCode, 200);

		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		response.prettyPrint();

		String contentType = response.header("Content-Type");
		System.out.println(contentType);

		List<Header> headersList = response.headers().asList();
		System.out.println(headersList.size());

		for (Header h : headersList) {
			System.out.println(h.getName() + " ==== " + h.getValue());
		}

	}
	
	@Test
	public void getAllUsersWithQueryParameter_withHasMap_APITest() {
		
		Map<String, String> queryParmMap = new HashMap<String, String>();
		queryParmMap.put("name", "naveen");
		queryParmMap.put("gender", "male");
		
		request.queryParams(queryParmMap);
		Response response = request.get("/public/v2/users");

		int statusCode = response.statusCode();
		System.out.println("Status code " + statusCode);

		Assert.assertEquals(statusCode, 200);

		String statusMesg = response.statusLine();
		System.out.println(statusMesg);

		response.prettyPrint();

		String contentType = response.header("Content-Type");
		System.out.println(contentType);

		List<Header> headersList = response.headers().asList();
		System.out.println(headersList.size());

		for (Header h : headersList) {
			System.out.println(h.getName() + " ==== " + h.getValue());
		}
		
	}

}
