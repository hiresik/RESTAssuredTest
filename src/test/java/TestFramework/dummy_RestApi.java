package TestFramework;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.ExcelUtils;
import files.ReUsableMethods;
import files.WriteExcelFile;
import files.payload;

public class dummy_RestApi {
	DateFormat df = new SimpleDateFormat("ddMMyyyy HH:mm:ss");
	Date dateobj = new Date();
	String[] al;
	
@Test (priority=1)
public void get_all_emp() {

		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
	
		RestAssured.baseURI= "http://dummy.restapiexample.com/";

		
	String getPlaceResponse=	given().log().all()
		.when().get("api/v1/employees")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	JsonPath js=ReUsableMethods.rawToJson(getPlaceResponse);
	String employee_count =js.getString("data.size()");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@ get_all_emp() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("employee_count: "+employee_count);
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("");
	}

@Test (priority=2)
public void get_emp() {

		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
	
		RestAssured.baseURI= "http://dummy.restapiexample.com/";

	String getPlaceResponse=	given().log().all().pathParam("id", "6")
		.when().get("api/v1/employee/{id}")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	JsonPath js=ReUsableMethods.rawToJson(getPlaceResponse);
	String message =js.getString("message");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ get_emp() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println(message);
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("");
	}

@Test (priority=3)
public void create_emp() {

		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
	
		RestAssured.baseURI= "http://dummy.restapiexample.com/";

	String getPlaceResponse=given().log().all().header("Content-Type","application/json")
			.body(payload.create_emp_p())
		.when().post("api/v1/create")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	JsonPath js=ReUsableMethods.rawToJson(getPlaceResponse);
	String message =js.getString("message");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@ create_emp() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println(message);
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("");
	
	}
@Test (priority=4)
public void upd_emp() {

		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
	
		RestAssured.baseURI= "http://dummy.restapiexample.com/";

	String getPlaceResponse=given().log().all().header("Content-Type","application/json")
			.body(payload.upd_emp_p()).pathParam("id", "7")
		.when().put("api/v1/update/{id}")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	JsonPath js=ReUsableMethods.rawToJson(getPlaceResponse);
	String message =js.getString("message");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@ upd_emp() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println(message);
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("");
	
	}


@Test (priority=5)
public void del_emp() {

		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
	
		RestAssured.baseURI= "http://dummy.restapiexample.com/";

	String getPlaceResponse=	given().log().all().pathParam("id", "6")
		.when().delete("api/v1/delete/{id}")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	JsonPath js=ReUsableMethods.rawToJson(getPlaceResponse);
	String message =js.getString("message");
	System.out.println("@@@@@@@@@@@@@@@@@@@@@ del_emp() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println(message);
	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	System.out.println("");
	}

@Test(dataProvider="entry",priority=6)
public void create_emp_xl(String name,String sal,String age) throws  Exception 
{

	RestAssured.baseURI= "http://dummy.restapiexample.com/";

String getPlaceResponse=given().log().all().header("Content-Type","application/json")
		.body("{\\\\\\\"name\\\\\\\":\\\\\\\""+name+"\\\\\\\",\\\\\\\"salary\\\\\\\":\\\\\\\""+sal+"\\\\\\\",\\\\\\\"age\\\\\\\":\\\\\\\""+age+"\\\\\\\"}")
	.when().post("api/v1/create")
	.then().assertThat().log().all().statusCode(200).extract().response().asString();
JsonPath js=ReUsableMethods.rawToJson(getPlaceResponse);
String message =js.getString("message");
String id =js.getString("data.id");
System.out.println("@@@@@@@@@@@@@@@@@@@@@ create_emp_xl @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
System.out.println(message);
System.out.println(id);
System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
System.out.println("");
System.out.println(df.format(dateobj));
String emp =(name+" "+sal+" "+age+" "+id+" "+df.format(dateobj));

al=( new String[]{ name, sal, age, id,df.format(dateobj)} );
WriteExcelFile.writeExcel("D:\\SeleniumCode\\RESTAssuredTest\\src\\test\\java\\TestFramework","output.xlsx","Sheet1",al);

//int s=al.length;
//System.out.println(s);

}

@Test(priority=7)
public void xl() throws IOException  
{

//int s=al.length;
//System.out.println(s);
//for(int i=0;i<s;i++)
//{
//System.out.println(al[i]);
//}
//WriteExcelFile.writeExcel("D:\\SeleniumCode\\RESTAssuredTest\\src\\test\\java\\TestFramework","output.xlsx","Sheet1",al);

}

 @org.testng.annotations.DataProvider
 
    public Object[][] entry() throws Exception{
 
       Object[][] testObjArray = ExcelUtils.getTableArray("D:\\SeleniumCode\\RESTAssuredTest\\src\\test\\java\\TestFramework\\demodata.xlsx","RestAssured");
  return (testObjArray);
 
		}

}
