package net.sourceforge.quoa;

import java.util.HashMap;
import java.util.Map;

public final class Parameters<K, V> {

	private Map<K, V> parameters;
	
	public Parameters(){
		parameters = new HashMap<K, V>();
	}
	
	public void put(final K key, final V value){
		parameters.put(key, value);
	}
	
	public V get(final K key){
		return parameters.get(key);
	}

	public void clear(){
		parameters.clear();
	}
	
	public boolean containsKey(final K key){
		return parameters.containsKey(key);
	}
}
