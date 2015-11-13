package tee.persistence;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import tee.fachlogik.TeeException;

public final class EntityManager_Singelton
{
	private static EntityManager em;

	private EntityManager_Singelton()
	{}
	
	public synchronized static EntityManager getInstance() throws TeeException
	{
		if(em == null)
		{
			try
			{
				em = Persistence.createEntityManagerFactory("Teeshop_Web").createEntityManager();
				
				
			} catch (Exception e)
			{
				throw new TeeException(
						"Datenbankverbindung kann nicht hersgestellt werden");
			}
		}
		return em;
	}
}


