package net.sourceforge.quoa.adapter;

import net.sourceforge.quoa.Field;
import net.sourceforge.quoa.Mappings;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.exception.ValueNotFoundException;
import net.sourceforge.quoa.provider.ServiceProvider;
import net.sourceforge.quoa.util.ServiceLocator;

public abstract class AbstractAdapter<Q, R> implements IQuoaAdapter<Q, R> {

	private AdapterContext context;
	private AdapterHelper helper;
	private ServiceProvider serviceProvider;
	private Parameters<String, Object> parameters;
	private Mappings<String, String> mappings;
	
	public AbstractAdapter(){
		helper = new AdapterHelper();
		serviceProvider = ServiceLocator.getServiceProvider();
		
		parameters = new Parameters<String, Object>();
		mappings   = new Mappings<String, String>();
	}
	
	void validate(final Configuration configuration, final Parameters<String, Object> parameters) throws ValueNotFoundException{
		
		for(Configuration.Parameter _parameter : configuration.parameters()){
			if(!parameters.containsKey(_parameter.getName())){
				throw new ValueNotFoundException("key not found in parameters: " + _parameter.getName());
				
			} else {
				final Object _o = parameters.get(_parameter.getName());
				if(null!= _o){
					if(!_o.getClass().equals(_parameter.getType())){
						throw new ValueNotFoundException("type of parameter dis not match: " + _parameter.getName());
					}
				} else {
					throw new ValueNotFoundException("value of parameter is null: " + _parameter.getName());
				}
			}
		}
		
		this.parameters = parameters;
	}
	
	@Override
	public void setMappings(final Mappings<String, String> mappings) {
		this.mappings = mappings;
	}
	
	protected Mappings<String, String> getMappings(){
		return mappings;
	}
	
	protected Parameters<String, Object> getParameters(){
		return parameters;
	}
	
	protected String mapped(final Field field){
		return mapped(field.getName());
	}
	
	protected String mapped(final String field){
		return helper.mapped(field, mappings);
	}
	
	protected void setContext(final AdapterContext context){
		this.context = context;
	}
	
	public AdapterContext getContext(){
		if(null== context){
			context = new AdapterContext();
		}
		return context;
	}
	
	protected ServiceProvider getServiceProvider(){
		return serviceProvider;
	}
	
	protected AdapterHelper getHelper(){
		return helper;
	}
}
