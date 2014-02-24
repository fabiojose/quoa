package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;

@Entity
public class Color extends Abstract {
	private static final long serialVersionUID = 2776622267246588310L;
	
	private int red;
	private int green;
	private int blue;
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
}
