package tee.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tee.fachlogik.Artikel;
import tee.fachlogik.Kunde;
import tee.fachlogik.Kundenverwaltung;
import tee.fachlogik.TeeException;

import javax.swing.JSplitPane;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import jdk.nashorn.internal.runtime.ListAdapter;
import javax.swing.ListSelectionModel;

public class Dialog_KundenAnzeigen extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private final Kundenverwaltung kv;
	
	private DefaultListModel<Kunde> kundenModel = new DefaultListModel<>();
	private JList<Kunde> listKunden;

	
	public Dialog_KundenAnzeigen(Kundenverwaltung kv)
	{
		setMaximumSize(new Dimension(735, 568));
		setMinimumSize(new Dimension(735, 568));
		this.kv = kv;
		initDialog_KundenAnzeigen();	
		listKunden.setCellRenderer(new KundenListRenderer());
		updateKundenListe();
		
	}
	
	public void updateKundenListe()
	{
		// Liste zuerst leeren, sonst werden Kunden doppelt angezeigt
		kundenModel.clear();
		for (Kunde k : kv.getKunden())
		{
			kundenModel.addElement(k);
			
		}
	}
	
	public void initDialog_KundenAnzeigen()
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
		
		JButton btnKundeLschen = new JButton("Kunde l\u00F6schen");
		btnKundeLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					do_btnKundeLschen_actionPerformed(e);
				} catch (TeeException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnKundeLschen.setBounds(10, 11, 152, 23);
		panel.add(btnKundeLschen);
		
		JLabel lblNr = new JLabel("Nr.");
		lblNr.setBounds(10, 55, 46, 14);
		panel.add(lblNr);
		
		JLabel lblNachname = new JLabel("Nachname");
		lblNachname.setBounds(136, 55, 81, 14);
		panel.add(lblNachname);
		
		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setBounds(274, 55, 81, 14);
		panel.add(lblVorname);
		
		JLabel lblPlz = new JLabel("PLZ");
		lblPlz.setBounds(418, 55, 46, 14);
		panel.add(lblPlz);
		
		JLabel lblOrt = new JLabel("Ort");
		lblOrt.setBounds(548, 55, 75, 14);
		panel.add(lblOrt);
		
		listKunden = new JList<Kunde>(kundenModel);
		listKunden.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		splitPane.setRightComponent(listKunden);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollBar.setBounds(696, 51, 17, 462);
		contentPanel.add(scrollBar);
	}
	protected void do_btnKundeLschen_actionPerformed(ActionEvent e) throws TeeException {
		
		Kunde k = listKunden.getSelectedValue();
		kv.kundeEntfernen(k);
		updateKundenListe();
		//JOptionPane.showMessageDialog(this, "Kunde gelöscht");
	}
	
}
