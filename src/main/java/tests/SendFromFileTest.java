package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent2;

public class SendFromFileTest extends BaseComponent2{

	//@Test
	public void testJsonFile() throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("data.json"));
		JSONArray todoList = (JSONArray) obj;

		
		for(Object totdo : todoList) {
			JSONObject objTodo = (JSONObject)totdo;
			Response resp = doPostRequest(objTodo.toJSONString());
			System.out.println(resp.asString());
		}	
	}
	@Test
	public void parseJson() throws IOException, ParseException {
		
		//1.parser de json
		JSONParser parser =  new JSONParser();
		//2. incarcam fisierul json
		FileReader reader = new FileReader("data2.json");
		//3 parsam fisierul
		Object obj = parser.parse(reader);
		//4 punem continutul in JSonArray
		JSONArray employeeList = (JSONArray) obj;
		System.out.println(employeeList);
		//5.luam un obiect Json idividual din JsonArray
		JSONObject employeeObject = (JSONObject) employeeList.get(0);
		System.out.println(employeeObject);
		
		JSONObject employeeAtribute = (JSONObject) employeeObject.get("employee");
		System.out.println(employeeAtribute.get("company"));
		
		System.out.println("---------------------------------------");
		File jsonFile = new File("data2.json");
		JsonPath jsonPath = JsonPath.from(jsonFile);
		
		System.out.println(jsonPath.getString("[0]"));
		System.out.println(jsonPath.getString("[0].employee.company"));
		
		
	}
	
	
}