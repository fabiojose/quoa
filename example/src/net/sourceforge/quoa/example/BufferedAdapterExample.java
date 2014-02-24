package net.sourceforge.quoa.example;

import net.sourceforge.quoa.Enumeration;
import net.sourceforge.quoa.Field;
import net.sourceforge.quoa.Mappings;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.Value;
import net.sourceforge.quoa.adapter.BufferedAdapter;
import net.sourceforge.quoa.adapter.PropertiesAdapter;
import net.sourceforge.quoa.adapter.ResultWrapper;
import net.sourceforge.quoa.bean.Period;
import net.sourceforge.quoa.bean.PeriodType;
import net.sourceforge.quoa.criteria.Equals;
import net.sourceforge.quoa.util.Constants.PARAMETERS;

public class BufferedAdapterExample {

	public static void main(String...args){
		final BufferedAdapter<String, ResultWrapper<Object, String>> _buffered = new BufferedAdapter<String, ResultWrapper<Object, String>>(new PropertiesAdapter());

		final Parameters<String, Object> _parameters = new Parameters<String, Object>();
		_parameters.put(PARAMETERS.URL, "http://localhost:8080/quoa/states.properties");
		_parameters.put(PARAMETERS.LOCATION, "C:\\temp");
		_parameters.put(PARAMETERS.TIMEOUT, new Period(1, PeriodType.SECONDS));
		
		final Mappings<String, String> _mappings = new Mappings<String, String>();
		_mappings.put("code"       , "prefix()");
		_mappings.put("description", "prefix().value()");
		_mappings.put("country"    , "prefix().country");
		_mappings.put("monogram"   , "prefix().code");
		_mappings.put("region"     , "prefix().region");
		_mappings.put("capital"    , "prefix().capital");
		
		_buffered.setParameters(_parameters);
		_buffered.setMappings(_mappings);
		
		System.out.println("code = sp");
		Enumeration<ResultWrapper<Object, String>> _results = _buffered.query(new Equals<String>(new Value<String>(new Field("code"), "sp")));
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result = _results.nextElement();
			System.out.println(_result);
		}
		_results.close();

	}
}
