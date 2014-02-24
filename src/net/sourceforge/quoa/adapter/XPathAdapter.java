package net.sourceforge.quoa.adapter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import net.sourceforge.quoa.Enumeration;
import net.sourceforge.quoa.EnumerationList;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.Value;
import net.sourceforge.quoa.criteria.Bag;
import net.sourceforge.quoa.criteria.Criteria;
import net.sourceforge.quoa.criteria.CriteriaValue;
import net.sourceforge.quoa.exception.QuoaException;
import net.sourceforge.quoa.exception.ValueNotFoundException;
import net.sourceforge.quoa.util.Constants;

public final class XPathAdapter extends AbstractAdapterWrapper<String> implements IQuoaAdapterWrapper<String> {
	private static final Configuration CONFIGURATION = new Configuration();
	static{
		CONFIGURATION.addParameter(new Configuration.Parameter(Constants.PARAMETERS.URL, String.class));
	};
	
	@Override
	public void setParameters(final Parameters<String, Object> parameters) throws ValueNotFoundException, QuoaException {
		validate(CONFIGURATION, parameters);
	}
	
	private InputSource getSource() throws QuoaException{
		
		InputSource _result = null;
		try{
			final URL _url = getHelper().getURL(getParameters());
			
			_result = new InputSource(getServiceProvider().getIOProvider().open(_url));
		}catch(IOException _e){
			throw new QuoaException(_e.getMessage(), _e);
		}
		
		return _result;
	}
	
	private String prefix(){
		
		String _result = getMappings().getPrefix();
		if(null== _result){
			_result = "";
		}
		
		return _result;
	}
	
	private Node search(final Node in, final String prefix, final StringBuilder path){
		
		Node _result = null;
		if(null!= in){
			_result = search(in.getParentNode(), prefix, path);
			
			//is this the root node?
			if(in.getNodeType() != Node.DOCUMENT_NODE){
				path.append(in.getNodeName());
			}
			
			if(path.toString().equals(prefix) ||
					((prefix.equals("/") || prefix.equals("")) && in.getNodeType() == Node.DOCUMENT_NODE)){
				return in;
			}
			path.append("/");
		}
		
		return _result;
	}
	
	private void evaluate(final CriteriaValue<String> criteria, final List<ResultWrapper<Object, String>> wrapper, final Node node, final String prefix, final String field) throws QuoaException {
		if(criteria.evaluate()){
			final ResultWrapper<Object, String> _wrapper = new ResultWrapper<Object, String>(new HashMap<Object, String>());
			_wrapper.put(field, node.getNodeValue());
			
			//get the prefix node
			final Node _nprefix = search(node, prefix, new StringBuilder());
			if(_nprefix != null){
				try{
					// wrapping the another mapped attributes
					final Set<String> _attributes = getMappings().keySet();
					for(String _attribute : _attributes){
						if(!_wrapper.containsKey(_attribute)){
							final String _toSelect = mapped(_attribute);
							
							final Node _selected = XPathAPI.selectSingleNode(_nprefix, _toSelect);
							if(null!= _selected){
								_wrapper.put(_attribute, _selected.getNodeValue());
							}
						}
					}
					
					wrapper.add(_wrapper);
				}catch(TransformerException _e){
					throw new QuoaException(_e.getMessage(), _e);
				}
			} else {
				throw new QuoaException("Node note found: " + prefix);
			}
		}
	}
	
	private NodeList xpath(final String expression) throws XPathExpressionException {
		
		final InputSource _source = getSource();
		
		final XPath _xpath = XPathFactory.newInstance().newXPath();
		return (NodeList)_xpath.evaluate(expression, _source, XPathConstants.NODESET);
	}
	
	private NodeList xpath(final Criteria<Value<String>, String> criteria) throws XPathExpressionException {
		
		final String _field = criteria.left().getField().getName();
		// put the prefix
		final String _prefix = prefix();
		final String _expression = (!_prefix.equals("") ? _prefix + "/" : "") + mapped(_field);
		
		return xpath(_expression);
	}

	@Override
	public Enumeration<ResultWrapper<Object, String>> query(final CriteriaValue<String> criteria) throws QuoaException {

		final List<ResultWrapper<Object, String>> _result = new ArrayList<ResultWrapper<Object,String>>();

		try{
			final NodeList _nodes = xpath(criteria);
			if(_nodes.getLength() != 0){
				for(int _index = 0; _index < _nodes.getLength(); _index++){
					final Node _node = _nodes.item(_index);
					
					criteria.cache(_node.getNodeValue());
					evaluate(criteria, _result, _node, prefix(), criteria.left().getField().getName());
				}
			}
		}catch(XPathExpressionException _e){
			throw new QuoaException(_e.getMessage(), _e);
		}
		
		return new EnumerationList<ResultWrapper<Object,String>>(_result);
	}
	
	public Enumeration<ResultWrapper<Object, String>> query(final Bag<Value<String>, String> bag) throws QuoaException {
		
		final List<ResultWrapper<Object, String>> _result = new ArrayList<ResultWrapper<Object,String>>();
		try{
			// get de prefix node
			final NodeList _prefixes = xpath(prefix());
			for(int _index = 0; _index < _prefixes.getLength(); _index++) {
				final Node _prefixn = _prefixes.item(_index);
			
				// for each criteria do cache of value
				for(Criteria<Value<String>, String> _criteria : bag.criterias()){
					final String _mapped = mapped(_criteria.left().getField());
					final Node _node = XPathAPI.selectSingleNode(_prefixn, _mapped);
					
					String _value = null;
					if(null!= _node){
						_value = _node.getNodeValue();
					}
					_criteria.cache(_value);
				}
				
				if(bag.evaluate()){
					// wrap all the mappings values
					final ResultWrapper<Object, String> _wrapper = new ResultWrapper<Object, String>();
					final Set<String> _attributes = getMappings().keySet();
					for(String _attribute : _attributes){
						final String _field = mapped(_attribute);
						final Node _node = XPathAPI.selectSingleNode(_prefixn, _field);
						
						String _value = null;
						if(null!= _node){
							_value = _node.getNodeValue();
						}
						_wrapper.put(_attribute, _value);
					}
					
					_result.add(_wrapper);
				}
			}
			
		}catch(XPathExpressionException _e){
			throw new QuoaException(_e.getMessage(), _e);
		}catch(TransformerException _e){
			throw new QuoaException(_e.getMessage(), _e);
		}
		
		return new EnumerationList<ResultWrapper<Object,String>>(_result);
	}
}
