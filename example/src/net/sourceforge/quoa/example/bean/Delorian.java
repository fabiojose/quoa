package net.sourceforge.quoa.example.bean;

import java.util.Date;

public final class Delorian extends Vehicle {
	private static final long serialVersionUID = -1666501986101659542L;

	private Date time;

	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public void doTimeTravel(){
		System.out.println("going to: " + getTime());
	}
}
