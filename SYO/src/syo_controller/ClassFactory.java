package syo_controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ClassFactory turns ResultSets into different classes.
 * 
 * @author ebrogt
 * 
 */
public class ClassFactory {

	/**
	 * Creates a DBBasisObjekt.
	 * @param rs
	 * @return
	 */
	public ArrayList<DBBasisObjekt> createBasisObjekte(ResultSet rs) {
		ArrayList<DBBasisObjekt> dbo = new ArrayList<DBBasisObjekt>();
		try {
			while (rs.next()) {
				dbo.add(new DBBasisObjekt(rs.getString(2), rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbo;
	}
}
