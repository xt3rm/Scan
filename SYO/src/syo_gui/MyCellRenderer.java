package syo_gui;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import syo_controller.DBFeld;

@SuppressWarnings("serial")
public class MyCellRenderer extends JPanel implements ListCellRenderer {

	private JLabel left;
	private JTextField right;

	public MyCellRenderer() {
		setLayout(new GridLayout(1, 2));
		left = new JLabel();
		right = new JTextField();
		left.setOpaque(true);
		right.setOpaque(true);
		add(left);
		add(right);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object dbfeld,
			int index, boolean isSelected, boolean cellHasFocus) {
		String leftData = ((DBFeld) dbfeld).getName();
		String rightData = ((DBFeld) dbfeld).getWert();
		left.setText(leftData);
		right.setText(rightData);
		if (isSelected) {
			left.setBackground(list.getSelectionBackground());
			left.setForeground(list.getSelectionForeground());
			right.setBackground(list.getSelectionBackground());
			right.setForeground(list.getSelectionForeground());
		} else {
			left.setBackground(list.getBackground());
			left.setForeground(list.getForeground());
			right.setBackground(list.getBackground());
			right.setForeground(list.getForeground());
		}
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		return this;
	}

}
