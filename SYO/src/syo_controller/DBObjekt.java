package syo_controller;



/**
 * DBObjekt models an Objekt in the database.
 * 
 * @author ebrogt
 * 
 */
public class DBObjekt extends DBBasisObjekt {

	private int sammlungID;
	private DBTyp typ;

	public DBObjekt(String name, int id, DBTyp typ, int sammlungID) {
		super(name, id);
		this.setSammlungID(sammlungID);
	}
	/**
	 * Used for initial creation.
	 * @param sammlungID
	 */
	public DBObjekt(int sammlungID){
		super();
		this.setSammlungID(sammlungID);
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

}
