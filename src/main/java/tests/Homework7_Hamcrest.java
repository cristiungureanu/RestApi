package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static utils.NumberChecker.*;
import static utils.NumberIsPositive.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

/**
 * Folosind Hamcrest si RestApi : https://swapi.dev/api/people/1/

Vom verifica:

	Numele este Luke Skywalker
	Height este mai mare de 171
	Mass este mai mic decat 80
	Intr-un singur assert vom verifica daca valorile atributelor skin, eye si hari sunt fair, blue si blond
	Birth-year nu este o valoare doar numerica
	Daca species este o colectie goala a clasei String
	Daca starship si vehicles au acealsi size
	Daca starship si vehicles nu sunt la fel
 * 
 * @author cristinel.ungureanu
 *
 */

public class Homework7_Hamcrest {

	@Test
	public void test() {
		Response resp=given().
				get("https://swapi.dev/api/people/1/").
				then().
				statusCode(200).extract().response();
		
		System.out.println(resp.asPrettyString());
		
		JsonPath jsonPath=resp.jsonPath();
		
		assertThat(jsonPath.getString("name"), equalTo("Luke Skywalker"));
		
		
		assertThat(jsonPath.getInt("height"), greaterThan(171));
		assertThat(jsonPath.getInt("mass"), lessThan(80));
		
       List<String> attributes= new ArrayList<>(); 
       
       attributes.add(jsonPath.getString("skin_color"));
       attributes.add(jsonPath.getString("eye_color"));
       attributes.add(jsonPath.getString("hair_color"));
		
	   assertThat(attributes,contains(equalTo("fair"),equalTo("blue"),equalTo("blond")));
	   assertThat(jsonPath.getString("birth_year"),is(not(numberOnly())));
		
	   List<String> species= jsonPath.getList("species");
	   assertThat(species, is(empty()));
	   
	   List<String> starships= jsonPath.getList("starships");
	   System.out.println(starships);
	   
	   List<String> vehicles= jsonPath.getList("vehicles");   
	   System.out.println(vehicles);
	   
	   assertThat(starships, hasSize(starships.size()));
	   
	   assertThat(starships.toString(),not (equalTo(vehicles.toString())));
	   
	}
	
}
