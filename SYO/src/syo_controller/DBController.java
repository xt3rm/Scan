package syo_controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import syo_model.DBTool;
import syo_gui.MainView;

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

	public ArrayList<String> updateSammlung() {
		DBTool.getInstance().connectDB();
		ArrayList<String> result = DBTool.getInstance().selectColumnFromTable(
				"sammlung", "SammlungName");
		DBTool.getInstance().closeDB();
		return result;
	}

}
