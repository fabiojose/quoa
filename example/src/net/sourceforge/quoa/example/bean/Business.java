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
public class Business extends Place implements ICustomer {
	private static final long serialVersionUID = -2394922814517598273L;

	@AttributeOverride(name = "code", column = @Column(name = "CLASS_CODE"))
	private Couple<Integer, String> classification;
	private String brand;
	private String document;
	
	private String mission;
	private String vision;
	
	//for ICustomer
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "BUSINESS_PHONE",
		joinColumns = {
			@JoinColumn(name = "BUSINESS_ID", referencedColumnName = "ID", nullable = false)
		}
	)
	private Set<Phone> phones;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "BUSINESS_ADDRESS",
		joinColumns = {
			@JoinColumn(name = "BUSINESS_ID", referencedColumnName = "ID", nullable = false)
		}
	)
	private Set<Address> addresses;
	
	public Couple<Integer, String> getClassification(){
		return classification;
	}
	public void setClassification(Couple<Integer, String> classification){
		this.classification = classification;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getMission() {
		return mission;
	}
	public void setMission(String mission) {
		this.mission = mission;
	}
	public String getVision() {
		return vision;
	}
	public void setVision(String vision) {
		this.vision = vision;
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
