package net.sourceforge.quoa.bean;

import java.io.Serializable;

import net.sourceforge.beanoa.bean.Visitable;
import net.sourceforge.beanoa.bean.Visitor;

public final class Period implements Serializable, Visitable<Period> {
	private static final long serialVersionUID = 2140482884205164572L;

	private int time;
	private PeriodType type;
	
	public Period(){
		
	}
	
	public Period(final int time, final PeriodType type){
		setTime(time);
		setType(type);
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public PeriodType getType() {
		return type;
	}
	public void setType(PeriodType type) {
		this.type = type;
	}
	
	@Override
	public <R> R accept(Visitor<Period, R> visitor) {
		return visitor.visit(this);
	}

}
