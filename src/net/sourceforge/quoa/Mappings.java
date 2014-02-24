package net.sourceforge.quoa;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Mappings<K, V> {

	private Map<K, V> mappings;
	private K prefix;
	
	public Mappings(){
		mappings = new HashMap<K, V>();
	}
	
	public Mappings(final K prefix){
		this();
		this.prefix = prefix;
	}
	
	public Set<K> keySet(){
		return mappings.keySet();
	}
	
	public void put(final K key, final V value){
		mappings.put(key, value);
	}
	
	public V get(final K key){
		return mappings.get(key);
	}
	
	public K getPrefix(){
		return prefix;
	}
	
	public void setPrefix(final K prefix){
		this.prefix = prefix;
	}
	
	public Collection<V> values(){
		return mappings.values();
	}
	
	public void clear(){
		mappings.clear();
	}
}
