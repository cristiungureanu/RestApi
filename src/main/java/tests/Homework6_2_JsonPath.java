package tests;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent6;
import utils.DataBuilder6;

/**
 * Folosind API de POST , GET si DELETE de la : https://keytodorestapi.herokuapp.com/
 
 Vom face 3 teste:
1. Primul test face POST todo si verifica daca raspunsul contine “Todo saved! Nice job!”
Body-ul acestui request este constuit in clasa DataBuilder. In clasa asta cand
construim JsonObject ul il si salvam intr-un fisier care se chema todo.json (PS gasiti un
exemplu in cursul 11 de la java J. Daca nu va iese, dati un semn J)

2. Al doilea test face un GET all .Citeste din fisierul Json titlul scris la post cu JsonPath
File jsonFile = new File("todo.json");
JsonPath jsonPath = JsonPath.from(jsonFile);

Mai departe folositi sintaxa lui jsonPath pt a scoate titlul
Folosind acel titlu citit din fisier cauta in raspunsul callului de GET tot cu jsonPath si citeste id-ul acelui todo.

3. Testul 3 va folosi id-ul de la testul doi , pentru a face un call de DELETE si a sterge acel todo.
Verifica la final mesajul. “Event deleted.”
 * 
 * @author cristinel.ungureanu
 *
 */

public class Homework6_2_JsonPath extends BaseComponent6{

	public String id;
	public String info;
	public String getId;
	
	@Test (priority=1)
	public void postUser() {
			
		Response result=doPostRequest("api/save",DataBuilder6.buildUser().toJSONString(),200);
		System.out.println(result.asPrettyString());
		
		info=result.jsonPath().getString("info");
		System.out.println(info);
		id=result.jsonPath().getString("id");
		System.out.println(id);
		
		assertEquals(info,"Todo saved! Nice job!");
	}
	
	@Test(priority=2)
	public void getUser() {
		
		File jsonFile = new File("todo.json");
		JsonPath jsonPath = JsonPath.from(jsonFile);
		
		String title=jsonPath.getString("title");
		System.out.println(title);
		
		Response result1=BaseComponent6.doGetAllRequest("api",200);

	  getId= result1.jsonPath().getString("find{it.title == '"+ title + "'}._id");
	  System.out.println(getId);
		
	  assertEquals(id, getId);
		
	}
	
	@Test(priority=3)
	public void deleteUser() {
		
		Response result=BaseComponent6.doDeleteRequest("api/delete/",getId,200);
		
		info=result.jsonPath().getString("msg");
		System.out.println(info);
		
		assertEquals(info,"Event deleted.");
		
	}
	
}
