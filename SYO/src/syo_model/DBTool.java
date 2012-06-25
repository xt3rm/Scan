package syo_model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

/**
 * This class provides the basic functionality to setup and access the database.
 * The Singleton pattern is used to prevent multiple instances.
 * 
 * @author ebrogt
 */
public class DBTool extends Observable {

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
			statement.execute(creator.getViewAllObjInfo());
			// close DB
			closeDB();
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
	 * Returns the number of rows in a table.
	 * 
	 * @param tblName
	 * @return int count
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
			propagateChange();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * 
	 * @param name
	 * @param typID
	 * @param sammlungID
	 */
	public void addObject(String name, int typID, int sammlungID) {
		String objekt = "INSERT INTO Objekt (ID_Objekt, ObjektName, Typ_ID) VALUES (NULL,'"
				+ name + "', '" + typID + "')";
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
			objekt = "UPDATE objekt SET Barcode = '" + key
					+ "' WHERE ID_Objekt ='" + key + "'";
			statement.executeUpdate(objekt);
			// Insert the fields

			ResultSet rs = this.selectFelderOfTypByID(typID);
			while (rs.next()) {
				this.connectDB();
				this.addEigenschaft("", key, rs.getInt("Feld_ID"));
			}

			propagateChange();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * 
	 * @param name
	 * @param typID
	 * @param sammlungID
	 * @param barcode
	 */
	public void addObject(String name, int typID, int sammlungID, String barcode) {
		String objekt = "INSERT INTO Objekt (ID_Objekt, ObjektName, Typ_ID, barcode) VALUES (NULL,'"
				+ name + "', '" + typID + "','" + barcode + "')";
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
			
			// Insert the fields
			ResultSet rs = this.selectFelderOfTypByID(typID);
			while (rs.next()) {
				this.connectDB();
				this.addEigenschaft("", key, rs.getInt("Feld_ID"));
			}

			propagateChange();
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
	public int addTyp(String name) {
		String typ = "INSERT INTO typ (ID_Typ, TypName) VALUES (NULL,'" + name
				+ "')";
		int key = -1;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(typ, Statement.RETURN_GENERATED_KEYS);
			rSet = statement.getGeneratedKeys();
			// Den neu erzeugten Primary Key in key speichern.
			while (rSet.next()) {
				key = rSet.getInt(1);
			}
			propagateChange();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return key;
	}

	public void addTyp_Feld(int typID, int feldID) {
		String typ = "INSERT INTO Typ_Feld (Typ_ID, Feld_ID) VALUES (" + typID
				+ "," + feldID + ")";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(typ);
			propagateChange();
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
	public void addStringFeld(String name) {
		String feld = "INSERT INTO feld (ID_Feld, FeldName) VALUES (NULL,'"
				+ name + "')";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(feld); // Der
											// generierte
											// Key
											// soll
											// bereitgestellt
											// werden.

			propagateChange();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * Adds an entry to table Eigenschaft.
	 * 
	 * @param wert
	 *            The value to be inserted.
	 * @param objektID
	 *            int The id of the corresponding object.
	 * @param feldID
	 *            int The id of the inserted feld.
	 */
	public void addEigenschaft(String wert, int objektID, int feldID) {
		String eigenschaft = "INSERT INTO eigenschaft (Objekt_ID, Feld_ID, Wert) VALUES ("
				+ objektID + "," + feldID + ",'" + wert + "')";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(eigenschaft);
			propagateChange();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		}
	}

	// Delete-Statements //
	// *******************//
	// *******************//

	/**
	 * Adds an entry to table Eigenschaft.
	 * 
	 * @param tblName
	 *            String The name of the table.
	 * @param id
	 *            int The id of the object.
	 */
	public void deleteItemOfTableByID(String tblName, int id) {
		String delete = "DELETE FROM " + tblName + " WHERE ID_" + tblName
				+ "= " + id;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(delete);
			propagateChange();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	/**
	 * 
	 * 
	 * 
	 */
	public void deleteSammlungByID(int id) {

		ResultSet resSet = selectObjectsOfSammlungByID(id);

		String deleteSammlung = "DELETE FROM sammlung WHERE ID_Sammlung = "
				+ id;

		try {
			statement = connection.createStatement();
			while (resSet.next()) {
				String delObj = "DELETE FROM objekt WHERE ID_Objekt ="
						+ resSet.getInt(1);
				statement.executeUpdate(delObj);
			}
			statement.executeUpdate(deleteSammlung);

			propagateChange();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	// Select-Statements //
	// *******************//
	// *******************//

	/**
	 * Selects everything from a given table.
	 * 
	 * @param tblName
	 *            The name of the table-
	 * @return ResultSet
	 */
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

	/**
	 * Selects a row with the specified ID from a table.
	 * 
	 * @param tblName
	 *            String. The name of the table.
	 * @param id
	 *            int The ID of the row. (Primary Key)
	 * @return ResultSet
	 */
	public ResultSet selectFromTableID(String tblName, int id) {
		String select = "SELECT * FROM " + tblName + "WHERE ID_" + tblName;
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

	/**
	 * Selects all Objekte of a Sammlung.
	 * 
	 * @param sammlungID
	 * @return ResultSet
	 */
	public ResultSet selectObjectsOfSammlungByID(int sammlungID) {
		String selObj = "SELECT * FROM Objekt AS O "
				+ "JOIN Objekt_Sammlung AS OS ON "
				+ "O.ID_Objekt = OS.Objekt_ID " + "WHERE " + sammlungID
				+ " = OS.Sammlung_ID";
		try {
			statement = connection.createStatement();
			statement.execute(selObj);
			rSet = statement.getResultSet();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rSet;
	}

	/**
	 * Selects all info to all Objekte
	 * 
	 * @return ArrayList<String>
	 */
	public ResultSet selectColumnFromObjectInfo(String colName) {
		String selAll = "SELECT * FROM allObjInfo";

		try {
			statement = connection.createStatement();
			statement.execute(selAll);
			rSet = statement.getResultSet();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rSet;
	}

	/**
	 * 
	 * @param typID
	 * @return
	 */
	public ResultSet selectFelderOfTypByID(int typID) {
		String selFelder = "SELECT * FROM feld as F "
				+ "JOIN Typ_Feld as TF ON F.ID_Feld = TF.Feld_ID "
				+ "WHERE TF.Typ_ID = " + typID;
		try {
			statement = connection.createStatement();
			statement.execute(selFelder);
			rSet = statement.getResultSet();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rSet;

	}

	public ResultSet selectBarcodeOfObjekt(int objectID) {
		String select = "SELECT barcode FROM objekt WHERE ID_Objekt= "
				+ objectID;
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

	/**
	 * Selects the objekt with the given Barcode.
	 * 
	 * @param barcode
	 * @return
	 */
	public ResultSet selectobjectWithBarcode(String barcode) {
		String select = "SELECT ID_Objekt FROM objekt WHERE barcode= "
				+ barcode;
		ResultSet newResult = null;
		try {
			statement = connection.createStatement();
			statement.execute(select);
			rSet = statement.getResultSet();
			while (rSet.next()) {
				newResult = this.selectAllInfoOfObject(rSet.getInt(1));
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return newResult;
	}

	/**
	 * ajfhalh
	 * 
	 * @param objektID
	 * @return
	 */
	public ResultSet selectAllInfoOfObjectByBarcode(String barcode) {
		String select = "SELECT * FROM objekt AS O "
				+ "JOIN Typ_Feld as TF on TF.Typ_ID = O.Typ_ID "
				+ "JOIN Feld as F on F.ID_Feld = TF.Feld_ID "
				+ "JOIN Eigenschaft as E on E.Feld_ID = TF.Feld_ID ,"
				+ "(SELECT ID_Objekt FROM Objekt WHERE barcode = '"
				+ barcode
				+ "' ) as ID "
				+ "WHERE O.ID_Objekt = ID.ID_Objekt AND E.Objekt_ID = ID.ID_Objekt";
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

	/**
	 * ajfhalh
	 * 
	 * @param objektID
	 * @return
	 */
	public ResultSet selectAllInfoOfObject(int objektID) {

		String select = "SELECT * FROM objekt AS O "
				+ "JOIN Typ_Feld as TF on TF.Typ_ID = O.Typ_ID "
				+ "JOIN Feld as F on F.ID_Feld = TF.Feld_ID "
				+ "JOIN Eigenschaft as E on E.Feld_ID = TF.Feld_ID "
				+ "WHERE O.ID_Objekt = " + objektID + " AND E.Objekt_ID ="
				+ objektID;
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

	/**
	 * Updates table Eigenschaft for an Objekt.
	 * 
	 * @param objectID
	 * @param feldID
	 * @param wert
	 */
	public void updateObjectData(int objectID, int feldID, String wert) {
		String update = "UPDATE eigenschaft SET wert = '" + wert
				+ "' WHERE objekt_ID = " + objectID + " AND feld_ID = "
				+ feldID;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(update);
			rSet = statement.getResultSet();
			propagateChange();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Combines setChanged and notifyObservers in one method.
	 */
	private void propagateChange() {
		this.setChanged();
		this.notifyObservers();
	}
}
