package net.sourceforge.quoa.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;

import net.sourceforge.quoa.exception.QuoaException;
import net.sourceforge.quoa.util.Constants.PATTERNS;

public final class Commons {

	private static Commons instance = new Commons();
	private Commons(){
	}
	
	public static synchronized Commons getInstance(){
		return instance;
	}
	
	public <T> List<T> toList(final Enumeration<T> enumeration){
		
		List<T> _result = new ArrayList<T>();
		while(enumeration.hasMoreElements()){
			_result.add(enumeration.nextElement());
		}
		
		return _result;
	}
	
	public static URL toURL(final String url) throws QuoaException {
		
		URL _result = null;
		try{
			_result = new URL(url);
		}catch(MalformedURLException _e){
			throw new QuoaException(_e.getMessage(), _e);
		}
		return _result;
	}
	
	public static boolean hasPlaceholder(final String placeholder) {
		final Matcher _matcher = PATTERNS.PLACEHOLDER.matcher(placeholder);		
		return _matcher.find();
	}
	
	public static String placeholder(final String placeholder) throws QuoaException {
		
		String _result = null;
		final Matcher _matcher = PATTERNS.PLACEHOLDER.matcher(placeholder);
		
		if(_matcher.find()){
			_result = placeholder.substring(_matcher.start() + 2, _matcher.end() - 1);
		} else {
			throw new QuoaException("Placeholder did not match: " + placeholder);
		}
		
		return _result;
	}
	
	public static String placeholder(final String placeholder, final String value) throws QuoaException {
		
		String _result = null;
		final Matcher _matcher = PATTERNS.PLACEHOLDER.matcher(placeholder);
		
		if(_matcher.find()){
			_result = _matcher.replaceAll(value);
		} else {
			throw new QuoaException("Placeholder did not match: " + placeholder);
		}
		return _result;
	}
	
	public static Date newDate(final int year, final int month, final int date){
		final Calendar _calendar = Calendar.getInstance();
		_calendar.set(year, month, date);
		
		return _calendar.getTime();
	}
	
	public static void main(String...args){
		System.out.println(placeholder("${code}.country"));
		System.out.println(placeholder("${code}.country", "sp"));
	}
}
