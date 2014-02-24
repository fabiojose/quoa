package net.sourceforge.quoa.example.bean;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class School extends Place {
	private static final long serialVersionUID = 3181848599017745875L;

	@AttributeOverride(name = "code", column = @Column(name = "DELEGATION_CODE"))
	private Couple<String, String> delegation;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "SCHOOL_PHONE",
		joinColumns = {
			@JoinColumn(name = "SCHOOL_ID", referencedColumnName = "ID", nullable = false)
		}
	)
	private Set<Phone> phones;
	
	public Couple<String, String> getDelegation() {
		return delegation;
	}
	public void setDelegation(Couple<String, String> delegation) {
		this.delegation = delegation;
	}
	public Set<Phone> getPhones() {
		return phones;
	}
	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}
}
