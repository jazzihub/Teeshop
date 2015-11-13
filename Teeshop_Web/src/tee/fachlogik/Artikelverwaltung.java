package tee.fachlogik;

import java.util.ArrayList;
import java.util.List;

public class Artikelverwaltung
{
	private List<Artikel> artikelListe;

	public Artikelverwaltung()
	{
		artikelListe = new ArrayList<Artikel>();
	}

	public void artikelHinzufugen(Artikel artikel) throws TeeException
	{
		if (artikel != null)
		{
			for (Artikel a : artikelListe)
			{
				if (a.getBezeichnung().equals(artikel.getBezeichnung()))
				{
					throw new TeeException(
							"Artikel mit dieser Bezeichnung existiert bereits");
				}
			}
			artikelListe.add(artikel);
		} else
			throw new TeeException("Kein Artikel");
	}

	public void artikelEntfernen(Artikel artikel) throws TeeException
	{
		if (artikel != null)
		{
			if (artikelListe.remove(artikel) == false)
			{
				throw new TeeException("Artikel " + artikel
						+ " wurde nicht gelöscht");
			}

		} else
			throw new TeeException("Kein Artikel");

	}

	public List<Artikel> artikelSuchenBezeichnung(CharSequence bezeichnung)
			throws TeeException
	{
		List<Artikel> suchergebnis = new ArrayList<Artikel>();

		boolean suche = false;

		if (!bezeichnung.equals(null))
		{
			for (Artikel a : artikelListe)
			{

				suche = a.getBezeichnung().toLowerCase().contains(bezeichnung);
				if (suche == true)
				{
					suchergebnis.add(a);
				}
			}
		} else
		{
			throw new TeeException("Bitte Artikelbezeichnung eingeben!");
		}

		if (suchergebnis.isEmpty())
		{
			throw new TeeException("Kein Ergebnis!");
		}

		return suchergebnis;
	}

	public List<Artikel> getArtikel()
	{
		return artikelListe;
	}

	public Artikel artikelSuchen(int id) throws TeeException
	{
		Artikel artikel = null;
		for (Artikel a : artikelListe)
		{
			if (a.getId() == id)
			{
				return a;
			}
		}
		return artikel;

	}
	
	public void updateArtikel()
	{
		
	}
}
