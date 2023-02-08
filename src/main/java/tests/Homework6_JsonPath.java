package tests;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent5;

public class Homework6_JsonPath extends BaseComponent5{

	/**
	 * 	Folosind RestApi disponibile la adresa:
 				https://fakerestapi.azurewebsites.net/index.html
 		Vom face 1 call de GET pe pathul :
 				https://fakerestapi.azurewebsites.net/api/v1/Books
 		Vom cauta in raspuns folosind JsonPath toate cartile care au page count intre 1000- 2000
 		Vom face un assert ca am gasit 10 (de obiecei nr de 10 este constant pentru conditia asta, dar daca se intampla sa nu fie, 
		folosti nr returnat in assert (nu este controlat de mine site-ul))
	 * 
	 */
	
	@Test
	public void jsonPathExamples() {
		
		Response result = doGetAllRequest();
		
		System.out.println(result.asString());
		assertTrue(result.asString().contains("Book 1"));
		
		//JsonPath
		JsonPath jsonPath = result.jsonPath();
		
		System.out.println(jsonPath.getString("pageCount"));
		
		List<String> books = jsonPath.getList("findAll{it.pageCount > 1000 & it.pageCount <= 2000}.title");
		System.out.println(books);
		
		System.out.println(jsonPath.getString("books.size()"));
		
		assertEquals(books.size(),10);
		
	}
}
