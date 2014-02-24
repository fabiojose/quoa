package net.sourceforge.quoa.adapter;

import java.util.HashMap;
import java.util.Map;

public class ResultWrapper<K, V> {

	private Map<K, V> mapping;
	
	ResultWrapper(){
		this.mapping = new HashMap<K, V>();
	}
	
	ResultWrapper(final Map<K, V> mapping){
		this.mapping = mapping;
	}
	
	public V get(final K key){
		return mapping.get(key);
	}
	
	void put(final K key, final V value){
		mapping.put(key, value);
	}
	
	public boolean containsKey(final K key){
		return mapping.containsKey(key);
	}
	
	public boolean containsValue(final V value){
		return mapping.containsValue(value);
	}
	
	public String toString(){
		return "" + mapping;
	}
}
