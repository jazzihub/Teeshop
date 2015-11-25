package tee.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import tee.fachlogik.Artikel;
import tee.fachlogik.Artikelverwaltung;
import tee.fachlogik.Kunde;
import tee.fachlogik.TeeException;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dialog_ArtikelAnlegen extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JTextField tfBezeichnung;
	private JTextField tfNettoPreis;
	private Artikelverwaltung artikelverwaltung;
	private JTextField tfLagerstand;
	private Panel_Artikel artikelPanel;
	private JComboBox cBMwst;
	private TextOhneZahl toz;
	private JTextField tfGramm;

	public Dialog_ArtikelAnlegen(Artikelverwaltung av, Panel_Artikel ap)
	{
		setPreferredSize(new Dimension(599, 354));
		setTitle("Neuer Artikel");
		this.artikelverwaltung = av;
		this.artikelPanel = ap;
		initDialogArtikelAnlegen();
	}

	public void initDialogArtikelAnlegen()
	{
		
		setBounds(100, 100, 598, 354);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNeuerArtikel = new JLabel("Neuer Artikel");
			lblNeuerArtikel.setForeground(new Color(95, 158, 160));
			lblNeuerArtikel.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNeuerArtikel.setBounds(29, 25, 160, 20);
			contentPanel.add(lblNeuerArtikel);
		}
		{
			JLabel lblBezeichnung = new JLabel("Bezeichnung:");
			lblBezeichnung.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblBezeichnung.setForeground(new Color(0, 0, 0));
			lblBezeichnung.setBounds(29, 87, 95, 14);
			contentPanel.add(lblBezeichnung);
		}
		{
			JLabel lblNettopreis = new JLabel("Nettopreis:");
			lblNettopreis.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNettopreis.setBounds(29, 155, 95, 14);
			contentPanel.add(lblNettopreis);
		}
		{
			JLabel lblMwst = new JLabel("MWSt:");
			lblMwst.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblMwst.setBounds(337, 155, 67, 14);
			contentPanel.add(lblMwst);
		}

		tfBezeichnung = new JTextField();
		tfBezeichnung.setBounds(148, 81, 371, 20);
		contentPanel.add(tfBezeichnung);
		tfBezeichnung.setColumns(10);

		tfNettoPreis = new JTextField();
		tfNettoPreis.setBounds(148, 153, 95, 20);
		contentPanel.add(tfNettoPreis);
		tfNettoPreis.setColumns(10);

		JLabel lblEuro = new JLabel("Euro");
		lblEuro.setBounds(245, 156, 46, 14);
		contentPanel.add(lblEuro);

		cBMwst = new JComboBox();
		cBMwst.setModel(new DefaultComboBoxModel(new String[] { "10", "20" }));
		cBMwst.setBounds(414, 153, 59, 20);
		contentPanel.add(cBMwst);
		{
			JLabel lblProzent = new JLabel("%");
			lblProzent.setBounds(473, 156, 46, 14);
			contentPanel.add(lblProzent);
		}
		{
			JLabel lblLagerstand = new JLabel("Lagerstand:");
			lblLagerstand.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblLagerstand.setBounds(29, 219, 95, 14);
			contentPanel.add(lblLagerstand);
		}
		{
			tfLagerstand = new JTextField();
			tfLagerstand.setBounds(148, 217, 86, 20);
			contentPanel.add(tfLagerstand);
			tfLagerstand.setColumns(10);
		}
		
		JLabel lblGewicht = new JLabel("Gewicht:");
		lblGewicht.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGewicht.setBounds(337, 220, 67, 14);
		contentPanel.add(lblGewicht);
		
		tfGramm = new JTextField();
		tfGramm.setBounds(414, 217, 86, 20);
		contentPanel.add(tfGramm);
		tfGramm.setColumns(10);
		
		JLabel lblGramm = new JLabel("gramm");
		lblGramm.setBounds(505, 220, 46, 14);
		contentPanel.add(lblGramm);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnSpeichern = new JButton("Speichern");
				btnSpeichern.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						do_btnSpeichern_actionPerformed(e);
					}
				});
				btnSpeichern.setActionCommand("");
				buttonPane.add(btnSpeichern);
				getRootPane().setDefaultButton(btnSpeichern);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						do_cancelButton_actionPerformed(e);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void do_btnSpeichern_actionPerformed(ActionEvent e)
	{

		String bezeichnung;
		double preis;
		int lagerstand;
		double mwst;	
		
		
		Artikel artikel = null;
		toz = new TextOhneZahl();
		bezeichnung = tfBezeichnung.getText();
		boolean b = toz.isAlpha(bezeichnung);

		preis = 0;
		lagerstand = 0;
		mwst = 0;
		
		if(bezeichnung.length() == 0)
		{
			JOptionPane.showMessageDialog(this, "Feld 'Artikelbezeichnung' ist ein Pflichtfeld");
			return;
		}

		if (b == true)
		{
			try
			{
				double gramm = Double.parseDouble(tfGramm.getText());
				preis = Double.parseDouble(tfNettoPreis.getText());

				String steuer = (String) cBMwst.getSelectedItem();
				mwst = Double.parseDouble(steuer);
				
				try
				{
					lagerstand = Integer.parseInt(tfLagerstand.getText());
					try
					{
						artikel = new Artikel(bezeichnung, gramm, preis, mwst,
								lagerstand);
						artikelverwaltung.artikelHinzufugen(artikel);
						artikelPanel.updateArtikelListe();
						JOptionPane.showMessageDialog(this,
								"Artikel gespeichert");
						dispose();

					} catch (Exception se)
					{
						JOptionPane.showMessageDialog(this, se.getMessage());
					}

				} catch (NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(this,
							"Bei Lagerstand ganze Zahlen eingeben!");
				}

			} catch (NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(this,
						"Bitte bei Preis Zahlen im Format 00.0 eingeben");
			}

		} else
			JOptionPane.showMessageDialog(this,
					"Bei der Bezeichnung nur Buchstaben eingeben!");

	}
	
//	 private void txtEingabeAMouseClicked(java.awt.event.MouseEvent evt) {                                         
//	        // TODO add your handling code here:
//	        txtEingabeA.setText("");
//	        txtEingabeA.setForeground(new java.awt.Color(0,0,0));
//	        txtEingabeA.setFont(new java.awt.Font("Tahoma", 0, 12)); 
//	    }     

	protected void do_cancelButton_actionPerformed(ActionEvent e)
	{
		dispose();
	}
}
