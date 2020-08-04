package stepDefinations;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
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

@RunWith(Cucumber.class)

public class stepDefinations {
    @Given("^User is on Rest API console$")
    public void user_is_on_rest_api_console() throws Throwable {
    	System.out.println("User is on Rest API console");
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

    @When("^User login to the Rest API console with creds$")
    public void user_login_to_the_rest_api_console_with_creds() throws Throwable {
    	System.out.println("User login to the Rest API console with creds");
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

    @Then("^All the users are listed$")
    public void all_the_users_are_listed() throws Throwable {
    	System.out.println("All the users are listed");

    }

    @And("^All the users are listed with name, salary & age$")
    public void all_the_users_are_listed_with_name_salary_age() throws Throwable {
    	System.out.println("All the users are listed with name, salary & age");

    }
}


