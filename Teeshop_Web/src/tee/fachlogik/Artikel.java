package tee.fachlogik;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;



@Entity
@NamedQuery(name = "Artikel.findAll", query = "SELECT a FROM Artikel a")
// JPQ-Language
public class Artikel implements Serializable
{
	@Id
	// kennzeichnet den eindeutigen Schlüssel
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Schlüssel automatisch generieren
	private int id;
	private String bezeichnung;
	@Column(name = "nettopreis")
	private double preisNetto; // BigDecimal als Datentyp?
	@Column(name = "mwst")
	private double ust;
	private int lagerstand;

	// bi-directional many-to-one association to Bestellung
	@OneToMany(mappedBy = "artikel", cascade=CascadeType.REMOVE) //Attribut in Klasse Bestellung
	private List<Bestellung> bestellungen;

	
	
	// Konstruktoren
	public Artikel()
	{
	}

	public Artikel(String bezeichnung, double preis, double ust, int lagerstand)
	{
		// setId(nextId);
		// nextId++;
		setBezeichnung(bezeichnung);
		setPreisNetto(preis);
		setUst(ust);
		setLagerstand(lagerstand);
	}

	
	// String
	@Override
	public String toString()
	{
		return "Artikel [id=" + id + ", bezeichnung=" + bezeichnung
				+ ", preisNetto=" + preisNetto + ", ust=" + ust
				+ ", lagerstand=" + lagerstand + "]";
	}

	
	
	// Getter/Setter
	
	public int getLagerstand()
	{
		return lagerstand;
	}

	public List<Bestellung> getBestellungen()
	{
		return bestellungen;
	}

	public void setBestellungen(List<Bestellung> bestellungen)
	{
		this.bestellungen = bestellungen;
	}

	public void setLagerstand(int lagerstand)
	{
		this.lagerstand = lagerstand;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getBezeichnung()
	{
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung)
	{
		this.bezeichnung = bezeichnung;
	}

	public double getPreisNetto()
	{
		return preisNetto;
	}

	public void setPreisNetto(double preisNetto)
	{
		this.preisNetto = preisNetto;
	}

	public double getUst()
	{
		return ust;
	}

	public void setUst(double ust)
	{
		this.ust = ust;
	}

}
