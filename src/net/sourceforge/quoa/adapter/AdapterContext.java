package net.sourceforge.quoa.adapter;

import java.util.HashMap;
import java.util.Map;

public final class AdapterContext {

	private Map<Object, Object> context;
	public AdapterContext(){
		context = new HashMap<Object, Object>();
	}
	
	public AdapterContext(final Map<Object, Object> context) {
		this.context = context;
	}
	
	void put(final Object key, final Object value){
		context.put(key, value);
	}
	
	public Object get(final Object key){
		return context.get(key);
	}
	
	Object remove(final Object key){
		return context.remove(key);
	}
	
	void clear(){
		context.clear();
	}
}
