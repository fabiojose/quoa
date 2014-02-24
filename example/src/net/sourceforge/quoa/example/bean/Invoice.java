package net.sourceforge.quoa.example.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public abstract class Invoice extends Abstract {
	private static final long serialVersionUID = -3211210183661431270L;

	private long number;
	private Date date;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Business business;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable(name = "INVOICE_SHIPMENT",
		joinColumns = {
			@JoinColumn(name = "INCOICE_ID", referencedColumnName = "ID", nullable = false)
		}
	)
	private Shipment shipment;
	
	@OneToMany
	@JoinTable(name = "INVOICE_SALES",
		joinColumns = {
			@JoinColumn(name = "INVOICE_ID", referencedColumnName = "ID", nullable = false)
		}
	)
	private Set<Sale> sales;
	
	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	
	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	public Set<Sale> getSales() {
		return sales;
	}
	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}
	
	public abstract ICustomer getCustomer();
	public abstract void setCustomer(ICustomer customer);
}
