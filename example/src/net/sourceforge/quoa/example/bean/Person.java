package net.sourceforge.quoa.example.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Person extends Lifeful implements ICustomer {
	private static final long serialVersionUID = 7039838969417584578L;

	private String document;
	private String alias;
	
	@Embedded
	@AttributeOverride(name = "code", column = @Column(name = "SEX_CODE"))
	private Couple<String, String> sex;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private Phone phone;
	
	@OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	private Address home;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "FATHER_ID", nullable = true)
	private Person father;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "MOTHER_ID", nullable = true)
	private Person mother;
	
	@OneToMany
	@JoinTable(name = "PERSON_SIBLING",
		joinColumns = {
			@JoinColumn(name = "PERSON_ID", referencedColumnName = "ID", nullable = false)
		}
	)
	private Set<Person> sibling;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "WEDLOCK_ID", nullable = true)
	private Person wedlock;
	
	//ICustomer's attributes
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "PERSON_PHONE",
		joinColumns = {
			@JoinColumn(name = "PERSON_ID", referencedColumnName = "ID", nullable = false)
		}
	)
	private Set<Phone> phones;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "PERSON_ADDRESS",
		joinColumns = {
			@JoinColumn(name = "PERSON_ID", referencedColumnName = "ID", nullable = false)
		}
	)
	private Set<Address> addresses;
	
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Couple<String, String> getSex() {
		if(null== sex){
			sex = new Couple<String, String>();
		}
		return sex;
	}
	public void setSex(Couple<String, String> sex) {
		this.sex = sex;
	}
	public Phone getPhone(){
		return phone;
	}
	public void setPhone(Phone phone){
		this.phone = phone;
	}
	public Address getHome() {
		return home;
	}
	public void setHome(Address home) {
		this.home = home;
	}
	public Person getFather() {
		return father;
	}
	public void setFather(Person father) {
		this.father = father;
	}
	public Person getMother() {
		return mother;
	}
	public void setMother(Person mother) {
		this.mother = mother;
	}	
	public Set<Person> getSibling() {
		if(null== sibling){
			sibling = new HashSet<Person>();
		}
		return sibling;
	}
	public void setSibling(Set<Person> sibling) {
		this.sibling = sibling;
	}
	public Person getWedlock() {
		return wedlock;
	}
	public void setWedlock(Person wedlock) {
		this.wedlock = wedlock;
		
		this.wedlock.wedlock = this;
	}
	@Override
	public Set<Phone> getPhones() {
		return phones;
	}
	@Override
	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}
	
	@Override
	public Set<Address> getAddresses() {
		return addresses;
	}
	@Override
	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}
}
