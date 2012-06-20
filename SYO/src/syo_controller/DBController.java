package syo_controller;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		//Change
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Update");

	}

	public void createSammlung(String name) {
		DBTool.getInstance().addSammlung(name);
	}

}
