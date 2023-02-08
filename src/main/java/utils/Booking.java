package utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Booking {
	
	public String firstname;
	public String lastname;
	public int totalprice;
	public boolean depositpaid;
	public BookingDates bookingdates;
	
	public String additionalneeds;
	
	public String toString() {
		return "Booking {"
				+ "firstname='"+firstname+"'" +
				",lastname='"+lastname+"'" +
				",totalprice='"+totalprice+"'"+
				",depositpaid='"+depositpaid+"'"+
				",bookingdates='"+bookingdates+"'"+
				",additionalneeds='"+additionalneeds+"'"+
				"}";
	}

}
