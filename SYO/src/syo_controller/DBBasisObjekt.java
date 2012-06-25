package syo_controller;

import java.util.ArrayList;

/**
 * Wraps the relevant data of a basic Entry in the database into a class.
 * 
 * @author ebrogt
 *
 */
public class DBBasisObjekt {

	private String name;
	private int id;
	protected DBBasisObjekt parent = null;
	protected ArrayList<DBBasisObjekt> children = new ArrayList<DBBasisObjekt>();
	
	
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

	public void setParent(DBBasisObjekt parent) {
		this.parent = parent;
	}

	public DBBasisObjekt getParent() {
		return this.parent;
	}

	public void setChildren(ArrayList<DBBasisObjekt> children) {
		this.children = children;
	}

	public ArrayList<DBBasisObjekt> getChildren() {
		return children;
	}
}
