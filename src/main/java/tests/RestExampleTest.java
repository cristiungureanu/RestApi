package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestExampleTest {

	@Test
	public void postATodo() {
		
		//Json Object
		JSONObject requestPayload = new JSONObject();
		requestPayload.put("title", "Dragos New Title");
		requestPayload.put("body", "request body bla bla");
		
		File fisier = new File("data1.json");
		
		Response response = RestAssured
				.given()
					.header("Content-Type", getClass(), "application/json")
					.header("accept","application/json")
				.when()
					//.body("{\"title\":\"dwadada\",\"body\":\"awdadadada\"}")   sau
					//.body(requestPayload.toJSONString())      sau
				.body(fisier)
					.post("https://keytodorestapi.herokuapp.com/api/save")
				.then()
					.assertThat().statusCode(200)
					.extract().response();
		
		System.out.println(response.asPrettyString());
		String id = response.jsonPath().getString("id");
		System.out.println(id);
		String info = response.jsonPath().getString("info");
		System.out.println(info);
		
		assertEquals(info, "Todo saved! Nice job!");
	}
	
	
	
}
