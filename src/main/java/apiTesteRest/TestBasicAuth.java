package apiTesteRest;

import static io.restassured.RestAssured.given;
import org.junit.Test;

public class TestBasicAuth {

		@Test
		public void testBasicAuth() {
		String uriBase = "https://postman-echo.com/basic-auth";
		given().auth()
		.basic("postman", "password")
		.when()
			.get(uriBase)
		.then()
		.assertThat()
		.statusCode(200);
		
		//.body("200", equals("200"));
		}
		
       
		@Test
		public void testBasicAuthNegative() {
		String uriBase = "https://postman-echo.com/basic-auth";
		given().auth()
		.basic("post", "password")
		.when()
			.get(uriBase)
		.then()
		.assertThat()
		.statusCode(401);
		//.statusCode(HttpStatus.OK.value());
		}

	}

