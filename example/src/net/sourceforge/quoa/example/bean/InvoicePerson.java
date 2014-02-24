package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public final class InvoicePerson extends Invoice {
	private static final long serialVersionUID = 3244013730684949693L;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Person.class)
	private ICustomer customer;
	
	@Override
	public ICustomer getCustomer() {
		return customer;
	}

	@Override
	public void setCustomer(ICustomer customer) {
		this.customer = customer;
	}

}
