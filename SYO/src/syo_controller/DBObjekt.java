package syo_controller;

import java.util.ArrayList;



/**
 * DBObjekt models an Objekt in the database.
 * 
 * @author ebrogt
 * 
 */
public class DBObjekt extends DBBasisObjekt {

	private int sammlungID;
	private DBTyp typ;
	private ArrayList<DBFeld> felder;

	public DBObjekt(String name, int id, DBTyp typ, int sammlungID) {
		super(name, id);
		this.setSammlungID(sammlungID);
		felder = new ArrayList<DBFeld>();
	}
	/**
	 * Used for initial creation.
	 * @param sammlungID
	 */
	public DBObjekt(int sammlungID){
		super();
		this.setSammlungID(sammlungID);
		felder = new ArrayList<DBFeld>();
	}

	public void setSammlungID(int sammlungID) {
		this.sammlungID = sammlungID;
	}

	public int getSammlungID() {
		return sammlungID;
	}

	public void setTyp(DBTyp typ) {
		this.typ = typ;
	}

	public DBTyp getTyp() {
		return typ;
	}
	
	public void addFeld(DBFeld feld) {
		felder.add(feld);
	}
	
	public void removeFeld(DBFeld feld) {
		felder.remove(feld);
	}

	public ArrayList<DBFeld> getFelder() {
		return this.felder;
	}
}
