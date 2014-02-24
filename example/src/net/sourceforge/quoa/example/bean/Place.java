package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Place extends Lifeless {
	private static final long serialVersionUID = 1794407783505125109L;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
