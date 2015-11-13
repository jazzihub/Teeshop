package tee.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import tee.fachlogik.Artikel;
import tee.fachlogik.Artikelverwaltung;
import tee.fachlogik.TeeException;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dialog_ArtikelBearbeiten extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private Artikelverwaltung artikelverwaltung;
	private Panel_Artikel artikelpanel;
	private Artikel artikel;
	private JTextField tfArtikelbezeichnung;
	private JTextField tfPreisNetto;
	private JTextField tfLagerstand;
	private JComboBox cBMwst;
	private TextOhneZahl toz;

	public Dialog_ArtikelBearbeiten(Artikel a, Artikelverwaltung av, Panel_Artikel ap)
	{
		setMaximumSize(new Dimension(610, 315));
		setTitle("Artikel bearbeiten");
		this.artikel = a;
		artikelpanel = ap;
		artikelverwaltung = av;
		initDialog_ArtikelBearbeiten();
		anzeigenFelder();
	}

	public void anzeigenFelder()
	{
		tfArtikelbezeichnung.setText(artikel.getBezeichnung());
		tfPreisNetto.setText(Double.toString(artikel.getPreisNetto()));
		cBMwst.getModel().setSelectedItem(Double.toString(artikel.getUst()));
		tfLagerstand.setText(Integer.toString(artikel.getLagerstand()));
	}

	protected void do_okButton_actionPerformed(ActionEvent e)
	{
		
		toz = new TextOhneZahl();
		boolean b = toz.isAlpha(tfArtikelbezeichnung.getText());
		double mwst = 0;

		if (b == true)
		{
			try
			{
				artikel.setBezeichnung(tfArtikelbezeichnung.getText());
				artikel.setPreisNetto(Double.parseDouble(tfPreisNetto.getText()));

				String steuer = (String) cBMwst.getSelectedItem();
				mwst = Double.parseDouble(steuer);
				artikel.setUst(mwst);
				
				try
				{

					artikel.setLagerstand(Integer.parseInt(tfLagerstand.getText()));;
					try
					{
						artikelpanel.updateArtikelListe();
						artikelverwaltung.updateArtikel();
						JOptionPane.showMessageDialog(this, "Änderungen gespeichert");
			
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

			dispose();
		} else
			JOptionPane.showMessageDialog(this,
					"Bei der Bezeichnung nur Buchstaben eingeben!");
		
	}

	public void initDialog_ArtikelBearbeiten()
	{
		setBounds(100, 100, 601, 313);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblArtikelbezeichnung = new JLabel("Artikelbezeichnung:");
			lblArtikelbezeichnung.setBounds(22, 40, 138, 14);
			contentPanel.add(lblArtikelbezeichnung);
		}
		{
			JLabel lblPreisNetto = new JLabel("Preis netto:");
			lblPreisNetto.setBounds(22, 91, 93, 14);
			contentPanel.add(lblPreisNetto);
		}
		{
			JLabel lblMwst = new JLabel("MwSt:");
			lblMwst.setBounds(22, 140, 46, 14);
			contentPanel.add(lblMwst);
		}
		{
			JLabel lblLagerstand = new JLabel("Lagerstand:");
			lblLagerstand.setBounds(22, 189, 93, 14);
			contentPanel.add(lblLagerstand);
		}

		tfArtikelbezeichnung = new JTextField();
		tfArtikelbezeichnung.setBounds(147, 37, 360, 20);
		contentPanel.add(tfArtikelbezeichnung);
		tfArtikelbezeichnung.setColumns(10);

		tfPreisNetto = new JTextField();
		tfPreisNetto.setBounds(147, 88, 130, 20);
		contentPanel.add(tfPreisNetto);
		tfPreisNetto.setColumns(10);

		cBMwst = new JComboBox();
		cBMwst.setModel(new DefaultComboBoxModel(new String[] { "10", "20" }));
		cBMwst.setBounds(147, 137, 78, 20);
		contentPanel.add(cBMwst);

		tfLagerstand = new JTextField();
		tfLagerstand.setBounds(147, 186, 86, 20);
		contentPanel.add(tfLagerstand);
		tfLagerstand.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u00C4nderungen speichern");
				okButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						do_okButton_actionPerformed(e);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Abbrechen");
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

	protected void do_cancelButton_actionPerformed(ActionEvent e)
	{
		dispose();
	}

}
