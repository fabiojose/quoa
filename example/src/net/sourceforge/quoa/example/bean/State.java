package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public final class State extends Democracy {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Country country;
	
	@OneToOne(fetch = FetchType.EAGER, optional = true)
	private Person governor;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Person getGovernor() {
		return governor;
	}

	public void setGovernor(Person governor) {
		this.governor = governor;
	}
	
}
