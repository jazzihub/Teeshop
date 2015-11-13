package tee.gui;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JList;

import java.awt.Dimension;

import javax.swing.JScrollBar;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import tee.fachlogik.Artikel;
import tee.fachlogik.Artikelverwaltung;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;

public class Panel_Artikel extends JPanel
{
	private Artikelverwaltung artikelverwaltung;
	private DefaultListModel<Artikel> artikelModel = new DefaultListModel<>();
	private JList<Artikel> listArtikel;

	public Panel_Artikel(Artikelverwaltung av)
	{
		setMinimumSize(new Dimension(894, 685));
		artikelverwaltung = av;
		initPanel_Artikel();
		listArtikel.setCellRenderer(new ArtikelListRenderer());

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(listArtikel, popupMenu);

		JMenuItem mntmBearbeiten = new JMenuItem("Bearbeiten");
		mntmBearbeiten.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				do_mntmBearbeiten_actionPerformed(e);
			}
		});
		popupMenu.add(mntmBearbeiten);
		updateArtikelListe();

	}

	public DefaultListModel<Artikel> getArtikelModel()
	{
		return artikelModel;
	}

	public void initPanel_Artikel()
	{
		setPreferredSize(new Dimension(894, 685));
		setLayout(null);

		JLabel lblArtikel = new JLabel("Artikel");
		lblArtikel.setBounds(10, 11, 113, 19);
		lblArtikel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblArtikel.setForeground(new Color(30, 144, 255));
		lblArtikel.setBackground(Color.LIGHT_GRAY);
		add(lblArtikel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 71, 853, 2);
		add(separator);

		JLabel lblNr = new JLabel("Nr.");
		lblNr.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNr.setBounds(10, 46, 46, 14);
		add(lblNr);

		JLabel lblBezeichnung = new JLabel("Bezeichnung");
		lblBezeichnung.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBezeichnung.setBounds(177, 46, 103, 14);
		add(lblBezeichnung);

		JLabel lblPreisNetto = new JLabel("Preis netto");
		lblPreisNetto.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPreisNetto.setBounds(347, 46, 94, 14);
		add(lblPreisNetto);

		JLabel lblMwst = new JLabel("MwSt");
		lblMwst.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMwst.setBounds(521, 46, 46, 14);
		add(lblMwst);

		JLabel lblLagerbestand = new JLabel("Lagerbestand");
		lblLagerbestand.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLagerbestand.setBounds(681, 46, 119, 14);
		add(lblLagerbestand);

		listArtikel = new JList<Artikel>(artikelModel);
		listArtikel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listArtikel.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				do_listArtikel_valueChanged(e);
			}
		});
		listArtikel.setBounds(10, 84, 853, 590);
		add(listArtikel);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollBar.setBounds(866, 84, 17, 590);
		add(scrollBar);
	}

	public void updateArtikelListe()
	{
		// Liste zuerst leeren, sonst werden Kunden doppelt angezeigt
		artikelModel.clear();
		for (Artikel a : artikelverwaltung.getArtikel())
		{
			artikelModel.addElement(a);
			
		}
	}

	protected void do_listArtikel_valueChanged(ListSelectionEvent e)
	{

	}

	private static void addPopup(Component component, final JPopupMenu popup)
	{
		component.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (e.isPopupTrigger())
				{
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e)
			{
				if (e.isPopupTrigger())
				{
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e)
			{
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	protected void do_mntmBearbeiten_actionPerformed(ActionEvent e)
	{

		if (!listArtikel.isSelectionEmpty())
		{
			// schaun, ob etwas ausgewählt ist von der Liste, bevor Fenster
			// geöffnet wird, sonst Fehlermeldung
			Artikel a = listArtikel.getSelectedValue();
			Dialog_ArtikelBearbeiten dialog = new Dialog_ArtikelBearbeiten(a, artikelverwaltung, this);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} else
		{
			JOptionPane.showMessageDialog(this, "Artikel aus der Liste wählen");
		}
	}
}
