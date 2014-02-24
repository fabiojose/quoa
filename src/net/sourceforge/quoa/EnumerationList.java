package net.sourceforge.quoa;

import java.util.List;

import net.sourceforge.quoa.exception.QuoaException;

public final class EnumerationList<T> implements Enumeration<T> {

	private List<T> list;
	private int index;
	
	public EnumerationList(final List<T> list){
		this.list = list;
		this.index = 0;
	}
	
	@Override
	public boolean hasMoreElements() {
		return (!list.isEmpty() && index < list.size());
	}

	@Override
	public T nextElement() {

		T _result = null;
		if(!list.isEmpty() && index < list.size()){
			_result = list.get(index++);
		}
		
		return _result;
	}

	@Override
	public void close() throws QuoaException {	
	}
	
	@Override
	public String toString(){
		return list.toString();
	}
}
