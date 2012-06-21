package syo_controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import syo_gui.MainView;
import syo_model.DBBasisObjekt;
import syo_model.DBTool;

public class DBController implements Observer {

	private MainView mainView;
	private ArrayList<String> sammlungen;
	

	public ArrayList<String> getSammlungen() {
		return sammlungen;
	}

	public void setSammlungen(ArrayList<String> sammlungen) {
		this.sammlungen = sammlungen;
	}

	public DBController(MainView mainView) {
		this.mainView = mainView;
		DBTool.getInstance().addObserver(this);
		sammlungen = new ArrayList<String>();
		// Change
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Update");

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
	 * @param name
	 * @param typId
	 * @param sammlungID
	 * @param barcode
	 */
	public void createObjekt(String name, int typId, int sammlungID, String barcode) {
		
	}

	/**
	 * Deletes a Sammlungsobjekt.
	 * 
	 * @param id
	 *            The id of the Sammlung
	 */
	public void deleteSammlung(int id) {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().deleteItemOfTableByID("sammlung", id);
		DBTool.getInstance().closeDB();
	}

	// Update Methods

	public ArrayList<DBBasisObjekt> updateSammlung() {
		DBTool.getInstance().connectDB();
		ArrayList<DBBasisObjekt> result = DBTool.getInstance().selectAllFromTable(
				"sammlung");
		DBTool.getInstance().closeDB();
		return result;
	}
	
	public ArrayList<DBBasisObjekt> getObjektOfSammlung(DBBasisObjekt obj) {
		DBTool.getInstance().connectDB();
		ArrayList<DBBasisObjekt> result = DBTool.getInstance().selectObjectsOfSammlungByID(obj.getId());
		DBTool.getInstance().closeDB();
		return result;
	}
	
	public ArrayList<DBBasisObjekt> getEveryRowOfTable(String tblName) {
		DBTool.getInstance().connectDB();
		ArrayList<DBBasisObjekt> result =DBTool.getInstance().selectAllFromTable(tblName);
		DBTool.getInstance().closeDB();
		return result;
	}
	
	

}
