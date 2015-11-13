package tee.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import tee.fachlogik.Bestellung;



public class BestellListRenderer implements ListCellRenderer<Bestellung>
{

	@Override
	public Component getListCellRendererComponent(JList<? extends Bestellung> list,
			Bestellung value, int index, boolean isSelected, boolean cellHasFocus)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		
		JPanel jp = new JPanel(new GridLayout(1, 5));
		jp.add(new JLabel (sdf.format(value.getDatum())));
		jp.add(new JLabel(value.getArtikel().getBezeichnung()));
		jp.add(new JLabel(String.valueOf(value.getAnzahl())));
		
		if (!isSelected)
			jp.setBackground(list.getBackground());
		else
			jp.setBackground(list.getSelectionBackground());
		return jp;
	}
}
