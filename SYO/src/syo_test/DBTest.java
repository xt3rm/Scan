package syo_test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import syo_model.DBTool;

public class DBTest {

	@Before
	public void setUp() throws Exception {
		DBTool.getInstance().setupDB("testsyo");
	}

	@After
	public void tearDown() throws Exception {
		DBTool.getInstance().dropDB("testsyo");
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
		assert(DBTool.getInstance().getRowCount("typ") == 1);
		assert(DBTool.getInstance().getRowCount("feld") == 1);
		assert(DBTool.getInstance().getRowCount("typ_feld") == 1);
	}
	
	@Test
	public void testUpdateSammlung() {
		DBTool.getInstance().addSammlung("TestSammlung");
		assert(DBTool.getInstance().getRowCount("sammlung") == 1);
	}
	
	@Test
	public void testUpdateObjekt() {
		DBTool.getInstance().addSammlung("TestSammlung");
		assert(DBTool.getInstance().getRowCount("sammlung") == 1);
		
		DBTool.getInstance().addTyp("TestTyp");
		assert(DBTool.getInstance().getRowCount("typ") == 1);
		
		DBTool.getInstance().addStringFeld("Tester", 2);
		assert(DBTool.getInstance().getRowCount("feld") == 1);
		assert(DBTool.getInstance().getRowCount("typ_feld") == 1);
		DBTool.getInstance().addObject("Testobjekt", 2, 2);
		assert(DBTool.getInstance().getRowCount("objekt") == 1);
		assert(DBTool.getInstance().getRowCount("objekt_sammlung") == 1);
	}
	
}
