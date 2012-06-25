package syo_controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import syo_model.DBTool;

/**
 * Controller für die Datenbankanbindung und das Erstellen von Objekten daraus.
 * 
 * @author ebrogt
 * 
 */
public class DBController  {

	private ArrayList<String> sammlungen;
	private ClassFactory factory;

	public DBController() {
		sammlungen = new ArrayList<String>();
		factory = new ClassFactory();
	}

	public ArrayList<String> getSammlungen() {
		return sammlungen;
	}

	public void setSammlungen(ArrayList<String> sammlungen) {
		this.sammlungen = sammlungen;
	}

	/**
	 * Creates a Sammlungsobjekt.
	 * 
	 * @param name
	 *            The name of the Sammlung
	 */
	public void createSammlung(String name) {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().addSammlung(name);
		DBTool.getInstance().closeDB();
	}

	/**
	 * Creates an Objekt.
	 * 
	 * @param name
	 * @param typID
	 * @param sammlungID
	 */
	public void createObjekt(String name, int typID, int sammlungID) {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().addObject(name, typID, sammlungID);
		DBTool.getInstance().closeDB();
	}

	/**
	 * Creates an Objekt if the barcode is already known.
	 * 
	 * @param name
	 * @param typId
	 * @param sammlungID
	 * @param barcode
	 */
	public void createObjekt(String name, int typID, int sammlungID,
			String barcode) {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().addObject(name, typID, sammlungID, barcode);
		DBTool.getInstance().closeDB();

	}

	/**
	 * Creates a new Feld.
	 * 
	 * @param name
	 */
	public void createFeld(String name) {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().addStringFeld(name);
		DBTool.getInstance().closeDB();
	}

	/**
	 * Creates a new Typ.
	 * 
	 * @param name
	 *            Name of the Typ.
	 * @param felder
	 *            The felder of the Typ.
	 */
	public void createTyp(String name, ArrayList<DBBasisObjekt> felder) {
		DBTool.getInstance().connectDB();
		int typID = DBTool.getInstance().addTyp(name);
		DBTool.getInstance().closeDB();
		for (DBBasisObjekt d : felder) {
			DBTool.getInstance().connectDB();
			DBTool.getInstance().addTyp_Feld(typID, d.getId());
			DBTool.getInstance().closeDB();
		}

	}

	/**
	 * Deletes a Sammlungsobjekt.
	 * 
	 * @param id
	 *            The id of the Sammlung
	 */
	public void deleteSammlung(int id) {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().deleteSammlungByID(id);
		DBTool.getInstance().closeDB();
	}
	
	/**
	 * Deletes a Objekt.
	 * 
	 * @param id
	 *            The id of the Objekt
	 */
	public void deleteObjekt(int id) {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().deleteItemOfTableByID("objekt", id);
		DBTool.getInstance().closeDB();
	}

	/**
	 * Get all Objekte in a Sammlung represented by a DBBasisObjekt.
	 * @param obj
	 * @return
	 */
	public ArrayList<DBBasisObjekt> getBaseObjektOfSammlung(DBBasisObjekt obj) {
		DBTool.getInstance().connectDB();
		ResultSet rs = DBTool.getInstance().selectObjectsOfSammlungByID(
				obj.getId());
		ArrayList<DBBasisObjekt> dbo = factory.createBasisObjekte(rs);
		DBTool.getInstance().closeDB();
		return dbo;
	}

	/**
	 * Returns an Objekt with all its values.
	 * 
	 * @param obj
	 * @return DBBasisObjekt
	 * @throws Exception
	 */
	public DBBasisObjekt getWholeObjekt(DBBasisObjekt dbo) throws Exception {
		DBTool.getInstance().connectDB();
		ResultSet rs = DBTool.getInstance().selectAllInfoOfObject(dbo.getId());
		dbo = factory.createObjektWithFields(dbo, rs);
		DBTool.getInstance().closeDB();
		return dbo;
	}

	/**
	 * Returns every row of a given table.
	 * 
	 * @param tblName
	 *            The name of the table.
	 * @return ArrayList<DBBasisObjekt>
	 */
	public ArrayList<DBBasisObjekt> getEveryRowOfTable(String tblName) {
		DBTool.getInstance().connectDB();
		ResultSet rs = DBTool.getInstance().selectAllFromTable(tblName);
		ArrayList<DBBasisObjekt> dbo = factory.createBasisObjekte(rs);
		DBTool.getInstance().closeDB();
		return dbo;
	}

	// Update Methods

	/**
	 * Get all Sammlungen
	 */
	public ArrayList<DBBasisObjekt> getSammlung() {
		DBTool.getInstance().connectDB();
		ResultSet rs = DBTool.getInstance().selectAllFromTable("sammlung");
		ArrayList<DBBasisObjekt> dbo = factory.createBasisObjekte(rs);
		DBTool.getInstance().closeDB();
		return dbo;
	}

	/**
	 * Uodates the Eigenschaften of an Objekt.
	 * 
	 * @param dbo
	 */
	public void updateObjekt(DBBasisObjekt dbo) {
		for (DBBasisObjekt f : dbo.getChildren()) {
			DBTool.getInstance().connectDB();
			DBTool.getInstance().updateObjectData(dbo.getId(), f.getId(),
					((DBFeld) f).getWert());
			DBTool.getInstance().closeDB();
		}
	}

	/**
	 * Gets the Barcode of an Objekt as a String.
	 * 
	 * @param dbo
	 * @return String
	 */
	public String getBarcodeOfObject(DBBasisObjekt dbo) {
		String result = "";
		DBTool.getInstance().connectDB();
		ResultSet rs = DBTool.getInstance().selectBarcodeOfObjekt(dbo.getId());
		try {
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBTool.getInstance().closeDB();
		return result;
	}

	/**
	 * Get the object belonging to the given barcode.
	 * 
	 * @param barcode
	 *            The barcode we are looking for.
	 * @return DBBasisObjekt
	 * @throws Exception
	 *             Throws an Exception if the Objekt was not found.
	 */
	public DBBasisObjekt getObjectOfBarcode(String barcode) throws Exception {
		DBBasisObjekt dbo = new DBBasisObjekt();
		DBTool.getInstance().connectDB();
		ResultSet rs = DBTool.getInstance().selectAllInfoOfObjectByBarcode(
				barcode);
		dbo = factory.createObjektWithFields(dbo, rs);
		DBTool.getInstance().closeDB();

		return dbo;
	}

}
