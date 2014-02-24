package net.sourceforge.quoa.criteria;

public interface Criteria<L, R> {

	void cache(R right);
	void cache(L left, R right);
	boolean evaluate();
	
	L left();
	R right();
	
}
