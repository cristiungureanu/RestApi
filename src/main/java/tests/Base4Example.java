package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent4;
import utils.DataBuilder;

public class Base4Example extends BaseComponent4{

	/*
	 * GET 1 : https://keytodorestapi.herokuapp.com/api/{id}
	 * GET ALL : https://keytodorestapi.herokuapp.com/api/
	 * POST : https://keytodorestapi.herokuapp.com/api/save
	 * PUT : https://keytodorestapi.herokuapp.com/api/todos/{id}
	 * DELETE: https://keytodorestapi.herokuapp.com/api/delete/{id}
	 * 
	 * 		requestSpec =  new RequestSpecBuilder()
				.setBaseUri("https://keytodorestapi.herokuapp.com/")
				.setBasePath("api/")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
				
	 * 	case "POST": result = given().spec(requestSpec).body(body).post("save");
	 */
	
	String id;
	
	@Test
	public void createTodo() {
		
		
		Response resp = doRequest("POST", "", DataBuilder.buildTodo().toJSONString());
		id = resp.jsonPath().getString("id");	
		System.out.println(resp.asPrettyString());		
	}
	@Test(priority = 1)
	public void getTodo() {
		
		System.out.println("--------------GET-----------------");
		Response resp = doRequest("GET", id, "");
		System.out.println(resp.asPrettyString());		
	
	}
	
	@Test(priority = 2)
	public void updateTodo() {
		
		System.out.println("--------------PUT-----------------");
		Response resp = doRequest("PUT", id, DataBuilder.buildTodo().toJSONString());
		System.out.println(resp.asPrettyString());		
	
	}
	
	@Test(priority = 3)
	public void deleteTodo() {
		
		System.out.println("--------------DELETE-----------------");
		Response resp = doRequest("delete", id, DataBuilder.buildTodo().toJSONString());
		System.out.println(resp.asPrettyString());	
	}
	
}
