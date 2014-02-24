package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public final class PriceProduct extends Price {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
	private ISelling selling;
	
	@Override
	public ISelling getSelling() {
		return selling;
	}

	@Override
	public void setSelling(ISelling selling) {
		this.selling = selling;		
	}

}
