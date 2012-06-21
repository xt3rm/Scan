package syo_controller;

/**
 * Wraps the relevant data of a basic Entry in the database into a class.
 * 
 * @author ebrogt
 *
 */
public class DBBasisObjekt {

	private String name;
	private int id;
	
	
	public DBBasisObjekt(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public DBBasisObjekt() {}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
