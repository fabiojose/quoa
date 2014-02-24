package net.sourceforge.quoa.example.bean;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Lifeless extends Being {
	private static final long serialVersionUID = -6571507058055345454L;

	private Date creation;

	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	
}
