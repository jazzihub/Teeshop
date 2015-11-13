package tee.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import tee.fachlogik.Bestellung;
import tee.fachlogik.Kunde;
import tee.fachlogik.TeeException;
import tee.persistence.BestellungenDB;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class Dialog_BestellungenAnzeigen extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	// private BestellungenDB bestellung;
	private Kunde kunde;

	private DefaultListModel<Bestellung> bestellModel = new DefaultListModel<>();
	private JList<Bestellung> listBestellung;
	private JTextField tfKunde;

	public Dialog_BestellungenAnzeigen(Kunde kunde)
	{
		// this.bestellung = bestellung;
		this.kunde = kunde;
		initDialog_BestellungenAnzeigen();
		listBestellung.setCellRenderer(new BestellListRenderer());
		updateBestellListe();
	}

	public void updateBestellListe()
	{
		bestellModel.clear();
		if (kunde != null)
		{
			for (Bestellung b : kunde.getBestellungen())
			{
				bestellModel.addElement(b);

			}
		}
	}

	public void initDialog_BestellungenAnzeigen()
	{
		setBounds(100, 100, 735, 568);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBounds(0, 0, 699, 518);
		contentPanel.add(splitPane);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 80));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);

		JLabel lblKunde = new JLabel("Kunde:");
		lblKunde.setBounds(10, 11, 75, 14);
		panel.add(lblKunde);

		tfKunde = new JTextField();
		tfKunde.setFont(new Font("Tahoma", Font.BOLD, 11));
		tfKunde.setEditable(false);
		tfKunde.setBounds(93, 8, 370, 20);
		panel.add(tfKunde);
		tfKunde.setColumns(10);
		if (kunde != null)
		{
			tfKunde.setText(kunde.getNachname() + " " + kunde.getVorname());
		}

		JLabel lblDatum = new JLabel("Datum");
		lblDatum.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatum.setBounds(10, 55, 46, 14);
		panel.add(lblDatum);

		JLabel lblArtikelbezeichnung = new JLabel("Artikelbezeichnung");
		lblArtikelbezeichnung.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArtikelbezeichnung.setBounds(191, 55, 198, 14);
		panel.add(lblArtikelbezeichnung);

		JLabel lblAnzahl = new JLabel("Anzahl");
		lblAnzahl.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAnzahl.setBounds(428, 55, 46, 14);
		panel.add(lblAnzahl);

		listBestellung = new JList<Bestellung>(bestellModel);
		splitPane.setRightComponent(listBestellung);
	}
}
