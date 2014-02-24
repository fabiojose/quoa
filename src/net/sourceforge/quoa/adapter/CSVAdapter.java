package net.sourceforge.quoa.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.quoa.Enumeration;
import net.sourceforge.quoa.EnumerationList;
import net.sourceforge.quoa.Mappings;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.Value;
import net.sourceforge.quoa.criteria.Criteria;
import net.sourceforge.quoa.criteria.CriteriaValue;
import net.sourceforge.quoa.exception.QuoaException;
import net.sourceforge.quoa.exception.ValueNotFoundException;
import net.sourceforge.quoa.util.Commons;
import net.sourceforge.quoa.util.Constants;
import net.sourceforge.quoa.util.Constants.PARAMETERS;
import net.sourceforge.quoa.util.Constants.PROTOCOLS;

public final class CSVAdapter extends AbstractAdapterWrapper<String> implements IQuoaAdapterWrapper<String> {
	
	private static final Configuration CONFIGURATION = new Configuration();
	static{
		CONFIGURATION.addParameter(new Configuration.Parameter(Constants.PARAMETERS.URL, String.class));
		CONFIGURATION.addParameter(new Configuration.Parameter(Constants.PARAMETERS.SEPARATOR, String.class));
	};
	
	private Adapter cadapter;
	public CSVAdapter(){	
	}
	
	public CSVAdapter(final File input, final String separator, final Mappings<String, String> mappings) throws IOException{
		getParameters().put(Constants.PARAMETERS.URL, PROTOCOLS.FILE + input.getAbsolutePath());
		getParameters().put(Constants.PARAMETERS.SEPARATOR, separator);
		
		setMappings(mappings);
	}
	
	private URL getURL(){
		final Object _o = getParameters().get(PARAMETERS.URL);
		URL _result;
		if(_o instanceof URL){
			_result = (URL)_o;
		} else {
			_result = Commons.toURL((String)_o);
		}

		return _result;
	}
	
	private String getSeparator(){
		return (String)getParameters().get(PARAMETERS.SEPARATOR);
	}

	private final class EnumerationAdapter implements Enumeration<Object>{

		private BufferedReader reader;
		private String currentLine;
		private int listc;
		private String separator;
		
		private EnumerationAdapter(final BufferedReader reader, final String separator){
			this.reader = reader;
			this.listc  = -1;
			this.separator = separator;
		}
		
		private EnumerationAdapter(final BufferedReader reader, final int listc, final String separator){
			this(reader, separator);
			
			if(listc < 0){
				throw new IllegalArgumentException("listc accept numbers greater or equals to 0");
			}
			this.listc = listc;
		}
		
		@Override
		public boolean hasMoreElements() throws QuoaException {

			try{
				currentLine = reader.readLine();
				
				if(listc >= 0){
					if(null!= currentLine){
						final String[] _splited = currentLine.split(separator);
						currentLine = _splited[listc];
					}
				}
			}catch(IOException _e){
				throw new QuoaException(_e);
			}
			
			return null!= currentLine;
		}

		@Override
		public String nextElement() throws QuoaException {

			if(null== currentLine){
				hasMoreElements();
			}
			
			String _result = currentLine;
			currentLine = null;
			
			return _result;
		}

		@Override
		public void close() throws QuoaException {
			try{
				reader.close();
			}catch(IOException _e){
				throw new QuoaException(_e);
			}
		}
		
	}

	@Override
	public Enumeration<ResultWrapper<Object, String>> query(final CriteriaValue<String> criteria) throws QuoaException {

		final List<ResultWrapper<Object, String>> _result = new ArrayList<ResultWrapper<Object, String>>();
		
		BufferedReader _reader = null;
		try{
			final URL url = getURL();
			
			_reader = new BufferedReader(new InputStreamReader(url.openStream()));
			final EnumerationAdapter _enum = new EnumerationAdapter(_reader, getSeparator());
			while(_enum.hasMoreElements()){
				cadapter.setRecord(_enum.nextElement());
				final String _value = cadapter.get(mapped( criteria.left().getField() ));
				criteria.cache(_value);
				
				//evaluating de criteria test
				if(criteria.evaluate()){
					final ResultWrapper<Object, String> _wrapper = new ResultWrapper<Object, String>(new HashMap<Object, String>());
					// wrapper all fields
					for(String _field : getMappings().keySet()){
						_wrapper.put(_field, cadapter.get(mapped( _field )));
					}
					
					_result.add(_wrapper);
				}
			}
		}catch(IOException _e){
			throw new QuoaException(_e);
		}finally{
			
			if(null!= _reader){
				try{
					_reader.close();
				}catch(IOException _e){
				}
			}
		}
				
		return new EnumerationList<ResultWrapper<Object, String>>(_result);
	}

