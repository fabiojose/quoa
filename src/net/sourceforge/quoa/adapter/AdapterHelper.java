package net.sourceforge.quoa.adapter;

import java.net.URL;

import net.sourceforge.quoa.Field;
import net.sourceforge.quoa.Mappings;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.util.Commons;
import net.sourceforge.quoa.util.Constants.PARAMETERS;

public final class AdapterHelper {

	public String mapped(final Field field, final Mappings<String, String> mappings){
		return mapped(field.getName(), mappings);
	}
	
	public String mapped(final String field, final Mappings<String, String> mappings){
		String _result = mappings.get(field);
		
		if(null== _result){
			return field;
		}
		
		return _result;
	}
	
	public URL getURL(final Parameters<String, Object> parameters){
		
		final Object _o = parameters.get(PARAMETERS.URL);
		URL _result;
		if(_o instanceof URL){
			_result = (URL)_o;
		} else {
			_result = Commons.toURL((String)_o);
		}

		return _result;
	}
}
