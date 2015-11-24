package start.teaTime;

import tee.fachlogik.Artikel;
import tee.fachlogik.Artikelverwaltung;
import tee.fachlogik.Bestellung;
import tee.fachlogik.Kunde;
import tee.fachlogik.Kundenverwaltung;
import tee.fachlogik.TeeException;
import tee.persistence.ArtikelverwaltungDB;
import tee.persistence.BestellungenDB;
import tee.persistence.KundenverwaltungDB;

public class Test
{

	public static void main(String[] args) throws TeeException
	{
		
		Artikelverwaltung av = new ArtikelverwaltungDB();
		Kundenverwaltung kv = new KundenverwaltungDB();		
		
		Kunde k1 = new Kunde("Yvonne","Huber","Laas", "32", 9640, "Kötschach");
		Artikel a1 = new Artikel("TestTeeTeeblabla", 100.0, 10.0, 200);
		
		kv.kundeHinzufugen(k1);
		av.artikelHinzufugen(a1);
		
		BestellungenDB b = new BestellungenDB();
		Bestellung b1 = new Bestellung(k1, a1, 10);
		b.bestellungHinzufügen(b1);
		
//		b.bestellungEntfernen(b1);

	}

}
