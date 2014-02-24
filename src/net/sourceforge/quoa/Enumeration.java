package net.sourceforge.quoa;

import net.sourceforge.quoa.exception.QuoaException;

public interface Enumeration<E> extends java.util.Enumeration<E> {

	/**
	 * Release the enumeration resources
	 */
	void close() throws QuoaException;
	
}
