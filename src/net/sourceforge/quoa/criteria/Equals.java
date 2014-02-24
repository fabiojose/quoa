package net.sourceforge.quoa.criteria;

import net.sourceforge.quoa.Value;

public final class Equals<R> extends AbstractCriteriaValue<R> {
	
	public Equals(final Value<R> left){
		cache(left, null);
	}

	@Override
	public boolean evaluate() {
		return value.getContent().equals(right);
	}

}
