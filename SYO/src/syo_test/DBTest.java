package syo_test;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import syo_model.DBTool;

public class DBTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void testSetup() {
		DBTool.getInstance().dropDB("testsyo");
		DBTool.getInstance().setupDB("testsyo");
	}
	
	@Test
	public void testConnection() {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().closeDB();
	}
	/*
	@Test
	public void testUpdateField() {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().addStringFeld("Tester", 1);
		DBTool.getInstance().closeDB();
	}
	*/
}
