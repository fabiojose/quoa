package net.sourceforge.quoa.criteria;

import net.sourceforge.quoa.Value;

public abstract class AbstractCriteriaValue<R> implements CriteriaValue<R> {

	protected Value<R> value;
	protected R right;
	
	public void cache(final Value<R> value, R right){
		this.value = value;
		this.right = right;
	}
	
	public void cache(final R right){
		this.right = right;
	}
	
	public Value<R> left(){
		return value;
	}
	
	public R right(){
		return right;
	}
}
