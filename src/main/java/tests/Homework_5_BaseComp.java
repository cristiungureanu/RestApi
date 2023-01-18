package tests;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import utils.BaseComponent3;

/**
 * Folosind modelul creat astazi la curs in clasa BaseComponents, vom face inca 1 o metoda in aceasi clasa pentru PUT
- Folosing BASE URL https://api.instantwebtools.net
- Vom face 5 teste:

Testul 1. 
Folosind path-ul v1/passenger/ cu body (JSONObject) :
{
"name": <random nume>, // nume generat cu Faker
"trips": 250 //fix valoarea aceasta
"airline” : 1981 // fix valoarea aceasta
}
- Vom crea un pasager nou
- Pe assert verificam daca numele din raspuns este acelasi cu numele generat de Faker .
 - Salvam Id-ul intr-o variabila pentru requesturile urmatoare

Testul 2:
- Folosind GET pe pathul v1/passenger/<id> , citim pasagerul creat si pe assert verificam daca numele airline –ului este Tarom

Testul 3:
- Folosind PUT Folosind GET pe pathul v1/passenger/<id> , cream un nou JSONObject pentru body, si updatam trips la 300 si airline cu valoarea 1
- Pe assert verificam ca ne apare textul : Passenger data put successfully completed
 * 
Testul 4:
- Folosind GET pe pathul v1/passenger/<id> , citim pasagerul updatat si pe assert verificam daca numele airline –ului este Quatar Airways, daca trips sunt 300 si daca
numele a ramas acelasi

Testul 5:
- Folosind DELETE pe pathul v1/passenger/<id> , stergem pasagerul si pe assert verificam mesajul Passenger data deleted successfully.

PS: ca sa aflati usor formatul raspunsului folositi Postman 
 * @author cristinel.ungureanu
 *
 */



public class Homework_5_BaseComp extends BaseComponent3{

	
	String id, id_updated, passengerName, name;
	
	JSONObject body = new JSONObject();
	
	@Test(priority=0)
	public void postPassenger() {
		
		
		Faker faker = new Faker(); //generare dinamica sa punem date in respectivul obiect
		
		name = faker.name().firstName();
		body.put("name", name);
		body.put("trips", "250");
		body.put("airline", "1981");
		
		Response response = doPostRequest("v1/passenger/", body.toJSONString(), HttpStatus.SC_OK);
		assertEquals(response.jsonPath().getString("name"), name);
		System.out.println(response.jsonPath().getString("name"));
		System.out.println(response.asPrettyString());
		
		
		id=response.jsonPath().getString("_id");
		System.out.println(id);
	}
	
	//@Test(priority = 1)
	public void getPassenger() {
		
		Response response = doGetRequest("v1/passenger/", id, 200);
		assertEquals(response.jsonPath().get("airline.name[0]"), "Tarom");   
		
		System.out.println(response.asPrettyString());
	}
	
	@Test(priority = 2)
	public void putPassenger() {
		
		body.put("trips", "300");
		body.put("airline", "1");
		System.out.println(body.toJSONString());
		System.out.println("-------------------");
		
		Response response = doPutRequest("v1/passenger/"+id, body.toJSONString(), 200);
		assertEquals(response.jsonPath().get("message"), "Passenger data put successfully completed.");  
		System.out.println(response.asPrettyString());
		
	}
	
	@Test(priority = 3)
		public void getUpdatedPassenger() {
			
			Response response = doGetRequest("v1/passenger/", id, 200);
			assertEquals(response.jsonPath().get("airline.name[0]"), "Quatar Airways");    
			assertEquals(response.jsonPath().get("trips"), 300);
			assertEquals(response.jsonPath().get("name"), name);
			
			System.out.println(response.asPrettyString());
		}
	
	
	@Test(priority = 4)
	public void deletePassenger() {
		
		Response response = doDeleteRequest("v1/passenger/", id, 200);
		assertEquals(response.jsonPath().getString("message"), "Passenger data deleted successfully.");
	} 
}
