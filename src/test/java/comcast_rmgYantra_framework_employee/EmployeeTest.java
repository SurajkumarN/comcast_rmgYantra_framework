package comcast_rmgYantra_framework_employee;

import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import comcast_rmgYantra_framework_genericUtility.BaseApiClass;
import comcast_rmgYantra_framework_genericUtility.DataBaseUtility;
import comcast_rmgYantra_framework_genericUtility.EndPoints;
import comcast_rmgYantra_framework_genericUtility.JavaUtility;
import comcast_rmgYantra_framework_genericUtility.PojoClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class EmployeeTest extends BaseApiClass{
	JavaUtility jutil = new JavaUtility();
	DataBaseUtility dbutil = new DataBaseUtility();
	
	@Test
	public void create() throws SQLException {
		
		int random = jutil.getRandonNumber();
		String projName = "amazon"+random;
		System.out.println("Actual Project Name "+projName);
		 PojoClass pobj = new PojoClass("Suraj", projName, "completed", "55");
		Response response = given()
		.body(pobj)
		.contentType(ContentType.JSON)
		.when()
		.post(EndPoints.ADDPROJECT);
		String responseProjectName = response.jsonPath().get("projectName");
		System.out.println("Response Project Name "+responseProjectName);
		response.then()
		.assertThat()
		.contentType(ContentType.JSON)
		//.statusCode(201)
		.log().all();
		
		
		String query = "select * from project";
		String expectedProjName = dbutil.getTheDataFromDatabaseAndVerify(query, responseProjectName, 4);
		//String expectedProjName = dbutil.getTheDataAndVerifyData(query,4, responseProjectName);
		System.out.println("Expected ProjName "+expectedProjName);
		Assert.assertEquals(expectedProjName, projName);
		
	}

}
