package net.sourceforge.quoa.criteria;

import net.sourceforge.quoa.Value;

public final class Not<R> implements CriteriaValue<R> {

	private CriteriaValue<R> criteria;
	public Not(final CriteriaValue<R> criteria){
		this.criteria = criteria;
	}
	
	@Override
	public boolean evaluate() {
		return !criteria.evaluate();
	}
	
	public void cache(final Value<R> value, R right){
		criteria.cache(value, right);
	}
	
	public void cache(final R right){
		criteria.cache(right);
	}
	
	public Value<R> left(){
		return criteria.left();
	}
	
	public R right(){
		return criteria.right();
	}

}
