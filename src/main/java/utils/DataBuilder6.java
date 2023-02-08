package utils;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

//este facuta sa construiasca date de test

public class DataBuilder6 {

	public static JSONObject buildUser() {
		
		JSONObject bodyBuilder = new JSONObject();
		Faker faker = new Faker(); //generare dinamica sa punem date in respectivul obiect
		
		
		bodyBuilder.put("body", faker.chuckNorris().fact());
		bodyBuilder.put("title", faker.name().firstName());
		
		try(FileWriter file= new FileWriter("todo.json")) {
			
			   file.write(bodyBuilder.toJSONString());
			   
		}catch (IOException e) {
			
			   e.printStackTrace();
		   }
		
		return bodyBuilder;
	}
	
	
}
