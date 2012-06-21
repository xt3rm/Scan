package syo_controller;



/**
 * DBFeld models a Feld in the database.
 * 
 * @author ebrogt
 *
 */
public class DBFeld extends DBBasisObjekt {

	private String wert;
	
	public DBFeld(String name, int id, String wert) {
		super(name, id);
		this.setWert(wert);
	}

	public void setWert(String wert) {
		this.wert = wert;
	}

	public String getWert() {
		return wert;
	}

}
