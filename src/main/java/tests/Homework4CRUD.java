package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * 
 * 
 * - Vom face 3 teste in RestAssured pentru metodele http GET, POST si DELETE
 - API-urile sunt cele din applicatia :
- https://keytrcrud.herokuapp.com/
- Datele de test le vom genera folosind libraria FAKER
- Modelul dupa care vom scrie testele este cel cu Response
- Primul va fi POST si vom refolosi id-ul pentru GET si DELETE
- Pentru assertrui vom avea :
- La POST si DELETE verificam : success si msg si status code
- La GET verificam doar status code
 * @author cristinel.ungureanu
 *
 */



public class Homework4CRUD {

	
	JSONObject body;
	String userId;
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://keytrcrud.herokuapp.com/";
		body = new JSONObject();
		
		//am pus in interiorul jsonului cele 2 campuri de care am nevoie
		Faker fake = new Faker();
		body.put("age", fake.number().numberBetween(5, 130));
		body.put("email", fake.internet().emailAddress());
		body.put("gender", "m");
		body.put("name", fake.cat().name());
		
	}
	
	@Test(priority=1)
	public void postAUserTest() {
		
		Response obj = 
			given().
				contentType(ContentType.JSON).
				body(body.toJSONString()).
			when().
				post("api/users").
			then().
				statusCode(201).
				body("msg",equalTo("Successfully added!")).
				body("result._id",anything()).
				assertThat().body("success",equalTo(true)).
				log().all().  //logam in interiorul consolei raspunsul intors
				extract().response();
		
		assertEquals(obj.jsonPath().getString("success"), "true");
		userId = obj.jsonPath().getString("result._id");
	}
	
	//verifica userul adaugat
	//@Test(priority=3)
	public void getAddedUsers() {
		
		Response response = 
				given().
					get("api/users/"+userId). 
				then().
					statusCode(200). 
					extract().
					response();
		
		System.out.println(response.jsonPath().getString("result._id"));
		System.out.println(response.asPrettyString());
	}
	
	//verifica toti userii
	//@Test(priority=2)
	public void getAllUsers() {
		
		Response response = 
				given().
					get("api/users"). 
				then().
					statusCode(200). 
					extract().
					response();
		
		System.out.println(response.asPrettyString());
	}
	
	//sterge userul adaugat
	@Test(priority=4)
	public void deleteAddedUser() {
		given().
			delete("api/users/"+userId).
		then().
			statusCode(200).
			body("msg",equalTo("It has been deleted.")). 
			assertThat().body("success",equalTo(true));


	}
}
