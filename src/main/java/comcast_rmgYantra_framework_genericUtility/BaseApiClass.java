package comcast_rmgYantra_framework_genericUtility;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.*;

import java.sql.SQLException;

/**
 * This class is created for provides basic configuration to all test scripts
 * 
 * @author HP
 *
 */
public class BaseApiClass {
	DataBaseUtility dbutil = new DataBaseUtility();

	@BeforeSuite
	public void configBS() {
		dbutil.connectToDB();
		baseURI="http://localhost";
		port=8084;
		Reporter.log("DataBase linked", true);
	}

	@AfterSuite
	public void configAS() throws SQLException {
		dbutil.disConnectToDB();
		Reporter.log("DataBAse dilinked", true);
	}

}
