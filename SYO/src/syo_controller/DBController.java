package syo_controller;

import java.util.Observable;
import java.util.Observer;

import syo_model.DBTool;

public class DBController implements Observer {

	
	public DBController() {
		DBTool.getInstance().addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Update");
		
	}

}
