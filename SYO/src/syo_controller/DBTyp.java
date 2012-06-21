package syo_controller;

import java.util.ArrayList;

public class DBTyp extends DBBasisObjekt {

	private ArrayList<DBFeld> felder;
	
	public DBTyp(String name, int id) {
		super(name, id);
	}
	
	public DBTyp(){
		super();
	}
	
	public void addFeld(DBFeld feld) {
		felder.add(feld);
	}
	
	public void removeFeld(DBFeld feld) {
		felder.remove(feld);
	}

}
