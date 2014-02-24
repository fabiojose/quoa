package net.sourceforge.quoa.example.bean;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public final class BeanExtensionDate extends BeanExtension<Date> {
	private static final long serialVersionUID = 9067251650524416151L;

	private Date value;
	
	@Override
	public Date getValue() {
		return value;
	}

	@Override
	public void setValue(Date value) {
		this.value = value;
	}

}
