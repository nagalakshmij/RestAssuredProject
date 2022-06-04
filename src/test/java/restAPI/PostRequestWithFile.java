package restAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithFile 
{

	@Test
	public void test() throws IOException
	{
		RestAssured.baseURI = "http://localhost:3000/employees";
		byte[] databytes1=Files.readAllBytes((Paths.get("data.json")));

		RequestSpecification request =RestAssured.given();

		Map<String,Object> Mapobj=new HashMap<String,Object>();
		Mapobj.put("name", "alekhya");
		Mapobj.put("salary", 50000);


		Response  resp=request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(databytes1)
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

