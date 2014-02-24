package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;

@Entity
public class Student extends Person {
	private static final long serialVersionUID = -5207311145781068358L;

	private String academic;

	public String getAcademic() {
		return academic;
	}
	public void setAcademic(String academic) {
		this.academic = academic;
	}
	
}
