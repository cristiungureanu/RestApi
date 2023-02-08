package utils;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

//este facuta sa construiasca date de test

public class DataBuilder {

	public static JSONObject buildUser() {
		
		JSONObject bodyBuilder = new JSONObject();
		Faker faker = new Faker(); //generare dinamica sa punem date in respectivul obiect
		
		String email = faker.internet().emailAddress();
		bodyBuilder.put("name", faker.name().firstName());
		bodyBuilder.put("email", email);
		bodyBuilder.put("age", faker.number().numberBetween(5, 130));
		bodyBuilder.put("gender", "m");
		
		return bodyBuilder;
	}
	
	public static JSONObject buildTodo() {
		
		JSONObject todoBuilder = new JSONObject();
		Faker faker = new Faker();
		todoBuilder.put("title", faker.lordOfTheRings().character());
		todoBuilder.put("body", faker.dune().saying());
		
		return todoBuilder;
		
	}
	
	public static JSONObject buildToken() {
		JSONObject body = new JSONObject();
		body.put("username", "admin");
		body.put("password", "password123");
		
		return body;
	}
	
	public static JSONObject buildBooking() {
		
		JSONObject booking = new JSONObject();
		booking.put("firstname" , "John");
		booking.put("lastname" ,"Doe");
		booking.put("totalprice" , 111);
		booking.put("depositpaid" , true);
			JSONObject bookingDates = new JSONObject();
			bookingDates.put("checkin" , "2023-01-01");
			bookingDates.put("checkout" , "2023-01-11");
		booking.put("bookingdates" , bookingDates);
		booking.put("additionalneeds" , "Breakfast");
		
		
		return booking;
		
	}
}
