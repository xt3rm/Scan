package syo_model;

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
		host = "127.0.0.1";
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
	 * Creates the given database with all necessary tables.
	 * 
	 * @param name
	 */
	public void setupDB(String name) {
		closeDB(); // Close the connection this is for tests
		DBStringCreator creator = new DBStringCreator();
		db = name;
		String createDB = "CREATE DATABASE  IF NOT EXISTS " + name
				+ " DEFAULT CHARACTER SET utf8";
		try {
			connectForCreation();
			statement = connection.createStatement();
			statement.executeUpdate(createDB);
			closeDB(); // Close Initial connection.
			connectDB(); // Reopen it with a database selected.
			statement = connection.createStatement();
			// Erzeuge Haupttabellen
			statement.executeUpdate(creator.getTblSammlung());
			statement.executeUpdate(creator.getTblFeld());
			statement.executeUpdate(creator.getTblTyp());
			statement.executeUpdate(creator.getTblObjekt());
			statement.executeUpdate(creator.getTblEigenschaft());
			// Zwischentabellen erzeugen
			statement.executeUpdate(creator.getTblObjekt_Sammlung());
			statement.executeUpdate(creator.getTblTyp_Feld());

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * Deletes the given Database.
	 * 
	 * @param name
	 */
	public void dropDB(String name) {
		String drop = "DROP DATABASE IF EXISTS " + name;
		try {
			connectDB();
			statement = connection.createStatement();
			statement.execute(drop);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		closeDB();
	}

	/**
	 * Sets up a connection without giving the name of a database. This method
	 * can be used to connect to the server if no database has been created yet.
	 */
	private void connectForCreation() {
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
			connection = DriverManager.getConnection("jdbc:mysql://" + host
					+ "/?" + "user=" + user + "&password=" + password);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * Opens the connection to the database.
	 * 
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
	 * 
	 * @param tblName
	 * @return
	 */
	public int getRowCount(String tblName) {
		int count = 0;
		String countRows = "SELECT COUNT(*) FROM " + tblName;
		try {
			statement = connection.createStatement();
			if (statement.execute(countRows)) {
				rSet = statement.getResultSet();
			}
			while (rSet.next()) {
				count = rSet.getRow();
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return count;
	}

	/**
	 * Adds a Sammlung to the database.
	 * 
	 * @param name
	 *            The name of the Sammlung.
	 */
	public void addSammlung(String name) {
		String sammlung = "INSERT INTO Sammlung (ID_Sammlung, Sammlungname) VALUES (NULL,'"
				+ name + "')";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sammlung);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * Adds an object to the database.
	 * 
	 * @param name
	 *            The name of the object.
	 * @param typID
	 *            The ID of the type of the object
	 */
	public void addObject(String name, int typID, int sammlungID, String barcode) {
		String objekt = "INSERT INTO Objekt (ID_Objekt, ObjektName, Typ_ID, Barcode) VALUES (NULL,'"
				+ name + "', '" + typID + "','" + barcode +"')";
		int key = -1;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(objekt, Statement.RETURN_GENERATED_KEYS); // Der
																				// generierte
																				// Key
																				// soll
																				// bereitgestellt
																				// werden.
			rSet = statement.getGeneratedKeys();
			// Den neu erzeugten Primary Key in key speichern.
			while (rSet.next()) {
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
	}

	/**
	 * Adds a type to the database.
	 * 
	 * @param name
	 *            The name of the type.
	 * @param feldIDs
	 *            The list of the fields that belong to this type.
	 */
	public void addTyp(String name) {
		String typ = "INSERT INTO typ (ID_Typ, TypName) VALUES (NULL,'" + name
				+ "')";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(typ);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * Adds a field of the type varchar to the database.
	 * 
	 * @param name
	 *            The name of the field.
	 * @param length
	 *            int the length of the field.
	 */
	public void addStringFeld(String name, int typID) {
		String feld = "INSERT INTO feld (ID_Feld, FeldName) VALUES (NULL,'"
				+ name + "')";
		int key = -1;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(feld, Statement.RETURN_GENERATED_KEYS); // Der
																			// generierte
																			// Key
																			// soll
																			// bereitgestellt
																			// werden.
			rSet = statement.getGeneratedKeys();
			// Den neu erzeugten Primary Key in key speichern.
			while (rSet.next()) {
				key = rSet.getInt(1);
			}
			String objekt_sammlung = "INSERT INTO Typ_Feld (Typ_ID, Feld_ID) VALUES ("
					+ typID + ", " + key + ")";
			statement.executeUpdate(objekt_sammlung);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public void addEigenschaft(String wert, int objektID, int feldID) {
		String eigenschaft = "INSERT INTO eigenschaft (Objekt_ID, Feld_ID, Wert) VALUES ("
				+ objektID + "," + feldID + ",'" + wert + "')";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(eigenschaft);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public ResultSet selectAllFromTable(String tblName) {
		String select = "SELECT * FROM " + tblName;
		try {
			statement = connection.createStatement();
			statement.execute(select);
			rSet = statement.getResultSet();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rSet;
	}
}
