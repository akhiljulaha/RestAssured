package GETAPIs;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

public class GetAPIWithPathParm {
	
	
	/*
	 * 2017 -- 20
	 * 2016 -- 21
	 * 1977 -- 9
	 * use data provider
	 */
	@DataProvider
	public Object[][] getCircuitYearData(){
		return new  Object [][] {
			{"2016", 21},
			{"2017", 20},
			{"1966", 9},
			{"2023", 22}
		};
	}
	
	@Test(dataProvider = "getCircuitYearData")
	public void getCircuitDataAPIWith_Year_DataProvider(String SessionYear, int totalCircuits) {
		RestAssured.baseURI = "http://ergast.com";
		given().log().all()
	    .pathParam("year", SessionYear)
	        .when().log().all()
	            .get("/api/f1/{year}/circuits.json")
	               .then()
	                   .assertThat()
	                       .statusCode(200)
	                           .and()
	                                .body("MRData.CircuitTable.season", equalTo(SessionYear))
	                                    .and()
	                                        .body("MRData.CircuitTable.Circuits.circuitId", hasSize(totalCircuits));
		
	}
	
	
	
	
}


