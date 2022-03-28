package comcast_rmgYantra_framework_genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

import groovyjarjarantlr4.v4.parse.ANTLRParser.element_return;

/**
 * This class is created for database connectivity and for common method
 * 
 * @author Surajkumar
 *
 */
public class DataBaseUtility {
	Connection connect = null;

	/**
	 * This method will connect the database connection
	 */
	public void connectToDB() {
		try {
			Driver dbDriver = new Driver();
			DriverManager.registerDriver(dbDriver);
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method will disconnect the database connection
	 * 
	 * @throws SQLException
	 */
	public void disConnectToDB() throws SQLException {
		connect.close();
	}

	/**
	 * This methods returns all the data from database
	 * 
	 * @param query
	 * @return
	 */
	public ResultSet getAllData(String query) {
		ResultSet result = null;
		try {
			result = connect.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * This method will validate the data and return the data
	 * 
	 * @param query
	 * @param expectedData
	 * @param columnind
	 * @return
	 * @throws SQLException
	 */
	public String getTheDataFromDatabaseAndVerify(String query, String expectedData, int columnind)
			throws SQLException {
		ResultSet result = connect.createStatement().executeQuery(query);

		String actualData = null;
		while (result.next()) {
			System.out.println(result.getString(4));
			if (result.getString(columnind).equals(expectedData)) {
				actualData = result.getString(columnind);
			}
		}
		return actualData;

	}

	public String getTheDataAndVerifyData(String query, int columnNumber, String expectedData) throws SQLException {
		ResultSet result = connect.createStatement().executeQuery(query);
		boolean flag = false;
		String actualData = null;
		while (result.next()) {
			try {
				if (result.getString(columnNumber).equals(expectedData)) {
					System.out.println(result.getString(columnNumber));
					flag = true;
					actualData = result.getString(columnNumber);
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (flag) {
			System.out.println("Data is verified and present");
			return actualData;
		} else {
			System.out.println("Data is not present");
			return actualData;
		}
	}

}
