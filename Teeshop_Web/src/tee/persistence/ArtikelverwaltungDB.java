package tee.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import javax.swing.JOptionPane;

import tee.fachlogik.Artikel;
import tee.fachlogik.Artikelverwaltung;
import tee.fachlogik.TeeException;

public class ArtikelverwaltungDB extends Artikelverwaltung
{
	private EntityManager em;
//
	public ArtikelverwaltungDB() throws TeeException
	{
		em = EntityManager_Singelton.getInstance();
	
	}

	//Artikel hinzufügen
	@Override
	public void artikelHinzufugen(Artikel artikel)
	{
		try
		{
			em.getTransaction().begin();
			if (!em.contains(artikel))
			{
				em.persist(artikel);
			}
			em.getTransaction().commit();
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"Artikel konnte nicht hinzugefügt werden");
		}
	}

	//Artikel entfernen
	@Override
	public void artikelEntfernen(Artikel artikel) throws TeeException
	{
		try
		{
			em.getTransaction().begin();
			em.remove(artikel);
			em.getTransaction().commit();
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"Artikel konnte nicht gelöscht werden");
		}
	}

	//Artikel über Bezeichnung suchen
	@Override
	public List<Artikel> artikelSuchenBezeichnung(CharSequence bezeichnung)
			throws TeeException
	{
		List<Artikel> artikellist = getArtikel();
		List<Artikel> ergebnislist = new ArrayList<Artikel>();
		boolean suche = false;

		for (Artikel a : artikellist)
		{
			suche = a.getBezeichnung().toLowerCase().contains(bezeichnung);
			if (suche == true)
			{
				ergebnislist.add(a);
			}
		}

		if (ergebnislist.isEmpty())
		{
			throw new TeeException("Keine Artikel gefunden");
		}
		return ergebnislist;
	}

	//alle Artikel
	@Override
	public List<Artikel> getArtikel()
	{
		TypedQuery<Artikel> query = em.createNamedQuery("Artikel.findAll",
				Artikel.class);
		List<Artikel> liste = query.getResultList();
		return liste;
	}

	//Artikel über ID suchen
	@Override
	public Artikel artikelSuchen(int id) throws TeeException
	{
		if (id >= 0)
		{
			Artikel a = em.find(Artikel.class, id);
			return a;
		} else
		{
			throw new TeeException("Bitte Artikel-ID über 0 eingeben!");
		}
	}
	
	//Änderungen speichern
	@Override
	public void updateArtikel()
	{
		em.getTransaction().begin();
		System.out.println("geändert");
		em.getTransaction().commit();
	}
	
	

}
