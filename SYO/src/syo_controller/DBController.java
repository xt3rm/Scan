package syo_controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import syo_gui.MainView;
import syo_model.DBTool;

public class DBController implements Observer {

	private MainView mainView;
	private ArrayList<String> sammlungen;
	private ClassFactory factory;

	public DBController(MainView mainView) {
		this.mainView = mainView;
		DBTool.getInstance().addObserver(this);
		sammlungen = new ArrayList<String>();
		factory = new ClassFactory();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Update");
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
	public void createObjekt(String name, int typId, int sammlungID,
			String barcode) {

	}

	/**
	 * 
	 * @param name
	 */
	public void createFeld(String name) {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().addStringFeld(name);
		DBTool.getInstance().closeDB();
	}

	/**
	 * 
	 * @param name
	 * @param felder
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
	 */
	public DBBasisObjekt getWholeObjekt(DBBasisObjekt dbo) {
		DBTool.getInstance().connectDB();
		ResultSet rs = DBTool.getInstance().selectAllInfoOfObject(dbo.getId());
		dbo = factory.createObjektWithFields(dbo, rs);
		DBTool.getInstance().closeDB();
		return dbo;
	}

	/**
	 * 
	 * @param tblName
	 * @return
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
	 * 
	 */
	public ArrayList<DBBasisObjekt> updateSammlung() {
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

}
