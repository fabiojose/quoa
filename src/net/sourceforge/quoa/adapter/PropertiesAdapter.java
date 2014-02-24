package net.sourceforge.quoa.adapter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import net.sourceforge.quoa.Enumeration;
import net.sourceforge.quoa.EnumerationList;
import net.sourceforge.quoa.Field;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.criteria.CriteriaValue;
import net.sourceforge.quoa.exception.QuoaException;
import net.sourceforge.quoa.exception.ValueNotFoundException;
import net.sourceforge.quoa.util.Constants;
import net.sourceforge.quoa.util.Constants.PATTERNS;
import net.sourceforge.quoa.util.Constants.STRINGS;
import net.sourceforge.quoa.util.StringCommons;

public final class PropertiesAdapter extends AbstractAdapterWrapper<String> {
	private static final Configuration CONFIGURATION = new Configuration();
	static{
		CONFIGURATION.addParameter(new Configuration.Parameter(Constants.PARAMETERS.URL, String.class));
	};

	@Override
	public void setParameters(final Parameters<String, Object> parameters) throws ValueNotFoundException, QuoaException {
		validate(CONFIGURATION, parameters);
	}
	
	private Properties getProperties() throws QuoaException{
		
		final Properties _result = new Properties();
		final URL _url = getHelper().getURL(getParameters());
		try{
			_result.load(getServiceProvider().getIOProvider().open(_url));
		}catch(IOException _e){
			throw new QuoaException(_e.getMessage(), _e);
		}
		return _result;
	}
	
	private String prefixOf(final String property){
		
		String _result = null;
		final int _indexOf = property.indexOf('.'); 
		if(_indexOf != -1){
			_result = property.substring(0, _indexOf);
		} else {
			_result = property;
		}
		return _result;
	}
	
	private String replace(final String property, final String prefix){
		return property.replace(STRINGS.PREFIX_FUNCTION, prefix);
	}
	
	private void doMapping(final ResultWrapper<Object, String> wrapper, final Properties properties){
		
		final Set<String> _fields = getMappings().keySet();
		for(String _field : _fields){
			if(!wrapper.containsKey(_field)){
				final String _vprefix = (String)getContext().get(STRINGS.PREFIX_FUNCTION);
				final String _mapped = mapped(_field);
				
				if(StringCommons.match(_mapped, PATTERNS.PREFIX_FUNCTION)){
					String _value = null;
					if(StringCommons.exact(_mapped, PATTERNS.PREFIX_FUNCTION)){
						_value = _vprefix;
						
					} else if(StringCommons.exact(_mapped, PATTERNS.PREFIX_VALUE_FUNCTION)){
						_value = properties.getProperty(_vprefix);
						
					} else if(StringCommons.exact(_mapped, PATTERNS.PREFIX_FUNCTION_KEY)){
						_value = properties.getProperty(replace(_mapped, _vprefix));
						
					} else {
						throw new QuoaException("mapped name did not match: " + _mapped);
					}
					
					wrapper.put(_field, _value);
				} else {
					throw new QuoaException("mapped name did not match with the prefix pattern: " + _mapped);
				}
			}
		}
	}
	
	@Override
	public Enumeration<ResultWrapper<Object, String>> query(final CriteriaValue<String> criteria) throws QuoaException {

		final Field _field      = criteria.left().getField();
		final String _mapped    = mapped(_field);
		final Properties _props = getProperties();
		final java.util.Enumeration<Object> _properties = _props.keys();
		final java.util.List<ResultWrapper<Object, String>> _result = new ArrayList<ResultWrapper<Object,String>>();
		
		while(_properties.hasMoreElements()){
			final String _property = (String)_properties.nextElement();
			
			// validate prefix pattern
			if(StringCommons.match(_mapped, PATTERNS.PREFIX_FUNCTION)){
				
				// obtain the prefix function in the context
				String _vprefix = (String)getContext().get(STRINGS.PREFIX_FUNCTION);
				if(null== _vprefix){
					//resolve the property´s prefix 
					_vprefix = prefixOf(_property);
					getContext().put(STRINGS.PREFIX_FUNCTION, _vprefix);
				} else {
					//another prefix value, then change in the context
					if(!_vprefix.equals(prefixOf(_property))){
						_vprefix = prefixOf(_property);
						getContext().put(STRINGS.PREFIX_FUNCTION, _vprefix);
					}
				}
				
				String _value = null;
				if(StringCommons.exact(_mapped, PATTERNS.PREFIX_FUNCTION)){
					//this is just the prefix function
					_value = _property;
					
				} else if(StringCommons.exact(_mapped, PATTERNS.PREFIX_VALUE_FUNCTION)){
					//this is the prefix function plus value function
					_value  = _props.getProperty(_property);
					
				} else if(StringCommons.exact(_mapped, PATTERNS.PREFIX_FUNCTION_KEY)){
					//this is the prefix function plus any property key (a suffix)
					final String _lproperty = replace(_mapped, _vprefix);
					if(_lproperty.equals(_property)){
						//is this the same property?
						_value = _props.getProperty(_lproperty);
					}
				} else {
					throw new QuoaException("mapped name did not match: " + _mapped);
				}
				
				criteria.cache(_value);
				if(criteria.evaluate()){
					//match result
					final ResultWrapper<Object, String> _wrapper = new ResultWrapper<Object, String>(new HashMap<Object, String>());
					_wrapper.put(criteria.left().getField().getName(), _value);
					
					// resolve the values in the mappings
					doMapping(_wrapper, _props);
					
					_result.add(_wrapper);
				}
			} else {
				throw new QuoaException("mapped name did not match with the prefix pattern: " + _mapped);
			}
		}
		
		return new EnumerationList<ResultWrapper<Object,String>>(_result);
	}
}