	/**
	 * Parameters: url
	 * 
	 * @throws ValueNotFoundException If a necessary parameters not found
	 */
	@Override
	public void setParameters(final Parameters<String, Object> parameters) throws ValueNotFoundException, QuoaException {
		validate(CONFIGURATION, parameters);
	}
	
	public void setMappings(final Mappings<String, String> mappings){
		super.setMappings(mappings);
		
		//columns adapter with value of mapping (source names)
		cadapter = new Adapter(new HashSet<String>(mappings.values()), getSeparator());
	}
	
	public static void main(String...args){
	
		final String _syntax = "columns()[99]";
				
		final Set<String> _columns = new HashSet<String>();
		_columns.add("columns()[0]");
		_columns.add("columns()[1]");
		_columns.add("columns()[2]");
		
		final Adapter _adapter = new Adapter(_columns, ";");
		_adapter.setRecord("FÁBIO J. MORAES;29;SERRA NEGRA");
		System.out.println(_adapter.get("columns()[0]"));
		System.out.println(_adapter.get("columns()[1]"));
		System.out.println(_adapter.get("columns()[2]"));
		System.out.println(_adapter.get("columns()[20]"));
		
		System.out.println(_syntax.matches("columns\\(\\)\\[[0-9]+\\]"));
		System.out.println(_syntax.matches(".*\\[[0-9]+\\]"));
		if(_syntax.matches("columns\\(\\)\\[[0-9]+\\]")){
			
		}
	}

	
	public Enumeration<ResultWrapper<Object, String>> query(Criteria<Criteria<Value<String>, String>, Criteria<Value<String>, String>> criteria) throws QuoaException {
		final Criteria<Value<String>, String> _left  = criteria.left();
		final Criteria<Value<String>, String> _right = criteria.right();
		
		final List<ResultWrapper<Object, String>> _result = new ArrayList<ResultWrapper<Object, String>>();
		
		BufferedReader _reader = null;
		try{
			final URL url = getURL();
			
			_reader = new BufferedReader(new InputStreamReader(url.openStream()));
			final EnumerationAdapter _enum = new EnumerationAdapter(_reader, getSeparator());
			while(_enum.hasMoreElements()){
				cadapter.setRecord(_enum.nextElement());
				final String _lvalue = cadapter.get(mapped( _left.left().getField() ));
				final String _rvalue = cadapter.get(mapped( _right.left().getField()));
				
				_left.cache(_lvalue);
				_right.cache(_rvalue);
				
				//evaluating de criteria test
				if(criteria.evaluate()){
					final ResultWrapper<Object, String> _wrapper = new ResultWrapper<Object, String>(new HashMap<Object, String>());
					// wrapper all fields
					for(String _field : getMappings().keySet()){
						_wrapper.put(_field, cadapter.get(mapped( _field )));
					}
					
					_result.add(_wrapper);
				}
			}
		}catch(IOException _e){
			throw new QuoaException(_e);
		}finally{
			
			if(null!= _reader){
				try{
					_reader.close();
				}catch(IOException _e){
				}
			}
		}
				
		return new EnumerationList<ResultWrapper<Object, String>>(_result);
	}
}

class Adapter{
	private static final String PATTERN = "columns\\(\\)\\[[0-9]+\\]";
	private static final String INDEX_PATTERN = "\\[[0-9]+\\]";
	private Set<String> columns;
	private String separator;
	private Map<String, String> mapping;
	public Adapter(final Set<String> columns, final String separator) throws IllegalArgumentException{							
		this.columns = columns;
		this.separator = separator;
	}
	
	private String[] splited;
	public void setRecord(final String record) throws IllegalArgumentException, QuoaException{
		splited = record.split(separator);
		if(splited.length != columns.size()){
			throw new IllegalArgumentException("Record does not contains all the columns: length = " + splited.length);
		}
		
		final Pattern _pattern = Pattern.compile(INDEX_PATTERN);
		mapping = new HashMap<String, String>();
		for(String _column : columns){
			if(_column.matches(PATTERN)){
				final Matcher _matcher = _pattern.matcher(_column);
				_matcher.find();
				final String _sindex = _column.substring(_matcher.start() + 1, _matcher.end() - 1);
				
				final int _index = Integer.parseInt(_sindex);
				try{
					mapping.put(_column, splited[_index]);
				}catch(ArrayIndexOutOfBoundsException _e){
					throw new QuoaException("Column not found: " + _index);
				}
			} else {
				throw new IllegalArgumentException("Column did not match (pattern = " + PATTERN + "): " + _column);
			}
		}
	}
	
	public String get(final String column) throws ValueNotFoundException{
		
		final String _result = mapping.get(column);
		if(null== _result){
			throw new ValueNotFoundException("Value of column not found: " + column);
		}
		
		return _result;
	}
};
