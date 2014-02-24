package net.sourceforge.quoa.criteria;

public abstract class AbstractCriteria<L, R> implements Criteria<L, R> {

	protected L left;
	protected R right;
	
	@Override
	public void cache(R right) {
		this.right = right;
	}
	
	@Override
	public void cache(L left, R right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public L left() {
		return left;
	}
	
	@Override
	public R right() {
		return right;
	}
}
