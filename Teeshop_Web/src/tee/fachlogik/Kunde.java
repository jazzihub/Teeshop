package tee.fachlogik;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



//name = Name der Tabelle in Datenbank
//name wird in java persistence query language verwendet



//@NamedQueries({
//	@NamedQuery(name="Employee.findAll", 
//			query="SELECT e FROM Employee e"),
//	@NamedQuery(name="Employee.findBirthdate",
//			query="SELECT e FROM Employee e WHERE e.birth "
//					+ "BETWEEN :d1 AND :d2"),
//					// unnötig !!!
//	@NamedQuery(name="Employee.byDepartment",
//			query="SELECT e FROM Employee e "
//					+ " WHERE e.department = :d")
//})


@Entity
@NamedQuery(name="Kunde.findAll", query="SELECT k FROM Kunde k")
public class Kunde implements Serializable 
{
	//Attribute
	@Id //kennzeichnet den eindeutigen Schlüssel
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Schlüssel automatisch generieren
	private int id;
	@Column(length = 30)
	private String vorname;
	@Column(length = 30)
	private String nachname;
	private int plz;
	@Column(length = 45)
	private String strasse;
	private String hausnr;
	@Column(length = 45)
	private String ort;
	
	//bi-directional many-to-one association to Bestellung
		@OneToMany(mappedBy="kunde")
		private List<Bestellung> bestellungen;
	
		
		
	//Konstruktoren
	public Kunde()
	{		
	}
	
	public Kunde(String vorname, String nachname, String strasse,
			String hausnr, int plz, String ort)
	{
		super();
		setVorname(vorname);
		setNachname(nachname);
		setPlz(plz);
		setStrasse(strasse);
		setHausnr(hausnr);
		setOrt(ort);
	}


	//Getter/Setter
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	public String getVorname()
	{
		return vorname;
	}
	public void setVorname(String vorname)
	{
		this.vorname = vorname;
	}
	public String getNachname()
	{
		return nachname;
	}
	public void setNachname(String nachname)
	{
		this.nachname = nachname;
	}
	
	public List<Bestellung> getBestellungen()
	{
		return bestellungen;
	}

	public void setBestellungen(List<Bestellung> bestellungen)
	{
		this.bestellungen = bestellungen;
	}

	public int getPlz()
	{
		return plz;
	}
	public void setPlz(int plz)
	{
		this.plz = plz;
	}
	public String getStrasse()
	{
		return strasse;
	}
	public void setStrasse(String strasse)
	{
		this.strasse = strasse;
	}
	public String getHausnr()
	{
		return hausnr;
	}
	public void setHausnr(String hausnr)
	{
		this.hausnr = hausnr;
	}
	public String getOrt()
	{
		return ort;
	}
	public void setOrt(String ort)
	{
		this.ort = ort;
	}

	//String
	@Override
	public String toString()
	{
		return "Kunde [id=" + id + ", vorname=" + vorname + ", nachname="
				+ nachname + ", plz=" + plz + ", strasse=" + strasse
				+ ", hausnr=" + hausnr + ", ort=" + ort + "]";
	}
	
	
	
	
	
	
	
}
