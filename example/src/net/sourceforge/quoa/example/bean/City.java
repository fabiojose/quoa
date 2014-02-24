package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public final class City extends Democracy {
	private static final long serialVersionUID = 800832232291888930L;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	private State state;
	
	@OneToOne(optional = false)
	private Person mayor;
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Person getMayor() {
		return mayor;
	}

	public void setMayor(Person mayor) {
		this.mayor = mayor;
	}
	
}
