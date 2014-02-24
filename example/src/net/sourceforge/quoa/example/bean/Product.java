package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;

@Entity
public final class Product extends Artifact implements ISelling {
	private static final long serialVersionUID = 2268238567808450186L;

	private int parts;
	private String code;

	public int getParts() {
		return parts;
	}

	public void setParts(int parts) {
		this.parts = parts;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}
}
