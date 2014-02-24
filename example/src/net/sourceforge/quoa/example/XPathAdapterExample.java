package net.sourceforge.quoa.example;

import net.sourceforge.quoa.Enumeration;
import net.sourceforge.quoa.Field;
import net.sourceforge.quoa.Mappings;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.Value;
import net.sourceforge.quoa.adapter.ResultWrapper;
import net.sourceforge.quoa.adapter.XPathAdapter;
import net.sourceforge.quoa.criteria.And;
import net.sourceforge.quoa.criteria.Bag;
import net.sourceforge.quoa.criteria.Equals;
import net.sourceforge.quoa.criteria.Or;
import net.sourceforge.quoa.util.Constants.PARAMETERS;

public final class XPathAdapterExample {

	public static void main(String...args){
		final XPathAdapter _adapter = new XPathAdapter();
		final Parameters<String, Object> _parameters = new Parameters<String, Object>();
		_parameters.put(PARAMETERS.URL, "file:static/AddressType.xml");
		
		final Mappings<String, String> _mappings   = new Mappings<String, String>();
		_mappings.setPrefix("/AddressesTypes/type");
		_mappings.put("code"       , "code/text()");
		_mappings.put("description", "description/text()");
		
		_adapter.setParameters(_parameters);
		_adapter.setMappings(_mappings);
		
		Enumeration<ResultWrapper<Object, String>> _results = _adapter.query(new Equals<String>(new Value<String>(new Field("code"), "3")));
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result  = _results.nextElement();
			System.out.println(_result);
		}
		
		_results = _adapter.query(new Equals<String>(new Value<String>(new Field("description"), "COMERCIAL")));
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result  = _results.nextElement();
			System.out.println(_result);
		}
		
		System.out.println("\nBAG or");
		final Bag<Value<String>, String> _or = new Or<Value<String>, String>();
		_or.combine(new Equals<String>(new Value<String>(new Field("code"), "3")));
		_or.combine(new Equals<String>(new Value<String>(new Field("code"), "1")));
		_results = _adapter.query(_or);
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result  = _results.nextElement();
			System.out.println(_result);
		}
		
		System.out.println("\nBAG and");
		final Bag<Value<String>, String> _and = new And<Value<String>, String>();
		_and.combine(new Equals<String>(new Value<String>(new Field("code"), "66666")));
		_and.combine(new Equals<String>(new Value<String>(new Field("description"), "COMERCIAL")));
		_results = _adapter.query(_and);
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result  = _results.nextElement();
			System.out.println(_result);
		}
		
		System.out.println("\nBAG or2");
		final Bag<Value<String>, String> _or2 = new Or<Value<String>, String>();
		_or2.combine(new Equals<String>(new Value<String>(new Field("description"), "COMERCIAL")));
		_or2.combine(new Equals<String>(new Value<String>(new Field("description"), "ZUMBI")));
		_results = _adapter.query(_or2);
		while(_results.hasMoreElements()){
			final ResultWrapper<Object, String> _result  = _results.nextElement();
			System.out.println(_result);
		}
	}

}
