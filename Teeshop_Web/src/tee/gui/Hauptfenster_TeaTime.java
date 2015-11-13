package tee.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tee.fachlogik.Artikel;
import tee.fachlogik.Artikelverwaltung;
import tee.fachlogik.Bestellung;
import tee.fachlogik.Kunde;
import tee.fachlogik.Kundenverwaltung;
import tee.fachlogik.TeeException;
import tee.persistence.BestellungenDB;

public class Hauptfenster_TeaTime extends JFrame
{

	private JPanel contentPane;
	private JPanel panel;
	private Artikelverwaltung av;
	private Panel_Artikel panel_Artikel;
	private JTextField tfArtikelSuchen;
	private Kundenverwaltung kv;
	private JTextField tfKundenNr;
	private BestellungenDB bestellung;

	public Hauptfenster_TeaTime(Artikelverwaltung av, Kundenverwaltung kv, BestellungenDB b)
	{
		setPreferredSize(new Dimension(2100, 800));
		setMaximumSize(new Dimension(1500, 800));
		setTitle("Tea Time");
		this.av = av;
		this.kv = kv;
		this.bestellung = b;
		initHauptfenster_TeaTime();
	}

	public void initHauptfenster_TeaTime()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 629);

		setMinimumSize(new Dimension(1200, 760));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		panel_Artikel = new Panel_Artikel(av);
		splitPane.setRightComponent(panel_Artikel);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 700));
		panel.setMinimumSize(new Dimension(200, 700));
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);

		JButton btnArtikelLschen = new JButton("Artikel l\u00F6schen");
		btnArtikelLschen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					do_btnArtikelLschen_actionPerformed(e);
				} catch (TeeException e1)
				{
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnArtikelLschen.setBounds(28, 110, 146, 23);
		panel.add(btnArtikelLschen);

		JButton btnArtikelAnlegen = new JButton("Artikel anlegen");
		btnArtikelAnlegen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				do_btnArtikelAnlegen_actionPerformed(e);
			}
		});
		btnArtikelAnlegen.setBounds(28, 59, 146, 23);
		panel.add(btnArtikelAnlegen);

		JButton btnSuchenStart = new JButton("Suche starten");
		btnSuchenStart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					do_btnSuchenStart_actionPerformed(e);
				} catch (TeeException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSuchenStart.setBounds(28, 288, 146, 23);
		panel.add(btnSuchenStart);

		JLabel lblArtikelSuchen = new JLabel("Artikel suchen:");
		lblArtikelSuchen.setBounds(28, 233, 146, 14);
		panel.add(lblArtikelSuchen);

		tfArtikelSuchen = new JTextField();
		tfArtikelSuchen.setToolTipText("Bezeichnung eingeben");
		tfArtikelSuchen.setBounds(28, 258, 146, 20);
		panel.add(tfArtikelSuchen);
		tfArtikelSuchen.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 336, 180, 2);
		panel.add(separator_1);
		
		JButton btnAlleArtikel = new JButton("Alle Artikel");
		btnAlleArtikel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnAlleArtikel_actionPerformed(e);
			}
		});
		btnAlleArtikel.setBounds(28, 166, 146, 23);
		panel.add(btnAlleArtikel);
		
		JButton btnKundenAnlegen = new JButton("Kunde anlegen");
		btnKundenAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnKundenAnlegen_actionPerformed(e);
			}
		});
		btnKundenAnlegen.setBounds(28, 401, 146, 23);
		panel.add(btnKundenAnlegen);
		
		JLabel lblArtikel = new JLabel("Artikel:");
		lblArtikel.setBounds(10, 11, 46, 14);
		panel.add(lblArtikel);
		
		JLabel lblKunden = new JLabel("Kunden:");
		lblKunden.setBounds(10, 358, 46, 14);
		panel.add(lblKunden);
		
		JButton btnKundenAnzeigen = new JButton("Kunden anzeigen");
		btnKundenAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnKundenAnzeigen_actionPerformed(e);
			}
		});
		btnKundenAnzeigen.setBounds(28, 439, 146, 23);
		panel.add(btnKundenAnzeigen);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 497, 180, 2);
		panel.add(separator);
		
		JLabel lblBestellungen = new JLabel("Bestellungen:");
		lblBestellungen.setBounds(10, 525, 125, 14);
		panel.add(lblBestellungen);
		
		JButton btnBestellungenAnzeigen = new JButton("Bestellungen anzeigen");
		btnBestellungenAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					do_btnBestellungenAnzeigen_actionPerformed(e);
				} catch (TeeException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnBestellungenAnzeigen.setBounds(10, 613, 180, 23);
		panel.add(btnBestellungenAnzeigen);
		
		JLabel lblKundenNr = new JLabel("Kunden Nr:");
		lblKundenNr.setBounds(28, 575, 81, 14);
		panel.add(lblKundenNr);
		
		tfKundenNr = new JTextField();
		tfKundenNr.setToolTipText("Kundennummer eingeben");
		tfKundenNr.setBounds(128, 572, 46, 20);
		panel.add(tfKundenNr);
		tfKundenNr.setColumns(10);
	}

	
	//Artikel anlegen
	protected void do_btnArtikelAnlegen_actionPerformed(ActionEvent e)
	{
		Dialog_ArtikelAnlegen dialog = new Dialog_ArtikelAnlegen(av,
				panel_Artikel);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	//Artikel löschen
	protected void do_btnArtikelLschen_actionPerformed(ActionEvent e)
			throws TeeException
	{
		String nr;
		int nummer = 0;
		Artikel artikel = null;

		try
		{
			nr = JOptionPane.showInputDialog(null,
					"Geben Sie die Nummer des Artikels ein");
			nummer = Integer.parseInt(nr);
			artikel = av.artikelSuchen(nummer);

			if (artikel == null)
			{
				throw new TeeException("Kein Artikel gefunden");

			} else
			{

				try
				{

					int reply = JOptionPane.showConfirmDialog(null,
							"Artikel wirklich löschen?", "Confirm",
							JOptionPane.YES_NO_OPTION);

					if (reply == JOptionPane.YES_OPTION)
					{
						av.artikelEntfernen(artikel);
						panel_Artikel.updateArtikelListe();
						JOptionPane
								.showMessageDialog(this, "Artikel gelöscht!");

					} else if (reply == JOptionPane.NO_OPTION)
					{
						return;
					}

				} catch (TeeException e1)
				{
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}

			}
		} catch (NumberFormatException e2)
		{
			JOptionPane.showMessageDialog(this, "Kein Artikel gelöscht");
		}
	}

	//Artikel über Bezeichnung suchen
	protected void do_btnSuchenStart_actionPerformed(ActionEvent e) throws TeeException
	{
		CharSequence suchtext = tfArtikelSuchen.getText();
		if(suchtext == "") //?
		{
			throw new TeeException("Bitte Artikelbezeichnung eingeben!");
		}
			
		List<Artikel> suchergebnis = new ArrayList<Artikel>();
		try
		{
			suchergebnis = av.artikelSuchenBezeichnung(suchtext);
			DefaultListModel<Artikel> am = panel_Artikel.getArtikelModel();
			am.clear();

			for (Artikel a : suchergebnis)
				am.addElement(a);
			
		} catch (TeeException e1)
		{
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}

	}
	
	//alle Artikel
	protected void do_btnAlleArtikel_actionPerformed(ActionEvent e) 
	{
		panel_Artikel.updateArtikelListe();
	}
	
	//Kunden anlegen
	protected void do_btnKundenAnlegen_actionPerformed(ActionEvent e) {
		
		Dialog_KundeAnlegen dialog = new Dialog_KundeAnlegen(kv);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	//Kunden anzeigen
	protected void do_btnKundenAnzeigen_actionPerformed(ActionEvent e) {
		Dialog_KundenAnzeigen dialog = new Dialog_KundenAnzeigen(kv);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	//Bestelungen anzeigen
	protected void do_btnBestellungenAnzeigen_actionPerformed(ActionEvent e) throws TeeException {
		
		int kundenNr = Integer.parseInt(tfKundenNr.getText());		
		Kunde k;
		try
		{
			k = kv.kundeSuchen(kundenNr);
			if(k != null)
			{
			Dialog_BestellungenAnzeigen dialog = new Dialog_BestellungenAnzeigen(k);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Keine Bestellungen gefunden");
			}
		} catch (TeeException e1)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
		
	
	}
}
