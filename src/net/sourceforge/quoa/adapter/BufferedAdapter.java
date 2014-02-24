package net.sourceforge.quoa.adapter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sourceforge.beanoa.bean.Visitor;
import net.sourceforge.quoa.Enumeration;
import net.sourceforge.quoa.Mappings;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.bean.Period;
import net.sourceforge.quoa.bean.visitor.PeriodMilisecondsVisitor;
import net.sourceforge.quoa.criteria.CriteriaValue;
import net.sourceforge.quoa.exception.NonUniqueException;
import net.sourceforge.quoa.exception.QuoaException;
import net.sourceforge.quoa.exception.ValueNotFoundException;
import net.sourceforge.quoa.util.Commons;
import net.sourceforge.quoa.util.Constants;

public class BufferedAdapter<Q, R> extends AbstractAdapter<Q, R> implements IQuoaAdapter<Q, R> {
	private final Log log = LogFactory.getLog(BufferedAdapter.class);
	
	private static final Configuration CONFIGURATION = new Configuration();
	static{
		CONFIGURATION.addParameter(new Configuration.Parameter(Constants.PARAMETERS.URL, String.class));
		CONFIGURATION.addParameter(new Configuration.Parameter(Constants.PARAMETERS.LOCATION, String.class));
		CONFIGURATION.addParameter(new Configuration.Parameter(Constants.PARAMETERS.TIMEOUT, Period.class));
	};

	private URL url;
	private String location;
	private Period timeout;
	private Date latest;
	
	private IQuoaAdapter<Q, R> adapter;
	
	public BufferedAdapter(final IQuoaAdapter<Q, R> adapter){
		this.adapter = adapter;
	}

	@Override
	public void setParameters(final Parameters<String, Object> parameters) throws ValueNotFoundException, QuoaException {
		validate(CONFIGURATION, parameters);
		adapter.setParameters(parameters);
		
		//url to resource
		url = Commons.toURL((String)parameters.get(Constants.PARAMETERS.URL));
		
		//location to store data
		location = (String)parameters.get(Constants.PARAMETERS.LOCATION);
		
		//timeout to refresh the data
		timeout = (Period)parameters.get(Constants.PARAMETERS.TIMEOUT);
	}
	
	private Visitor<Period, Long> miliseconds = new PeriodMilisecondsVisitor();
	private void vbuffer() throws QuoaException {
		final Date _now = new Date();
		if(null== latest){
			latest = _now;
		}
		final long _time = _now.getTime() - latest.getTime();
		if(_time > timeout.accept(miliseconds) || _time == 0){
			log.info("buffering data...");
			try{
				// fetch data again
				final BufferedInputStream  _input = new BufferedInputStream( getServiceProvider().getIOProvider().open(url) );
				
				// create a new name to buffer in the location
				final UUID _id = UUID.randomUUID();
				final File _file = getServiceProvider().getIOProvider().createFile(location + File.separator + _id.toString());
				final BufferedOutputStream _output = new BufferedOutputStream(new FileOutputStream(_file));
				
				try{
					byte[] _buf = new byte[1024];
					int _length = 0;
					while( (_length = _input.read(_buf)) != -1){
						_output.write(_buf, 0, _length);
					}
					
					// change de URL in parameters to locate to location
					getParameters().put(Constants.PARAMETERS.URL, new URL(Constants.PROTOCOLS.FILE + _file.getAbsolutePath()));
				}finally{
					_input.close();
					_output.close();
				}
			}catch(IOException _e){
				throw new QuoaException(_e.getMessage(), _e); 
			}
			
			latest = new Date();
		}
	}

	@Override
	public R querySingle(final CriteriaValue<Q> criteria) throws QuoaException, NonUniqueException {
		vbuffer();//verify the buffer
		
		return adapter.querySingle(criteria);
	}

	@Override
	public R queryFirst(CriteriaValue<Q> criteria) throws QuoaException {
		vbuffer();//verify the buffer
		
		return adapter.queryFirst(criteria);
	}

	@Override
	public Enumeration<R> query(CriteriaValue<Q> criteria) throws QuoaException {
		vbuffer();//verify the buffer
		
		return adapter.query(criteria);
	}
	
	@Override
	public void setMappings(final Mappings<String, String> mappings) {
		adapter.setMappings(mappings);
	}
}
