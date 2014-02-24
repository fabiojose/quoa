package net.sourceforge.quoa.adapter;

import net.sourceforge.quoa.Enumeration;
import net.sourceforge.quoa.Mappings;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.criteria.CriteriaValue;
import net.sourceforge.quoa.exception.NonUniqueException;
import net.sourceforge.quoa.exception.QuoaException;
import net.sourceforge.quoa.exception.ValueNotFoundException;

public interface IQuoaAdapter<Q, R> {
	
	void setMappings(Mappings<String, String> mappings);
	void setParameters(Parameters<String, Object> parameters) throws ValueNotFoundException, QuoaException;
	
	/**
	 * 
	 * @param value
	 * @return A single result
	 * @throws QuoaException
	 * @throws NonUniqueException
	 */
	R querySingle(CriteriaValue<Q> criteria) throws QuoaException, NonUniqueException;
	
	/**
	 * 
	 * @param value
	 * @return The first result found
	 * @throws QuoaException
	 */
	R queryFirst (CriteriaValue<Q> criteria) throws QuoaException;
	
	/**
	 * Query a list of values based on a value
	 * @param key
	 * @return An enumeration with values
	 */
	Enumeration<R> query(CriteriaValue<Q> criteria) throws QuoaException;
	
}
