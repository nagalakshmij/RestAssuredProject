package restBDD;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestBDD 
{
	@Test
	public void test1()
	{
		RestAssured.given()
				    .baseUri("http://localhost:3000/employees")
				    .when()
				    .get("/1")
				    .then()
				    .log()
				    .body()
				    .statusCode(200);
	 
				   
		
	}

	@Test
	public void test2()
	{
		RestAssured.given()
				    .baseUri("http://localhost:3000/employees")
				    //.queryParam("id", 1)
				    //.queryParam("name", "pankaj")
				    .when()
				    .get()
				    .then()
				    .log()
				    .body()
				    .statusCode(200)
				    .body("[0].name",Matchers.equalTo("Pankaj"));
	 
				   
		
	}
	
	
	@Test
	public void test3()
	{
		Response response=RestAssured.given()
				    .baseUri("http://localhost:3000/employees")
				    //.queryParam("id", 1)
				    //.queryParam("name", "pankaj")
				    .when()
				    .get();
				    
		JsonPath Jpath =response.jsonPath();
		Jpath.get("name");
		
		List<String> names=Jpath.get("name");
		
		/*for(String str: names)
		{
			System.out.println("names"+str);
		}*/
		
		//System.out.println(names.get(0));
		
		//Assert.assertEquals(names.get(0),"David");
		
		String Header=response.getHeader("Content-Type");
		System.out.println(Header);
				   
		
	}
}
	