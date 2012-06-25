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
	 * 
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

	/**
	 * 
	 * @param dbo
	 * @param rs
	 * @return
	 * @throws Exception
	 */

	public DBBasisObjekt createObjektWithFields(DBBasisObjekt dbo, ResultSet rs)
			throws Exception {
		if (rs != null) {
			dbo.setChildren(new ArrayList<DBBasisObjekt>());
			try {
				while (rs.next()) {
					dbo.getChildren().add(
							new DBFeld(rs.getString("FeldName"), rs
									.getInt("Feld_ID"), rs.getString("Wert")));
					dbo.setId(rs.getInt("ID_Objekt"));
					dbo.setName(rs.getString("ObjektName"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new Exception("Did not find Object");
		}

		return dbo;
	}

}
