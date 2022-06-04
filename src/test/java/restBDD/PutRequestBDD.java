package restBDD;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutRequestBDD
{

	@Test()
	 public void test1()
	 {
		 Map<String,Object> Mapobj=new HashMap<String,Object>();
			Mapobj.put("name", "tim");
			Mapobj.put("salary", "50000");
			
			RestAssured.given()
		    .baseUri("http://localhost:3000/employees")
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(Mapobj)
		    .when()
		    .put("/1")
		    .then()
		    .log()
		    .body()
		    .statusCode(200)
		    .body("name",Matchers.equalTo("tim"));
	 }
	}


