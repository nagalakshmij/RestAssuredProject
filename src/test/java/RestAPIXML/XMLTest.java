package RestAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest 
{
	@Test
	public void test1()
	{
		RestAssured.given()
				    .baseUri("https://chercher.tech/sample/api/books.xml")
				    .when()
				    .get()
				    .then()
				    .log()
				    .body()
				    .statusCode(200);
	 
				   
		
	}
	
	@Test
	public void test2()
	{
		Response response =RestAssured.given()
				    .baseUri("https://chercher.tech/sample/api/books.xml")
				    .when()
				    .get();
		NodeChildrenImpl books=response.then().extract().path("bookstore.book.title");
		System.out.println("All the books are"+ books.toString());
		System.out.println("First book is "+books.get(0).toString());
		System.out.println("second book is "+books.get(1).toString());
		
				    
				   
		
	}
	
	
// to get the langauge using attribute
	@Test
	public void test3()
	{
		Response response =RestAssured.given()
				    .baseUri("https://chercher.tech/sample/api/books.xml")
				    .when()
				    .get();
		NodeChildrenImpl books=response.then().extract().path("bookstore.book.title");
		System.out.println("All the books are"+ books.toString());
		System.out.println("First book is "+books.get(0).toString());
		System.out.println("second book is "+books.get(1).toString());
		
		System.out.println("second book is "+books.get(0).getAttribute("lang").toString());
		
		
		for (int i=0; i<books.size();i++)
		{
			System.out.println(books.get(i).getAttribute("lang").toString());
		}
			   
		
		NodeChildrenImpl prices=response.then().extract().path("bookstore.book.price");
		System.out.println("All the prices are"+ prices.toString());
		
		
		System.out.println("First price paper back  is "+prices.get(0).children().get("paperback"));
		
		
				   
		
	}
}
