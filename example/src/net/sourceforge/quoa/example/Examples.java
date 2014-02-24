package net.sourceforge.quoa.example;

import java.util.Enumeration;

import net.sourceforge.quoa.Field;
import net.sourceforge.quoa.Mappings;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.Value;
import net.sourceforge.quoa.adapter.BufferedAdapter;
import net.sourceforge.quoa.adapter.CSVAdapter;
import net.sourceforge.quoa.adapter.IQuoaAdapter;
import net.sourceforge.quoa.adapter.PropertiesAdapter;
import net.sourceforge.quoa.adapter.ResultWrapper;
import net.sourceforge.quoa.bean.Period;
import net.sourceforge.quoa.bean.PeriodType;
import net.sourceforge.quoa.criteria.Any;
import net.sourceforge.quoa.criteria.Equals;
import net.sourceforge.quoa.example.bean.Address;
import net.sourceforge.quoa.example.bean.Person;
import net.sourceforge.quoa.util.Constants.PARAMETERS;

public final class Examples {

	public static void main(String[] args) {
		
		final Person _person = new Person();
		//_person.setDocument("30055599980");//passo 1, informacao pin
		final Address _home = new Address();
		_home.getType().setCode(1L);
		//_person.setHome(_home);
		try{
			final Parameters<String, Object> _parameters = new Parameters<String, Object>();
			//_parameters.put(PARAMETERS.URL, "file:D:\\study\\workspace\\beanoa\\tests\\quoa\\names.csv");
			_parameters.put(PARAMETERS.URL, "http://localhost:8080/cimg/names.csv");
			_parameters.put(PARAMETERS.SEPARATOR, ";");
			_parameters.put(PARAMETERS.TIMEOUT, new Period(1, PeriodType.MINUTES));
			_parameters.put(PARAMETERS.LOCATION, "D:\\study\\workspace\\beanoa\\temp");
			//final CSVAdapter _adapter = new CSVAdapter(new File("D:\\study\\workspace\\beanoa\\tests\\quoa\\names.csv"));
			
			final IQuoaAdapter<String, ResultWrapper<Object, String>> _adapter = new BufferedAdapter<String, ResultWrapper<Object, String>>( new CSVAdapter() );
			_adapter.setParameters(_parameters);
			
			final Mappings<String, String> _mappings = new Mappings<String, String>();
			_mappings.put("documento", "columns()[0]");
			_mappings.put("nome"     , "columns()[1]");
			_adapter.setMappings(_mappings);
			
			System.out.println(_person.getDocument());
			
			//Enumeration<ResultWrapper<Object, String>> _results = _adapter.query(new Equals<String>(new Field("documento"), _person.getDocument() ));
			Enumeration<ResultWrapper<Object, String>> _results = _adapter.query(new Equals<String>(new Value<String>(new Field("documento"), _person.getDocument())));
			ResultWrapper<Object, String> _result = _results.nextElement();
			System.out.println(_result.get("nome"));
			
			System.out.println();
			ResultWrapper<Object, String> _wrapper = _adapter.queryFirst(new Equals<String>(new Value<String>( new Field("documento"), _person.getDocument() )));
			System.out.println(_wrapper.get("nome"));
			
			System.out.println();
			_results = _adapter.query(new Any<String>(new Field("documento")));
			while(_results.hasMoreElements()){
				_result = _results.nextElement();
				System.out.println(_result.get("documento") + ", " + _result.get("nome"));
			}
			/*
			System.out.println();
			final CriteriaValue<String> _equals1 = new Equals<String>(new Value<String>(new Field("documento"), "29961079980"));
			final CriteriaValue<String> _equals2 = new Equals<String>(new Value<String>(new Field("documento"), "d055599980"));
			final Criteria<Criteria<Value<String>, String>, Criteria<Value<String>, String>> _or = new Or2<Value<String>, String>(_equals1, _equals2);
			_results = _adapter.query(_or);
			while(_results.hasMoreElements()){
				_result = _results.nextElement();
				System.out.println(_result.get("documento") + ", " + _result.get("nome"));
			}
			
			System.out.println();
			final Criteria<Criteria<Value<String>, String>, Criteria<Value<String>, String>> _and = new And2<Value<String>, String>(new Not<String>(_equals1), new Not<String>(_equals2));
			_results = _adapter.query(_and);
			while(_results.hasMoreElements()){
				_result = _results.nextElement();
				System.out.println(_result.get("documento") + ", " + _result.get("nome"));
			}
			*/
			System.out.println("\nProperties Adapter: country");
			_parameters.clear();
			_parameters.put(PARAMETERS.URL, "file:D:\\study\\workspace\\beanoa\\tests\\quoa\\countries.properties");
			final PropertiesAdapter _padapter = new PropertiesAdapter();
			_padapter.setParameters(_parameters);
			
			_mappings.clear();
			_mappings.put("code"       , "property()");
			_mappings.put("description", "property().value()");
			_padapter.setMappings(_mappings);
			_results = _padapter.query(new Equals<String>(new Value<String>(new Field("code"), "us")));
			while(_results.hasMoreElements()){
				_result = _results.nextElement();
				System.out.println(_result.get("code") + " = " + _result.get("description"));
			}
			
			System.out.println("\nProperties Adapter: state");
			_parameters.put(PARAMETERS.URL, "file:D:\\study\\workspace\\beanoa\\tests\\quoa\\states.properties");
			final PropertiesAdapter _states = new PropertiesAdapter();
			_states.setParameters(_parameters);
			
			_mappings.clear();
			_mappings.put("code"       , "property()");
			_mappings.put("description", "property().value()");
			_mappings.put("country"    , "${code}.country");
			_states.setMappings(_mappings);
			_results = _states.query(new Equals<String>(new Value<String>(new Field("code"), "sp")));
			while(_results.hasMoreElements()){
				_result = _results.nextElement();
				System.out.println(_result);
				System.out.println(_result.get("code") + " = " + _result.get("description"));
			}
			
			System.out.println("\nProperties Adapter: states of Country");
			_results = _states.query(new Equals<String>(new Value<String>(new Field("country"), "br")));
			while(_results.hasMoreElements()){
				_result = _results.nextElement();
				System.out.println(_result);
				System.out.println(_result.get("code") + " = " + _result.get("description"));
			}
			
			System.out.println("\nProperties Adapter: city");
			_parameters.put(PARAMETERS.URL, "file:D:\\study\\workspace\\beanoa\\tests\\quoa\\cities.properties");
			final PropertiesAdapter _cities = new PropertiesAdapter();
			_cities.setParameters(_parameters);
			
			_mappings.clear();
			_mappings.put("code"       , "property()");
			_mappings.put("description", "property().value()");
			_mappings.put("state"      , "${code}.state");
			_cities.setMappings(_mappings);
			_results = _cities.query(new Equals<String>(new Value<String>(new Field("code"), "10000")));
			while(_results.hasMoreElements()){
				_result = _results.nextElement();
				System.out.println(_result);
				System.out.println(_result.get("code") + " = " + _result.get("description"));
			}
			
			System.out.println("\nProperties Adapter 2: state");
			_parameters.put(PARAMETERS.URL, "file:D:\\study\\workspace\\beanoa\\tests\\quoa\\states.properties");
			_mappings.clear();
			_mappings.clear();
			_mappings.put("code"           , "prefix()");
			_mappings.put("description"    , "prefix().value()");
			_mappings.put("country"        , "prefix().country");
			final PropertiesAdapter _a2 = new PropertiesAdapter();
			_a2.setParameters(_parameters);
			_a2.setMappings(_mappings);
			
			_results = _a2.query(new Equals<String>(new Value<String>(new Field("code"), "sp")));
			while(_results.hasMoreElements()){
				_result = _results.nextElement();
				System.out.println(_result.get("code") + " = " + _result.get("description"));
			}
			
			_results = _a2.query(new Equals<String>(new Value<String>(new Field("description"), "Espirito Santo")));
			while(_results.hasMoreElements()){
				_result = _results.nextElement();
				System.out.println(_result.get("code") + " = " + _result.get("description"));
			}
						
			_results = _a2.query(new Equals<String>(new Value<String>(new Field("country"), "br")));
			while(_results.hasMoreElements()){
				_result = _results.nextElement();
				System.out.println(_result.get("description") + "(" + _result.get("code") + ")");
			}
			/*
			 * 
			System.out.println(_adapter.query(new Value<Object>("")));
			System.out.println(_adapter.query(new Value<Object>("16055703577")));
			
			final IQuoaAdapter<Object, Object> _atype = new JAXBAdapter(new File("D:\\study\\workspace\\beanoa\\src\\net\\sourceforge\\quoa\\xml\\AddressType.xml"));
			_results = _atype.query(new Value<Object>( _home.getType().getCode() ));
			_home.getType().setContent((String)_results.nextElement());
			System.out.println(_person);
			
			net.sourceforge.quoa.Enumeration<Object> _list = _adapter.query(new Field("name"));
			while(_list.hasMoreElements()){
				System.out.println(_list.nextElement());
			}
			_list.close();
			
			_list = _adapter.query(new Field("document"));
			final List<Object> _olist = Commons.getInstance().toList(_list);
			for(Object _o : _olist){
				System.out.println(_o);
			}
			
			_list.close();
			*/
		}catch(Exception _e){
			_e.printStackTrace();
		}
	}

}
