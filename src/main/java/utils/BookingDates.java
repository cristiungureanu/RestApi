package utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingDates {
/**
 * "bookingdates": {
 *  "checkin": "2-18-01-01",
 *  "checkout": "2019-01-01"
 * },
 */
	
	
	public String checkin;
	public String checkout;
	
	public String toString() {
		
		return "BookingDates{"
				+ "checkin='"+ checkin +"'"
			    +", checkout='" +checkout+"'"
			    +"}";
	}
}
