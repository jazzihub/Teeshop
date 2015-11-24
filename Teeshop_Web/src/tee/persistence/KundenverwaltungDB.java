package tee.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;


import tee.fachlogik.Artikel;
import tee.fachlogik.Kunde;
import tee.fachlogik.Kundenverwaltung;
import tee.fachlogik.TeeException;

public class KundenverwaltungDB extends Kundenverwaltung
{
	private static EntityManager em;
	
	public KundenverwaltungDB() throws TeeException
	{
		em = EntityManager_Singelton.getInstance();
	}

	@Override
	public void kundeHinzufugen(Kunde kunde) throws TeeException
	{
		try
		{
			em.getTransaction().begin();
			if (!kundeDoppelt(kunde))
			{
				em.persist(kunde);
				JOptionPane
				.showMessageDialog(null, "Kunde gespeichert");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Kunde existiert bereits");
			}
			em.getTransaction().commit();
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"Kunde konnte nicht hinzugefügt werden");
		}
	}
	
	
	public static boolean kundeDoppelt(Kunde kunde) //geht alle Kunden durch
	{
		TypedQuery<Kunde> query = em.createNamedQuery("Kunde.doppelt", Kunde.class);
		query.setParameter("nachname", kunde.getNachname());
		query.setParameter("vorname", kunde.getVorname());
		query.setParameter("plz", kunde.getPlz());
		query.setParameter("strasse", kunde.getStrasse());
		
		return query.getResultList().size() > 0; 
	}


	@Override
	public void kundeEntfernen(Kunde kunde) throws TeeException
	{
		try
		{
			em.getTransaction().begin();
			
			em.remove(kunde);
			em.getTransaction().commit();
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"Kunde konnte nicht gelöscht werden");
		}
	}

	@Override
	public List<Kunde> getKunden()
	{
		TypedQuery<Kunde> query = em.createNamedQuery("Kunde.findAll",
				Kunde.class);
		List<Kunde> liste = query.getResultList();
		return liste;
	}

	@Override
	public Kunde kundeSuchen(int id) throws TeeException
	{
		if (id >= 0)
		{
			Kunde k = em.find(Kunde.class, id);
			//System.out.println(k);
			return k;
		} else
		{
			throw new TeeException("Bitte Kundennummer über 0 eingeben!");
		}
	}

}
