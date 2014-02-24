package net.sourceforge.quoa.example.bean;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Shipment extends Address {
	private static final long serialVersionUID = -2204238418149826155L;

	private Date prevision;

	public Date getPrevision() {
		return prevision;
	}

	public void setPrevision(Date prevision) {
		this.prevision = prevision;
	}
}
