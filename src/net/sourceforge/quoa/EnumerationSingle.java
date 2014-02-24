package net.sourceforge.quoa;

import net.sourceforge.quoa.exception.QuoaException;

public final class EnumerationSingle<T> implements Enumeration<T> {

	private T value;
	private boolean ready;
	
	public EnumerationSingle(final T value){
		this.value = value;
		ready = false;
	}
	
	@Override
	public boolean hasMoreElements() {
		return !ready;
	}

	@Override
	public T nextElement() {
		
		if(!ready){
			ready = true;
			return value;
		}
		
		return null;
	}

	@Override
	public void close() throws QuoaException {		
	}

}
