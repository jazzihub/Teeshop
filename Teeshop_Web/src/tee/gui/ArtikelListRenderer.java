package tee.gui;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import tee.fachlogik.Artikel;
import tee.fachlogik.Kunde;

public class ArtikelListRenderer implements ListCellRenderer<Artikel>
{

	@Override
	public Component getListCellRendererComponent(JList<? extends Artikel> list,
			Artikel value, int index, boolean isSelected, boolean cellHasFocus)
	{
		JPanel jp = new JPanel(new GridLayout(1, 5));
		jp.add(new JLabel(String.valueOf(value.getId())));
		jp.add(new JLabel(value.getBezeichnung()));
		jp.add(new JLabel(String.valueOf(value.getGramm())));
		jp.add(new JLabel(String.valueOf(value.getPreisNetto())));
		jp.add(new JLabel(String.valueOf(value.getUst())));
		jp.add(new JLabel(String.valueOf(value.getLagerstand())));
		if (!isSelected)
			jp.setBackground(list.getBackground());
		else
			jp.setBackground(list.getSelectionBackground());
		return jp;
	}

}
