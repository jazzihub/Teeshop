package tee.fachlogik;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="bestellen")
@NamedQuery(name="Bestellung.findAll", query="SELECT b FROM Bestellung b")
public class Bestellung implements Serializable {
	private static final long serialVersionUID = 1L;

	//Attribute
	@EmbeddedId
	private BestellungPK id;

	private int anzahl;

	@Temporal(TemporalType.DATE)
	private Date datum;

	//bi-directional many-to-one association to Artikel
	@ManyToOne
	private Artikel artikel;

	//bi-directional many-to-one association to Kunde
	@ManyToOne
	private Kunde kunde;

	
	
	//Konstruktoren
	public Bestellung() {
	}
	
	public Bestellung(Kunde kunde, Artikel artikel, int anzahl)
	{
		this.datum = new Date(); //aktuelles Datum
		this.artikel = artikel;
		this.anzahl = anzahl;
		this.kunde = kunde;
	}

	
	//Getter, Setter
	public BestellungPK getId() {
		return this.id;
	}

	public void setId(BestellungPK id) {
		this.id = id;
	}

	public int getAnzahl() {
		return this.anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Artikel getArtikel() {
		return this.artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

	public Kunde getKunde() {
		return this.kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

}