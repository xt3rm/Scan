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
	private String host;
	private String db;
	private String user;
	private String password;
	private static DBTool instance;

	/**
	 * Constructor sets up the necessary data for the connection.
	 */
	private DBTool() {
		host = "localhost";
		db = "syo";
		user = "root";
		password = "1234";
	}

	/**
	 * Returns the only instance of DBTool. Creates a DBTool-instance only if
	 * there isn't one already.
	 * 
	 * @return DBTool The only instance of DBTool.
	 */
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
	public void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		} catch (InstantiationException ex) {
			ex.printStackTrace();
			System.out.println("SQLException: " + ex.getMessage());
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
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
	public void closeDB() {
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
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Adds a Sammlung to the database.
	 * 
	 * @param name
	 *            The name of the Sammlung.
	 */
	public void addSammlung(String name) {
		String sammlung = "INSERT INTO Sammlung (Sammlung_ID, Sammlungname) VALUES (NULL,'"
				+ name + "')";
		connectDB();
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sammlung);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		closeDB();
	}

	/**
	 * Adds an object to the database.
	 * 
	 * @param name
	 *            The name of the object.
	 * @param typID
	 *            The ID of the type of the object
	 */
	public void addObject(String name, int typID, int sammlungID) {
		String objekt = "INSERT INTO Objekt (ID_Objekt, ObjektName, Typ_ID) VALUES (NULL,'"
				+ name + "', '" + typID + "')";
		int key = -1;
		connectDB();
		try {
			statement = connection.createStatement();
			statement.executeUpdate(objekt, Statement.RETURN_GENERATED_KEYS); // Der generierte Key soll bereitgestellt werden.
			rSet = statement.getGeneratedKeys();
			
			while (rSet.next()){
				key = rSet.getInt(1);
				}
			String objekt_sammlung = "INSERT INTO Objekt_Sammlung (Objekt_ID, Sammlung_ID) VALUES ("
					+ key + ", " + sammlungID + ")";
			statement.executeUpdate(objekt_sammlung);
			
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		closeDB();
	}

	/**
	 * Adds a type to the database.
	 * 
	 * @param name
	 *            The name of the type.
	 * @param feldIDs
	 *            The list of the fields that belong to this type.
	 */
	public void addType(String name, ArrayList<String> feldIDs) {
		// TODO Implement
	}

	/**
	 * Adds a field of the type Integer to the database.
	 * 
	 * @param name
	 *            The name of the field.
	 * @param length
	 *            int the length of the field.
	 */
	public void addIntegerField(String name, int length) {
		// TODO Implement
	}

	/**
	 * Adds a field of the type varchar to the database.
	 * 
	 * @param name
	 *            The name of the field.
	 * @param length
	 *            int the length of the field.
	 */
	public void addStringField(String name, int length) {
		// TODO Implement
	}
}
