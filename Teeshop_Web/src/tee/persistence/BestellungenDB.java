package tee.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import tee.fachlogik.Artikel;
import tee.fachlogik.Bestellung;
import tee.fachlogik.Kunde;
import tee.fachlogik.TeeException;

public class BestellungenDB
{

	private EntityManager em;
	
	public BestellungenDB() throws TeeException
	{
		em = EntityManager_Singelton.getInstance(); 
	}

	public static List<Bestellung> vonKunde(Kunde kunde)
	{
		return kunde.getBestellungen();
	}
	
	public static List<Bestellung> vonKundeAm(Kunde kunde, Date datum)
	{
		return null;
	}
	
	public void bestellungHinzufügen(Bestellung b)
	{
		try
		{
			em.getTransaction().begin();
			if (!em.contains(b))
			{
				em.persist(b);
			}
			em.getTransaction().commit();
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"Bestellung konnte nicht hinzugefügt werden");
		}
	}
	
	public void bestellungEntfernen(Bestellung b)
	{
		try
		{
			em.getTransaction().begin();
			em.remove(b);
			em.getTransaction().commit();
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"Bestellung konnte nicht gelöscht werden");
		}
	}

}
