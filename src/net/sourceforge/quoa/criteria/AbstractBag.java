package net.sourceforge.quoa.criteria;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.quoa.exception.QuoaException;

public abstract class AbstractBag<L, R> implements Bag<L, R> {

	private Set<Criteria<L, R>> criterias;
	
	public AbstractBag(){
		criterias = new HashSet<Criteria<L,R>>();
	}
	
	@Override
	public Bag<L, R> combine(Criteria<L, R> criteria) throws QuoaException {
		criterias.add(criteria);
		return this;
	}
	
	@Override
	public Set<Criteria<L, R>> criterias() {
		return criterias;
	}
	
	protected void criteria(final Set<Criteria<L, R>> criterias){
		this.criterias = criterias;
	}
}
