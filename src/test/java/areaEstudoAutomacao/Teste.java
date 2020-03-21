package areaEstudoAutomacao;


import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.junit.Test;

public class Teste {
	
	//private JUnitReportServiceExample junitAssertEqualsServiceSample;
    //private ServiceObject serviceObject;
    
		@Test
		public void testAPI() {
		String uriBase = "https://postman-echo.com/get";
		given()
			.relaxedHTTPSValidation()
			.param("foo1", "bar1")
			.param("foo2", "bar2")
		.when()
			.get(uriBase)
		.then()
			.statusCode(200) // O status http retornado foi 200
			.contentType(ContentType.JSON) // O response foi retornado no formato JSON
			.body("headers.host", is("postman-echo.com")) // A chave �host� possui exatamente o valor �postman-echo.com�
			.body("args.foo1", containsString("bar1"))
			.body("args.foo2", containsString("bar2"));//A chave �foo1� cont�m o valor �bar�
		
				//String accessToken = JSONObject.getNames("postman-token").toString();
				//System.out.println(accessToken);
		
		
		}

	}

