package syo_gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import syo_controller.DBBasisObjekt;
import syo_controller.DBFeld;

@SuppressWarnings("serial")
public class MyTableModel extends AbstractTableModel {

	private ArrayList<DBBasisObjekt> felder;
	
	public MyTableModel( ArrayList<DBBasisObjekt> felder) {
		this.felder = felder;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return felder.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		String result = "";
		if (col == 0) {
			result = ((DBFeld)felder.get(row)).getName();
		} else {
			result = ((DBFeld)felder.get(row)).getWert();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
	
	@Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }
    
	@Override
    public void setValueAt(Object value, int row, int col) {
        ((DBFeld)felder.get(row)).setWert(value.toString());
        fireTableCellUpdated(row, col);
    }
}
