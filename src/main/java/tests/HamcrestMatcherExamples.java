package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent2;
import static io.restassured.RestAssured.given;

public class HamcrestMatcherExamples {

	
	@Test
	public void hamcrestMatchers() {
		
		Response resp =  given().
				get("https://swapi.dev/api/planets/1/").
				then().
				statusCode(200).extract().response();
		
		System.out.println(resp.asString());
		JsonPath jsonPath =  resp.jsonPath();
		
		assertThat(jsonPath.getString("name"), equalTo("Tatooine"));
		String name = jsonPath.getString("name");
		
		assertThat(name,is(equalTo("Tatooine")));
		assertThat(name,is("Tatooine"));
		
		assertThat(name, either(is("Tatooine")).or(is("Tatooine2")).or(is("Tatooine3")));

		
		assertThat(resp.asString(), is(instanceOf(String.class)));
		
		//not
		assertThat(name, is(not("Terra")));
		assertThat(name, is(not(equalTo("Terra"))));
		assertThat(name, is(not(instanceOf(Integer.class))));
		
		//start-with
		assertThat(resp.asString(), startsWith("{\"name"));
		assertThat(resp.asString(), startsWithIgnoringCase("{\"NAME"));
		
		//ends-with
		assertThat(resp.asString(), endsWith("planets/1/\"}"));
		assertThat(resp.asString(), endsWithIgnoringCase("pLaNeTs/1/\"}"));

		//ContainsString
		assertThat(jsonPath.getString("created"),containsString(":"));
		assertThat(name, containsStringIgnoringCase("tAtOo"));
		
		List<String> movies = jsonPath.getList("films");
		List<String> films = new ArrayList<>(movies);

		assertThat(films, contains("https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));	

		assertThat(films, contains(
				startsWith("https"),
		        endsWith("3/"),
		        equalTo("https://swapi.dev/api/films/4/"),
		        startsWith("https://swapi.dev"),
		        endsWith("api/films/6/")));	
	
	}
	

}