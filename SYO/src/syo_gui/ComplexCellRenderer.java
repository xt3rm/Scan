
package syo_gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class ComplexCellRenderer implements ListCellRenderer {
	  protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	
	  public Component getListCellRendererComponent(JList list, Object value, int index,
		    boolean isSelected, boolean cellHasFocus) {
		    Font theFont = null;
		    Color theForeground = null;
		    String theText = null;
		
		    JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
		    isSelected, cellHasFocus);
		
		    if (value instanceof Object[]) {
		    	Object values[] = (Object[]) value;
			    theFont = (Font) values[0];
			    theForeground = (Color) values[1];
			    theText = (String) values[2];
		    } else {
			    theFont = list.getFont();
			    theForeground = list.getForeground();
			    theText = "";
		    }
		    if (!isSelected) {
		    	renderer.setForeground(theForeground);
		    }
		    renderer.setText(theText);
		    renderer.setFont(theFont);
		    return renderer;
	  }
}