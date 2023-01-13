package utils;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class BaseComponent2 {

	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void createRequestSpecification() {
		
		requestSpec = new RequestSpecBuilder() 
				.setBaseUri("https://keytodorestapi.herokuapp.com/")
				.setBasePath("api/save")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
		
		
	}
	
	@BeforeClass
	public static void createResponseSpecification() {
		
		responseSpec =  new ResponseSpecBuilder()
				.expectStatusCode(anyOf(is(200),is(201)))
				.build();
	}
	
	//POST
		public static Response doPostRequest(String body) {
			
			Response result = 
					given(). 
						spec(requestSpec). 
						body(body). 
					when(). 
						post(). 
					then(). 
						spec(responseSpec). 
						extract().response();
			
			return result;
					
					
		}
		
		//PUT
		public static Response doPutRequest(String id, String body) {
			
			Response result = 
					given(). 
						spec(requestSpec).  
						body(body). 
					when(). 
						put(id). 
					then(). 
						spec(responseSpec). 
						extract().response();
			
			return result;
					
					
		}
		
		//Get
		public static Response doGetOneRequest(String id) {
			Response result = 
					given(). 
						spec(requestSpec). 
					when(). 
						get(id). 
					then(). 
						spec(responseSpec) .
						extract().response();
			
			return result;
		}
		
		//delete
		public static Response doDeleteRequest(String id) {
			Response result = 
					given(). 
						spec(requestSpec).  
					when(). 
						delete(id). 
					then(). 
						spec(responseSpec). 
						extract().response();
			
			return result;
		}
		
		//Get
				public static Response doGetAllRequest() {
					Response result = 
							given(). 
								spec(requestSpec). 
							when(). 
								get(). 
							then(). 
								spec(responseSpec) .
								extract().response();
					
					return result;
				}
}
