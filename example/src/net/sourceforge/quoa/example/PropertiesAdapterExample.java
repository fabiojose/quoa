package net.sourceforge.quoa.example;

import net.sourceforge.quoa.Enumeration;
import net.sourceforge.quoa.Field;
import net.sourceforge.quoa.Mappings;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.Value;
import net.sourceforge.quoa.adapter.PropertiesAdapter;
import net.sourceforge.quoa.adapter.ResultWrapper;
import net.sourceforge.quoa.criteria.Equals;
import net.sourceforge.quoa.util.Constants.PARAMETERS;

public class PropertiesAdapterExample {

	public static void main(String...args){
		final PropertiesAdapter _adapter = new PropertiesAdapter();
		final Parameters<String, Object> _parameters = new Parameters<String, Object>();
		_parameters.put(PARAMETERS.URL, "file:static/states.properties");
		
		final Mappings<String, String> _mappings = new Mappings<String, String>();
		_mappings.put("code"       , "prefix()");
		_mappings.put("description", "prefix().value()");
		_mappings.put("country"    , "prefix().country");
		_mappings.put("monogram"   , "prefix().code");
		_mappings.put("region"     , "prefix().region");
		_mappings.put("capital"    , "prefix().capital");
		
		_adapter.setParameters(_parameters);
		_adapter.setMappings(_mappings);
		
		System.out.println("code = sp");
		Enumeration<ResultWrapper<Object, String>> _results = _adapter.query(new Equals<String>(new Value<String>(new Field("code"), "sp")));
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result = _results.nextElement();
			System.out.println(_result);
		}
		_results.close();
		
		System.out.println("\ndescription = Espirito Santo");
		_results = _adapter.query(new Equals<String>(new Value<String>(new Field("description"), "Espirito Santo")));
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result = _results.nextElement();
			System.out.println(_result);
		}
		_results.close();
		
		System.out.println("\ncountry = br");
		_results = _adapter.query(new Equals<String>(new Value<String>(new Field("country"), "br")));
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result = _results.nextElement();
			System.out.println(_result);
		}
		_results.close();
		
		System.out.println("\ncountry = hn");
		_results = _adapter.query(new Equals<String>(new Value<String>(new Field("country"), "hn")));
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result = _results.nextElement();
			System.out.println(_result);
		}
		_results.close();
		
		System.out.println("\nCities");
		_parameters.put(PARAMETERS.URL, "file:static/cities.properties");
		final PropertiesAdapter _cities = new PropertiesAdapter();
		_mappings.clear();
		_mappings.put("code"       , "prefix()");
		_mappings.put("description", "prefix().value()");
		_mappings.put("state"      , "prefix().state");
		_cities.setParameters(_parameters);
		_cities.setMappings(_mappings);
		_results = _cities.query(new Equals<String>(new Value<String>(new Field("description"), "Serra Negra")));
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result = _results.nextElement();
			System.out.println(_result);
		}
		_results.close();
		
		_results = _cities.query(new Equals<String>(new Value<String>(new Field("state"), "SP")));
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result = _results.nextElement();
			System.out.println(_result);
		}
		_results.close();
	}
}
