package start.teaTime;

import java.awt.EventQueue;

import tee.fachlogik.Artikel;
import tee.fachlogik.Artikelverwaltung;
import tee.fachlogik.Bestellung;
import tee.fachlogik.Kundenverwaltung;
import tee.fachlogik.TeeException;
import tee.gui.Hauptfenster_TeaTime;
import tee.persistence.ArtikelverwaltungDB;
import tee.persistence.BestellungenDB;
import tee.persistence.KundenverwaltungDB;

public class start
{
	
	public static void main(String[] args) throws TeeException
	{
		
		Artikelverwaltung av = new ArtikelverwaltungDB();
		Kundenverwaltung kv = new KundenverwaltungDB();
		BestellungenDB b = new BestellungenDB();
//		Artikel a1 = new Artikel("Grüntee", 10, 10, 20);
//		Artikel a2 = new Artikel("Schwarztee", 20,20,20);
//		Artikel a3 = new Artikel("Wart", 30,10,40);
//		
//		
//		av.artikelHinzufugen(a1);
//		av.artikelHinzufugen(a2);
//		av.artikelHinzufugen(a3);
//			
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Hauptfenster_TeaTime frame = new Hauptfenster_TeaTime(av,kv,b);
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		
	}
}
