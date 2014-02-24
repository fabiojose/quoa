package net.sourceforge.quoa;

import java.io.Serializable;

public final class Field implements Serializable {
	private static final long serialVersionUID = 365161698999153354L;

	private String name;
	public Field(final String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
