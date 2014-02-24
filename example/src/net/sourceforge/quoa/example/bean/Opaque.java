package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Opaque extends Artifact {
	private static final long serialVersionUID = -976588650521314778L;

	@ManyToOne(fetch = FetchType.EAGER)
	private Color color;

	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
}
