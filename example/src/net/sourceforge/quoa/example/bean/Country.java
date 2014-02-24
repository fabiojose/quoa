package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public final class Country extends Democracy {
	private static final long serialVersionUID = 5709703810715424633L;

	@OneToOne(fetch = FetchType.EAGER, optional = true)
	private Person president;

	public Person getPresident() {
		return president;
	}

	public void setPresident(Person president) {
		this.president = president;
	}
	
}
