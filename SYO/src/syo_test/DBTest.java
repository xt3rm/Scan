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
	/*
	@Test
	public void testUpdateField() {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().addStringFeld("Tester", 1);
		DBTool.getInstance().closeDB();
	}*/
	
}
