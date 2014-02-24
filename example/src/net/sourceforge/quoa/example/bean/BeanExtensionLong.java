package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;

@Entity
public final class BeanExtensionLong extends BeanExtension<Long> {
	private static final long serialVersionUID = 2601844413955758198L;

	private Long value;
	
	@Override
	public Long getValue() {
		return value;
	}

	@Override
	public void setValue(Long value) {
		this.value = value;
	}

}
