package syo_gui;

import syo_model.DBTool;

public class SYO  {

	public static void main(String[] args) {
		DBTool.getInstance().connectDB();
		DBTool.getInstance().addObject("TestNeu", 1, 1);
		DBTool.getInstance().closeDB();
		new MainView("SYO");
	
	}
}
