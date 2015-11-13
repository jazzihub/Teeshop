package tee.fachlogik;

import java.util.ArrayList;
import java.util.List;

public class Kundenverwaltung
{
	private List<Kunde> kundenliste;

	public Kundenverwaltung()
	{
		kundenliste = new ArrayList<Kunde>();
	}

	public void kundeHinzufugen(Kunde kunde) throws TeeException
	{
		if (kunde != null)
		{
			for (Kunde k : kundenliste)
			{
				if (k.equals(kunde))
				{
					throw new TeeException("Kunde existiert bereits");
				}
			}
			kundenliste.add(kunde);
		} else
			throw new TeeException("Kein Kunde");
	}

	public void kundeEntfernen(Kunde kunde) throws TeeException
	{
		if (kunde != null)
		{

			if (kundenliste.remove(kunde) == false)
			{
				throw new TeeException("Kunde wurde nicht gelöscht");
			}

		} else
			throw new TeeException("Kein Kunde!");

	}

	public List<Kunde> getKunden()
	{
		return kundenliste;
	}

	public Kunde kundeSuchen(int nr) throws TeeException
	{
		Kunde kunde = null;
		for (Kunde k : kundenliste)
		{
			if (k.getId() == nr)
			{
				return k;
			}
			else
			{
				throw new TeeException("Zu diesem Kunden gibt es keine Bestellungen");
			}
		}
		return kunde;
	}
}
