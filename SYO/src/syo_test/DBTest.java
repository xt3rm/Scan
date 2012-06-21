package syo_test;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import syo_controller.DBBasisObjekt;
import syo_model.DBTool;

public class DBTest {

	@Before
	public void setUp() throws Exception {
		DBTool.getInstance().setupDB("testsyo");
		DBTool.getInstance().connectDB();
		
		DBTool.getInstance().addSammlung("TestSammlung");
		
		DBTool.getInstance().addTyp("TestTyp");

		
		DBTool.getInstance().addStringFeld("Tester");

		
		DBTool.getInstance().addObject("Testobjekt", 2, 2);
	
		
		DBTool.getInstance().addEigenschaft("TestWert", 2, 2);

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
		DBTool.getInstance().addStringFeld("Tester");
		assertTrue(DBTool.getInstance().getRowCount("typ") == 1);
		assertTrue(DBTool.getInstance().getRowCount("feld") == 1);
		assertTrue(DBTool.getInstance().getRowCount("typ_feld") == 1);
	}
	
	@Test
	public void testUpdateSammlung() {
		DBTool.getInstance().addSammlung("TestSammlung");
		assert(DBTool.getInstance().getRowCount("sammlung") == 1);
	}
	
	
}
