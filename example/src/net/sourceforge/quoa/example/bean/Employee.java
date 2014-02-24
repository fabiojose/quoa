package net.sourceforge.quoa.example.bean;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Employee extends Person {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private Business business;
	private Date admission;
	private Date demission;
	private Double remuneration;
	
	@AttributeOverride(name = "code", column = @Column(name = "FUNCTION_CODE", nullable = false))
	private Couple<String, String> function;
	
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public Date getAdmission() {
		return admission;
	}
	public void setAdmission(Date admission) {
		this.admission = admission;
	}
	public Date getDemission() {
		return demission;
	}
	public void setDemission(Date demission) {
		this.demission = demission;
	}
	public Double getRemuneration() {
		return remuneration;
	}
	public void setRemuneration(Double remuneration) {
		this.remuneration = remuneration;
	}
	public Couple<String, String> getFunction() {
		return function;
	}
	public void setFunction(Couple<String, String> function) {
		this.function = function;
	}
}
