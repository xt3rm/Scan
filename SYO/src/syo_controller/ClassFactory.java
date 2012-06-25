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
		dbo.setChildren(new ArrayList<DBBasisObjekt>());
		int count = 0; // To check if something was found
		try {
			while (rs.next()) {
				count++;
				dbo.getChildren().add(
						new DBFeld(rs.getString("FeldName"), rs
								.getInt("Feld_ID"), rs.getString("Wert")));
				dbo.setId(rs.getInt("ID_Objekt"));
				dbo.setName(rs.getString("ObjektName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (count == 0) {
			throw new Exception("Ungültiger Barcode: Objekt nicht gefunden!");
		}

		return dbo;
	}

}
