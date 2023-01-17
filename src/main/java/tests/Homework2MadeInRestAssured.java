package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * 
 * Testul facut la tema nr 2 in Postman, va fi facut acum in RestAssured
- https://fakerestapi.azurewebsites.net/api/v1/Books
- Acelasi test in 2 modalitati diferite.
- request body va fi dat sub forma unui fisier extern json.
- assertam status code 200 si faptul ca raspunsul contine pe elementul “id” valoarea trimisa de noi.
- Pentru rest assured pathul de assert este similar codului scris la curs, deci asa:
- postREquest.jsonPath().getString(”id");
 * @author cristinel.ungureanu
 *
 */

public class Homework2MadeInRestAssured {

	@Test
	public void postBooks() {
		

		File fisier =  new File("data3.json");
		
		Response response = RestAssured
				.given()
				    .header("Content-Type", "application/json")
				    .header("accept", "*/*" )
				.when()
					.body(fisier)
					.post("https://fakerestapi.azurewebsites.net/api/v1/Books")
				.then()
					.assertThat().statusCode(200)
					.extract().response();
		
		System.out.println(response.asPrettyString());
		
		assertEquals(response.jsonPath().getString("id"), "235");
	}
}
