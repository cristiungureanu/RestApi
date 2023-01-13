package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;

public class BaseComponent {

	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI = "https://keytrcrud.herokuapp.com/";
	}
	
	//POST
	public static Response doPostRequest(String path, String body, int statusCode) {
		
		Response result = 
				given(). 
					contentType(ContentType.JSON). 
					body(body). 
				when(). 
					post(path). 
				then(). 
					statusCode(statusCode). 
					extract().response();
		
		return result;
				
				
	}
	
	//PUT
	public static Response doPutRequest(String path, String body, int statusCode) {
		
		Response result = 
				given(). 
					contentType(ContentType.JSON). 
					body(body). 
				when(). 
					put(path). 
				then(). 
					statusCode(statusCode). 
					extract().response();
		
		return result;
				
				
	}
	
	//Get
	public static Response doGetRequest(String path, String id, int statusCode) {
		Response result = 
				given(). 
					contentType(ContentType.JSON). 
				when(). 
					get(path+id). 
				then(). 
					statusCode(statusCode). 
					extract().response();
		
		return result;
	}
	
	//delete
	public static Response doDeleteRequest(String path, String id, int statusCode) {
		Response result = 
				given(). 
					contentType(ContentType.JSON). 
				when(). 
					delete(path+id). 
				then(). 
					statusCode(statusCode). 
					extract().response();
		
		return result;
	}
}
