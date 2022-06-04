package apichaining;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest 
{
	
	Response response;
	String BaseURI= "http://localhost:3000/employees";
	
	@Test
	public void test1()
	{
		// to fetch all the employee details
		response= GetMethodAll();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		// to update the data
		response=PostMethod("sripad", "1000");
		JsonPath Jpath =response.jsonPath();
		int EmpId=Jpath.get("id");
		System.out.println("id"+ EmpId);


		Assert.assertEquals(response.getStatusCode(), 201);
		
		response=PutMethod(EmpId,"rick","1000");
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Jpath=response.jsonPath();
		Assert.assertEquals(Jpath.get("name"), "rick");
		
		response=Delete(EmpId);
		Assert.assertEquals(response.getStatusCode(), 200);
		/*String ResponseBody = response.getBody().asString();
        Assert.assertEquals(ResponseBody, "{}"); */
		Assert.assertEquals(response.getBody().asString(), "{}");
		
		response=GetMethod(EmpId);
		Assert.assertEquals(response.getBody().asString(), "{}");
		
         
	}
	
	public Response GetMethodAll()
	{
		RestAssured.baseURI = BaseURI;
		RequestSpecification request =RestAssured.given();
		Response response=request.get();
		
		// printing
		
		String responsebody=response.getBody().asString();
		System.out.println(responsebody);
		return response;
	}
	
	public Response GetMethod(int empid)
	{
		RestAssured.baseURI = BaseURI;
		RequestSpecification request =RestAssured.given();
		Response response=request.get("/"+empid);
		
		// printing
		
		String responsebody=response.getBody().asString();
		System.out.println(responsebody);
		return response;
	}
	
	public Response PostMethod(String name, String salary)
	{
		RestAssured.baseURI = BaseURI;

		RequestSpecification request =RestAssured.given();
		
		JSONObject jobj=new JSONObject();
		jobj.put("name", name);
		jobj.put("salary", salary);
				

		Response  resp=request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(jobj.toString())
				.post("/create");
		
		return resp;

	}
	
	
	public Response PutMethod(int Empid,String name, String salary)
	{
		RestAssured.baseURI = BaseURI;

		RequestSpecification request =RestAssured.given();
		
		JSONObject jobj=new JSONObject();
		jobj.put("name", name);
		jobj.put("salary", salary);
				

		Response  resp=request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(jobj.toString())
				.put("/"+Empid);
		
		return resp;

	}
	
	public Response Delete(int Empid)
	{
	RestAssured.baseURI = BaseURI;
	
	RequestSpecification request =RestAssured.given();
	
	Response response=request.delete("/"+Empid);
	return response;
	
	/*String responsebody=response.getBody().asString();
	
	System.out.println(responsebody);
	
	int responsecode=response.getStatusCode();
	
	System.out.println(response.getStatusCode());
	
	Assert.assertEquals(responsecode, 200);*/
}
}

