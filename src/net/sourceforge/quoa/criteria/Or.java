package net.sourceforge.quoa.criteria;

import net.sourceforge.quoa.exception.QuoaException;

public final class Or<L, R> extends AbstractBag<L, R> implements Bag<L, R> {
	
	@Override
	public boolean evaluate() throws QuoaException {
		
		boolean _result = false;
		for(Criteria<L, R> _criteria : criterias()){
			_result = _result || _criteria.evaluate();
		}
		
		return _result;
	}

}
