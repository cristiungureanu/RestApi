package utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingId {

	public int bookingid;
	public Booking booking;
	
	public String toString() {
		return "BookingId{"+
				"bookingid="+bookingid+
				", booking="+booking+
				"}";
	}

}
