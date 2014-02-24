package net.sourceforge.quoa.criteria;

import java.util.Set;

import net.sourceforge.quoa.exception.QuoaException;

public interface Bag<L, R> {

	Bag<L, R> combine(Criteria<L, R> criteria) throws QuoaException;
	boolean evaluate() throws QuoaException;
	Set<Criteria<L, R>> criterias();
}
