package tee.gui;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import tee.fachlogik.Kunde;


public class KundenListRenderer implements ListCellRenderer<Kunde>
{

	@Override
	public Component getListCellRendererComponent(JList<? extends Kunde> list,
			Kunde value, int index, boolean isSelected, boolean cellHasFocus)
	{
		JPanel jp = new JPanel(new GridLayout(1, 5));
		jp.add(new JLabel(String.valueOf(value.getId())));
		jp.add(new JLabel(value.getNachname()));
		jp.add(new JLabel(value.getVorname()));
		jp.add(new JLabel(String.valueOf(value.getPlz())));
		jp.add(new JLabel(value.getOrt()));
		
		if (!isSelected)
			jp.setBackground(list.getBackground());
		else
			jp.setBackground(list.getSelectionBackground());
		return jp;
	}
}

