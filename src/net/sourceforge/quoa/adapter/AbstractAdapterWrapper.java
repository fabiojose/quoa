package net.sourceforge.quoa.adapter;

import java.util.HashMap;

import net.sourceforge.quoa.Enumeration;
import net.sourceforge.quoa.criteria.CriteriaValue;
import net.sourceforge.quoa.exception.NonUniqueException;
import net.sourceforge.quoa.exception.QuoaException;

public abstract class AbstractAdapterWrapper<Q> extends AbstractAdapter<Q, ResultWrapper<Object, Q>> implements IQuoaAdapterWrapper<Q> {

	public ResultWrapper<Object, Q> querySingle(final CriteriaValue<Q> criteria) throws QuoaException, NonUniqueException {

		final Enumeration<ResultWrapper<Object, Q>> _results = query(criteria);
		ResultWrapper<Object, Q> _result = _results.nextElement();
		if(null== _result){
			_result = new ResultWrapper<Object, Q>(new HashMap<Object, Q>());
		} else {
			if(_results.hasMoreElements()){
				throw new NonUniqueException("There are more results.");
			}
		}
		
		return _result;
	}
	
	@Override
	public ResultWrapper<Object, Q> queryFirst(final CriteriaValue<Q> criteria) throws QuoaException {

		final Enumeration<ResultWrapper<Object, Q>> _results = query(criteria);
		ResultWrapper<Object, Q> _result = _results.nextElement();
		if(null== _result){
			_result = new ResultWrapper<Object, Q>(new HashMap<Object, Q>());
		}
		
		return _result;
	}
}
