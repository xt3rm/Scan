package syo_model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 * 
 * @author kuepfers
 */
public class DBTool {

	private Connection connection;
	private Statement statement = null;
	private ResultSet rSet = null;
	private static DBTool instance;

	private DBTool() {
	}

	public static DBTool getInstance() {
		if (instance == null) {
			instance = new DBTool();
		}
		return instance;
	}

	/**
	 * Opens the connection to the database.
	 * 
	 * @param host
	 * @param db
	 * @param user
	 * @param password
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void connectDB(String host, String db, String user, String password)
			throws InstantiationException, IllegalAccessException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}

		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://" + host + "/" + db + "?"
							+ "user=" + user + "&password=" + password);

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * Closes the connection. Checks if there is an existing connection before
	 * attempting to close it.
	 */
	public void closeDBconnection() {
		if (rSet != null) {
			try {
				rSet.close();
			} catch (SQLException sqlEx) {
			}
			rSet = null;
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException sqlEx) {
			}
			statement = null;
		}
	}
	
	/**
	 * Adds a Sammlung to the database.
	 * @param name The name of the Sammlung.
	 */
	public void addSammlung(String name) {
		
	}

	/**
	 * Adds an object to the database.
	 * 
	 * @param name The name of the object.
	 * @param typID The ID of the type of the object
	 */
	public void addObject(String name, String typID) {

	}

	/**
	 * Adds a type to the database.
	 * 
	 * @param name The name of the type.
	 * @param feldIDs The list of the fields that belong to this type.
	 */
	public void addType(String name, ArrayList<String> feldIDs) {

	}
	
	/**
	 * Adds a field of the type Integer to the database.
	 * @param name The name of the field.
	 * @param length int the length of the field.
	 */
	public void addIntegerField(String name, int length) {
		
	}
	
	/**
	 * Adds a field of the type varchar to the database.
	 * @param name The name of the field.
	 * @param length int the length of the field.
	 */
	public void addStringField(String name, int length) {
		
	}
}
