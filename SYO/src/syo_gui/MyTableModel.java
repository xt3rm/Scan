package syo_gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import syo_controller.DBFeld;

@SuppressWarnings("serial")
public class MyTableModel extends AbstractTableModel {

	private ArrayList<DBFeld> felder;
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
