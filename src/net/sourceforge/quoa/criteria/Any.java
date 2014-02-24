package net.sourceforge.quoa.criteria;

import net.sourceforge.quoa.Field;
import net.sourceforge.quoa.Value;

public final class Any<R> extends AbstractCriteriaValue<R> implements CriteriaValue<R> {

	public Any(final Field field){
		cache(new Value<R>(field, null), null);
	}
	
	@Override
	public boolean evaluate() {
		return Boolean.TRUE;
	}

}
