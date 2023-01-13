package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

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
