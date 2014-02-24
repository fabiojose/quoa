package net.sourceforge.quoa.util;

import net.sourceforge.quoa.provider.ServiceProvider;
import net.sourceforge.quoa.provider.impl.ServiceProviderImpl;

public final class ServiceLocator {

	private static final ThreadLocal<Object> POOL = new ThreadLocal<Object>();
	
	private ServiceLocator(){
		
	}
	
	public static synchronized ServiceProvider getServiceProvider(){
		ServiceProvider _result = (ServiceProvider)POOL.get();
		if(null== _result){
			_result = new ServiceProviderImpl();
			POOL.set(_result);
		}
		
		return _result;
	}
}
