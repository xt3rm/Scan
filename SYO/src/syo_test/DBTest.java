package syo_test;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import syo_model.DBBasisObjekt;
import syo_model.DBTool;

public class DBTest {

	@Before
	public void setUp() throws Exception {
		DBTool.getInstance().setupDB("testsyo");
		DBTool.getInstance().addSammlung("TestSammlung");
		assertTrue(DBTool.getInstance().getRowCount("sammlung") == 1);
		
		DBTool.getInstance().addTyp("TestTyp");
		assertTrue(DBTool.getInstance().getRowCount("typ") == 1);
		
		DBTool.getInstance().addStringFeld("Tester", 2);
		assertTrue(DBTool.getInstance().getRowCount("feld") == 1);
		assertTrue(DBTool.getInstance().getRowCount("typ_feld") == 1);
		
		DBTool.getInstance().addObject("Testobjekt", 2, 2, "12345");
		assertTrue(DBTool.getInstance().getRowCount("objekt") == 1);
		assertTrue(DBTool.getInstance().getRowCount("objekt_sammlung") == 1);
		
		DBTool.getInstance().addEigenschaft("TestWert", 2, 2);
		assertTrue(DBTool.getInstance().getRowCount("eigenschaft") == 1);
	}

	@After
	public void tearDown() throws Exception {
		DBTool.getInstance().dropDB("testsyo");
		DBTool.getInstance().closeDB();
	}
	
	@Test
	public void testConnection() {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().closeDB();
	}
	
	@Test
	public void testSetup() {
		DBTool.getInstance().dropDB("testsyo");
		DBTool.getInstance().setupDB("testsyo");
	}
	
	@Test
	public void testUpdateTypAndFeld() {
		DBTool.getInstance().addTyp("TestTyp");
		DBTool.getInstance().addStringFeld("Tester", 2);
		assertTrue(DBTool.getInstance().getRowCount("typ") == 1);
		assertTrue(DBTool.getInstance().getRowCount("feld") == 1);
		assertTrue(DBTool.getInstance().getRowCount("typ_feld") == 1);
	}
	
	@Test
	public void testUpdateSammlung() {
		DBTool.getInstance().addSammlung("TestSammlung");
		assert(DBTool.getInstance().getRowCount("sammlung") == 1);
	}
	
	@Test
	public void testSelectAllFromTable() {
		ArrayList<DBBasisObjekt> rs = DBTool.getInstance().selectAllFromTable("objekt");
		assertTrue(rs.get(0).equals("Testobjekt"));
		ArrayList<DBBasisObjekt> rt = DBTool.getInstance().selectAllFromTable("allObjInfo");
		assertTrue(rt.get(0).equals("TestWert"));
	}	
}
