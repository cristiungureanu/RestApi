package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.Booking;
import utils.BookingDates;
import utils.BookingId;

import static io.restassured.RestAssured.given;

public class TestRequest {

	@Test
	public void createBooking() {
		
		BookingDates bookingDates = new BookingDates("2023-01-01", "2023-04-04");
		Booking booking = new Booking("Test", "Ceva", 250, false, bookingDates, "Breakast");
		
		Response response = given().
				header("Content-Type", "application/json").
				header("Accept", "application/json").
				body(booking).
				when().
					post("https://restful-booker.herokuapp.com/booking").
				then().
					statusCode(200)
					.extract().response();
		
		System.out.println(response.asPrettyString());
		
		BookingId bookingid = response.as(BookingId.class);
		System.out.println("------------------");
		System.out.println(bookingid.toString());
		assertEquals(booking.toString(), bookingid.getBooking().toString());
	}
}
