package net.sourceforge.quoa;

import java.io.Serializable;

public final class Value<T> implements Serializable {
	private static final long serialVersionUID = 4157514414170650803L;

	private Field field;
	private T content;
	public Value(final Field field, final T content){
		this.field   = field;
		this.content = content;
	}
	
	public T getContent(){
		return content;
	}

	public Field getField() {
		return field;
	}
}
