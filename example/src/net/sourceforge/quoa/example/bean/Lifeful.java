package net.sourceforge.quoa.example.bean;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Lifeful extends Being {
	private static final long serialVersionUID = -8549761694947478757L;
	
	private Date bith;
	private Date death;
	
	public Date getBith() {
		return bith;
	}
	public void setBith(Date bith) {
		this.bith = bith;
	}
	public Date getDeath() {
		return death;
	}
	public void setDeath(Date death) {
		this.death = death;
	}
	
}
