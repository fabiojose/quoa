package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public final class InvoiceBusiness extends Invoice {
	private static final long serialVersionUID = 6871125961468818080L;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Business.class)
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
