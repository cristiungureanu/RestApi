package tests;

import org.testng.annotations.Test;

public class TestUserDetails {

	@Test
	public void testCeva() {
		
		UserDetails ud =  new UserDetails();
		ud.setCity("craiova");
		System.out.println(ud.getCity());
	}
}
