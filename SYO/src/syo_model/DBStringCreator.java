package syo_model;

/**
 * Holds the Strings needed to setup the database. Contains almost no logic but
 * many setters and getters.
 * 
 * @author ebrogt
 * 
 */
public class DBStringCreator {

	private String tblSammlung;
	private String tblObjekt;
	private String tblTyp;
	private String tblFeld;
	private String tblEigenschaft;
	private String tblTyp_Feld;
	private String tblObjekt_Sammlung;

	/**
	 * Constructor. Sets the SQL-Statements.
	 */
	public DBStringCreator() {
		tblSammlung = "CREATE TABLE sammlung ("
				+ "ID_Sammlung int(11) NOT NULL AUTO_INCREMENT, "
				+ "SammlungName varchar(100) NOT NULL,"
				+ "PRIMARY KEY (ID_Sammlung)"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1";

		tblObjekt = "CREATE TABLE objekt ("
				+ "ID_Objekt int(11) NOT NULL AUTO_INCREMENT,"
				+ "ObjektName varchar(50) NOT NULL,"
				+ "Typ_ID int(11) NOT NULL,"
				+ "Barcode varchar(100) DEFAULT NULL,"
				+ "PRIMARY KEY (ID_Objekt),"
				+ "KEY FK_Objekt_Typ (Typ_ID),"
				+ "CONSTRAINT FK_Objekt_Typ FOREIGN KEY (Typ_ID) REFERENCES typ (ID_Typ)"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1";

		tblFeld = "CREATE TABLE feld (ID_Feld int(11) NOT NULL AUTO_INCREMENT,"
				+ "  FeldName varchar(50) NOT NULL," + "PRIMARY KEY (ID_Feld)"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1";

		tblTyp = "CREATE TABLE typ ("
				+ "ID_Typ int(11) NOT NULL AUTO_INCREMENT,"
				+ " TypName varchar(50) NOT NULL," + " PRIMARY KEY (ID_Typ)"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1";

		tblTyp_Feld = "CREATE TABLE typ_feld ("
				+ "Typ_ID int(11) NOT NULL,"
				+ "Feld_ID int(11) NOT NULL,"
				+ "PRIMARY KEY (Typ_ID,Feld_ID),"
				+ "KEY FK_Typ_Feld_Feld (Feld_ID),"
				+ "CONSTRAINT FK_Typ_Feld_Feld FOREIGN KEY (Feld_ID) REFERENCES feld (ID_Feld) ON DELETE CASCADE,"
				+ "CONSTRAINT FK_Typ_Feld_Typ FOREIGN KEY (Typ_ID) REFERENCES typ (ID_Typ) ON DELETE CASCADE"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1";

		tblEigenschaft = "CREATE TABLE eigenschaft ("
				+ "Objekt_ID int(11) NOT NULL,"
				+ "Feld_ID int(11) NOT NULL,"
				+ "Wert varchar(1000) DEFAULT NULL,"
				+ "PRIMARY KEY (Objekt_ID,Feld_ID),"
				+ "KEY FK_Eigenschaften_Wert (Feld_ID),"
				+ "KEY Fk_Eigenschaft_Objekt_ID (Objekt_ID),"
				+ "KEY Fk_Eigenschaft_Feld_ID (Feld_ID),"
				+ "CONSTRAINT Fk_Eigenschaft_Feld_ID FOREIGN KEY (Feld_ID) REFERENCES feld (ID_Feld) ON DELETE CASCADE ON UPDATE NO ACTION,"
				+ "CONSTRAINT Fk_Eigenschaft_Objekt_ID FOREIGN KEY (Objekt_ID) REFERENCES objekt (ID_Objekt) ON DELETE CASCADE ON UPDATE NO ACTION"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1";

		tblObjekt_Sammlung = "CREATE TABLE objekt_sammlung ("
				+ "Objekt_ID int(11) NOT NULL,"
				+ "Sammlung_ID int(11) NOT NULL,"
				+ "PRIMARY KEY (Objekt_ID,Sammlung_ID),"
				+ "KEY FK_Objekt_Sammlung_Sammlung (Sammlung_ID),"
				+ "CONSTRAINT FK_Objekt_Sammlung_Objekt FOREIGN KEY (Objekt_ID) REFERENCES objekt (ID_Objekt) ON DELETE CASCADE,"
				+ "CONSTRAINT FK_Objekt_Sammlung_Sammlung FOREIGN KEY (Sammlung_ID) REFERENCES sammlung (ID_Sammlung) ON DELETE CASCADE"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=latin1";
	}

	
	
	public void setTblSammlung(String tblSammlung) {
		this.tblSammlung = tblSammlung;
	}

	public String getTblSammlung() {
		return tblSammlung;
	}

	public void setTblObjekt(String tblObjekt) {
		this.tblObjekt = tblObjekt;
	}

	public String getTblObjekt() {
		return tblObjekt;
	}

	public void setTblTyp(String tblTyp) {
		this.tblTyp = tblTyp;
	}

	public String getTblTyp() {
		return tblTyp;
	}

	public void setTblFeld(String tblFeld) {
		this.tblFeld = tblFeld;
	}

	public String getTblFeld() {
		return tblFeld;
	}

	public void setTblEigenschaft(String tblEigenschaft) {
		this.tblEigenschaft = tblEigenschaft;
	}

	public String getTblEigenschaft() {
		return tblEigenschaft;
	}

	public void setTblTyp_Feld(String tblTyp_Feld) {
		this.tblTyp_Feld = tblTyp_Feld;
	}

	public String getTblTyp_Feld() {
		return tblTyp_Feld;
	}

	public void setTblObjekt_Sammlung(String tblObjekt_Sammlung) {
		this.tblObjekt_Sammlung = tblObjekt_Sammlung;
	}

	public String getTblObjekt_Sammlung() {
		return tblObjekt_Sammlung;
	}

}
