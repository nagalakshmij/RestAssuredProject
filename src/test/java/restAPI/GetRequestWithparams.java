package restAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithparams {

	@Test
	public void test1()
	{
		RestAssured.baseURI = "http://localhost:3000/employees";
		RequestSpecification request =RestAssured.given();
		//Response response=request.param("id","2").get();
		Response response=request.get();
		String responsebody=response.getBody().asString();
		System.out.println(responsebody);
		int responsecode=response.getStatusCode();
		System.out.println(response.getStatusCode());
		//Assert.assertEquals(responsecode, 200);
		//Assert.assertTrue(responsebody.contains("David"));
		
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
