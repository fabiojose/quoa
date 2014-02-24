package net.sourceforge.quoa.example.bean;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public abstract class Price extends Abstract {
	private static final long serialVersionUID = -1109643652509716171L;

	private Date date;
	private double value;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public abstract ISelling getSelling();
	public abstract void setSelling(ISelling selling);

}
