package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;

@Entity
public final class Service extends Abstract implements ISelling {
	private static final long serialVersionUID = 1050406719842147737L;

	private String code;
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

}
