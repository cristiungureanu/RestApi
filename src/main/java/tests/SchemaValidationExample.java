package tests;

import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;;

public class SchemaValidationExample {

	@Test
	public void validateSchema() {
		
		Response result = given()
				.get("https://keytrcrud.herokuapp.com/api/users/63c806172708c80016073e0a")
				.then()
				.statusCode(200)
				.log().all()
				.extract().response();
		
		System.out.println(result.asString());
		
		assertThat(result.asString(), matchesJsonSchemaInClasspath("schema.json"));
		
	}
	
	
}
