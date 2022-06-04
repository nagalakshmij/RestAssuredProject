package restAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest
{
	@Test
	public void test1()
	{
		RestAssured.baseURI = "http://localhost:3000/employees";

		RequestSpecification request =RestAssured.given();

		Response  resp=request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body("{\r\n"
						+ "        \"name\": \"nagalakshmi\",\r\n"
						+ "        \"salary\": \"5000\"\r\n"
						+ "        \r\n"
						+ "    }")
				.post("/create");

		String responsebody=resp.getBody().asString();
		System.out.println(responsebody);
		
		
		JsonPath Jpath =resp.jsonPath();
		Jpath.get("id");
		System.out.println("id"+Jpath.get("id"));

		int  responsecode=resp.getStatusCode();
		Assert.assertEquals(responsecode, 201);



	}
}
