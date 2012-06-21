package syo_gui;

import syo_model.DBTool;


public class SYO  {

	public static void main(String[] args) {
		DBTool.getInstance().setupDB("syo");
		MainView mv = new MainView();
		mv.setVisible(true);
		mv.setLocationRelativeTo(null);
	}
}