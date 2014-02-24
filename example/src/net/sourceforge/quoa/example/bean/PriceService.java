package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public final class PriceService extends Price {
	private static final long serialVersionUID = 8686195642543167549L;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Service.class)
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
