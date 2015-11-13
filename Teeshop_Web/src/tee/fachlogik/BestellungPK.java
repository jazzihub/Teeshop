package tee.fachlogik;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the bestellen database table.
 * 
 */
@Embeddable
public class BestellungPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="artikel_id", insertable=false, updatable=false)
	private int artikelId;

	@Column(name="kunde_id", insertable=false, updatable=false)
	private int kundeId;

	public BestellungPK() {
	}
	public int getArtikelId() {
		return this.artikelId;
	}
	public void setArtikelId(int artikelId) {
		this.artikelId = artikelId;
	}
	public int getKundeId() {
		return this.kundeId;
	}
	public void setKundeId(int kundeId) {
		this.kundeId = kundeId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BestellungPK)) {
			return false;
		}
		BestellungPK castOther = (BestellungPK)other;
		return 
			(this.artikelId == castOther.artikelId)
			&& (this.kundeId == castOther.kundeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.artikelId;
		hash = hash * prime + this.kundeId;
		
		return hash;
	}
}