package net.sourceforge.quoa;

import java.util.HashMap;
import java.util.Map;

import net.sourceforge.quoa.annotation.Cacheable;
import net.sourceforge.quoa.converter.ByPassConverter;

public final class ObjectPool {

	private static final ThreadLocal<Map<Class<? extends Object>, Object>> POOL = new ThreadLocal<Map<Class<? extends Object>,Object>>();
	private ObjectPool(){
	}
	
	private static synchronized Map<Class<? extends Object>, Object> map(){
		
		Map<Class<? extends Object>, Object> _result = POOL.get();
		if(null== _result){
			_result = new HashMap<Class<? extends Object>, Object>();
			POOL.set(_result);
		}
		
		return _result;
	}
	
	@SuppressWarnings("unchecked")
	public static synchronized <T> T getInstante(final Class<T> type){
		T _result = null;
		
		final Map<Class<? extends Object>, Object> _map = map();
		final Object _o = _map.get(type);
		if(null== _o){
			try{
				_result = type.newInstance();
				
				if(null!= type.getAnnotation(Cacheable.class)){
					_map.put(type, _result);
				}
			}catch(IllegalAccessException _e){
				throw new RuntimeException(_e.getMessage(), _e);
				
			}catch(InstantiationException _e){
				throw new RuntimeException(_e.getMessage(), _e);
			}
		} else {
			_result = (T)_o;
		}
		
		return _result;
	}
	
	public static void main(String...args){
		System.out.println(ObjectPool.getInstante(ByPassConverter.class));
		System.out.println(ObjectPool.getInstante(ByPassConverter.class));
	}
}
