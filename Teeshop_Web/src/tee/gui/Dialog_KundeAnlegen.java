package tee.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tee.fachlogik.Artikel;
import tee.fachlogik.Kunde;
import tee.fachlogik.Kundenverwaltung;
import tee.fachlogik.TeeException;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class Dialog_KundeAnlegen extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private Kundenverwaltung kv;
	private JTextField tfVorname;
	private JTextField tfNachname;
	private JTextField tfStrasse;
	private JTextField tfPlz;
	private JTextField tfWohnort;
	private JTextField tfHausnummer;
	private TextOhneZahl toz;
	

	public Dialog_KundeAnlegen(Kundenverwaltung kv)
	{
		setMaximumSize(new Dimension(600, 358));
		setMinimumSize(new Dimension(600, 358));
		this.kv = kv;
		initDialog_KundeAnlegen();
	}

	public void initDialog_KundeAnlegen()
	{
		setBounds(100, 100, 600, 357);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setBounds(100, 100, 598, 354);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setLayout(null);
		{
			JLabel lblNeuerKunde = new JLabel("Neuer Kunde");
			lblNeuerKunde.setForeground(new Color(100, 149, 237));
			lblNeuerKunde.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNeuerKunde.setBounds(10, 23, 120, 14);
			contentPanel.add(lblNeuerKunde);
		}
		{
			JLabel lblVorname = new JLabel("Vorname:");
			lblVorname.setBounds(10, 80, 80, 14);
			contentPanel.add(lblVorname);
		}
		{
			JLabel lblNachname = new JLabel("Nachname:");
			lblNachname.setBounds(10, 113, 80, 14);
			contentPanel.add(lblNachname);
		}
		{
			JLabel lblStrae = new JLabel("Stra\u00DFe:");
			lblStrae.setBounds(10, 150, 58, 14);
			contentPanel.add(lblStrae);
		}
		{
			JLabel lblHausnr = new JLabel("Hausnr:");
			lblHausnr.setBounds(347, 150, 58, 14);
			contentPanel.add(lblHausnr);
		}
		{
			JLabel lblPlz = new JLabel("PLZ:");
			lblPlz.setBounds(10, 192, 46, 14);
			contentPanel.add(lblPlz);
		}
		{
			JLabel lblWohnort = new JLabel("Wohnort:");
			lblWohnort.setBounds(194, 192, 68, 14);
			contentPanel.add(lblWohnort);
		}

		tfVorname = new JTextField();
		tfVorname.setBounds(86, 77, 216, 20);
		contentPanel.add(tfVorname);
		tfVorname.setColumns(10);

		tfNachname = new JTextField();
		tfNachname.setBounds(86, 110, 216, 20);
		contentPanel.add(tfNachname);
		tfNachname.setColumns(10);

		tfStrasse = new JTextField();
		tfStrasse.setBounds(86, 147, 216, 20);
		contentPanel.add(tfStrasse);
		tfStrasse.setColumns(10);

		tfPlz = new JTextField();
		tfPlz.setBounds(86, 189, 86, 20);
		contentPanel.add(tfPlz);
		tfPlz.setColumns(10);

		tfWohnort = new JTextField();
		tfWohnort.setBounds(280, 189, 224, 20);
		contentPanel.add(tfWohnort);
		tfWohnort.setColumns(10);

		tfHausnummer = new JTextField();
		tfHausnummer.setBounds(418, 147, 86, 20);
		contentPanel.add(tfHausnummer);
		tfHausnummer.setColumns(10);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnKundeSpeichern = new JButton("Speichern");
				btnKundeSpeichern.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							do_btnKundeSpeichern_actionPerformed(e);
						} catch (TeeException e1)
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnKundeSpeichern.setActionCommand("OK");
				buttonPane.add(btnKundeSpeichern);
				getRootPane().setDefaultButton(btnKundeSpeichern);
			}
			{
				JButton btnKundenAnlegenAbbrechen = new JButton("Abbechen");
				btnKundenAnlegenAbbrechen
						.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								do_btnKundenAnlegenAbbrechen_actionPerformed(e);
							}
						});
				btnKundenAnlegenAbbrechen.setActionCommand("Cancel");
				buttonPane.add(btnKundenAnlegenAbbrechen);
			}
		}
	}

	protected void do_btnKundeSpeichern_actionPerformed(ActionEvent e) throws TeeException
	{

		Kunde kunde = null;

		toz = new TextOhneZahl();
		String vorname = tfVorname.getText();
		boolean vn = toz.isAlpha(vorname);
		String nachname = tfNachname.getText();
		boolean nn = toz.isAlpha(nachname);

		String strasse = tfStrasse.getText();
		String hnr = tfHausnummer.getText();
		String ort = tfWohnort.getText();
		boolean wo = toz.isAlpha(ort);
		int plz = 0;
		
		if(vorname.length() == 0 || nachname.length() == 0 || strasse.length() == 0 || ort.length() == 0)
		{
			JOptionPane.showMessageDialog(this, "Alle Felder müssen ausgefüllt werden!");
			return;
		}
		

		if (vn == true && nn == true && wo == true)
		{
			try
			{
				if (tfPlz.getText().length() == 4
						|| tfPlz.getText().length() == 5)
				{
					plz = Integer.parseInt(tfPlz.getText());
				} 
				else
				{
					JOptionPane.showMessageDialog(this, "PLZ muss 4- bzw. 5-stellig sein");
					return;
				}

					try
					{
						kunde = new Kunde(vorname, nachname, strasse, hnr, plz,
								ort);
						kv.kundeHinzufugen(kunde);
						dispose();
						

					} catch (TeeException se)
					{
						JOptionPane.showMessageDialog(this, se.getMessage());
					}
				

			} catch (NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(this,
						"Bitte bei PLZ Zahlen eingeben");
			}

		} else
			JOptionPane.showMessageDialog(this,
					"Bei Vornamen, Nachnamen und Wohnort nur Buchstaben eingeben!");
	}

	protected void do_btnKundenAnlegenAbbrechen_actionPerformed(ActionEvent e)
	{
		dispose();
	}
}
