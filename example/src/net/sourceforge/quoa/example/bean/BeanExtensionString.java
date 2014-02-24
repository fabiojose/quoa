package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;

@Entity
public final class BeanExtensionString extends BeanExtension<String> {
	private static final long serialVersionUID = -7585419654390963378L;
	
	private String value;
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

}
