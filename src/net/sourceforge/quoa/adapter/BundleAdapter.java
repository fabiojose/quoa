package net.sourceforge.quoa.adapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import net.sourceforge.quoa.Enumeration;
import net.sourceforge.quoa.Parameters;
import net.sourceforge.quoa.criteria.CriteriaValue;
import net.sourceforge.quoa.exception.NonUniqueException;
import net.sourceforge.quoa.exception.QuoaException;
import net.sourceforge.quoa.exception.ValueNotFoundException;
import net.sourceforge.quoa.util.Constants.CHARS;
import net.sourceforge.quoa.util.Constants.STRINGS;
import net.sourceforge.quoa.util.StringCommons;
import net.sourceforge.quoa.util.Constants.PARAMETERS;

public class BundleAdapter<Q, R> extends AbstractAdapter<Q, R> implements IQuoaAdapter<Q, R>{
	
	private static final Configuration CONFIGURATION = new Configuration();
	static{
		CONFIGURATION.addParameter(new Configuration.Parameter(PARAMETERS.LOCALE, Locale.class));
		CONFIGURATION.addParameter(new Configuration.Parameter(PARAMETERS.URL, String.class));
	}
	
	public BundleAdapter(){
	
	}
	
	private Locale getLocale() throws QuoaException {
		final Object _o = getParameters().get(PARAMETERS.LOCALE);
		if(null!= _o){
			return (Locale)_o;
		}
		
		return Locale.getDefault();
	}
	
	private URL getLocalized() throws MalformedURLException, IOException {
		
		final URL _base = getHelper().getURL(getParameters());
		final String _sbase = _base.toExternalForm();
		
		URL _localized = null;
		final Locale _locale = getLocale();
		
		final int _index = StringCommons.lastIndexOf(CHARS.POINT, _sbase);
		
		// language + country
		final String _lc = _sbase.substring(0, _index) + STRINGS.UNDERSCORE + _locale.getLanguage() + STRINGS.UNDERSCORE + _locale.getCountry() + _sbase.substring(_index);
		
		_localized = new URL(_lc);
		
		return _localized;
	}
	
	@Override
	public void setParameters(final Parameters<String, Object> parameters) throws ValueNotFoundException, QuoaException {
		validate(CONFIGURATION, parameters);
	}

	@Override
	public R querySingle(CriteriaValue<Q> criteria) throws QuoaException, NonUniqueException {

		return null;
	}

	@Override
	public R queryFirst(CriteriaValue<Q> criteria) throws QuoaException {

		return null;
	}

	@Override
	public Enumeration<R> query(CriteriaValue<Q> criteria) throws QuoaException {

		return null;
	}
	
	public static void main(String...args){
		try{
			final URL _url = new URL("http://apache.org/download/units.csv");
			System.out.println(_url.getPath());
			System.out.println(_url.getFile());
			System.out.println(_url.toExternalForm());
			
			final BundleAdapter<String, String> _adapter = new BundleAdapter<String, String>();
			_adapter.getParameters().put(PARAMETERS.LOCALE, new Locale("en", "US"));
			_adapter.getParameters().put(PARAMETERS.URL, "http://localhost:8080/cimg/download/units.csv");
			
			System.out.println(_adapter.getLocalized());
		}catch(Exception _e){
			_e.printStackTrace();
		}
	}

}
