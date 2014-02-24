package net.sourceforge.quoa.example.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import net.sourceforge.beanoa.bean.Visitable;
import net.sourceforge.beanoa.bean.Visitor;

@Entity
@SequenceGenerator(name = "PHONE_SEQ", sequenceName = "PHONE_SEQ", initialValue = 1, allocationSize = 1)
public final class Phone implements Serializable, Visitable<Phone> {
	private static final long serialVersionUID = -1727304721469596213L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHONE_SEQ")
	private long id;

	@Transient
	private Couple<Integer, String> type;
	
	@Transient
	private Couple<Long, String> carrier;
	private String number;
	private String note;
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public Couple<Integer, String> getType() {
		return type;
	}

	public void setType(Couple<Integer, String> type) {
		this.type = type;
	}

	public Couple<Long, String> getCarrier() {
		return carrier;
	}

	public void setCarrier(Couple<Long, String> carrier) {
		this.carrier = carrier;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public <R> R accept(final Visitor<Phone, R> visitor) {
		return visitor.visit(this);
	}

}
