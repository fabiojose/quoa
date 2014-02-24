package net.sourceforge.quoa.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringCommons {

	private StringCommons(){
	}
	
	public static boolean match(final String s, final Pattern pattern){
		
		final Matcher _matcher = pattern.matcher(s);
		return _matcher.find();
	}
	
	public static boolean exact(final String s, final Pattern pattern){
		return s.matches(pattern.pattern());
	}
	
	public static int lastIndexOf(final char ch, final String s){
		
		int _result = s.indexOf(ch);
		int _index = 0;
		while( (_index = s.indexOf(ch, _result +1)) != -1){
			_result = _index;
		}
		
		return _result;
	}
	
	public static void main(String...args){
		System.out.println(lastIndexOf('.', "fabi.o.j.o.s.e.m.o.raes.d"));
	}
}
