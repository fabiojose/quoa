package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;

@Entity
public class Democracy extends Abstract {
	private static final long serialVersionUID = 6957994324886846376L;

	private String monogram;

	public String getMonogram() {
		return monogram;
	}

	public void setMonogram(String monogram) {
		this.monogram = monogram;
	}
	
}
